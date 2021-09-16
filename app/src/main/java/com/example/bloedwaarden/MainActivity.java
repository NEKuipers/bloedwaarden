package com.example.bloedwaarden;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    // variabelen beginnend met n zijn geboortedatum/tijd
    // variabelen beginned met bl zijn bloedafname datum/tijd
    private TextView nDisplayDate, nDisplayTime, blDisplayDate, blDisplayTime;
    private DatePickerDialog.OnDateSetListener nDateSetListener;
    private TimePickerDialog.OnTimeSetListener nTimeSetListener;
    private DatePickerDialog.OnDateSetListener blDateSetListener;
    private TimePickerDialog.OnTimeSetListener blTimeSetListener;
    int nyear, nmonth, nday, nhour, nminutes;
    int blyear, blmonth, blday, blhour, blminutes;
    private EditText bloedWaardeInvoer;
    int bloedWaarde = 0;
    String ndate, bldate;
    String weight, weeks, risico;
    boolean resetNDisplayDate = false;
    boolean resetNDisplayTime = false;
    boolean resetBLDisplayDate = false;
    boolean resetBLDisplayTime = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bloedWaardeInvoer = (EditText)findViewById(R.id.bloedwaarde);


        Button btnNavToSecond = (Button) findViewById(R.id.goOnButton);
        btnNavToSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNavToSecond.requestFocusFromTouch();
                try {
                    bloedWaarde = Integer.parseInt(bloedWaardeInvoer.getText().toString());
                }   catch(NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
                if (TextUtils.isEmpty(bloedWaardeInvoer.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Voer een bloedwaarde in.", Toast.LENGTH_LONG).show();
                        return ;
                }
                if (bloedWaarde > 600) {
                    Toast.makeText(getApplicationContext(), "Bloedwaarde is te hoog.", Toast.LENGTH_LONG).show();
                    return ;
                }
                if (bloedWaarde <= 0) {
                    Toast.makeText(getApplicationContext(), "Bloedwaarde is te laag.", Toast.LENGTH_LONG).show();
                    return ;
                }
                if (nDisplayDate.getText().toString().matches("") || resetNDisplayDate == true) {
                    Toast.makeText(getApplicationContext(), "Vul een geboortedatum in.", Toast.LENGTH_LONG).show();
                    return ;
                }
                if (blDisplayDate.getText().toString().matches("") || resetBLDisplayDate == true) {
                    Toast.makeText(getApplicationContext(), "Vul een metingdatum in.", Toast.LENGTH_LONG).show();
                    return ;
                }
                if (nDisplayTime.getText().toString().matches("") || resetNDisplayTime == true) {
                    Toast.makeText(getApplicationContext(), "Vul een geboortetijd in.", Toast.LENGTH_LONG).show();
                    return ;
                }
                if (blDisplayTime.getText().toString().matches("") || resetBLDisplayTime == true) {
                    Toast.makeText(getApplicationContext(), "Vul een metingtijd in.", Toast.LENGTH_LONG).show();
                    return ;
                }
                Intent intent = new Intent(MainActivity.this, ScreenTwo.class);
                intent.putExtra("BLOEDWAARDE_INT", bloedWaarde);
                intent.putExtra("GEBOORTEDATUM", ndate);
                intent.putExtra("GEBOORTEDATUM_Y", nyear);
                intent.putExtra("GEBOORTEDATUM_M", nmonth);
                intent.putExtra("GEBOORTEDATUM_D", nday);
                intent.putExtra("GEBOORTETIJD_H", nhour);
                intent.putExtra("GEBOORTETIJD_M", nminutes);
                intent.putExtra("BLOEDPRIKDATUM", bldate);
                intent.putExtra("BLOEDPRIKDATUM_Y", blyear);
                intent.putExtra("BLOEDPRIKDATUM_M", blmonth);
                intent.putExtra("BLOEDPRIKDATUM_D", blday);
                intent.putExtra("BLOEDPRIKTIJD_H", blhour);
                intent.putExtra("BLOEDPRIKTIJD_M", blminutes);
                intent.putExtra("WEKEN_ZWANGER", weeks);
                intent.putExtra("GEWICHT", weight);
                intent.putExtra("RISICO", risico);
                startActivity(intent);
            }
        });

        Button btnRiskInfo = (Button) findViewById(R.id.buttonRiskInfo);
        btnRiskInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 2; i++) {
                    Toast.makeText(getApplicationContext(), "Risicofactoren:\n - Bloedgroepanatonismen (ABO, Rh, e.a.)\n - Hemolyse (G6PD, sferocytose e.a.\n - Asfyxie: AS <5 (5') of pH NA <7.0\n - Ziek, suf, (verdenking) infectie\n - Serum albumine <30 g/l", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button btnReset = (Button) findViewById(R.id.resetButton);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnReset.requestFocusFromTouch();
                bloedWaardeInvoer.setText("");
                nDisplayTime.setText("00:00");
                blDisplayTime.setText("00:00");
                nDisplayDate.setText("01/01/2021");
                blDisplayDate.setText("01/01/2021");
                nTimeSetListener = null;
                blTimeSetListener = null;
                resetBLDisplayDate = true;
                resetBLDisplayTime = true;
                resetNDisplayDate = true;
                resetNDisplayTime = true;
            }
        });

        nDisplayTime = (TextView) findViewById(R.id.nTime);
        nDisplayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Panel,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                nhour = hourOfDay;
                                nminutes = minute;
                                Calendar caltime = Calendar.getInstance();
                                caltime.set(0,0,0,nhour, nminutes);
                                nDisplayTime.setText(DateFormat.format("HH:mm", caltime));
                                resetNDisplayTime = false;
                            }
                        }, 0, 0, true
                );
                timePickerDialog.updateTime(nhour, nminutes);
                timePickerDialog.show();
            }
        });

        blDisplayTime = (TextView) findViewById(R.id.bloodTime);
        blDisplayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Light_Panel,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                blhour = hourOfDay;
                                blminutes = minute;
                                Calendar caltime = Calendar.getInstance();
                                caltime.set(0,0,0,blhour, blminutes);
                                blDisplayTime.setText(DateFormat.format("HH:mm", caltime));
                                resetBLDisplayTime = false;
                            }
                        }, 0, 0, true
                );
                timePickerDialog.updateTime(blhour, blminutes);
                timePickerDialog.show();
            }
        });

        nDisplayDate = (TextView) findViewById(R.id.nDate);
        nDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                nyear = cal.get(Calendar.YEAR);
                nmonth = cal.get(Calendar.MONTH);
                nday = cal.get(Calendar.DAY_OF_MONTH);
                resetNDisplayDate = false;
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_DeviceDefault, nDateSetListener, nyear, nmonth, nday);
                dialog.show();
            }
        });

        blDisplayDate = (TextView) findViewById(R.id.bloodDate);
        blDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                blyear = cal.get(Calendar.YEAR);
                blmonth = cal.get(Calendar.MONTH);
                blday = cal.get(Calendar.DAY_OF_MONTH);
                resetBLDisplayDate = false;
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_DeviceDefault, blDateSetListener, blyear, blmonth, blday);
                dialog.show();
            }
        });

        nDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            month = month + 1;
            if (month < 10) {
                if (dayOfMonth < 10) {
                    ndate = "0" + dayOfMonth + "/" + "0" + month + "/" + year;
                } else {
                    ndate = dayOfMonth + "/" + "0" + month + "/" + year;
                }
            } else {
                if (dayOfMonth < 10) {
                    ndate = "0" + dayOfMonth + "/" + month + "/" + year;
                } else {
                    ndate = dayOfMonth + "/" + month + "/" + year;
                }
            }
            nDisplayDate.setText(ndate);
            }
        };
        blDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                if (month < 10) {
                    if (dayOfMonth < 10) {
                        bldate = "0" + dayOfMonth + "/" + "0" + month + "/" + year;
                    } else {
                        bldate = dayOfMonth + "/" + "0" + month + "/" + year;
                    }
                } else {
                    if (dayOfMonth < 10) {
                        bldate = "0" + dayOfMonth + "/" + month + "/" + year;
                    } else {
                        bldate = dayOfMonth + "/" + month + "/" + year;
                    }
                }
                blDisplayDate.setText(bldate);
            }
        };

        Spinner spinnerGewicht = findViewById(R.id.spinnerGewicht);
        ArrayAdapter<CharSequence> adapterGewicht = ArrayAdapter.createFromResource(this, R.array.arrayGewicht, android.R.layout.simple_spinner_item);
        adapterGewicht.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGewicht.setAdapter(adapterGewicht);
        spinnerGewicht.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                weight = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner spinnerWekenZwanger = findViewById(R.id.spinnerWekenZwanger);
        ArrayAdapter<CharSequence> adapterWekenZwanger = ArrayAdapter.createFromResource(this, R.array.arrayWekenZwanger, android.R.layout.simple_spinner_item);
        adapterWekenZwanger.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWekenZwanger.setAdapter(adapterWekenZwanger);
        spinnerWekenZwanger.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                weeks = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinnerRisico = findViewById(R.id.spinnerRisico);
        ArrayAdapter<CharSequence> adapterRisico = ArrayAdapter.createFromResource(this, R.array.arrayRisicofactoren, android.R.layout.simple_spinner_item);
        adapterRisico.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRisico.setAdapter(adapterRisico);
        spinnerRisico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                risico = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}