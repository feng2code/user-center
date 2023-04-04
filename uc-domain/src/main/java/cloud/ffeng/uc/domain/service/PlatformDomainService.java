package cloud.ffeng.uc.domain.service;

import cloud.ffeng.uc.domain.entity.PlatformAuth;
import cloud.ffeng.uc.domain.valobj.PlatformAuthUser;
import cloud.ffeng.uc.types.enums.PlatformEnum;

public interface PlatformDomainService {

    /**
     * 授权码授权
     *
     * @param code   授权码
     * @param authId 授权流水ID
     * @return 授权用户信息
     */
    PlatformAuthUser auth(String code, String authId);

    /**
     * 创建新的授权流程
     *
     * @param platform    平台
     * @param redirectUrl 重定向链接
     * @return 平台授权信息
     */
    PlatformAuth createNewAuth(PlatformEnum platform, String redirectUrl);
}
