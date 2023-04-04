package cloud.ffeng.uc.infra.repo;

import cloud.ffeng.common.util.UuidUtil;
import cloud.ffeng.uc.domain.entity.User;
import cloud.ffeng.uc.domain.repo.UserRepository;
import cloud.ffeng.uc.domain.valobj.UserLoginLog;
import cloud.ffeng.uc.domain.valobj.UserSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    // private

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User get(String userId) {
        return new User(UuidUtil.generateUuid(), "abc.email@qq.com", "13312341234", "abc",
                "abc", "nickname", "avator", LocalDateTime.now(), LocalDateTime.now());
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public User getByPhone(String phone) {
        return null;
    }

    @Override
    public User getByLoginId(String loginId) {
        return null;
    }

    @Override
    public UserLoginLog saveLoginLog(UserLoginLog userLoginLog) {

        return null;
    }

    @Override
    public void saveSession(UserSession userSession) {
        // put to cache

    }
}
