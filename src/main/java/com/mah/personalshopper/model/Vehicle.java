package com.mah.personalshopper.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Vehicle")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    private Long year;
    private Brand brand;
    private String licencePlate;
    private String model;
    private String version;
    private DeclaredState state;
    private Colour colour;
    private Double kilometers;
    private Double sellingPrice;
    private Integer sellingTime;

    @CreatedDate
    private Date createdAt;
    // String documentationUrl;

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", licencePlate='" + licencePlate + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

}
