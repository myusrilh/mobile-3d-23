package com.example.emotiondetectorfortherapy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emotiondetectorfortherapy.R;
import com.example.emotiondetectorfortherapy.model.MedicalReportData;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MedicalReportAdapter extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list_medical,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return MedicalReportData.hasil.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvHasil;
        private TextView tvHari;
        private TextView tvBulan;
        private TextView tvTanggal;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHasil = (TextView) itemView.findViewById(R.id.hasil_medical_report);
            tvHari = (TextView) itemView.findViewById(R.id.hari_medical_report);
            tvBulan = (TextView) itemView.findViewById(R.id.tanggal_medical_report);
            tvTanggal = (TextView) itemView.findViewById(R.id.bulan_medical_report);


        }

        public void bindView(int position){
            tvHasil.setText(MedicalReportData.hasil[position]);
            tvHari.setText(MedicalReportData.hari[position]);
            tvBulan.setText(MedicalReportData.bulan[position]);
            tvTanggal.setText(String.valueOf(MedicalReportData.tanggal[position]));
        }

        @Override
        public void onClick(View view) {

        }
    }
}
