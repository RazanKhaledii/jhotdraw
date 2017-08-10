/* @(#)Event.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.event;

import java.util.EventObject;

/**
 * Event.
 *
 * @author Werner Randelshofer
 * @version $Id$
 * @param <E> The type of the event source
 */
public class Event<E> extends EventObject {

    private final static long serialVersionUID = 1L;

    public Event(E source) {
        super(source);
    }

    @Override
    public E getSource() {
        @SuppressWarnings("unchecked")
        E temp = (E) super.getSource();
        return temp;
    }

}
