package io.github.omerturkkan.javapractice.generics.genericclass;

// Two independent type parameters
public class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    // Swapping returns a differently parameterised type
    public Pair<V, K> swap() {
        return new Pair<>(value, key);
    }

    // A generic method inside a generic class: R is unrelated to K and V
    public <R> Pair<K, R> withValue(R newValue) {
        return new Pair<>(key, newValue);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", key, value);
    }
}
