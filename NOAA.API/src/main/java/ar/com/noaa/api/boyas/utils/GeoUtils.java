package ar.com.noaa.api.boyas.utils;

import java.math.*;

public class GeoUtils {
    // Coordenadas decimales
    // La latitud y longitud son números decimales con las siguientes
    // características:
    // - Latitud entre 0° y 90 °: Hemisferio Norte,
    // - Latitud entre 0° y -90°: Hemisferio Sur,
    // - Longitud entre 0° y 180°: Al este del meridiano de Greenwich,
    // - Longitud entre 0° y -180°: Al oeste del meridiano de Greenwich.

    public static boolean rangoPlanetario(Double latitud, Double longitud) {
        if (Math.abs(latitud) > 90) {
            return false;
        } else if (Math.abs(longitud) > 180) {
            return false;
        } else {
            return true;
        }
    }

    public static String chequearHemisferio(Double latitud) {
        if (latitud >= 0 && latitud <= 90) {
            return "NORTE";
        } else if (latitud > 90) {
            return "OUT OF THIS WORLD";
        } else

            return "SUR";
    }

}