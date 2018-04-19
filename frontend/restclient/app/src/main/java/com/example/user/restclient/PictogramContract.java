package com.example.user.restclient;

import android.provider.BaseColumns;

/**
 * Created by User on 28/10/2017.
 */

public class PictogramContract {

    private PictogramContract(){}

    public static abstract class tablaPlayer implements BaseColumns {
        public static final String TABLE_NAME = "player";

        public static String COL_NAME_ID = _ID;
        public static String COL_NAME_IMAGE_PATH = "image_path";
        public static String COL_NAME_NATIONALITY = "nationality";
        public static String COL_NAME_COMMON_NAME = "common_name";
        public static String COL_NAME_DETAILS = "details";

    }
}
