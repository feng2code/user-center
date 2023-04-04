package cloud.ffeng.uc.facade.api;


import cloud.ffeng.uc.facade.request.UserRegisterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/register")
public interface UserRegisterFacade {

    /**
     * 注册
     *
     * @param request 注册请求
     * @return 注册结果
     */
    @PostMapping
    String register(UserRegisterRequest request);

}
