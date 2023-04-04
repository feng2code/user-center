package cloud.ffeng.uc.domain.entity;

import cloud.ffeng.common.util.AssertUtil;
import cloud.ffeng.common.util.UuidUtil;
import cloud.ffeng.uc.domain.common.config.ValidationConfig;
import cloud.ffeng.uc.domain.common.util.KaptchaUtil;
import cloud.ffeng.uc.domain.valobj.ValidationExt;
import cloud.ffeng.uc.types.enums.VerificationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

import static cloud.ffeng.common.result.ResultStatus.BIZ_ERROR;

@Getter
@ToString
public class Verification implements Serializable {

    private String validationId;

    private String validationCode;

    private VerificationTypeEnum validationType;

    private LocalDateTime expireTime;

    private ValidationExt ext;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public void checkCodeIsValid(String validationCode) {
        AssertUtil.isTrue(this.getExpireTime().isAfter(LocalDateTime.now()), BIZ_ERROR, "验证码已过期，请刷新后重试！");
        AssertUtil.isTrue(StringUtils.equals(validationCode, this.getValidationCode()), BIZ_ERROR, "验证码错误，请刷新后重试！");
    }

    public static Verification newImgCode() {
        Verification verification = new Verification();
        verification.validationId = UuidUtil.generateUuid();
        verification.validationType = VerificationTypeEnum.IMG_CODE;

        // 图片验证码信息
        KaptchaUtil.PicInfo picInfo = KaptchaUtil.newValidationPic();
        verification.validationCode = picInfo.getCode();
        ValidationExt validationExt = new ValidationExt();
        validationExt.setImgBase64(picInfo.getImgBase64());
        verification.ext = validationExt;

        // 设置统一的过期时间
        verification.expireTime = LocalDateTime.now().plusMinutes(ValidationConfig.getDefaultExpireMinutes());
        return verification;
    }

}
