package ru.levelp.solution3;

import org.reflections.Reflections;
import ru.levelp.annotations.Autowired;
import ru.levelp.annotations.Service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Set;

public class AppLoader {

    private final Reflections scanner;
    private final HashMap<Class<?>, Object> context = new HashMap<>();

    private AppLoader(Package aPackage) {
        scanner = new Reflections(aPackage.getName());
    }

    public static AppLoader run(Class<?> appClass) {
        AppLoader appLoader = new AppLoader(appClass.getPackage());
        appLoader.init();
        return appLoader;
    }

    public Object getInstance(Class<?> cls) {
        return context.get(cls);
    }

    private void init() {
        // Step 1. Initialize context
        Set<Class<?>> serviceClasses = scanner.getTypesAnnotatedWith(Service.class);
        for(Class<?> serviceCls : serviceClasses) {
            try {
                Object obj = serviceCls.getDeclaredConstructor().newInstance();

                context.put(serviceCls, obj);
                for (Class<?> in : serviceCls.getInterfaces()) {
                    if (context.containsKey(in)) {
                        throw new RuntimeException(serviceCls + " has more than one impl");
                    }
                    context.put(in, obj);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // Step 2. Inject dependencies
        for(Class<?> serviceCls : serviceClasses) {
            Field[] allFields = serviceCls.getDeclaredFields();
            for(Field field : allFields) {
                if (!field.isAnnotationPresent(Autowired.class)) {
                    continue;
                }

                Class<?> type = field.getType();
                Object valueInstance = context.get(type);
                if (valueInstance == null) {
                    throw new RuntimeException("There is no impl of " + type);
                }
                Object serviceInstance = context.get(serviceCls);
                field.setAccessible(true);
                try {
                    field.set(serviceInstance, valueInstance);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
