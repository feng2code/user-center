package cloud.ffeng.uc.domain.service;

import cloud.ffeng.uc.domain.entity.User;
import cloud.ffeng.uc.domain.valobj.UserSession;

public interface UserDomainService {

    /**
     * 登录
     *
     * @param loginKey 登录key
     * @param password 密码
     * @return 登录用户
     */
    User validatePassword(String loginKey, String password);

    /**
     * 生成session信息
     *
     * @param userId 用户ID
     * @return sessionId
     */
    UserSession generateSession(String userId);


    /**
     * 创建新用户
     *
     * @param user 新用户创建
     * @return 用户
     */
    User createNewUser(User user);

    /**
     * 匹配用户
     *
     * @param openId 平台的ID
     */
    User matchUser(String openId);
}
