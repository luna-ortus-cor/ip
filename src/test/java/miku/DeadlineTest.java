package miku;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testFormat() throws Exception {
        assertEquals("[D] [ ] [3] test (by: 01 Jan 2025, 11:00PM)", (
            new Deadline("test", "2025-01-01 2300")).toString());
        assertEquals("[D] [X] [3] test (by: 01 Jan 2025, 11:00PM)", (
            new Deadline("test", true, "2025-01-01 2300")).toString());
        assertEquals("[D] [ ] [3] test (by: tonight)", (
            new Deadline("test", "tonight")).toString());
        assertEquals("[D] [X] [3] test (by: tonight)", (
            new Deadline("test", true, "tonight")).toString());
        assertEquals("D | 0 | 3 | test | 2025-01-01 2300 |  |", (
            new Deadline("test", "2025-01-01 2300")).toSaveFormat());
        assertEquals("D | 1 | 3 | test | 2025-01-01 2300 |  |", (
            new Deadline("test", true, "2025-01-01 2300")).toSaveFormat());
        assertEquals("D | 0 | 3 | test | tonight |  |", (
            new Deadline("test", "tonight")).toSaveFormat());
        assertEquals("D | 1 | 3 | test | tonight |  |", (
            new Deadline("test", true, "tonight")).toSaveFormat());
    }
}
