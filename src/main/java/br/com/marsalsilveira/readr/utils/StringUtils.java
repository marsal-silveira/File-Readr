package br.com.marsalsilveira.readr.utils;

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

//    public static String[] parseStringToStringArray(String str) {
//
//        if (isEmpty(str)) {
//            return new String[]{};
//        }
//
//        str = str.trim().replaceAll("\\s+", " ").toLowerCase();
//
//        return str.split(" ");
//    }
//
//    public static String formatString(String string) {
//
//        if (string == null) {
//
//            return null;
//        }
//
//        return string.trim().replaceAll("\\s+", " ").toLowerCase();
//
//    }
//
//    public static String joinArgsToString(String delimiter, String[] args, int from, int to) {
//
//        if (delimiter == null || args == null || from >= to || from > args.length || to > args.length || from < 0 || to < 0) {
//            return "";
//        }
//
//        return String.join(delimiter, Arrays.copyOfRange(args, from, to));
//
//    }
//
//    public static String removeSpecialCharacters(String str) {
//
//        if (str == null) {
//            return null;
//        }
//
//        str = Normalizer.normalize(str, Normalizer.Form.NFD);
//        str =  str.replaceAll("[^\\p{ASCII}]", "");
//
//        return str.replaceAll("[^a-zA-Z0-9\\s+]", "");
//    }
}
