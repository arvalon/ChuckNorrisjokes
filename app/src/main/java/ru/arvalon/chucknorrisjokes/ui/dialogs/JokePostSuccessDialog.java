package ru.arvalon.chucknorrisjokes.ui.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import ru.arvalon.chucknorrisjokes.R;

/**
 * Created by arvalon on 13.11.2016.
 */

public class JokePostSuccessDialog extends DialogFragment {

    public interface DialogHost{
        void DialogExitFromApplication();
        void DialogGotoMainMenu();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext())
                .setMessage(R.string.JokePostSuccessDialogMessage)
                .setTitle(R.string.JokePostSuccessDialogTitle)
                .setPositiveButton(R.string.JokePostSuccessDialogPositiveButton,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try{
                                    ((DialogHost)getContext()).DialogExitFromApplication();
                                }catch (ClassCastException ex){}
                                dialog.dismiss();
                            }
                        }).setNegativeButton(R.string.JokePostSuccessDialogNegativeButton,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try{
                                    ((DialogHost)getContext()).DialogGotoMainMenu();
                                }catch (ClassCastException ex){}
                                dialog.dismiss();
                            }
                        });
        return builder.create();
    }
}
