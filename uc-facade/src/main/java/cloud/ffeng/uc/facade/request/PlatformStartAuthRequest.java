package cloud.ffeng.uc.facade.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlatformStartAuthRequest implements Serializable {

    private String redirectUrl;

    private Integer platform;

}
