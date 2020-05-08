package kgy.util.common;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

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
    return getAllFieldsByModifiers(type,
        null,
        new HashSet<Integer>() {{
          add(Modifier.PROTECTED);
          add(Modifier.PRIVATE);
        }},
        null,
        new HashSet<Integer>() {{
          add(Modifier.STATIC);
        }});
  }

  public static List<Field> getAllPublicStaticFinalFields(Class<?> type) {
    return getAllFieldsByModifiers(type,
        new HashSet<Integer>() {{
          add(Modifier.PUBLIC);
          add(Modifier.STATIC);
          add(Modifier.FINAL);
        }},
        null, null, null);
  }

  private static List<Class<?>> recursionAllClassByPackageAndAnnotation(
      String fileSeparator, List<Class<?>> classes, String pathname, File file, String[] packages, String[] notPackages,
      Class<? extends Annotation> annotation) {
    try {
      if (!pathname.endsWith(fileSeparator)) {
        pathname += fileSeparator;
      }
      String filePath = file.getPath();

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

  private static boolean elementOf(int modifier, int modifiers) {
    return (modifiers & modifier) != 0;
  }

  private static List<Field> getAllFieldsByModifiers(Class<?> type, Collection<Integer> andIncludeModifiers, Collection<Integer> orIncludeModifiers, Collection<Integer> andExcludeModifiers, Collection<Integer> orExcludeModifiers) {
    List<Field> fields = new ArrayList<>();
    while (null != type) {
      List<Field> typeFields = new ArrayList<>();
      for (Field field : type.getDeclaredFields()) {
        int fieldModifiers = field.getModifiers();

        Boolean added = null;
        if (null != andIncludeModifiers) {
          if (fieldModifiers == 0) {
            added = false;
          } else {
            added = true;
            for (Integer andIncludeModifier : andIncludeModifiers) {
              if (!elementOf(andIncludeModifier, fieldModifiers)) {
                added = false;
                break;
              }
            }
          }
        }
        if (null != orIncludeModifiers) {
          if (fieldModifiers == 0) {
            added = false;
          } else {
            for (Integer orIncludeModifier : orIncludeModifiers) {
              if (elementOf(orIncludeModifier, fieldModifiers)) {
                added = true;
                break;
              } else {
                added = false;
              }
            }
          }
        }
        if (null != andExcludeModifiers) {
          for (Integer andExcludeModifier : andExcludeModifiers) {
            if (!elementOf(andExcludeModifier, fieldModifiers)) {
              if (null == added) {
                added = true;
              }
              break;
            }
          }
        }
        if (null != orExcludeModifiers) {
          if (null == added) {
            added = true;
          }
          for (Integer orExcludeModifier : orExcludeModifiers) {
            if (elementOf(orExcludeModifier, fieldModifiers)) {
              added = false;
              break;
            }
          }
        }
        if (added) {
          fields.add(field);
        }
      }

      Collections.reverse(typeFields);
      fields.addAll(typeFields);
      type = type.getSuperclass();
    }

    Collections.reverse(fields);
    return fields;
  }
}
