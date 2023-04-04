package cloud.ffeng.uc.domain.valobj;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserLoginLog {

    private String userId;

    private String sessionId;

    private String loginIp;

    private String bizType;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
