package com.example.rakeshgondaliya.backgroundsevicesyncapp.model;

/**
 * Created by rakeshgondaliya on 16/12/15.
 */
public class CorePoint {

    String pointName;
    int priority;

    /** Constructor **/
    public CorePoint(String pointName, int priority)
    {
        this.pointName = pointName;
        this.priority = priority;
    }

    public String getPointName() {
        return pointName;
    }

    public int getPriority() {
        return priority;
    }

    /** toString() **/
    public String toString()
    {
        return "Point Name : "+ pointName +"\nPriority : "+ priority;
    }

}
