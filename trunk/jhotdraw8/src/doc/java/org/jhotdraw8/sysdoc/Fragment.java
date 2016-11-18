/* @(#)Fragment.java
 * Copyright (c) 2016 by the authors and contributors of JHotDraw.
 * You may only use this file in compliance with the accompanying license terms.
 */
package org.jhotdraw8.sysdoc;

import java.nio.file.Path;

/**
 * Identifies a text fragment in a file.
 *
 * @author Werner Randelshofer
 * @version $$Id$$
 */
public class Fragment {

    private final Path file;
    private final String name;

    public Fragment(Path file, String name) {
        this.file = file;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fragment{" + "file=" + file.getFileName() + ", name=" + name + '}';
    }

    public String getFileName() {
        return file.getFileName().toString();
    }

}
