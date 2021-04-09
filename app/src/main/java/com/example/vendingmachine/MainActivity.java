package com.example.vendingmachine;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;

import IFabrics.IFabric;
import IFabrics.borjomyFabric;
import IFabrics.croissantFabric;
import IFabrics.laysFabric;
import product.borjomy;

public class MainActivity extends FragmentActivity {


    Spinner chooseProduct;
    Spinner chooseVendingMachine;
    EditText AmountOfProducts;
    Button add;
    Button generateStudents;

    machineFragment fragment1 = machineFragment.newInstance();
    machineFragment fragment2 = machineFragment.newInstance();
    machineFragment fragment3 = machineFragment.newInstance();
    machineFragment fragment4 = machineFragment.newInstance();

    Handler handler = new Handler() {   // создание хэндлера
        @Override
        public void handleMessage(Message msg) {
            switch (msg.getData().getString("name")) {
                case "1":
                    fragment1.setMachineNameValue(msg.getData().getString("name"));
                    fragment1.setMachineStatusValue(msg.getData().getString("status"));
                    fragment1.setMachineStudentValue(msg.getData().getInt("student"));
                    fragment1.setMachineAmountOfProductsValue(msg.getData().getInt("productsAmount"));
                    fragment1.setMachineProductsListValue(msg.getData().getString("productsList"));
                    break;
                case "2":
                    fragment2.setMachineNameValue(msg.getData().getString("name"));
                    fragment2.setMachineStatusValue(msg.getData().getString("status"));
                    fragment2.setMachineStudentValue(msg.getData().getInt("student"));
                    fragment2.setMachineAmountOfProductsValue(msg.getData().getInt("productsAmount"));
                    fragment2.setMachineProductsListValue(msg.getData().getString("productsList"));
                    break;

                case "3":
                    fragment3.setMachineNameValue(msg.getData().getString("name"));
                    fragment3.setMachineStatusValue(msg.getData().getString("status"));
                    fragment3.setMachineStudentValue(msg.getData().getInt("student"));
                    fragment3.setMachineAmountOfProductsValue(msg.getData().getInt("productsAmount"));
                    fragment3.setMachineProductsListValue(msg.getData().getString("productsList"));
                    break;
                case "4":
                    fragment4.setMachineNameValue(msg.getData().getString("name"));
                    fragment4.setMachineStatusValue(msg.getData().getString("status"));
                    fragment4.setMachineStudentValue(msg.getData().getInt("student"));
                    fragment4.setMachineAmountOfProductsValue(msg.getData().getInt("productsAmount"));
                    fragment4.setMachineProductsListValue(msg.getData().getString("productsList"));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //инициализация групп студентов на рандом
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

        //инициализация автоматов и запись их имен в соответсвующие виджеты текста
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

        //инициализация фрагментов
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(R.id.fragmentContainer1, fragment1);
        transaction.add(R.id.fragmentContainer2, fragment2);
        transaction.add(R.id.fragmentContainer3, fragment3);
        transaction.add(R.id.fragmentContainer4, fragment4);
        transaction.commit();




        //создание выпадающего меню с продуктами
        chooseProduct = findViewById(R.id.chooseProduct);
        String[] products = {"borjomy", "croissant", "lays"};
        ArrayAdapter<String> adapterProducts = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, products);
        adapterProducts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseProduct.setAdapter(adapterProducts);

        //создание выпадающего меню с автоматами
        chooseVendingMachine = findViewById(R.id.chooseVendingMachine);
        String[] machines = {"1", "2", "3", "4"};
        ArrayAdapter<String> adapterMachines = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, machines);
        adapterMachines.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseVendingMachine.setAdapter(adapterMachines);

        AmountOfProducts = findViewById(R.id.AmountOfProducts);

        //создание кнопки добавления продуктов
        add = (Button) findViewById(R.id.addProducts);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment1.setMachineNameValue("1");
                fragment2.setMachineNameValue("2");
                fragment3.setMachineNameValue("3");
                fragment4.setMachineNameValue("4");
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
                if (AmountOfProducts.getText().toString().equals(""))
                    amount = 1;
                else
                    amount = Integer.parseInt(AmountOfProducts.getText().toString());
                switch (chooseVendingMachine.getSelectedItem().toString()) {
                    case "1":
                        machine1.addProduct(fabric, amount);
                        fragment1.setMachineAmountOfProductsValue(machine1.getProduct().size());
                        break;
                    case "2":
                        machine2.addProduct(fabric, amount);
                        fragment2.setMachineAmountOfProductsValue(machine2.getProduct().size());
                        break;
                    case "3":
                        machine3.addProduct(fabric, amount);
                        fragment3.setMachineAmountOfProductsValue(machine3.getProduct().size());
                        break;
                    case "4":
                        machine4.addProduct(fabric, amount);
                        fragment4.setMachineAmountOfProductsValue(machine4.getProduct().size());
                        break;
                }
            }
        });

        //создание кнопки запуска скудентов
        generateStudents = (Button) findViewById(R.id.generateStudents);
        generateStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VendingMachineLifeCycle lifeCycle1 = new VendingMachineLifeCycle(machine1, handler);
                VendingMachineLifeCycle lifeCycle2 = new VendingMachineLifeCycle(machine2, handler);
                VendingMachineLifeCycle lifeCycle3 = new VendingMachineLifeCycle(machine3, handler);
                VendingMachineLifeCycle lifeCycle4 = new VendingMachineLifeCycle(machine4, handler);
                lifeCycle1.start();
                lifeCycle2.start();
                lifeCycle3.start();
                lifeCycle4.start();
            }
        });

    }




    class VendingMachineLifeCycle extends Thread {
        private Machine machine;
        private Handler handler;

        public VendingMachineLifeCycle(Machine machine, Handler handler) {
            this.machine = machine;
            this.handler = handler;
        }

        @Override
        public void run() {
            for (int i = 0; i < machine.getQueue().size(); i++) {
                machine.startToWork(machine.getQueue().get(i));
                publishProgress(machine);
                machine.workingWithAClient();
                publishProgress(machine);
                machine.paying();
                publishProgress(machine);
                machine.delivering();
                publishProgress(machine);
            }
        }


        protected void publishProgress(Machine machine) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("name", machine.getName());
            bundle.putSerializable("status", machine.getStatus().toString());
            bundle.putSerializable("student", machine.getClientNumber());
            String wantToBuy = "";
            if (machine.getChoose() != null && machine.getStatus() == com.example.vendingmachine.Status.ISPAYING)
                for (int i = 0; i < machine.getChoose().length; i++) {
                    wantToBuy += machine.getProduct().get(machine.getChoose()[i]).getName() + " ";
                }
            bundle.putSerializable("productsList", wantToBuy);
            bundle.putSerializable("productsAmount", machine.getProduct().size());
            Message msg = new Message();
            msg.setData(bundle);
            handler.sendMessage(msg);
        }

    }
}