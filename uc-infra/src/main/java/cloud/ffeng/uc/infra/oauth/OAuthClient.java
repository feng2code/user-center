package cloud.ffeng.uc.infra.oauth;

import cloud.ffeng.uc.domain.valobj.PlatformAuthUser;

public interface OAuthClient {

    String authUrl(String state);

    PlatformAuthUser getOAuthUser(String code, String state);

}
