/*
 * @(#)BrowseFileDirectoryAction.java
 * Copyright © The authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.app.action.file;

import javafx.event.ActionEvent;
import org.jhotdraw8.annotation.NonNull;
import org.jhotdraw8.annotation.Nullable;
import org.jhotdraw8.app.Application;
import org.jhotdraw8.app.ApplicationLabels;
import org.jhotdraw8.app.FileBasedActivity;
import org.jhotdraw8.app.action.AbstractActivityAction;

import java.awt.Desktop;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class BrowseFileDirectoryAction extends AbstractActivityAction<FileBasedActivity> {

    private static final long serialVersionUID = 1L;

    public static final String ID = "file.browseFileDirectory";

    /**
     * Creates a new instance.
     *
     * @param app  the application
     * @param view the view
     */
    public BrowseFileDirectoryAction(@NonNull Application app, FileBasedActivity view) {
        super(app, view, FileBasedActivity.class);
        ApplicationLabels.getResources().configureAction(this, ID);
    }

    /**
     * Creates a new instance.
     *
     * @param app the application
     */
    public BrowseFileDirectoryAction(@NonNull Application app) {
        this(app, null);
    }

    @Override
    protected void onActionPerformed(ActionEvent event, @NonNull FileBasedActivity activity) {
        if (isDisabled()) {
            return;
        }
        final URI uri = activity.getURI();
        doIt(uri);
    }

    private void doIt(@Nullable URI uri) {
        if (uri == null) {
            return;
        }
        try {
            Path path = Paths.get(uri);
            if (path != null) {
                //Desktop.getDesktop().browseFileDirectory(path.toFile());
                try {
                    try {
                        Desktop.class.getMethod("browseFileDirectory", File.class).invoke(Desktop.getDesktop(), path.toFile());
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileSystemNotFoundException e) {
            Logger.getLogger(BrowseFileDirectoryAction.class.getName()).warning(e.getMessage());
        }
    }

}
