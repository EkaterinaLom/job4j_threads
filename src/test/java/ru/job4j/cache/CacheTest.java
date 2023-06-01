package ru.job4j.cache;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class CacheTest {

    @Test
    void whenAdd() {
        Cache cache = new Cache();
        Base first = new Base(1, 1);
        Base second = new Base(2, 1);
        assertThat(cache.add(first)).isTrue();
        assertThat(cache.add(second)).isTrue();
        assertThat(cache.add(first)).isFalse();
    }

    @Test
    void whenDelete() {
        Cache cache = new Cache();
        Base first = new Base(1, 2);
        assertThat(cache.add(first)).isTrue();
        assertThat(cache.add(first)).isFalse();
        cache.delete(first);
        assertThat(cache.add(first)).isTrue();
    }

    @Test
    void whenUpdate() {
        Cache cache = new Cache();
        Base first = new Base(1, 1);
        cache.add(first);
        assertThat(cache.update(new Base(1, 1))).isTrue();
    }

    @Test
    void whenNotUpdate() {
        Cache cache = new Cache();
        Base first = new Base(1, 2);
        cache.add(first);
        assertThatThrownBy(() -> cache.update(new Base(1, 1))).isInstanceOf(OptimisticException.class);
    }
}