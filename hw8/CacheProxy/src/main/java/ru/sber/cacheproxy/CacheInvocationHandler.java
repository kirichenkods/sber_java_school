package ru.sber.cacheproxy;

import ru.sber.annotations.Cache;
import ru.sber.enums.CacheTypeEnum;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CacheInvocationHandler implements InvocationHandler {

    private final Map<Object, Object> cache = new HashMap<>();
    private final Object target;

    public CacheInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Cache.class)) {
            return invoke(method, args);
        }

        Cache annotation = method.getAnnotation(Cache.class);

        String key = (String) getKey(method, args, annotation);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        Object result = method.invoke(target, args);
        cache.put(key, result);

        if (annotation.cacheType() == CacheTypeEnum.FILE && annotation.zip()) {
            saveToDiskZip(key, result);
        } else if (annotation.cacheType() == CacheTypeEnum.FILE) {
            saveToDisk(key, result);
        }

        return result;
    }

    private Object invoke(Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(target, args);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Impossible", e);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    private Object getKey(Method method, Object[] args, Cache annotation) {
        StringBuilder builder = new StringBuilder();
        if (annotation.fileNamePrefix().length() > 0) {
            builder.append(annotation.fileNamePrefix());
        } else {
            builder.append(method.getName());
        }

        return builder.toString();
    }

    private void saveToDiskZip(String key, Object result) {
        String zipFilename = key + ".zip";

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
             ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFilename))) {

            // Сериализуем объект в байтовый массив
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();

            // Добавляем байты в ZIP-архив
            ZipEntry entry = new ZipEntry(key + ".txt");
            zipOutputStream.putNextEntry(entry);
            zipOutputStream.write(byteArrayOutputStream.toByteArray());
            zipOutputStream.closeEntry();

        } catch (IOException e) {
            System.err.println("Ошибка записи объекта на диск: " + e.getMessage());
        }
    }

    private void saveToDisk(String key, Object result) {
        try (FileOutputStream fileOut = new FileOutputStream(key + ".txt");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(result);
        } catch (IOException e) {
            System.err.println("Ошибка записи объекта на диск: " + e.getMessage());
        }
    }
}
