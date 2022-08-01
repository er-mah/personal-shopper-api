package com.mah.personalshopper.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Setter
@Getter
@NoArgsConstructor
public class PriceInputDto {

    public Integer year;
    public Integer versionId;

    public String state;
    public String kilometers;
    public String sellingTime;
    public String colour;

}
