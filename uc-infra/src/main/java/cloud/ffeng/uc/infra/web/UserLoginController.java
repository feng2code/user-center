package cloud.ffeng.uc.infra.web;


import cloud.ffeng.common.result.Result;
import cloud.ffeng.uc.application.service.UserLoginAppService;
import cloud.ffeng.uc.facade.dto.UserLoginDTO;
import cloud.ffeng.uc.facade.request.UserLoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class UserLoginController {

    private final UserLoginAppService userLoginAppService;

    /**
     * 用户登录：账号密码 + 验证码
     *
     * @param request 登录请求
     * @return 登录结果
     */
    @PostMapping
    public Result<UserLoginDTO> login(@RequestBody UserLoginRequest request) {
        return Result.success(userLoginAppService.login(request));
    }

    /**
     * 二维码登录：结果获取
     *
     * @param codeId 二维码ID
     * @return 登录结果
     */
    @GetMapping("/code/{codeId}")
    private Result<UserLoginDTO> loginByQrCodeId(@PathVariable String codeId) {

        return Result.success(null);
    }

    /**
     * 三方授权回调登录
     *
     * @param code  授权码
     * @param state 状态值：授权流水ID
     * @return 登录结果
     */
    @GetMapping("/oauth/callback")
    private Result<UserLoginDTO> loginByOauth(@RequestParam String code, @RequestParam String state) {
        return Result.success(null);
    }

}
