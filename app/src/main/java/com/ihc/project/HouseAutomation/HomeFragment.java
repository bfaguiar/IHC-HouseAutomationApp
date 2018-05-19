package com.ihc.project.HouseAutomation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.view.View;
import android.widget.RadioButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RadioButton rd;

    public HomeFragment() {
        // Required empty public constructor
    }


    public void OnCreate() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        
        return view;
    }

}
