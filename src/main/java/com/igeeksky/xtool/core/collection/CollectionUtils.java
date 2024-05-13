/*
 * Copyright 2017 Patrick.lau All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.igeeksky.xtool.core.collection;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;


/**
 * 集合工具类
 *
 * @author Patrick.Lau
 * @since 0.0.1
 */
public class CollectionUtils {

    private CollectionUtils() {
    }

    /**
     * 判断集合是否为空或不含任何元素
     *
     * @param collection 集合
     * @return 如果集合为空或不含任何元素，返回 {@link Boolean#TRUE} ; 否则返回{@link Boolean#FALSE}
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * 判断集合是否不为空且至少含一个元素
     *
     * @param collection 集合
     * @return 如果集合不为空且至少含一个元素，返回 {@link Boolean#TRUE} ; 否则返回{@link Boolean#FALSE}
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 拼接多个集合
     * <p>
     * 采用第一个集合来保存其它集合的元素，
     * 因此第一个集合不能为 Collections.singletonList() || Collections.emptyList() ……等无法添加元素的集合
     *
     * @param collections 多个集合（集合可以不含元素，但不能为空对象）
     * @param <E>         值类型
     * @return 第一个集合（包含其它集合所有元素）
     */
    @SafeVarargs
    public static <E> Collection<E> concat(Collection<E>... collections) {
        Collection<E> first = collections[0];
        for (int i = 1; i < collections.length; i++) {
            first.addAll(collections[i]);
        }
        return first;
    }

    public static <E> HashSet<E> newHashSet() {
        return new HashSet<>();
    }

    public static <E> HashSet<E> newHashSet(int capacity) {
        return new HashSet<>(capacity / 3 * 4 + 1);
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet() {
        return new LinkedHashSet<>();
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet(int capacity) {
        return new LinkedHashSet<>(capacity / 3 * 4 + 1);
    }
}