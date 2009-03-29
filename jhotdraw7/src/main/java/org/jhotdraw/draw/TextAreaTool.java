/*
 * @(#)TextAreaTool.java  2.3.1  2009-09-29
 *
 * Copyright (c) 1996-2009 by the original authors of JHotDraw
 * and all its contributors.
 * All rights reserved.
 *
 * The copyright of this software is owned by the authors and  
 * contributors of the JHotDraw project ("the copyright holders").  
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * the copyright holders. For details see accompanying license terms. 
 */
package org.jhotdraw.draw;

import ch.randelshofer.quaqua.util.ResourceBundleUtil;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoableEdit;
import org.jhotdraw.geom.*;

/**
 * A tool to create new or edit existing figures that implement the TextHolderFigure
 * interface, such as TextAreaFigure. The figure to be created is specified by a
 * prototype.
 * <p>
 * To create a figure using the TextAreaTool, the user does the following mouse
 * gestures on a DrawingView:
 * <ol>
 * <li>Press the mouse button over the DrawingView. This defines the
 * start point of the Figure bounds.</li>
 * <li>Drag the mouse while keeping the mouse button pressed, and then release
 * the mouse button. This defines the end point of the Figure bounds.</li>
 * </ol>
 * When the user has performed these mouse gesture, the TextAreaTool overlays
 * a text area over the drawing where the user can enter the text for the Figure.
 * <p>
 * To edit an existing text figure using the TextAreaTool, the user does the
 * following mouse gesture on a DrawingView:
 * </p>
 * <ol>
 * <li>Press the mouse button over a Figure on the DrawingView.</li>
 * </ol>
 * <p>
 * The TextAreaTool then uses Figure.findFigureInside to find a Figure that
 * implements the TextHolderFigure interface and that is editable. Then it overlays
 * a text area over the drawing where the user can enter the text for the Figure.
 * </p>
 * <p>
 * XXX - Maybe this class should be split up into a CreateTextAreaTool and
 * a EditTextAreaTool.
 * </p>
 *
 * @author Werner Randelshofer
 * @version 2.3.1 2009-03-29 Editing of a TextArea which is behind another figure
 * did not work. Partially implemented undoable edit handling.
 * <br>2.3 2008-05-17 Honor toolDoneAfterCreation property.
 * <br>2.2 2007-11-25 Added variable isForCreationOnly.
 * <br>2.1 2007-08-22 Added support for property 'toolDoneAfterCreation'.
 * <br>2.0 2006-01-14 Changed to support double precison coordinates.
 * <br>1.0 2003-12-01 Derived from JHotDraw 5.4b1.
 * @see TextHolderFigure
 * @see FloatingTextArea
 */
public class TextAreaTool extends CreationTool implements ActionListener {

    private FloatingTextArea textArea;
    private TextHolderFigure typingTarget;
    /**
     * By default this tool is only used for the creation of new TextAreaFigures.
     * If this variable is set to false, the tool is used to create new
     * TextAreaFigures and edit existing TextAreaFigures.
     */
    private boolean isForCreationOnly = true;
    /**
     * Rubberband color of the tool. When this is null, the tool does not
     * draw a rubberband.
     */
    private Color rubberbandColor = null;

    /** Creates a new instance. */
    public TextAreaTool(TextHolderFigure prototype) {
        super(prototype);
    }

    public TextAreaTool(TextHolderFigure prototype, Map attributes) {
        super(prototype, attributes);
    }

    /**
     * Sets the rubberband color for the tool. Setting this to null, disables
     * the rubberband.
     *
     * @param c Rubberband color or null.
     */
    public void setRubberbandColor(Color c) {
        rubberbandColor = c;
    }

    /**
     * By default this tool is used to create a new TextHolderFigure.
     * If this property is set to false, the tool is used to create
     * a new TextHolderFigure or to edit an existing TextHolderFigure.
     */
    public void setForCreationOnly(boolean newValue) {
        isForCreationOnly = newValue;
    }

    /**
     * Returns true, if this tool can be only be used for creation of
     * TextHolderFigures and not for editing existing ones. 
     */
    public boolean isForCreationOnly() {
        return isForCreationOnly;
    }

    public void deactivate(DrawingEditor editor) {
        endEdit();
        super.deactivate(editor);
    }

