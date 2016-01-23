package com.example.user.pennapps16.http.server;

import android.util.Log;

import com.example.user.pennapps16.CardboardOverlayView;

import fi.iki.elonen.NanoHTTPD;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;

/**
 * Created by prern_000 on 1/22/2016.
 */
public class AppServer extends NanoHTTPD {
        private final static int PORT = 8080;
        private CardboardOverlayView overlayView = null;
        private static final String MESSAGE = "message";
        private int i=0;
        public String message;

        public AppServer(CardboardOverlayView overlayView) throws  IOException {
            super(PORT);
            start();
            System.out.println("\nRunning! Point your browers to http://localhost:8080/ \n");
            this.overlayView = overlayView;
        }

        @Override
        public Response serve(IHTTPSession session) {
            Map<String,String> params = session.getParms();
            message = params.get(MESSAGE);
            if ( message == null ) {
                message = "";
            } else {
                overlayView.show3DToast(message);
            }
            System.out.println("msg: " + message);
            i++;
            return newFixedLengthResponse("<html><body>ABC" + i + "</body></html>");
        }
}
