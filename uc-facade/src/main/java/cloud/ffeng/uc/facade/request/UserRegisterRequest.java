package cloud.ffeng.uc.facade.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "校验码获取失败，请重新获取")
    private String verificationId;

    @NotBlank(message = "校验码不能为空")
    private String verificationCode;

}
