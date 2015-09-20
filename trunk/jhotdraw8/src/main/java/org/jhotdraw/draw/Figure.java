/* @(#)Figure.java
 * Copyright (c) 2015 by the authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.draw;

import static java.lang.Math.*;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlySetProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Effect;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import org.jhotdraw.beans.PropertyBean;
import org.jhotdraw.collection.Key;
import org.jhotdraw.draw.connector.Connector;
import org.jhotdraw.draw.handle.Handle;
import org.jhotdraw.draw.handle.SimpleHighlightHandle;

/**
 * A <em>figure</em> is a graphical (figurative) element of a {@link Drawing}.
 * <p>
 * A figure can render its graphical representation into a JavaFX {@code Node}.
 * </p>
 * <p>
 * A figure can be composed of other figures in a tree structure.
 * The composition is implemented with the {@code children} and the
 * {@code parent} properties. The composition can be restricted to specific
 * child and parent types using the type parameters {@code <Figure>} and
 * {@code <F>}.</p>
 * <p>
 * Some figures can be connected to other figures.
 * All figures which are connected with this figure must maintain an entry in
 * the {@code connections} property of this figure.
 * </p>
 * <p>
 * The state of a figure is described by its property map. The property map
 * consists of key and value pairs. The keys are of type {@link Key}. If a
 * property affects the graphical representation of the figure, a key of type
 * {@code FigureKey} must be used. {@code FigureKey} provides a mean to describe
 * how the value affects the graphical representation of the figure.</p>
 * <p>
 * The state of a figure may depend on the state of other figures. The
 * dependencies may be cyclic due to connections.
 * A figure provides a method {@code layout} which updates the state of the
 * figure and of its descendants in the tree structure, but not of connected
 * figures.
 * </p>
 * <p>
 * A figure can produce {@code Handle}s which allow to graphically change
 * the state of the figure.
 *
 * @author Werner Randelshofer @version $Id$
 */
public interface Figure extends PropertyBean {
    // ----
    // key declarations
    // ----

    /**
     * Specifies a blend mode applied to the figure. The {@code null} value is
     * interpreted as {@code SRC_OVER}.
     * <p>
     * Default value: {@code SRC_OVER}.
     */
    public static FigureKey<BlendMode> BLEND_MODE = new FigureKey<BlendMode>("blendMode", BlendMode.class, DirtyMask.of(DirtyBits.NODE), BlendMode.SRC_OVER);
    /**
     * Specifies an effect applied to the figure. The {@code null} value means
     * that no effect is applied.
     * <p>
     * Default value: {@code null}.
     */
    public static FigureKey<Effect> EFFECT = new FigureKey<>("effect", Effect.class, DirtyMask.of(DirtyBits.NODE), null);
    /**
     * Specifies the opacity of the figure. A figure with {@code 0} opacity is
     * completely translucent. A figure with {@code 1} opacity is completely
     * opaque.
     * <p>
     * Values smaller than {@code 0} are treated as {@code 0}. Values larger
     * than {@code 1} are treated as {@code 1}.
     * <p>
     * Default value: {@code 1}.
     */
    public static FigureKey<Double> OPACITY = new FigureKey<>("opacity", Double.class, DirtyMask.of(DirtyBits.NODE), 1.0);
    /**
     * Defines the angle of rotation around the center of the figure in degrees.
     * Default value: {@code 0}.
     */
    public static FigureKey<Double> ROTATE = new FigureKey<>("rotate", Double.class, DirtyMask.of(DirtyBits.NODE, DirtyBits.LAYOUT), 0.0);
    /**
     * Defines the rotation axis used. Default value: {@code Rotate.Z_AXIS}.
     */
    public static FigureKey<Point3D> ROTATION_AXIS = new FigureKey<>("rotationAxis", Point3D.class, DirtyMask.of(DirtyBits.NODE, DirtyBits.LAYOUT), Rotate.Z_AXIS);
    /**
     * Defines the scale factor by which coordinates are scaled on the x axis
     * about the center of the figure. Default value: {@code 1}.
     */
    public static FigureKey<Double> SCALE_X = new FigureKey<>("scaleX", Double.class, DirtyMask.of(DirtyBits.NODE, DirtyBits.LAYOUT), 1.0);
    /**
     * Defines the scale factor by which coordinates are scaled on the y axis
     * about the center of the figure. Default value: {@code 1}.
     */
    public static FigureKey<Double> SCALE_Y = new FigureKey<>("scaleY", Double.class, DirtyMask.of(DirtyBits.NODE, DirtyBits.LAYOUT), 1.0);
    /**
     * Defines the scale factor by which coordinates are scaled on the z axis
     * about the center of the figure. Default value: {@code 1}.
     */
    public static FigureKey<Double> SCALE_Z = new FigureKey<>("scaleZ", Double.class, DirtyMask.of(DirtyBits.NODE, DirtyBits.LAYOUT), 1.0);
    /**
     * Defines the translation on the x axis
     * about the center of the figure. Default value: {@code 0}.
     */
    public static FigureKey<Double> TRANSLATE_X = new FigureKey<>("translateX", Double.class, DirtyMask.of(DirtyBits.NODE, DirtyBits.LAYOUT), 0.0);
    /**
     * Defines the translation on the y axis
     * about the center of the figure. Default value: {@code 0}.
     */
    public static FigureKey<Double> TRANSLATE_Y = new FigureKey<>("translateY", Double.class, DirtyMask.of(DirtyBits.NODE, DirtyBits.LAYOUT), 0.0);
    /**
     * Defines the translation on the z axis
     * about the center of the figure. Default value: {@code 0}.
     */
    public static FigureKey<Double> TRANSLATE_Z = new FigureKey<>("translateZ", Double.class, DirtyMask.of(DirtyBits.NODE, DirtyBits.LAYOUT), 0.0);

