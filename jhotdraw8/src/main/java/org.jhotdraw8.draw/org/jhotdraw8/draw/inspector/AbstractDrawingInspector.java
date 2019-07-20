/* @(#)AbstractDrawingInspector.java
 * Copyright © The authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.draw.inspector;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.jhotdraw8.annotation.Nonnull;
import org.jhotdraw8.annotation.Nullable;
import org.jhotdraw8.draw.DrawingView;
import org.jhotdraw8.draw.figure.Drawing;
import org.jhotdraw8.draw.model.DrawingModel;

/**
 * AbstractDrawingInspector.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public abstract class AbstractDrawingInspector implements Inspector<DrawingView> {

    @Nonnull
    protected final ObjectProperty<DrawingView> subject = new SimpleObjectProperty<>();
    @Nullable
    protected DrawingModel drawingModel;
    @Nullable
    protected Drawing drawing;

    private final ChangeListener<Drawing> drawingListener = this::handleDrawingChanged;
    private final ChangeListener<DrawingModel> modelListener = this::handleDrawingModelChanged;

    {
        subject.addListener(this::handleDrawingViewChanged);
    }

    public ObjectProperty<DrawingView> subjectProperty() {
        return subject;
    }


    protected void handleDrawingViewChanged(ObservableValue<? extends DrawingView> observable, @Nullable DrawingView oldValue, @Nullable DrawingView newValue) {
        Drawing oldDrawing = drawing;
        DrawingModel oldModel = drawingModel;
        if (oldValue != null) {
            oldValue.modelProperty().removeListener(modelListener);
            oldValue.drawingProperty().removeListener(drawingListener);
            oldDrawing = oldValue.getDrawing();
        }
        Drawing newDrawing = null;
        if (newValue != null) {
            newValue.drawingProperty().addListener(drawingListener);
            newValue.modelProperty().addListener(modelListener);
            newDrawing = newValue.getDrawing();
            drawingModel = newValue.getModel();
        }
        handleDrawingModelChanged(null, oldModel, drawingModel);
        handleDrawingChanged(null, oldDrawing, newDrawing);
    }

    protected DrawingModel getDrawingModel() {
        return getSubject().getModel();
    }

    protected Drawing getDrawing() {
        return getSubject().getDrawing();
    }

    protected DrawingModel getModel() {
        return getSubject().getModel();
    }


    /**
     * Must be implemented by subclasses.
     *
     * @param observable
     * @param oldValue the old drawing
     * @param newValue the new drawing
     */
    protected abstract void handleDrawingChanged(ObservableValue<? extends Drawing> observable, Drawing oldValue, Drawing newValue);

    /**
     * Can be overriden by subclasses.
     * This implementation is empty.
     *
     * @param observable
     * @param oldValue the old drawing model
     * @param newValue the new drawing model
     */
    protected void handleDrawingModelChanged(ObservableValue<? extends DrawingModel> observable, DrawingModel oldValue, DrawingModel newValue) {

    }
}
