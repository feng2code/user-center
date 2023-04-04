package cloud.ffeng.uc.facade.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlatformStartAuthDTO implements Serializable {

    private String authUrl;

    private String authId;

    private String redirectUrl;


}
