package com.example.urbalog.Class;

import java.io.Serializable;
import java.util.Objects;

public class Triplet<T, U, V> extends Object implements Serializable {

    private T first;
    private U second;
    private V third;

    public Triplet(T first, U second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() { return first; }
    public U getSecond() { return second; }
    public V getThird() { return third; }

    public void setFirst(T first) { this.first = first; }
    public void setSecond(U second) { this.second = second; }
    public void setThird(V third) { this.third = third; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triplet)) return false;
        Triplet<?, ?, ?> triplet = (Triplet<?, ?, ?>) o;
        return Objects.equals(getFirst(), triplet.getFirst()) &&
                Objects.equals(getSecond(), triplet.getSecond()) &&
                Objects.equals(getThird(), triplet.getThird());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirst(), getSecond(), getThird());
    }

    @Override
    public String toString() {
        return "Triplet{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }
}
