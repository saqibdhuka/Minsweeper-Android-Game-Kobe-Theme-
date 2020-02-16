package app.kobetribute;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogBox extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inf = getActivity().getLayoutInflater();
        View view = inf.inflate(R.layout.layout_dialog, null);
        view.setMinimumWidth(600);
        view.setMinimumHeight(800);

        builder.setView(view)
                .setTitle("Game Ended")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent newIntent = new Intent(DialogBox.this.getActivity(), MainActivity.class);
                        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(newIntent);

                    }
                });

        return builder.create();
    }
}
