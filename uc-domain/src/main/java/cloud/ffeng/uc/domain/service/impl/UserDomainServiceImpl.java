package cloud.ffeng.uc.domain.service.impl;

import cloud.ffeng.common.util.AssertUtil;
import cloud.ffeng.common.util.UuidUtil;
import cloud.ffeng.uc.domain.common.util.LoginKeyUtil;
import cloud.ffeng.uc.domain.common.util.PasswordUtil;
import cloud.ffeng.uc.domain.entity.User;
import cloud.ffeng.uc.domain.repo.UserRepository;
import cloud.ffeng.uc.domain.service.UserDomainService;
import cloud.ffeng.uc.domain.valobj.UserSession;
import cloud.ffeng.uc.types.enums.LoginKeyTypeEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static cloud.ffeng.common.result.ResultStatus.BIZ_ERROR;
import static cloud.ffeng.uc.types.enums.LoginKeyTypeEnum.*;

@Service
@AllArgsConstructor
public class UserDomainServiceImpl implements UserDomainService {

    private final UserRepository userRepository;

    @Override
    public User validatePassword(String loginKey, String password) {
        LoginKeyTypeEnum loginKeyType = LoginKeyUtil.predicate(loginKey);
        User user = null;
        if (loginKeyType == EMAIL) {
            user = userRepository.getByEmail(loginKey);
        }
        if (loginKeyType == PHONE) {
            user = userRepository.getByPhone(loginKey);
        }
        if (loginKeyType == LOGIN_ID) {
            user = userRepository.getByLoginId(loginKey);
        }
        AssertUtil.isTrue(null != user && PasswordUtil.match(user.getEncryptedPassword(), password),
                BIZ_ERROR, "登录账号或密码错误！");

        return user;
    }

    @Override
    public UserSession generateSession(String userId) {
        User user = userRepository.get(userId);
        AssertUtil.nonNull(user, BIZ_ERROR, "生成会话信息错误，用户不存在！");

        // 记录登录日志
        UserSession userSession = new UserSession();
        userSession.setUserId(userId);
        userSession.setSessionId(UuidUtil.generateUuid());
        userRepository.saveLoginLog(user.newLoginLog(userSession.getSessionId()));

        userSession.setExpireTime(LocalDateTime.now().plusDays(1));

        // 保存session会话信息
        userRepository.saveSession(userSession);

        return userSession;
    }

    @Override
    public User createNewUser(User user) {
        return new User(UuidUtil.generateUuid(), "abc.email@qq.com", "13312341234", "abc",
                "abc", "nickname", "avator", LocalDateTime.now(), LocalDateTime.now());
    }

    @Override
    public User matchUser(String openId) {
        return null;
    }

}
