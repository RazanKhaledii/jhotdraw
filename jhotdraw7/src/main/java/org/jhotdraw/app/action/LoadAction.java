/*
 * @(#)LoadAction.java
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
package org.jhotdraw.app.action;

import org.jhotdraw.util.*;
import org.jhotdraw.gui.*;
import org.jhotdraw.gui.event.*;
import javax.swing.*;
import java.io.*;
import java.net.URI;
import org.jhotdraw.app.Application;
import org.jhotdraw.app.View;
import org.jhotdraw.gui.chooser.URIChooser;
import org.jhotdraw.net.URIUtil;

/**
 * Loads a file into the current view.
 *
 * @author  Werner Randelshofer
 * @version $Id$
 */
public class LoadAction extends AbstractSaveBeforeAction {

    public final static String ID = "file.load";

    /** Creates a new instance. */
    public LoadAction(Application app) {
        this(app,null);
    }
    /** Creates a new instance. */
    public LoadAction(Application app, View view) {
        super(app, view);
        ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.app.Labels");
        labels.configureAction(this, "file.open");
    }

    protected URIChooser getFileChooser(View view) {
        return view.getOpenChooser();
    }

    public void doIt(View view) {
        URIChooser fileChooser = getFileChooser(view);
        if (fileChooser.showOpenDialog(view.getComponent()) == URIChooser.APPROVE_OPTION) {
            loadViewFromURI(view, fileChooser.getSelectedURI());
        } else {
            view.setEnabled(true);
        }
    }

    public void loadViewFromURI(final View view, final URI uri) {
        view.setEnabled(false);

        // Open the file
        view.execute(new Worker() {

            protected Object construct() throws IOException {
                view.read(uri);
                return null;
            }

            @Override
            protected void done(Object value) {
                view.setURI(uri);
                view.setEnabled(true);
                getApplication().addRecentURI(uri);
            }

            @Override
            protected void failed(Throwable value) {
                ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.app.Labels");
                JSheet.showMessageSheet(view.getComponent(),
                        "<html>" + UIManager.getString("OptionPane.css") +
                        "<b>" + labels.getFormatted("file.load.couldntLoad.message", URIUtil.getName(uri)) + "</b><p>" +
                        ((value == null) ? "" : value),
                        JOptionPane.ERROR_MESSAGE, new SheetListener() {

                    public void optionSelected(SheetEvent evt) {
                        view.clear();
                        view.setEnabled(true);
                    }
                });
            }

        });
    }
}
