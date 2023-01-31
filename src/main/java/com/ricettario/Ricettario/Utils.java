package com.ricettario.Ricettario;

public class Utils {
    public static String sanitizeString(String dirtyString) {
        return dirtyString.replaceAll("\\n", "").replaceAll("\\r", "").replaceAll("\u00A0", "").trim();
    }
}
