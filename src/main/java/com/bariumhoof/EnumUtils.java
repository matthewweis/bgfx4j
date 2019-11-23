package com.bariumhoof;

import com.bariumhoof.bgfx4j.enums.BGFX_STATE;

import java.util.EnumSet;
import java.util.Iterator;

public class EnumUtils {


    public static long flags(EnumSet<BGFX_STATE> states) {
        long bits = 0L;

        final var iterator = states.iterator();
        while (iterator.hasNext()) {
            final BGFX_STATE next = iterator.next();
            bits |= next.VALUE;
        }

        return bits;
    }

//    public static long flags(EnumSet<BGFX_STATE> states) {
//        long bits = 0L;
//
//        final var iterator = states.iterator();
//        while (iterator.hasNext()) {
//            final BGFX_STATE next = iterator.next();
//            bits |= next.VALUE;
//        }
//
//        return bits;
//    }

//    public static <E extends Enum<E>> long enumSetToBitFlags(EnumSet<E> states) {
//
//        if (states.size() > 64) {
//            throw new RuntimeException("Cannot convert enum set to long bit set as it contains over 64 enums");
//        }
//
//        // faster impl of:
//        // long bits = states.stream().reduce(0L, (n, s) -> n | s.VALUE, (n1, n2) -> n1 | n2);
//
//        long bits = 0L;
//
//        final var iterator = states.iterator();
//        while (iterator.hasNext()) {
//            final E next = iterator.next();
//            bits |= next.ordinal();
//        }
//
//        return bits;
//    }

}
