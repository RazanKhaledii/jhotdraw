/*
 * @(#)SVGPathFigure.java  1.0  July 8, 2006
 *
 * Copyright (c) 1996-2006 by the original authors of JHotDraw
 * and all its contributors ("JHotDraw.org")
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * JHotDraw.org ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * JHotDraw.org.
 */

package org.jhotdraw.samples.svg.figures;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.undo.*;
import org.jhotdraw.draw.*;
import org.jhotdraw.draw.action.*;
import org.jhotdraw.geom.*;
import org.jhotdraw.samples.svg.*;
import org.jhotdraw.samples.svg.SVGConstants;
import org.jhotdraw.undo.*;
import org.jhotdraw.util.*;
import org.jhotdraw.xml.*;
import static org.jhotdraw.samples.svg.SVGAttributeKeys.*;

/**
 * SVGPath is a composite Figure which contains one or more
 * SVGBezierFigures as its children.
 *
 * @author Werner Randelshofer
 * @version 1.0 July 8, 2006 Created.
 */
public class SVGPathFigure extends AbstractAttributedCompositeFigure implements SVGFigure {
    /**
     * This cachedPath is used for drawing.
     */
    private GeneralPath cachedPath;
    private Rectangle2D.Double cachedDrawingArea;
    
    private final static boolean DEBUG = false;
    
