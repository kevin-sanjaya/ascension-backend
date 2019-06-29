package com.app.ascension.model.division;

import io.swagger.annotations.*;
import lombok.*;
import java.util.*;
import com.app.ascension.model.response.*;

@Data
@EqualsAndHashCode(callSuper=false)
public class DivisionResponse extends PageResponse {
    @ApiModelProperty(required = true, value = "")
    private List<Division> divisions;
}
