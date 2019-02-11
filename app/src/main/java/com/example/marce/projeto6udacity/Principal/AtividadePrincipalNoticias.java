package com.example.marce.projeto6udacity.Principal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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


        getSupportLoaderManager().initLoader(0, null, this).forceLoad();

    }

    @NonNull
    @Override
    public Loader<List<Noticias>> onCreateLoader(int i, @Nullable Bundle bundle) {

        return new ConnectionLoader(getApplicationContext(), getApplicationContext().getString(R.string.urlServer));

    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Noticias>> loader, List<Noticias> noticiasList) {

        RecyclerView recyclerView = findViewById(R.id.recyclerNoticias);
        TextView tituloInicial = findViewById(R.id.tituloPaginaInicial);
        TextView mensagemFalhou = findViewById(R.id.conexaoFalhouMensagem);
        ImageView imagemFalhou = findViewById(R.id.imagemFalhouMensagem);
        CardView btnTentarNovamente = findViewById(R.id.btnTentarNovamente);

        if (noticiasList.size() > 0) {

            tituloInicial.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            mensagemFalhou.setVisibility(View.GONE);
            imagemFalhou.setVisibility(View.GONE);
            btnTentarNovamente.setVisibility(View.GONE);

            recyclerView.setAdapter(new AdaptadorRecyclerTelaInicial(getApplicationContext(), noticiasList));
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            Toast.makeText(AtividadePrincipalNoticias.this, getApplicationContext().getString(R.string.bem_vindo), Toast.LENGTH_SHORT).show();

        } else {
            recyclerView.setVisibility(View.GONE);

            tituloInicial = findViewById(R.id.tituloPaginaInicial);
            mensagemFalhou = findViewById(R.id.conexaoFalhouMensagem);
            imagemFalhou = findViewById(R.id.imagemFalhouMensagem);
            btnTentarNovamente = findViewById(R.id.btnTentarNovamente);

            tituloInicial.setVisibility(View.GONE);
            mensagemFalhou.setVisibility(View.VISIBLE);
            imagemFalhou.setVisibility(View.VISIBLE);
            btnTentarNovamente.setVisibility(View.VISIBLE);

            Toast.makeText(AtividadePrincipalNoticias.this, getApplicationContext().getString(R.string.conexao_falhou), Toast.LENGTH_SHORT).show();

            btnTentarNovamente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportLoaderManager().restartLoader(0, null, AtividadePrincipalNoticias.this).forceLoad();
                    Toast.makeText(AtividadePrincipalNoticias.this, getApplicationContext().getString(R.string.aguardar), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<List<Noticias>> loader) {
        getSupportLoaderManager().destroyLoader(0);

    }
}
