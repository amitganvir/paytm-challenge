package movingaverage;

import java.util.List;

public interface IMovingAverage {

    /**
     * Add an integer to the collection
     *
     * @param element
     */
    void addElement(int element);

    /**
     * Get an element at a given position
     */
    int getElement(int position);

    /**
     * @param lastNElements Could be any number from 0 to N.
     *                      N being the number of elements in the collection.
     *                      <p>
     *                      lastNElements should be a non-negative value.
     *                      <p>
     *                      Throws a RuntimeException if lastNElements is negative
     * @return average of the lastNElements.
     * @returns average of all the elements when lastNElements equals 0
     */
    double movingAverageOf(int lastNElements);

    /**
     * @return Moving average of all the elements added till now.
     */
    double movingAverage();

    /**
     *
     * @return All the elements as List
     */
    List<Integer> getAllElements();

    default double getAverage(int maxSum, int minSum, int lastNElements) {
        if (lastNElements == 0) {
            throw new RuntimeException("No elements to compute the average");
        }
        return (maxSum - minSum) * 1.0 / lastNElements;
    }
}
