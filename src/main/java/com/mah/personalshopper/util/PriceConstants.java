package com.mah.personalshopper.util;

import java.util.HashMap;

public class PriceConstants {

    public static final Double rangeVariation = 0.05;
    public static final Double startingPercentageDiscount = -0.25;
    public static final Integer roundValue = 1000;


    public static final HashMap<String, Double> kilometers = new HashMap<>();

    static {
        kilometers.put("+200k", -0.05);             // More than 200.000 kms made
        kilometers.put("150k-200k", -0.03);         // Between 150.000 and 200.000 kms made
        kilometers.put("100k-150k", 0.00);          // Between 100.000 and 150.000 kms made
        kilometers.put("50k-100k", 0.03);           // Between 50.000 and 100.000 kms made
        kilometers.put("-50k", 0.05);               // Less than 50.000 kms made
    }

    public static final HashMap<String, Double> availableSellingTime = new HashMap<>();

    static {
        availableSellingTime.put("ONE_WEEK", -0.05);
        availableSellingTime.put("TWO_WEEKS", -0.03);
        availableSellingTime.put("THREE_WEEKS", 0.00);
        availableSellingTime.put("FOUR_WEEKS", 0.03);
        availableSellingTime.put("NO_HURRY", 0.05);
    }

    public static final HashMap<String, Double> state = new HashMap<>();

    static {
        state.put("MANY_DETAILS", -0.05);
        state.put("FEW_DETAILS", -0.03);
        state.put("GOOD", 0.00);
        state.put("VERY_GOOD", 0.03);
        state.put("EXCELLENT", 0.05);
    }

    public static final HashMap<String, Double> colour = new HashMap<>();

    static {
        colour.put("EXOTIC", -0.03);            // Other
        colour.put("RARE", 0.00);               // Blue, brown, beige
        colour.put("COMMON", 0.03);             // Gray, silver, black, red
        colour.put("MOST_COMMON", 0.05);        // White
    }

    public static final HashMap<String, Double> age = new HashMap<>();

    static {
        age.put(">15", -0.05);
        age.put("10-15", -0.03);
        age.put("6-9", 0.00);
        age.put("3-5", 0.01);
        age.put("0-2", 0.02);
    }

}