
import java.util.logging.Logger;
import kgy.util.common.LogUtil;

public class TestLogUtil {

  private static final Logger LOG = Logger.getLogger(TestLogUtil.class.getName());

  public static void main(String[] args) {
    LogUtil.info(LOG, "aaa", "aaa");
  }
}
