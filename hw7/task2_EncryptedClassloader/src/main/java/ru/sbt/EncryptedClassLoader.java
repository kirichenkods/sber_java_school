package ru.sbt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class EncryptedClassLoader extends ClassLoader {
    private final String key;
    private final File dir;

    public EncryptedClassLoader(String key, File dir, ClassLoader parent) {
        super(parent);
        this.key = key;
        this.dir = dir;
    }

    public Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = Files.readAllBytes(dir.toPath());
            decode(bytes);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("class not found: " + name, e);
        }
    }

    private void decode(byte[] bytes) {
        byte encryptingKey = (byte) key.length();

        for (int i = 0; i < bytes.length; ++i) {
            bytes[i] += encryptingKey;
        }
    }
}