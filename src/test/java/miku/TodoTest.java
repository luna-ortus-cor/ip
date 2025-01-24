package miku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest{
    @Test
    public void testFormat() throws Exception{
        assertEquals("[T] [ ] test",(new Todo("test")).toString());
        assertEquals("[T] [X] test",(new Todo("test",true)).toString());
        assertEquals("T | 0 | test",(new Todo("test")).toSaveFormat());
        assertEquals("T | 1 | test",(new Todo("test",true)).toSaveFormat());
    }
}
