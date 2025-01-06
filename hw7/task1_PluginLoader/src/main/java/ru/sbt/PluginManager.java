package ru.sbt;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginManager {
    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName) {
        try {
            String pluginDirPath = pluginRootDirectory + File.separator + pluginName;
            File pluginDir = new File(pluginDirPath);

            if (!pluginDir.exists() || !pluginDir.isDirectory()) {
                throw new IllegalArgumentException("Директория плагина не найдена: " + pluginDirPath);
            }

            File pluginFile = new File(pluginDirPath + File.separator + pluginName + ".jar");
            if (!pluginFile.exists()) {
                throw new IllegalArgumentException("Плагин " + pluginName + " в директории " +
                        pluginDir + " не найден");
            }

            URL pluginUrl = pluginFile.toURI().toURL();
            // Создаем новый класслоадер для данного плагина
            URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{pluginUrl});

            // Загружаем класс плагина
            Class<?> pluginClass = urlClassLoader.loadClass(pluginClassName);

            // Ищем конструктор
            Constructor<?> constructor = pluginClass.getConstructor();

            // Создаем экземпляр плагина
            Object pluginInstance = constructor.newInstance();

            return (Plugin) pluginInstance;
        } catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException |
                 InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Ошибка при загрузке плагина", e);
        }
    }
}
