package com.samuel.dom.samueland.Interfaces;


/**
 * This is a useful callback mechanism so we can abstract our AsyncTasks out into separate, re-usable
 * and testable classes yet still retain a hook back into the calling activity. Basically, it'll make classes
 * cleaner and easier to unit test.
 *
 * If you’re an android developer, chances are you’ve used an Async task more than once. As your apps develop, grow, and become more complex,
 * theres a high chance you’re going to have multiple Async tasks. In an application I’ve recently been developing, I had around 5 Async tasks
 * for a single activity, which made the actual activity quite difficult to read.

 Adhering to KIS and DRY, I wanted to devise a mechanism for extracting the logic of these Async tasks out into separate external classes,
 thus reducing the clutter of inner classes in my activities, and also meaning that I could re-use these Async tasks elsewhere, and also externalising them makes them easier to mock and unit test.

 The easiest mechanism I’ve found, is to use generics and provide a callback, which is somewhat similar to the delegate/protocol pattern in iOS programming.

 Firstly, create a class with a generic method, like follows :
 *
 * @param <T>
 */
public interface AsyncTaskCompleteListener<T>
{
    /**
     * Invoked when the AsyncTask has completed its execution.
     * @param result The resulting object from the AsyncTask.
     */
    public void onTaskComplete(T result);
}