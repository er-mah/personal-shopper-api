package com.mah.personalshopper.model.constants;

import java.util.HashMap;

public class PriceConstants {

    public static final Double rangeVariation = 0.05;
    public static final Double startingPercentageDiscount = -0.25;
    public static final HashMap<String, Double> kilometers = new HashMap<>();

    static {
        kilometers.put("INTENSIVE_USE", -0.05);     // Intensive use: More than 150.000 kms made
        kilometers.put("AVERAGE_USE", 0.00);        // Average use: Between 100.000 and 150.000 kms made
        kilometers.put("MINIMUM_USE", 0.05);        // Minimum use: Less than 100.000 kms made
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

    static  {
        colour.put("EXOTIC", -0.03);            // Other
        colour.put("RARE", 0.00);               // Blue, brown, beige
        colour.put("COMMON", 0.03);             // Gray, silver, black, red
        colour.put("MOST_COMMON", 0.05);        // White
    }



}