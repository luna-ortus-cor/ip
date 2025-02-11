package miku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest{
    @Test
    public void testFormat() throws Exception{
        assertEquals("[ ] [3] test",(new Task("test")).toString());
        assertEquals("[X] [3] test",(new Task("test",true)).toString());
        assertEquals("0 | 3 | test",(new Task("test")).toSaveFormat());
        assertEquals("1 | 3 | test",(new Task("test",true)).toSaveFormat());
    }
}
