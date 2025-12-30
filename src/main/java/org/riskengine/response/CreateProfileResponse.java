package org.riskengine.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProfileResponse implements BaseResponse {
    String rc;
    String message;
    String status;

}
