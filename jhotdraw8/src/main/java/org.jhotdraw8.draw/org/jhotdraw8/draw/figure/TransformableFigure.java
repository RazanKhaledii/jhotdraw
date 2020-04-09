/*
 * @(#)TransformableFigure.java
 * Copyright © The authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.draw.figure;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import org.jhotdraw8.annotation.NonNull;
import org.jhotdraw8.annotation.Nullable;
import org.jhotdraw8.collection.ImmutableList;
import org.jhotdraw8.collection.ImmutableLists;
import org.jhotdraw8.collection.Key;
import org.jhotdraw8.css.CssPoint2D;
import org.jhotdraw8.css.text.CssTranslate3DConverterOLD;
import org.jhotdraw8.draw.key.DoubleStyleableKey;
import org.jhotdraw8.draw.key.ObjectFigureKey;
import org.jhotdraw8.draw.key.Point3DStyleableMapAccessor;
import org.jhotdraw8.draw.key.Scale3DStyleableMapAccessor;
import org.jhotdraw8.draw.key.TransformListStyleableKey;
import org.jhotdraw8.draw.render.RenderContext;
import org.jhotdraw8.geom.PreciseRotate;
import org.jhotdraw8.geom.Transforms;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.jhotdraw8.draw.figure.FigureImplementationDetails.CACHE;
import static org.jhotdraw8.draw.figure.FigureImplementationDetails.IDENTITY_TRANSFORM;

/**
 * A transformable figure supports the transformation of a figure.
 * <p>
 * The following transformations are supported:
 * <ul>
 * <li>Translation ofCollection the local bounds ofCollection the figure.</li>
 * <li>Rotation around the center ofCollection the untransformed local bounds
 * ofCollection the figure.</li>
 * <li>Scaling around the center ofCollection the untransformed local bounds
 * ofCollection the figure.</li>
 * <li>Arbitrary sequence ofCollection affine transformations ofCollection the
 * figure.</li>
 * </ul>
 * Note that transformation matrices computed from the Rotation and Scaling must
 * be recomputed every time when the local bounds ofCollection the figure
 * change.
 *
 * @author Werner Randelshofer
 * @design.pattern Figure Mixin, Traits.
 */
public interface TransformableFigure extends TransformCacheableFigure {

    /**
     * Defines the angle of rotation around the center of the figure in degrees.
     * <p>
     * Default value: {@code 0}.
     */
    DoubleStyleableKey ROTATE = new DoubleStyleableKey("rotate", 0.0);
    /**
     * Defines the rotation axis used.
     * <p>
     * Default value: {@code Rotate.Z_AXIS}.
     */
    ObjectFigureKey<Point3D> ROTATION_AXIS = new ObjectFigureKey<>("rotationAxis", Point3D.class, Rotate.Z_AXIS);
    /**
     * Defines the scale factor by which coordinates are scaled on the x axis
     * about the center of the figure. Default value: {@code 1}.
     */
    DoubleStyleableKey SCALE_X = new DoubleStyleableKey("scaleX", 1.0);
    /**
     * Defines the scale factor by which coordinates are scaled on the y axis
     * about the center of the figure. Default value: {@code 1}.
     */
    DoubleStyleableKey SCALE_Y = new DoubleStyleableKey("scaleY", 1.0);
    /**
     * Defines the scale factor by which coordinates are scaled on the z axis
     * about the center of the figure. Default value: {@code 1}.
     */
    DoubleStyleableKey SCALE_Z = new DoubleStyleableKey("scaleZ", 1.0);
    /**
     * Defines the scale factor by which coordinates are scaled on the axes
     * about the center ofCollection the figure.
     */
    Scale3DStyleableMapAccessor SCALE = new Scale3DStyleableMapAccessor("scale", SCALE_X, SCALE_Y, SCALE_Z);
    TransformListStyleableKey TRANSFORMS = new TransformListStyleableKey("transform", ImmutableLists.emptyList());
    /**
     * Defines the translation on the x axis about the center of the figure.
     * Default value: {@code 0}.
     */
    DoubleStyleableKey TRANSLATE_X = new DoubleStyleableKey("translateX", 0.0);
    /**
     * Defines the translation on the y axis about the center of the figure.
     * Default value: {@code 0}.
     */
    DoubleStyleableKey TRANSLATE_Y = new DoubleStyleableKey("translateY", 0.0);
    /**
     * Defines the translation on the z axis about the center of the figure.
     * Default value: {@code 0}.
     */
    DoubleStyleableKey TRANSLATE_Z = new DoubleStyleableKey("translateZ", 0.0);
    /**
     * Defines the translation on the axes about the center ofCollection the
     * figure.
     */
    Point3DStyleableMapAccessor TRANSLATE = new Point3DStyleableMapAccessor("translate", TRANSLATE_X, TRANSLATE_Y, TRANSLATE_Z, new CssTranslate3DConverterOLD(false));

