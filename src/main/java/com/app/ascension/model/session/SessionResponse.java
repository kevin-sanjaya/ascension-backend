package com.app.ascension.model.session;

import com.app.ascension.model.response.OperationResponse;
import io.swagger.annotations.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
public class SessionResponse extends OperationResponse {
  @ApiModelProperty(required = true, value = "")
  private SessionItem item;
}
