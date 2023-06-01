package sg.edu.rp.c346.id22020749.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etToDo;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    ListView lvToDo;
    Spinner spinAddRemove;

    ArrayList<String> alToDo;
    ArrayAdapter<String> aaToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etToDo = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        lvToDo = findViewById(R.id.ToDoList);
        spinAddRemove = findViewById(R.id.spinner);

        alToDo = new ArrayList<>();

        aaToDo = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alToDo);

        lvToDo.setAdapter(aaToDo);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnAdd.isEnabled()) {
                    String newToDo = etToDo.getText().toString();
                    alToDo.add(newToDo);
                    aaToDo.notifyDataSetChanged();
                    etToDo.setText("");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnDelete.isEnabled()) {
                    String index1 = etToDo.getText().toString();
                    if (!index1.isEmpty()) {
                        int index = Integer.parseInt(index1);
                        if (index >= 0 && index < alToDo.size()) {
                            alToDo.remove(index);
                            aaToDo.notifyDataSetChanged();
                            etToDo.setText("");
                        } else {
                            Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alToDo.clear();
                aaToDo.notifyDataSetChanged();
            }
        });

        spinAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Boyang","onItemSelected" + position);

                switch (position) {
                    case 0:
                        etToDo.setHint("Type in a new task here");
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;
                    case 1:
                        etToDo.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}