package edu.cnm.deepdive.nasaapod.controller;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.fragment.app.DialogFragment;
import edu.cnm.deepdive.nasaapod.R;
import java.util.Date;

public class InfoFragment extends DialogFragment {

  public static final String TITLE_KEY = "title";
  public static final String DESCRIPTION_KEY = "description";
  public static final String COPYRIGHT_KEY = "copyright";
  public static final String DATE_KEY = "date";

  private AlertDialog alertDialog;
  private View  dialogView;

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    Bundle args = getArguments();
    String title = args.getString(TITLE_KEY);
    String description = args.getString(DESCRIPTION_KEY);
    String copyright = args.getString(COPYRIGHT_KEY);
    Date date = (Date) args.getSerializable(DATE_KEY);
    return new Builder(getContext())
        .setIcon(R.drawable.ic_info_alert)
        .setTitle(title)
        .setMessage(description)
        .setNeutralButton(R.string.info_ok, (dlg, which) -> {})
        .create();
  }

}
