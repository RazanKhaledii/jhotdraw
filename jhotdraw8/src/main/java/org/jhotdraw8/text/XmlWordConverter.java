/* @(#)XmlWordConverter.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.text;

import java.io.IOException;
import java.nio.CharBuffer;
import java.text.ParseException;
import org.jhotdraw8.io.IdFactory;

/**
 * XmlWordConverter.
 *
 * @author Werner Randelshofer
 */
public class XmlWordConverter implements Converter<String> {

    @Override
    public void toString(Appendable out, IdFactory idFactory, String value) throws IOException {
        for (char ch : value.toCharArray()) {
            if (Character.isWhitespace(ch)) {
                break;
            }
            out.append(ch);
        }
    }

    @Override
    public String fromString(CharBuffer in, IdFactory idFactory) throws ParseException, IOException {
        int pos = in.position();
        StringBuilder out = new StringBuilder();
        while (in.remaining() > 0 && !Character.isWhitespace(in.charAt(0))) {
            out.append(in.get());
        }
        if (out.length() == 0) {
            in.position(pos);
            throw new ParseException("word expected", pos);
        }
        return out.toString();
    }

    @Override
    public String getDefaultValue() {
        return "";
    }
}
