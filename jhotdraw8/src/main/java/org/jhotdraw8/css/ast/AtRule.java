/* @(#)QualifiedRule.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.css.ast;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Collections;
import java.util.List;

/**
 * A "At rule" consists of an "At keyword", a "selector list" and a list of
 * "declaration"s.
 *
 * FIXME - An At Rule is actually quite more complex
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class AtRule extends AST {

    private final String atKeyword;
    private final SelectorGroup selectorList;
    @NonNull
    private final List<Declaration> declarations;

    public AtRule(String atKeyword,
                  SelectorGroup selectorGroup, @Nullable List<Declaration> declarations) {
        this.atKeyword = atKeyword;
        this.selectorList = selectorGroup;
        this.declarations = declarations == null ? Collections.emptyList() : Collections.unmodifiableList(declarations);
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder("AtRule: ");
        buf.append(selectorList.toString());
        buf.append("{");
        for (Declaration r : declarations) {
            buf.append(r.toString());
        }
        buf.append("}");
        return buf.toString();
    }

    public SelectorGroup getSelectorGroup() {
        return selectorList;
    }

    @NonNull
    public List<Declaration> getDeclarations() {
        return declarations;
    }

}
