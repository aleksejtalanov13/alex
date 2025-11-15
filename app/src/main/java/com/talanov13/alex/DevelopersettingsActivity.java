package com.talanov13.alex;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.button.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class DevelopersettingsActivity extends AppCompatActivity {
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	
	private LinearLayout linear4;
	private LinearLayout developer;
	private LinearLayout developer2;
	private LinearLayout developer3;
	private LinearLayout developer4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private TextView textview3;
	private EditText directoryalex;
	private TextView textview5;
	private Switch disabledirectorycheck;
	private TextView textview6;
	private EditText defaultskill;
	private TextView textview7;
	private MaterialButton materialbutton2;
	private TextView textview8;
	private MaterialButton texteditor;
	private MaterialButton materialbutton3;
	private TextView textview10;
	
	private SharedPreferences settings;
	private Intent starttexteditor = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.developersettings);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		linear4 = findViewById(R.id.linear4);
		developer = findViewById(R.id.developer);
		developer2 = findViewById(R.id.developer2);
		developer3 = findViewById(R.id.developer3);
		developer4 = findViewById(R.id.developer4);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		textview3 = findViewById(R.id.textview3);
		directoryalex = findViewById(R.id.directoryalex);
		textview5 = findViewById(R.id.textview5);
		disabledirectorycheck = findViewById(R.id.disabledirectorycheck);
		textview6 = findViewById(R.id.textview6);
		defaultskill = findViewById(R.id.defaultskill);
		textview7 = findViewById(R.id.textview7);
		materialbutton2 = findViewById(R.id.materialbutton2);
		textview8 = findViewById(R.id.textview8);
		texteditor = findViewById(R.id.texteditor);
		materialbutton3 = findViewById(R.id.materialbutton3);
		textview10 = findViewById(R.id.textview10);
		settings = getSharedPreferences("settingsalex", Activity.MODE_PRIVATE);
		
		disabledirectorycheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					settings.edit().putString("disabledirectorycheck", "true").commit();
					SketchwareUtil.showMessage(getApplicationContext(), "Теперь Алекс не будет проверять свою папку");
				} else {
					settings.edit().remove("disabledirectorycheck").commit();
					SketchwareUtil.showMessage(getApplicationContext(), "Теперь Алекс будет проверять свою папку");
				}
			}
		});
		
		materialbutton2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				settings.edit().putString("directory", directoryalex.getText().toString()).commit();
				settings.edit().putString("defaultskill", defaultskill.getText().toString()).commit();
				SketchwareUtil.showMessage(getApplicationContext(), "Текстовые значения сохранены Изменения вступят в силу после перезапуска приложения");
			}
		});
		
		texteditor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				starttexteditor.setClass(getApplicationContext(), TexteditActivity.class);
				startActivity(starttexteditor);
			}
		});
		
		materialbutton3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				settings.edit().putString("develop", "yes").commit();
				SketchwareUtil.showMessage(getApplicationContext(), "Готово теперь приложение будет запускаться в режиме разработчика");
			}
		});
	}
	
	private void initializeLogic() {
		if (settings.getString("disabledirectorycheck", "").equals("true")) {
			disabledirectorycheck.setChecked(true);
		}
		directoryalex.setText(settings.getString("directory", ""));
		defaultskill.setText(settings.getString("defaultskill", ""));
	}
	
}