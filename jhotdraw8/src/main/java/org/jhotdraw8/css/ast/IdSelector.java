/* @(#)IdSelector.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.css.ast;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jhotdraw8.css.SelectorModel;

/**
 * An "id selector" matches an element if the element has an id with the
 * specified value.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class IdSelector extends SimpleSelector {

    private final String id;

    public IdSelector(String id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return "Id:" + id;
    }

    @Nullable
    @Override
    public <T> T match(@NonNull SelectorModel<T> model, @Nullable T element) {
        return (element != null && model.hasId(element, id)) //
                ? element : null;
    }

    @Override
    public int getSpecificity() {
        return 100;
    }

}
