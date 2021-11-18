package com.igeeksky.xtool.core.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Patrick.Lau
 * @since 0.0.4 2021-10-21
 */
public class Lists {

    private Lists() {
    }

    public static <E> ArrayList<E> concatAndNew(List<ArrayList<E>> lists) {
        int size = 0;
        for (ArrayList<E> list : lists) {
            size += list.size();
        }
        ArrayList<E> out = new ArrayList<>(size);
        for (ArrayList<E> list : lists) {
            out.addAll(list);
        }
        return out;
    }

    public static <E> List<E> concat(List<List<E>> lists) {
        int size = lists.size();
        List<E> first = lists.get(0);
        for (int i = 1; i < size; i++) {
            first.addAll(lists.get(i));
        }
        return first;
    }
}
