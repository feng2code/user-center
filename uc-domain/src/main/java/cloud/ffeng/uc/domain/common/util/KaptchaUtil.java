package cloud.ffeng.uc.domain.common.util;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;

/**
 * @author yiren
 */
public class KaptchaUtil {

    private static DefaultKaptcha defaultKaptcha;

    public static DefaultKaptcha newDefaultKaptcha() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.width", "150");
        properties.put("kaptcha.height", "50");
        properties.put("kaptcha.textproducer.font.color", "white");
        properties.put("kaptcha.textproducer.char.space", "5");
        properties.put("kaptcha.textproducer.char.length", Integer.valueOf(3 + new Random().nextInt() % 3).toString());
        properties.put("kaptcha.background.clear.from", "gray");
        properties.put("kaptcha.background.clear.to", "cyan");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    public static String base64Image(BufferedImage bufferedImage) {
        String base64 = null;
        try {
            //输出流
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", stream);
            base64 = "data:image/png;base64," + Base64.getEncoder().encodeToString(stream.toByteArray());
            stream.flush();
            stream.close();
            return base64;
        } catch (IOException e) {
            throw new RuntimeException("base64Image: transfer to base64 img error", e);
        }
    }

    public static PicInfo newValidationPic() {
        if (Objects.isNull(defaultKaptcha)) {
            synchronized (KaptchaUtil.class) {
                if (Objects.isNull(defaultKaptcha)) {
                    defaultKaptcha = newDefaultKaptcha();
                }
            }
        }
        String code = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(code);
        String base64Image = base64Image(image);

        PicInfo picInfo = new PicInfo();
        picInfo.setCode(code);
        picInfo.setImgBase64(base64Image);

        return picInfo;
    }


    @Data
    public static class PicInfo {
        private String code;

        private String imgBase64;
    }

}