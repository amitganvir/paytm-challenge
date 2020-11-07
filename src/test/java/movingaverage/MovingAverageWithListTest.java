package movingaverage;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertEquals;

public class MovingAverageWithListTest {

    private IMovingAverage movingAverage;

    @Before
    public void setup() {
        movingAverage = new MovingAverageWithList();
        addValues(movingAverage);
    }

    @Test
    public void shouldReturnValidMovingAverageWhenLastNElementsAreLessThanTotalSize() {
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () ->
                movingAverage.movingAverageOf(3));
        assertEquals("Functionality not supported. Try using MovingAverageWithMap implementation", runtimeException.getMessage());
    }

    @Test
    public void shouldReturnValidMovingAverageWhenLastNElementsIsEqualToTotalSize() {
        double totalAverage = movingAverage.movingAverageOf(10);
        assertEquals(5.5, totalAverage, 0.0);
    }

    @Test
    public void shouldReturnElementsInOrder() {
        assertEquals(5, movingAverage.getElement(5));
        assertEquals(1, movingAverage.getElement(1));
        assertEquals(9, movingAverage.getElement(9));
    }

    private void addValues(IMovingAverage movingAverage) {
        movingAverage.addElement(1);
        movingAverage.addElement(2);
        movingAverage.addElement(3);
        movingAverage.addElement(4);
        movingAverage.addElement(5);
        movingAverage.addElement(6);
        movingAverage.addElement(7);
        movingAverage.addElement(8);
        movingAverage.addElement(9);
        movingAverage.addElement(10);
    }
}
