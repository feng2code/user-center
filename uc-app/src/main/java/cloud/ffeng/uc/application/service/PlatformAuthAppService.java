package cloud.ffeng.uc.application.service;

import cloud.ffeng.uc.facade.dto.PlatformStartAuthDTO;
import cloud.ffeng.uc.facade.request.PlatformStartAuthRequest;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface PlatformAuthAppService {


    /**
     * 开始授权登录：Oauth2授权开始
     *
     * @param request 开始授权请求
     * @return 开始授权信息
     */
    PlatformStartAuthDTO startAuth(@Valid PlatformStartAuthRequest request);

}
