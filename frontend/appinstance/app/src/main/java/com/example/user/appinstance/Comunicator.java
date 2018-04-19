package com.example.user.appinstance;

/**
 * Created by User on 03/11/2017.
 */

public class Comunicator {
    private static Pictogram obj;

    public static void setObjeto(Pictogram newObjeto) {
        obj = newObjeto;
    }

    public static Pictogram getObjeto() {
        return obj;
    }
}

