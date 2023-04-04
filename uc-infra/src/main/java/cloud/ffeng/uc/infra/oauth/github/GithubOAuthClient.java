package cloud.ffeng.uc.infra.oauth.github;

import cloud.ffeng.uc.domain.valobj.PlatformAuthUser;
import cloud.ffeng.uc.infra.oauth.OAuth;
import cloud.ffeng.uc.infra.oauth.OAuthClient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static cloud.ffeng.uc.infra.oauth.OAuthConstants.REDIRECT_URL;
import static cloud.ffeng.uc.types.enums.PlatformEnum.GITHUB;

@Slf4j
@OAuth(platform = GITHUB)
@AllArgsConstructor
public class GithubOAuthClient implements OAuthClient {

    public static final String clientId = "92625a482e1954f2e1a0";
    public static final String clientSecret = "3333856c53b1f7e11b1caf4c5bb761487de81698";

    public static final String OAUTH_URL = "https://github.com/login/oauth/authorize?client_id=%s&redirect_url=%s&state=%s&scope=%s";

    public static final String ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token?client_id=%s&client_secret=%s&code=%s&redirect_uri=%s&state=%s";

    public static final String GET_GITHUB_USER_INFO_URL = "https://api.github.com/user";

    public static final String scope = "user";
    public static final String APPLICATION_JSON = "application/json";
    public static final String EMPTY_STR = "";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String ACCEPT = "Accept";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String ACCESS_TOKEN = "access_token";

    private final RestTemplate restTemplate;

    @Override
    public String authUrl(String state) {
        return String.format(OAUTH_URL, clientId, REDIRECT_URL, state, scope);
    }

    @Override
    public PlatformAuthUser getOAuthUser(String code, String state) {
        // 1. 获取accessToken
        String accessUrl = String.format(ACCESS_TOKEN_URL, clientId, clientSecret, code, REDIRECT_URL, state);
        HttpHeaders accessHeaders = new HttpHeaders();
        accessHeaders.set(CONTENT_TYPE, APPLICATION_JSON);
        accessHeaders.set(ACCEPT, APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(EMPTY_STR, accessHeaders);
        ResponseEntity<String> exchange = restTemplate.exchange(accessUrl, HttpMethod.POST, requestEntity, String.class);

        log.debug(JSON.toJSONString(JSONObject.parseObject(exchange.getBody()), SerializerFeature.PrettyFormat));

        // 2. 获取Github用户信息
        JSONObject jsonObject = JSONObject.parseObject(exchange.getBody());
        String accessToken = jsonObject.getString(ACCESS_TOKEN);
        HttpHeaders headers = new HttpHeaders();
        accessHeaders.set(ACCEPT, APPLICATION_JSON);
        headers.set(AUTHORIZATION, BEARER + accessToken);
        headers.set(CONTENT_TYPE, APPLICATION_JSON);
        requestEntity = new HttpEntity<>(EMPTY_STR, headers);
        ResponseEntity<String> entity = restTemplate.exchange(GET_GITHUB_USER_INFO_URL, HttpMethod.GET, requestEntity, String.class);

        log.debug(JSON.toJSONString(JSONObject.parseObject(entity.getBody()), SerializerFeature.PrettyFormat));
        GithubOAuthUser githubOAuthUser = JSONObject.parseObject(entity.getBody(), GithubOAuthUser.class);
        if (Objects.isNull(githubOAuthUser)) {
            return null;
        }

        return githubOAuthUser.toPlatformAuthUser();
    }

}
