package cloud.ffeng.uc.application.service;

import cloud.ffeng.uc.facade.dto.UserRegisterDTO;
import cloud.ffeng.uc.facade.request.UserRegisterRequest;

public interface UserRegisterAppService {

    /**
     * 用户注册
     *
     * @param request 注册请求
     * @return a
     */
    UserRegisterDTO register(UserRegisterRequest request);
}
