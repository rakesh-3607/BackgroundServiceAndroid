package com.example.rakeshgondaliya.backgroundsevicesyncapp.model;

/**
 * Implementation of the priority queue.
 *
 * Created by rakeshgondaliya on 16/12/15.
 */
public class PriorityQueue {

    private CorePoint[] heap;
    private int heapSize, capacity;

    /**
     *
     * @param capacity
     */
    public PriorityQueue(int capacity)
    {
        this.capacity = capacity + 1;
        heap = new CorePoint[this.capacity];
        heapSize = 0;
    }

    /**
     *
     */
    public void clear()
    {
        heap = new CorePoint[capacity];
        heapSize = 0;
    }

    /**
     *
     * @return
     */
    public boolean isEmpty()
    {
        return heapSize == 0;
    }

    /**
     *
     * @return
     */
    public boolean isFull()
    {
        return heapSize == capacity - 1;
    }

    /**
     *
     * @return
     */
    public int size()
    {
        return heapSize;
    }


    /**
     *
     * @param pointName
     * @param priority
     * @return
     */
    public CorePoint insert(String pointName, int priority)
    {
        CorePoint newPoint = new CorePoint(pointName, priority);

        heap[++heapSize] = newPoint;
        int pos = heapSize;
        while (pos != 1 && newPoint.priority > heap[pos/2].priority)
        {
            heap[pos] = heap[pos/2];
            pos /=2;
        }
        heap[pos] = newPoint;
        return newPoint;
    }

    /**
     *
     * @return
     */
    public CorePoint remove()
    {
        int parent, child;
        CorePoint item, temp;
        if (isEmpty() )
        {
            System.out.println("Heap is empty");
            return null;
        }

        item = heap[1];
        temp = heap[heapSize--];

        parent = 1;
        child = 2;
        while (child <= heapSize)
        {
            if (child < heapSize && heap[child].priority < heap[child + 1].priority)
                child++;
            if (temp.priority >= heap[child].priority)
                break;

            heap[parent] = heap[child];
            parent = child;
            child *= 2;
        }
        heap[parent] = temp;

        return item;
    }

}
