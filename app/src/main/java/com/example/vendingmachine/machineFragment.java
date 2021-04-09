package com.example.vendingmachine;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class machineFragment extends
        android.app.Fragment {

    private TextView machineName;
    private TextView machineStatus;
    private TextView machineStudent;
    private TextView machineProductsList;
    private TextView machineAmountOfProducts;


    public machineFragment() { }

    public static machineFragment newInstance() {
        machineFragment fragment = new machineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_machine, container, false);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = 140;
        view.setLayoutParams(params);
        machineName = view.findViewById(R.id.machineName);
        machineStatus = view.findViewById(R.id.machineStatus);
        machineStudent = view.findViewById(R.id.machineStudent);
        machineProductsList = view.findViewById(R.id.machineProductsList);
        machineAmountOfProducts = view.findViewById(R.id.machineAmountOfProducts);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup.LayoutParams params = view.getLayoutParams();
                if (params.height == 140) {
                    params.height = 800;
                }
                else {
                    params.height = 140;
                }
                view.setLayoutParams(params);
            }
        });
        return view;
    }

    public void setMachineNameValue(String name) {
        machineName.setText(name);
    }

    public void setMachineStatusValue(Status status) {
        machineStatus.setText(status.toString());
    }

    public void setMachineStatusValue(String status) {
        machineStatus.setText(status);
    }

    public void setMachineStudentValue(int amountOfStudents) {
        machineStudent.setText(Integer.toString(amountOfStudents));
    }

    public void setMachineProductsListValue(String products) {
        machineProductsList.setText(products);
    }

    public void appendMachineProductsListValue(String products) {
        machineProductsList.setText(machineProductsList.getText() + products);
    }

    public void setMachineAmountOfProductsValue(int n) {
        machineAmountOfProducts.setText(Integer.toString(n));
    }
}
