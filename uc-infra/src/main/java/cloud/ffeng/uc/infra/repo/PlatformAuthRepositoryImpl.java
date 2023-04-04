package cloud.ffeng.uc.infra.repo;

import cloud.ffeng.uc.domain.entity.PlatformAuth;
import cloud.ffeng.uc.domain.repo.PlatformAuthRepository;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Slf4j
@Repository
@AllArgsConstructor
public class PlatformAuthRepositoryImpl implements PlatformAuthRepository {
    Map<String, PlatformAuth> idAuthMap = Maps.newHashMap();

    @Override
    public PlatformAuth save(PlatformAuth platformAuth) {
        idAuthMap.put(platformAuth.getAuthId(), platformAuth);
        // mysql redis mongodb es
        log.info("{}", idAuthMap);
        return platformAuth;
    }

    @Override
    public PlatformAuth get(String authId) {
        log.info("{}", idAuthMap);
        return idAuthMap.get(authId);
    }
}
