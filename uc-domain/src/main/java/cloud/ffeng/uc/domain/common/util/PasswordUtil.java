package cloud.ffeng.uc.domain.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    public static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encode(String password) {
        return encoder.encode(password);
    }

    public static boolean match(String encodePassword, String password) {
        return encoder.matches(password, encodePassword);
    }

}
