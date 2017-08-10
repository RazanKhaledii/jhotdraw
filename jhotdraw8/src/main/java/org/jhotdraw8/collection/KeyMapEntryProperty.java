/* @(#)KeyMapEntryProperty.java
 * Copyright © 2017 by the authors and contributors of JHotDraw. MIT License.
 */
package org.jhotdraw8.collection;

import javafx.collections.ObservableMap;

/**
 * KeyMapEntryProperty.
 * <p>
 * FIXME currently only works fully if the provided MapAccessor is an instance
 * of Key.
 *
 * @author Werner Randelshofer
 */
public class KeyMapEntryProperty<V> extends MapEntryProperty<Key<?>, Object, V> {

    private final MapAccessor<V> accessor;

    public KeyMapEntryProperty(ObservableMap<Key<?>, Object> map, MapAccessor<V> key) {
        super(map, (key instanceof Key<?>) ? (Key<?>) key : null, key.getValueType());
        this.accessor = key;
    }

    @Override
    public V get() {
        @SuppressWarnings("unchecked")
        V ret = accessor.get(map);
        return ret;
    }

    @Override
    public void set(V value) {
        if (value != null && !tClazz.isAssignableFrom(value.getClass())) {
            throw new IllegalArgumentException("value is not assignable " + value);
        }
        accessor.put(map, value);

        // Note: super must be called after "put", so that listeners
        //       can be properly informed.
        super.set(value);
    }
}
