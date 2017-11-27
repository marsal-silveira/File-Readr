package br.com.marsalsilveira.readr.utils;

/**
 *
 */
public final class StringUtils {

    // Avoid init this class...
    private StringUtils() {}

    /**
     * Check if a String has a valid value (isn't null and it's value is not blank)
     *
     * @param str string to be validate
     * @return true if [str] has no value
     */
    public static boolean isEmpty(String str) {

        return str == null || str.trim().isEmpty();
    }

    /**
     * Extract all words from `str` param and return a String[] with them
     *
     * @param str to be parsed
     * @return String[] to all words
     */
    public static String[] toArray(String str) {

        if (StringUtils.isEmpty(str)) {

            return new String[]{};
        }

        str = str.trim().replaceAll("\\s+", " ");

        return str.split(" ");
    }
}