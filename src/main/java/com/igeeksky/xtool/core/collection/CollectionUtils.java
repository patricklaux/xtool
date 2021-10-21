package com.igeeksky.xtool.core.collection;

import java.util.Collection;
import java.util.Map;


/**
 * 集合容器工具类
 *
 * @author Patrick.Lau
 * @since 0.0.1
 */
public abstract class CollectionUtils {

    private CollectionUtils() {
    }

    /**
     * Return {@code true} if the supplied Collection is {@code null} or empty.
     * Otherwise, return {@code false}.
     *
     * @param collection the Collection to check
     * @return whether the given Collection is empty
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return (collection != null && !collection.isEmpty());
    }
}
