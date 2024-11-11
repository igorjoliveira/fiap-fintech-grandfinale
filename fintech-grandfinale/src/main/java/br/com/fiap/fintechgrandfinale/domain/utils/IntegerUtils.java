package br.com.fiap.fintechgrandfinale.domain.utils;

public class IntegerUtils {
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int tryParseInt(String str) {
        return !isInteger(str) ? 0 : Integer.parseInt(str);
    }
}
