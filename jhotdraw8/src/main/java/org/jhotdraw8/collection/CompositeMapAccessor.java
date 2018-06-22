/* @(#)CompositeMapAccessor.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.collection;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * CompositeMapAccessor.
 *
 * @param <T> the value type
 * @author Werner Randelshofer
 * @version $Id$
 */
public interface CompositeMapAccessor<T> extends MapAccessor<T> {

    long serialVersionUID = 1L;

    @Override
    default boolean containsKey( Map<Key<?>, Object> map) {
        for (MapAccessor<?> sub : getSubAccessors()) {
            if (!sub.containsKey(map)) {
                return false;
            }
        }
        return true;
    }

    @NonNull
    public Collection<MapAccessor<?>> getSubAccessors();

}
