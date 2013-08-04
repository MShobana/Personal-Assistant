package com.example.personalAssistant;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: shobana
 * Date: 8/4/13
 * Time: 2:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class SmsReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "";
        if (bundle != null)
        {
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            log("pdus length",String.valueOf(pdus.length));
            for (int i=0; i<msgs.length; i++){

                SmsMessage wholeMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
                String msgpart = wholeMessage.getMessageBody().toString();

                log("msg "+(i+1),msgpart);

                String spamtext="good morning";

                log("time",String.valueOf(wholeMessage.getTimestampMillis()));

                if(msgpart.contains(spamtext))
                {
                    Uri smsUri= Uri.parse("content://sms");
                    log("time",String.valueOf(wholeMessage.getTimestampMillis()));
                    context.getContentResolver()
                           .delete(smsUri, "address=? and date_sent=?"
                                   , new String[]{wholeMessage.getDisplayOriginatingAddress(), String.valueOf(wholeMessage.getTimestampMillis())}) ;
                }
            }

        }
    }

    private void log(String log,String msg) {
        Log.wtf(log, msg);
    }
}
