package com.tc.parkingproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tc.parkingproject.R;
import com.tc.parkingproject.model.Parking;

import java.util.ArrayList;

/**
 * ParkingProject created by user
 * student ID: 991522432
 * on 2019-11-26
 */
public class ParkingAdapter extends ArrayAdapter {

    Context context;

    public ParkingAdapter(@NonNull Context context, ArrayList<Parking> parkings) {
        super(context, 0, parkings);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.parking_item, parent, false);
        }

        TextView txtPlateNum = convertView.findViewById(R.id.txtPlateNum);
        TextView txtDate = convertView.findViewById(R.id.txtDate);
        TextView txtCost = convertView.findViewById(R.id.txtCost);

        ImageView imgParking = convertView.findViewById(R.id.imgParking);
        imgParking.setImageResource(R.drawable.parking_image);

        Parking parking = (Parking) getItem(position);

        String cost = "$" + String.valueOf(parking.getCost());

        txtPlateNum.setText(parking.getPlateNum());
        txtDate.setText(parking.getDate().toString());
        txtCost.setText(cost);



        return convertView;
    }
}
