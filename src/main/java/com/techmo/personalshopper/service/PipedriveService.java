package com.techmo.personalshopper.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techmo.personalshopper.dto.PersonalShopperInputDto;
import com.techmo.personalshopper.dto.ResponseDto;
import com.techmo.personalshopper.dto.RevisionDatesInputDto;
import com.techmo.personalshopper.dto.SaleTypeInputDto;
import com.techmo.personalshopper.dto.pipedrive.*;
import com.techmo.personalshopper.util.MiscMethods;
import com.techmo.personalshopper.dto.pipedrive.*;
import com.techmo.personalshopper.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;


@Service
public class PipedriveService {

    @Value("${pipedrive.token}")
    private String token;

    HttpClient client;
    ObjectMapper objectMapper;

    public PipedriveService() {

        this.objectMapper = new ObjectMapper();
        this.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.client = HttpClient.newHttpClient();

    }

    @Autowired
    public void GetProperties(@Value("${pipedrive.token}") String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public Pipeline[] getAllPipelines() {
        try {
            URI url = URI.create(ControllerConstants.PIPEDRIVE_BASE_URI + "/pipelines?api_token=" + token);
            String bearerToken = "Bearer " + getToken();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Authorization", bearerToken)
                    .GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            PipelinesDto dto = objectMapper.readValue(response.body(), PipelinesDto.class);

            return dto.data;

        } catch (Exception e) {
            return null;
        }
    }

    public Pipeline createPipeline(String name, Boolean active) {

        try {

            URI url = URI.create(ControllerConstants.PIPEDRIVE_BASE_URI + "/pipelines?api_token=" + token);
            String bearerToken = "Bearer " + getToken();

            // Define payload
            HashMap<String, Object> values = new HashMap<>() {{
                put("name", name);
                put("active", active);
            }};
            String payload = objectMapper.writeValueAsString(values);

            // Create request object
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(url)
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .header("Authorization", bearerToken)
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            PipelineDto dto = objectMapper.readValue(response.body(), PipelineDto.class);

            return dto.data;

        } catch (Exception e) {
            return null;
        }
    }

    public Stage[] getStagesByPipelineId(Long pipelineId) {
        try {

            URI url = URI.create(ControllerConstants.PIPEDRIVE_BASE_URI + "/stages?api_token=" + token + "&pipeline_id=" + pipelineId);
            String bearerToken = "Bearer " + getToken();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Authorization", bearerToken)
                    .GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            StagesDto dto = objectMapper.readValue(response.body(), StagesDto.class);

            return dto.data;

        } catch (Exception e) {
            return null;
        }
    }

    public Stage addStageToPipeline(String name, Long pipelineId) {
        try {

            URI url = URI.create(ControllerConstants.PIPEDRIVE_BASE_URI + "/stages?api_token=" + token);
            String bearerToken = "Bearer " + getToken();

            // Define payload
            HashMap<String, Object> values = new HashMap<>() {{
                put("name", name);
                put("pipeline_id", pipelineId);
            }};
            String payload = objectMapper.writeValueAsString(values);

            // Create request object
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(url)
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .header("Authorization", bearerToken)
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            StageDto dto = objectMapper.readValue(response.body(), StageDto.class);

            return dto.data;

        } catch (Exception e) {
            return null;
        }
    }

    public Person getPersonByTerm(String term, String type) {
        try {

            // By DNI
            URI url = URI.create(ControllerConstants.PIPEDRIVE_BASE_URI + "/persons/search?api_token=" + token + "&term=" + term + "&fields=custom_fields");

            // By name
            if (type.equals("name")) {
                url = URI.create(ControllerConstants.PIPEDRIVE_BASE_URI + "/persons/search?api_token=" + token + "&term=" + term + "&fields=name");
            }

            String bearerToken = "Bearer " + getToken();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Authorization", bearerToken)
                    .GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            PersonsDto dto = objectMapper.readValue(response.body(), PersonsDto.class);

            if (dto.data.items.isEmpty()) {
                return null;
            }
            // Get the first person from the list
            return dto.data.items.get(0).item;

        } catch (Exception e) {
            return null;
        }
    }

    public Person createPerson(String name, String email, String phone, String dni) {
        try {

            URI url = URI.create(ControllerConstants.PIPEDRIVE_BASE_URI + "/persons?api_token=" + token);
            String bearerToken = "Bearer " + getToken();

            String formattedName = MiscMethods.capitalizeFirstLetterOfEachWord(name);

            // Define payload
            HashMap<String, String> values = new HashMap<>();
            values.put("name", formattedName);
            values.put("email", email);
            values.put("phone", phone);
            values.put(ControllerConstants.PIPEDRIVE_DNI_HASH, dni);

            String payload = objectMapper.writeValueAsString(values);

            // Create request object
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(url)
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .header("Authorization", bearerToken)
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            PersonDto dto = objectMapper.readValue(response.body(), PersonDto.class);

            return dto.data;

        } catch (Exception e) {
            return null;
        }
    }

    public Deal createDeal(String title, Long personId, Long stageId, String vehicleBrand, Integer vehicleYear, String vehicleModel, String vehicleVersion) {
        try {

            URI url = URI.create(ControllerConstants.PIPEDRIVE_BASE_URI + "/deals" + "?api_token=" + token);
            String bearerToken = "Bearer " + getToken();

            // Define payload
            HashMap<String, Object> values = new HashMap<>() {{
                put("title", title);
                put("person_id", personId.toString());
                put("stage_id", stageId.toString());
                put("visible_to", 3);
                put(ControllerConstants.PIPEDRIVE_VEHICLE_BRAND_HASH, vehicleBrand);
                put(ControllerConstants.PIPEDRIVE_VEHICLE_YEAR_HASH, vehicleYear);
                put(ControllerConstants.PIPEDRIVE_VEHICLE_MODEL_HASH, vehicleModel);
                put(ControllerConstants.PIPEDRIVE_VEHICLE_VERSION_HASH, vehicleVersion);
                put(ControllerConstants.PIPEDRIVE_INPUT_CHANNEL, "Personal Shopper WEB");
            }};

            String payload = objectMapper.writeValueAsString(values);

            // Create request object
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Authorization", bearerToken)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            DealDto dto = objectMapper.readValue(response.body(), DealDto.class);

            return dto.data;

        } catch (Exception e) {
            return null;
        }
    }

    public Note addNoteToDeal(String content, Long dealId) {
        try {

            URI url = URI.create(ControllerConstants.PIPEDRIVE_BASE_URI + "/notes?api_token=" + token);
            String bearerToken = "Bearer " + getToken();

            // Define payload
            HashMap<String, Object> values = new HashMap<>() {{
                put("deal_id", dealId);
                put("content", content);
            }};
            String payload = objectMapper.writeValueAsString(values);

            // Create request object
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(url)
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .header("Authorization", bearerToken)
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            NoteDto dto = objectMapper.readValue(response.body(), NoteDto.class);

            return dto.data;

        } catch (Exception e) {
            return null;
        }
    }

    public ResponseDto<Deal> persistPersonalShopperData(PersonalShopperInputDto dto) {

        try {
            Long pipelineId = 0L;
            Long stageId = 0L;
            Long personId = 0L;
            Long dealId = 0L;
            String dealTitle = dto.vehicleBrand + " " + dto.vehicleVersion;

            Pipeline[] pipelines = this.getAllPipelines();

            for (Pipeline pipeline : pipelines) {
                if (pipeline.name.equals("Personal Shopper")) {
                    pipelineId = pipeline.id;
                }
            }
            // if pipeline not found, create a new pipeline
            if (pipelineId == 0L) {
                Pipeline newPipeline = this.createPipeline("Personal Shopper", true);
                assert newPipeline != null;
                pipelineId = newPipeline.id;
            }

            Stage[] stages = this.getStagesByPipelineId(pipelineId);
            if (stages != null) {
                for (Stage stage : stages) {
                    if (stage.name.equals("INGRESO DE LEAD")) {
                        stageId = stage.id;
                    }
                }
            }


            // if stage not found, create a new stage
            if (stageId == 0L) {
                Stage newStage = this.addStageToPipeline("INGRESO DE LEAD", pipelineId);
                this.addStageToPipeline("CONTACTO", pipelineId);
                this.addStageToPipeline("REVISIÓN MECÁNICA", pipelineId);
                this.addStageToPipeline("OFERTA DE COMPRA", pipelineId);
                this.addStageToPipeline("DOCUMENTACIÓN OK", pipelineId);
                this.addStageToPipeline("PUBLICADO", pipelineId);
                this.addStageToPipeline("VENDIDO", pipelineId);
                assert newStage != null;
                stageId = newStage.id;
            }

            // Get person by name
            Person person = this.getPersonByTerm(dto.ownerName, "name");
            if (person == null) {
                person = this.getPersonByTerm(dto.ownerDni, "dni");
            }
            if (person == null) {
                person = this.createPerson(dto.ownerName, dto.ownerEmail, dto.ownerPhone, dto.ownerDni);
            }
            assert person != null;
            personId = person.id;

            Deal deal = this.createDeal(dealTitle, personId, stageId, dto.vehicleBrand, dto.vehicleYear, dto.vehicleModel, dto.vehicleVersion);
            assert deal != null;
            dealId = deal.id;

            String content = "<b>Trato con " + MiscMethods.capitalizeFirstLetterOfEachWord(dto.ownerName) + "</b>" +
                    "<br><br>⚠️ Verificar que los datos del contacto coincidan con el contacto anexado al deal/trato<br><br>" +
                    "<br><br><u>Datos de la venta</u>: <div><ul>" +
                    "<li>Cantidad solicitada por el cliente: "+ dto.saleRequestedAmount +"&nbsp;</li>" +
                    "<li>Moneda: "+ dto.saleCurrency +"&nbsp;</li>" +
                    "<li>Urgencia: "+ dto.saleUrgency +"&nbsp;</li>" +
                    "<li>Valor base de cotización: "+ dto.saleBaseQuotationValue +"&nbsp;</li>" +
                    "<li>Rango ofrecido al cliente: "+ dto.saleQuotationRange +"&nbsp;</li>" +
                    "<br>" +
                    "</ul><br><u>Datos del dueño</u>:<br><ul>" +
                    "<li>Sexo: "+ dto.ownerSex +"&nbsp;</li>" +
                    "<li>Nombre: "+ dto.ownerName +"&nbsp;</li>" +
                    "<li>DNI: "+ dto.ownerDni +"&nbsp;</li>" +
                    "<li>CUIL/CUIT: "+ dto.ownerCuil +"&nbsp;</li>" +
                    "<li>Email: "+ dto.ownerEmail +"&nbsp;</li>" +
                    "<li>Teléfono: "+ dto.ownerPhone +"&nbsp;</li>" +
                    "<li>Código postal: "+ dto.ownerPostalCode +"&nbsp;</li>" +
                    "</ul><br><u>Datos del vehículo</u>:<br><ul>" +
                    "<li>Año: "+ dto.vehicleYear +"&nbsp;</li>" +
                    "<li>Marca: "+ dto.vehicleBrand +"&nbsp;</li>" +
                    "<li>Modelo: "+ dto.vehicleModel +"&nbsp;</li>" +
                    "<li>Versión: "+ dto.vehicleVersion +"&nbsp;</li>" +
                    "<li>Color: "+ dto.vehicleColor +"&nbsp;</li>" +
                    "<li>Patente: "+ dto.vehicleLicensePlate +"&nbsp;</li>" +
                    "<li>Kilometraje: "+ dto.vehicleKilometers +"&nbsp;</li>" +
                    "<li>Estado: "+ dto.vehicleState +"&nbsp;</li>" +
                    "<li>Comentarios adicionales: "+ dto.vehicleComments +"&nbsp;</li>" +
                    "</ul></div>";
            this.addNoteToDeal(content, dealId);
            return new ResponseDto<>(HttpStatus.CREATED, "Personal shopper data sucessfully stored.", deal);

        } catch (Exception e) {
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.toString(), null);
        }
    }

