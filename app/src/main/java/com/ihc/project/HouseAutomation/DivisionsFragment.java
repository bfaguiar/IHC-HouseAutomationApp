package com.ihc.project.HouseAutomation;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class DivisionsFragment extends Fragment {

    CardView card;

    public DivisionsFragment() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_divisions, container, false);
        card = (CardView) v.findViewById(R.id.cardView);
        card.setOnClickListener(new CardView.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        FragmentManager myfragmentManager = getFragmentManager();
                                        FragmentTransaction myfragmentTransaction = myfragmentManager.beginTransaction();

                                        LRoomFragment myfragment = new LRoomFragment();

                                        myfragmentTransaction.add(R.id.navigation_divisions, myfragment);

                                       /*
                                       *
                                       * getFragmentManager().beginTransaction().hide(R.id.navigation_divisions, this).commit();
                                       *
                                       */
                                    }
                                }

        );
        return v;
    }
}