    /**
     * Updates a figure node with all transformation properties defined in this
     * interface.
     * <p>
     * Applies the following properties: {@code TRANSFORM}, translation
     * {@code TRANSLATE_X}, {@code TRANSLATE_Y}, {@code TRANSLATE_Z}, scale
     * {@code SCALE_X}, {@code SCALE_Y}, {@code SCALE_Z}, and rotation
     * {@code ROTATE}, {@code ROTATION_AXIS}.
     * <p>
     * This method is intended to be used by {@link #updateNode}.
     *
     * @param ctx  the render context
     * @param node a node which was created with method {@link #createNode}.
     */
    default void applyTransformableFigureProperties(@NonNull RenderContext ctx, @NonNull Node node) {
        Transform t = getLocalToParent();
        List<Transform> transforms = node.getTransforms();
        if (t == null || t.isIdentity()) {
            if (!transforms.isEmpty()) {
                transforms.clear();
            }
        } else if (transforms.size() == 1) {
            if (!Objects.equals(transforms.get(0), t)) {
                transforms.set(0, t);
            }
        } else {
            transforms.clear();
            transforms.add(t);
        }
    }

    default void clearTransforms() {
        remove(SCALE_X);
        remove(SCALE_Y);
        remove(ROTATE);
        remove(TRANSLATE_X);
        remove(TRANSLATE_Y);
        remove(TRANSFORMS);
    }

    default void flattenTransforms() {
        Transform p2l = getLocalToParent(false);
        remove(SCALE_X);
        remove(SCALE_Y);
        remove(ROTATE);
        remove(TRANSLATE_X);
        remove(TRANSLATE_Y);
        if (p2l == null || p2l.isIdentity()) {
            remove(TRANSFORMS);
        } else {
            set(TRANSFORMS, ImmutableLists.of(p2l));
        }
    }

    @Nullable
    default Transform getInverseTransform() {
        ImmutableList<Transform> list = getStyledNonNull(TRANSFORMS);
        Transform t;
        if (list.isEmpty()) {
            t = null; // leave null
        } else {
            try {
                t = list.get(list.size() - 1).createInverse();
                for (int i = list.size() - 2; i >= 0; i--) {
                    t = Transforms.concat(t, list.get(i).createInverse());
                }
            } catch (NonInvertibleTransformException e) {
                throw new InternalError(e);
            }
        }
        return t;
    }

    @Nullable
    @Override
    default Transform getLocalToParent() {
        return getLocalToParent(true);
    }

    @Nullable
    default Transform getLocalToParent(boolean styled) {
        Transform l2p = CACHE && styled ? getCachedValue(FigureImplementationDetails.LOCAL_TO_PARENT) : null;
        if (l2p == null) {
            Point2D center = getCenterInLocal();

            ImmutableList<Transform> t = styled ? getStyled(TRANSFORMS) : get(TRANSFORMS);
            double sx = styled ? getStyledNonNull(SCALE_X) : getNonNull(SCALE_X);
            double sy = styled ? getStyledNonNull(SCALE_Y) : getNonNull(SCALE_Y);
            double r = styled ? getStyledNonNull(ROTATE) : getNonNull(ROTATE);
            double tx = styled ? getStyledNonNull(TRANSLATE_X) : getNonNull(TRANSLATE_X);
            double ty = styled ? getStyledNonNull(TRANSLATE_Y) : getNonNull(TRANSLATE_Y);

            if (tx != 0.0 || ty != 0.0) {
                Translate tt = new Translate(tx, ty);
                l2p = Transforms.concat(l2p, tt);
            }
            if (r != 0) {
                Rotate tr = new PreciseRotate(r, center.getX(), center.getY());
                l2p = Transforms.concat(l2p, tr);
            }
            if ((sx != 1.0 || sy != 1.0) && sx != 0.0 && sy != 0.0) {// check for 0.0 avoids creating a non-invertible transform
                Scale ts = new Scale(sx, sy, center.getX(), center.getY());
                l2p = Transforms.concat(l2p, ts);
            }
            if (t != null && !t.isEmpty()) {
                l2p = Transforms.concat(l2p, getTransform());
            }
            if (l2p == null) {
                l2p = IDENTITY_TRANSFORM;
            }
            if (CACHE && styled) {
                setCachedValue(FigureImplementationDetails.LOCAL_TO_PARENT, l2p);
            }
        }
        return l2p;
    }

