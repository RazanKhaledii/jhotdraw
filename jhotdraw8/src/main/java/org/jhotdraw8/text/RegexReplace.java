/* @(#)RegexReplace.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.text;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Find - ReplaceAll regular expression.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class RegexReplace {

    private final String find;
    private final String replace;
    private transient Pattern pattern;

    public RegexReplace() {
        this.find = null;
        this.replace = null;
    }

    public RegexReplace(String find, String replace) {
        this.find = find;
        this.replace = replace;
    }

    public String getFind() {
        return find;
    }

    public String getReplace() {
        return replace;
    }

    @Override
    public String toString() {
        return "/" + escape(find) + "/" + escape(replace) + "/";
    }

    private String escape(String str) {
        return str == null ? "" : str.replace("/", "\\/");
    }

    /**
     * Applies the regular expression to the string.
     *
     * @param str the string
     * @return the replaced string
     */
    public String apply(String str) {
        if (str == null) {
            return str;
        }
        if (find == null) {
            return replace == null ? str : replace;
        }
        if (pattern == null) {
            pattern = Pattern.compile(find);
        }

        Matcher m = pattern.matcher(str);
        try {
            return replace == null ? m.replaceAll("$0") : m.replaceAll(replace);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return str;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.find);
        hash = 53 * hash + Objects.hashCode(this.replace);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RegexReplace other = (RegexReplace) obj;
        if (!Objects.equals(this.find, other.find)) {
            return false;
        }
        if (!Objects.equals(this.replace, other.replace)) {
            return false;
        }
        return true;
    }

}
