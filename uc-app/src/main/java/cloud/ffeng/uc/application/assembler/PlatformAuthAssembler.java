package cloud.ffeng.uc.application.assembler;

import cloud.ffeng.uc.domain.entity.PlatformAuth;
import cloud.ffeng.uc.facade.dto.PlatformStartAuthDTO;

public final class PlatformAuthAssembler {

    public static PlatformStartAuthDTO toUserStartAuthLoginDTO(PlatformAuth platformAuth) {
        PlatformStartAuthDTO platformStartAuthDTO = new PlatformStartAuthDTO();
        platformStartAuthDTO.setAuthUrl(platformAuth.getAuthUrl());
        platformStartAuthDTO.setAuthId(platformAuth.getAuthId());
        platformStartAuthDTO.setRedirectUrl(platformAuth.getRedirectUrl());
        return platformStartAuthDTO;
    }

}
