package cloud.ffeng.uc.application.assembler;

import cloud.ffeng.common.util.UuidUtil;
import cloud.ffeng.uc.domain.entity.User;
import cloud.ffeng.uc.domain.valobj.PlatformAuthUser;
import cloud.ffeng.uc.domain.valobj.UserSession;
import cloud.ffeng.uc.facade.dto.UserLoginByAuthDTO;
import cloud.ffeng.uc.facade.dto.UserLoginDTO;
import cloud.ffeng.uc.facade.dto.UserRegisterDTO;
import cloud.ffeng.uc.facade.request.UserRegisterRequest;

import java.time.LocalDateTime;

public final class UserAssembler {

    public static UserLoginDTO toUserLoginDTO(UserSession userSession) {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUserId(userSession.getUserId());
        userLoginDTO.setSessionId(userSession.getSessionId());
        userLoginDTO.setExpireTime(userSession.getExpireTime());
        return userLoginDTO;
    }

    public static UserLoginByAuthDTO toUserLoginByAuthDTO(UserSession userSession) {
        UserLoginByAuthDTO userLoginByAuth = new UserLoginByAuthDTO();
        userLoginByAuth.setUserId(userSession.getUserId());
        userLoginByAuth.setSessionId(userSession.getSessionId());
        userLoginByAuth.setExpireTime(userSession.getExpireTime());
        return userLoginByAuth;
    }

    public static User buildNewUser(UserRegisterRequest request) {
        return null;
    }

    public static UserRegisterDTO toUserRegisterDTO(UserSession userSession) {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setUserId(userSession.getUserId());
        userRegisterDTO.setSessionId(userSession.getSessionId());
        userRegisterDTO.setExpireTime(userSession.getExpireTime());
        return userRegisterDTO;
    }

    public static User buildNewUser(PlatformAuthUser authUser) {
        return new User(UuidUtil.generateUuid(), "abc.email@qq.com", "13312341234", "abc",
                "abc", "nickname", "avator", LocalDateTime.now(), LocalDateTime.now());
    }

}
