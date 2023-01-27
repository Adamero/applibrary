package pl.edu.wat.library.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import pl.edu.wat.library.retrofit.DeleteInterface;

public class DeleteFragment extends DialogFragment {

    private String message;
    private String id;
    private DeleteInterface deleteInterface;

    public DeleteFragment(String message, String id, DeleteInterface deleteInterface){
        this.message = message;
        this.id = id;
        this.deleteInterface = deleteInterface;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message + id + "?")
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteInterface.delete(id);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("Action: ", "cancel");
                    }
                });
        return builder.create();
    }
}
