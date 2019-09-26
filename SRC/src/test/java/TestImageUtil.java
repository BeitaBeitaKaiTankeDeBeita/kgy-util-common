
import java.io.IOException;
import kgy.util.common.ImageUtil;

public class TestImageUtil {

  public static void main(String[] args) throws IOException {
//    scale("D:\\SE\\Projects\\KGY\\0.4.1 KGY Template Spring Boot\\SOURCE\\target\\classes\\public\\uploaded\\6C1A86BEBD5680679C68F48C63811FF5.jpg",
//        100);

    ImageUtil.scale(
        "D:\\SE\\Projects\\KGY\\0.4.1 KGY Template Spring Boot\\SOURCE\\target\\classes\\public\\uploaded\\1.jpg",
        50, 100,
        ImageUtil.SCALE_POSITION_CENTER, ImageUtil.SCALE_POSITION_CENTER, ImageUtil.SCALE_SIZE_COVER);
  }
}
