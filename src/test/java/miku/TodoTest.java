package miku;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void testFormat() throws Exception {
        assertEquals("[T] [ ] [3] test", (new Todo("test")).toString());
        assertEquals("[T] [X] [3] test", (new Todo("test", true)).toString());
        assertEquals("T | 0 | 3 | test |  |", (new Todo("test")).toSaveFormat());
        assertEquals("T | 1 | 3 | test |  |", (new Todo("test", true)).toSaveFormat());
    }
}
