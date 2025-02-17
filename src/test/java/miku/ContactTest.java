package miku;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ContactTest {
    @Test
    public void testFormat() throws Exception {
        assertEquals("skibidi | - | toilet | - | - | 12345678 | 65 | "
            + "- | - | - | O+ | sigma@gmail.com | - | 420 skibidi road | - | ", (new Contact(
                "skibidi", "", "toilet", "", "", "12345678", "65",
                    "", "", null, "O+", "sigma@gmail.com", "", "420 skibidi road", "")).toString());
    }

    @Test
    public void testEmail() throws Exception {
        assertEquals(true, Contact.validateEmail("test@gmail.com"));
        assertEquals(true, Contact.validateEmail("teste-1234_5678@nus.edu.sg"));
        assertEquals(false, Contact.validateEmail("test.com"));
        assertEquals(false, Contact.validateEmail("test-1234@.com"));
    }
}
