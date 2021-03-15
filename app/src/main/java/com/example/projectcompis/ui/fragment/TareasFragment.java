package com.example.projectcompis.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectcompis.R;
import com.example.projectcompis.crud.CRUD_Piso;
import com.example.projectcompis.model.Piso;
import com.example.projectcompis.model.Tarea;
import com.example.projectcompis.model.TareaAntigua;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

public class TareasFragment extends Fragment {
    OnTareaInteractionListener mListener;
    List<TareaAntigua> tareaAntiguaList;

    public TareasFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*
        idPiso = getArguments().getInt("ID_PISO");
        Piso piso = CRUD_Piso.getPisoByID(idPiso);
        RealmList<Tarea> listaTareas = piso.getTareas();

        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            Tarea tarea = null;
            tareaNuevaList = new ArrayList<>();
            int contador = 0;
            //while (!listaTareas.isEmpty()) {
                tarea = listaTareas.get(contador);
                tareaNuevaList.add(tarea);
                //contador++;
            //}
            recyclerView.setAdapter(new MyTareaRecyclerViewAdapter(getActivity(), tareaNuevaList, mListener));
        }

         */

        View view = inflater.inflate(R.layout.activity_tareas, container, false);
        tareaAntiguaList = new ArrayList<>();

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            // Lista de averias
            tareaAntiguaList = new ArrayList<>();
            tareaAntiguaList.add(new TareaAntigua("Limpiar el baño", "Jose Maria", "Media"));
            tareaAntiguaList.add(new TareaAntigua("Limpiar el salón", "Jorge", "Media"));
            tareaAntiguaList.add(new TareaAntigua("Limpiar la cocina", "Diego", "Media"));
            tareaAntiguaList.add(new TareaAntigua("Comprar papel higienico", "Jose Maria", "Alta"));
            tareaAntiguaList.add(new TareaAntigua("Comprar bolsas de basura", "Jorge", "Baja"));
            tareaAntiguaList.add(new TareaAntigua("Comprar lejia", "Diego", "Baja"));

            recyclerView.setAdapter(new MyTareaRecyclerViewAdapter(getActivity(), tareaAntiguaList, mListener));
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTareaInteractionListener) {
            mListener = (OnTareaInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTareaInteractionListener");
        }
    }
}