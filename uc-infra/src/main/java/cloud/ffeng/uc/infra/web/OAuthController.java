package cloud.ffeng.uc.infra.web;

import cloud.ffeng.common.result.Result;
import cloud.ffeng.uc.application.service.UserLoginAppService;
import cloud.ffeng.uc.facade.dto.UserLoginByAuthDTO;
import cloud.ffeng.uc.facade.request.UserLoginByAuthRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {

    private final UserLoginAppService userLoginAppService;

    @RequestMapping("callback")
    public Result<UserLoginByAuthDTO> callback(@RequestParam String code,
                                               @RequestParam String state) {
        UserLoginByAuthRequest request = new UserLoginByAuthRequest();
        request.setCode(code);
        request.setAuthId(state);
        return Result.success(userLoginAppService.loginByAuth(request));
    }

}
