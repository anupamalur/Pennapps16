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
        private static final String SLOW_DOWN = "Woaah! Please slow down";
        private static final String BETTER = "Better";
        private String previousMessage = "";

        public AppServer(CardboardOverlayView overlayView) throws  IOException {
            super(PORT);
            start();
            System.out.println("\nRunning! Point your browers to http://localhost:8080/ \n");
            this.overlayView = overlayView;
        }

        @Override
        public Response serve(IHTTPSession session) {
            Map<String,String> params = session.getParms();
            //System.out.println(params);
            //for ( Map.Entry<String,String> pm : params.entrySet()){
            //   System.out.print(pm.getKey() + "------>" + pm.getValue());
            //}
            String message = params.get(MESSAGE);
            System.out.println("msg: " + message);
            System.out.println("previousMessage: " + previousMessage);
            if ( message == null ) {
                message = "";
            } else {
                if (!previousMessage.equals(message)) {
                    if (previousMessage.equals(SLOW_DOWN))
                        message = "Better";
                    overlayView.show3DToast(message);
                    if ( message.equals(SLOW_DOWN) || message.equals(BETTER)){
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    previousMessage = message;
                }
            }
            return newFixedLengthResponse("<html><body>Result</body></html>");
        }
}
