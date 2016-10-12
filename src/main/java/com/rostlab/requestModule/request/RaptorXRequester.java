package com.rostlab.requestModule.request;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Longes
 */
public class RaptorXRequester extends Requester {

    public RaptorXRequester(String email) {
        this.email = email;
        try {
            this.url = new URL("http://raptorx.uchicago.edu/StructurePrediction/predict/pred/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void makeRequest (String sequence) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(this.url.toString());
        httpPost.addHeader("User-Agent", "Mozilla/5.0");

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("sequences", "MYKMYFLKDQKFSLSGTIRINDKTQSEYGSVWCPGLSITGLHHDAIDHNMFEEMETEIIEYLGPWVQAEYRRIKG"));
        urlParameters.add(new BasicNameValuePair("email", this.email));
        urlParameters.add(new BasicNameValuePair("jobname", "EVATest"));

        HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
        httpPost.setEntity(postParams);

        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

        System.out.println("POST Response Status:: "
                + httpResponse.getStatusLine().getStatusCode());

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();

        // print result
        System.out.println(response.toString());
        httpClient.close();
    }
}
