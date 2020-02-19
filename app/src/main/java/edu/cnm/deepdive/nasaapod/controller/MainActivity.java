package edu.cnm.deepdive.nasaapod.controller;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import edu.cnm.deepdive.android.DateTimePickerFragment;
import edu.cnm.deepdive.android.DateTimePickerFragment.Mode;
import edu.cnm.deepdive.nasaapod.R;
import edu.cnm.deepdive.nasaapod.viewmodel.MainViewModel;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

  private MainViewModel viewModel;
  private BottomNavigationView navView;
  private NavController navController;
  private ProgressBar loading;
  private FloatingActionButton calendarFab;
  private Calendar calendar;
  private NavOptions navOptions;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    loading = findViewById(R.id.loading);
    setupNavigation();
    setupViewModel();
    setupCalendarPicker();
  }

  private void setupViewModel() {
    viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    viewModel.getApod().observe(this, (apod) -> {
      calendar.setTime(apod.getDate());
      if (navController.getCurrentDestination().getId() != R.id.navigation_image) {
        navView.setSelectedItemId(R.id.navigation_image);
        navController.navigate(R.id.navigation_image, null, navOptions);
      }
    });
  }

  private void setupNavigation() {
    // TODO Eliminate back stack.
    navOptions = new NavOptions.Builder()
        .setPopUpTo(R.id.mobile_navigation, true)
        .build();
    navView = findViewById(R.id.nav_view);
    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        R.id.navigation_image, R.id.navigation_history)
        .build();
    navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    navView.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        navController.navigate(item.getItemId(), null, navOptions);
        return true;
      }
    });
  }

  public void loadApod(Date date) {
    setProgressVisibility(View.VISIBLE);
    viewModel.setApodDate(date);
  }

  public void setProgressVisibility(int visibility) {
    loading.setVisibility(visibility);
  }

  private void setupCalendarPicker() {
    calendar = Calendar.getInstance();
    calendarFab = findViewById(R.id.calendar_fab);
    calendarFab.setOnClickListener((v) -> {
      DateTimePickerFragment fragment = new DateTimePickerFragment();
      fragment.setCalendar(calendar);
      fragment.setMode(Mode.DATE);
      fragment.setOnChangeListener((cal) -> loadApod(cal.getTime()));
      fragment.show(getSupportFragmentManager(), fragment.getClass().getName());
    });
  }

}
