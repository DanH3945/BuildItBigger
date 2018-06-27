package com.hereticpurge.jokelib;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

// Simple little library to pull jokes from the Chuck Norris joke api @ www.icndb.com

public class Joke {

    private static final String JAVA_IO_EXCEPTION_STRING = "Java IO Exception occurred.";
    private static final String NULL_JOKE_STRING = "Tried to load null joke";
    private static final String JOKE_DB_URL_RANDOM = "http://api.icndb.com/jokes/random";
    private static final String JSON_VALUE = "value";
    private static final String JSON_JOKE = "joke";

    public static String getNextJoke(){

        String sJokeString;
        try {
            sJokeString = getChuckJoke();
        } catch (IOException e) {
            sJokeString = JAVA_IO_EXCEPTION_STRING;
        }

        if (sJokeString == null){
            sJokeString = NULL_JOKE_STRING;
        }
        return sJokeString;
    }

    private static String getChuckJoke() throws IOException {
        InputStream is = null;
        JSONObject jsonObject = null;
        String joke = null;

        try {
            URL url = new URL(JOKE_DB_URL_RANDOM);

            is = url.openStream();

            BufferedReader bReader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jString = readJsonString(bReader);
            jsonObject = new JSONObject(jString);
        } catch (MalformedURLException e) {
            // this shouldn't happen...
            e.printStackTrace();
        } finally {
            if (is != null){
                is.close();
            }
        }
        if (jsonObject != null) {
            joke = jsonObject.getJSONObject(JSON_VALUE).getString(JSON_JOKE);
        }
        return joke;
    }

    private static String readJsonString(Reader rd) throws IOException{
        StringBuilder sb = new StringBuilder();
        int charInt;
        while ((charInt = rd.read()) != -1){
            sb.append((char) charInt);
        }
        return sb.toString();
    }
}
