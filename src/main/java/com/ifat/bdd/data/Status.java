package com.ifat.bdd.data;

import java.util.Arrays;

public enum Status {
    SHORT(0, 9),
    MEDIUM(10, 19),
    LONG(20, Integer.MAX_VALUE);

    private final int min;
    private final int max;

    Status(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static Status getStatus(int strLen) {
        return Arrays.stream(Status.values())
                .filter(e -> strLen <= e.max && strLen >= e.min)
                .findFirst()
                .get();
    }
}
