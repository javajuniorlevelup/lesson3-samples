package ru.levelp.config;

public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> type);
}
