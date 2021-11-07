import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    @Test
    public void test_EventCreate() {
        LocalDate dt = LocalDate.parse("2018-11-01");
        Event e = new Event(false, dt , "Test");

        assertFalse(e.getComplete());
        assertEquals(e.getDueDate(), dt);
        assertEquals(e.getDescription(), "Test");
    }
}