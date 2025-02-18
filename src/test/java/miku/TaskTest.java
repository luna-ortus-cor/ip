package miku;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testFormat() throws Exception {
        assertEquals("[ ] [3] test", (new Task("test")).toString());
        assertEquals("[X] [3] test", (new Task("test", true)).toString());
        assertEquals("0 | 3 | test", (new Task("test")).toSaveFormat());
        assertEquals("1 | 3 | test", (new Task("test", true)).toSaveFormat());

        assertEquals("[ ] [1] test", (new Task("test", 1)).toString());
        assertEquals("[X] [1] test", (new Task("test", true, 1)).toString());
        assertEquals("0 | 1 | test", (new Task("test", 1)).toSaveFormat());
        assertEquals("1 | 1 | test", (new Task("test", true, 1)).toSaveFormat());

        assertEquals("[ ] [5] test", (new Task("test", 5)).toString());
        assertEquals("[X] [5] test", (new Task("test", true, 5)).toString());
        assertEquals("0 | 5 | test", (new Task("test", 5)).toSaveFormat());
        assertEquals("1 | 5 | test", (new Task("test", true, 5)).toSaveFormat());

        assertEquals("[ ] [1] test", (new Task("test", 0)).toString());
        assertEquals("[X] [1] test", (new Task("test", true, 0)).toString());
        assertEquals("0 | 1 | test", (new Task("test", 0)).toSaveFormat());
        assertEquals("1 | 1 | test", (new Task("test", true, 0)).toSaveFormat());

        assertEquals("[ ] [5] test", (new Task("test", 10)).toString());
        assertEquals("[X] [5] test", (new Task("test", true, 10)).toString());
        assertEquals("0 | 5 | test", (new Task("test", 10)).toSaveFormat());
        assertEquals("1 | 5 | test", (new Task("test", true, 10)).toSaveFormat());
    }
}