    /**
     * Creates a new figure at the mouse location.
     * If editing is in progress, this finishes editing.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        TextHolderFigure textHolder = null;

        // Note: The search sequence used here, must be
        // consistent with the search sequence used by the
        // HandleTracker, SelectAreaTracker, DelegationSelectionTool, SelectionTool.

        // If possible, continue to work with the current selection
        DrawingView v = getView();
        Point2D.Double p = v.viewToDrawing(e.getPoint());
        Figure pressedFigure = null;
        if (true /*isSelectBehindEnabled()*/) {
            for (Figure f : v.getSelectedFigures()) {
                if (f.contains(p)) {
                    pressedFigure = f;
                    break;
                }
            }
        }

        // If the point is not contained in the current selection,
        // search for a figure in the drawing.
        if (pressedFigure == null) {
            pressedFigure = getDrawing().findFigureInside(p);
        }

        // 
        if (pressedFigure instanceof TextHolderFigure) {
            textHolder = (TextHolderFigure) pressedFigure;
            if (!textHolder.isEditable() || isForCreationOnly) {
                textHolder = null;
            }
        }

        if (textHolder != null) {
            createdFigure = null;
            beginEdit(textHolder);
            return;
        }
        if (typingTarget != null) {
            endEdit();
            if (isToolDoneAfterCreation()) {
                fireToolDone();
            }
        } else {
            super.mousePressed(e);
        // update view so the created figure is drawn before the floating text
        // figure is overlaid. (Note, fDamage should be null in StandardDrawingView
        // when the overlay figure is drawn because a JTextField cannot be scrolled)
        //view().checkDamage();
            /*
        textHolder = (TextHolderFigure)getCreatedFigure();
        beginEdit(textHolder);*/
        }
    }

    /**
     * This method allows subclasses to do perform additonal user interactions
     * after the new figure has been created.
     * The implementation of this class just invokes fireToolDone.
     */
    @Override
    protected void creationFinished(Figure createdFigure) {
        getView().clearSelection();
        getView().addToSelection(createdFigure);
        beginEdit((TextHolderFigure) createdFigure);
    }
    /*
    public void mouseDragged(java.awt.event.MouseEvent e) {
    }
     */

    @Override
    public void draw(Graphics2D g) {
        if (createdFigure != null && rubberbandColor != null) {
            g.setColor(rubberbandColor);
            g.draw(getView().drawingToView(createdFigure.getBounds()));
        }
    }

    protected void beginEdit(TextHolderFigure textHolder) {
        if (textArea == null) {
            textArea = new FloatingTextArea();

        //textArea.addActionListener(this);
        }

        if (textHolder != typingTarget && typingTarget != null) {
            endEdit();
        }
        textArea.createOverlay(getView(), textHolder);
        textArea.setBounds(getFieldBounds(textHolder), textHolder.getText());
        textArea.requestFocus();
        typingTarget = textHolder;
    }

    private Rectangle2D.Double getFieldBounds(TextHolderFigure figure) {
        Rectangle2D.Double r = figure.getDrawingArea();
        Insets2D.Double insets = figure.getInsets();
        insets.subtractTo(r);

        // FIXME - Find a way to determine the parameters for grow.
        //r.grow(1,2);
        //r.width += 16;
        r.x -= 1;
        r.y -= 2;
        r.width += 18;
        r.height += 4;
        return r;
    }

    protected void endEdit() {
        if (typingTarget != null) {
            typingTarget.willChange();

            final TextHolderFigure editedFigure = typingTarget;
            final String oldText = typingTarget.getText();
            final String newText = textArea.getText();

            if (newText.length() > 0) {
                typingTarget.setText(newText);
            } else {
                if (createdFigure != null) {
                    getDrawing().remove((Figure) getAddedFigure());
                // XXX - Fire undoable edit here!!
                } else {
                    typingTarget.setText("");
                }
            }

            UndoableEdit edit = new AbstractUndoableEdit() {

                @Override
                public String getPresentationName() {
                    ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.draw.Labels");
                    return labels.getString("attribute.text.text");
                }

                @Override
                public void undo() {
                    super.undo();
                    editedFigure.willChange();
                    editedFigure.setText(oldText);
                    editedFigure.changed();
                }

                @Override
                public void redo() {
                    super.redo();
                    editedFigure.willChange();
                    editedFigure.setText(newText);
                    editedFigure.changed();
                }
            };
            getDrawing().fireUndoableEditHappened(edit);

            typingTarget.changed();
            typingTarget = null;

            textArea.endOverlay();
        }
    //	        view().checkDamage();
    }

    public void actionPerformed(ActionEvent event) {
        endEdit();
        if (isToolDoneAfterCreation()) {
            fireToolDone();
        }
    }
}
