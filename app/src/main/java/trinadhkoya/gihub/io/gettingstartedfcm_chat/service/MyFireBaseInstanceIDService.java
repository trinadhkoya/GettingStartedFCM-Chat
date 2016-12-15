package trinadhkoya.gihub.io.gettingstartedfcm_chat.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by trina on 12/15/2016.
 */



public class MyFireBaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG=MyFireBaseInstanceIDService.class.getName();

    @Override
    public void onTokenRefresh() {
            super.onTokenRefresh();
        String refreshedToken= FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"Refreshed Token is "+refreshedToken);

        //TODO:send it your server or where ever you need
        sendRegistrationTokenToServer(refreshedToken);

    }

    private void sendRegistrationTokenToServer(String refreshedToken) {
        //TODO:

    }
}
