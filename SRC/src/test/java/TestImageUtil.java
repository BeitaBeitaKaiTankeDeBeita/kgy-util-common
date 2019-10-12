
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TestImageUtil {

  public static void main(String[] args) throws IOException {
//    scale("D:\\SE\\Projects\\KGY\\0.4.1 KGY Template Spring Boot\\SOURCE\\target\\classes\\public\\uploaded\\6C1A86BEBD5680679C68F48C63811FF5.jpg",
//        100);
//
//    ImageUtil.scale(
//        "D:\\SE\\Projects\\KGY\\0.4.1 KGY Template Spring Boot\\SOURCE\\target\\classes\\public\\uploaded\\1.jpg",
//        50, 100,
//        ImageUtil.SCALE_POSITION_CENTER, ImageUtil.SCALE_POSITION_CENTER, ImageUtil.SCALE_SIZE_COVER);
//
//
//
//    byte type = 2;
//    byte theme = 3;
//
//    String fengmian = ""; // C:/Users/kisto/OneDrive/Pictures/146133831424980.jpg
//    String zhuti = "某某某社区\n开展“四个一”系列主题活动——向建党98周年献礼";
//    Integer canyuRenshu = 28;
//    String huiyiDidian = "某某某社区会议室";
//    String huiyiShijian = "2019年7月4日 10:00-12:00";
//    String huodongJieshaoHuiyiYicheng = "重温入党誓词\n集中缴纳党费\n接受党性教育\n开展一次集中下访活动\n开展一次党员志愿活动";
//    String[] jingcaiShunjians = {
//      "http://dev.sdnicsoft.com/green.png",
//      "http://dev.sdnicsoft.com/red.png"
//    };
//    String[] huiyiShipins = {
//      "http://dev.sdnicsoft.com/4.3.png",
//      "http://dev.sdnicsoft.com/4.3.png"
//    };
//    String huiyiZongjie = "在“七一”来临之际，全镇各党支部纷纷重温入党誓词。在机关党支部主题党日活动现场，由四名青年党员托举党旗入场，机关党支部书记石文韬带领全体党员面向党旗宣誓，大家紧握右拳、庄严肃立，面向党旗立下铿锵誓言。";
//
//    BufferedImage target;
//
//    /**
//     * 第一部分
//     */
//    BufferedImage target1 = ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/1.1.jpg"));
//
//    // 活动/会议 封面
//    if (null == fengmian || fengmian.isEmpty()) {
//      fengmian = "D:/新建文件夹/theme/" + type + "/" + theme + "/1.2.png";
//    }
//    target1 = ImageUtil.drawImage(target1,
//        ImageUtil.scale(ImageIO.read(new File(fengmian)), 690, 388, ImageUtil.SCALE_POSITION_CENTER, ImageUtil.SCALE_POSITION_CENTER, ImageUtil.SCALE_SIZE_COVER), 15 * 2, 118, new HashMap<String, Object>() {
//      {
//        put("border-radius", 8 * 2 * 2);
//      }
//    });
//    switch (type) {
//      case 2:
//        switch (theme) {
//          case 3:
//            target1 = ImageUtil.drawImage(target1,
//                ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/1.3.png")), 15 * 2, 118, new HashMap<String, Object>() {
//              {
//                put("border-radius", 8 * 2 * 2);
//              }
//            });
//            break;
//          default:
//        }
//        break;
//      default:
//    }
//
//    // 活动/会议 主题
//    if (null != zhuti && !zhuti.isEmpty()) {
//      switch (type) {
//        case 1:
//          switch (theme) {
//            case 3:
//              target1 = ImageUtil.drawString(target1,
//                  zhuti, 33 * 2, 118 + 31 * 2, new HashMap<String, Object>() {
//                {
//                  put("font", new Font("等线", Font.BOLD, 18 * 2));
//                  put("paint", Color.getHSBColor(354, 1, 0.78F));
//
//                  put("container-width", 214 * 2);
//                }
//              });
//              break;
//            default:
//          }
//          break;
//        case 2:
//          switch (theme) {
//            case 3:
//              target1 = ImageUtil.drawString(target1,
//                  zhuti, 81 * 2, 118 + 31 * 2, new HashMap<String, Object>() {
//                {
//                  put("font", new Font("等线", Font.BOLD, 18 * 2));
//                  put("paint", Color.getHSBColor(0, 0, 1));
//
//                  put("container-width", 214 * 2);
//                  put("container-height", 74.08 * 2);
//                  put("justify-content", "center");
//                  put("align-items", "center");
//                  put("line-height", 24 * 2);
//                }
//              });
//              break;
//            default:
//          }
//          break;
//        default:
//      }
//    }
//
//    // 活动/会议 参与人数
//    if (null != canyuRenshu) {
//      switch (type) {
//        case 1:
//          switch (theme) {
//            case 3:
//              target1 = ImageUtil.drawString(target1, "参与人数：" + canyuRenshu + "人", 33 * 2, 118 + 89 * 2, new HashMap<String, Object>() {
//                {
//                  put("font", new Font("等线", Font.PLAIN, 14 * 2));
//                  put("paint", Color.getHSBColor(354, 1, 0.78F));
//                }
//              });
//              break;
//            default:
//          }
//          break;
//        case 2:
//          switch (theme) {
//            case 3:
//              target1 = ImageUtil.drawString(target1, "参与人数：" + canyuRenshu + "人", 81 * 2, 118 + 116 * 2, new HashMap<String, Object>() {
//                {
//                  put("font", new Font("等线", Font.PLAIN, 14 * 2));
//                  put("paint", Color.getHSBColor(0, 0, 1));
//
//                  put("container-width", 214 * 2);
//                  put("justify-content", "center");
//                  put("line-height", 19 * 2);
//                }
//              });
//              break;
//            default:
//          }
//          break;
//        default:
//      }
//    }
//
//    // 活动/会议 地点
//    if (null != huiyiDidian && !huiyiDidian.isEmpty()) {
//      switch (type) {
//        case 1:
//          switch (theme) {
//            case 3:
//              target1 = ImageUtil.drawString(target1, huiyiDidian, 33 * 2, 118 + 137 * 2, new HashMap<String, Object>() {
//                {
//                  put("font", new Font("等线", Font.PLAIN, 12 * 2));
//                  put("paint", Color.getHSBColor(354, 1, 0.78F));
//                }
//              });
//              break;
//            default:
//          }
//          break;
//        case 2:
//          switch (theme) {
//            case 3:
//              target1 = ImageUtil.drawString(target1, huiyiDidian, 81 * 2, 118 + 135 * 2, new HashMap<String, Object>() {
//                {
//                  put("font", new Font("等线", Font.PLAIN, 14 * 2));
//                  put("paint", Color.getHSBColor(0, 0, 1));
//
//                  put("container-width", 214 * 2);
//                  put("justify-content", "center");
//                  put("line-height", 19 * 2);
//                }
//              });
//              break;
//            default:
//          }
//          break;
//        default:
//      }
//    }
//
//    // 活动/会议 时间
//    if (null != huiyiShijian && !huiyiShijian.isEmpty()) {
//      switch (type) {
//        case 1:
//          switch (theme) {
//            case 3:
//              target1 = ImageUtil.drawString(target1, huiyiShijian, 33 * 2, 118 + (int) (109.4 * 2), new HashMap<String, Object>() {
//                {
//                  put("font", new Font("等线", Font.PLAIN, 14 * 2));
//                  put("paint", Color.getHSBColor(354, 1, 0.78F));
//                }
//              });
//              break;
//            default:
//          }
//          break;
//        case 2:
//          switch (theme) {
//            case 3:
//              target1 = ImageUtil.drawString(target1, huiyiShijian, 81 * 2, 118 + (int) (155.4 * 2), new HashMap<String, Object>() {
//                {
//                  put("font", new Font("等线", Font.PLAIN, 14 * 2));
//                  put("paint", Color.getHSBColor(0, 0, 1));
//
//                  put("container-width", 214 * 2);
//                  put("justify-content", "center");
//                  put("line-height", 19 * 2);
//                }
//              });
//              break;
//            default:
//          }
//          break;
//        default:
//      }
//    }
//
//    /**
//     * 第二部分 活动介绍/会议议程
//     */
//    BufferedImage target2 = null;
//
//    if (null != huodongJieshaoHuiyiYicheng && !huodongJieshaoHuiyiYicheng.isEmpty()) {
//      BufferedImage[] target2Parts;
//      switch (type) {
//        case 1:
//          switch (theme) {
//            case 3:
//              target2Parts = new BufferedImage[3];
//              target2Parts[0] = ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/2.1.jpg"));
//              target2Parts[1] = ImageUtil.drawString(ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/2.2.jpg")),
//                  huiyiZongjie, 30 * 2, (int) (1.5 * 2), new HashMap<String, Object>() {
//                {
//                  put("font", new Font("等线", Font.PLAIN, 16 * 2));
//                  put("paint", Color.getHSBColor(0, 0, 0.11F));
//
//                  put("line-height", 26 * 2);
//                  put("container-width", (375 - 15 * 4) * 2);
//                  put("grow", ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/2.3.jpg")));
//                }
//              });
//              break;
//            default:
//              throw new UnsupportedOperationException(type + "," + theme);
//          }
//          break;
//        case 2:
//          switch (theme) {
//            case 3:
//              String[] huiyiYichengParts = huodongJieshaoHuiyiYicheng.split("\n");
//              target2Parts = new BufferedImage[huiyiYichengParts.length + 2];
//              target2Parts[0] = ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/2.1.jpg"));
//              for (int i = 0; i < huiyiYichengParts.length; i++) {
//                // 会议议题序号
//                target2Parts[i + 1] = ImageUtil.drawString(ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/2.2.jpg")),
//                    String.valueOf(i + 1), 36 * 2, (int) (2.5 * 2), new HashMap<String, Object>() {
//                  {
//                    put("font", new Font("等线", Font.BOLD, 14 * 2));
//                    put("paint", Color.WHITE);
//
//                    put("container-width", 8 * 2);
//                    put("justify-content", "center");
//                  }
//                });
//
//                // 会议议题
//                target2Parts[i + 1] = ImageUtil.drawString(target2Parts[i + 1],
//                    huiyiYichengParts[i], 58 * 2, (int) (1.5 * 2), new HashMap<String, Object>() {
//                  {
//                    put("font", new Font("等线", Font.PLAIN, 16 * 2));
//                    put("paint", Color.getHSBColor(0, 0, 0.11F));
//
//                    put("container-width", (375 - 58 - 30) * 2);
//                    put("grow", ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/2.3.jpg")));
//                  }
//                });
//              }
//              break;
//            default:
//              throw new UnsupportedOperationException(type + "," + theme);
//          }
//          break;
//        default:
//          throw new UnsupportedOperationException(type + "," + theme);
//      }
//      target2Parts[target2Parts.length - 1] = ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/2.4.jpg"));
//
//      for (BufferedImage target2Part : target2Parts) {
//        if (null == target2) {
//          target2 = target2Part;
//        } else {
//          target2 = ImageUtil.drawImage(target2, target2Part, 0, target2.getHeight());
//        }
//      }
//    }
//
//    /**
//     * 第三部分 精彩瞬间
//     */
//    BufferedImage target3 = null;
//
//    if (null != jingcaiShunjians) {
//      BufferedImage[] target3Parts = new BufferedImage[jingcaiShunjians.length + 2];
//      target3Parts[0] = ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/3.1.jpg"));
//      for (int i = 0; i < jingcaiShunjians.length; i++) {
//        // 党建 会议 精彩瞬间
//        target3Parts[i + 1] = ImageUtil.drawImage(ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/3.2.jpg")),
//            ImageUtil.scale(ImageIO.read((File) HttpUtil.get(jingcaiShunjians[i]).getContent()), (375 - 15 * 4) * 2, (int) ((375d - 15 * 4) / 16 * 9 * 2), ImageUtil.SCALE_POSITION_CENTER, ImageUtil.SCALE_POSITION_CENTER, ImageUtil.SCALE_SIZE_COVER), 15 * 2 * 2, (int) 7.5 * 2, new HashMap<String, Object>() {
//          {
//            put("border-radius", 6 * 2 * 2);
//            put("grow", ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/3.3.jpg")));
//          }
//        });
//      }
//      target3Parts[target3Parts.length - 1] = ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/3.4.jpg"));
//
//      for (BufferedImage target3Part : target3Parts) {
//        if (null == target3) {
//          target3 = target3Part;
//        } else {
//          target3 = ImageUtil.drawImage(target3, target3Part, 0, target3.getHeight());
//        }
//      }
//    }
//
//    /**
//     * 第四部分 会议视频
//     */
//    BufferedImage target4 = null;
//
//    if (null != huiyiShipins) {
//      BufferedImage[] target4Parts = new BufferedImage[huiyiShipins.length / 2 + 2];
//      target4Parts[0] = ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/4.1.jpg"));
//      for (int i = 0; i < huiyiShipins.length; i++) {
//        // 党建 会议 会议视频
//        if (i % 2 == 0) {
//          target4Parts[i + 1] = ImageUtil.drawImage(ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/4.2.jpg")),
//              ImageUtil.scale(ImageIO.read((File) HttpUtil.get(huiyiShipins[i]).getContent()), (375 - 15 * 4 - 15) / 2 * 2, (int) ((375d - 15 * 4 - 15) / 2 * 16 / 9 * 2), ImageUtil.SCALE_POSITION_CENTER, ImageUtil.SCALE_POSITION_CENTER, ImageUtil.SCALE_SIZE_COVER), 15 * 2 * 2, (int) 7.5 * 2, new HashMap<String, Object>() {
//            {
//              put("border-radius", 6 * 2 * 2);
//              put("grow", ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/4.4.jpg")));
//            }
//          });
//        } else {
//          target4Parts[i + 1] = ImageUtil.drawImage(target4Parts[i],
//              ImageUtil.scale(ImageIO.read((File) HttpUtil.get(huiyiShipins[i]).getContent()), (375 - 15 * 4 - 15) / 2 * 2, (int) ((375d - 15 * 4 - 15) / 2 * 16 / 9 * 2), ImageUtil.SCALE_POSITION_CENTER, ImageUtil.SCALE_POSITION_CENTER, ImageUtil.SCALE_SIZE_COVER), ((375 - 15 * 4 - 15) / 2 + 15 * 3) * 2, (int) 7.5 * 2, new HashMap<String, Object>() {
//            {
//              put("border-radius", 6 * 2 * 2);
//              put("grow", ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/4.4.jpg")));
//            }
//          });
//        }
//      }
//      target4Parts[target4Parts.length - 1] = ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/4.5.jpg"));
//
//      for (BufferedImage target4Part : target4Parts) {
//        if (null == target4) {
//          target4 = target4Part;
//        } else {
//          target4 = ImageUtil.drawImage(target4, target4Part, 0, target4.getHeight());
//        }
//      }
//      ImageIO.write(target4, "png", new File("D:/新建文件夹/theme/" + type + "/" + theme + "/tar.4.png"));
//    }
//
//    /**
//     * 第五部分 会议总结
//     */
//    BufferedImage target5 = null;
//
//    if (null != huiyiZongjie && !huiyiZongjie.isEmpty()) {
//      BufferedImage[] target5Parts = new BufferedImage[3];
//      target5Parts[0] = ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/5.1.jpg"));
//
//      // 党建 会议 总结
//      target5Parts[1] = ImageUtil.drawString(ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/5.2.jpg")),
//          huiyiZongjie, 30 * 2, (int) (1.5 * 2), new HashMap<String, Object>() {
//        {
//          put("font", new Font("等线", Font.PLAIN, 15 * 2));
//          put("paint", Color.getHSBColor(0, 0, 0.11F));
//
//          put("line-height", 26 * 2);
//          put("container-width", (375 - 15 * 4) * 2);
//          put("grow", ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/5.3.jpg")));
//        }
//      });
//      target5Parts[2] = ImageIO.read(new File("D:/新建文件夹/theme/" + type + "/" + theme + "/5.4.jpg"));
//
//      for (BufferedImage target5Part : target5Parts) {
//        if (null == target5) {
//          target5 = target5Part;
//        } else {
//          target5 = ImageUtil.drawImage(target5, target5Part, 0, target5.getHeight());
//        }
//      }
//    }
//
//    /**
//     *
//     */
//    target = ImageUtil.drawImage(target1, target2, 0, target1.getHeight());
//    target = ImageUtil.drawImage(target, target3, 0, target.getHeight());
//    target = ImageUtil.drawImage(target, target4, 0, target.getHeight());
//    target = ImageUtil.drawImage(target, target5, 0, target.getHeight());
//
//    ImageIO.write(target, "png", new File("D:/新建文件夹/theme/" + type + "/" + theme + "/tar.png"));
//
//
//
//    ImageIO.write(ImageUtil.drawImage(ImageIO.read(new File("D:\\新建文件夹\\theme\\2\\3\\4.2.jpg")),
//        ImageUtil.scale(
//            ImageIO.read(new File("D:\\新建文件夹\\theme\\2\\3\\4.3.png")),
//            (375 - 15 * 4 - 15) / 2 * 2, (int) ((375d - 15 * 4 - 15) / 2 * 16 / 9 * 2), ImageUtil.SCALE_POSITION_CENTER, ImageUtil.SCALE_POSITION_CENTER, ImageUtil.SCALE_SIZE_COVER),
//        15 * 2 * 2, (int) 7.5 * 2, new HashMap<String, Object>() {
//      {
//        put("border-radius", 6 * 2 * 2);
//        put("grow", ImageIO.read(new File("D:/新建文件夹/theme/2/3/4.4.jpg")));
//      }
//    }), "png", new File("D:\\新建文件夹\\theme\\2\\3\\tar.4.3.1.png"));
//
//    ImageIO.write(drawImage(ImageIO.read(new File("D:\\新建文件夹\\theme\\2\\3\\4.3.png"))), "png", new File("D:\\新建文件夹\\theme\\2\\3\\tar.4.3.2.png"));
  }

  public static BufferedImage drawImage(BufferedImage source) throws IOException {
    BufferedImage target = new BufferedImage(source.getWidth() * 2, source.getHeight(), BufferedImage.TYPE_INT_ARGB);

    Graphics2D graphics2D = target.createGraphics();

    graphics2D.drawImage(source, 0, 0, null);

    graphics2D.dispose();

    return target;
  }
}
