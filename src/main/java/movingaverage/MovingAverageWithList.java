package movingaverage;

import java.util.ArrayList;
import java.util.List;

public class MovingAverageWithList implements IMovingAverage {

    private int numberOfElements;
    private int totalSum;
    private List<Integer> elements = new ArrayList<>();

    @Override
    public void addElement(int element) {
        elements.add(element);
        numberOfElements++;
        totalSum += element;
    }

    @Override
    public int getElement(int position) {
        if (position > numberOfElements || position <= 0) {
            throw new RuntimeException("Invalid position to get element " + position);
        }
        return elements.get(position - 1);
    }

    @Override
    public double movingAverageOf(int lastNElements) {

        if (lastNElements < numberOfElements) {
            throw new RuntimeException("Functionality not supported. Try using MovingAverageWithMap implementation");
        }
        return movingAverage();
    }

    @Override
    public double movingAverage() {
        return getAverage(totalSum, 0, numberOfElements);
    }
}