    // ----
    // property names
    // ----
    /**
     * The name of the children property.
     */
    public final static String CHILDREN_PROPERTY = "children";
    /**
     * The name of the parent property.
     */
    public final static String PARENT_PROPERTY = "parent";
    /**
     * The name of the connections property.
     */
    public final static String CONNECTIONS_PROPERTY = "connections";

    // ----
    // property fields
    // ----
    /**
     * The child figures.
     * <p>
     * All changes on this property causes this figure to fire an invalidation
     * event.
     * <p>
     * If a child is added to this figure, the child must be removed from its
     * former parent. This figure must set itself set as the parent of the
     * child immediately after the figure has been added.</p>
     * <p>
     * If a child is removed from this figure, this figure must set parent to
     * null
     * immediately before the child is removed.</p>
     * <p>
     * Note that this method returns a {@code ReadOnlyListProperty} and not just
     * an {@code ObservableList}. {@code ListChangeListener}s can get the
     * associated {@code Figure} using the following code:</p>
     * <pre>{@code
     * (ListChangeListener.Change change) -> Figure figure =
     *      (Figure) ((ReadOnlyProperty) change.getList()).getBean();
     * }</pre>
     *
     *
     * @return the children property, with {@code getBean()} returning this
     * figure, and {@code getName()} returning {@code CHILDREN_PROPERTY}.
     */
    ReadOnlyListProperty<Figure> childrenProperty();

    /**
     * The connection figures.
     * <p>
     * By convention this set is maintained by the connected figures.
     * <p>
     * For example, to remove a {@code ConnectionFigure} from this set set its
     * corresponding
     * {@code START_FIGURE} or {@code END_FIGURE} property to null.
     *
     * @return the connections property, with {@code getBean()} returning this
     * figure, and {@code getName()} returning {@code CONNECTIONS_PROPERTY}.
     */
    ReadOnlySetProperty<Figure> connectionsProperty();

    /**
     * The parent figure.
     * <p>
     * If this figure has not been added as a child to another figure, then this
     * variable will be null.
     * </p>
     * By convention the parent is set exclusively by a composite figure on
     * its child figures.
     * The composite figure sets parent to itself on a child immediately
     * after the child figure has been added to the composite figure.
     * The composite figure sets parent to {@code null} on a child immediately
     * after the child figure has been removed from the composite figure.
     *
     * @return the parent property, with {@code getBean()} returning this
     * figure, and {@code getName()} returning {@code PARENT_PROPERTY}.
     */
    ObjectProperty<Figure> parentProperty();

    // ----
    // behavior methods
    // ----
    /**
     * The bounds that should be used for layout calculations for this figure.
     * <p>
     * The bounds are given in the untransformed local coordinate
     * space of the figure.
     *
     * @return the layout bounds
     */
    public Bounds getBoundsInLocal();

    default public Bounds getBoundsInParent() {
        // FIXME apply transform properties
        // also implementations have to transform their
        // geometry so that we get tighter bounds
        return getBoundsInLocal();
    }

    default public Bounds getBoundsInDrawing() {
        // FIXME apply parent transforms until root
        // also implementations have to transform their
        // geometry so that we get tighter bounds
        return getBoundsInLocal();
    }

    /**
     * Attempts to change the layout bounds of the figure.
     * <p>
     * The figure may choose to only partially change its layout bounds.
     * <p>
     * Reshape typically changes property values in this figure. The way how
     * this is performed is implementation specific.
     *
     * @param transform the desired transformation
     */
    void reshape(Transform transform);

