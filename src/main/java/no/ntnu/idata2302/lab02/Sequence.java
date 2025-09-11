/*
 * This file is part of NTNU's IDATA2302 Lab02.
 *
 * Copyright (C) NTNU 2022
 * All rights reserved.
 *
 */
package no.ntnu.idata2302.lab02;

import java.util.Arrays;

/**
 * Implement the Sequence ADT from Lecture 2.2
 *
 * The items in the sequence are indexed from 1 (as opposed to Java arrays that
 * are indexed from 0)
 */
public class Sequence {

    private static final int INITIAL_CAPACITY = 100;

    private int capacity;
    private int length;
    private int[] items;

    public Sequence() {
        this(INITIAL_CAPACITY, new int[]{});
    }

    public Sequence(int capacity, int[] items) {
        if (capacity < items.length) {
            throw new IllegalArgumentException("Capacity must be greater than item count");
        }
        this.capacity = capacity;
        this.length = items.length;
        this.items = new int[capacity];
        for (int i=0 ; i<items.length ; i++) {
            this.items[i] = items[i];
        }
    }

    /**
     * @return The number of items in the sequence
     */
    public int getLength() {
        return this.length;
    }

    /**
     * @return the number of "buckets" currently allocated
     */
    int getCapacity() {
        return this.capacity;
    }

    /**
     * Return the item stored at the given index
     *
     * @param index the index of the desired item, starting at 1
     * @return the item at the given index.
     */
    public int get(int index) {
        if (index < 1 || index > length) {
            throw new IllegalArgumentException("Invalid index!");
        }
        return this.items[index - 1];
    }

    /**
     * Inserts an item at a given index.
     *
     * <p>Shifts original item at index, and all consecutive items,
     * one step to the right.</p>
     *
     * @param item the item that must be inserted
     */
    public void insert(int item, int index) {
      // Check parameters
      // Index out of bounds
      if (index < 1 || index > length + 1) {
          throw new IllegalArgumentException("Invalid index!");
      }

      // Resize if needed
      //if (length == capacity) {
      //    int newCapacity = capacity * 2;
      //    int[] newItems = new int[newCapacity];
      //    for (int i = 0; i < length; i++) {
      //        newItems[i] = items[i];
      //    }
      //    items = newItems;
      //    capacity = newCapacity;
      //}
      // Styvian Alternative
      if (length == capacity) {
        int newCapacity = capacity * 2;
        items = java.util.Arrays.copyOf(items, newCapacity);
      }

      // Move all items beyond (inclusive) the index one step right
      for (int i = length; i >= index; i--) {
          items[i] = items[i - 1];
      }
      // Insert the new value
      items[index - 1] = item;

      // Increment the length
      length++;
    }

    /**
     * Removes the index at the given index
     *
     * @param index the index that must be removed.
     */
    public void remove(int index) {
      // Check parameters
      // Index out of bounds
      if (index < 1 || index > length + 1) {
        throw new IllegalArgumentException("Invalid index!");
      }

      // Halve the capacity if the length of the array
      // is <= 25% of the current capacity.
      if (length <= capacity / 4 ) {
        int newCapacity = (capacity / 2);
        items = Arrays.copyOf(items, newCapacity);
        capacity = newCapacity;
      }

      // Move all items beyond the index (exclusive) one step left,
      // overwriting the initial value
      for(int i = length-1; i >= index; i--) {
        items[i] = items[i+1];
      }
      // Clear the last item
      items[length] = 0;

      // Decrement the length
      length--;
    }

    /**
     * Find a index where the given item can be found. Returns 0 if that item cannot
     * be found.
     *
     * @param item the item whose index must be found
     * @return an
     */
    public int search(int item) {
        // TODO: Implement
        throw new RuntimeException("Not yet implemented.");
    }

    /**
     * Find both the smallest and the largest items in the sequence.
     *
     * @return an array of length two where the first entry is the minimum and the
     *         second the maximum
     */
    public int[] extrema() {
        // TODO: Implement
        throw new RuntimeException("Not yet implemented.");
    }

    /**
     * Check whether the given sequence contains any duplicate item
     *
     * @return true if the sequence has the the same items at multiple indices
     */
    public boolean hasDuplicate() {
        // TODO: Implement
        throw new RuntimeException("Not yet implemented.");
    }


    /**
     * Convert the sequence into an Java array
     */
    public int[] toArray() {
        return items;
    }

}