    @NonNull
    default List<Transform> getLocalToParentAsList(boolean styled) {
        ArrayList<Transform> list = new ArrayList<>();

        Point2D center = getCenterInLocal();

        ImmutableList<Transform> t = styled ? getStyledNonNull(TRANSFORMS) : getNonNull(TRANSFORMS);
        double sx = styled ? getStyledNonNull(SCALE_X) : getNonNull(SCALE_X);
        double sy = styled ? getStyledNonNull(SCALE_Y) : getNonNull(SCALE_Y);
        double r = styled ? getStyledNonNull(ROTATE) : getNonNull(ROTATE);
        double tx = styled ? getStyledNonNull(TRANSLATE_X) : getNonNull(TRANSLATE_X);
        double ty = styled ? getStyledNonNull(TRANSLATE_Y) : getNonNull(TRANSLATE_Y);

        if (tx != 0.0 || ty != 0.0) {
            Translate tt = new Translate(tx, ty);
            list.add(tt);
        }
        if (r != 0) {
            Rotate tr = new Rotate(r, center.getX(), center.getY());
            list.add(tr);
        }
        if ((sx != 1.0 || sy != 1.0) && sx != 0.0 && sy != 0.0) {// check for 0.0 avoids creating a non-invertible transform
            Scale ts = new Scale(sx, sy, center.getX(), center.getY());
            list.add(ts);
        }
        if (!t.isEmpty()) {
            list.addAll(t.asList());
        }
        return list;
    }

    /**
     * Returns null if identity.
     */
    @Nullable
    default Transform getParentToLocal() {
        return getParentToLocal(true);
    }

    /**
     * Returns null if identity.
     *
     * @param styled whether the styled value should be used
     * @return the transform or null
     */
    @Nullable
    default Transform getParentToLocal(boolean styled) {
        Transform p2l = CACHE ? getCachedValue(FigureImplementationDetails.PARENT_TO_LOCAL) : null;
        if (p2l == null) {
            Point2D center = getCenterInLocal();

            ImmutableList<Transform> t = styled ? getStyled(TRANSFORMS) : get(TRANSFORMS);
            double sx = styled ? getStyledNonNull(SCALE_X) : getNonNull(SCALE_X);
            double sy = styled ? getStyledNonNull(SCALE_Y) : getNonNull(SCALE_Y);
            double r = styled ? getStyledNonNull(ROTATE) : getNonNull(ROTATE);
            double tx = styled ? getStyledNonNull(TRANSLATE_X) : getNonNull(TRANSLATE_X);
            double ty = styled ? getStyledNonNull(TRANSLATE_Y) : getNonNull(TRANSLATE_Y);

            if (t != null && !t.isEmpty()) {
                p2l = getInverseTransform();
            }
            if ((sx != 1.0 || sy != 1.0) && sx != 0.0 && sy != 0.0) {// check for 0.0 avoids creating a non-invertible transform
                Scale ts = new Scale(1.0 / sx, 1.0 / sy, center.getX(), center.getY());
                p2l = Transforms.concat(p2l, ts);
            }
            if (r != 0) {
                Rotate tr = new PreciseRotate(-r, center.getX(), center.getY());
                p2l = Transforms.concat(p2l, tr);
            }
            if (tx != 0.0 || ty != 0.0) {
                Translate tt = new Translate(-tx, -ty);
                p2l = Transforms.concat(p2l, tt);
            }
            if (p2l == null) {
                // KEEP IT NULL - muahaha
                //p2l = IDENTITY_TRANSFORM;
            }
            if (CACHE) {
                setCachedValue(FigureImplementationDetails.PARENT_TO_LOCAL, p2l);
            }
        }
        return p2l;
    }

    @Nullable
    default Transform getTransform() {
        ImmutableList<Transform> list = getStyledNonNull(TRANSFORMS);
        Transform t;
        if (list.isEmpty()) {
            t = null; // leave empty
        } else {
            t = list.get(0);
            for (int i = 1, n = list.size(); i < n; i++) {
                t = Transforms.concat(t, list.get(i));
            }
        }
        return t;
    }

    /**
     * Convenience method for setting a new value for the {@link #TRANSFORMS}
     * property.
     *
     * @param transforms new value
     */
    default void setTransforms(@NonNull Transform... transforms) {
        if (transforms.length == 1 && transforms[0].isIdentity()) {
            set(TRANSFORMS, ImmutableLists.emptyList());
        } else {
            set(TRANSFORMS, ImmutableLists.of(transforms));
        }
    }

