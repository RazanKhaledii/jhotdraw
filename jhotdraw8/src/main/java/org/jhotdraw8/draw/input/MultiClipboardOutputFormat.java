/* @(#)MultiClipboardOutputFormat.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.draw.input;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import javafx.scene.input.DataFormat;
import org.jhotdraw8.draw.figure.Drawing;
import org.jhotdraw8.draw.figure.Figure;

/**
 * MultiClipboardOutputFormat.
 *
 * @author Werner Randelshofer
 * @version $$Id: MultiClipboardOutputFormat.java 1237 2016-12-20 08:57:59Z
 * rawcoder $$
 */
public class MultiClipboardOutputFormat implements ClipboardOutputFormat {

    private ClipboardOutputFormat[] formats;

    public MultiClipboardOutputFormat(ClipboardOutputFormat... formats) {
        this.formats = formats;
    }

    @Override
    public void write(Map<DataFormat, Object> out, Drawing drawing, Collection<Figure> selection) throws IOException {
        for (ClipboardOutputFormat f : formats) {
            f.write(out, drawing, selection);
        }
    }

}
