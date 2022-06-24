package com.mah.personalshopper.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Vehicle")
public class Vehicle implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    Integer year;
    String brand;
    String licencePlate;
    String model;
    String version;
    String state;
    String colour;
    Double kilometers;
    Double sellingPrice;
    Integer sellingTime;
    String documentationUrl;

}
