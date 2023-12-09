package com.zybooks.cardmatch;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class HelpDialog extends DialogFragment{
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setMessage(R.string.help_instructions)
                .setTitle(R.string.help_button)
                .setPositiveButton(R.string.ok, (dialog, id) -> {
                    // User clicked OK
                });
        return builder.create();
    }
}