    /** Creates a new instance. */
    public SVGPathFigure() {
        add(new SVGBezierFigure());
        SVGAttributeKeys.setDefaults(this);
    }
    
    
    public void draw(Graphics2D g)  {
        double opacity = OPACITY.get(this);
        opacity = Math.min(Math.max(0d, opacity), 1d);
        if (opacity != 0d) {
            if (opacity != 1d) {
                Rectangle2D.Double drawingArea = getDrawingArea();
                
                Rectangle2D clipBounds = g.getClipBounds();
                if (clipBounds != null) {
                    Rectangle2D.intersect(drawingArea, clipBounds, drawingArea);
                }
                
                if (! drawingArea.isEmpty()) {
                    
                    BufferedImage buf = new BufferedImage(
                           Math.max(1, (int) ((2 + drawingArea.width) * g.getTransform().getScaleX())),
                           Math.max(1, (int) ((2 + drawingArea.height) * g.getTransform().getScaleY())),
                            BufferedImage.TYPE_INT_ARGB);
                    Graphics2D gr = buf.createGraphics();
                    gr.scale(g.getTransform().getScaleX(), g.getTransform().getScaleY());
                    gr.translate((int) -drawingArea.x, (int) -drawingArea.y);
                    gr.setRenderingHints(g.getRenderingHints());
                    drawFigure(gr);
                    gr.dispose();
                    Composite savedComposite = g.getComposite();
                    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) opacity));
                    g.drawImage(buf, (int) drawingArea.x, (int) drawingArea.y,
                            2 + (int) drawingArea.width, 2 + (int) drawingArea.height, null);
                    g.setComposite(savedComposite);
                }
            } else {
                drawFigure(g);
            }
        }
    }
    
    public void drawFigure(Graphics2D g) {
        AffineTransform savedTransform = null;
        if (TRANSFORM.get(this) != null) {
            savedTransform = g.getTransform();
            g.transform(TRANSFORM.get(this));
        }
        Paint paint = SVGAttributeKeys.getFillPaint(this);
        if (paint != null) {
            g.setPaint(paint);
            drawFill(g);
        }
        paint = SVGAttributeKeys.getStrokePaint(this);
        if (paint != null) {
            g.setPaint(paint);
            g.setStroke(SVGAttributeKeys.getStroke(this));
            drawStroke(g);
        }
        if (TRANSFORM.get(this) != null) {
            g.setTransform(savedTransform);
        }
    }
    
    public void drawFill(Graphics2D g) {
        g.fill(getPath());
    }
    public void drawStroke(Graphics2D g) {
        g.draw(getPath());
    }
    
    public void invalidate() {
        super.invalidate();
        cachedPath = null;
        cachedDrawingArea = null;
    }
    
    protected GeneralPath getPath() {
        if (cachedPath == null) {
            cachedPath = new GeneralPath();
            cachedPath.setWindingRule(WINDING_RULE.get(this) == WindingRule.EVEN_ODD ?
                GeneralPath.WIND_EVEN_ODD :
                GeneralPath.WIND_NON_ZERO
                    );
            for (Figure child : getChildren()) {
                SVGBezierFigure b = (SVGBezierFigure) child;
                cachedPath.append(b.getBezierPath(), false);
            }
        }
        return cachedPath;
    }
    
    public Rectangle2D.Double getDrawingArea() {
        if (cachedDrawingArea == null) {
            double strokeTotalWidth = AttributeKeys.getStrokeTotalWidth(this);
            double width = strokeTotalWidth / 2d;
            if (STROKE_JOIN.get(this) == BasicStroke.JOIN_MITER) {
                width *= STROKE_MITER_LIMIT.get(this);
            } else if (STROKE_CAP.get(this) != BasicStroke.CAP_BUTT) {
                width += strokeTotalWidth * 2;
            }
            GeneralPath gp = (GeneralPath) getPath();
            Rectangle2D strokeRect = new Rectangle2D.Double(0,0,width,width);
            if (TRANSFORM.get(this) != null) {
                gp = (GeneralPath) gp.clone();
                gp.transform(TRANSFORM.get(this));
                strokeRect = TRANSFORM.get(this).createTransformedShape(strokeRect).getBounds2D();
            }
            Rectangle2D rx = gp.getBounds2D();
            Rectangle2D.Double r = (rx instanceof Rectangle2D.Double) ?
                (Rectangle2D.Double) rx :
                new Rectangle2D.Double(rx.getX(), rx.getY(), rx.getWidth(), rx.getHeight());
            Geom.grow(r, strokeRect.getWidth(), strokeRect.getHeight());
            cachedDrawingArea = r;
        }
        return (Rectangle2D.Double) cachedDrawingArea.clone();
    }
    
    @Override final public void write(DOMOutput out) throws IOException {
        throw new UnsupportedOperationException("Use SVGStorableOutput to write this Figure.");
    }
    @Override final public void read(DOMInput in) throws IOException {
        throw new UnsupportedOperationException("Use SVGStorableInput to read this Figure.");
    }
    
    public boolean contains(Point2D.Double p) {
        getPath();
        if (TRANSFORM.get(this) != null) {
            try {
                p = (Point2D.Double) TRANSFORM.get(this).inverseTransform(p, new Point2D.Double());
            } catch (NoninvertibleTransformException ex) {
                ex.printStackTrace();
            }
        }
        /*
        return cachedPath.contains(p2);
         */
        boolean isClosed = CLOSED.get(getChild(0));
        double tolerance = Math.max(2f, AttributeKeys.getStrokeTotalWidth(this) / 2d);
        if (isClosed || FILL_COLOR.get(this) != null) {
            if (getPath().contains(p)) {
                return true;
            }
            double grow = AttributeKeys.getPerpendicularHitGrowth(this) * 2d;
            GrowStroke gs = new GrowStroke((float) grow,
                    (float) (AttributeKeys.getStrokeTotalWidth(this) *
                    STROKE_MITER_LIMIT.get(this))
                    );
            if (gs.createStrokedShape(getPath()).contains(p)) {
                return true;
            } else {
                if (isClosed) {
                    return false;
                }
            }
        }
        if (! isClosed) {
            if (Shapes.outlineContains(getPath(), p, tolerance)) {
                return true;
            }
        }
        return false;
    }
    public void setBounds(Point2D.Double anchor, Point2D.Double lead) {
        if (getChildCount() == 1 && ((SVGBezierFigure) getChild(0)).getNodeCount() <= 2) {
            SVGBezierFigure b = (SVGBezierFigure) getChild(0);
            b.setBounds(anchor, lead);
            invalidate();
        } else {
            super.setBounds(anchor, lead);
        }
    }
    public void transform(AffineTransform tx) {
        if (TRANSFORM.get(this) != null ||
                (tx.getType() & (AffineTransform.TYPE_TRANSLATION)) != tx.getType()) {
            if (TRANSFORM.get(this) == null) {
                TRANSFORM.basicSetClone(this, tx);
            } else {
                AffineTransform t = TRANSFORM.getClone(this);
                t.preConcatenate(tx);
                TRANSFORM.basicSet(this, t);
            }
        } else {
            for (Figure f : getChildren()) {
                f.transform(tx);
            }
            if (FILL_GRADIENT.get(this) != null &&
                    ! FILL_GRADIENT.get(this).isRelativeToFigureBounds()) {
                Gradient g = FILL_GRADIENT.getClone(this);
                g.transform(tx);
                FILL_GRADIENT.basicSet(this, g);
            }
            if (STROKE_GRADIENT.get(this) != null && 
                    ! STROKE_GRADIENT.get(this).isRelativeToFigureBounds()) {
                Gradient g = STROKE_GRADIENT.getClone(this);
                g.transform(tx);
                STROKE_GRADIENT.basicSet(this, g);
            }
        }
        invalidate();
    }
    public void restoreTransformTo(Object geometry) {
        invalidate();
        Object[] restoreData = (Object[]) geometry;
        ArrayList<BezierPath> paths = (ArrayList<BezierPath>) restoreData[0];
        for (int i=0, n = getChildCount(); i < n; i++) {
            getChild(i).setBezierPath(paths.get(i));
        }
            TRANSFORM.basicSetClone(this, (AffineTransform) restoreData[1]);
            FILL_GRADIENT.basicSetClone(this, (Gradient) restoreData[2]);
            STROKE_GRADIENT.basicSetClone(this, (Gradient) restoreData[3]);
    }
    
    public Object getTransformRestoreData() {
        ArrayList<BezierPath> paths = new ArrayList<BezierPath>(getChildCount());
        for (int i=0, n = getChildCount(); i < n; i++) {
            paths.add(getChild(i).getBezierPath());
        }
        return new Object[] {
            paths,
            TRANSFORM.getClone(this),
            FILL_GRADIENT.getClone(this),
            STROKE_GRADIENT.getClone(this),
        };
    }
    public void setAttribute(AttributeKey key, Object newValue) {
        super.setAttribute(key, newValue);
        invalidate();
    }
    protected void setAttributeOnChildren(AttributeKey key, Object newValue) {
        // empty!
    }
    
    public boolean isEmpty() {
        for (Figure child : getChildren()) {
            SVGBezierFigure b = (SVGBezierFigure) child;
            if (b.getNodeCount() > 0) {
                return false;
            }
        }
        return true;
    }
    
    @Override public Collection<Handle> createHandles(int detailLevel) {
        LinkedList<Handle> handles = new LinkedList<Handle>();
        switch (detailLevel % 2) {
            case 0 :
                handles.add(new SVGPathOutlineHandle(this));
                for (Figure child : getChildren()) {
                    handles.addAll(((SVGBezierFigure) child).createHandles(this, detailLevel));
                }
                break;
            case 1 :
                TransformHandleKit.addTransformHandles(this, handles);
                break;
            default:
                break;
        }
        return handles;
    }
    
    @Override public Collection<Action> getActions(Point2D.Double p) {
        final ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.samples.svg.Labels");
        LinkedList<Action> actions = new LinkedList<Action>();
        if (TRANSFORM.get(this) != null) {
            actions.add(new AbstractAction(labels.getString("removeTransform")) {
                public void actionPerformed(ActionEvent evt) {
                    ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.samples.svg.Labels");
                    SVGPathFigure.this.willChange();
                    fireUndoableEditHappened(
                            TRANSFORM.setUndoable(SVGPathFigure.this, null, labels)
                            );
                    SVGPathFigure.this.changed();
                }
            });
            actions.add(new AbstractAction(labels.getString("flattenTransform")) {
                public void actionPerformed(ActionEvent evt) {
                    // CompositeEdit edit = new CompositeEdit(labels.getString("flattenTransform"));
                    //TransformEdit edit = new TransformEdit(SVGPathFigure.this, )
                    final Object restoreData = getTransformRestoreData();
                    UndoableEdit edit = new AbstractUndoableEdit() {
                        public String getPresentationName() {
                            return labels.getString("flattenTransform");
                        }
                        
                        public void undo() throws CannotUndoException {
                            super.undo();
                            willChange();
                            restoreTransformTo(restoreData);
                            changed();
                        }
                        
                        public void redo() throws CannotRedoException {
                            super.redo();
                            willChange();
                            restoreTransformTo(restoreData);
                            flattenTransform();
                            changed();
                        }
                    };
                    willChange();
                    flattenTransform();
                    changed();
                    fireUndoableEditHappened(edit);
                }
            });
        }
        actions.add(new AbstractAction(labels.getString("closePath")) {
            public void actionPerformed(ActionEvent evt) {
                for (Figure child : getChildren()) {
                    SVGPathFigure.this.willChange();
                    getDrawing().fireUndoableEditHappened(
                            CLOSED.setUndoable(child, true, labels)
                    );
                    SVGPathFigure.this.changed();
                }
            }
        });
        actions.add(new AbstractAction(labels.getString("openPath")) {
            public void actionPerformed(ActionEvent evt) {
                for (Figure child : getChildren()) {
                    SVGPathFigure.this.willChange();
                    getDrawing().fireUndoableEditHappened(
                            CLOSED.setUndoable(child, false, labels)
                    );
                    SVGPathFigure.this.changed();
                }
            }
        });
        actions.add(new AbstractAction(labels.getString("windingEvenOdd")) {
            public void actionPerformed(ActionEvent evt) {
                    SVGPathFigure.this.willChange();
                    getDrawing().fireUndoableEditHappened(
                            WINDING_RULE.setUndoable(SVGPathFigure.this, WindingRule.EVEN_ODD, labels)
                    );
                    SVGPathFigure.this.changed();
            }
        });
        actions.add(new AbstractAction(labels.getString("windingNonZero")) {
            public void actionPerformed(ActionEvent evt) {
                WINDING_RULE.set(SVGPathFigure.this, WindingRule.NON_ZERO);
                    getDrawing().fireUndoableEditHappened(
                            WINDING_RULE.setUndoable(SVGPathFigure.this, WindingRule.EVEN_ODD, labels)
                    );
            }
        });
        return actions;
    }
    // CONNECTING
    public boolean canConnect() {
        return false; // SVG does not support connecting
    }
    public Connector findConnector(Point2D.Double p, ConnectionFigure prototype) {
        return null; // SVG does not support connectors
    }
    public Connector findCompatibleConnector(Connector c, boolean isStartConnector) {
        return null; // SVG does not support connectors
    }
    /**
     * Handles a mouse click.
     */
    @Override public boolean handleMouseClick(Point2D.Double p, MouseEvent evt, DrawingView view) {
        if (evt.getClickCount() == 2 && view.getHandleDetailLevel() % 2 == 0) {
            for (Figure child : getChildren()) {
                SVGBezierFigure bf = (SVGBezierFigure) child;
                int index = bf.getBezierPath().findSegment(p, (float) (5f / view.getScaleFactor()));
                if (index != -1) {
                    bf.handleMouseClick(p, evt, view);
                    evt.consume();
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override public void add(final int index, final Figure figure) {
        super.add(index, (SVGBezierFigure) figure);
    }
    
    @Override public SVGBezierFigure getChild(int index) {
        return (SVGBezierFigure) super.getChild(index);
    }
    public SVGPathFigure clone() {
        SVGPathFigure that = (SVGPathFigure) super.clone();
        return that;
    }
    
    public void flattenTransform() {
        willChange();
        AffineTransform tx = TRANSFORM.get(this);
        if (tx != null) {
        for (Figure child : getChildren()) {
            ((SVGBezierFigure) child).transform(tx);
            ((SVGBezierFigure) child).flattenTransform();
        }
        }
        TRANSFORM.basicSet(this, null);
        changed();
    }
}