    public ResponseDto<Object> addRevisionDatesToDeal(RevisionDatesInputDto dto, Long dealId) {

        try {
            String content = "<b>Disponibilidad para revisión mecánica: </b>"+ dto.saleMechanicalRevisionDates;
            this.addNoteToDeal(content, dealId);
            return new ResponseDto<>(HttpStatus.CREATED, "Dates stored in deal.", null);

        } catch (Exception e) {
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.toString(), null);
        }
    }

    public ResponseDto<Object> addSelectedSaleType(SaleTypeInputDto dto, Long dealId) {

        try {
            String content = "<b>Tipo de venta seleccionado por el cliente: </b>"+ dto.saleType;
            this.addNoteToDeal(content, dealId);
            return new ResponseDto<>(HttpStatus.CREATED, "Sale type stored in deal.", null);

    public ResponseDto<Object> deleteDeal(Long dealId) {
        try {

            URI url = URI.create(ControllerConstants.PIPEDRIVE_BASE_URI + "/deals/"+ dealId + "?api_token=" + token);
            String bearerToken = "Bearer " + getToken();

            // Create request object
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(url)
                    .DELETE()
                    .header("Authorization", bearerToken)
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return new ResponseDto<>(HttpStatus.OK, "Deal successfully deleted", response.body());

        } catch (Exception e) {
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, e.toString(), null);
        }
    }
}
