package com.crosemont.guichetautomatiqueatm;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class AndroidAdapter extends ArrayAdapter<Client>{
    //Liste clients
    private final ArrayList<Client> clientList;


    //Le présent contexte interface
    private final Context context;

    private final int viewRes;
    private final Resources res;

    public AndroidAdapter(Context context, int textViewResourceId, ArrayList<Client> versions) {
        super(context, textViewResourceId, versions);
        this.clientList = versions;
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
        final Client client = clientList.get(position);
        if (client != null) {
            final TextView titre = (TextView) view.findViewById(R.id.titre);
            final TextView description = (TextView) view.findViewById(R.id.description);
            final TextView api = (TextView) view.findViewById(R.id.api);
            String nomClient = "Prenom : " + client.getPrenom();
            titre.setText(nomClient);
            String prenomClient = "Nom : " + client.getNom();
            description.setText(prenomClient);
            String numCompteClient = res.getString(R.string.api) + " " + client.getNumCompte();
            api.setText(numCompteClient);
        }
        return view;
    }

    @Override
    public int getCount() {
        return clientList.size();
    }
}
