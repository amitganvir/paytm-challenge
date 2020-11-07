package movingaverage;

import java.util.LinkedHashMap;
import java.util.Map;

public class MovingAverageWithMap implements IMovingAverage {

    private int numberOfElements;
    private Map<Integer, Integer> elementMap = new LinkedHashMap<>();

    /**
     * Adds an element into the element Map.
     * - Key: (currentSize + 1)
     * - Value: previousValue + element
     * <p>
     * This is a constant time i.e. O(1) operation
     * <p>
     * At any given point of time, numberOfElements or Nth key will have the value as (element + SUM of all previous elements)
     *
     * @param element
     */
    public void addElement(int element) {
        int previousSum = elementMap.getOrDefault(numberOfElements, 0);
        elementMap.put(++numberOfElements, previousSum + element);
    }

    /**
     * Returns an element at the specified index.
     * If index is less than zero, throws an RuntimeException.
     * <p>
     * This is a constant time i.e. O(1) operation as map lookup is constant time.
     *
     * @param position
     * @return Element at index.
     */
    public int getElement(int position) {
        if (position < 0) {
            throw new RuntimeException("Invalid position to get element " + position);
        }
        return elementMap.get(position) - elementMap.getOrDefault(position - 1, 0);
    }

    /**
     * @param lastNElements Could be any number from 1 to N.
     *                      N being the number of elements in the collection.
     *                      lastNElements should be a non-negative, non-zero value.
     *                      Throws a RuntimeException if lastNElements is negative
     * @Logic: Moving Average is calculated as follows:
     * <p>
     * A = get the value for the last element added. key = size of the map (numberOfElements)
     * B = get the value for (numberOfElements - lastNElements)
     * <p>
     * A has the sum of all values up till now (including A)
     * B has sum of all the values up till B (including B)
     * <p>
     * Subtract B from A, this will give sum of lastNElements.
     * C = A - B
     * <p>
     * Divide C by lastNElements, This will be the average of values for the lastNElmeents.
     */
    public double movingAverageOf(int lastNElements) {
        if (lastNElements < 0 || lastNElements == 0) {
            throw new RuntimeException("lastNElements should not be a non-negative and non-zero value");
        }

        if (lastNElements == numberOfElements || lastNElements > numberOfElements) {
            return movingAverage();
        }

        int maxIndex = numberOfElements;
        int minIndex = Math.max(0, numberOfElements - lastNElements);

        int maxSum = elementMap.getOrDefault(maxIndex, 0);
        int minSum = elementMap.getOrDefault(minIndex, 0);

        return getAverage(maxSum, minSum, lastNElements);
    }

    @Override
    public double movingAverage() {
        return getAverage(elementMap.getOrDefault(numberOfElements, 0), 0, numberOfElements);
    }
}
