package com.tiny.lang.java.classloader;

import com.tiny.lang.java.common.ISpeaker;
import com.tiny.lang.java.common.Person;
import com.tiny.lang.java.common.Teacher;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ServiceLoader;

/**
 * @author tiny
 */
public class MainRunner {

    private static final String PERSON_CLASS = "com.tiny.lang.java.common.Person";

    private static final String TEACHER_CLASS = "com.tiny.lang.java.common.Teacher";

    public static void main(String[] args) throws Exception {
        Properties properties = System.getProperties();
        String target = properties.get("user.dir").toString() + "/target";
        ClassLoader mainThreadClassLoader = ISpeaker.class.getClassLoader();
        String[] allowedPackages = {ISpeaker.class.getName()};
        UserJarClassLoader loader = new UserJarClassLoader(mainThreadClassLoader, allowedPackages);
        Files.find(Paths.get(target), 1,
                (path, ignored) -> path.toString().endsWith(".jar")
        ).forEach(path -> loader.addResource(path.toString()));

        // test case 1
        // execute function on specified classloader
        List<Object> classes = new ArrayList<>();
        Class<?> personClass = ClassLoaderUtil.executeFuncOnSpecifiedClassLoader(loader, MainRunner::loadPersonClass);
        Class<?> teacherClass = ClassLoaderUtil.executeFuncOnSpecifiedClassLoader(loader, MainRunner::loadTeacherClass);
        classes.add(personClass);
        classes.add(teacherClass);
        classes.forEach(System.out::println);

        // test case 2
        Thread.sleep(1000);
        System.out.println("-----test case 2------");
        ISpeaker speaker = ClassLoaderUtil.executeFuncOnSpecifiedClassLoader(loader, MainRunner::loadISpeakerService);
        System.out.println(speaker);
    }

    private static ISpeaker loadISpeakerService(ClassLoader classLoader) {
        return ServiceLoader.load(ISpeaker.class, classLoader)
                .findFirst()
                .orElse(null);
    }

    private static Class<?> loadPersonClass(ClassLoader classLoader) {
        Class<?> aClass = null;
        try {
            aClass = classLoader.loadClass(PERSON_CLASS);
            System.out.println(aClass == Person.class);
            Object object = aClass.getDeclaredConstructor().newInstance();
            // throw ClassCastException: class loaded from different ClassLoader
            System.out.println(((Person) object).getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aClass;
    }

    private static Class<?> loadTeacherClass(ClassLoader classLoader) {
        Class<?> aClass = null;
        try {
            aClass = classLoader.loadClass(TEACHER_CLASS);
            System.out.println(aClass == Teacher.class);
            Object object = aClass.getDeclaredConstructor().newInstance();
            // throw ClassCastException: class loaded from different ClassLoader
            System.out.println(((Teacher) object).getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aClass;
    }

}
