package com.kovlev.iapws_if97;

import android.util.SparseArray;

import java.text.DecimalFormat;
import java.util.HashMap;

public class Utils {
    private static HashMap<Integer, DecimalFormat> formatCache = new HashMap<>();

    public static DecimalFormat createFormatter(int digits) {
        // Setting a reasonable limit
        digits = digits > 12 ? 12 : digits;
        if (!formatCache.containsKey(digits)) {
            StringBuilder sb = new StringBuilder();
            sb.append("#.");
            for (int i = 0; i < digits; i++) {
                sb.append("#");
            }
            formatCache.put(digits, new DecimalFormat(sb.toString()));
        }
        return formatCache.get(digits);
    }
}
