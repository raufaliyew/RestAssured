package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ZippopotamPlace {


    @JsonProperty("place name")
    private String placeName;

    private String longitude;
    private String state;
    @JsonProperty("state abbreviation")
    private String stateAbbr;

    private String latitude;

}
