package cloud.ffeng.uc.infra.web;


import cloud.ffeng.uc.facade.api.UserRegisterFacade;
import cloud.ffeng.uc.facade.request.UserRegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/register")
public class RegisterController implements UserRegisterFacade {

    @Override
    @PostMapping
    public String register(@RequestBody UserRegisterRequest request) {

        return "";
    }

}
