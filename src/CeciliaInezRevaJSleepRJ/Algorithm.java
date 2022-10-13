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
import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    private Algorithm (){}
    //Method Count
    public static <T> int count (T[] array, T value)
    {
        return count (Arrays.asList(array).iterator(), value);
    }
    public static <T> int count (T[] array, Predicate <T> pred)
    {
        return count (Arrays.asList(array).iterator(), pred);
    }
    public static <T> int count (Iterable <T> iterable, T value)
    {
        return count (iterable.iterator(), value);
    }
    public static <T> int count(Iterator <T> iterator, T value){
        int count = 0;
        while (iterator.hasNext())
        {
            if (iterator.next().equals(value))
            {
                count += 1;
            }
        }
        return count;
    }
    public static <T> int count (Iterator <T> iterator, Predicate <T> pred)
    {
        int count = 0;
        while (iterator.hasNext())
        {
            if (pred.predicate(iterator.next()))
            {
                count += 1;
            }
        }
        return count;
    }
    public static <T> int count (Iterable <T> iterable, Predicate <T> pred)
    {
        int count = 0;
        for (T itr: iterable)
        {
            if (pred.predicate(itr))
            {
                count++;
            }
        }
        return count;
    }


    //Method Exists
    public static <T> boolean exist (T[] array, Predicate <T> pred)
    {
        final Iterator <T> it = Arrays.stream(array).iterator();
        return exists (it, pred);
    }
    public static <T> boolean exists(Iterator <T> iterator, T value)
    {
        final Predicate <T> pred = value::equals;
        return exists (iterator, pred);
    }
    public static <T> boolean exist (T[] array, T value)
    {
        final Iterator <T> it = Arrays.stream(array).iterator();
        return exists(it, value);
    }
    public static <T> boolean exists (Iterable<T> iterable, T value)
    {
        final Iterator <T> it = iterable.iterator();
        return exists (it, value);
    }
    public static <T> boolean exists (Iterable<T> iterable, Predicate <T> pred)
    {
        final Iterator <T> it = iterable.iterator();
        return exists (it, pred);
    }
    public static <T> boolean exists(Iterator <T> iterator, Predicate <T> pred)
    {
        while (iterator.hasNext())
        {
            T current = iterator.next();
            if (pred.predicate(current))
            {
                return true;
            }
        }
        return false;
    }


    //Method Find
    public static <T> T find(T[] array, T value)
    {
        final Predicate <T> pred = value::equals;
        return find (array, pred);
    }
    public static <T> T find (Iterable <T> iterable, T value)
    {
        final Predicate <T> pred = value::equals;
        return find (iterable, pred);
    }
    public static <T> T find(Iterator <T> iterator, T value)
    {
        final Predicate <T> pred = value::equals;
        return find (iterator, pred);
    }
    public static <T> T find(T[] array, Predicate <T> pred)
    {
        for(T itr : array)
        {
            if (pred.predicate(itr))
            {
                return itr;
            }
        }
        return null;
    }
    public static <T> T find(Iterable <T> iterable, Predicate <T> pred)
    {
        for (T itr : iterable)
        {
            if (pred.predicate(itr))
            {
                return itr;
            }
        }
        return null;
    }
    public static <T> T find (Iterator <T> iterator, Predicate <T> pred)
    {
        while (iterator.hasNext()){
            T current = iterator.next();
            if (pred.predicate(current)){
                return current;
            }
        }
        return null;
    }


    //Method collect
    public static <T> List<T> collect (Iterable <T> iterable, T value)
    {
        return collect (iterable.iterator(), value);
    }
    public static <T> List<T> collect (Iterable <T> iterable, Predicate <T> pred)
    {
        final Iterator <T> it = iterable.iterator();
        return collect (it, pred);
    }
    public static <T> List<T> collect (T[] array, T value)
    {
        return collect (Arrays.asList(array).iterator(), value);
    }
    public static <T> List<T> collect (Iterator <T> iterator, T value)
    {
        final Predicate <T> pred = value::equals;
        return collect (iterator, pred);
    }
    public static <T> List<T> collect (T[] array, Predicate <T> pred)
    {
        final Iterator <T> it = Arrays.stream(array).iterator();
        return collect (it, pred);
    }
    public static <T> List<T> collect (Iterator <T> iterator, Predicate <T> pred)
    {
        List<T> coll = new ArrayList<>();
        while (iterator.hasNext())
        {
            T current = iterator.next();
            if(pred.predicate(current))
            {
                coll.add (current);
            }
        }
        return coll;
    }


    //Method Paginate
    public static <T> List<T> paginate (Iterator <T> iterator, int page, int pageSize, Predicate <T> pred)
    {
        int value = 0;
        List<T> pag = new ArrayList<>();

        int add = page + pageSize;
        int addpag = page * pageSize;

        while(iterator.hasNext())
        {
            T current = iterator.next();
            if (value < add && value >= addpag)
            {
                pag.add(current);
            } value++;
        } return pag;
    }
    public static <T> List<T> paginate (T[] array, int page, int pageSize, Predicate <T> pred)
    {
        Iterator <T> it = Arrays.stream(array).iterator();
        return paginate (it, page, pageSize, pred);
    }
    public static <T> List<T> paginate (Iterable <T> iterable, int page, int pageSize, Predicate <T> pred)
    {
        Iterator <T> it = iterable.iterator();
        return paginate (it, page, pageSize, pred);
    }

}