    /**
     * Attempts to change the layout bounds of the figure.
     * <p>
     * Width and height are ignored, if the figure is not resizable.
     * <p>
     * If the layout bounds of the figure changes, it fires an invalidation
     * event.
     *
     * @param x desired x-position
     * @param y desired y-position
     * @param width desired width, may be negative
     * @param height desired height, may be negative
     */
    default void reshape(double x, double y, double width, double height) {
        Bounds oldBounds = getBoundsInLocal();
        Rectangle2D newBounds = new Rectangle2D(x - min(width, 0), y
                - min(height, 0), abs(width), abs(height));

        double sx = newBounds.getWidth() / oldBounds.getWidth();
        double sy = newBounds.getHeight() / oldBounds.getHeight();

        Affine reshape = new Affine();
        Affine tx = new Affine();
        tx.appendTranslation(-oldBounds.getMinX(), -oldBounds.getMinY());
        if (!Double.isNaN(sx) && !Double.isNaN(sy)
                && !Double.isInfinite(sx) && !Double.isInfinite(sy)
                && (sx != 1d || sy != 1d)
                && !(sx < 0.0001) && !(sy < 0.0001)) {
            reshape.append(tx);
            tx.setToIdentity();
            tx.scale(sx, sy);
            reshape.append(tx);
            tx.setToIdentity();
        }
        tx.translate(newBounds.getMinX(), newBounds.getMinY());
        reshape.append(tx);
        reshape(reshape);
    }

    /**
     * This method is invoked by {@code DrawingRenderer}, when it needs a node
     * to
     * create a scene graph for a figure.
     * <p>
     * A typical implementation should look like this:
     * <pre>{@code
     * public Node createNode(DrawingRenderer v) {
     *     return new ...desired subclass of Node...();
     * }
     * }</pre>
     * <p>
     * A figure may be shown in multiple {@code DrawingRenderer}s
     * simultaneously. Each {@code DrawingRenderer} uses this method to
     * instantiate a JavaFX node for the figure. This method must create a new
     * instance because returning an already existing instance may cause
     * undesired side effects on other {@code DrawingRenderer}s.
     *
     * @param renderer the drawing view which will use the node
     * @return the newly created node
     */
    Node createNode(DrawingRenderer renderer);

    /**
     * This method is invoked by {@code DrawingRenderer}, when it needs to
     * update
     * the node which represents the scene graph in the figure.
     * <p>
     * A figure which is composed from child figures, must add the nodes of its
     * children to its node. This ensures that coordinate space transformations
     * of the composed figure are properly propagated to its children.
     * </p>
     * <pre>{@code
     * public void updateNode(DrawingView v, Node n) {
     *     ObservableList<Node> group = ((Group) n).getChildren();
     *     group.clear();
     *     for (Figure child : childrenProperty()) {
     *         group.add(v.getNode(child));
     *     }
     * }
     * }</pre>
     * <p>
     * A figure may be shown in multiple {@code DrawingView}s. Each
     * {@code DrawingView} view uses this method to update the a JavaFX node for
     * the figure.
     *
     * @param renderer the drawing view
     * @param node the node which was created with {@link #createNode}
     */
    void updateNode(DrawingRenderer renderer, Node node);

    /**
     * Whether children may be added to this figure.
     *
     * @return true if children are allowed
     */
    boolean allowsChildren();

    /**
     * Whether the {@code layout} method of this figure does anything.
     *
     * @return true if the {@code layout} method is not empty.
     */
    boolean isLayoutable();

    /**
     * Whether the figure is selectable.
     *
     * @return true if the user may select the figure
     */
    boolean isSelectable();

    /**
     * Creates handles of the specified level and for the specified drawing
     * view.
     *
     * @param detailLevel The desired detail level
     * @param dv The drawing view which will display the handles
     * @return The handles. Returns an empty list if the figure does not provide
     * handles at the desired detail level.
     */
    default List<Handle> createHandles(int detailLevel, DrawingView dv) {
        if (detailLevel > 0) {
            return Collections.emptyList();
        } else {
            List<Handle> list = new LinkedList<>();
            list.add(new SimpleHighlightHandle(this, dv));
            return list;
        }
    }

    /**
     * Gets a connector for this figure at the given location.
     *
     * @param p the location of the connector.
     * @param prototype The prototype used to create a connection or null if
     * unknown. This allows for specific connectors for different
     * connection figures.
     * @return Returns the connector. Returns null if there is no connector
     * at the given location.
     */
    Connector findConnector(Point2D p, Figure prototype);

    /** Updates the state of this figure and of its descendant figures.
     * Does not update connection figures.
     */
    void layout();
    // ----
    // convenience methods
    // ----

    /**
     * Adds a new child to the figure.
     *
     * @param newChild the new child
     */
    default void add(Figure newChild) {
        childrenProperty().add(newChild);
    }

    /**
     * Removes a child from the figure.
     *
     * @param child a child of the figure
     */
    default void remove(Figure child) {
        childrenProperty().remove(child);
    }

