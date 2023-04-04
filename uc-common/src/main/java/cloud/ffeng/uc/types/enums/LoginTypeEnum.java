package cloud.ffeng.uc.types.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginTypeEnum {
    USER_PWD(100, "账号密码登录"),
    EMAIL_CODE(100, "邮箱验证码登录"),
    PHONE_CODE(100, "手机验证码登录"),
    QR_CODE(200, "扫码登录"),
    OAUTH2(300, "OAuth2授权登录"),

    ;

    private final int code;
    private final String desc;
}
