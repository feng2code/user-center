package cloud.ffeng.uc.infra.oauth;

import cloud.ffeng.uc.types.enums.PlatformEnum;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
@Component
public @interface OAuth {

    PlatformEnum platform();
}
