/* @(#)ResizePane.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.gui.dock;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * ResizePane.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class ResizePane extends BorderPane {

    private final BooleanProperty userResizable = new SimpleBooleanProperty(true);

    public ResizePane() {
        ResizeButton rb = new ResizeButton();
        rb.setTarget(this);
        setBottom(rb);
        rb.visibleProperty().bind(userResizable);
    }

    public boolean isUserResizable() {
        return userResizable.get();
    }

    public void setUserResizable(boolean value) {
        userResizable.set(value);
  }

    @NonNull
    public BooleanProperty userResizableProperty() {
        return userResizable;
    }

    public void setContent(Node n) {
        setCenter(n);
    }

    public Node getContent() {
        return getCenter();
    }

}
