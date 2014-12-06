package com.aps.utfpr.horarioutfpr;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AtualizarHorarioDialogFragment extends DialogFragment {

    /*
    RETIRADO DO SITE DEVELOPER.ANDROID:
    http://developer.android.com/guide/topics/ui/dialogs.html
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.atualizar_horario_dialog_layout, null))
                .setPositiveButton(R.string.logar_atualizar_horario, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Atualizar o horario
                    }
                })
                .setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AtualizarHorarioDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
