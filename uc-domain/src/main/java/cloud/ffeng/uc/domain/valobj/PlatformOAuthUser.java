package cloud.ffeng.uc.domain.valobj;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlatformOAuthUser implements Serializable {

    private String platformUserId;

    private String avatarUrl;

    private String nickname;

    private String email;


}
