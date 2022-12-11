package com.CeciliaInezRevaJSleepRJ;

/**
 * A thread object that extends the `Thread` class and overrides the `run()`
 * method to print the current thread's name and ID to the console.
 *
 * @author Cecilia Inez Reva
 */
public class ThreadingObject extends Thread{
    /**
     * Constructs a new `ThreadingObject` with the specified name.
     *
     * @param name the name of the thread
     */
    public ThreadingObject(String name) {
        super(name);
    }

    /**
     * Overrides the `run()` method of the `Thread` class to print the current
     * thread's name and ID to the console.
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ " is running");
        System.out.println("Id Thread "+ Thread.currentThread().getId());
    }
}
