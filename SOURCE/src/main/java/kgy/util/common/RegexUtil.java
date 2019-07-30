package kgy.util.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式 Util
 *
 * @author KistorG
 */
public class RegexUtil {

  public static String[] patternMatcherGroups(CharSequence input, String regex) {
    List<String> list = new ArrayList<>(0);

    Matcher matcher = Pattern.compile(regex).matcher(input);
    while (matcher.find()) {
      list.add(matcher.group());
    }

    return list.toArray(new String[list.size()]);
  }

  private RegexUtil() {
  }
}
