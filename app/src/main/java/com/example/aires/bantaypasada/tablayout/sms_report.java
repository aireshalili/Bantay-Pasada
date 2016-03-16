package com.example.aires.bantaypasada.tablayout;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aires.bantaypasada.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 */
public class sms_report extends Fragment {

    public sms_report() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sms_report, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RadioButton radio_traffic = (RadioButton) getView().findViewById(R.id.traffic);
        RadioButton radio_drivers = (RadioButton) getView().findViewById(R.id.driver);
        RadioButton radio_others = (RadioButton) getView().findViewById(R.id.others);

        final EditText body = (EditText) getView().findViewById(R.id.sms_body);
        final EditText header = (EditText) getView().findViewById(R.id.header);

        final Spinner spinner = (Spinner) getView().findViewById(R.id.spinner);
        radio_traffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                header.setVisibility(View.GONE);
                ArrayAdapter<String> arrayAdapter;
                List<String> TrafficList;
                TrafficList = new ArrayList<>();
                TrafficList.add("Driving under the influence of liquor");
                TrafficList.add("Driving under the influence of drugs");
                TrafficList.add("Allowing an unlicensed/improperly licensed person to drive motor vehicle");
                TrafficList.add("Allowing an unlicensed/improperly licensed person to drive motor vehicle");
                TrafficList.add("Possession and use of fake/spurious Driver’s License");
                TrafficList.add("Conviction of the driver of a crime using a motor vehicle");
                TrafficList.add("Operating / driving a motor vehicle which is unregistered/improperly Registered");
                TrafficList.add("Operating a motor vehicle with unregistered substitute or replacement engine, engine block or chassis");
                TrafficList.add("Operating /allowing the operation of MV with a suspended / revoked Certificate/Official Receipt of registration");
                TrafficList.add("Tourist operating or allowing the use of non Philippine registered Motor Vehicle beyond the 90 day period of his sojourn in the country");
                TrafficList.add("Display/Use of an expired commemorative plates or stickers");
                TrafficList.add("Tampered/ marked plates or stickers ");
                TrafficList.add("Illegal transfer or use of MV regularly issued MV plates, tags or stickers except security plates on authorized Motor Vehicle");
                arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_textview, TrafficList);
                arrayAdapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);
            }
        });

        radio_drivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                header.setVisibility(View.GONE);
                ArrayAdapter<String> arrayAdapter;
                List<String> DriversList;
                DriversList = new ArrayList<>();
                DriversList.add("1");
                DriversList.add("2");
                DriversList.add("3");
                arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_textview, DriversList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);
            }
        });

        radio_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.GONE);
                header.setVisibility(View.VISIBLE);
            }
        });


        Button button = (Button) getView().findViewById(R.id.button_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = "";
                if (header.isShown()) {
                    head = header.getText().toString();
                } else {
                    try {
                        head = spinner.getSelectedItem().toString();
                    }catch (Exception e){}
                }
                final String smsBody = body.getText().toString();

                if (head.equals("")) {
                    Toast.makeText(getContext(), "Empty header", Toast.LENGTH_SHORT).show();
                }else if(smsBody.equals("")){
                    Toast.makeText(getContext(),"Empty details" ,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), head + "\n" + "\n" + smsBody, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
