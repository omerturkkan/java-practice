package io.github.omerturkkan.javapractice.generics.genericclass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

// One implementation reused for any entity type, with no casting
public class Repository<T> {
    private final List<T> items = new ArrayList<>();

    public void save(T item) {
        items.add(item);
    }

    public Optional<T> findFirst(Predicate<T> filter) {
        for (T item : items) {
            if (filter.test(item)) return Optional.of(item);
        }
        return Optional.empty();
    }

    public List<T> findAll(Predicate<T> filter) {
        List<T> result = new ArrayList<>();
        for (T item : items) {
            if (filter.test(item)) result.add(item);
        }
        return result;
    }

    public int count() {
        return items.size();
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
