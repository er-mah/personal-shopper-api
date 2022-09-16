package com.mah.personalshopper.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class RevisionDatesInputDto {

    @NotNull
    public List<String> saleMechanicalRevisionDates;

}
