package cloud.ffeng.uc.domain.repo;

import cloud.ffeng.uc.domain.entity.Verification;

public interface ValidationRepository {


    Verification save(Verification verification);

    Verification get(String validationId);
}
