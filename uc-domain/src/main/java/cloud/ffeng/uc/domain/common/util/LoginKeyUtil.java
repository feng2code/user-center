package cloud.ffeng.uc.domain.common.util;

import cloud.ffeng.uc.types.enums.LoginKeyTypeEnum;

public class LoginKeyUtil {

    public static LoginKeyTypeEnum predicate(String loginKey) {
        if (LoginKeyTypeEnum.PHONE.getPattern().matcher(loginKey).matches()) {
            return LoginKeyTypeEnum.PHONE;
        }
        if (LoginKeyTypeEnum.EMAIL.getPattern().matcher(loginKey).matches()) {
            return LoginKeyTypeEnum.EMAIL;
        }
        return LoginKeyTypeEnum.LOGIN_ID;
    }
}
