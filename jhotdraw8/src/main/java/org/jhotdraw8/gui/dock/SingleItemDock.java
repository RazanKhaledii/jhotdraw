/* @(#)SingleItemDock.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.gui.dock;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jhotdraw8.gui.CustomSkin;

/**
 * SingleItemDock is a non-editable dock which can hold exactly one item.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class SingleItemDock extends Control implements Dock {

    @NonNull
    private BorderPane borderPane = new BorderPane();
    private final ObservableList<DockItem> items = FXCollections.observableArrayList();

    public SingleItemDock() {
        this(null);
    }

    public SingleItemDock(@Nullable DockItem item) {
        setSkin(new CustomSkin<>(this));
        getChildren().add(borderPane);
        SplitPane.setResizableWithParent(this, Boolean.TRUE);
        VBox.setVgrow(this, Priority.ALWAYS);
        HBox.setHgrow(this, Priority.ALWAYS);

        setMinWidth(40);
        setMinHeight(40);
        setMaxWidth(Double.MAX_VALUE);
        setMaxHeight(Double.MAX_VALUE);

        getItems().addListener(new ListChangeListener<DockItem>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends DockItem> c) {
                while (c.next()) {
                    for (DockItem remitem : c.getRemoved()) {
                        borderPane.setCenter(null);
                    }
                    for (DockItem additem : c.getAddedSubList()) {
                        borderPane.setCenter(additem.getContent());
                    }
                }
            }
        });

        if (item != null) {
            items.add(item);
        }
    }

    @NonNull
    @Override
    public ObservableList<DockItem> getItems() {
        return items;
    }

    @Override
    public boolean isEditable() {
        return false;
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        borderPane.resizeRelocate(0, 0, getWidth(), getHeight());
    }

    @Override
    protected double computePrefHeight(double width) {
        return borderPane.prefHeight(width);
    }

    @Override
    protected double computePrefWidth(double height) {
        return borderPane.prefWidth(height);
    }
    
    @NonNull
    private ObjectProperty<Track> track=new SimpleObjectProperty<>();
    @NonNull
    @Override
    public ObjectProperty<Track> trackProperty() {
        return track;
    }


}
