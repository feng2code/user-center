package cloud.ffeng.uc.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO implements Serializable {
    private String userId;
    private String sessionId;
    private LocalDateTime expireTime;

}
