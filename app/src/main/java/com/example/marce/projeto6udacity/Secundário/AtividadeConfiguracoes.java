package com.example.marce.projeto6udacity.Secundário;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.marce.projeto6udacity.R;

public class AtividadeConfiguracoes extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new AtividadeConfiguracoesFragment()).commit();
    }

    public static class AtividadeConfiguracoesFragment extends PreferenceFragment{

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.configuracoes_fragmento);

            bindSummaryValue(findPreference(getString(R.string.sessao_item)));

        }
    }

    private static void bindSummaryValue(Preference preference){
        preference.setOnPreferenceChangeListener(preferenceChangeListener);

        preferenceChangeListener.onPreferenceChange(preference,PreferenceManager.getDefaultSharedPreferences(
                preference.getContext()).getString(
                        preference.getKey(),""));

    }

    private static Preference.OnPreferenceChangeListener preferenceChangeListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object valorAlterado) {

           String valorASetar = valorAlterado.toString();

           if(preference instanceof ListPreference){

               ListPreference listaValores = (ListPreference) preference;

               int index = listaValores.findIndexOfValue(valorASetar);

               preference.setSummary(index >= 0 ? listaValores.getEntries()[index] : null);
           }
            return true;
        }
    };
}
