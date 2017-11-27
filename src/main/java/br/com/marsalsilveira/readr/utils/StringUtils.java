package br.com.marsalsilveira.readr.utils;

/**
 * String utils class.
 */
public final class StringUtils {

    // Avoid to create it
    private StringUtils() {}

    //******************************************************************************************************************
    //* Utils
    //******************************************************************************************************************

    /**
     * Check if the given string is null or empty.
     *
     * @param str The string to be checked.
     *
     * @return true if the string is null or empty. False otherwise.
     */
    public static boolean isEmpty(String str) {

        return (str == null) || (str.trim().isEmpty());
    }

    /**
     * Check if the given string is not null and not empty.
     * It'j just a wrapper to <code>!StringUtils.isEmpty(str)</code>.
     *
     * @param str The string to be checked.
     *
     * @return true if the string is not null and not empty. False otherwise.
     */
    public static boolean isNotEmpty(String str) {

        return !StringUtils.isEmpty(str);
    }
}