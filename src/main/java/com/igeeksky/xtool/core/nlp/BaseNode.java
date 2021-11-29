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

import java.util.Map;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-10
 */
abstract class BaseNode<V> implements Iterable<Node<V>>, Map.Entry<Character, V> {

    public final char c;
    protected V value;

    public BaseNode(char c) {
        this.c = c;
    }

    public BaseNode(char c, V value) {
        this.c = c;
        this.value = value;
    }

    public char getC() {
        return c;
    }

    @Override
    public Character getKey() {
        return c;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        return this.value = value;
    }

    /**
     * 增加子节点（或返回已有的与字符c相等的子节点）
     *
     * @param c         字符
     * @param creator   节点创建器
     * @param convertor 节点转换器
     * @return 如果存在与字符c相等的原有子节点，则返回该子节点；否则，新增子节点（结果不为空）
     */
    public abstract Node<V> addChild(char c, NodeCreator<V> creator, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor);

    /**
     * 查找子节点
     *
     * @param c 字符
     * @return 如果存在与字符c相等的原有子节点，则返回该子节点；否则，返回空。
     */
    public abstract Node<V> findChild(char c);

    /**
     * 删除子节点
     *
     * @param child     子节点
     * @param convertor 节点转换器
     */
    public abstract void deleteChild(Node<V> child, NodeConvertor<? extends Node<V>, ? extends TreeNode<V>> convertor);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseNode)) {
            return false;
        }

        BaseNode<?> baseNode = (BaseNode<?>) o;

        if (getC() != baseNode.getC()) {
            return false;
        }
        return getValue() != null ? getValue().equals(baseNode.getValue()) : baseNode.getValue() == null;
    }

    @Override
    public int hashCode() {
        int result = getC();
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        return result;
    }
}