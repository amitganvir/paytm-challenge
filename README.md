Solution for getting moving average of elements(Integers).

#### Components:
   - **IMovingAverage.java** Interface with methods
        - _addElement()_ : Adds elements in the collection
        - _getElement(int position)_: Gets an element at a given position
        - _movingAverageOf(int lastNElements)_: Returns average of lastNElements
        - _movingAverage()_: Returns average of all elements added till now.
        
   - **MovingAverageWithMap.java**
    Implementation of IMovingAverage that uses a HashMap to save elements.
    
    - Add element is a constant time and space operation i.e. O(1)
    - Get element is a constant time and space operation i.e. O(1)
    - Get all elements as List is a O(N) time and O(N) space operation.
    - Calculation of moving average is constant time and space operation i.e. O(1)
    
   **Moving Average Calculation:**
    
      The idea is to save the element in a map on every add element operation. 
      Instead of saving only the element, I am saving the element + sum of all previous elements in the map.
      So, at every position key, the value is going to the element at that position plus the sum of all the elements added previously.
           
            key = currentNumberOfElements
            value = elements + sumOfElementsTillNow
            
   **Example**
   
      Given numbers added 1,2,3,4,5,6,7,8,9,10 map will looks like:
            10 ->  55
            9  ->  45
            8  ->  36
            7  ->  28
            6  ->  21
            5  ->  15
            4  ->  10
            3  ->  6
            2  ->  3
            1  ->  1
         
      For computing moving average of last N elements, lets say, N = 3, here are the steps
        - Step 1: 
            Get the value for the max number of elements added i.e. 10th element, value = 55
        - Step 2:
            Get the value of the (numberOfElements - 3) i.e. 10 - 3 = 7th elment, value = 28
        - Step 3:
            Find the average using the formula: (55 - 28)/3
         
   **Time Complexity is O(1)**
   
        Step1 and Step2 are map lookups and hence have a constant time O(1) complexity
        Step3 is a divide operation and hence its a constant time O(1) complexity
   
   **Space Complexity is O(N)**
       
   
**Testing**
    - MovingAverageWithMapTest has test cases with different scenarios.
