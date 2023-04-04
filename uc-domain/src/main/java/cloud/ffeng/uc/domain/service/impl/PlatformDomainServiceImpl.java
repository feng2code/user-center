package cloud.ffeng.uc.domain.service.impl;

import cloud.ffeng.common.util.AssertUtil;
import cloud.ffeng.common.util.UuidUtil;
import cloud.ffeng.uc.domain.entity.PlatformAuth;
import cloud.ffeng.uc.domain.repo.PlatformAuthRepository;
import cloud.ffeng.uc.domain.repo.PlatformOAuthRepository;
import cloud.ffeng.uc.domain.service.PlatformDomainService;
import cloud.ffeng.uc.domain.valobj.PlatformAuthUser;
import cloud.ffeng.uc.types.enums.PlatformEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static cloud.ffeng.common.result.ResultStatus.BIZ_ERROR;


@Slf4j
@Service
@AllArgsConstructor
public class PlatformDomainServiceImpl implements PlatformDomainService {

    private final PlatformAuthRepository platformAuthRepository;
    private final PlatformOAuthRepository platformOAuthRepository;


    @Override
    public PlatformAuthUser auth(String code, String authId) {
        // 1. 获取授权流水信息
        PlatformAuth platformAuth = platformAuthRepository.get(authId);
        AssertUtil.nonNull(platformAuth, BIZ_ERROR, "授权信息不存在！");

        // 2. 获取平台授权用户的信息
        PlatformAuthUser authUser = platformOAuthRepository.getAuthUser(platformAuth.getPlatform(), code, authId);
        AssertUtil.nonNull(authUser, BIZ_ERROR, "获取授权用户信息失败");

        // 3. 记录当前授权流水的重定向地址信息
        authUser.setRedirectUrl(platformAuth.getRedirectUrl());
        return authUser;
    }

    @Override
    public PlatformAuth createNewAuth(PlatformEnum platform, String redirectUrl) {
        String authId = UuidUtil.generateUuid();

        // 1. 获取指定平台的授权链接
        String authUrl = platformOAuthRepository.getAuthUrl(platform, authId);

        // 2. 保存授权流水信息
        return platformAuthRepository.save(new PlatformAuth(authId, redirectUrl, platform, authUrl, LocalDateTime.now(), LocalDateTime.now()));
    }

}
