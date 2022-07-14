package com.mah.personalshopper.model;

import com.mah.personalshopper.model.enums.DeclaredState;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

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
    private UUID id;

    @Column(nullable = false)
    private Long year;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String version;

    @Column(nullable = false)
    private String licencePlate;

    @Column(nullable = false)
    private String colour;

    @Column(nullable = false)
    private Double kilometers;

    @Column(nullable = false)
    private Double sellingPrice;

    @Column(nullable = false)
    private Long saleTime;

    @Column(nullable = false)
    private DeclaredState state;

    @Column(nullable = false)
    private String comments;

    @CreatedDate
    private Date createdAt;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Owner owner;
    // String documentationUrl;

    @Override
    public String toString() {
        return "Vehicle{" + "id=" + id + ", brand='" + brand + '\'' + ", licencePlate='" + licencePlate + '\'' + ", model='" + model + '\'' + '}';
    }

}
