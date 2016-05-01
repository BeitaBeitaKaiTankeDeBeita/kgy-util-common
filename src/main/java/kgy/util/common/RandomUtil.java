package kgy.util.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 随机工具类
 *
 * @author Kistory
 */
public class RandomUtil {

  /**
   * 随机整数
   *
   * @param min
   * @param max
   * @return
   */
  public static int randomInt(int min, int max) {
    return new BigDecimal(min - 1 + (max - min + 1) * Math.random()).setScale(0, RoundingMode.CEILING).intValue();
  }

  /**
   * 随机整数
   *
   * @param min
   * @param max
   * @return
   */
  public static int randomInt(double min, double max) {
    return new BigDecimal(min - 1 + (max - min + 1) * Math.random()).setScale(0, RoundingMode.CEILING).intValue();
  }

  /**
   * 有概率的随机整数
   *
   * @param min
   * @param max
   * @param probability
   * @return
   */
  public static int randomInt(int min, int max, double[] probability) {
    double r = Math.random();
    double p0, p1 = 1;
    for (int i = 0; i < max - min; i++) {
      try {
        p0 = probability[i];
        p1 -= p0;
      } catch (ArrayIndexOutOfBoundsException e) {
        p0 = p1 / (max - min - probability.length + 1);
      }
      if (r < p0) {
        return min + i;
      } else {
        r -= p0;
      }
    }
    return max;
  }

  /**
   * 随机浮点数
   *
   * @param min
   * @param max
   * @param scale
   * @return
   */
  public static float randomFloat(float min, float max, int scale) {
    double dbl = 1 / Math.pow(10, scale);
    return new BigDecimal(min - dbl + (max - min + dbl) * Math.random()).setScale(scale, RoundingMode.CEILING).floatValue();
  }

  /**
   * 随机双精度型
   *
   * @param min
   * @param max
   * @param scale
   * @return
   */
  public static double randomDouble(double min, double max, int scale) {
    double dbl = 1 / Math.pow(10, scale);
    return new BigDecimal(min - dbl + (max - min + dbl) * Math.random()).setScale(scale, RoundingMode.CEILING).doubleValue();
  }

  /**
   * 有概率的随机布尔型
   *
   * @param probability
   * @return
   */
  public static boolean randomBoolean(double probability) {
    return Math.random() >= (1 - probability);
  }

  /**
   * 随机布尔型
   *
   * @return
   */
  public static boolean randomBoolean() {
    return randomBoolean(0.5);
  }

  /**
   * 随机字符串
   *
   * @param seeds
   * @param length
   * @return
   */
  public static String randomString(String[] seeds, int length) {
    String result = "";
    for (int i = 0; i < length; i++) {
      result += seeds[randomInt(0, seeds.length - 1)];
    }
    return result;
  }

  /**
   * 随机字符串
   *
   * @param seedsStr
   * @param length
   * @return
   */
  public static String randomString(String seedsStr, int length) {
    return randomString(seedsStr.split(""), length);
  }

  /**
   * 随机字符串
   *
   * @param length
   * @return
   */
  public static String randomString(int length) {
    return randomString("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", length);
  }

  /**
   * 随机 Unicode 字符
   *
   * @param min
   * @param max
   * @param length
   * @return
   */
  public static String randomUnicode(int min, int max, int length) {
    String result = "";
    for (int i = 0; i < length; i++) {
      result += new String(Character.toChars(randomInt(min, max)));
    }
    return result;
  }

  /**
   * 随机中日韩统一表意文字
   *
   * @param length
   * @return
   */
  public static String randomCJKUnifiedIdeographs(int length) {
    String result = "";
    for (int i = 0; i < length; i++) {
      result += new String(Character.toChars(randomInt(0X4E00, 0X9FD5)));
    }
    return result;
  }

