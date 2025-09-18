/*
 * This file is part of NTNU's IDATA2302 Lab02.
 *
 * Copyright (C) NTNU 2022
 * All rights reserved.
 *
 */
package no.ntnu.idata2302.lab02;

public class Counter {

  public static void main(String[] args) {
    var counter = Counter.decimal(5);
    for (int i = 0; i < 50; i++) {
      System.out.println(counter.value());
      counter.increment();
    }
  }

  /**
   * Create a new counter for decimal numbers (i.e., base 10)
   *
   * @param digitCount the number of digits to include in the counter
   * @return a new Counter object
   */
  public static Counter decimal(int digitCount) {
    return withAlphabet("0123456789", digitCount);
  }

  /**
   * Create a new counter for binary numbers (i.e., base 2)
   *
   * @param digitCount the number of digits in to include in the counter
   * @return a new counter object
   */
  public static Counter binary(int digitCount) {
    return withAlphabet("01", digitCount);
  }

  /**
   * Create a counter of a specific length with a specific alphabet
   *
   * @param alphabet   the set of symbols used by the counter
   * @param digitCount the maximum number of digits
   */
  private static Counter withAlphabet(String alphabet, int digitCount) {
    if (digitCount <= 0)
      throw new IllegalArgumentException("The number of digits must be positive");
    assert alphabet != null && !alphabet.isEmpty() : "null or '' are not a valid alphabet";

    var digits = new DigitDisplay[digitCount];
    for (int i = 0; i < digitCount; i++) {
      digits[i] = new DigitDisplay(alphabet);
    }
    return new Counter(digits);
  }

  private final DigitDisplay digits[];

  Counter(DigitDisplay[] digits) {
    this.digits = digits;
  }

  /**
   * Increment the counter by one
   */
  public void increment() {
    boolean carryOver = true;
    int index = 0;

    while (carryOver && index < digits.length) {
      digits[index].next();
      carryOver = digits[index].isZero();

      index++;
    }
  }

  /**
   * @return the current value of the counter
   */
  public String value() {
    var buffer = new StringBuilder();
    for (int i = digits.length - 1; i >= 0; i--) {
      buffer.append(digits[i].symbol());
    }
    return buffer.toString();
  }
}


/**
 * Represent a single-digit display, on a given alphabet
 */
class DigitDisplay {

    private final String alphabet;
    private int index;

    DigitDisplay(String alphabet) {
        this(alphabet, 0);
    }

    DigitDisplay(String alphabet, int initialSymbol) {
        this.alphabet = alphabet;
        this.index = initialSymbol;
    }

    /**
     * Increment the display to the next symbol. Loop back to the first symbol if
     * needed.
     */
    void next() {
        index = (index + 1) % alphabet.length();
    }

    /**
     * Reset the display to the "zero" symbol
     */
    void reset() {
        index = 0;
    }

    /**
     * @return the symbol currently shown by this display
     */
    char symbol() {
        return alphabet.charAt(index);
    }

    /**
     * @return true if the display currently shows the "zero" symbol
     */
    boolean isZero() {
        return index == 0;
    }
}
