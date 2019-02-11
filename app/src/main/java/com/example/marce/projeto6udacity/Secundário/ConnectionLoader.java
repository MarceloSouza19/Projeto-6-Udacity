package com.example.marce.projeto6udacity.Secundário;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.example.marce.projeto6udacity.Entidades.Noticias;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ConnectionLoader extends AsyncTaskLoader<List<Noticias>> {

    String url;
    public ConnectionLoader(@NonNull Context context, String url) {
        super(context);
        this.url=url;
    }

    @Nullable
    @Override
    public List<Noticias> loadInBackground() {

        List<Noticias> noticiasList = new ArrayList<>();
        try {
            Connection connection = new Connection();
            noticiasList = connection.connectionServer(new URL(url));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return noticiasList;
    }

    @Override
    public void deliverResult(@Nullable List<Noticias> data) {
        super.deliverResult(data);
    }
}
