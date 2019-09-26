
import java.util.logging.Level;
import java.util.logging.Logger;
import kgy.util.common.LogUtil;

public class TestLogUtil {

  private static final Logger LOG = Logger.getLogger(TestLogUtil.class.getName());

  public static void main(String[] args) {
//
//    LogUtil.info(LOG, "A", 1);
//    LogUtil.log(LOG, Level.WARNING, "B");

    LogUtil.printStackTrace(LOG, Level.SEVERE, new UnsupportedOperationException("231231313213dsfafadsfasd"));
    LogUtil.printStackTrace(LOG, Level.WARNING, new UnsupportedOperationException("231231313213dsfafadsfasd"));
  }
}
