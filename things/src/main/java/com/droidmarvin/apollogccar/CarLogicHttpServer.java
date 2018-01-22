package com.droidmarvin.apollogccar;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.Map;
import fi.iki.elonen.NanoHTTPD;

import static android.content.ContentValues.TAG;

public class CarLogicHttpServer extends NanoHTTPD {

    public static interface CommandListener { public void onCommand(String command);}

    public void CarLogicHttpServer (int port, Context context, CommandListener listener) {
        super(port);

        this.context = context;
        this.listener = listener;

        Log.d(TAG, "Starting Server");

        try {
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public NanoHTTPD.Response serve(NanoHTTPD.IHTTPSession session) {
//        Map<String, String> params = session.getParms();
//        String control = params.get("control");
//        String action = params.get("btn");
//        Log.d(TAG, "Serve - Control ["+control+"] - Action ["+action+"]");
//        if (action != null && !"".equals(action))
//            listener.onCommand(action);
//        return newFixedLengthResponse(readHTMLFile().toString());
//    }

    @Override
    public Response serve(IHTTPSession session) {
        String msg = "<html><body><h1>Hello server</h1>\n";
        Map<String, String> parms = session.getParms();
        if (parms.get("username") == null) {
            msg += "<form action='?' method='get'>\n  <p>Your name: <input type='text' name='username'></p>\n" + "</form>\n";
        } else {
            msg += "<p>Hello, " + parms.get("username") + "!</p>";
        }
        return newFixedLengthResponse(msg + "</body></html>\n");

    }
}
