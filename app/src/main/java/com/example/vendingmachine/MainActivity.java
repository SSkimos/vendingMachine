package com.example.vendingmachine;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import IFabrics.IFabric;
import IFabrics.borjomyFabric;
import IFabrics.croissantFabric;
import IFabrics.laysFabric;

public class MainActivity extends AppCompatActivity {

    TextView machineName1;
    TextView machineStatus1;
    TextView machineStudent1;
    TextView machineProductList1;
    TextView machineAmountProduct1;

    TextView machineName2;
    TextView machineStatus2;
    TextView machineStudent2;
    TextView machineProductList2;
    TextView machineAmountProduct2;

    TextView machineName3;
    TextView machineStatus3;
    TextView machineStudent3;
    TextView machineProductList3;
    TextView machineAmountProduct3;

    TextView machineName4;
    TextView machineStatus4;
    TextView machineStudent4;
    TextView machineProductList4;
    TextView machineAmountProduct4;

    Spinner chooseProduct;
    Spinner chooseMachine;
    EditText AmountProduct;
    Button add;
    Button generateStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Student> studentGroup1;
        ArrayList<Student> studentGroup2;
        ArrayList<Student> studentGroup3;
        ArrayList<Student> studentGroup4;
        studentGroup1 = new ArrayList<Student>();
        studentGroup2 = new ArrayList<Student>();
        studentGroup3 = new ArrayList<Student>();
        studentGroup4 = new ArrayList<Student>();

        for (int i = 0; i < 20; i++) {
            int randNum = (int) ((Math.random() * 100) % 4);
            switch (randNum) {
                case 0:
                    studentGroup1.add(new Student(i, 300 + (int) (Math.random() * 100)));
                    break;
                case 1:
                    studentGroup2.add(new Student(i, 300 + (int) (Math.random() * 100)));
                    break;
                case 2:
                    studentGroup3.add(new Student(i, 300 + (int) (Math.random() * 100)));
                    break;
                case 3:
                    studentGroup4.add(new Student(i, 300 + (int) (Math.random() * 100)));
                    break;
            }
        }
        Machine machine1;
        Machine machine2;
        Machine machine3;
        Machine machine4;

        machine1 = new Machine("1");
        machine1.setQueue(studentGroup1);
        machine2 = new Machine("2");
        machine2.setQueue(studentGroup2);
        machine3 = new Machine("3");
        machine3.setQueue(studentGroup3);
        machine4 = new Machine("4");
        machine4.setQueue(studentGroup4);


        machineName1 = findViewById(R.id.machineName1);
        machineName1.setText(machine1.getName());
        machineStatus1 = findViewById(R.id.machineStatus1);
        machineStudent1 = findViewById(R.id.machineStudent1);
        machineProductList1 = findViewById(R.id.machineProductList1);
        machineAmountProduct1 =  findViewById(R.id.machineAmountProduct1);
        machineName2 = findViewById(R.id.machineName2);

        machineName2 = findViewById(R.id.machineName2);
        machineName2.setText(machine2.getName());
        machineStatus2 = findViewById(R.id.machineStatus2);
        machineStudent2 = findViewById(R.id.machineStudent2);
        machineProductList2 = findViewById(R.id.machineProductList2);
        machineAmountProduct2 =  findViewById(R.id.machineAmountProduct2);

        machineName3 = findViewById(R.id.machineName3);
        machineName3.setText(machine3.getName());
        machineStatus3 = findViewById(R.id.machineStatus3);
        machineStudent3 = findViewById(R.id.machineStudent3);
        machineProductList3 = findViewById(R.id.machineProductList3);
        machineAmountProduct3 =  findViewById(R.id.machineAmountProduct3);

        machineName4 = findViewById(R.id.machineName4);
        machineName4.setText(machine4.getName());
        machineStatus4 = findViewById(R.id.machineStatus4);
        machineStudent4 = findViewById(R.id.machineStudent4);
        machineProductList4 = findViewById(R.id.machineProductList4);
        machineAmountProduct4 =  findViewById(R.id.machineAmountProduct4);

