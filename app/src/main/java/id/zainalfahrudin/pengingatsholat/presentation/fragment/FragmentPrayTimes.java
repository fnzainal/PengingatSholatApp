package id.zainalfahrudin.pengingatsholat.presentation.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import id.zainalfahrudin.pengingatsholat.R;
import id.zainalfahrudin.pengingatsholat.data.model.WaktuSholatHarianModel;

/**
 * Created by zainal on 11/7/16 - 8:02 PM.
 * Zainal Fahrudin
 * fnzainal@gmail.com
 */
public class FragmentPrayTimes extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private TextView tvFajr, tvDzuhur, tvAshar, tvMaghrib, tvIsya;
    private String TAG = getClass().getSimpleName();
    private SwipeRefreshLayout swipe;
    private CardView cvDate;
    private String day, month, year;
    private String method = "4";
    private String namaKota = "semarang";
    private String date;
    private TextView tvDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_waktu_sholat, container, false);
        tvDate = (TextView)view.findViewById(R.id.tvDate);
        tvFajr = (TextView) view.findViewById(R.id.tvFajr);
        tvDzuhur = (TextView) view.findViewById(R.id.tvDzuhur);
        tvAshar = (TextView) view.findViewById(R.id.tvAshar);
        tvMaghrib = (TextView) view.findViewById(R.id.tvMaghrib);
        tvIsya = (TextView) view.findViewById(R.id.tvIsya);
        swipe = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefresh);
        cvDate = (CardView) view.findViewById(R.id.cvCurrentDate);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        swipe.setOnRefreshListener(this);
        cvDate.setOnClickListener(this);

        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        date = df.format(Calendar.getInstance().getTime());


        getPrayTimes(namaKota, method, date);

        int jumlah = 2 + getInteger(23, "", 2.1);

        tvDate.setText(
                String.valueOf(jumlah)
        );

        tvDate.setText(getString());

    }

    private String getString(){
        String s = "ini string";

        return s;
    }

    private int getInteger(int o, String string1, double dobel){
        int i = 10;
        return i;
    }

    private void getVoid(){
        tvDate.setText("qweasd");
    }

    void getPrayTimes(String namaKota, String method, String date) {
//        String stringdate = "12-11-2016";
//        String[] strings = date.split("-");
        String[] strings = date.split("-"); //3
        day = strings[0];
        month = strings[1];
        year = strings[2];

        swipe.setRefreshing(true);
        String url = "http://ibacor.com/api/pray-times?address="+namaKota
                +"&timezone=7&method=" +method
                +"&year="+year+"&month="+month+"&day="+day;

        Log.d(TAG, "getPrayTimes: url="+url);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: "+response);
                WaktuSholatHarianModel model = new Gson().fromJson(response, WaktuSholatHarianModel.class);

                stopRefresing();
                if (model.getStatus().equals("success")) {

                    tvFajr.setText(model.getResult().getFajr());
                    tvDzuhur.setText(model.getResult().getDhuhr());
                    tvAshar.setText(model.getResult().getAsr());
                    tvMaghrib.setText(model.getResult().getMaghrib());
                    tvIsya.setText(model.getResult().getIsha());

                    Snackbar.make(tvIsya, "updated..", Snackbar.LENGTH_SHORT).show();

                }else{
                    Snackbar.make(tvIsya, model.getStatus(), Snackbar.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(tvIsya, error.getMessage(), Snackbar.LENGTH_SHORT).show();
                stopRefresing();
            }
        });
        requestQueue.add(stringRequest);

    }

    private void stopRefresing() {
        if (swipe.isRefreshing()){
            swipe.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        getPrayTimes(namaKota, method, date);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cvCurrentDate:
                showDialog();
                break;
        }
    }

    private void showDialog() {
        Log.d(TAG, "showDialog: clicked");
        final AlertDialog.Builder builder;

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View modifyView = inflater.inflate(R.layout.layout_custom_data, null);

        final EditText editTextKota = (EditText) modifyView.findViewById(R.id.etNamaKota);
        Spinner spinner = (Spinner) modifyView.findViewById(R.id.spinnerMethod);
        final DatePicker datePicker = (DatePicker) modifyView.findViewById(R.id.datePicker);

        final Calendar c = Calendar.getInstance();
        final int intYear = c.get(Calendar.YEAR);
        final int intMonth = c.get(Calendar.MONTH);
        final int intDay = c.get(Calendar.DAY_OF_MONTH);

        editTextKota.setText(namaKota);


        final List<String> list = new ArrayList<>();
        list.add("Shia Ithna-Ashari");
        list.add("University of Islamic Sciences, Karachi");
        list.add("Islamic Society of North America (ISNA)");
        list.add("Muslim World League (MWL)");
        list.add("Umm al-Qura, Makkah");
        list.add("Egyptian General Authority of Survey");
        list.add("Custom Setting");
        list.add("Institute of Geophysics, University of Tehran");

        datePicker.init(intYear, intMonth, intDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int mount, int day) {
                date = String.valueOf(day+"-"+mount+"-"+year);
                Log.d(TAG, "onDateChanged: date= "+date);

                String[] daysArray = new String[] {"saturday","sunday","monday","tuesday","wednesday","thursday","friday"};

                String stringDay = "";

                int dayOfWeek =0;
                SimpleDateFormat formatter = new SimpleDateFormat(date);
                Date dateUtils;
                try {
                    dateUtils = formatter.parse(date);
                    Calendar c = Calendar.getInstance();
                    c.setTime(dateUtils);
                    dayOfWeek = c.get(Calendar.DAY_OF_WEEK)-1;
                    if (dayOfWeek < 0) {
                        dayOfWeek += 7;
                    }
                    stringDay  = daysArray[dayOfWeek];
                } catch (Exception e) {
                    e.printStackTrace();
                }


                tvDate.setText(stringDay +", "+date);
            }
        });

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                method = String.valueOf(position);

                String selected = list.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        builder = new AlertDialog.Builder(getActivity())
                .setView(R.layout.layout_custom_data)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (editTextKota.getText().toString().length()>4) {
                            namaKota = editTextKota.getText().toString();
                            getPrayTimes(namaKota, method, date);
                        }else {
                            getPrayTimes(namaKota, method, date);
                        }
                    }
                });

        builder.setView(modifyView);
        builder.show();


    }
}
