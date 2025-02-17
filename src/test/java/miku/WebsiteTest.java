package miku;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class WebsiteTest {
    @Test
    public void testFormat() throws Exception {
        assertEquals("Website | skibidi | skibidi | https://skibidi.com", (
            new Website("skibidi", "skibidi", "https://skibidi.com")).toString());
    }
}
