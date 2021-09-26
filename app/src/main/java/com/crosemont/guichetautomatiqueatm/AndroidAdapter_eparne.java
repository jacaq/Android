package com.crosemont.guichetautomatiqueatm;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AndroidAdapter_eparne extends ArrayAdapter<Epargne> {


    private final ArrayList<Epargne> epargneList;

    //Le présent contexte interface
    private final Context context;

    private final int viewRes;
    private final Resources res;

    public AndroidAdapter_eparne(Context context, int textViewResourceId, ArrayList<Epargne> versions) {
        super(context, textViewResourceId, versions);
        this.epargneList = versions;
        this.context = context;
        this.viewRes = textViewResourceId;
        this.res = context.getResources();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(viewRes, parent, false);
        }
        final Epargne client = epargneList.get(position);
        if (client != null) {
            final TextView titre = (TextView) view.findViewById(R.id.titre);
            final TextView description = (TextView) view.findViewById(R.id.description);
            final TextView api = (TextView) view.findViewById(R.id.api);

            String nipClient = "Nip client : "+  client.getNumeroNip();
            titre.setText(nipClient);
            String numeroComptClient = "Numero compte : " + client.getNumeroCompte();
            description.setText(numeroComptClient);
            String soldeDuClient =  "Solde client : " + client.getSoldeCompte();
            api.setText(soldeDuClient);

        }
        return view;
    }

    @Override
    public int getCount() {
        return epargneList.size();
    }
}
