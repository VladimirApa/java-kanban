package yandex.app.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {


    @Test
    void epicTaskIsEqualToEachOtherTest() {
        Epic epic1 = new Epic("Тест", "один");
        Epic epic2 = new Epic("Test", "one");
        epic1.setId(0);
        epic2.setId(0);

        assertEquals(epic1, epic2);
    }
}