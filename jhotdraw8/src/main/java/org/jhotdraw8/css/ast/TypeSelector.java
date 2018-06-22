/* @(#)TypeSelector.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.css.ast;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jhotdraw8.css.SelectorModel;

/**
 * A "class selector" matches an element if the element has a type with the
 * specified value.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class TypeSelector extends SimpleSelector {

    private final String type;

    public TypeSelector(String type) {
        this.type = type;
    }

    @NonNull
    @Override
    public String toString() {
        return "Type:" + type;
    }

    @Nullable
    @Override
    public <T> T match(@NonNull SelectorModel<T> model, @Nullable T element) {
        return (element != null && model.hasType(element, type)) //
                ? element : null;
    }

    @Override
    public int getSpecificity() {
        return 1;
    }
}
