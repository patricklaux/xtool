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
 * 用于文本段的关键字过滤，返回关键字在文本段中的起止位置
 *
 * @param <V>   与Node的值类型相同
 * @param begin key 在文本段中的起始位置
 * @param end   key 在文本段中的结束位置
 * @param value key 对应的 value
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-23
 */
public record Found<V>(int begin, int end, String key, V value) {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Found<?> found)) {
            return false;
        }

        if (begin() != found.begin()) {
            return false;
        }
        if (end() != found.end()) {
            return false;
        }
        if (key() != null ? !key().equals(found.key()) : found.key() != null) {
            return false;
        }
        return value() != null ? value().equals(found.value()) : found.value() == null;
    }

    @Override
    public int hashCode() {
        int result = key() != null ? key().hashCode() : 0;
        result = 31 * result + begin();
        result = 31 * result + end();
        result = 31 * result + (value() != null ? value().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{\"begin\":" + begin +
                ", \"end\":" + end +
                (null == key ? "" : (", \"key\":\"" + key + "\"")) +
                (null == value ? "" : (", \"value\":\"" + value + "\"")) +
                "}";
    }

}