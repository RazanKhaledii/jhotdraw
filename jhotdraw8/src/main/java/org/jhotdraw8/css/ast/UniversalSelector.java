/* @(#)UniversalSelector.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.css.ast;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.jhotdraw8.css.SelectorModel;

/**
 * A "universal selector" matches an element if the element exists.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class UniversalSelector extends SimpleSelector {

    @NonNull
    @Override
    public String toString() {
        return "Universal:*";
    }

    @Override
    public <T> T match(SelectorModel<T> model, T element) {
        return element;
    }

    @Override
    public int getSpecificity() {
        return 0;
    }
}
