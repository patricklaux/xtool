package com.igeeksky.xtool.core.nlp;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-29
 */
public class TrieConstants {
    private TrieConstants() {
    }

    public static final int TABLE_MAX_CAPACITY = 1 << 16;
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
    public static final int REDUCE_RANGE_32 = 32;
    public static final int REDUCE_THRESHOLD_32 = 8;
    public static final int REDUCE_RANGE_16 = 16;
    public static final int REDUCE_THRESHOLD_16 = 6;
    public static final int REDUCE_RANGE_8 = 8;
    public static final int REDUCE_THRESHOLD_8 = 4;
    public static final int REDUCE_RANGE_4 = 4;
    public static final int REDUCE_THRESHOLD_4 = 2;

}
