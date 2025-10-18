package org.example.container;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class SimpleContainer {
    private final Map<Class<?>, Object> singletons = new HashMap<>();
    private final Map<Class<?>, Class<?>> implementations = new HashMap<>();

    /**
     * Registers an implementation for an interface.
     * When the container is asked for the interface, it will create the implementation instead.
     *
     * @param interfaceClass
     * @param implementationClass
     */
    public void register(Class<?> interfaceClass, Class<?> implementationClass) {
        implementations.put(interfaceClass, implementationClass);
    }

    /**
     * Gets or creates an instance of the requested class.
     * If the class has constructor parameters so they are recursively resolved.
     *
     * @param <T>
     * @param clazz
     * @return
     */
    public <T> T get(Class<T> clazz) {
        //-- Check mapped for this interface---
        Class<?> actualClass = implementations.getOrDefault(clazz, clazz);

        // --Return cached instance if it exists--
        if (singletons.containsKey(actualClass)) {
            return clazz.cast(singletons.get(actualClass));
        }

        try {
            // --Get the classes constructors--
            Constructor<?>[] constructors = actualClass.getConstructors();

            if (constructors.length == 0) {
                throw new IllegalArgumentException("Class " + actualClass.getName() + " has no public constructors");
            }

            //-- Use the first constructor--
            Constructor<?> constructor = constructors[0];

            //-- Get parameter types of the constructor--
            Class<?>[] parameterTypes = constructor.getParameterTypes();

            // --Ifconstructor has no parameters create a simple instance--
            if (parameterTypes.length == 0) {
                T instance = (T) actualClass.getConstructor().newInstance();
                singletons.put(actualClass, instance);
                return instance;
            }

            // --Recursively resolve each dependency--
            Object[] parameters = new Object[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                parameters[i] = get(parameterTypes[i]);
            }
            @SuppressWarnings("unchecked")
            T instance = (T) constructor.newInstance(parameters);
            singletons.put(actualClass, instance);

            return instance;

        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate class: " + actualClass.getName(), e);
        }
    }


    public void clear() {
        singletons.clear();
    }

    public int getCachedInstanceCount() {
        return singletons.size();
    }
}
