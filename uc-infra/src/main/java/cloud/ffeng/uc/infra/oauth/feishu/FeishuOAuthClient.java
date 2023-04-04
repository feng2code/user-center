package cloud.ffeng.uc.infra.oauth.feishu;


import cloud.ffeng.uc.domain.valobj.PlatformAuthUser;
import cloud.ffeng.uc.infra.oauth.OAuth;
import cloud.ffeng.uc.infra.oauth.OAuthClient;

import static cloud.ffeng.uc.types.enums.PlatformEnum.FEI_SHU;

@OAuth(platform = FEI_SHU)
public class FeishuOAuthClient implements OAuthClient {

    @Override
    public String authUrl(String state) {
        return null;
    }

    @Override
    public PlatformAuthUser getOAuthUser(String code, String state) {
        return null;
    }

}
