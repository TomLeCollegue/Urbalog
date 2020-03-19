package com.example.urbalog.Class;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * Custom Pair class who implements serialization
 * Use for data transfer between devices (Signal, Object)
 * @param <S> -> Object
 */

public class TransferPackage<S> implements Serializable {

    public final Signal sig;
    public final S second;

    public TransferPackage(Signal s, S o) {
        this.sig = s;
        this.second = o;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransferPackage)) return false;
        TransferPackage<?> that = (TransferPackage<?>) o;
        return sig.equals(that.sig) &&
                second.equals(that.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sig, second);
    }

    @NonNull
    @Override
    public String toString() {
        return "TransferPackage{" +
                "first=" + sig +
                ", second=" + second +
                '}';
    }

    public static <B> TransferPackage<B> create(Signal a, B b){
        return new TransferPackage<B>(a, b);
    }

}
