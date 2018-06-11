/**
 * Author: Bernardo Tabuenca
 *
 */
package com.example.user.appinstance;

public class Comunicator {
    private static Pictogram obj;

    public static void setObjeto(Pictogram newObjeto) {
        obj = newObjeto;
    }

    public static Pictogram getObjeto() {
        return obj;
    }
}

