package com.www.lightmeter;

import android.util.Log;

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
                Log.e("www", "making new variable. keys[i] : " + keys[i] +  "Double: " + Double.valueOf(keys[i]));
                vals.put(Double.valueOf(keys[i]), formatDoubleString("" + keys[i]));
            }
        } else {
            for (int i = 0, maplen = Math.min(keys.length, strings.length); i < maplen; i++) {
                vals.put(Double.valueOf(keys[i]), strings[i]);
            }
        }
    }

    public double getCurrentVal() {
        return keys[currentIndex];
    }

    public String getCurrentValAsString() {
        return valToString(keys[currentIndex]);
    }

    public double nextVal() {
        currentIndex = Math.min(currentIndex + 1, keys.length - 1);
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
            if (i < string.length() - 1 && string.charAt(i) == '.' && string.charAt(i + 1) == '0') {
                i++;
                continue;
            }
            newString += string.charAt(i);
        }
        return newString;
    }

    double setQuantizedValue(double actual) {
        double correctVal = -1;
        for (int i = 0; i < keys.length; i++) {
            if (actual > keys[i]) {
                continue;
            } else {
                correctVal = keys[i];
                currentIndex = i;
                break;
            }
        }
        return correctVal;
    }
}
