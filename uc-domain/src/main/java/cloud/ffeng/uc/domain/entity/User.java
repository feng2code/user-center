package cloud.ffeng.uc.domain.entity;

import cloud.ffeng.uc.domain.valobj.UserLoginLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     * 纯数字
     */
    private String phone;

    /**
     * 登录号
     * [0-9][A-Za-z][_-]
     */
    private String loginId;

    /**
     * 密码
     */
    private String encryptedPassword;

    /**
     * 昵称
     * max length 15
     */
    private String nickname;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


    public UserLoginLog newLoginLog(String sessionId) {
        return new UserLoginLog();
    }


    public boolean isMale() {
        return true;
    }
}
