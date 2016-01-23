package com.example.user.pennapps16.http.server;

import fi.iki.elonen.NanoHTTPD;
import java.io.IOException;
import java.util.Map;

/**
 * Created by prern_000 on 1/22/2016.
 */
public class AppServer extends NanoHTTPD {
        private final static int PORT = 8080;

        public AppServer() throws  IOException {
            super(PORT);
            start();
            System.out.println( "\nRunning! Point your browers to http://localhost:8080/ \n" );
        }

        @Override
        public Response serve(IHTTPSession session) {
            Map<String,String> params = session.getParms();
            String msg = "<html><body><h1>Server</h1>\n";
            msg += "<p>We serve " + session.getUri() + " !<br/>";
            for ( Map.Entry<String,String> entry: params.entrySet()){
                msg += entry.getKey() + "----->" + entry.getValue() + "<br/>";
            }
            return newFixedLengthResponse(msg + "</p></body></html>\n" );
        }
}
