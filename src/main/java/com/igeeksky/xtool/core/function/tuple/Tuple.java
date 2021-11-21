package com.igeeksky.xtool.core.function.tuple;

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

    /**
     * 获取迭代器
     *
     * @return 迭代器
     */
    @Override
    default Iterator<Object> iterator() {
        return new Tuples.TupleIterator(toArray());
    }
}