    /**
     * Gets the child with the specified index from the figure.
     *
     * @param index the index
     * @return the child
     */
    default Figure getChild(int index) {
        return childrenProperty().get(index);
    }

    /**
     * Gets the last child.
     *
     * @return The last child. Returns null if the figure has no children.
     */
    default Figure getLastChild() {
        return childrenProperty().isEmpty() ? null : childrenProperty().get(0);
    }

    /**
     * Gets the first child.
     *
     * @return The first child. Returns null if the figure has no children.
     */
    default Figure getFirstChild() {
        return childrenProperty().isEmpty() //
                ? null//
                : childrenProperty().get(childrenProperty().getSize() - 1);
    }

    /**
     * Returns all children of the figure.
     *
     * @return a list of the children
     */
    default ObservableList<Figure> children() {
        return childrenProperty().get();
    }

    /**
     * Returns the parent figure.
     * <p>
     * Note that there is no convenience method named {@code setParent}.
     *
     * @return parent figure or null, if the figure has no parent.
     */
    default Figure getParent() {
        return parentProperty().get();
    }

    /** Returns the root.
     * @return the root  */
    default Figure getRoot() {
        Figure root = this;
        while (root.getParent() != null) {
            root = root.getParent();
        }
        return root;
    }

    /** Returns an iterable which can iterate through this figure and all
     * its descendants in preorder sequence.
     * @return the iterable
     */
    default public Iterable<Figure> preorderIterable() {

        return new Iterable<Figure>() {

            @Override
            public Iterator<Figure> iterator() {
                return new PreorderIterator(Figure.this);
            }
        };
    }

    /**
     * Returns all connections of the figure.
     *
     * @return a list of the children
     */
    default ObservableSet<Figure> connections() {
        return connectionsProperty().get();
    }

    /**
     * Updates a figure node with all applicable {@code FigureKey}s defined in
     * this interface.
     * <p>
     * This method is intended to be used by {@link #updateNode}.
     *
     * @param node a node which was created with method {@link #createNode}.
     */
    default void applyFigureProperties(Node node) {
        node.setBlendMode(get(BLEND_MODE));
        node.setEffect(get(EFFECT));
        node.setOpacity(get(OPACITY));
        node.setRotate(get(ROTATE));
        node.setRotationAxis(get(ROTATION_AXIS));
        node.setScaleX(get(SCALE_X));
        node.setScaleY(get(SCALE_Y));
        node.setScaleZ(get(SCALE_Z));
        node.setTranslateX(get(TRANSLATE_X));
        node.setTranslateY(get(TRANSLATE_Y));
        node.setTranslateZ(get(TRANSLATE_Z));
    }

    // ---
    // static methods
    // ---
    /**
     * Returns all keys declared in the figure class and inherited from parent
     * classes.
     *
     * @param f A figure.
     * @return the keys
     */
    public static Set<Key<?>> getKeys(Figure f) {
        return getDeclaredAndInheritedKeys(f.getClass());
    }

    /**
     * Returns all keys declared in this class and inherited from parent
     * classes.
     *
     * @param c A figure class.
     * @return the keys
     */
    public static Set<Key<?>> getDeclaredAndInheritedKeys(Class<?> c) {
        try {
            HashSet<Key<?>> keys = new HashSet<>();
            for (Field f : c.getFields()) {
                if (FigureKey.class.isAssignableFrom(f.getType())) {
                    FigureKey<?> k = (FigureKey<?>) f.get(null);
                    keys.add(k);
                }
            }
            return keys;
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new InternalError("class can not read its own keys");
        }
    }

    static class PreorderIterator implements Iterator<Figure> {

        private final LinkedList<Iterator<Figure>> stack = new LinkedList<>();

        private PreorderIterator(Figure root) {
            LinkedList<Figure> v = new LinkedList<>();
            v.add(root);
            stack.push(v.iterator());
        }

        @Override
        public boolean hasNext() {
            return (!stack.isEmpty() && stack.peek().hasNext());
        }

        @Override
        public Figure next() {
            Iterator<Figure> iter = stack.peek();
            Figure node = iter.next();
            Iterator<Figure> children = node.children().iterator();

            if (!iter.hasNext()) {
                stack.pop();
            }
            if (children.hasNext()) {
                stack.push(children);
            }
            return node;
        }
    }

    /** Transforms the specified point from drawing coordinates into local
     * coordinates.
     *
     * @param p point in drawing coordinates
     * @return point in local coordinates
     */
    default Point2D drawingToLocal(Point2D p) {
        // FIXME implement me
        return p;
    }

    /** Transforms the specified point from local coordinates into drawing
     * coordinates.
     *
     * @param p point in drawing coordinates
     * @return point in local coordinates
     */
    default Point2D localToDrawing(Point2D p) {
        // FIXME implement me
        return p;
    }
}
