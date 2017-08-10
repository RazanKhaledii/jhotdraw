/* @(#)TypeSelector.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.css.ast;

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

    @Override
    public String toString() {
        return "Type:" + type;
    }

    @Override
    public <T> T match(SelectorModel<T> model, T element) {
        return (element != null && model.hasType(element, type)) //
                ? element : null;
    }

    @Override
    public int getSpecificity() {
        return 1;
    }
}
