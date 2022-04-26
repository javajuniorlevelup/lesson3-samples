package ru.levelp.solution1;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

public class SimpleContainer {

    private static final SimpleContainer instance = new SimpleContainer();

    private final Map<Class<?>, Class<?>> registry = new HashMap<>();

    private SimpleContainer() {
    }

    public static SimpleContainer getInstance() {
        return instance;
    }

    public <T> void register(Class<T> type, Class<? extends T> implClass) {
        registry.put(type, implClass);
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<?> implClass = registry.get(type);
        if (implClass == null) {
            throw new RuntimeException(type + " hasn't implementation.");
        }

        return (T) implClass.getDeclaredConstructor().newInstance();
    }
}
