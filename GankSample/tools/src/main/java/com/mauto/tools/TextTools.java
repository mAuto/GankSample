package com.mauto.tools;


/**
 * Created by haohuidong on 18-9-25.
 */

public class TextTools {
    /**
     * Returns true if the string is null or 0-length.
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }
}
