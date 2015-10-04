package com.bignerdranch.android.basicviews5;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] mPresidentsArray;
    private Button mShowItemsButton;
    private ListView mPresidentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get a reference to the Show Selected button
        mShowItemsButton = (Button) findViewById(R.id.selected_button);
        mShowItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemsSelected = "Selected items: \n";
                for (int i = 0; i < mPresidentsList.getCount(); i++) {
                    if (mPresidentsList.isItemChecked(i)) {
                        itemsSelected += mPresidentsList.getItemAtPosition(i) + "\n";
                    }
                }

                Toast.makeText(MainActivity.this, itemsSelected, Toast.LENGTH_LONG).show();
            }
        });

        //get a reference to the ListView
        mPresidentsList = (ListView) findViewById(R.id.presidents_list);
        mPresidentsList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        mPresidentsList.setTextFilterEnabled(true);
        mPresidentsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick (AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this,
                        "You have selected " + mPresidentsArray[position],
                        Toast.LENGTH_SHORT).show();
            }
        });

        mPresidentsArray = getResources().getStringArray(R.array.presidents_array);

        ListAdapter presidentListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, mPresidentsArray);
        mPresidentsList.setAdapter(presidentListAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
