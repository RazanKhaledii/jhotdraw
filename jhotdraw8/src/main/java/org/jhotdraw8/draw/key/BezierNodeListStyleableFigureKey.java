/* @(#)BezierNodeListStyleableFigureKey.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.draw.key;

import java.util.function.Function;
import javafx.css.CssMetaData;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;

import org.jhotdraw8.collection.ImmutableObservableList;
import org.jhotdraw8.draw.figure.Figure;
import org.jhotdraw8.geom.BezierNode;
import org.jhotdraw8.styleable.StyleablePropertyBean;
import org.jhotdraw8.text.Converter;
import org.jhotdraw8.text.StyleConverterAdapter;
import org.jhotdraw8.text.CssBezierNodeListConverter;
import org.jhotdraw8.styleable.WriteableStyleableMapAccessor;

/**
 * BezierNodeListStyleableFigureKey.
 *
 * @author Werner Randelshofer
 */
public class BezierNodeListStyleableFigureKey extends SimpleFigureKey<ImmutableObservableList<BezierNode>> implements WriteableStyleableMapAccessor<ImmutableObservableList<BezierNode>> {

    private final static long serialVersionUID = 1L;

    private final CssMetaData<?, ImmutableObservableList<BezierNode>> cssMetaData;

    /**
     * Creates a new instance with the specified name and with null as the
     * default value.
     *
     * @param name The name of the key.
     */
    public BezierNodeListStyleableFigureKey(String name) {
        this(name, null);
    }

    /**
     * Creates a new instance with the specified name and default value.
     *
     * @param name The name of the key.
     * @param defaultValue The default value.
     */
    public BezierNodeListStyleableFigureKey(String name, ImmutableObservableList<BezierNode> defaultValue) {
        this(name, DirtyMask.of(DirtyBits.NODE), defaultValue);
    }

    /**
     * Creates a new instance with the specified name, mask and default value.
     *
     * @param name The name of the key.
     * @param mask The dirty mask.
     * @param defaultValue The default value.
     */
    public BezierNodeListStyleableFigureKey(String name, DirtyMask mask, ImmutableObservableList<BezierNode> defaultValue) {
        super(name, ImmutableObservableList.class, new Class<?>[]{BezierNode.class}, mask, defaultValue);

        Function<Styleable, StyleableProperty<ImmutableObservableList<BezierNode>>> function = s -> {
            StyleablePropertyBean spb = (StyleablePropertyBean) s;
            return spb.getStyleableProperty(this);
        };
        boolean inherits = false;
        String property = Figure.JHOTDRAW_CSS_PREFIX + getCssName();
        final StyleConverter<String, ImmutableObservableList<BezierNode>> converter
                = new StyleConverterAdapter<ImmutableObservableList<BezierNode>>(getConverter());
        CssMetaData<Styleable, ImmutableObservableList<BezierNode>> md
                = new SimpleCssMetaData<Styleable, ImmutableObservableList<BezierNode>>(property, function,
                        converter, defaultValue, inherits);
        cssMetaData = md;
    }

    @Override
    public CssMetaData<?, ImmutableObservableList<BezierNode>> getCssMetaData() {
        return cssMetaData;
    }

    private Converter<ImmutableObservableList<BezierNode>> converter;

    @Override
    public Converter<ImmutableObservableList<BezierNode>> getConverter() {
        if (converter == null) {
            converter = new CssBezierNodeListConverter(true);
        }
        return converter;
    }

}
