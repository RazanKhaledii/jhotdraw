/* @(#)NullConstrainer.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.draw.constrain;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.shape.Path;
import org.jhotdraw8.draw.DrawingView;
import org.jhotdraw8.draw.figure.Figure;

/**
 * NullConstrainer does not constrain anything.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class NullConstrainer extends AbstractConstrainer {

    private final Path node = new Path();

    @Override
    public Point2D translatePoint(Figure f, Point2D p, Point2D dir) {
        return p.add(dir);
    }

    @Override
    public Rectangle2D translateRectangle(Figure f, Rectangle2D r, Point2D dir) {
        return new Rectangle2D(r.getMinX() + dir.getX(), r.getMinY() + dir.getY(), r.getWidth(), r.getHeight());
    }

    @Override
    public double translateAngle(Figure f, double angle, double dir) {
        return angle + dir;
    }

    @Override
    public Node getNode() {
        return node;
    }

    @Override
    public void updateNode(DrawingView drawingView) {
        // empty
    }

}
