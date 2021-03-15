package com.example.projectcompis.ui.fragment;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectcompis.R;
import com.example.projectcompis.model.Tarea;
import com.example.projectcompis.model.TareaAntigua;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link TareaAntigua}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTareaRecyclerViewAdapter extends RecyclerView.Adapter<MyTareaRecyclerViewAdapter.ViewHolder> {

    private final List<TareaAntigua> mValues;
    private final OnTareaInteractionListener mListener;

    public MyTareaRecyclerViewAdapter(Context context, List<TareaAntigua> items, OnTareaInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_tareas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.tvTarea.setText(holder.mItem.getNombreTarea());
        holder.tvNombre.setText(holder.mItem.getNombreUsuario());
        holder.tvPrioridad.setText(holder.mItem.getPrioridad());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onTareaClick(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvTarea;
        public final TextView tvNombre;
        public final TextView tvPrioridad;
        // public TareaAntigua mItem;
        public TareaAntigua mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvTarea = (TextView) view.findViewById(R.id.tv_T_Tarea);
            tvNombre = (TextView) view.findViewById(R.id.tv_T_Nombre);
            tvPrioridad = (TextView) view.findViewById(R.id.tv_T_Prioridad);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvTarea.getText() + "'";
        }
    }
}