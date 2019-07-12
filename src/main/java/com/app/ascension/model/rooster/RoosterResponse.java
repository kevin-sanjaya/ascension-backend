package com.app.ascension.model.rooster;

import io.swagger.annotations.*;
import lombok.*;
import java.util.*;
import com.app.ascension.model.response.*;

@Data
@EqualsAndHashCode(callSuper=false)
public class RoosterResponse extends PageResponse {
    @ApiModelProperty(required = true, value = "")
    private List<Rooster> roosters;
}
