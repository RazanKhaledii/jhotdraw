/* @(#)AbstractAction.java
 * Copyright (c) 2015 by the authors and contributors of JHotDraw.
 * You may only use this file in compliance with the accompanying license terms.
 */
package org.jhotdraw8.app.action;

import java.util.IdentityHashMap;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import org.jhotdraw8.app.AbstractDisableable;
import org.jhotdraw8.collection.Key;

/**
 * AbstractAction.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public abstract class AbstractAction extends AbstractDisableable implements Action {

    /**
     * Holds the properties.
     */
    protected final ObservableMap<Key<?>, Object> properties//
            = FXCollections.observableMap(new IdentityHashMap<>());

    private final BooleanProperty selected=new SimpleBooleanProperty(this, SELECTED_PROPERTY);
    /**
     * Creates a new instance. Binds {@code disabled} to {@code disable}.
     */
    public AbstractAction() {
        this(null);

    }

    /**
     * Creates a new instance. Binds {@code disabled} to {@code disable}.
     *
     * @param id the id of the action
     */
    public AbstractAction(String id) {
        set(Action.ID_KEY, id);

    }

    @Override
    public final ObservableMap<Key<?>, Object> getProperties() {
        return properties;
    }

    /**
     * Invokes {@link #handleActionPerformed} if the action is not disabled and the
     * event is not consumed. Consumes the event after invoking {@code
 handleActionPerformed}.
     *
     * @param event the action event
     */
    @Override
    public final void handle(ActionEvent event) {
        if (!isDisabled() && !event.isConsumed()) {
            handleActionPerformed(event);
            event.consume();
        }
    }

    /**
     * This method is invoked when the action is not disabled and the event is
     * not consumed.
     *
     * @param event the action event
     */
    protected abstract void handleActionPerformed(ActionEvent event);

    @Override
    public BooleanProperty selectedProperty() {
        return selected;
    }
}
