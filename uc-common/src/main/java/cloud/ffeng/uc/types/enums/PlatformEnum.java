package cloud.ffeng.uc.types.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;


@Getter
@AllArgsConstructor
public enum PlatformEnum {
    ALIPAY(100, "支付宝"),

    WECHAT(200, "微信"),
    DING_TALK(300, "钉钉"),
    FEI_SHU(400, "飞书"),
    GITHUB(500, "Github"),
    ;

    private final int code;
    private final String desc;

    public static PlatformEnum of(Integer code) {
        return Stream.of(values()).filter(item -> code.equals(item.getCode())).findFirst().orElse(null);
    }
}
