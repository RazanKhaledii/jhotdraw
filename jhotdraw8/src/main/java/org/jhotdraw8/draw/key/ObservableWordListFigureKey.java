/* @(#)ObservableWordListStyleableFigureKey.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.draw.key;

import org.jhotdraw8.collection.ImmutableObservableList;

/**
 * ObservableWordListStyleableFigureKey.
 *
 * @author Werner Randelshofer
 */
public class ObservableWordListFigureKey extends SimpleFigureKey<ImmutableObservableList<String>> {

    private final static long serialVersionUID = 1L;

    /**
     * Creates a new instance with the specified name and with null as the
     * default value.
     *
     * @param name The name of the key.
     */
    public ObservableWordListFigureKey(String name) {
        this(name, null);
    }

    /**
     * Creates a new instance with the specified name and default value.
     *
     * @param name The name of the key.
     * @param defaultValue The default value.
     */
    public ObservableWordListFigureKey(String name, ImmutableObservableList<String> defaultValue) {
        this(name, DirtyMask.of(DirtyBits.NODE), defaultValue);
    }

    /**
     * Creates a new instance with the specified name, mask and default value.
     *
     * @param name The name of the key.
     * @param mask The dirty mask.
     * @param defaultValue The default value.
     */
    public ObservableWordListFigureKey(String name, DirtyMask mask, ImmutableObservableList<String> defaultValue) {
        super(name, ImmutableObservableList.class, new Class<?>[]{String.class}, mask, defaultValue);
    }
}
