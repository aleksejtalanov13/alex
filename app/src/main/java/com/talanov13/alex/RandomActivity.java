package com.talanov13.alex;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
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
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class RandomActivity extends AppCompatActivity {
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	
	private TextView chat;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private TextView textview3;
	private EditText ot;
	private EditText doe;
	private MaterialButton selectrandom;
	private EditText selected;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.random);
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
		chat = findViewById(R.id.chat);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		textview3 = findViewById(R.id.textview3);
		ot = findViewById(R.id.ot);
		doe = findViewById(R.id.doe);
		selectrandom = findViewById(R.id.selectrandom);
		selected = findViewById(R.id.selected);
		
		selectrandom.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				selected.setText(String.valueOf((long)(SketchwareUtil.getRandom((int)(Double.parseDouble(ot.getText().toString())), (int)(Double.parseDouble(doe.getText().toString()))))));
				chat.setText("Выбрал случайное число для вас");
			}
		});
	}
	
	private void initializeLogic() {
	}
	
}