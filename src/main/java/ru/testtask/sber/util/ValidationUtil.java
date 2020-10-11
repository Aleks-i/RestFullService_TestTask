package ru.testtask.sber.util;

import ru.testtask.sber.model.AbstractBaseEntity;
import ru.testtask.sber.util.exception.NotFoundException;

public class ValidationUtil {

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static void checkNotFound(boolean found, String arg) {
        if (!found) {
            throw new NotFoundException(arg);
        }
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(AbstractBaseEntity baseEntity, int id) {
        if (baseEntity.isNew()) {
            baseEntity.setId(id);
        } else if (baseEntity.getId() != id) {
            throw new IllegalArgumentException(baseEntity + " must be with id=" + id);
        }
    }
}