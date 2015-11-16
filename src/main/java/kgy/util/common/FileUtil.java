package kgy.util.common;

import java.io.File;

/**
 * 文件工具类
 *
 * @author Kistory管音鹏
 * @version 1.1
 * @build 2015-10-8 16:47:17
 */
public class FileUtil {

    /**
     * 获取完整扩展名
     *
     * @param file
     * @return
     */
    public static String getExtensions(File file) {
        String extension = file.getName();
        if (extension.contains(".")) {
            return extension.substring(extension.indexOf(".") + 1);
        }
        return "";
    }

    /**
     * 获取顶级扩展名
     *
     * @param file
     * @return
     */
    public static String getExtension(File file) {
        String extension = file.getName();
        if (extension.contains(".")) {
            return extension.substring(extension.lastIndexOf(".") + 1);
        }
        return "";
    }

    private FileUtil() {
    }
}
