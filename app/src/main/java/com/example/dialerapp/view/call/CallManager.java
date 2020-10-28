package com.example.dialerapp.view.call;

import android.annotation.TargetApi;
import android.os.Build;
import android.telecom.Call;
import android.telecom.VideoProfile;

@TargetApi(Build.VERSION_CODES.M)
public class CallManager {

    // Variables
    public static Call sCall;

    // -- Call Actions -- //

    /**
     * Call a given number
     *
     * @param activity activity
     * @param number   number to call to
     */
    /*public static void call(@NotNull Activity activity, @NotNull String number) {
        if (PermissionUtils.checkDefaultDialer(activity)) {
            try {
                // initiate variables
                TelecomManager telecomManager = (TelecomManager) activity.getSystemService(Context.TELECOM_SERVICE);
                assert telecomManager != null;
                List<PhoneAccountHandle> phoneAccountHandleList = telecomManager.getCallCapablePhoneAccounts();
                int simCard = getSimSelection(activity);

                // create call intent
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + Uri.encode(number)));

                // add sim selection to call intent
                if (phoneAccountHandleList != null && !phoneAccountHandleList.isEmpty())
                    callIntent.putExtra(TelecomManager.EXTRA_PHONE_ACCOUNT_HANDLE, phoneAccountHandleList.get(simCard));

                // handle sim card selection
                Timber.d("simcard index %s", simCard);
                if (simCard != -1) callIntent.putExtra("com.android.phone.extra.slot", simCard);

                // start the call
                activity.startActivity(callIntent);

            } catch (SecurityException e) {
                e.printStackTrace();
                Toast.makeText(activity, "Couldn't make a call due to security reasons", Toast.LENGTH_LONG).show();
            } catch (NullPointerException e) {
                e.printStackTrace();
                Toast.makeText(activity, "Couldnt make a call, no phone number", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(activity, "Set koler as the default dialer to make calls", Toast.LENGTH_LONG).show();
        }
    }*/



    /**
     * Answers incoming call
     */
    public static void answer() {
        if (sCall != null) sCall.answer(VideoProfile.STATE_AUDIO_ONLY);
    }

    /**
     * Ends call
     * If call ended from the other side, disconnects
     */
    public static void reject() {
        if (sCall != null) {
            if (sCall.getState() == Call.STATE_RINGING) sCall.reject(false, null);
            else sCall.disconnect();
        }
    }

    /**
     * Add a call to the current call
     *
     * @param call to other call to add to the current one
     */
    public static void addCall(Call call) {
        if (sCall != null) sCall.conference(call);
    }

    /**
     * Registers a Callback object to the current call
     *
     * @param callback the callback to register
     */
    public static void registerCallback(CallActivity.Callback callback) {
        if (sCall == null) return;
        sCall.registerCallback(callback);
    }

    /**
     * Unregisters the Callback from the current call
     *
     * @param callback the callback to unregister
     */
    public static void unregisterCallback(Call.Callback callback) {
        if (sCall == null) return;
        sCall.unregisterCallback(callback);
    }
}
