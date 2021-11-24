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


package com.igeeksky.xtool.core.nlp;

import java.util.Objects;

/**
 * 用于文本段的关键字过滤，返回关键字在文本段中的起止位置
 *
 * @param <V> 与Node的值类型相同
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-23
 */
public class Found<V> {

    /**
     * key 在文本段中的起始位置
     */
    private final int start;

    /**
     * key 在文本段中的结束位置
     */
    private final int end;

    /**
     * 节点
     */
    private BaseNode<V> node;

    /**
     * key 对应的 value
     */
    private final V value;

    public Found(int start, int end, BaseNode<V> node) {
        this.start = start;
        this.end = end;
        this.node = node;
        this.value = node != null ? node.getValue() : null;
    }

    /**
     * @return value 在文本中的开始位置
     */
    public int getStart() {
        return start;
    }

    /**
     * @return value 在文本中的结束位置
     */
    public int getEnd() {
        return end;
    }

    /**
     * @return 节点
     */
    BaseNode<V> getNode() {
        return node;
    }

    /**
     * 将节点置为空
     */
    protected void setNodeNull() {
        this.node = null;
    }

    /**
     * @return value值
     */
    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Found)) {
            return false;
        }
        Found<?> word = (Found<?>) o;
        if (start != word.start) {
            return false;
        }
        if (end != word.end) {
            return false;
        }
        return Objects.equals(value, word.value);
    }

    @Override
    public int hashCode() {
        int result = start;
        result = 31 * result + end;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{\"start\":" + start +
                ", \"end\":" + end +
                (null == value ? "" : (", \"value\":\"" + value + "\"")) +
                "}";
    }
}