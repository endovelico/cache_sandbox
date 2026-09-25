package com.sandbox.cache.reachability;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class SoftReferenceCache<K, V> {

    private final Map<K, SoftValue<K, V>> cache = new HashMap<>();
    private final ReferenceQueue<V> referenceQueue = new ReferenceQueue<>();

    public synchronized void put(K key, V value) {
        cleanup();

        cache.put(key, new SoftValue<>(key, value, referenceQueue));
    }

    public synchronized V get(K key) {
        cleanup();

        SoftValue<K, V> reference = cache.get(key);

        if (reference == null) {
            return null;
        }

        V value = reference.get();

        if (value == null) {
            cache.remove(key);
        }

        return value;
    }

    public synchronized void remove(K key) {
        cleanup();
        cache.remove(key);
    }

    public synchronized int size() {
        cleanup();
        return cache.size();
    }

    private void cleanup() {
        SoftValue<K, V> reference;

        while ((reference = (SoftValue<K, V>) referenceQueue.poll()) != null) {
            cache.remove(reference.key, reference);
        }
    }

    private static class SoftValue<K, V>
            extends SoftReference<V> {

        private final K key;

        SoftValue(
                K key,
                V value,
                ReferenceQueue<V> queue
        ) {
            super(value, queue);
            this.key = key;
        }
    }
}
