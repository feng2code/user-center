package cloud.ffeng.uc.domain.common.enums;


import cloud.ffeng.uc.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntityIdEnum {
    USER(0, "用户", "user", User.class),

    ;
    private final int code;
    private final String desc;
    private final String key;
    private final Class<?> clazz;
}
