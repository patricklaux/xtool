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

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-11-15
 */
public interface NodeConvertor<T extends Node<?>, R extends TreeNode<?>> {

    /**
     * 节点转换（链表转树）
     *
     * @param node 待转换的节点
     * @return 转换后的节点
     */
    R toTreeNode(T node);

    /**
     * 节点转换（树转链表）
     *
     * @param node 待转换的节点
     * @return 转换后的节点
     */
    T fromTreeNode(R node);

}
