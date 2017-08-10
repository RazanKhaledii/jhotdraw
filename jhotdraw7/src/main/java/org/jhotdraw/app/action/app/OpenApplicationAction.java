/* @(#)OpenApplicationAction.java
 * Copyright © 1996-2017 The authors and contributors of JHotDraw.
 * MIT License, CC-by License, or LGPL License.
 */

package org.jhotdraw.app.action.app;

import java.awt.event.ActionEvent;
import javax.swing.Action;
import org.jhotdraw.app.Application;
import org.jhotdraw.app.action.AbstractApplicationAction;

/**
 * Handles an open application request from Mac OS X (this action does nothing).
 * <p>
 * This action is called when {@code DefaultOSXApplication} receives an
 * Open Application event from the Mac OS X Finder or another Mac OS X application.
 * <p>
 * This action is automatically created by {@code DefaultOSXApplication}
 * and put into the {@code ApplicationModel} before
 * {@link org.jhotdraw.app.ApplicationModel#initApplication} is called.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class OpenApplicationAction extends AbstractApplicationAction {
    private static final long serialVersionUID = 1L;

    public static final String ID = "application.openApplication";
    /** Creates a new instance. */
    public OpenApplicationAction(Application app) {
        super(app);
        putValue(Action.NAME, "OSX Open Application");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

}
