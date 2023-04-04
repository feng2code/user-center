package cloud.ffeng.uc.domain.entity;

import cloud.ffeng.uc.types.enums.PlatformEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlatformAuth implements Serializable {

    private String authId;

    private String redirectUrl;

    private PlatformEnum platform;

    private String authUrl;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
