package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Zippopotam {

    @JsonProperty("post code")
    private String postCode;

    private String country;
    @JsonProperty("country abbreviation")
    private String countryAbr;

    private List<ZippopotamPlace> places;



}
