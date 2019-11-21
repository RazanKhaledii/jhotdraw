/*
 * @(#)DepthFirstSpliterator.java
 * Copyright © The authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.graph;

import org.jhotdraw8.annotation.NonNull;
import org.jhotdraw8.annotation.Nullable;
import org.jhotdraw8.collection.AbstractEnumeratorSpliterator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * DepthFirstSpliterator.
 *
 * @param <V> the vertex type
 * @author Werner Randelshofer
 */
public class DepthFirstSpliterator<V> extends AbstractEnumeratorSpliterator<V> {

    @NonNull
    private final Function<V, Iterable<V>> nextFunction;
    @NonNull
    private final Deque<V> deque;
    @NonNull
    private final Predicate<V> visited;
    private V current;

    /**
     * Creates a new instance.
     *
     * @param nextNodesFuncction the nextFunction
     * @param root               the root vertex
     */
    public DepthFirstSpliterator(Function<V, Iterable<V>> nextNodesFuncction, V root) {
        this(nextNodesFuncction, root, new HashSet<>()::add);
    }

    /**
     * Creates a new instance.
     *
     * @param nextFunction the function that returns the next vertices of a given vertex
     * @param root         the root vertex
     * @param visited      a predicate with side effect. The predicate returns true
     *                     if the specified vertex has been visited, and marks the specified vertex
     *                     as visited.
     */
    public DepthFirstSpliterator(@Nullable Function<V, Iterable<V>> nextFunction, @Nullable V root, @Nullable Predicate<V> visited) {
        super(Long.MAX_VALUE, ORDERED | DISTINCT | NONNULL);
        Objects.requireNonNull(nextFunction, "nextFunction is null");
        Objects.requireNonNull(root, "root is null");
        Objects.requireNonNull(visited, "visited is null");
        this.nextFunction = nextFunction;
        deque = new ArrayDeque<>(16);
        this.visited = visited;
        deque.push(root);
        visited.test(root);
    }

    @Override
    public boolean moveNext() {
        current = deque.pollLast();
        if (current == null) {
            return false;
        }
        for (V next : nextFunction.apply(current)) {
            if (visited.test(next)) {
                deque.addLast(next);
            }
        }
        return true;
    }
}
