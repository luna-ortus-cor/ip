package miku;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testFormat() throws Exception {
        assertEquals("[E] [ ] [3] test (from: 01 Jan 2025, 11:00PM to: 02 Jan 2025, 11:00AM)", (
            new Event("test", "2025-01-01 2300", "2025-01-02 1100")).toString());
        assertEquals("[E] [X] [3] test (from: 01 Jan 2025, 11:00PM to: 02 Jan 2025, 11:00AM)", (
            new Event("test", true, "2025-01-01 2300", "2025-01-02 1100")).toString());
        assertEquals("[E] [ ] [3] test (from: tonight to: tmr)", (
            new Event("test", "tonight", "tmr")).toString());
        assertEquals("[E] [X] [3] test (from: tonight to: tmr)", (
            new Event("test", true, "tonight", "tmr")).toString());
        assertEquals("E | 0 | 3 | test | 2025-01-01 2300 | 2025-01-02 1100 |  |", (
            new Event("test", "2025-01-01 2300", "2025-01-02 1100")).toSaveFormat());
        assertEquals("E | 1 | 3 | test | 2025-01-01 2300 | 2025-01-02 1100 |  |", (
            new Event("test", true, "2025-01-01 2300", "2025-01-02 1100")).toSaveFormat());
        assertEquals("E | 0 | 3 | test | tonight | tmr |  |", (
            new Event("test", "tonight", "tmr")).toSaveFormat());
        assertEquals("E | 1 | 3 | test | tonight | tmr |  |", (
            new Event("test", true, "tonight", "tmr")).toSaveFormat());
    }
}
