package com.example.urbalog.Class;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * Custom Pair class who implements serialization
 * Use for data transfer between devices (Signal, Object)
 * @param <Signal> -> Signal for incoming payload
 * @param <S> -> Object
 */

public class Duo<Signal, S> implements Serializable {

    public final Signal sig;
    public final S second;

    public Duo(Signal s, S o)
    {
        this.sig = s;
        this.second = o;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Duo)) return false;
        Duo<?, ?> that = (Duo<?, ?>) o;
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
        return "Duo{" +
                "signal=" + sig +
                ", second=" + second +
                '}';
    }

    public static <A, B> Duo<A, B> create(A a, B b){
        return new Duo<A, B>(a, b);
    }

}
