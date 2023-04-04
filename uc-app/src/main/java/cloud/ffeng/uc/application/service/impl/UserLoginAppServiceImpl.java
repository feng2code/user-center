package cloud.ffeng.uc.application.service.impl;

import cloud.ffeng.uc.application.assembler.UserAssembler;
import cloud.ffeng.uc.application.service.UserLoginAppService;
import cloud.ffeng.uc.domain.entity.User;
import cloud.ffeng.uc.domain.service.PlatformDomainService;
import cloud.ffeng.uc.domain.service.UserDomainService;
import cloud.ffeng.uc.domain.service.VerificationDomainService;
import cloud.ffeng.uc.domain.valobj.PlatformAuthUser;
import cloud.ffeng.uc.domain.valobj.UserSession;
import cloud.ffeng.uc.facade.dto.UserLoginByAuthDTO;
import cloud.ffeng.uc.facade.dto.UserLoginDTO;
import cloud.ffeng.uc.facade.request.UserLoginByAuthRequest;
import cloud.ffeng.uc.facade.request.UserLoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 用户登录应用服务
 *
 * @author feng
 */
@Service
@AllArgsConstructor
public class UserLoginAppServiceImpl implements UserLoginAppService {

    private final UserDomainService userDomainService;
    private final PlatformDomainService platformDomainService;
    private final VerificationDomainService verificationDomainService;

    @Override
    public UserLoginDTO login(UserLoginRequest request) {
        // 1. 验证码校验
        verificationDomainService.validate(request.getVerificationId(), request.getVerificationCode());

        // 2. 账号密码校验
        User user = userDomainService.validatePassword(request.getLoginKey(), request.getPassword());

        // 3. Session生成
        UserSession session = userDomainService.generateSession(user.getUserId());

        // 4. 返回登录结果信息
        return UserAssembler.toUserLoginDTO(session);
    }

    @Override
    public UserLoginByAuthDTO loginByAuth(UserLoginByAuthRequest request) {
        // 1. 授权处理
        PlatformAuthUser authUser = platformDomainService.auth(request.getCode(), request.getAuthId());

        // 2. 判断是否存在用户
        User user = userDomainService.matchUser(authUser.getPlatformUserId());

        // 3. 不存在就新增
        if (Objects.isNull(user)) {
            user = userDomainService.createNewUser(UserAssembler.buildNewUser(authUser));
        }

        // 4. 存在用户就直接执行登录
        UserLoginByAuthDTO userLoginByAuthDTO = UserAssembler.toUserLoginByAuthDTO(userDomainService.generateSession(user.getUserId()));
        userLoginByAuthDTO.setRedirectUrl(authUser.getRedirectUrl());
        return userLoginByAuthDTO;
    }

}
