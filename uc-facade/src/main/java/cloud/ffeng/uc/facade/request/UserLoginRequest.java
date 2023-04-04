package cloud.ffeng.uc.facade.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class UserLoginRequest implements Serializable {

    @NotBlank(message = "账号不能为空")
    private String loginKey;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "校验码获取失败，请重新获取")
    private String verificationId;

    @NotBlank(message = "校验码不能为空")
    private String verificationCode;

}
