package ru.sber;

public class Game {
    public static void main(String[] args) {
        String pluginRootDirectory = ".\\hw7\\task1_PluginLoader\\src\\plugins";
        PluginManager manager = new PluginManager(pluginRootDirectory);

        String pluginClassName = "ru.sbt.PluginImpl";

        Plugin pluginA = manager.load("pluginA", pluginClassName);
        pluginA.doUsefull();

//        Plugin pluginB = manager.load("pluginB", pluginClassName);
//        pluginB.doUsefull();
    }
}
