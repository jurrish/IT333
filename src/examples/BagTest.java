package examples;

import java.util.Iterator;
import java.util.ArrayList;

public class BagTest
{
    public static void main(String[] args)
    {
        //create a bag
        Bag myBag = new Bag(8);

        //add a few elements to the bag
        myBag.add("eloquent");
        myBag.add("hilarious");
        myBag.add("equestrian");

        System.out.println(myBag.toString());

        //this doesnt work - we need iterators
//        for (int i = 0; i < myBag.size(); i++)
//        {
//            System.out.println(myBag.);
//        }

        //we need Iterators! requires two interfaces
        // - Iterable<T> interface: declares that a class has an iterator
        // - Iterator<T> interface: the smart object that completes our iteration

        Iterator<Object> it = myBag.iterator();

        while(it.hasNext())
        {
            String word = (String)it.next();
            System.out.println(word);
        }

        for (Object word : myBag)
        {
            //will throw ConcurrentModException if we modify our content
//            myBag.add("Hello");
            System.out.println(word);
        }

        //for-each loop, enhanced for loop
        ArrayList<String> colors = new ArrayList<String>();

        colors.add("blue");
        colors.add("red");
        colors.add("white");

        //forEach uses iterators internally
        for (String color : colors)
        {
            System.out.println(color);
        }

        Iterator<String> iterator = colors.iterator();
        while(iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }
}
