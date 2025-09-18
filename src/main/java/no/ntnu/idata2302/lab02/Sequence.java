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
 * Implement the Sequence ADT from Lecture 2.2.
 *
 * <p>
 * The items in the sequence are indexed from 1 (as opposed to Java arrays that
 * are indexed from 0)
 * </p>
 */
public class Sequence {

  private static final int INITIAL_CAPACITY = 100;

  private int capacity;
  private int length;
  private int[] items;

  public Sequence() {
    this(INITIAL_CAPACITY, new int[] {});
  }

  public Sequence(int capacity, int[] items) {
    if (capacity < items.length) {
      throw new IllegalArgumentException("Capacity must be greater than item count");
    }
    this.capacity = capacity;
    this.length = items.length;
    this.items = new int[capacity];
    for (int i = 0; i < items.length; i++) {
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
   * <p>
   * Shifts original item at index, and all consecutive items,
   * one step to the right.
   * </p>
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
    // if (length == capacity) {
    // int newCapacity = capacity * 2;
    // int[] newItems = new int[newCapacity];
    // for (int i = 0; i < length; i++) {
    // newItems[i] = items[i];
    // }
    // items = newItems;
    // capacity = newCapacity;
    // }
    // Styvian Alternative
    if (length == capacity) {
      int newCapacity = capacity * 2;
      items = java.util.Arrays.copyOf(items, newCapacity);
      capacity = newCapacity;
    }

    // Move all items beyond (inclusive) the index one step to the right
    for (int i = length; i >= index; i--) {
      items[i] = items[i - 1];
    }
    // Insert the new value
    items[index - 1] = item;

    // Increment the length
    length++;
  }

  /**
   * Removes the value at the given index and shifts
   * the rest of the sequence accordingly.
   *
   * @param index the value that must be removed.
   */
  public void remove(int index) {
    // Check parameters
    if (index < 1 || index > length) {
      throw new IllegalArgumentException("Invalid index!");
    }

    // Shift items left from index
    for (int i = index - 1; i < length - 1; i++) {
      items[i] = items[i + 1];
    }

    // Decrement the length
    length--;

    // Clear the last item
    items[length] = 0;

    // Halve the capacity if the length is <= 25% of the current capacity
    if (length > 0 && length <= capacity / 4) {
      int newCapacity = capacity / 2;
      items = Arrays.copyOf(items, newCapacity);
      capacity = newCapacity;
    }
  }

  /**
   * Find an index where the given item can be found. Returns 0 if that item
   * cannot
   * be found.
   *
   * @param item the item whose index must be found
   * @return the (int) index of the item, if it is found, or 0 if not.
   */
  public int search(int item) {
    int i = 1;
    while (i <= items.length) {
      if (items[i - 1] == item) {
        return i;
      }
      i++;
    }
    return 0;
  }

  /**
   * Find both the smallest and the largest items in the sequence.
   *
   * @return an array of length two where the first entry is the minimum and the
   *         second the maximum
   */
  public int[] extrema() {
    if (length == 0) {
      throw new IllegalStateException("No elements in sequence");
    }

    int[] extremaValues = { Integer.MAX_VALUE, Integer.MIN_VALUE };

    for (int i = 0; i < length; i++) {
      int item = items[i];
      if (item < extremaValues[0])
        extremaValues[0] = item;
      if (item > extremaValues[1])
        extremaValues[1] = item;
    }
    return extremaValues;
  }

  /**
   * Check whether the given sequence contains any duplicate item
   *
   * @return true if the sequence has the the same items at multiple indices
   */
  public boolean hasDuplicate() {
    for (int i = 0; i < length; i++) {
      for (int j = i + 1; j < length; j++) {
        if (items[i] == items[j]) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Convert the sequence into an Java array
   */
  public int[] toArray() {
    return items;
  }

}
