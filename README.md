Solution for getting moving average of elements(Integers).

#### Components:
   - **IMovingAverage.java** Interface with methods
        - _addElement()_ : Adds elements in the collection
        - _getElement(int position)_: Gets an element at a given position
        - _movingAverageOf(int lastNElements)_: Returns average of lastNElements
        - _movingAverage()_: Returns average of all elements added till now.
        
   - **MovingAverageWithMap.java**
    Implementation of IMovingAverage that uses a HashMap to save elements.
    
    - Add element is a constant time operation i.e. O(1)
    - Get element is a constant time operation i.e. O(1)
    - Idea is to save the elements in a map on every add element operation:
           key = currentNumberOfElements
           value = elements + sumOfElementsTillNow
    - Average is computed as follows:
         for given numbers added 1,2,3,4,5,6,7,8,9,10 map will looks like this:
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
         
         for computing moving average of last N elements, lets say, N = 3 
            Get the value for the max number of elements added i.e. 10th element, value = 55
            Get the value of the (numberOfElements - 3) i.e. 10 - 3 = 7th elment, value = 28
         
         Average is computed using the formula: (55 - 28)/3
         
         Runtime Complexity:
            Average computation is a constant time operation O(1)
    
    This implementation can compute the average of last N itmes added when N is less than total elements.
     
           
   - **MovingAverageWithList**
    Implementation of IMovingAverage that uses ArrayList as collection to save elements.
   
    - This implementaion uses a instance variable to save the sum of elements added so far and another variable to store numberOfElements added till now.
    - Add element is a constant time operation i.e. O(1)
    - Get element is a constant time operation i.e. O(1)
    - Moving average is computed as (totalSum/numberOfElements). This is a constant time operation O(1)
    - This implementation only gives moving average of all the elements. It does not have the capability to give average of last X elements,where X is a number less than total size.
