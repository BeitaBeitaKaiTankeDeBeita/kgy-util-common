package kgy.util.common;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReflectionUtil {
//  public static Set<Class<?>> getTypesAnnotatedWith(String prefix, Class<? extends Annotation> annotation) {
////    return new Reflections(prefix).getTypesAnnotatedWith(annotation);
//    Set<Class<?>> classes = new HashSet<>();
//    return classes;
//  }

  public static List<Class<?>> getAllClassByPackagesAndNotPackagesAndAnnotation(
      String pathname, String[] packages, String[] notPackages, Class<? extends Annotation> annotation) {
    String fileSeparator = System.getProperty("file.separator");

    return recursionAllClassByPackageAndAnnotation(
        fileSeparator, new ArrayList<>(), pathname, new File(pathname), packages, notPackages, annotation);
  }

  public static List<Class<?>> getAllClassByNotPackagesAndAnnotation(
      String pathname, String[] notPackages, Class<? extends Annotation> annotation) {
    String fileSeparator = System.getProperty("file.separator");

    return recursionAllClassByPackageAndAnnotation(
        fileSeparator, new ArrayList<>(), pathname, new File(pathname), null, notPackages, annotation);
  }

  public static List<Field> getAllProtectedOrPrivateNotStaticFields(Class<?> type) {
    List<Field> fields = new ArrayList<>();

    while (null != type) {
      List<Field> typeFields = new ArrayList<>();
      for (Field field : type.getDeclaredFields()) {
        int fieldModifiers = field.getModifiers();
        if ((Modifier.isProtected(field.getModifiers()) || Modifier.isPrivate(field.getModifiers())) &&
            !Modifier.isStatic(fieldModifiers)) {
          typeFields.add(field);
        }
      }

      Collections.reverse(typeFields);
      fields.addAll(typeFields);
      type = type.getSuperclass();
    }

    Collections.reverse(fields);
    return fields;
  }

  private static List<Class<?>> recursionAllClassByPackageAndAnnotation(
      String fileSeparator, List<Class<?>> classes, String pathname, File file, String[] packages, String[] notPackages,
      Class<? extends Annotation> annotation) {
    try {
      if (!pathname.endsWith(fileSeparator)) {
        pathname += fileSeparator;
      }
      String filePath = file.getPath();
      System.out.println("filePath: " + filePath);

      if (file.isDirectory()) {
        if (null != packages) {
          boolean included = false;
          for (String myPackage : packages) {
            String packagePathname = pathname + myPackage.replace(".", fileSeparator) + fileSeparator;
            included = packagePathname.startsWith(filePath) || filePath.startsWith(packagePathname);
            if (included) {
              break;
            }
          }

          if (included) {
            for (File myFile : file.listFiles()) {
              recursionAllClassByPackageAndAnnotation(
                  fileSeparator, classes, pathname, myFile, packages, notPackages, annotation);
            }
          }
        } else if (null != notPackages) {
          boolean excluded = false;
          for (String myPackage : notPackages) {
            String packagePathname = pathname + myPackage.replace(".", fileSeparator) + fileSeparator;
            excluded = filePath.startsWith(packagePathname);
            if (excluded) {
              break;
            }
          }

          if (!excluded) {
            for (File myFile : file.listFiles()) {
              recursionAllClassByPackageAndAnnotation(
                  fileSeparator, classes, pathname, myFile, packages, notPackages, annotation);
            }
          }
        } else {
          for (File myFile : file.listFiles()) {
            recursionAllClassByPackageAndAnnotation(
                fileSeparator, classes, pathname, myFile, packages, notPackages, annotation);
          }
        }
      } else {
        if (filePath.endsWith(".class")) {
          Class<?> myClass = Class.forName(filePath
              .replaceAll(pathname.replace("\\", "\\\\") + "|\\.class", "")
              .replace(fileSeparator, "."));
          if (null != myClass.getAnnotation(annotation)) {
            classes.add(myClass);
          }
        }
      }

      return classes;
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
