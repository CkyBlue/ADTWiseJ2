package Utility;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Utilities {
    public static String readRawTextFile(Context ctx, int resId) {
        return readRawTextFile(ctx.getResources(), resId);
    }

    public static String readRawTextFile(Resources resources, int resId) {
        InputStream inputStream = resources.openRawResource(resId);

        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputReader);
        String line;
        StringBuilder text = new StringBuilder();

        try {
            while ((line = bufferedReader.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            return null;
        }
        return text.toString();
    }


    public static ArrayList<Integer> indicesOf(String match, String text) {
        /*Returns an ArrayList of all the indices where a parse to the parse String is found in the text String*/

        ArrayList<Integer> indices = new ArrayList<Integer>();

        for (int i = -1; (i = text.indexOf(match, i + 1)) != -1; i++) {
            indices.add(i);
        }

        return indices;
    }

    public static String padRight(String str, int size, char padChar) {
        if (str.length() < size) {
            char[] temp = new char[size];
            int i = 0;

            while (i < str.length()) {
                temp[i] = str.charAt(i);
                i++;
            }

            while (i < size) {
                temp[i] = padChar;
                i++;
            }

            str = new String(temp);
        }

        return str;
    }

    public static String padLeft(String str, int size, char padChar) {
        if (str.length() < size) {
            char[] temp = new char[size];
            int i = 0;

            while (i < size - str.length()) {
                temp[i] = padChar;
                i++;
            }

            while (i < size) {
                temp[i] = str.charAt(i - (size - str.length()));
                i++;
            }

            str = new String(temp);
        }

        return str;
    }
}
