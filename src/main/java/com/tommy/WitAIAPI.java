package com.tommy;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tommy on 2016/8/1.
 */
public class WitAIAPI {

    public static void main(String[] args) throws IOException {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("https://api.wit.ai/speech?v=20160801");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
      //  nvps.add(new BasicNameValuePair("username", "vip"));
      //  nvps.add(new BasicNameValuePair("password", "secret"));
        httpPost.setHeader("Authorization","Bearer PYQKFYTDHIBF5LGIVXHGGGBD7PI3RW4D");
        httpPost.setHeader("Content-Type","audio/wav");
      //  httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        File file=new File("I:\\VoiceTestWav\\hello2.wav");
        InputStreamEntity reqEntity = new InputStreamEntity(
                new FileInputStream(file), -1, ContentType.APPLICATION_OCTET_STREAM);
        reqEntity.setChunked(true);
        httpPost.setEntity(reqEntity);
        System.out.println("start speech"+new Date().toLocaleString());
        HttpResponse response2 = httpClient.execute(httpPost);

        try {
            System.out.println(response2.getStatusLine());
            HttpEntity entity2 = response2.getEntity();
            System.out.println(EntityUtils.toString(response2.getEntity()));
            System.out.println("end speech"+new Date().toLocaleString());

            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
