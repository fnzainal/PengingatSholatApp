package id.zainalfahrudin.pengingatsholat.presentation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.zainalfahrudin.pengingatsholat.R;

/**
 * Created by zainal on 11/7/16 - 9:32 PM.
 * Zainal Fahrudin
 * fnzainal@gmail.com
 */
public class FragmentWeekly extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.week_layout, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
