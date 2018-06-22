/* @(#)CustomSkin.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.gui;

import javafx.scene.control.Control;
import javafx.scene.control.SkinBase;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * A custom skin without behavior.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class CustomSkin<C extends Control> extends SkinBase<C> {

    public CustomSkin(@NonNull C control) {
        super(control);
    }

}
