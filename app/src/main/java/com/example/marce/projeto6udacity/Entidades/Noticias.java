package com.example.marce.projeto6udacity.Entidades;

public class Noticias {

    String tituloNoticia;
    String nomeSessaoNoticia;
    String dataNoticia;
    String webURL;

    public Noticias(String tituloNoticia, String nomeSessaoNoticia, String dataNoticia, String webURL) {
        this.tituloNoticia = tituloNoticia;
        this.nomeSessaoNoticia = nomeSessaoNoticia;
        this.dataNoticia = dataNoticia;
        this.webURL = webURL;
    }

    public String getWebURL() {
        return webURL;
    }

    public String getTituloNoticia() {

        return tituloNoticia;
    }

    public String getNomeSessaoNoticia() {
        return nomeSessaoNoticia;
    }

    public String getDataNoticia() {
        return dataNoticia;
    }
}
