package cloud.ffeng.uc.application.service.impl;

import cloud.ffeng.uc.application.assembler.PlatformAuthAssembler;
import cloud.ffeng.uc.application.service.PlatformAuthAppService;
import cloud.ffeng.uc.domain.entity.PlatformAuth;
import cloud.ffeng.uc.domain.service.PlatformDomainService;
import cloud.ffeng.uc.facade.dto.PlatformStartAuthDTO;
import cloud.ffeng.uc.facade.request.PlatformStartAuthRequest;
import cloud.ffeng.uc.types.enums.PlatformEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
@AllArgsConstructor
public class PlatformAuthAppServiceImpl implements PlatformAuthAppService {

    private final PlatformDomainService platformDomainService;

    @Override
    public PlatformStartAuthDTO startAuth(PlatformStartAuthRequest request) {
        // 1. 创建授权流程
        PlatformAuth platformAuth = platformDomainService.createNewAuth(PlatformEnum.of(request.getPlatform()), request.getRedirectUrl());

        // 2. 返回开始授权流程的信息
        return PlatformAuthAssembler.toUserStartAuthLoginDTO(platformAuth);
    }

}
