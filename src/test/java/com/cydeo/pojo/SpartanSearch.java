package com.cydeo.pojo;

import com.cydeo.utils.SpartanTestBase;
import lombok.Data;

import java.util.List;

@Data
public class SpartanSearch extends SpartanTestBase {
    private List<Spartan> content;
    private int totalElement;


}
