package edu.lewisu.cs.peterschilder.todo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Peter Schilder on 3/20/2016.
 */
public class DatePickerFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.dialog_date, null);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);
        builder.setTitle(R.string.due);
        builder.setPositiveButton(android.R.string.ok, null);
        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }
}
