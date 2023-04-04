package cloud.ffeng.uc.types.constants;

import java.util.regex.Pattern;

public class UserConstants {

    public static final Pattern LOGIN_ID_PATTERN = Pattern.compile("[A-Za-z][0-9A-Za-z_-]{3,31}");
    public static final Pattern EMAIL_PATTERN = Pattern.compile("[0-9A-Za-z.!#$%^&*_-]{4,32}@[A-Za-z0-9_-]{1,16}]\\.[A-Za-z0-9._-]{1,16}");
    public static final Pattern PHONE_PATTERN = Pattern.compile("[0-9]{4,16}");

}
