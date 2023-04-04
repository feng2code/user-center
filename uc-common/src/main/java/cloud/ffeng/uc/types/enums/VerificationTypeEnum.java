package cloud.ffeng.uc.types.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VerificationTypeEnum {
    IMG_CODE(100, "图片验证码"),
    EMAIL_CODE(200, "邮箱验证码"),
    PHONE_CODE(300, "手机验证码"),
    ;

    private final int code;
    private final String desc;
}
