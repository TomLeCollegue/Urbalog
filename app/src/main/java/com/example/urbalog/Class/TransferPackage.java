package com.example.urbalog.Class;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * Custom Pair class who implements serialization
 * Use for data transfer between devices (Game instance, Object)
 * @param <F> -> Game Instance
 * @param <S> -> Object
 */

public class TransferPackage<F, S> implements Serializable {

    public final F first;
    public final S second;

    public TransferPackage(F g, S o)
    {
        this.first = g;
        this.second = o;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransferPackage)) return false;
        TransferPackage<?, ?> that = (TransferPackage<?, ?>) o;
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
        return "TransferPackage{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    public static <A, B> TransferPackage<A, B> create(A a, B b){
        return new TransferPackage<A, B>(a, b);
    }

}
