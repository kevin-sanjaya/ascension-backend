package com.app.ascension.model.division;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table(name = "divisions")
public class Division  {

    @Id
    private Integer id;
    private String name;

    public void setId(int id) {
        this.id = ++id;
    }
}
