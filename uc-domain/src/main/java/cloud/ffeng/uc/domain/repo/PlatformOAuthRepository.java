package cloud.ffeng.uc.domain.repo;


import cloud.ffeng.uc.domain.valobj.PlatformAuthUser;
import cloud.ffeng.uc.types.enums.PlatformEnum;

public interface PlatformOAuthRepository {

    String getAuthUrl(PlatformEnum platform, String state);


    PlatformAuthUser getAuthUser(PlatformEnum platform, String code, String state);
}
