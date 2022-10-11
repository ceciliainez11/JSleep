package CeciliaInezRevaJSleepRJ;

/**
 * Praktikum OOP - CS.
 *
 * @author (Cecilia Inez Reva Manurung)
 *          NPM: 2106636994
 * @version (11 Oktober 2022)
 */

import java.util.HashMap;

public class Serializable {
    // instance variables - replace the example below with your own
    public final int id;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<Class<?>, Integer>(); //variabel instance

    public static <T> Integer getClosingId(Class<T> getter)
    {
        return mapCounter.get(getter);
    }

    public static <T> Integer setClosingId(Class<T>setter, int number)
    {
        return mapCounter.replace(setter, number);
    }

    public int compareTo(Serializable temp)
    {
        return((Integer) this.id).compareTo(temp.id);
    }

    public boolean equals(Object tempObject) {
        if (tempObject instanceof Serializable && ((Serializable)tempObject).id == this.id)
        {   return true;
        }   return false;
    }

    public boolean equals(Serializable tempObject)
    {
        if(tempObject.id == this.id){
            return true;
        } else {
            return false;
        }
    }

    protected Serializable(int id) { // initialise instance variable
        Integer tempId;
        if (!mapCounter.containsKey(this.getClass())) {
            tempId = 0;
        } else {
            tempId = mapCounter.get(this.getClass());
            tempId++;
        } this.id = tempId;
        mapCounter.put(this.getClass(), this.id);
    }
}