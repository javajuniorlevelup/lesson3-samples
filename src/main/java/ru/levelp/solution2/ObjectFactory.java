package ru.levelp.solution2;

import lombok.SneakyThrows;
import ru.levelp.config.Config;
import ru.levelp.config.JavaConfig;

public class ObjectFactory {

    private static final ObjectFactory instance = new ObjectFactory();
    private final Config config = new JavaConfig("ru.levelp");

    public static ObjectFactory getInstance() {
        return instance;
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if (implClass.isInterface()) {
            implClass = config.getImplClass(type);
        }
        return implClass.getDeclaredConstructor().newInstance();
    }
}




