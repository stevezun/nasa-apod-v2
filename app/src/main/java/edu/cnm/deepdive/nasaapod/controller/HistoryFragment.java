package edu.cnm.deepdive.nasaapod.controller;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.nasaapod.R;
import edu.cnm.deepdive.nasaapod.model.pojo.ApodWithStats;
import edu.cnm.deepdive.nasaapod.viewmodel.MainViewModel;


public class HistoryFragment extends Fragment {

  private ListView apodList;
  private MainViewModel viewModel;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View layout = inflater.inflate( R.layout.fragment_history, container, false );
    apodList = layout.findViewById( R.id.apod_list );
    return layout;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated( view, savedInstanceState );
    viewModel = new ViewModelProvider( getActivity() ).get( MainViewModel.class );
    viewModel.getAllApodSummaries().observe( getViewLifecycleOwner(), (apods) -> {
      ArrayAdapter<ApodWithStats> adapter = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, apods );
      apodList.setAdapter( adapter );
    } );
  }

}
