/* @(#)DirectedGraph.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */

package org.jhotdraw8.graph;

import java.util.Iterator;
import java.util.function.Function;

/**
 * Defines a facade for a directed graph.
 * <p>
 * A directed graph {@code G} is defined as a tuple {@code G = (V, E)}.
 * <p>
 * The graph is composed of set of vertices {@code V = (v_1, ..., v_n) }, and a
 * set of edges {@code E = (e_1, ..., e_n)}.
 * <p>
 * An edge is an ordered (directed) pair of two vertices {@code e=(v_i, v_j)}.
 *
 * @author Werner Randelshofer
 * @version $$Id$$
 * @param <V> the vertex type
 */
public interface DirectedGraph<V> {

    /**
     * Returns the number of vertices {@code V}.
     *
     * @return vertex count
     */
    int getVertexCount();

    /**
     * Returns Vertex vi.
     *
     * @param indexOfVertex index of vertex
     * @return vertex vi
     */
    V getVertex(int indexOfVertex);

    /**
     * Returns the number of next vertices of v.
     *
     * @param vertex a vertex
     * @return the number of next vertices of v.
     */
    int getNextCount(V vertex);

    /**
     * Returns the i-th next vertex of v.
     *
     * @param vertex a vertex
     * @param i index of next vertex
     * @return the i-th next vertex of v
     */
    V getNext(V vertex, int i);

    /**
     * Dumps the graph for debugging purposes.
     * @return a dump of the directed graph
     */
    default String dump() {
        return dump(Object::toString);
    }

    /**
     * Returns the next vertices after the specified vertex.
     *
     * @param vertex a vertex
     * @return an iterable for the next vertices after vertex
     */
    default Iterable<V> getNextVertices(V vertex) {
        class NextVertexIterator implements Iterator<V> {

            private int index;
            private final V vertex;
            private final int nextCount;

            public NextVertexIterator(V vertex) {
                this.vertex = vertex;
                this.nextCount = getNextCount(vertex);
            }

            @Override
            public boolean hasNext() {
                return index < nextCount;
            }

            @Override
            public V next() {
                return getNext(vertex, index++);
            }

        }
        return () -> new NextVertexIterator(vertex);
    }

    /**
     * Dumps the graph for debugging purposes.
     *
     * /** Returns all vertices.
     *
     * @return an iterable for all vertice
     */
    default Iterable<V> getVertices() {
        class VertexIterator implements Iterator<V> {

            private int index;
            private final int vertexCount;

            public VertexIterator() {
                vertexCount = getVertexCount();
            }

            @Override
            public boolean hasNext() {
                return index < vertexCount;
            }

            @Override
            public V next() {
                return getVertex(index++);
            }

        }
        return () -> new VertexIterator();
    }

    /**
     * Dumps the graph for debugging purposes.
     * @param toStringFunction a function which converts a vertex to a string
     * @return the dumped graph
     */
    default String dump(Function<V, String> toStringFunction) {
        StringBuilder buf = new StringBuilder();
        buf.append("DirectedGraph:");
        for (int ii = 0, nn = getVertexCount(); ii < nn; ii++) {
            V v = getVertex(ii);
            buf.append("\n  ").append(toStringFunction.apply(v)).append(" -> ");
            for (int i = 0, n = getNextCount(v); i < n; i++) {
                if (i != 0) {
                    buf.append(", ");
                }
                buf.append(toStringFunction.apply(getNext(v, i)));
            }
            buf.append('.');
        }
        return buf.toString();
    }
}
