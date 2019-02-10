package com.example.marce.projeto6udacity.Adaptadores;

import java.net.URL;
import java.util.Date;

public class AdaptadorRecyclerTelaInicial {

    private String nomeDaSessao;
    private Date dataPublicacao;
    private String urlParaIntent;
    private String tituloNoticia;

    public AdaptadorRecyclerTelaInicial(String nomeDaSessao, Date dataPublicacao, String urlParaIntent, String tituloNoticia) {
        this.nomeDaSessao = nomeDaSessao;
        this.dataPublicacao = dataPublicacao;
        this.urlParaIntent = urlParaIntent;
        this.tituloNoticia = tituloNoticia;
    }

    public String getNomeDaSessao() {
        return nomeDaSessao;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public String getUrlParaIntent() {
        return urlParaIntent;
    }

    public String getTituloNoticia() {
        return tituloNoticia;
    }
}
