package com.tiny.lang.java.classloader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author tiny
 */
public class UserJarClassLoader extends URLClassLoader {

    public UserJarClassLoader() {
        this(new URL[]{});
    }

    public UserJarClassLoader(URL[] urls) {
        super(urls, null);
    }

    public void addResource(String jarPath) {
        try {
            super.addURL(new File(jarPath).toURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
