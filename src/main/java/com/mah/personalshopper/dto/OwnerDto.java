package com.mah.personalshopper.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class OwnerDto {

    String id;
    String fullName;
    String email;
    String tel;
    String address;
    String dni;

}
