package com.d2dautowash.smsaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by parthvora on 05/01/18.
 */

public class SMSReceiver extends BroadcastReceiver {

    private static final String LOG_TAG = "SMSReceiver";
    public SMSReceiver(){
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(LOG_TAG,"Incoming SMS Received");
        Bundle bundle = intent.getExtras();

        Object messages[] = (Object[]) bundle.get("pdus");
        SmsMessage smsMessage[] = new SmsMessage[messages.length];
        for (int n = 0; n < messages.length; n++) {
            smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
        }

        // show first message
        final String body = smsMessage[0].getMessageBody();
        final String sender = smsMessage[0].getOriginatingAddress().replaceAll("\\+","");

        boolean sendCallback = false;
        String callbackUrl = null;
        for (Map.Entry<String, String> entry : Config.SMS_RULES.entrySet()) {
            String key = entry.getKey();
            if(body.startsWith(key)){
                sendCallback = true;
                callbackUrl = entry.getValue();
                break;
            }
        }

        if(sendCallback){
            try{
                Toast.makeText(context, "Sending Callback for SMS: " + smsMessage[0].getMessageBody(), Toast.LENGTH_SHORT).show();
                String queryParams = "mobile=" + URLEncoder.encode(sender, "UTF-8") + "&msg="+URLEncoder.encode(body, "UTF-8");
                new RestClientGetCall(callbackUrl, queryParams).execute();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
