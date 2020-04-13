package com.example.urbalog.Class;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * Custom Pair class who implements serialization
 * Use for data transfer between devices (Game instance, Object)
 * @param <F> -> Game
 * @param <S> -> Object
 */

public class Duo<F, S> implements Serializable {
    public final F first;
    public S second;

    public Duo(F s, S o)
    {
        this.first = s;
        this.second = o;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Duo)) return false;
        Duo<?, ?> that = (Duo<?, ?>) o;
        return first.equals(that.first) &&
                second.equals(that.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @NonNull
    @Override
    public String toString() {
        return "Duo{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    public static <A, B> Duo<A, B> create(A a, B b){
        return new Duo<A, B>(a, b);
    }

}
