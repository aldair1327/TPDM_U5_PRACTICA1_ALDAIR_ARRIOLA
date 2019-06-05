package com.example.tpdm_unidad5_practica1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SmsBroadcastReceiver";
    String msg, phoneNo = "";
    ConexionBase base;
    @Override
    public void onReceive(Context context, Intent intent) {
        //retrieves the general action to be performed and display on log
        base=new ConexionBase(context, "BDFrases", null, 1);
        Log.i(TAG, "Intent Received: " +intent.getAction());
        if (intent.getAction()==SMS_RECEIVED)
        {
            Bundle dataBundle = intent.getExtras();
            if (dataBundle!=null)
            {
                Object[] mypdu = (Object[])dataBundle.get("pdus");
                final SmsMessage[] message = new SmsMessage[mypdu.length];

                for (int i = 0; i<mypdu.length; i++)
                {
                    //for build versions >= API Level 23
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    {
                        String format = dataBundle.getString("format");
                        //From PDU we get all object and SmsMessage Object using following line of code
                        message[i] = SmsMessage.createFromPdu((byte[])mypdu[i], format);
                    }
                    else
                    {
                        //<API level 23
                        message[i] = SmsMessage.createFromPdu((byte[])mypdu[i]);
                    }
                    msg = message[i].getMessageBody();
                    phoneNo = message[i].getOriginatingAddress();
                    onSend(context);
                    Intent smsIntent = new Intent(context,MainActivity.class);
                    smsIntent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                    smsIntent.putExtra("number",phoneNo);
                    smsIntent.putExtra("message",msg);
                    context.startActivity(smsIntent);
                }
                //Toast.makeText(context, "Message: " +msg +"\nNumber: " +phoneNo, Toast.LENGTH_LONG).show();
            }
        }
    }
    public void onSend(Context v){
        SmsManager smsManager = SmsManager.getDefault();
        String mensaje = buscaFrase();
        smsManager.sendTextMessage(phoneNo, null, mensaje+"", null, null);
        Toast.makeText(v, mensaje, Toast.LENGTH_SHORT).show();
    }

    public String buscaFrase() {
        try {

            int n = (int)(Math.random()*20)+1;
            SQLiteDatabase base = this.base.getReadableDatabase();
            String []fraseR = {n+""};
            Cursor c = base.rawQuery("SELECT * FROM Frases WHERE frase = ?", fraseR);
            System.out.println(c.getCount());
            if (c.moveToFirst()) {
                return (c.getString(1));
            } else {
                return ("No se encontro ninguna frase");
            }
        } catch (SQLiteException e) {
            return (e.getMessage());
        }
    }
}
