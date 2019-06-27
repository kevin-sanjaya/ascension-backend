package com.app.ascension.model.response;

import com.app.ascension.model.data.SingleSerise;
import lombok.*;
import java.util.*;

@Data
@EqualsAndHashCode(callSuper=false)
public class SingleDataSeriseResponse extends OperationResponse {
    private List<SingleSerise> items;
}
