
import kgy.util.common.RegexUtil;

public class TestRegexUtil {

  public static void main(String[] args) {
    for (String str : RegexUtil.patternMatcherGroups("1+2-3*4/5*(${2}+${2})", "(?<=\\$\\{).*?(?=})")) {
      System.out.println(str);
    }
  }
}
