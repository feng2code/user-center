package cloud.ffeng.uc.domain.service;

import cloud.ffeng.uc.domain.entity.Verification;
import cloud.ffeng.uc.types.enums.VerificationTypeEnum;

public interface VerificationDomainService {

    void validate(String verificationId, String verificationCode);

    Verification generate(VerificationTypeEnum type);
}
