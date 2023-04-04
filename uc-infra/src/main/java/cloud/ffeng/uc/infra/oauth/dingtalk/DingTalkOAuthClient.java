package cloud.ffeng.uc.infra.oauth.dingtalk;


import cloud.ffeng.common.util.FuncUtil;
import cloud.ffeng.uc.domain.valobj.PlatformAuthUser;
import cloud.ffeng.uc.infra.oauth.OAuth;
import cloud.ffeng.uc.infra.oauth.OAuthClient;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiSnsGetuserinfoBycodeRequest;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.request.OapiUserGetUseridByUnionidRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiSnsGetuserinfoBycodeResponse;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserGetUseridByUnionidResponse;
import com.taobao.api.ApiException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static cloud.ffeng.uc.infra.oauth.OAuthConstants.REDIRECT_URL;
import static cloud.ffeng.uc.types.enums.PlatformEnum.DING_TALK;

@Slf4j
@OAuth(platform = DING_TALK)
@AllArgsConstructor
public class DingTalkOAuthClient implements OAuthClient {

    /**
     * <a href="https://open-dev.dingtalk.com/fe/old#/list-bench">配置地址：老版工作台</a>
     */
    public static final String loginAppId = "dingoaqm57ykv5rprjm5pe";
    public static final String loginAppSecret = "9MMoyw_HvW6kiSgXiBszYO33rgVKKBrD1ZsMnHNc0gd-Hf__g-fhw9BS4IEECWBG";

    public static final String OAUTH_URL = "https://oapi.dingtalk.com/connect/qrconnect?appid=%s&response_type=code&scope=snsapi_login&state=%s&redirect_uri=%s";

    /**
     * 内部应用配置地址 用来获取用户信息
     * <a href="https://open-dev.dingtalk.com/fe/app#/appMgr/inner/h5/2239480504/1">配置地址</a>
     */
    public static final String appId = "dingope1j6h5dcxsfvur";
    public static final String appSecret = "ysXP1asmdT2e8x8NlVNSFsrCQdyWeZdboh2-mh4SqzjmP43kZ3ptdI02KoMx_TfH";
    public static final String DING_TALK_GET_USER_INFO_BY_CODE = "https://oapi.dingtalk.com/sns/getuserinfo_bycode";
    public static final String DING_TALK_GET_TOKEN = "https://oapi.dingtalk.com/gettoken";
    public static final String GET_USER_ID_BY_UNION_ID_AND_TOKEN = "https://oapi.dingtalk.com/user/getUseridByUnionid";
    public static final String GET_USER_INFO_BY_USER_ID = "https://oapi.dingtalk.com/user/get";
    public static final String GET = "GET";

    @Override
    public String authUrl(String state) {
        try {
            return String.format(OAUTH_URL, loginAppId, state, URLEncoder.encode(REDIRECT_URL, StandardCharsets.UTF_8.toString()));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("encode error", e);
        }
    }

    @Override
    public PlatformAuthUser getOAuthUser(String code, String state) {
        DefaultDingTalkClient client = new DefaultDingTalkClient(DING_TALK_GET_USER_INFO_BY_CODE);
        OapiSnsGetuserinfoBycodeRequest req = new OapiSnsGetuserinfoBycodeRequest();
        req.setTmpAuthCode(code);
        try {
            OapiSnsGetuserinfoBycodeResponse response = client.execute(req, loginAppId, loginAppSecret);
            PlatformAuthUser authUser = toPlatformAuthUser(response);
            log.info("getOAuthUser: DING_TALK_GET_USER_INFO_BY_CODE: {}", JSONObject.toJSONString(response));

            FuncUtil.ifTrueThen(Objects.isNull(authUser), ()->{
                throw new RuntimeException("get DingTalk user info by code error. ");
            });

            client = new DefaultDingTalkClient(DING_TALK_GET_TOKEN);
            OapiGettokenRequest request = new OapiGettokenRequest();
            request.setAppkey(appId);
            request.setAppsecret(appSecret);
            request.setHttpMethod(GET);
            OapiGettokenResponse tokenResp = client.execute(request);
            log.info("getOAuthUser: DING_TALK_GET_TOKEN: {}", tokenResp.getAccessToken());
            log.debug(tokenResp.getBody());

            client = new DefaultDingTalkClient(GET_USER_ID_BY_UNION_ID_AND_TOKEN);
            OapiUserGetUseridByUnionidRequest userIdRequest = new OapiUserGetUseridByUnionidRequest();
            userIdRequest.setUnionid(authUser.getPlatformOpenId());
            userIdRequest.setHttpMethod(GET);
            OapiUserGetUseridByUnionidResponse userIdResp = client.execute(userIdRequest, tokenResp.getAccessToken());
            log.info("getOAuthUser: GET_USER_ID_BY_UNION_ID_AND_TOKEN: {}", userIdResp.getUserid());

            client = new DefaultDingTalkClient(GET_USER_INFO_BY_USER_ID);
            OapiUserGetRequest userInfoRequest = new OapiUserGetRequest();
            userInfoRequest.setUserid(userIdResp.getUserid());
            userInfoRequest.setHttpMethod(GET);
            OapiUserGetResponse userInfoResp = client.execute(userInfoRequest, tokenResp.getAccessToken());

            authUser.setPlatformUserId(userIdResp.getUserid());
            authUser.setEmail(StringUtils.isEmpty(userInfoResp.getEmail()) ? userInfoResp.getOrgEmail() : userInfoResp.getEmail());
            authUser.setPhone(userInfoResp.getMobile());
            authUser.setAvatarUrl(userInfoResp.getAvatar());
            authUser.setRealName(userInfoResp.getName());

            log.info("getOAuthUser: GET_USER_INFO_BY_USER_ID: {}", userInfoResp.getBody());
            return authUser;
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    public PlatformAuthUser toPlatformAuthUser(OapiSnsGetuserinfoBycodeResponse response) {
        if (Objects.isNull(response) || Objects.isNull(response.getUserInfo()) || Objects.isNull(response.getUserInfo().getOpenid())) {
            return null;
        }
        OapiSnsGetuserinfoBycodeResponse.UserInfo userInfo = response.getUserInfo();
        PlatformAuthUser platformAuthUser = new PlatformAuthUser();
        platformAuthUser.setPlatform(DING_TALK);
        platformAuthUser.setPlatformOpenId(userInfo.getUnionid());
        platformAuthUser.setNickname(userInfo.getNick());
        return platformAuthUser;
    }

}
