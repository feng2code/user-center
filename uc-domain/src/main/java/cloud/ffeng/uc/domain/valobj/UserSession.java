package cloud.ffeng.uc.domain.valobj;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserSession {

    private String sessionId;

    private String userId;

    private LocalDateTime expireTime;

}
