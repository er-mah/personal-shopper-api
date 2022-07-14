package com.mah.personalshopper.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Owner")
public class Owner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String dni;

    @OneToOne(mappedBy = "owner")
    private Vehicle vehicle;

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
