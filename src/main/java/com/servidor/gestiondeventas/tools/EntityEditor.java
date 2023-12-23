package com.servidor.gestiondeventas.tools;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class EntityEditor<T> {
    public T editEntity(T updatedEntity, T originalEntity) {
        try {
            Field[] fields = updatedEntity.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object updatedValue = field.get(updatedEntity);
                Object originalValue = field.get(originalEntity);

                if (updatedValue != null && !updatedValue.equals(originalValue)) {
                    field.set(originalEntity, updatedValue);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return originalEntity;
    }
}