    default boolean hasCenterTransforms() {
        double sx = getStyledNonNull(SCALE_X);
        double sy = getStyledNonNull(SCALE_Y);
        double r = getStyledNonNull(ROTATE);
        double tx = getStyledNonNull(TRANSLATE_X);
        double ty = getStyledNonNull(TRANSLATE_Y);
        return sx != 1 || sy != 1 || r != 0 || tx != 0 || ty != 0;
    }

    default boolean hasTransforms() {
        return !getNonNull(TRANSFORMS).isEmpty();
    }

    @Override
    default boolean invalidateTransforms() {
        if (!CACHE) {
            return false;
        }
        // intentional use ofCollection long-circuit or-expressions!!
        return TransformCacheableFigure.super.invalidateTransforms()
                | null != setCachedValue(FigureImplementationDetails.PARENT_TO_LOCAL, null)
                | null != setCachedValue(FigureImplementationDetails.LOCAL_TO_PARENT, null);
    }

    @Override
    default void reshapeInLocal(Transform transform) {
        if (hasCenterTransforms() && !(transform instanceof Translate)) {
            ImmutableList<Transform> ts = getNonNull(TRANSFORMS);
            if (ts.isEmpty()) {
                set(TRANSFORMS, ImmutableLists.of(transform));
            } else {
                int last = ts.size() - 1;
                Transform concatenatedWithLast = Transforms.concat(ts.get(last), transform);
                if (concatenatedWithLast instanceof Affine) {
                    set(TRANSFORMS, ImmutableLists.add(ts, transform));
                } else {
                    set(TRANSFORMS, ImmutableLists.set(ts, last, concatenatedWithLast));
                }
            }
            return;
        }

        Bounds b = getLayoutBounds();
        b = transform.transform(b);
        reshapeInLocal(b.getMinX(), b.getMinY(), b.getWidth(), b.getHeight());
    }

    @Override
    default void reshapeInParent(Transform transform) {
        final boolean hasCenters = hasCenterTransforms();
        final boolean hasTransforms = hasTransforms();
        if (!hasTransforms && (transform instanceof Translate)) {
            reshapeInLocal(transform);
            return;
        }
        Transform parentToLocal = getParentToLocal();
        if (hasCenters || hasTransforms()) {
            if (transform instanceof Translate) {
                Translate translate = (Translate) transform;
                if (!hasCenters) {
                    Point2D p = parentToLocal == null ? new Point2D(translate.getTx(), translate.getTy())
                            : parentToLocal.deltaTransform(translate.getTx(), translate.getTy());
                    reshapeInLocal(new Translate(p.getX(), p.getY()));
                } else {
                    set(TRANSLATE_X, getNonNull(TRANSLATE_X) + translate.getTx());
                    set(TRANSLATE_Y, getNonNull(TRANSLATE_Y) + translate.getTy());
                }
            } else {
                flattenTransforms();
                ImmutableList<Transform> transforms = getNonNull(TRANSFORMS);
                if (transforms.isEmpty()) {
                    set(TRANSFORMS, ImmutableLists.of(transform));
                } else {
                    set(TRANSFORMS, ImmutableLists.add(transforms, 0, transform));
                }
            }
        } else {
            reshapeInLocal(Transforms.concat(parentToLocal, transform));
        }
    }

    @Override
    default void transformInLocal(Transform t) {
        flattenTransforms();
        ImmutableList<Transform> transforms = getNonNull(TRANSFORMS);
        if (transforms.isEmpty()) {
            set(TRANSFORMS, ImmutableLists.of(t));
        } else {
            set(TRANSFORMS, ImmutableLists.add(transforms, t));
        }
    }

    @Override
    default void transformInParent(@Nullable Transform t) {
        if (t == null || t.isIdentity()) {
            return;
        }
        if (t instanceof Translate) {
            Translate tr = (Translate) t;
            flattenTransforms();
            ImmutableList<Transform> transforms = getNonNull(TRANSFORMS);
            if (transforms.isEmpty()) {
                translateInLocal(new CssPoint2D(tr.getTx(), tr.getTy()));
            } else {
                set(TRANSLATE_X, getNonNull(TRANSLATE_X) + tr.getTx());
                set(TRANSLATE_Y, getNonNull(TRANSLATE_Y) + tr.getTy());
            }
        } else {
            flattenTransforms();
            ImmutableList<Transform> transforms = getNonNull(TRANSFORMS);
            if (transforms.isEmpty()) {
                set(TRANSFORMS, ImmutableLists.of(t));
            } else {
                set(TRANSFORMS, ImmutableLists.add(transforms, 0, t));
            }
        }
    }

    @NonNull
    static Set<Key<?>> getDeclaredKeys() {
        Set<Key<?>> keys = new LinkedHashSet<>();
        Figure.getDeclaredKeys(TransformableFigure.class, keys);
        return keys;
    }
}
