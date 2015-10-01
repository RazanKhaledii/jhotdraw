/* @(#)NoLayoutNoConnectionsDrawingModel.java
 * Copyright (c) 2015 by the authors and contributors of JHotDraw.
 * You may only use this file in compliance with the accompanying license terms.
 */
package org.jhotdraw.draw.model;

import javafx.scene.transform.Transform;
import org.jhotdraw.collection.Key;
import org.jhotdraw.draw.key.DirtyBits;
import org.jhotdraw.draw.key.DirtyMask;
import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.draw.key.FigureKey;
import org.jhotdraw.draw.key.SimpleFigureKey;

/**
 * This drawing model assumes that the drawing contains no figures which perform
 layouts and no getConnectedFigures between figures.
 *
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class NoLayoutNoConnectionsDrawingModel extends AbstractDrawingModel {

    @Override
    public void setRoot(Drawing root) {
        this.root = root;
        fire(DrawingModelEvent.rootChanged(this, root));
    }

    @Override
    public void removeFromParent(Figure child) {
        Figure parent = child.getParent();
        if (parent != null) {
            int index = parent.getChildren().indexOf(child);
            if (index != -1) {
                parent.getChildren().remove(index);
                fire(DrawingModelEvent.figureRemoved(this, parent, child, index));
                fire(DrawingModelEvent.nodeInvalidated(this, parent));
            }
        }
    }

    @Override
    public void insertChildAt(Figure child, Figure parent, int index) {
        parent.getChildren().add(index, child);
        fire(DrawingModelEvent.figureAdded(this, parent, child, index));
        fire(DrawingModelEvent.nodeInvalidated(this, parent));
    }

    @Override
    public <T> T set(Figure figure, Key<T> key, T value) {
        T oldValue = figure.set(key, value);
        if (key instanceof FigureKey) {
            FigureKey<T> fk = (FigureKey<T>) key;
            DirtyMask dm = fk.getDirtyMask();
            if (dm.containsOneOf(DirtyBits.NODE)) {
                fire(DrawingModelEvent.nodeInvalidated(this, figure));
            }
        }
        return oldValue;
    }

    @Override
    public void reshape(Figure f, Transform transform) {
        f.reshape(transform);
        fire(DrawingModelEvent.subtreeNodesInvalidated(this, f));
    }

    @Override
    public void reshape(Figure f, double x, double y, double width, double height) {
        f.reshape(x, y, width, height);
        fire(DrawingModelEvent.subtreeNodesInvalidated(this, f));
    }

    @Override
    public void layout(Figure f) {
        f.layout();
        // no event fired! fire(DrawingModelEvent.subtreeNodesInvalidated(this,f));
    }

    @Override
    public void applyCss(Figure figure) {
        figure.applyCss();
        fire(DrawingModelEvent.subtreeNodesInvalidated(this, figure));
    }
}
