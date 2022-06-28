package com.mah.personalshopper.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Owner")
public class Owner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String tel;
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
