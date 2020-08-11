package com.tiny.lang.java.classloader;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * @author tiny
 */
public class MainRunner {
    public static void main(String[] args) throws Exception {
        Properties properties = System.getProperties();
        String target = properties.get("user.dir").toString() + "/target";
        UserJarClassLoader loader = new UserJarClassLoader();
        Files.find(Paths.get(target), 1,
                (path, basicFileAttributes) -> path.toString().endsWith(".jar")
        ).forEach(path -> loader.addResource(path.toFile().getPath()));
        // Thread.currentThread().setContextClassLoader(loader)
        Class<?> aClass = loader.loadClass("com.tiny.lang.java.classloader.MainRunner");
        System.out.println(aClass == MainRunner.class);
    }
}
