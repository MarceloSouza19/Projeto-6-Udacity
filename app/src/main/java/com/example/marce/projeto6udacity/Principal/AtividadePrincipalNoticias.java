package com.example.marce.projeto6udacity.Principal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.marce.projeto6udacity.Adaptadores.AdaptadorRecyclerTelaInicial;
import com.example.marce.projeto6udacity.Entidades.Noticias;
import com.example.marce.projeto6udacity.R;
import com.example.marce.projeto6udacity.Secundário.ConnectionLoader;

import java.util.List;

public class AtividadePrincipalNoticias extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Noticias>> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_principal_noticias);


        getSupportLoaderManager().initLoader(0,null,this).forceLoad();
    }

    @NonNull
    @Override
    public Loader<List<Noticias>> onCreateLoader(int i, @Nullable Bundle bundle) {

        return new ConnectionLoader(getApplicationContext(),"http://content.guardianapis.com/search?q=debates&api-key=test");
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Noticias>> loader, List<Noticias> noticiasList) {

        RecyclerView recyclerView = findViewById(R.id.recyclerNoticias);

        recyclerView.setAdapter(new AdaptadorRecyclerTelaInicial(getApplicationContext(),noticiasList));

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Noticias>> loader) {

    }
}
