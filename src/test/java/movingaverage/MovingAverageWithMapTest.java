package movingaverage;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MovingAverageWithMapTest {

    private IMovingAverage movingAverage;

    @Before
    public void setup() {
        movingAverage = new MovingAverageWithMap();
        addValues(movingAverage);
    }

    @Test
    public void shouldThrowAnExceptionWhenMovingAverageIsCalledWithNoElements() {

        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () ->
                new MovingAverageWithMap().movingAverage());
        assertEquals("No elements to compute the average", runtimeException.getMessage());
    }

    @Test
    public void shouldReturnValidMovingAverageWhenLastNElementsAreLessThanTotalSize() {
        double lastThreeMovingAvg = movingAverage.movingAverageOf(3);
        assertEquals(1/3.0, lastThreeMovingAvg, 0.0);
    }

    @Test
    public void shouldReturnValidMovingAverageWhenLastNElementsIsEqualToTotalSize() {
        double totalAverage = movingAverage.movingAverageOf(10);
        assertEquals(1.9, totalAverage, 0.0);
    }

    @Test
    public void shouldReturnElementsInOrder() {
        assertEquals(-5, movingAverage.getElement(5));
        assertEquals(1, movingAverage.getElement(1));
        assertEquals(9, movingAverage.getElement(9));
    }

    @Test
    public void shouldReturnAllElementsInOrder() {

        List<Integer> elements = movingAverage.getAllElements();

        assertEquals(-5, (int) elements.get(4));
        assertEquals(1, (int) elements.get(0));
        assertEquals(9, (int) elements.get(8));
    }

    @Test
    public void shouldThrowExceptionWhenLastNIndexValueIsNegative() {
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () ->
                movingAverage.movingAverageOf(-1));

        assertEquals("lastNElements should not be a non-negative and non-zero value", runtimeException.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenLastNIndexValueIsZero() {
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () ->
                movingAverage.movingAverageOf(0));

        assertEquals("lastNElements should not be a non-negative and non-zero value", runtimeException.getMessage());
    }

    private void addValues(IMovingAverage movingAverage) {
        movingAverage.addElement(1);
        movingAverage.addElement(2);
        movingAverage.addElement(3);
        movingAverage.addElement(4);
        movingAverage.addElement(-5);
        movingAverage.addElement(6);
        movingAverage.addElement(7);
        movingAverage.addElement(-18);
        movingAverage.addElement(9);
        movingAverage.addElement(10);
    }
}
