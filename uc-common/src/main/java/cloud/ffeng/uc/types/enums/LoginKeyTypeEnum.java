package cloud.ffeng.uc.types.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.regex.Pattern;

import static cloud.ffeng.uc.types.constants.UserConstants.*;

@Getter
@AllArgsConstructor
public enum LoginKeyTypeEnum {
    LOGIN_ID(LOGIN_ID_PATTERN),
    EMAIL(EMAIL_PATTERN),
    PHONE(PHONE_PATTERN),
    ;

    private final Pattern pattern;
}
