package github.aqumpusaxy.mana_jade.util;

import java.text.DecimalFormat;

public class NumberFormatter {
    private static final DecimalFormat TWO_DECIMAL_FORMAT = new DecimalFormat("#.##");

    public static String formatDouble(double number) {
        return TWO_DECIMAL_FORMAT.format(number);
    }
}
