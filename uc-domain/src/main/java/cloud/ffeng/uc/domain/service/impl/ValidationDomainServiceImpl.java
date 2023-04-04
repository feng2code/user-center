package cloud.ffeng.uc.domain.service.impl;

import cloud.ffeng.common.util.AssertUtil;
import cloud.ffeng.uc.domain.entity.Verification;
import cloud.ffeng.uc.domain.repo.ValidationRepository;
import cloud.ffeng.uc.domain.service.VerificationDomainService;
import cloud.ffeng.uc.types.enums.VerificationTypeEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static cloud.ffeng.common.result.ResultStatus.BIZ_ERROR;
import static cloud.ffeng.uc.types.enums.VerificationTypeEnum.IMG_CODE;

@Service
@AllArgsConstructor
public class ValidationDomainServiceImpl implements VerificationDomainService {


    private ValidationRepository validationRepository;

    @Override
    public void validate(String verificationId, String verificationCode) {
        // 获取验证码信息
        Verification verification = validationRepository.get(verificationId);
        AssertUtil.nonNull(verification, BIZ_ERROR, "验证码信息不存在，请刷新验证码后重试！");

        // 校验验证码有效性
        verification.checkCodeIsValid(verificationCode);
    }

    @Override
    public Verification generate(VerificationTypeEnum type) {
        if (Objects.equals(type, IMG_CODE)) {
            Verification verification = Verification.newImgCode();
            validationRepository.save(verification);
            return verification;
        }

        return null;
    }

}
