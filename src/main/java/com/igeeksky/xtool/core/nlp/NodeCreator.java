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
 * 创建新节点（避免Node与具体实现耦合）
 *
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-30
 */
public interface NodeCreator<V> {

    /**
     * 创建节点
     *
     * @param c 字符
     * @return 新创建的节点
     */
    Node<V> apply(char c);

}