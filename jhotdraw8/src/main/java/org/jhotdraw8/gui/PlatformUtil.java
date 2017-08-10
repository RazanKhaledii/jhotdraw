/* @(#)PlatformUtil.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.gui;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import javafx.application.Platform;

/**
 * PlatformUtil.
 *
 * @author Werner Randelshofer
 */
public class PlatformUtil {

    public static void invokeAndWait(Runnable r) {
        if (Platform.isFxApplicationThread()) {
            r.run();
        } else {
            FutureTask<Boolean> task = new FutureTask<>(r, true);
            Platform.runLater(task);
            try {
                task.get();
            } catch (InterruptedException | ExecutionException ex) {
                throw new InternalError(ex);
            }
        }
    }
}
