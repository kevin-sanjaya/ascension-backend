package com.app.ascension.model.data;

import lombok.*;
import java.math.BigDecimal;

@Data
public class SingleSerise  {
    private String name;
    private BigDecimal value;

    public SingleSerise( String name, BigDecimal value){
        this.name  = name;
        this.value = value;
    }
}
