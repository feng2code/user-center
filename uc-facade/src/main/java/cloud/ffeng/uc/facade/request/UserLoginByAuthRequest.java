package cloud.ffeng.uc.facade.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class UserLoginByAuthRequest implements Serializable {

    @NotBlank(message = "授权码不能为空")
    private String code;

    @NotBlank(message = "授权ID不能为空")
    private String authId;

}
