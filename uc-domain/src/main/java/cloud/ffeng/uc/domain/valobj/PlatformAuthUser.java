package cloud.ffeng.uc.domain.valobj;

import cloud.ffeng.uc.types.enums.PlatformEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class PlatformAuthUser implements Serializable {
    private String redirectUrl;
    private String authId;
    private PlatformEnum platform;
    private String phone;
    private String email;
    private String platformUserId;
    private String platformOpenId;
    private String nickname;
    private String realName;
    private String desc;
    private String avatarUrl;

}
