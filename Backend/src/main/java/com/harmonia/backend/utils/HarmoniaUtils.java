package com.harmonia.backend.utils;

public class HarmoniaUtils {

    public static boolean arrayMatchesString(String[] array, String value) {
        for (String arrayRecord : array) {
            if (matchPattern(arrayRecord, value)) {
                return true;
            }
        }
        return false;
    }

    public static boolean matchPattern(String pattern, String str) {

        // If we reach at the end of both strings, we are done
        if (pattern.length() == 0 && str.length() == 0) return true;

        // Make sure that the characters after '*' are present in str string. This function assumes that
        // the pattern string will not contain two consecutive '*'
        if (pattern.length() > 1 && pattern.charAt(0) == '*' && str.length() == 0) return false;

        // If the pattern string contains '?', or current characters of both strings match
        if ((pattern.length() > 1 && pattern.charAt(0) == '?') || (pattern.length() != 0 && str.length() != 0 && pattern.charAt(0) == str.charAt(0)))
            return matchPattern(pattern.substring(1), str.substring(1));

        // If there is *, then there are two possibilities
        // a: We consider current character of str string
        // b: We ignore current character of str string.
        if (pattern.length() > 0 && pattern.charAt(0) == '*')
            return matchPattern(pattern.substring(1), str) || matchPattern(pattern, str.substring(1));
        return false;
    }
}
