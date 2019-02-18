package com.example.marce.projeto6udacity.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.marce.projeto6udacity.Entidades.Noticias;
import com.example.marce.projeto6udacity.R;

import java.util.List;

public class AdaptadorRecyclerTelaInicial extends RecyclerView.Adapter<AdaptadorRecyclerTelaInicial.ViewHolder> {


    Context context;
    List<Noticias> noticiasList;

    public AdaptadorRecyclerTelaInicial(Context context, List<Noticias> noticiasList) {
        this.context = context;
        this.noticiasList = noticiasList;
    }

    @NonNull
    @Override
    public AdaptadorRecyclerTelaInicial.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_noticias,
                viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final Noticias noticias = noticiasList.get(i);

        viewHolder.tituloNoticia.setText(noticias.getmTituloNoticia());
        viewHolder.sessaoNoticia.setText(noticias.getmNomeSessaoNoticia());
        viewHolder.dataNoticia.setText(noticias.getmDataNoticia());
        viewHolder.autorNome.setText(noticias.getmNomeAutorNoticia());

        viewHolder.linearSelecao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(noticias.getmWebURL()));

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noticiasList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tituloNoticia;
        TextView dataNoticia;
        TextView sessaoNoticia;
        LinearLayout linearSelecao;
        TextView autorNome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tituloNoticia = itemView.findViewById(R.id.tituloNoticia);
            dataNoticia = itemView.findViewById(R.id.dataNoticia);
            sessaoNoticia = itemView.findViewById(R.id.sessaoNoticia);
            linearSelecao = itemView.findViewById(R.id.linearSelecao);
            autorNome = itemView.findViewById(R.id.autorNome);
        }
    }
}
