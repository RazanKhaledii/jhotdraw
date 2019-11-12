/*
 * @(#)UriStyleableKey.java
 * Copyright © The authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.draw.key;

import javafx.css.CssMetaData;
import javafx.css.StyleConverter;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import org.jhotdraw8.annotation.NonNull;
import org.jhotdraw8.css.text.CssUriConverter;
import org.jhotdraw8.draw.figure.Figure;
import org.jhotdraw8.styleable.StyleablePropertyBean;
import org.jhotdraw8.styleable.WriteableStyleableMapAccessor;
import org.jhotdraw8.text.Converter;
import org.jhotdraw8.text.StyleConverterAdapter;

import java.net.URI;
import java.util.function.Function;

/**
 * URIStyleableFigureKey.
 *
 * @author Werner Randelshofer
 */
public class UriStyleableKey extends AbstractStyleableKey<URI> implements WriteableStyleableMapAccessor<URI> {

    private final static long serialVersionUID = 1L;

    @NonNull
    private final CssMetaData<?, URI> cssMetaData;

    /**
     * Creates a new instance with the specified name and with null as the
     * default value.
     *
     * @param name The name of the key.
     */
    public UriStyleableKey(String name) {
        this(name, null);
    }

    /**
     * Creates a new instance with the specified name, type token class, default
     * value, and allowing or disallowing null values.
     *  @param key          The name of the name. type parameters are given. Otherwise
     *                     specify them in arrow brackets.
     * @param defaultValue The default value.
     */
    public UriStyleableKey(String key, URI defaultValue) {
        super(key, URI.class, defaultValue);
        /*
         StyleablePropertyFactory factory = new StyleablePropertyFactory(null);
         cssMetaData = factory.createPoint2DCssMetaData(
         Figure.JHOTDRAW_CSS_PREFIX + getName(), s -> {
         StyleablePropertyBean spb = (StyleablePropertyBean) s;
         return spb.getStyleableProperty(this);
         });*/

        Function<Styleable, StyleableProperty<URI>> function = s -> {
            StyleablePropertyBean spb = (StyleablePropertyBean) s;
            return spb.getStyleableProperty(this);
        };
        boolean inherits = false;
        String property = Figure.JHOTDRAW_CSS_PREFIX + getCssName();
        final StyleConverter<String, URI> cnvrtr
                = new StyleConverterAdapter<>(getConverter());
        CssMetaData<Styleable, URI> md
                = new SimpleCssMetaData<>(property, function,
                cnvrtr, defaultValue, inherits);
        cssMetaData = md;
    }

    @NonNull
    @Override
    public CssMetaData<?, URI> getCssMetaData() {
        return cssMetaData;

    }

    private Converter<URI> converter;

    @Override
    public Converter<URI> getConverter() {
        if (converter == null) {
            converter = new CssUriConverter();
        }
        return converter;
    }
}
