package cloud.ffeng.uc.domain.common.config;

import lombok.Getter;

public class ValidationConfig {
    @Getter
    private static int defaultExpireMinutes = 3;

    public void setDefaultExpireMinutes(int minutes) {
        if (minutes < 1) {
            throw new IllegalArgumentException("Validation expire minutes must be more than zero.");
        }
        defaultExpireMinutes = minutes;
    }

}
