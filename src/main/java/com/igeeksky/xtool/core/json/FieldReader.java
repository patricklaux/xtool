package com.igeeksky.xtool.core.json;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Patrick.Lau
 * @since 1.0.0 2024/9/15
 */
public class FieldReader {

    private final Field field;

    private final Method readMethod;

    public FieldReader(Field field) {
        this(field, null);
    }

    public FieldReader(Field field, Method readMethod) {
        this.field = field;
        this.readMethod = readMethod;
    }

    public Object read(Object obj) throws InvocationTargetException, IllegalAccessException {
        if (readMethod != null) {
            if (readMethod.canAccess(obj)) {
                return readMethod.invoke(obj);
            }
        } else {
            return field.get(obj);
        }
        return null;
    }

}