        chooseProduct = findViewById(R.id.chooseProduct);
        String[] products = {"borjomy", "croissant", "lays"};
        ArrayAdapter<String> adapterProducts = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, products);
        adapterProducts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseProduct.setAdapter(adapterProducts);

        chooseMachine = findViewById(R.id.chooseMachine);
        String[] machines = {"1", "2", "3", "4"};
        ArrayAdapter<String> adapterMachines = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, machines);
        adapterMachines.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseMachine.setAdapter(adapterMachines);

        AmountProduct = findViewById(R.id.AmountProduct);

        add = (Button) findViewById(R.id.addProduct);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                IFabric fabric = new borjomyFabric();
                switch (chooseProduct.getSelectedItem().toString()) {
                    case "borjomy":
                        fabric = new borjomyFabric();
                        break;
                    case "croissant":
                        fabric = new croissantFabric();
                        break;
                    case "lays":
                        fabric = new laysFabric();
                }
                int amount;
                if (AmountProduct.getText().toString().equals(""))
                    amount = 1;
                else
                    amount = Integer.parseInt(AmountProduct.getText().toString());
                switch (chooseMachine.getSelectedItem().toString()) {
                    case "1":
                        machine1.addProduct(fabric, amount);
                        machineAmountProduct1.setText(Integer.toString(machine1.getProduct().size()));
                        break;
                    case "2":
                        machine2.addProduct(fabric, amount);
                        machineAmountProduct2.setText(Integer.toString(machine2.getProduct().size()));
                        break;
                    case "3":
                        machine3.addProduct(fabric, amount);
                        machineAmountProduct3.setText(Integer.toString(machine3.getProduct().size()));
                        break;
                    case "4":
                        machine4.addProduct(fabric, amount);
                        machineAmountProduct4.setText(Integer.toString(machine4.getProduct().size()));
                        break;
                }

            }
        });

        generateStudents = (Button) findViewById(R.id.generateStudents);
        generateStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                machineLifeCycle lifeCycle1 = new machineLifeCycle();
                machineLifeCycle lifeCycle2 = new machineLifeCycle();
                machineLifeCycle lifeCycle3 = new machineLifeCycle();
                machineLifeCycle lifeCycle4 = new machineLifeCycle();
                lifeCycle1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, machine1);
                lifeCycle2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, machine2);
                lifeCycle3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, machine3);
                lifeCycle4.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, machine4);
            }
        });
    }

    class machineLifeCycle extends AsyncTask<Machine, Machine, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Machine... Machine) {
            for (int i = 0; i < Machine[0].getQueue().size(); i++) {
                Machine[0].startToWork(Machine[0].getQueue().get(i));
                publishProgress(Machine[0]);
                Machine[0].workingWithAClient();
                publishProgress(Machine[0]);
                Machine[0].paying();
                publishProgress(Machine[0]);
                Machine[0].delivering();
                publishProgress(Machine[0]);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Machine... Machine) {
            if (Machine[0].getName().equals("1")) {
                machineStatus1.setText(Machine[0].getStatus().toString());
                machineStudent1.setText(Integer.toString(Machine[0].getClientNumber()));
                String wantToBuy = "";
                if (Machine[0].getChoose() != null && Machine[0].getStatus() == com.example.vendingmachine.Status.ISPAYING)
                    for (int i = 0; i < Machine[0].getChoose().length; i++) {
                        wantToBuy += Machine[0].getProduct().get(Machine[0].getChoose()[i]).getName() + " ";
                    }
                machineProductList1.setText(wantToBuy);
                machineAmountProduct1.setText(Integer.toString(Machine[0].getProduct().size()));
            }

            if (Machine[0].getName().equals("2")) {
                machineStatus2.setText(Machine[0].getStatus().toString());
                machineStudent2.setText(Integer.toString(Machine[0].getClientNumber()));
                String wantToBuy = "";
                if (Machine[0].getChoose() != null && Machine[0].getStatus() == com.example.vendingmachine.Status.ISPAYING)
                    for (int i = 0; i < Machine[0].getChoose().length; i++) {
                        wantToBuy += Machine[0].getProduct().get(Machine[0].getChoose()[i]).getName() + " ";
                    }
                machineProductList2.setText(wantToBuy);
                machineAmountProduct2.setText(Integer.toString(Machine[0].getProduct().size()));
            }

            if (Machine[0].getName().equals("3")) {
                machineStatus3.setText(Machine[0].getStatus().toString());
                machineStudent3.setText(Integer.toString(Machine[0].getClientNumber()));
                String wantToBuy = "";
                if (Machine[0].getChoose() != null && Machine[0].getStatus() == com.example.vendingmachine.Status.ISPAYING)
                    for (int i = 0; i < Machine[0].getChoose().length; i++) {
                        wantToBuy += Machine[0].getProduct().get(Machine[0].getChoose()[i]).getName() + " ";
                    }
                machineProductList3.setText(wantToBuy);
                machineAmountProduct3.setText(Integer.toString(Machine[0].getProduct().size()));
            }

            if (Machine[0].getName().equals("4")) {
                machineStatus4.setText(Machine[0].getStatus().toString());
                machineStudent4.setText(Integer.toString(Machine[0].getClientNumber()));
                String wantToBuy = "";
                if (Machine[0].getChoose() != null && Machine[0].getStatus() == com.example.vendingmachine.Status.ISPAYING)
                    for (int i = 0; i < Machine[0].getChoose().length; i++) {
                        wantToBuy += Machine[0].getProduct().get(Machine[0].getChoose()[i]).getName() + " ";
                    }
                machineProductList4.setText(wantToBuy);
                machineAmountProduct4.setText(Integer.toString(Machine[0].getProduct().size()));
            }
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}