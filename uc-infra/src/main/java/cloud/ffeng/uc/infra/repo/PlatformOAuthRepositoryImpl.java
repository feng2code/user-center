package cloud.ffeng.uc.infra.repo;

import cloud.ffeng.common.exception.BizException;
import cloud.ffeng.uc.domain.repo.PlatformOAuthRepository;
import cloud.ffeng.uc.domain.valobj.PlatformAuthUser;
import cloud.ffeng.uc.infra.oauth.OAuth;
import cloud.ffeng.uc.infra.oauth.OAuthClient;
import cloud.ffeng.uc.types.enums.PlatformEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
@AllArgsConstructor
public class PlatformOAuthRepositoryImpl implements PlatformOAuthRepository, ApplicationContextAware {

    private static final Map<PlatformEnum, OAuthClient> OAUTH_CLIENT_MAP = new HashMap<>();

    @Override
    public String getAuthUrl(PlatformEnum platform, String state) {
        OAuthClient client = OAUTH_CLIENT_MAP.get(platform);
        return client.authUrl(state);
    }

    @Override
    public PlatformAuthUser getAuthUser(PlatformEnum platform, String code, String state) {
        OAuthClient client = OAUTH_CLIENT_MAP.get(platform);
        try {
            return client.getOAuthUser(code, state);
        } catch (Exception e) {
            throw BizException.bizError(String.format("获取【%s】授权信息失败。", platform.getDesc()), e);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        Map<String, OAuthClient> beansOfType = context.getBeansOfType(OAuthClient.class);
        for (OAuthClient client : beansOfType.values()) {
            if (client.getClass().isAnnotationPresent(OAuth.class)) {
                OAuth declaredAnnotation = client.getClass().getDeclaredAnnotation(OAuth.class);
                OAUTH_CLIENT_MAP.put(declaredAnnotation.platform(), client);
            }
        }
    }
}
