package com.ihc.project.HouseAutomation;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class DivisionsFragment extends AppCompatDialogFragment
{

    private EditText editname, editdivision;
    private RecyclerView mRecyclerView;
    private ListAdapter mListadapter;
    private FloatingActionButton button;
    private ArrayList<Division> array;
    private String name, division;

    DataCommunicationHome mCallback;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_divisions, container, false);
        array = new ArrayList<>();
        //Ir buscar informação ao dialogo
        button =  view.findViewById(R.id.botaoimagem);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                final View viewDialog = getLayoutInflater().inflate(R.layout.layout_dialog,null);
                editname = (EditText) viewDialog.findViewById(R.id.div_name);
                editdivision = (EditText) viewDialog.findViewById(R.id.div_type);

                mBuilder.setPositiveButton("Create!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        name = editname.getText().toString();
                        division = editdivision.getText().toString();
                        array.add(
                                new Division(name, division, R.drawable.living_room )
                        );
                    }
                });
                mBuilder.setNegativeButton("Exit!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialogInterface.dismiss();
                    }
                });

                mBuilder.setView(viewDialog);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        array.add(
                new Division("Primary Room", "Living Room", R.drawable.living_room)
        );



        mListadapter = new ListAdapter(array);
        mRecyclerView.setAdapter(mListadapter);

        /*
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {

            ArrayList<Device> listaAuxiliar = new ArrayList<>();
            @Override
            public void onClick(View view, int position) {
                Division division = array.get(position);
                for(Device device : mCallback.getArrayListDevices())
                    if(division.getName().equalsIgnoreCase(device.getNameDivision()))
                        listaAuxiliar.add(device);
                mCallback.setArrayListDevices(listaAuxiliar);
                mCallback.setIDButtonDevices(0);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        */

        return view;
    }

    public void showDevicesListFragment(int rID)
    {
        mCallback.setIDButtonDevices(rID);

        Fragment fr = new DevicesListFragment();

        FragmentChangeListener fc=(FragmentChangeListener)getActivity();
        fc.replaceFragment(fr);

    }



    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
    {
        private ArrayList<Division> dataList;

        public ListAdapter(ArrayList<Division> array)
        {
            this.dataList = array;
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView textViewText;
            TextView textViewComment;
            ImageView imageViewImage;

            public ViewHolder(View itemView)
            {
                super(itemView);
                this.textViewText = (TextView) itemView.findViewById(R.id.division_name);
                this.textViewComment = (TextView) itemView.findViewById(R.id.division_type);
                this.imageViewImage = (ImageView) itemView.findViewById(R.id.image);
            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position)
        {
            holder.textViewText.setText(dataList.get(position).getName());
            holder.textViewComment.setText(dataList.get(position).getType());
            holder.imageViewImage.setImageResource(dataList.get(position).getImage());

        }

        @Override
        public int getItemCount()
        {
            return dataList.size();
        }

        public int getSelectedID(int position) {
            return dataList.get(position).getID();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (DataCommunicationHome) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement DataCommunication");
        }
    }
}