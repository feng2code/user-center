package cloud.ffeng.uc.infra.web;

import cloud.ffeng.common.result.Result;
import cloud.ffeng.uc.application.service.PlatformAuthAppService;
import cloud.ffeng.uc.facade.dto.PlatformStartAuthDTO;
import cloud.ffeng.uc.facade.request.PlatformStartAuthRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/platform")
public class PlatformController {

    private final PlatformAuthAppService platformAuthAppService;

    @RequestMapping("/startAuth")
    public Result<PlatformStartAuthDTO> startAuth(@RequestParam Integer platform, @RequestParam String redirectUrl) {
        PlatformStartAuthRequest request = new PlatformStartAuthRequest();
        request.setPlatform(platform);
        request.setRedirectUrl(redirectUrl);
        return Result.success(platformAuthAppService.startAuth(request));
    }
}
