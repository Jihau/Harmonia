package com.harmonia.backend.utils;

import com.harmonia.backend.constants.HarmoniaConstants;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * A class to represent a HarmoniaUtils.
 * This class contains utility methods for the Harmonia application.
 *
 * @version 2.0
 *
 * @author Harmonia Team.
 */

public class HarmoniaUtils {

    /**
     * Returns true if the given array contains the given value.
     *
     * @param array the array to check
     * @param value the value to check for
     * @return true if the given array contains the given value
     */
    public static boolean arrayMatchesString(String[] array, String value) {
        for (String arrayRecord : array) {
            if (matchPattern(arrayRecord, value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the given pattern matches the given string.
     *
     * @param pattern the pattern to check
     * @param str the string to check
     * @return true if the given pattern matches the given string
     */

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

    /**
     * Generates a key for the back end.
     * @return the generated key.
     */
    public static String generateBackEndKey() {
        return HarmoniaConstants.KEY_FOR_HARMONIA_BACK_END = BCrypt.hashpw(HarmoniaConstants.KEYWORD_GENERATE_KEY, BCrypt.gensalt());
    }
}
