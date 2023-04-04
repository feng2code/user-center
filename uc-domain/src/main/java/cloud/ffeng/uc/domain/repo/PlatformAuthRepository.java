package cloud.ffeng.uc.domain.repo;

import cloud.ffeng.uc.domain.entity.PlatformAuth;

public interface PlatformAuthRepository {

    /**
     * 保存平台授权流水
     *
     * @param platformAuth 平台授权流水
     * @return 平台授权流水
     */
    PlatformAuth save(PlatformAuth platformAuth);

    /**
     * 获取平台授权流水信息
     *
     * @param authId 授权ID
     * @return 平台授权
     */
    PlatformAuth get(String authId);


}
