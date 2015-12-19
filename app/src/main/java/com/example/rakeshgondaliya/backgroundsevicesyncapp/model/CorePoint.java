package com.example.rakeshgondaliya.backgroundsevicesyncapp.model;

/**
 * Model Class for Point.
 * Future REST API model will be replciated here and passed to type safe REST client.
 *
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
