/* @(#)EqualsMatchSelector.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.css.ast;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jhotdraw8.css.SelectorModel;

/**
 * An "attribute value selector" matches an element if the element has an
 * attribute with the specified name and value.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class EqualsMatchSelector extends AbstractAttributeSelector {

    private final String attributeName;
    private final String attributeValue;

    public EqualsMatchSelector(String attributeName, String attributeValue) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    @Nullable
    @Override
    protected <T> T match(@NonNull SelectorModel<T> model, T element) {
        return model.attributeValueEquals(element, attributeName, attributeValue) ? element : null;
    }

    @NonNull
    @Override
    public String toString() {
        return "[" + attributeName + "=" + attributeValue + ']';
    }
}
