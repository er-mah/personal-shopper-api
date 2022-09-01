package com.mah.personalshopper.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Setter
@Getter
@NoArgsConstructor
public class PriceInputDto {
    @NotNull
    public Integer year;
    @NotNull
    public Integer versionId;
    @NotNull
    public String state;
    @NotNull
    public String kilometers;
    @NotNull
    public String sellingTime;
    @NotNull
    public String colour;
    @NotNull
    public String age;

}
