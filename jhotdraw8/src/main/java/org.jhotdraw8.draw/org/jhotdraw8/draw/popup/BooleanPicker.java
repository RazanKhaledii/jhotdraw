package org.jhotdraw8.draw.popup;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import org.jhotdraw8.css.text.CssConverter;
import org.jhotdraw8.draw.DrawLabels;
import org.jhotdraw8.text.Converter;
import org.jhotdraw8.util.Resources;

/**
 * Picker for boolean values.
 */
public class BooleanPicker extends AbstractPicker<Boolean> {
    private ContextMenu contextMenu;
    private MenuItem noneItem;

    public BooleanPicker() {

    }


    private void init() {
        Resources labels = DrawLabels.getResources();
        contextMenu = new ContextMenu();
        MenuItem initialItem;
        MenuItem trueItem;
        MenuItem falseItem;
        initialItem = new MenuItem();
        noneItem = new MenuItem();
        trueItem = new MenuItem();
        falseItem = new MenuItem();
        initialItem.setOnAction(e -> applyInitialValue());
        noneItem.setOnAction(e -> applyValue(null));
        trueItem.setOnAction(e -> applyValue(true));
        falseItem.setOnAction(e -> applyValue(false));
        labels.configureMenuItem(initialItem, "value.initial");
        labels.configureMenuItem(noneItem, "value.none");
        labels.configureMenuItem(trueItem, "value.true");
        labels.configureMenuItem(falseItem, "value.false");
        contextMenu.getItems().addAll(
                trueItem, falseItem,
                new SeparatorMenuItem(),
                initialItem, noneItem
        );
    }

    private void update() {
        if (contextMenu == null) {
            init();
        }
        Converter<Boolean> converter = getMapAccessor().getConverter();
        if (converter instanceof CssConverter<?>) {
            CssConverter<?> cssConverter = (CssConverter<?>) converter;
            noneItem.setVisible(cssConverter.isNullable());
        }
    }

    public void show(Node anchor, double screenX, double screenY) {
        update();
        contextMenu.show(anchor, screenX, screenY);
    }
}
