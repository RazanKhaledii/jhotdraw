/*
 * @(#)ImmutableSets.java
 * Copyright © 2020 The authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.collection;

import org.jhotdraw8.annotation.NonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class ImmutableSets {
    @NonNull
    public static <T> ImmutableSet<T> add(@NonNull Collection<T> collection, T item) {
        switch (collection.size()) {
        case 0:
            return new ImmutableHashSet<>(Collections.singleton(item));
        default:
            Set<T> a = new LinkedHashSet<>(collection);
            a.add(item);
            return new ImmutableHashSet<>(true, a);
        }
    }

    @NonNull
    public static <T> ImmutableSet<T> add(@NonNull ReadOnlyCollection<T> collection, T item) {
        switch (collection.size()) {
        case 0:
            return new ImmutableHashSet<>(Collections.singleton(item));
        default:
            Set<T> a = new LinkedHashSet<T>(new CollectionWrapper<T>(collection));
            a.add(item);
            return new ImmutableHashSet<>(true, a);
        }
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public static <T> ImmutableSet<T> emptySet() {
        return (ImmutableSet<T>) ImmutableHashSet.EMPTY;
    }

    @NonNull
    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> ImmutableSet<T> of(@NonNull T... items) {
        switch (items.length) {
        case 0:
            return emptySet();
        case 1:
            return new ImmutableSingletonSet<>(items[0]);
        default:
            return new ImmutableHashSet<T>(items);
        }
    }

    @NonNull
    public static <T> ImmutableSet<T> ofCollection(@NonNull Collection<T> collection) {
        switch (collection.size()) {
        case 0:
            return emptySet();
        case 1:
            return new ImmutableSingletonSet<>(collection.iterator().next());
        default:
            return new ImmutableHashSet<T>(collection);
        }
    }

    @NonNull
    public static <T> ImmutableSet<T> ofCollection(ReadOnlyCollection<T> collection) {
        if (collection instanceof ImmutableSet) {
            return (ImmutableSet<T>) collection;
        }
        switch (collection.size()) {
        case 0:
            return emptySet();
        case 1:
            return new ImmutableSingletonSet<>(collection.iterator().next());
        default:
            return new ImmutableHashSet<T>(collection);
        }
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public static <T> ImmutableSet<T> ofArray(Object[] a, int offset, int length) {
        switch (length) {
        case 0:
            return emptySet();
        case 1:
            return new ImmutableSingletonSet<>((T) a[offset]);
        default:
            return new ImmutableHashSet<T>(a, offset, length);
        }
    }

    @NonNull
    @SuppressWarnings({"unchecked"})
    public static <T> ImmutableSet<T> remove(@NonNull Collection<T> collection, T item) {
        switch (collection.size()) {
        case 0:
            return (ImmutableSet<T>) emptySet();
        case 1:
            if (collection.contains(item)) {
                return (ImmutableSet<T>) emptySet();
            } else {
                return ofCollection(collection);
            }
        case 2:
            if (collection.contains(item)) {
                Iterator<T> iter = collection.iterator();
                T one = iter.next();
                T two = iter.next();
                return new ImmutableSingletonSet<>(one.equals(item) ? two : one);

            } else {
                return ofCollection(collection);
            }
        default:
            if (collection.contains(item)) {
                Set<T> a = new LinkedHashSet<>(collection);
                a.remove(item);
                return new ImmutableHashSet<>(true, a);
            } else {
                return ofCollection(collection);
            }
        }
    }

    @NonNull
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> ImmutableSet<T> remove(@NonNull ReadOnlyCollection<T> collection, T item) {
        switch (collection.size()) {
        case 0:
            return (ImmutableSet<T>) emptySet();
        case 1:
            if (collection.contains(item)) {
                return (ImmutableSet<T>) emptySet();
            } else {
                return ofCollection(collection);
            }
        case 2:
            if (collection.contains(item)) {
                Iterator<T> iter = collection.iterator();
                T one = iter.next();
                T two = iter.next();
                return new ImmutableSingletonSet<>(one.equals(item) ? two : one);
            } else {
                return ofCollection(collection);
            }
        default:
            if (collection.contains(item)) {
                Set<T> a = new LinkedHashSet<>(new CollectionWrapper<>(collection));
                a.remove(item);
                return new ImmutableHashSet<>(true, a);
            } else {
                return ofCollection(collection);
            }
        }
    }
}
