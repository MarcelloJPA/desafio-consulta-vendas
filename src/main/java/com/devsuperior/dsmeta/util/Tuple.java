package com.devsuperior.dsmeta.util;

public class Tuple<T, U> {
    private T first;
    private U second;

    public Tuple(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public static <T, U> Tuple<T, U> of(T first, U second) {
        return new Tuple<>(first, second);
    }
}
