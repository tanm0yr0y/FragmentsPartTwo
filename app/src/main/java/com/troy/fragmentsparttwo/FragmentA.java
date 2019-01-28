package com.troy.fragmentsparttwo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentA extends Fragment implements AdapterView.OnItemClickListener {

    ListView listView;
    Communicator communicator;
    int saveState = -1;
    int position = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        Log.d("RRR", "onCreateView saveState: "+saveState);
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Log.d("RRR", "onActivityCreated saveState: "+saveState);

        communicator = (Communicator) getActivity();
        listView = getActivity().findViewById(R.id.listview_a);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.titles, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        //Log.d("ROYs", savedInstanceState.toString());

        if(savedInstanceState != null) {
            Log.d("RRR", "savedInstanceState");
            position = savedInstanceState.getInt("state");
            Log.d("RRR", "savedInstanceState position: "+position);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(position != -1) {
            Log.d("RRR", "position not equal to minus one");
            communicator.respond(position);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("RRR", "onSaveInstanceState");
        if(saveState != -1) {
            Log.d("RRR", "saveState not equal to minus one");
            outState.putInt("state", saveState);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        saveState = position;
        Log.d("RRR", "onItemClicked saveState: "+saveState);
        communicator.respond(position);
    }
}
