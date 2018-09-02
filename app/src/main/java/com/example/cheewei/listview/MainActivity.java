package com.example.cheewei.listview;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button save;
    EditText txtItemName;
    EditText txtPrice;
    ListView listView;
    Item cart;
    TextView tv;
    ArrayList <Item> itemlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtItemName = (EditText) findViewById(R.id.ItemName);
        txtPrice = (EditText) findViewById(R.id.Price);
        save = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listview);
        tv = (TextView)findViewById(R.id.textView);

        final ItemListAdapter adapter = new ItemListAdapter (this, R.layout.adapter_view,itemlist);
        listView.setAdapter(adapter);

        save.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                if (txtItemName.getText().toString().equals("")){
                    txtItemName.setError("The item name cannot be empty");

                }
                else if (txtPrice.getText().toString().equals("")){
                    txtPrice.setError("The price cannot be empty");
                }
                else if (txtItemName.getText().toString().equals("") && txtPrice.getText().toString().equals("")){
                    txtItemName.setError("The item name cannot be empty");
                    txtPrice.setError("The price cannot be empty");
                }
                else{

                    String getinputitemname = txtItemName.getText().toString();
                    double getprice = Double.parseDouble(txtPrice.getText().toString());

                    cart = new Item(getinputitemname, getprice);
                    itemlist.add(cart);
                    listView.setAdapter(adapter);
                    calTotal(tv);
                    Toast.makeText(getApplicationContext(),"Insert Successful",Toast.LENGTH_SHORT).show();
                    txtItemName.setText("");
                    txtPrice.setText("");
                }
            }

        });


       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                AlertDialog.Builder msg = new AlertDialog.Builder(MainActivity.this);
                msg.setTitle("Alert");
                msg.setMessage("Are you sure you want to delete this item?");
                msg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        itemlist.remove(position);
                        calTotal(tv);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(),"Your have delete one item",Toast.LENGTH_SHORT).show();
                    }
                });
                msg.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                msg.setCancelable(true);
                msg.create().show();

            }
        });

      }

    public void calTotal (TextView tv) {

        double total = 0.0;
        for (int i = 0; i < itemlist.size(); i++) {
            total += itemlist.get(i).getPrice();
        }
        tv.setText(String.valueOf(total) );
    }
}


