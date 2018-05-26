package com.ihc.project.HouseAutomation;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.*;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RadioButton rd;
    CardView cardMoods;
    RadioGroup radioGrooupMoods;
    RadioButton radioButtonClicked;
    GraphView graph;
    ImageView img;
    CardView cardGraphPlot;
    CardView cardTemperature;
    TextView text;

    private static final Random RANDOM = new Random();
    private LineGraphSeries<DataPoint> series;
    private int lastX = 0;
    DataPoint[] dataPoints = new DataPoint[7];


    DataCommunicationHome mCallback;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        cardTemperature = view.findViewById(R.id.card);
        img=    view.findViewById(R.id.imageView7);
        Glide.with(this).load(R.drawable.thermostat_icon_png).into(img);text=view.findViewById(R.id.textView3);
        Integer n =(RANDOM.nextInt(40));
        text.setText((n.toString()+ "ºC"));
        cardGraphPlot = view.findViewById(R.id.card_graph);
        graph     = view.findViewById(R.id.graph_plot);
        //data graph  house.
        series        = new LineGraphSeries<DataPoint>( new DataPoint[] {
            new DataPoint(1, RANDOM.nextDouble() * 400),
                new DataPoint(2, RANDOM.nextDouble() * 400),
                new DataPoint(3, RANDOM.nextDouble() * 400),
                new DataPoint(4, RANDOM.nextDouble() * 400),
                new DataPoint(5, RANDOM.nextDouble() * 400),
                new DataPoint(6, RANDOM.nextDouble() * 400),
                new DataPoint(7, RANDOM.nextDouble() * 400),
        });

        series.setThickness(8); // graph line grossura.
        series.setDrawDataPoints(true);

        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setVerticalAxisTitle("KW/h Avg.");

        // GraphView 4.x
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"Mon.", "Tue.", "Wed.", "Thu.", "Fri.", "Sat.", "Sun."});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);


        graph.addSeries(series);

        // customize a little bit viewport
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(400);
        viewport.setScrollable(true);






        radioGrooupMoods   = view.findViewById(R.id.radiogroup);
        cardMoods = view.findViewById(R.id.card_moods);

        radioGrooupMoods.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.party:
                        cardMoods.setBackgroundResource(R.drawable.party_mood_jpeg);
                        radioGrooupMoods.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.lightPink));
                        break;
                    case R.id.night_mode:
                        cardMoods.setBackgroundResource(R.drawable.out_mood_png);
                        radioGrooupMoods.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.lightBlue));
                        break;
                    case R.id.economic:
                        radioGrooupMoods.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.lightGreen));
                        cardMoods.setBackgroundResource(R.drawable.ecologic_mood_jpg);
                        break;
                    case R.id.off:
                        radioGrooupMoods.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorBackgnrd));
                        cardMoods.setBackgroundColor(Color.WHITE);
                        break;
                }
            }
        });

        return view;
    }

}
