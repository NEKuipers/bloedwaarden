package com.example.bloedwaarden;

import android.content.Intent;
import android.graphics.Color;
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

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ScreenTwo extends AppCompatActivity {

    int bloedwaardeInt;
    String bloedwaardeText, ndate, bldate, ndatecalc, bldatecalc, gewicht, wekenZwanger, risico;
    int nyear, nmonth, nday, nhour, nminutes;
    int blyear, blmonth, blday, blhour, blminutes;
    long daysOld, hoursOld;
    int[] WTArray, FTArray;

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
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                gewicht = null;
            } else {
                gewicht = extras.getString("GEWICHT");
            }
        } else {
            gewicht = (String) savedInstanceState.getSerializable("GEWICHT");
        }
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                wekenZwanger = null;
            } else {
                wekenZwanger = extras.getString("WEKEN_ZWANGER");
            }
        } else {
            wekenZwanger = (String) savedInstanceState.getSerializable("WEKEN_ZWANGER");
        }
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                risico = null;
            } else {
                risico = extras.getString("RISICO");
            }
        } else {
            risico = (String) savedInstanceState.getSerializable("RISICO");
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

        //variabelen instellen voor risicofactoren afwezig
        if (risico.compareTo("Afwezig") == 0 && wekenZwanger.compareTo("≥38 weken") == 0) {
            WTArray = new int[]{270, 320, 370, 410, 420, 420, 420};
            FTArray = new int[]{120, 200, 260, 300, 340, 360, 360};
        }
        else if (risico.compareTo("Afwezig") == 0 && wekenZwanger.compareTo("35-37.6 weken") == 0) {
            WTArray = new int[]{240, 380, 320, 360, 380, 380, 380};
            FTArray = new int[]{85, 160, 220, 260, 290, 300, 300};
        }
        else if (risico.compareTo("Afwezig") == 0 && wekenZwanger.compareTo("<35 weken") == 0 && gewicht.compareTo(">2000 gram") == 0) {
            WTArray = new int[]{100, 250, 310, 310, 310, 310, 310};
            FTArray = new int[]{50, 180, 240, 240, 240, 240, 240};
        }
        else if (risico.compareTo("Afwezig") == 0 && wekenZwanger.compareTo("<35 weken") == 0 && gewicht.compareTo("1500-2000 gram") == 0) {
            WTArray = new int[]{100, 230, 290, 290, 290, 290, 290};
            FTArray = new int[]{50, 160, 220, 220, 220, 220, 220};
        }
        else if (risico.compareTo("Afwezig") == 0 && wekenZwanger.compareTo("<35 weken") == 0 && gewicht.compareTo("1250-1500 gram") == 0) {
            WTArray = new int[]{100, 210, 260, 260, 260, 260, 260};
            FTArray = new int[]{50, 140, 190, 190, 190, 190, 190};
        }
        else if (risico.compareTo("Afwezig") == 0 && wekenZwanger.compareTo("<35 weken") == 0 && gewicht.compareTo("1000-1250 gram") == 0) {
            WTArray = new int[]{100, 190, 220, 220, 220, 220, 220};
            FTArray = new int[]{50, 120, 150, 150, 150, 150, 150};
        }
        else if (risico.compareTo("Afwezig") == 0 && wekenZwanger.compareTo("<35 weken") == 0 && gewicht.compareTo("<1000 gram") == 0) {
            WTArray = new int[]{100, 170, 170, 170, 170, 170, 170};
            FTArray = new int[]{50, 100, 100, 100, 100, 100, 100};
        }

        //risicogroepen aanwezig
        else if (risico.compareTo("Aanwezig") == 0 && wekenZwanger.compareTo("≥38 weken") == 0) {
            WTArray = new int[]{240, 280, 320, 360, 380, 380, 380};
            FTArray = new int[]{85, 160, 220, 260, 290, 300, 300};
        }
        else if (risico.compareTo("Aanwezig") == 0 && wekenZwanger.compareTo("35-37.6 weken") == 0) {
            WTArray = new int[]{200, 250, 290, 310, 320, 320, 320};
            FTArray = new int[]{70, 130, 190, 230, 250, 260, 260};
        }
        else if (risico.compareTo("Aanwezig") == 0 && wekenZwanger.compareTo("<35 weken") == 0 && gewicht.compareTo(">2000 gram") == 0) {
            WTArray = new int[]{100, 230, 290, 290, 290, 290, 290};
            FTArray = new int[]{50, 160, 220, 220, 220, 220, 220};
        }
        else if (risico.compareTo("Aanwezig") == 0 && wekenZwanger.compareTo("<35 weken") == 0 && gewicht.compareTo("1500-2000 gram") == 0) {
            WTArray = new int[]{100, 210, 260, 260, 260, 260, 260};
            FTArray = new int[]{50, 140, 190, 190, 190, 190, 190};
        }
        else if (risico.compareTo("Aanwezig") == 0 && wekenZwanger.compareTo("<35 weken") == 0 && gewicht.compareTo("1250-1500 gram") == 0) {
            WTArray = new int[]{100, 190, 220, 220, 220, 220, 220};
            FTArray = new int[]{50, 120, 150, 150, 150, 150, 150};
        }
        else if (risico.compareTo("Aanwezig") == 0 && wekenZwanger.compareTo("<35 weken") == 0 &&
                (gewicht.compareTo("1000-1250 gram") == 0 || gewicht.compareTo("<1000 gram") == 0)) {
            WTArray = new int[]{100, 170, 170, 170, 170, 170, 170};
            FTArray = new int[]{50, 100, 100, 100, 100, 100, 100};
        }
        else {
            WTArray = new int[]{0, 500, 0, 500, 0, 500, 0};
            FTArray = new int[]{500, 0, 500, 0, 500, 0, 500};
        }


        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> curveWisselTransfusie = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, WTArray[0]),
                new DataPoint(24, WTArray[1]),
                new DataPoint(48, WTArray[2]),
                new DataPoint(72, WTArray[3]),
                new DataPoint(96, WTArray[4]),
                new DataPoint(120, WTArray[5]),
                new DataPoint(144, WTArray[6])
        });
        LineGraphSeries<DataPoint> curveFotoTherapie = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, FTArray[0]),
                new DataPoint(24, FTArray[1]),
                new DataPoint(48, FTArray[2]),
                new DataPoint(72, FTArray[3]),
                new DataPoint(96, FTArray[4]),
                new DataPoint(120, FTArray[5]),
                new DataPoint(144, FTArray[6])
        });
        LineGraphSeries<DataPoint> biliwaarde = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(hoursOld, bloedwaardeInt),
                new DataPoint(hoursOld, bloedwaardeInt + 1),
        });



        graph.addSeries(curveFotoTherapie);
        graph.addSeries(curveWisselTransfusie);
        graph.addSeries(biliwaarde);
        curveFotoTherapie.setColor(Color.CYAN);
        biliwaarde.setColor(Color.RED);
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setVerticalAxisTitle("TSB umol/l");
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(144);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(WTArray[6] >= 400 ? 600 : (WTArray[6] >= 200 ? 400 : 200));
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);

    }
}
