package CeciliaInezRevaJSleepRJ;

/**
 * Praktikum OOP - CS.
 *
 * @author (Cecilia Inez Reva Manurung)
 *          NPM: 2106636994
 * @version (11 Oktober 2022)
 */

import java.util.Iterator;
import java.util.Arrays;

public class Algorithm {
    private Algorithm ()
    {

    }
    //Method Count
    public static <T> int count (T[] arr, T val)
    {
        return count (Arrays.asList(arr).iterator(), val);
    }
    public static<T> int count (T[] arr, Predicate <T> pred)
    {
        return count (Arrays.asList(arr).iterator(), pred);
    }
    public static <T> int count (Iterable<T> iterable, T val)
    {
        return count(iterable.iterator(),val);
    }
    public static <T> int count(Iterator <T> iterator, T val){
        int loop = 0;
        while (iterator.hasNext())
        {
            if (iterator.next().equals(val))
            {
                loop += 1;
            }
        }
        return loop;
    }
    public static <T> int count (Iterator <T> iterator, Predicate <T> pred)
    {
        int loop = 0;
        while (iterator.hasNext())
        {
            if (pred.predicate(iterator.next()))
            {
                loop += 1;
            }
        }
        return loop;
    }
    public static <T> int count (Iterable <T> iterable, Predicate <T> pred)
    {
        int loop = 0;
        for (T t: iterable)
        {
            if (pred.predicate(t))
            {
                loop++;
            }
        }
        return loop;
    }

    //Method Exists
    public static <T> boolean exists (Iterable<T> iterable, T val)
    {
        final Iterator <T> loop = iterable.iterator();
        return exists(loop, val);
    }
    public static <T> boolean exist (T[] arr, Predicate<T> pred)
    {
        final Iterator<T> loop = Arrays.stream(arr).iterator();
        return exists (loop,pred);
    }
    public static <T> boolean exists (Iterable<T> iterable, Predicate<T> pred)
    {
        final Iterator <T> loop = iterable.iterator();
        return exists (loop, pred);
    }
    public static <T> boolean exist (T[] arr, T val)
    {
        final Iterator <T> loop = Arrays.stream(arr).iterator();
        return exists (loop,val);
    }
    public static <T> boolean exists(T[] arr, T val)
    {
        final Iterator<T> loop = Arrays.stream(arr).iterator();
        return exists (loop,val);
    }
    public static <T> boolean exists(Iterator<T> iterator, T val)
    {
        final Predicate<T> pred = val::equals;
        return exists (iterator, pred);
    }
    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred)
    {
        while(iterator.hasNext())
        {
            T current = iterator.next();
            if (pred.predicate(current) )
            {
                return true;
            }
        }
        return false;
    }

    //Method Find
    public static <T> T find(T[] arr, T val)
    {
        final Predicate <T> pred = val::equals;
        return find (arr, pred);
    }
    public static <T> T find (Iterable <T> iterator, T val)
    {
        final Predicate <T> pred = val :: equals;
        return find (iterator,pred);
    }
    public static <T> T find (Iterator<T> iterator, T val)
    {
        final Predicate <T> pred = val::equals;
        return find (iterator,pred);
    }
    public static <T> T find (Iterator<T> iterator, Predicate<T> pred)
    {
        while (iterator.hasNext()){
            T current = iterator.next();
            if (pred.predicate(current)){
                return current;
            }
        }
        return null;
    }
    public static <T> T find(T[] arr, Predicate <T> pred)
    {
        for(T t:arr)
        {
            if (pred.predicate(t))
            {
                return t;
            }
        }
        return null;
    }
    public static <T> T find(Iterable <T> iterable, Predicate <T> pred)
    {
        for (T t:iterable)
        {
            if (pred.predicate(t))
            {
                return t;
            }
        }
        return null;
    }
}
