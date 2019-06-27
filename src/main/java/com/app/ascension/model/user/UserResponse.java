package com.app.ascension.model.user;

import com.app.ascension.model.response.OperationResponse;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserResponse extends OperationResponse {
    private User data = new User();
}
