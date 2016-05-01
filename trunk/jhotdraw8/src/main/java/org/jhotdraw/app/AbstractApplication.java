/* @(#)AbstractApplication.java
 * Copyright (c) 2015 by the authors and contributors of JHotDraw.
 * You may only use this file in compliance with the accompanying license terms.
 */
package org.jhotdraw.app;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlySetProperty;
import javafx.beans.property.ReadOnlySetWrapper;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.collections.SetChangeListener;
import org.jhotdraw.collection.Key;

/**
 * AbstractApplication.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public abstract class AbstractApplication extends javafx.application.Application implements org.jhotdraw.app.Application {

    /**
     * Holds the max number of recent URIs.
     */
    private final IntegerProperty maxNumberOfRecentUris//
            = new SimpleIntegerProperty(//
                    this, MAX_NUMBER_OF_RECENT_URIS_PROPERTY, //
                    10);

    /**
     * Holds the recent URIs.
     */
    private final ReadOnlySetProperty<URI> recentUris//
            = new ReadOnlySetWrapper<URI>(//
                    this, RECENT_URIS_PROPERTY, //
                    FXCollections.observableSet(new LinkedHashSet<URI>())).getReadOnlyProperty();

    {
        Preferences prefs = Preferences.userNodeForPackage(AbstractApplication.class);
        String recentUrisSerialized = prefs.get("recentUris", "");
        for (String str : recentUrisSerialized.split("\t")) {
            if (str.isEmpty()) {
                continue;
            }
            if (recentUris.size() >= getMaxNumberOfRecentUris()) {
                break;
            }
            try {
                URI uri = new URI(str);
                recentUris.add(uri);
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        }
        recentUris.get().addListener(new SetChangeListener<URI>() {
            @Override
            public void onChanged(SetChangeListener.Change<? extends URI> change) {
                StringBuilder buf = new StringBuilder();
                int skip = recentUris.size() - getMaxNumberOfRecentUris();
                for (URI uri : recentUris) {
                    if (--skip > 0) {
                        continue;
                    }
                    if (buf.length() != 0) {
                        buf.append('\t');
                    }
                    String str = uri.toString();
                    if (str.indexOf("\t") != -1) {
                        System.err.println("AbstractApplication warning can't store URI in preferences. URI=" + uri);
                        continue;
                    }
                    buf.append(str);
                }
                prefs.put("recentUris", buf.toString());
            }

        });
    }

    /**
     * Holds the disablers.
     */
    private final SetProperty<Object> disablers = new SimpleSetProperty<>(this, DISABLERS_PROPERTY, FXCollections.observableSet());

    /**
     * True if disablers is not empty.
     */
    private final ReadOnlyBooleanProperty disabled;

    {
        ReadOnlyBooleanWrapper w = new ReadOnlyBooleanWrapper(this, DISABLED_PROPERTY);
        w.bind(Bindings.not(disablers.emptyProperty()));
        disabled = w.getReadOnlyProperty();
    }

    /**
     * Properties.
     */
    private ObservableMap<Key<?>, Object> properties;

    @Override
    public ReadOnlySetProperty<URI> recentUrisProperty() {
        return recentUris;
    }

    @Override
    public ReadOnlyBooleanProperty disabledProperty() {
        return disabled;
    }

    @Override
    public IntegerProperty maxNumberOfRecentUrisProperty() {
        return maxNumberOfRecentUris;
    }

    @Override
    public SetProperty<Object> disablersProperty() {
        return disablers;
    }

    @Override
    public final ObservableMap<Key<?>, Object> getProperties() {
        if (properties == null) {
            properties = FXCollections.observableMap(new IdentityHashMap<>());
        }
        return properties;
    }

}
