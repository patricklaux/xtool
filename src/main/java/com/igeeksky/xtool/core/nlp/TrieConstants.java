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
 * @since 0.0.4 2021-10-29
 */
public class TrieConstants {
    private TrieConstants() {
    }

    public static final int TABLE_MAX_CAPACITY = 1 << 16;
    public static final int TABLE_HALF_CAPACITY = TABLE_MAX_CAPACITY >> 1;
    public static final int TABLE_MAX_MASK = TABLE_MAX_CAPACITY - 1;
    public static final int TABLE_INITIAL_CAPACITY = 1;
    public static final int TO_TREE_NODE_THRESHOLD = 8;
    public static final int FROM_TREE_NODE_THRESHOLD = 3;
    public static final int AVL_RIGHT_ROTATE_THRESHOLD = 2;
    public static final int AVL_LEFT_ROTATE_THRESHOLD = -2;
    public static final int AVL_RIGHT_SLANT = -1;
    public static final int AVL_LEFT_SLANT = 1;

    /**
     * 根据当前直接后缀的数量来判断缩容策略，预留一定的缓冲空间，避免增加一个Key就扩容，删除一个key就缩容，同时又避免空间浪费过多
     */
    public static final int EXPAND_RANGE_63488 = 63488;
    public static final int REDUCE_RANGE_63488 = EXPAND_RANGE_63488 - 30;

    public static final int REDUCE_RANGE_32768 = 32768;
    public static final int REDUCE_THRESHOLD_32768 = 28;

    public static final int REDUCE_RANGE_16384 = 16384;
    public static final int REDUCE_THRESHOLD_16384 = 26;

    public static final int REDUCE_RANGE_8192 = 8192;
    public static final int REDUCE_THRESHOLD_8192 = 24;

    public static final int REDUCE_RANGE_4096 = 4096;
    public static final int REDUCE_THRESHOLD_4096 = 22;

    public static final int REDUCE_RANGE_2048 = 2048;
    public static final int REDUCE_THRESHOLD_2048 = 20;

    public static final int REDUCE_RANGE_1024 = 1024;
    public static final int REDUCE_THRESHOLD_1024 = 18;

    public static final int REDUCE_RANGE_512 = 512;
    public static final int REDUCE_THRESHOLD_512 = 16;

    public static final int REDUCE_RANGE_256 = 256;
    public static final int REDUCE_THRESHOLD_256 = 14;

    public static final int REDUCE_RANGE_128 = 128;
    public static final int REDUCE_THRESHOLD_128 = 12;

    public static final int REDUCE_RANGE_64 = 64;
    public static final int REDUCE_THRESHOLD_64 = 10;

    public static final int REDUCE_RANGE_32 = 32;
    public static final int REDUCE_THRESHOLD_32 = 8;

    public static final int REDUCE_RANGE_16 = 16;
    public static final int REDUCE_THRESHOLD_16 = 6;

    public static final int REDUCE_RANGE_8 = 8;
    public static final int REDUCE_THRESHOLD_8 = 4;

    public static final int REDUCE_RANGE_4 = 4;
    public static final int REDUCE_THRESHOLD_4 = 2;
}
