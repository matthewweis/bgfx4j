package com.bariumhoof;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Flags {

    public static boolean containsFlag(byte flags, byte flag) {
        return (flags & flag) != (byte) 0;
    }

    public static boolean containsFlag(short flags, short flag) {
        return (flags & flag) != (short) 0;
    }

    public static boolean containsFlag(int flags, int flag) {
        return (flags & flag) != 0;
    }

    public static boolean containsFlag(long flags, long flag) {
        return (flags & flag) != 0L;
    }

}
