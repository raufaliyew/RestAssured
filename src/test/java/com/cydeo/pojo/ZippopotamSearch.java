package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ZippopotamSearch {
    @JsonProperty("country abbreviation")
    private String countryAbbr;
    private List<ZippopotamPlaceSearchByState> places;
    private String country;
    @JsonProperty("place name")
    private String placeName;
    private String state;
    @JsonProperty("state abbreviation")
    private String stateAbbr;

}
