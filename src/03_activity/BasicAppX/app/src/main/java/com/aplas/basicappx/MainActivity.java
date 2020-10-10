package com.aplas.basicappx;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private Distance dist = new Distance();
    private Weight weight = new Weight();
    private Temperature temp = new Temperature();
    private Button convertBtn;
    private EditText inputTxt;
    private EditText outputTxt;
    private Spinner unitOri;
    private Spinner unitConv;
    private RadioGroup unitType;
    private CheckBox roundBox;
    private CheckBox formBox;
    private ImageView imgView;
    private AlertDialog startDialog;
    private ImageView imgForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convertBtn = (Button) findViewById(R.id.convertButton);
        inputTxt = (EditText) findViewById(R.id.inputText);
        outputTxt = (EditText) findViewById(R.id.outputText);
        unitOri = (Spinner) findViewById(R.id.oriList);
        unitConv = (Spinner) findViewById(R.id.convList);
        unitType = (RadioGroup) findViewById(R.id.radioGroup);
        roundBox = (CheckBox) findViewById(R.id.chkRounded);
        formBox = (CheckBox) findViewById(R.id.chkFormula);
        imgView = (ImageView) findViewById(R.id.img);
        imgForm = (ImageView) findViewById(R.id.imgFormula);

        unitType.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton selected = (RadioButton) findViewById(checkedId);
                        String radiobtnselected = selected.getText().toString();
                        ArrayAdapter<CharSequence> arrayAdapter;
                        if (radiobtnselected.equalsIgnoreCase("Temperature")) {
                            arrayAdapter = ArrayAdapter.createFromResource(unitType.getContext(),
                                    R.array.tempList, android.R.layout.simple_spinner_item);
                            imgView.setImageResource(R.drawable.temperature);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            unitOri.setAdapter(arrayAdapter);
                            unitConv.setAdapter(arrayAdapter);
                        } else if (radiobtnselected.equalsIgnoreCase("Distance")) {
                            arrayAdapter = ArrayAdapter.createFromResource(unitType.getContext(),
                                    R.array.distList, android.R.layout.simple_spinner_item);
                            imgView.setImageResource(R.drawable.distance);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            unitOri.setAdapter(arrayAdapter);
                            unitConv.setAdapter(arrayAdapter);
                        } else if (radiobtnselected.equalsIgnoreCase("Weight")) {
                            arrayAdapter = ArrayAdapter.createFromResource(unitType.getContext(),
                                    R.array.weightList, android.R.layout.simple_spinner_item);
                            imgView.setImageResource(R.drawable.weight);
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            unitOri.setAdapter(arrayAdapter);
                            unitConv.setAdapter(arrayAdapter);
                        }
                        inputTxt.setText("0");
                        outputTxt.setText("0");
                    }
                }
        );

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doConvert();
            }
        });
        unitOri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doConvert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        unitConv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doConvert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        roundBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doConvert();
            }
        });

        formBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doConvert();
                if(buttonView.isChecked()){
                    imgForm.setVisibility(View.VISIBLE);
                }else{
                    imgForm.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        startDialog = new AlertDialog.Builder(MainActivity.this).create();
        startDialog.setTitle("Application started");
        startDialog.setMessage("This app can use to convert to any units");
        startDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        startDialog.show();
    }

    protected void doConvert() {
        RadioButton rdGrBtn = (RadioButton) findViewById(unitType.getCheckedRadioButtonId());
        String radioBtnSelected = rdGrBtn.getText().toString();

        double hasil = convertUnit(radioBtnSelected,
                unitOri.getSelectedItem().toString(),
                unitConv.getSelectedItem().toString(),
                Double.parseDouble(inputTxt.getText().toString()));
        if (roundBox.isChecked()) {
            outputTxt.setText(strResult(hasil, true));
        } else{
            outputTxt.setText(strResult(hasil, false));
        }
    }


    protected double convertUnit(String type, String oriUnit, String convUnit, double value) {
        if ("Temperature".equalsIgnoreCase(type)) {
            return temp.convert(oriUnit, convUnit, value);
        } else if ("Distance".equalsIgnoreCase(type)) {
            return dist.convert(oriUnit, convUnit, value);
        } else if ("Weight".equalsIgnoreCase(type)) {
            return weight.convert(oriUnit, convUnit, value);
        }
        return value;
    }

    protected String strResult(double val, boolean rounded) {
        DecimalFormat df = new DecimalFormat("#.##");
        DecimalFormat df2 = new DecimalFormat("#.#####");
        String hasil = null;
        if (rounded) {
            hasil = df.format(val);
            return hasil;
        } else {
            hasil = df2.format(val);
            return hasil;
        }
    }
}