package com.example.marce.projeto6udacity.Entidades;

public class Noticias {

    String mtituloNoticia;
    String mnomeSessaoNoticia;
    String mdataNoticia;
    String mwebURL;
    String mNomeAutorNoticia;



    public Noticias(String tituloNoticia, String nomeSessaoNoticia, String dataNoticia, String webURL, String nomeAutorNoticia) {
        this.mtituloNoticia = tituloNoticia;
        this.mnomeSessaoNoticia = nomeSessaoNoticia;
        this.mdataNoticia = dataNoticia;
        this.mwebURL = webURL;
        this.mNomeAutorNoticia=nomeAutorNoticia;
    }

    public String getmNomeAutorNoticia() {
        return mNomeAutorNoticia;
    }

    public String getmWebURL() {
        return mwebURL;
    }

    public String getmTituloNoticia() {

        return mtituloNoticia;
    }

    public String getmNomeSessaoNoticia() {
        return mnomeSessaoNoticia;
    }

    public String getmDataNoticia() {
        return mdataNoticia;
    }
}
