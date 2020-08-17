package com.tiny.lang.java.classloader;

import java.util.function.Function;

/**
 * @author tiny.wang
 */
public class ClassLoaderUtil {
    public static <R> R executeFuncOnSpecifiedClassLoader(ClassLoader classLoader, Function<ClassLoader, R> function) {
        final Thread currentThread = Thread.currentThread();
        final ClassLoader originalClassLoader = currentThread.getContextClassLoader();
        try {
            currentThread.setContextClassLoader(classLoader);
            return function.apply(classLoader);
        } finally {
            currentThread.setContextClassLoader(originalClassLoader);
        }
    }
}
