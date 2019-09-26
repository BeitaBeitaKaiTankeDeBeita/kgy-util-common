
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class NewMain {

  public static void main(String[] args) throws FileNotFoundException, IOException {
//    System.out.println(DatetimeUtil.ignoreCascade(new Date(), Calendar.DAY_OF_MONTH));
//
//    System.out.println(Integer.parseInt("2018010100"));
//    System.out.println(Integer.parseInt("20180101001"));

    File file = new File("D:/test1.txt");

    zipFile(file);
  }

  public static void zipFile(File fileToZip) throws IOException {
    try (FileOutputStream fos = new FileOutputStream("D:/compressed.zip"); ZipOutputStream zipOut = new ZipOutputStream(fos)) {
      FileInputStream fis;
      fis = new FileInputStream(fileToZip);
      ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
      zipOut.putNextEntry(zipEntry);
      byte[] bytes = new byte[1024];
      int length;
      while ((length = fis.read(bytes)) >= 0) {
        zipOut.write(bytes, 0, length);
      }
      fis.close();
    }
  }

  public static void ZipMultipleFiles() throws IOException {
    List<String> srcFiles = Arrays.asList("test1.txt", "test2.txt");
    try (FileOutputStream fos = new FileOutputStream("multiCompressed.zip"); ZipOutputStream zipOut = new ZipOutputStream(fos)) {
      for (String srcFile : srcFiles) {
        File fileToZip = new File(srcFile);
        try (FileInputStream fis = new FileInputStream(fileToZip)) {
          ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
          zipOut.putNextEntry(zipEntry);

          byte[] bytes = new byte[1024];
          int length;
          while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
          }
        }
      }
    }
  }
}
