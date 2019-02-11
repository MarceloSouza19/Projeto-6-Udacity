package com.example.marce.projeto6udacity.Secundário;

import com.example.marce.projeto6udacity.Entidades.Noticias;

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

public class Connection {

    HttpURLConnection urlConnection;
    InputStream inputStream;
    String jsonResponse="";


    public List<Noticias> connectionServer(URL url) throws IOException {

        try{

            if(url==null){
                return new ArrayList<>();
            }
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();

            if(urlConnection.getResponseCode()==200){
                inputStream=urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);

               return parserJsonResult(jsonResponse);
            }
        }catch(IOException e){
            throw new RuntimeException("Problemas ao conectar com "+url+"\n Mensagem: "+e);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if(urlConnection!=null){
                urlConnection.disconnect();
            }
            if(inputStream!=null){
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

        if(jsonArrayResults.length()>0){

            for(int i=0;i<jsonArrayResults.length();i++){

                JSONObject itemResponse = jsonArrayResults.getJSONObject(i);

                String tituloNoticia = itemResponse.getString("webTitle");
                String nomeSessaoNoticia = itemResponse.getString("sectionName");
                String dataNoticia = itemResponse.getString("webPublicationDate");
                String webURL = itemResponse.getString("webUrl");

                listaNoticias.add(new Noticias(tituloNoticia,nomeSessaoNoticia,dataNoticia,webURL));
            }
        }


        return listaNoticias;
    }

    public static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();

        if(inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = reader.readLine();

            while(line !=null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


}
