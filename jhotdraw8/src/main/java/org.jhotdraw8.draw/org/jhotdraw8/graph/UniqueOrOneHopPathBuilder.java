package org.jhotdraw8.graph;

import org.jhotdraw8.annotation.Nonnull;

import java.util.ArrayDeque;
import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Builder for creating unique paths from a directed graph.
 * <p>
 * The builder searches for unique paths using a breadth-first search.<br>
 * Returns only a path if it is unique or if there is only one hop
 * from start to goal.
 *
 * @param <V> the vertex type
 * @param <A> the arrow type
 * @author Werner Randelshofer
 */
public class UniqueOrOneHopPathBuilder<V, A> extends AbstractPathBuilder<V, A> {

    public UniqueOrOneHopPathBuilder(@Nonnull DirectedGraph<V, A> g) {
        this(g::getNextVertices);
    }

    public UniqueOrOneHopPathBuilder(@Nonnull Function<V, Iterable<V>> nextNodesFunction) {
        super(nextNodesFunction);
    }

    private static class MyBackLink<VV, AA> extends BackLink<VV, AA> {

        final MyBackLink<VV, AA> parent;
        final VV vertex;
        final int depth;

        public MyBackLink(VV vertex, MyBackLink<VV, AA> parent, int depth) {
            this.vertex = vertex;
            this.parent = parent;
            this.depth = depth;
        }

        @Override
        BackLink<VV, AA> getParent() {
            return parent;
        }

        @Override
        VV getVertex() {
            return vertex;
        }
    }


    protected BackLink<V, A> search(@Nonnull V root,
                                    @Nonnull Predicate<V> goal,
                                    Function<V, Iterable<V>> nextNodesFunction,
                                    @Nonnull Predicate<V> visited,
                                    int maxLength) {

        Queue<MyBackLink<V, A>> queue = new ArrayDeque<>(16);

        MyBackLink<V, A> rootBackLink = new MyBackLink<>(root, null, maxLength);
        visited.test(root);
        queue.add(rootBackLink);
        MyBackLink<V, A> found = null;
        Set<V> nonUnique = new LinkedHashSet<>();
        while (!queue.isEmpty()) {
            MyBackLink<V, A> node = queue.remove();
            if (goal.test(node.vertex)) {
                if (found != null) {
                    return null;// path is not unique!
                }
                if (node.getParent() == rootBackLink) {
                    return node; // One hop is considered unique.
                }
                found = node;
            }
            if (node.depth > 0) {
                for (V next : nextNodesFunction.apply(node.vertex)) {
                    if (visited.test(next)) {
                        MyBackLink<V, A> backLink = new MyBackLink<V, A>(next, node, node.depth - 1);
                        queue.add(backLink);
                    } else {
                        nonUnique.add(next);
                    }
                }
            }
        }
        for (MyBackLink<V, A> node = found; node != null; node = node.parent) {
            if (nonUnique.contains(node.vertex)) {
                // path is not unique!
                return null;
            }
        }
        return found;
    }

}
