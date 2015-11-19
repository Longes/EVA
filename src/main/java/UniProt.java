package com.rostlab.eva;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

/**
 * Created by Longes on 15.11.2015.
 */
public class UniProt {
    private static String SERVER = "http://www.uniprot.org/";
    private static final Logger LOG = Logger.getAnonymousLogger();
    private String log;
    private String server;

    public static void getProtein(String id, String format) throws IOException, InterruptedException {
        StringBuilder requestBuilder = new StringBuilder(SERVER + id + "." + format);
        String request = requestBuilder.toString();
        URL url = new URL(request);
        LOG.info("Submitting protein request");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        HttpURLConnection.setFollowRedirects(true);
        conn.setDoInput(true);
        conn.connect();

        int status = conn.getResponseCode();
        while (true)
        {
            int wait = 0;
            String header = conn.getHeaderField("Retry-After");
            if (header != null)
                wait = Integer.valueOf(header);
            if (wait == 0)
                break;
            LOG.info("Waiting (" + wait + ")...");
            conn.disconnect();
            Thread.sleep(wait * 1000);
            conn = (HttpURLConnection) new URL(request).openConnection();
            conn.setDoInput(true);
            conn.connect();
            status = conn.getResponseCode();
        }
        if (status == HttpURLConnection.HTTP_OK)
        {
            LOG.info("Got a OK reply");
            InputStream reader = conn.getInputStream();
            URLConnection.guessContentTypeFromStream(reader);
            StringBuilder builder = new StringBuilder();
            int a = 0;
            while ((a = reader.read()) != -1)
            {
                builder.append((char) a);
            }
            System.out.println(builder.toString());
        }
        else
            LOG.severe("Failed, got " + conn.getResponseMessage() + " for "
                    + request);
        conn.disconnect();
    }

    public static void translate(String tool, ParameterNameValue[] params) throws IOException, InterruptedException {
        StringBuilder locationBuilder = new StringBuilder(SERVER + tool + "/?");
        for (int i = 0; i < params.length; i++)
        {
            if (i > 0)
                locationBuilder.append('&');
            locationBuilder.append(params[i].getName()).append('=').append(params[i].getValue());
        }
        String location = locationBuilder.toString();
        URL url = new URL(location);
        LOG.info("Submitting translation request");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        HttpURLConnection.setFollowRedirects(true);
        conn.setDoInput(true);
        conn.connect();

        int status = conn.getResponseCode();
        while (true)
        {
            int wait = 0;
            String header = conn.getHeaderField("Retry-After");
            if (header != null)
                wait = Integer.valueOf(header);
            if (wait == 0)
                break;
            LOG.info("Waiting (" + wait + ")...");
            conn.disconnect();
            Thread.sleep(wait * 1000);
            conn = (HttpURLConnection) new URL(location).openConnection();
            conn.setDoInput(true);
            conn.connect();
            status = conn.getResponseCode();
        }
        if (status == HttpURLConnection.HTTP_OK)
        {
            LOG.info("Got a OK reply");
            InputStream reader = conn.getInputStream();
            URLConnection.guessContentTypeFromStream(reader);
            StringBuilder builder = new StringBuilder();
            int a = 0;
            while ((a = reader.read()) != -1)
            {
                builder.append((char) a);
            }
            System.out.println(builder.toString());
        }
        else
            LOG.severe("Failed, got " + conn.getResponseMessage() + " for "
                    + location);
        conn.disconnect();
    }

    public void setSERVER(String SERVER) {
        server = SERVER;
    }

    public String getSERVER() {
        return server;
    }
}
