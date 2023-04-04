package cloud.ffeng.uc.application.service.impl;

import cloud.ffeng.uc.application.assembler.UserAssembler;
import cloud.ffeng.uc.application.service.UserRegisterAppService;
import cloud.ffeng.uc.domain.entity.User;
import cloud.ffeng.uc.domain.service.UserDomainService;
import cloud.ffeng.uc.domain.service.VerificationDomainService;
import cloud.ffeng.uc.domain.valobj.UserSession;
import cloud.ffeng.uc.facade.dto.UserRegisterDTO;
import cloud.ffeng.uc.facade.request.UserRegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRegisterAppServiceImpl implements UserRegisterAppService {

    private UserDomainService userDomainService;
    private VerificationDomainService verificationDomainService;

    @Override
    public UserRegisterDTO register(UserRegisterRequest request) {
        // 1. 验证码校验
        verificationDomainService.validate(request.getVerificationId(), request.getVerificationCode());

        // 2. 创建新用户
        User newUser = userDomainService.createNewUser(UserAssembler.buildNewUser(request));

        // 3. 创建session
        UserSession userSession = userDomainService.generateSession(newUser.getUserId());

        // 4. 返回注册结果
        return UserAssembler.toUserRegisterDTO(userSession);
    }

}
