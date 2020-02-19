package edu.cnm.deepdive.nasaapod.controller;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.nasaapod.R;
import edu.cnm.deepdive.nasaapod.viewmodel.MainViewModel;
import java.text.DateFormat;
import java.util.Date;
import org.w3c.dom.Text;

public class InfoFragment extends DialogFragment {

  public static final String TITLE_KEY = "title";
  public static final String DESCRIPTION_KEY = "description";
  public static final String COPYRIGHT_KEY = "copyright";
  public static final String DATE_KEY = "date";

  private AlertDialog alert;
  private View dialogView;

  @SuppressLint("InflateParams")
  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    dialogView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_info, null);
    alert = new Builder(getContext())
        .setIcon(R.drawable.ic_info_alert)
        .setTitle(getString( R.string.default_info_title))
        .setView(dialogView)
        .setNeutralButton(R.string.info_ok, (dlg, which) -> {})
        .create();

    return alert;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return dialogView;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    MainViewModel viewModel = new ViewModelProvider( getActivity() ).get(MainViewModel.class);
    viewModel.getApod().observe( getViewLifecycleOwner(), (apod) -> {
      TextView date = dialogView.findViewById( R.id.date );
      TextView description = dialogView.findViewById( R.id.description );
      TextView webUrl = dialogView.findViewById( R.id.web_url );
      TextView url = dialogView.findViewById( R.id.url );
      TextView hdurl = dialogView.findViewById( R.id.hd_url );
      alert.setTitle( apod.getTitle() );
      description.setText( apod.getDescription() );
      webUrl.setText( getString( R.string.web_url_format, apod.getDate() ) );
      url.setText( apod.getUrl() );
      String highRes = apod.getHdUrl();
      if (highRes != null && !apod.getUrl().equals( highRes )) {
        hdurl.setText(highRes);
      } else {
        hdurl.setVisibility(View.GONE);
        dialogView.findViewById( R.id.hd_url_label ).setVisibility( View.GONE );
      }
      DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getContext());
      date.setText( dateFormat.format( apod.getDate() ) );
      dialogView.forceLayout();;
    }  );
  }

}
