package com.www.lightmeter;

import java.util.HashMap;
import java.util.Map;

public class Variable {
    double[] keys;
    Map<Double, String> vals = new HashMap<>();
    int currentIndex;

    public Variable(double[] keys, String[] strings) {
        currentIndex = 0;
        this.keys = keys;
        if (strings == null) {
            for (int i = 0, maplen = keys.length; i < maplen; i++) {
                vals.put(keys[i], formatDoubleString("" + keys[i]));
            }
        } else {
            for (int i = 0, maplen = Math.min(keys.length, strings.length); i < maplen; i++) {
                vals.put(keys[i], strings[i]);
            }
        }
    }

    public double getCurrentVal() {
        return keys[currentIndex];
    }

    public double nextVal() {
        currentIndex = Math.min(currentIndex + 1, keys.length);
        return keys[currentIndex];
    }

    public double prevVal() {
        currentIndex = Math.max(currentIndex - 1, 0);
        return keys[currentIndex];
    }

    String valToString(double val) {
        return vals.get(val);
    }

    private String formatDoubleString(String string) {
        String newString = "";
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '.' && string.charAt(i + 1) == '0' && i != string.length() - 1) {
                continue;
            }
            newString += string.charAt(i);
        }
        return newString;
    }
}
