/* @(#)Rectangle2DStyleableMapAccessor.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.draw.key;

import static java.lang.Double.max;
import java.util.Map;
import java.util.function.Function;
import javafx.css.CssMetaData;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import javafx.geometry.Rectangle2D;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jhotdraw8.collection.Key;
import org.jhotdraw8.collection.MapAccessor;
import org.jhotdraw8.styleable.StyleablePropertyBean;
import org.jhotdraw8.draw.figure.Figure;
import org.jhotdraw8.text.Converter;
import org.jhotdraw8.text.CssRectangle2DConverter;
import org.jhotdraw8.text.StyleConverterAdapter;

/**
 * Rectangle2DStyleableMapAccessor.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class Rectangle2DStyleableMapAccessor extends AbstractStyleableFigureMapAccessor<Rectangle2D> {

    private final static long serialVersionUID = 1L;

    @NonNull
    private final CssMetaData<?, Rectangle2D> cssMetaData;
    @NonNull
    private final MapAccessor<Double> xKey;
    @NonNull
    private final MapAccessor<Double> yKey;
    @NonNull
    private final MapAccessor<Double> widthKey;
    @NonNull
    private final MapAccessor<Double> heightKey;

    /**
     * Creates a new instance with the specified name.
     *
     * @param name the name of the accessor
     * @param xKey the key for the x coordinate of the rectangle
     * @param yKey the key for the y coordinate of the rectangle
     * @param widthKey the key for the width of the rectangle
     * @param heightKey the key for the height of the rectangle
     */
    public Rectangle2DStyleableMapAccessor(String name, MapAccessor<Double> xKey, MapAccessor<Double> yKey, MapAccessor<Double> widthKey, MapAccessor<Double> heightKey) {
        super(name, Rectangle2D.class, new MapAccessor<?>[]{xKey, yKey, widthKey, heightKey}, new Rectangle2D(xKey.getDefaultValue(), yKey.getDefaultValue(), widthKey.getDefaultValue(), heightKey.getDefaultValue()));

        Function<Styleable, StyleableProperty<Rectangle2D>> function = s -> {
            StyleablePropertyBean spb = (StyleablePropertyBean) s;
            return spb.getStyleableProperty(this);
        };
        boolean inherits = false;
        String property = Figure.JHOTDRAW_CSS_PREFIX + getCssName();
        final StyleConverter<String, Rectangle2D> cnvrtr
                = new StyleConverterAdapter<>(getConverter());
        CssMetaData<Styleable, Rectangle2D> md
                = new SimpleCssMetaData<>(property, function,
                        cnvrtr, getDefaultValue(), inherits);
        cssMetaData = md;

        this.xKey = xKey;
        this.yKey = yKey;
        this.widthKey = widthKey;
        this.heightKey = heightKey;
    }

    @NonNull
    @Override
    public CssMetaData<?, Rectangle2D> getCssMetaData() {
        return cssMetaData;

    }

    private Converter<Rectangle2D> converter;

    @Override
    public Converter<Rectangle2D> getConverter() {
        if (converter == null) {
            converter = new CssRectangle2DConverter();
        }
        return converter;
    }

    @NonNull
    @Override
    public Rectangle2D get(Map<? super Key<?>, Object> a) {
        return new Rectangle2D(xKey.get(a), yKey.get(a), max(0.0,widthKey.get(a)), max(0.0,heightKey.get(a)));
    }

    @NonNull
    @Override
    public Rectangle2D put(Map<? super Key<?>, Object> a, @NonNull Rectangle2D value) {
        Rectangle2D oldValue = get(a);
        xKey.put(a, value.getMinX());
        yKey.put(a, value.getMinY());
        widthKey.put(a, value.getWidth());
        heightKey.put(a, value.getHeight());
        return oldValue;
    }

    @NonNull
    @Override
    public Rectangle2D remove(Map<? super Key<?>, Object> a) {
        Rectangle2D oldValue = get(a);
        xKey.remove(a);
        yKey.remove(a);
        widthKey.remove(a);
        heightKey.remove(a);
        return oldValue;
    }

    @Override
    public boolean isNullable() {
        return false;
    }
}
