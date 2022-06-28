package com.mah.personalshopper.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Vehicle")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long year;

    @Column(nullable = false)
    private Brand brand;

    @Column(nullable = false)
    private String licencePlate;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String version;

    @Column(nullable = false)
    private DeclaredState state;

    @Column(nullable = false)
    private Colour colour;

    @Column(nullable = false)
    private Double kilometers;

    @Column(nullable = false)
    private Double sellingPrice;

    @Column(nullable = false)
    private Integer sellingTime;

    @CreatedDate
    private Date createdAt;
    private String comments;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Owner owner;
    // String documentationUrl;

    @Override
    public String toString() {
        return "Vehicle{" + "id=" + id + ", brand='" + brand + '\'' + ", licencePlate='" + licencePlate + '\'' + ", model='" + model + '\'' + '}';
    }

}
