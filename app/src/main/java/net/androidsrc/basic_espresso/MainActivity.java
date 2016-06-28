package net.androidsrc.basic_espresso;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    private RadioButton radio1,radio2;
    private EditText mEditText;
    private CheckBox checkBox;
    private Switch mSwitch;
    private SeekBar mSeekBar;
    private Spinner spinner;
    private Button submitButton;
    String spinnerValue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radio1=(RadioButton)findViewById(R.id.radioButton1);
        radio2=(RadioButton)findViewById(R.id.radioButton2);
        mEditText = (EditText) findViewById(R.id.editTextUserInput);
        checkBox=(CheckBox)findViewById(R.id.checkBox);
        mSwitch=(Switch)findViewById(R.id.switch1);
        mSeekBar=(SeekBar)findViewById(R.id.seekBar);
        spinner= (Spinner) findViewById(R.id.spinner);
        submitButton=(Button)findViewById(R.id.buttonSubmit);

        // Set the listeners for the buttons.
        findViewById(R.id.buttonSubmit).setOnClickListener(this);


        // Spinner Drop down elements
        final List<String> sampleData = new ArrayList<String>();
        sampleData.add("Hello");
        sampleData.add("Welcome");
        sampleData.add("to");
        sampleData.add("AndroidSRC.net");

        //set default value
        spinnerValue=sampleData.get(0);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sampleData);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerValue=sampleData.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSubmit:
                Intent intent=new Intent(MainActivity.this,ShowDataActivity.class);
                String data="";
                //get data from all elements
                if(radio1.isChecked()){
                    data+=" Radio1 button is clicked,\n";
                }else{
                    data+=" Radio2 button is clicked,\n";
                }

                data+="\""+mEditText.getText().toString()+"\""+" is written in editText,\n";
                if(checkBox.isChecked()){
                    data+=" checkBox is checked,\n";
                }else{
                    data+=" checkBox is not checked,\n";
                }

                if(mSwitch.isChecked()){
                    data+=" switch is On,\n";
                }else{
                    data+=" switch is Off,\n";
                }

                data+=" SeekBar is at "+mSeekBar.getProgress()+"%\n";

                data+="\""+spinnerValue+"\" is selected on spinner";

                intent.putExtra("data",data);
                startActivity(intent);

                break;
        }
    }
}
