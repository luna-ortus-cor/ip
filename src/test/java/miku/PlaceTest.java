package miku;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlaceTest {
    @Test
    public void testFormat() throws Exception {
        assertEquals("Place | skibidi | skibidi | 1 skibidi | 1.200000 | -1.200000", (
            new Place("skibidi", "skibidi", "1 skibidi", 1.200, -1.200)).toString());
        assertEquals("Place | skibidi | skibidi,,,,, | 1 skibidi | 1.200000 | 1.200000", (
            new Place("skibidi", "skibidi,,,,,", "1 skibidi", 1.200, 1.200)).toString());
    }
}
