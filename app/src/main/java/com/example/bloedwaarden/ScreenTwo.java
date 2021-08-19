package com.example.bloedwaarden;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ScreenTwo extends AppCompatActivity {

    int bloedwaardeInt;
    String bloedwaardeText, ndate, bldate, ndatecalc, bldatecalc;
    int nyear, nmonth, nday, nhour, nminutes;
    int blyear, blmonth, blday, blhour, blminutes;
    long daysOld, hoursOld;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                bloedwaardeInt = 0;
            } else {
                bloedwaardeInt = extras.getInt("BLOEDWAARDE_INT");
            }
        } else {
            bloedwaardeInt = (int) savedInstanceState.getSerializable("BLOEDWAARDE_INT");
        }
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                nyear = 0;
            } else {
                nyear = extras.getInt("GEBOORTEDATUM_Y");
            }
        } else {
            nyear = (int) savedInstanceState.getSerializable("GEBOORTEDATUM_Y");
        }
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                nmonth = 0;
            } else {
                nmonth = extras.getInt("GEBOORTEDATUM_M");
            }
        } else {
            nmonth = (int) savedInstanceState.getSerializable("GEBOORTEDATUM_M");
        }
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                nday = 0;
            } else {
                nday = extras.getInt("GEBOORTEDATUM_D");
            }
        } else {
            nday = (int) savedInstanceState.getSerializable("GEBOORTEDATUM_D");
        }
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                nhour = 0;
            } else {
                nhour = extras.getInt("GEBOORTETIJD_H");
            }
        } else {
            nhour = (int) savedInstanceState.getSerializable("GEBOORTETIJD_H");
        }
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                nminutes = 0;
            } else {
                nminutes = extras.getInt("GEBOORTETIJD_M");
            }
        } else {
            nminutes = (int) savedInstanceState.getSerializable("GEBOORTETIJD_M");
        }
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                blyear = 0;
            } else {
                blyear = extras.getInt("BLOEDPRIKDATUM_Y");
            }
        } else {
            blyear = (int) savedInstanceState.getSerializable("BLOEDPRIKDATUM_Y");
        }
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                blmonth = 0;
            } else {
                blmonth = extras.getInt("BLOEDPRIKDATUM_M");
            }
        } else {
            blmonth = (int) savedInstanceState.getSerializable("BLOEDPRIKDATUM_M");
        }
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                blday = 0;
            } else {
                blday = extras.getInt("BLOEDPRIKDATUM_D");
            }
        } else {
            blday = (int) savedInstanceState.getSerializable("BLOEDPRIKDATUM_D");
        }
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                blhour = 0;
            } else {
                blhour = extras.getInt("BLOEDPRIKTIJD_H");
            }
        } else {
            blhour = (int) savedInstanceState.getSerializable("BLOEDPRIKTIJD_H");
        }
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                blminutes = 0;
            } else {
                blminutes = extras.getInt("BLOEDPRIKTIJD_M");
            }
        } else {
            blminutes = (int) savedInstanceState.getSerializable("BLOEDPRIKTIJD_M");
        }
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                ndate = null;
            } else {
                ndate = extras.getString("GEBOORTEDATUM");
            }
        } else {
            ndate = (String) savedInstanceState.getSerializable("GEBOORTEDATUM");
        }
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                bldate = null;
            } else {
                bldate = extras.getString("BLOEDPRIKDATUM");
            }
        } else {
            bldate = (String) savedInstanceState.getSerializable("BLOEDPRIKDATUM");
        }

        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            String ndatetime = ndate + " " + Integer.toString(nhour) + ":" + Integer.toString(nminutes);
            String bldatetime = bldate + " " + Integer.toString(blhour) + ":" + Integer.toString(blminutes);
            Date date1 = myFormat.parse(ndatetime);
            Date date2 = myFormat.parse(bldatetime);
            hoursOld = TimeUnit.HOURS.convert(date2.getTime() - date1.getTime(), TimeUnit.MILLISECONDS);
            daysOld = hoursOld / 24;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        bloedwaardeText = Integer.toString(bloedwaardeInt);
        setContentView(R.layout.layout_screentwo);
        TextView bloedWaardeTextView = (TextView) findViewById(R.id.blwCijfer);
        bloedWaardeTextView.setText("Ingevulde bloedwaarde: " + bloedwaardeText);
        TextView geboortedatumTextView = (TextView) findViewById(R.id.geboorteDatum);
        geboortedatumTextView.setText("Patiënt is geboren op " + ndate + " om " + ((nhour >= 10) ? "" : "0") + Integer.toString(nhour) + ":" + ((nminutes >= 10) ? "" : "0") + Integer.toString(nminutes) );
        TextView bloedprikdatumTextView = (TextView) findViewById(R.id.bloedprikDatum);
        bloedprikdatumTextView.setText("Bloed is afgenomen op " + bldate + " om " + ((blhour >= 10) ? "" : "0") + Integer.toString(blhour) + ":" + ((blminutes >= 10) ? "" : "0") + Integer.toString(blminutes) );
        TextView daysOldTextView = (TextView) findViewById(R.id.daysOldText);

        if (hoursOld <= 96)
            daysOldTextView.setText("Patiënt is " + Long.toString(hoursOld) + " uur oud.");
        else if (daysOld < 7)
            daysOldTextView.setText("Patiënt is " + Long.toString(daysOld) + " dagen en " + Long.toString(hoursOld % 24) + " uur oud.");
        else
            daysOldTextView.setText("Patiënt is " + Long.toString(daysOld / 7) + " weken, " + Long.toString(daysOld % 7) + " dagen, en " + Long.toString(hoursOld % 24) + " uur oud.");

        ImageView bilicurveGraph = (ImageView) findViewById(R.id.graph);
        int imageResource = getResources().getIdentifier("@drawable/bilicurve",
                null, this.getPackageName());
        bilicurveGraph.setImageResource(imageResource);

    }
}
