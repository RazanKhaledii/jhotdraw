/*
 * @(#)AbstractPreferencesAction.java
 * 
 * Copyright (c) 2009-2010 The authors and contributors of JHotDraw.
 * 
 * You may not use, copy or modify this file, except in compliance with the 
 * accompanying license terms.
 */
package org.jhotdraw8.app.action.app;

import org.jhotdraw8.app.Application;
import org.jhotdraw8.app.action.AbstractApplicationAction;
import org.jhotdraw8.util.Resources;
import org.jhotdraw8.app.Project;

/**
 * Displays a preferences dialog for the application.
 * <p>
 *
 * @author Werner Randelshofer
 * @version $Id: AbstractPreferencesAction.java 1169 2016-12-11 12:51:19Z
 * rawcoder $
 */
public abstract class AbstractPreferencesAction<V extends Project<V>> extends AbstractApplicationAction<V> {

    private static final long serialVersionUID = 1L;

    public static final String ID = "application.preferences";

    /**
     * Creates a new instance.
     *
     * @param app the application
     */
    public AbstractPreferencesAction(Application<V> app) {
        super(app);
        Resources.getResources("org.jhotdraw8.app.Labels").configureAction(this, ID);
    }
}
