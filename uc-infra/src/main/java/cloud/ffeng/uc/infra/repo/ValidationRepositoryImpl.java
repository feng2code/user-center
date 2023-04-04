package cloud.ffeng.uc.infra.repo;

import cloud.ffeng.uc.domain.entity.Verification;
import cloud.ffeng.uc.domain.repo.ValidationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ValidationRepositoryImpl implements ValidationRepository {

    @Override

    public Verification save(Verification verification) {
        return null;
    }

    @Override
    public Verification get(String validationId) {
        return null;
    }
}
