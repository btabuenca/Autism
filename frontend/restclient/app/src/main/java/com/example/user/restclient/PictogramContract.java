/**
 * Author: Bernardo Tabuenca
 *
 */

package com.example.user.restclient;

import android.provider.BaseColumns;


public class PictogramContract {

    private PictogramContract(){}

    public static abstract class tablePictograms implements BaseColumns {
        public static final String TABLE_NAME = "pictogram";

        public static String COL_PICTOGRAM_ID = _ID;
        public static String COL_PICTOGRAM_NAME = "pictogram_name";
        public static String COL_PICTOGRAM_DESCRIPTION = "pictogram_description";
        public static String COL_PICTOGRAM_CATEGORYNAME = "category_name";
        public static String COL_PICTOGRAM_IMG = "pictogram_img";
        public static String COL_PICTOGRAM_SOUND = "pictogram_sound";


    }
}
