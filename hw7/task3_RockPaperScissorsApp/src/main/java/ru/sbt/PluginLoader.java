package ru.sbt;

import lombok.AllArgsConstructor;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PluginLoader {
    private String pathName;

    /**
     * Загружает плагины из pathName
     */
    public List<PlayableRockPaperScissorsImpl> getPlayersFromPath(String pathName) {
        File pluginDir = new File(pathName);
        if (!pluginDir.exists() || !pluginDir.isDirectory()) {
            throw new IllegalArgumentException("Директория с плагинами не найдена ");
        }
        File[] jars = pluginDir.listFiles(file -> file.isFile() && file.getName().endsWith(".jar"));
        if (jars.length == 0) {
            throw new IllegalArgumentException("В директории " + pluginDir.getPath() + " файлов jar не найдено");
        }

        Class<?>[] pluginClasses = new Class[jars.length];

        for (int i = 0; i < jars.length; i++) {
            try {
                URL jarURL = jars[i].toURI().toURL();
                URLClassLoader classLoader = new URLClassLoader(new URL[]{jarURL});
                pluginClasses[i] = classLoader.loadClass("ru.sbt.PlayableRockPaperScissorsImpl");
            } catch (MalformedURLException | ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }

        List<PlayableRockPaperScissorsImpl> players = new ArrayList<>();
        int countName = 0;
        for (Class<?> clazz : pluginClasses) {
            try {
                PlayableRockPaperScissorsImpl instance =
                        (PlayableRockPaperScissorsImpl) clazz.getDeclaredConstructor().newInstance();
                instance.setName("player" + ++countName);
                players.add(instance);
            } catch (InstantiationException | IllegalAccessException |
                     NoSuchMethodException | InvocationTargetException e) {
                System.err.println(e.getMessage());
            }
        }
        return players;
    }
}