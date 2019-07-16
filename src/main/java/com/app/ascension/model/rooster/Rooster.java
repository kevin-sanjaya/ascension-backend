package com.app.ascension.model.rooster;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table(name = "roosters")
public class Rooster {

    @Id
    private String id;
    private String name;
    private String nickname;
    private Integer age;
    private String country;
    private String role;
    private String avatar;
    private Boolean isCaptain;
    private Integer divisionId;
}
