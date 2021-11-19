package com.igeeksky.xtool.core.function;

import java.util.Iterator;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-19
 */
public interface Tuple extends Iterable<Object> {

    /**
     * 元素数量
     *
     * @return 元素数量
     */
    int size();

    /**
     * 转换为对象数组
     *
     * @return 对象数组
     */
    Object[] toArray();

    @Override
    default Iterator<Object> iterator() {
        return new Tuples.TupleIterator(toArray());
    }
}
