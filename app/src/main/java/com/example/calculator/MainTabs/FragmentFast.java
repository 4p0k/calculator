package com.example.calculator.MainTabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.calculator.CalculatorPackege.SaveReadSettings;
import com.example.calculator.R;

public class FragmentFast extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fast, container, false);

        CheckBox[][] arrayCheckBox = {
                {view.findViewById(R.id.a111),view.findViewById(R.id.a122),view.findViewById(R.id.a133),view.findViewById(R.id.a144)},
                {view.findViewById(R.id.a211),view.findViewById(R.id.a222),view.findViewById(R.id.a233),view.findViewById(R.id.a244)},
                {view.findViewById(R.id.a311),view.findViewById(R.id.a322),view.findViewById(R.id.a333),view.findViewById(R.id.a344)},
                {view.findViewById(R.id.a411),view.findViewById(R.id.a412),view.findViewById(R.id.a413),view.findViewById(R.id.a422)},
                {view.findViewById(R.id.a1),view.findViewById(R.id.a2),view.findViewById(R.id.a3),view.findViewById(R.id.a4)}  };

        SaveReadSettings srs = new SaveReadSettings();
        Boolean arraySetCheckBox[][] = new Boolean[5][4];
        String[] nameSign ={"add","sub","mul","div","num"};

        for (int i = 0; i < 5; i++){
            arraySetCheckBox[i] = srs.loadSetCheckBoxArray( nameSign[i],4, inflater.getContext() ) ;

            for (int j =0; j < 4; j++) {
                if (arraySetCheckBox[i][j]) {

                    arrayCheckBox[i][j].setChecked(true);
                }
            }
        }
        return view;
    }

}
