package com.example.marce.projeto6udacity.Secundário;

import android.content.Context;
import android.content.res.Resources;

import com.example.marce.projeto6udacity.Entidades.Noticias;
import com.example.marce.projeto6udacity.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Conexao {

    HttpURLConnection urlConnection = null;
    InputStream inputStream = null;
    String jsonResponse = "";
    Context context;
    private static final int READ_TIMEOUT = 10000;
    private static final int CONN_TIMEOUT = 15000;
    private static final int FIRST_POSITION=0;
    private static final int OK_RESPONSE_CODE=200;

    public Conexao(Context context) {
        this.context = context;
    }


    public List<Noticias> connectionServer(URL url) throws IOException {

        try {

            if (url == null) {
                return new ArrayList<>();
            }
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(READ_TIMEOUT);
            urlConnection.setConnectTimeout(CONN_TIMEOUT);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == OK_RESPONSE_CODE) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);

                return parserJsonResult(jsonResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (JSONException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new ArrayList<>();
    }

    public List<Noticias> parserJsonResult(String jsonResponse) throws JSONException {

        List<Noticias> listaNoticias = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(jsonResponse);

        JSONObject jsonObjectResponse = jsonObject.getJSONObject("response");

        JSONArray jsonArrayResults = jsonObjectResponse.getJSONArray("results");

        if (jsonArrayResults.length() > 0) {

            for (int i = 0; i < jsonArrayResults.length(); i++) {

                JSONObject itemResponse = jsonArrayResults.getJSONObject(i);

                String tituloNoticia = itemResponse.getString(context.getResources().getString(R.string.webTitle));
                String nomeSessaoNoticia = itemResponse.getString(context.getResources().getString(R.string.sectionName));
                String dataNoticia = itemResponse.getString(context.getResources().getString(R.string.webPublicationDate));
                String webURL = itemResponse.getString(context.getResources().getString(R.string.webUrl));
                JSONArray tagAutor = itemResponse.getJSONArray("tags");
                String nomeAutor ="";

                if(tagAutor.length()>0){
                    JSONObject dadosAutor = tagAutor.getJSONObject(FIRST_POSITION);
                    nomeAutor = dadosAutor.getString(context.getResources().getString(R.string.webTitle));
                }

                listaNoticias.add(new Noticias(tituloNoticia, nomeSessaoNoticia, dataNoticia, webURL, nomeAutor));
            }
        }


        return listaNoticias;
    }

    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = reader.readLine();

            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
