package miku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest{
    @Test
    public void testFormat() throws Exception{
        assertEquals("[D] [ ] test (by: 01 Jan 2025, 11:00PM)",(new Deadline("test","2025-01-01 2300")).toString());
        assertEquals("[D] [X] test (by: 01 Jan 2025, 11:00PM)",(new Deadline("test",true,"2025-01-01 2300")).toString());
        assertEquals("[D] [ ] test (by: tonight)",(new Deadline("test","tonight")).toString());
        assertEquals("[D] [X] test (by: tonight)",(new Deadline("test",true,"tonight")).toString());
        assertEquals("D | 0 | test | 2025-01-01 2300",(new Deadline("test","2025-01-01 2300")).toSaveFormat());
        assertEquals("D | 1 | test | 2025-01-01 2300",(new Deadline("test",true,"2025-01-01 2300")).toSaveFormat());
        assertEquals("D | 0 | test | tonight",(new Deadline("test","tonight")).toSaveFormat());
        assertEquals("D | 1 | test | tonight",(new Deadline("test",true,"tonight")).toSaveFormat());
    }
}