  /**
   * 随机姓
   *
   * @return
   */
  public static String randomXing() {
    return randomString(("赵,钱,孙,李,周,吴,郑,王,"
                         + "冯,陈,楮,卫,蒋,沈,韩,杨,"
                         + "朱,秦,尤,许,何,吕,施,张,"
                         + "孔,曹,严,华,金,魏,陶,姜,"
                         + "戚,谢,邹,喻,柏,水,窦,章,"
                         + "云,苏,潘,葛,奚,范,彭,郎,"
                         + "鲁,韦,昌,马,苗,凤,花,方,"
                         + "俞,任,袁,柳,酆,鲍,史,唐,"
                         + "费,廉,岑,薛,雷,贺,倪,汤,"
                         + "滕,殷,罗,毕,郝,邬,安,常,"
                         + "乐,于,时,傅,皮,卞,齐,康,"
                         + "伍,余,元,卜,顾,孟,平,黄,"
                         + "和,穆,萧,尹,姚,邵,湛,汪,"
                         + "祁,毛,禹,狄,米,贝,明,臧,"
                         + "计,伏,成,戴,谈,宋,茅,庞,"
                         + "熊,纪,舒,屈,项,祝,董,梁,"
                         + "杜,阮,蓝,闽,席,季,麻,强,"
                         + "贾,路,娄,危,江,童,颜,郭,"
                         + "梅,盛,林,刁,锺,徐,丘,骆,"
                         + "高,夏,蔡,田,樊,胡,凌,霍,"
                         + "虞,万,支,柯,昝,管,卢,莫,"
                         + "经,房,裘,缪,干,解,应,宗,"
                         + "丁,宣,贲,邓,郁,单,杭,洪,"
                         + "包,诸,左,石,崔,吉,钮,龚,"
                         + "程,嵇,邢,滑,裴,陆,荣,翁,"
                         + "荀,羊,於,惠,甄,麹,家,封,"
                         + "芮,羿,储,靳,汲,邴,糜,松,"
                         + "井,段,富,巫,乌,焦,巴,弓,"
                         + "牧,隗,山,谷,车,侯,宓,蓬,"
                         + "全,郗,班,仰,秋,仲,伊,宫,"
                         + "宁,仇,栾,暴,甘,斜,厉,戎,"
                         + "祖,武,符,刘,景,詹,束,龙,"
                         + "叶,幸,司,韶,郜,黎,蓟,薄,"
                         + "印,宿,白,怀,蒲,邰,从,鄂,"
                         + "索,咸,籍,赖,卓,蔺,屠,蒙,"
                         + "池,乔,阴,郁,胥,能,苍,双,"
                         + "闻,莘,党,翟,谭,贡,劳,逄,"
                         + "姬,申,扶,堵,冉,宰,郦,雍,"
                         + "郤,璩,桑,桂,濮,牛,寿,通,"
                         + "边,扈,燕,冀,郏,浦,尚,农,"
                         + "温,别,庄,晏,柴,瞿,阎,充,"
                         + "慕,连,茹,习,宦,艾,鱼,容,"
                         + "向,古,易,慎,戈,廖,庾,终,"
                         + "暨,居,衡,步,都,耿,满,弘,"
                         + "匡,国,文,寇,广,禄,阙,东,"
                         + "欧,殳,沃,利,蔚,越,夔,隆,"
                         + "师,巩,厍,聂,晁,勾,敖,融,"
                         + "冷,訾,辛,阚,那,简,饶,空,"
                         + "曾,毋,沙,乜,养,鞠,须,丰,"
                         + "巢,关,蒯,相,查,后,荆,红,"
                         + "游,竺,权,逑,盖,益,桓,公,"
                         + "万俟,司马,上官,欧阳,"
                         + "夏侯,诸葛,闻人,东方,"
                         + "赫连,皇甫,尉迟,公羊,"
                         + "澹台,公冶,宗政,濮阳,"
                         + "淳于,单于,太叔,申屠,"
                         + "公孙,仲孙,轩辕,令狐,"
                         + "锺离,宇文,长孙,慕容,"
                         + "鲜于,闾丘,司徒,司空,"
                         + "丌官,司寇,仉,督,子车,"
                         + "颛孙,端木,巫马,公西,"
                         + "漆雕,乐正,壤驷,公良,"
                         + "拓拔,夹谷,宰父,谷梁,"
                         + "晋,楚,阎,法,汝,鄢,涂,钦,"
                         + "段干,百里,东郭,南门,"
                         + "呼延,归,海,羊舌,微生,"
                         + "岳,帅,缑,亢,况,后,有,琴,"
                         + "梁丘,左丘,东门,西门,"
                         + "商,牟,佘,佴,伯,赏,南宫,"
                         + "墨,哈,谯,笪,年,爱,阳,佟,"
                         + "第五,言,福").split(","), 1);
  }

  /**
   * 随机名
   *
   * @return
   */
  public static String randomMing() {
    return randomCJKUnifiedIdeographs(randomInt(1, 2));
  }

  /**
   * 随机姓名
   *
   * @return
   */
  public static String randomXingming() {
    return randomXing() + randomMing();
  }

  /**
   * 随机日期
   *
   * @param minYear
   * @param maxYear
   * @return
   */
  public static Date randomData(int minYear, int maxYear) {
    int intYear = randomInt(minYear, maxYear);
    int intMonth = randomInt(1, 12);
    int intDay;
    switch (intMonth) {
      case 2:
        if ((intYear % 4 == 0 && intYear % 100 != 0) || intYear % 400 == 0) {
          intDay = randomInt(1, 29);
        } else {
          intDay = randomInt(1, 28);
        }
        break;
      case 4:
      case 6:
      case 9:
      case 11:
        intDay = randomInt(1, 30);
        break;
      default:
        intDay = randomInt(1, 31);
        break;
    }
    try {
      return new SimpleDateFormat("y-M-d").parse(intYear + "-" + intMonth + "-" + intDay);
    } catch (ParseException e) {
      throw new RuntimeException("ParseException");
    }
  }

  /**
   * 随机日期时间
   *
   * @param minYear
   * @param maxYear
   * @return
   */
  public static Date randomDatatime(int minYear, int maxYear) {
    int intYear = randomInt(minYear, maxYear);
    int intMonth = randomInt(1, 12);
    int intDay;
    int intHour = randomInt(0, 23);
    int intMinute = randomInt(0, 59);
    int intSecond = randomInt(0, 59);
    int intMillisecond = randomInt(0, 999);
    switch (intMonth) {
      case 2:
        if ((intYear % 4 == 0 && intYear % 100 != 0) || intYear % 400 == 0) {
          intDay = randomInt(1, 29);
        } else {
          intDay = randomInt(1, 28);
        }
        break;
      case 4:
      case 6:
      case 9:
      case 11:
        intDay = randomInt(1, 30);
        break;
      default:
        intDay = randomInt(1, 31);
        break;
    }
    try {
      return new SimpleDateFormat("y-M-d h:m:s.S").parse(intYear + "-" + intMonth + "-" + intDay + " " + intHour + ":" + intMinute + ":" + intSecond + "." + intMillisecond);
    } catch (ParseException e) {
      throw new RuntimeException("ParseException");
    }
  }

  private RandomUtil() {
  }
}
