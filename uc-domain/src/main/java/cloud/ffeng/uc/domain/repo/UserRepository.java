package cloud.ffeng.uc.domain.repo;

import cloud.ffeng.uc.domain.entity.User;
import cloud.ffeng.uc.domain.valobj.UserLoginLog;
import cloud.ffeng.uc.domain.valobj.UserSession;

public interface UserRepository {

    /**
     * 保存用户信息
     *
     * @param user 用户
     * @return 保存结果
     */
    User save(User user);

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    User get(String userId);

    /**
     * 根据用户邮箱获取用户信息
     *
     * @param email email
     * @return 用户信息
     */
    User getByEmail(String email);

    /**
     * 根据用户手机号获取用户信息
     *
     * @param phone phone
     * @return 用户信息
     */
    User getByPhone(String phone);

    /**
     * 根据用户登录名获取用户信息
     *
     * @param loginId loginId
     * @return 用户信息
     */
    User getByLoginId(String loginId);

    /**
     * 保存用户登录session
     *
     * @param userLoginLog userSession
     */
    UserLoginLog saveLoginLog(UserLoginLog userLoginLog);


    /**
     * 会话保存会话映射信息
     *
     * @param userSession userSession
     */
    void saveSession(UserSession userSession);
}
