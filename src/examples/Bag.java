package examples;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

//stores a fixed size array but elements can be added at will
//without specificying an index
public class Bag implements Iterable<Object>
{
    private static final int DEFAULT_BAG_SIZE = 10;
    private Object[] data;
    private int currentNumOfElements = 0;

    //modification count variable: increment a number everytime there's a change
    //anytime (add, remove, insert)
    //state of our array at any time
    private int modCount = 0;

    public Bag()
    {
        data = new Object[DEFAULT_BAG_SIZE];
    }

    public Bag(int sizeOfBag)
    {
        data = new Object[sizeOfBag];
    }

    public boolean add(Object newElement)
    {
        //search for a spot to place our new element
        // [1 ,3 ,131, null, null, null]

        for (int i = 0; i < data.length; i++) {
            //is the current spot empty
            if (data[i] == null)
            {
                data[i] = newElement;
                currentNumOfElements++;
                modCount++;
                return true;
            }
        }

        //the bag is full
        return false;
    }

    public int size()
    {
        return currentNumOfElements;
    }

    public String toString()
    {
        return Arrays.toString(data);
    }

    //this method declares that our class has an iterator,
    // AND it returns an iterator
    @Override
    public Iterator<Object> iterator()
    {
        return new BagIterator(data, this);
    }

    //inner class
    private static class BagIterator implements Iterator<Object>
    {
        private Object[] outerClassData;
        private int currentPosition = 0;
        private Bag parentBag;
        private int currentModCount;

        public BagIterator(Object[] outerClassData, Bag parentBag)
        {
            this.outerClassData = outerClassData;
            this.parentBag = parentBag;

            //save current state of the bag
            currentModCount = parentBag.modCount;
        }

        @Override
        public boolean hasNext() {
            //ensure that the data hasnt changed while we are iterating
            if(parentBag.modCount != currentModCount)
            {
                throw new ConcurrentModificationException("You cannot change a " +
                        "bag while iterating over it!");
            }

            //make sure we still have a valid index and the current element is not empty
            return currentPosition < outerClassData.length &&
                    outerClassData[currentPosition] != null;
        }

        @Override
        public Object next() {
            //ensure that the data hasnt changed while we are iterating
            if(parentBag.modCount != currentModCount)
            {
                throw new ConcurrentModificationException("You cannot change a " +
                        "bag while iterating over it!");
            }

            //return the current element and increment currentPosition so that it points
            //to the next element
            Object nextElement = outerClassData[currentPosition];
            currentPosition++;

            return nextElement;
        }
    }
}