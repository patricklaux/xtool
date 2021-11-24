/*
 * Copyright 2021 Patrick.lau All rights reserved.
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
