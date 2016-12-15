package trinadhkoya.gihub.io.gettingstartedfcm_chat;

import android.util.Log;

/**
 * Created by trina on 12/15/2016.
 */

public class Logs {

    private static final int NONE = 0;
    private static final int ERROR_WARNINGS = 1;
    private static final int DEBUG_INFO = 2;
    private static final int CURRENT_LOG_LEVEL = 2;


    public static void e(String TAG, String MESSAGE) {
        if (CURRENT_LOG_LEVEL >= 1) {
            Log.e(TAG, MESSAGE);
        }
    }

    public static void i(String TAG, String MESSAGE) {
        if (CURRENT_LOG_LEVEL >= 2) {

            Log.i(TAG, MESSAGE);
        }
    }

}
