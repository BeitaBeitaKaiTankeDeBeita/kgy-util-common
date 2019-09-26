package test;

import kgy.util.common.MessageDigestUtil;

public class TestMessageDigestUtil {

  public static void main(String[] args) {
    System.out.println(MessageDigestUtil.getMD5("fjkljreqwklfnkl;djifovdjarqeiowr"));
    System.out.println(MessageDigestUtil.getMD5("C4CA4238A0B923820DCC509A6F75849B"));
  }
}
