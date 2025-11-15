package com.talanov13.alex;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
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
import android.widget.Button;
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

public class ProtectfromkillingActivity extends AppCompatActivity {
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private double code = 0;
	
	private TextView textview1;
	private TextView textview2;
	private LinearLayout linear1;
	private TextView textview5;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private EditText codeenter;
	private MaterialButton confirm;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button6;
	private Button button7;
	private Button button8;
	private Button button9;
	private Button button10;
	private Button button11;
	private Button button12;
	private Button button13;
	private Button button14;
	private Button button15;
	private Button button16;
	private Button button17;
	private Button button18;
	private Button button19;
	private Button button20;
	private Button button21;
	private Button button22;
	private Button button23;
	private Button button24;
	private Button button25;
	private Button button26;
	private Button button27;
	private Button button28;
	private Button button29;
	
	private Intent kill = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.protectfromkilling);
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
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		linear1 = findViewById(R.id.linear1);
		textview5 = findViewById(R.id.textview5);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		codeenter = findViewById(R.id.codeenter);
		confirm = findViewById(R.id.confirm);
		button1 = findViewById(R.id.button1);
		button2 = findViewById(R.id.button2);
		button3 = findViewById(R.id.button3);
		button4 = findViewById(R.id.button4);
		button6 = findViewById(R.id.button6);
		button7 = findViewById(R.id.button7);
		button8 = findViewById(R.id.button8);
		button9 = findViewById(R.id.button9);
		button10 = findViewById(R.id.button10);
		button11 = findViewById(R.id.button11);
		button12 = findViewById(R.id.button12);
		button13 = findViewById(R.id.button13);
		button14 = findViewById(R.id.button14);
		button15 = findViewById(R.id.button15);
		button16 = findViewById(R.id.button16);
		button17 = findViewById(R.id.button17);
		button18 = findViewById(R.id.button18);
		button19 = findViewById(R.id.button19);
		button20 = findViewById(R.id.button20);
		button21 = findViewById(R.id.button21);
		button22 = findViewById(R.id.button22);
		button23 = findViewById(R.id.button23);
		button24 = findViewById(R.id.button24);
		button25 = findViewById(R.id.button25);
		button26 = findViewById(R.id.button26);
		button27 = findViewById(R.id.button27);
		button28 = findViewById(R.id.button28);
		button29 = findViewById(R.id.button29);
		
		confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (codeenter.getText().toString().equals(String.valueOf((long)(code)))) {
					kill.setClass(getApplicationContext(), TotallykillActivity.class);
					startActivity(kill);
					SketchwareUtil.showMessage(getApplicationContext(), "Ответ от Алекса: Наконец-то я смогу приступить к своему плану");
				} else {
					SketchwareUtil.showMessage(getApplicationContext(), "Неверный код Ответ от Алекса: Попробуй найти кнопку ХАХАХАХАХАХАХАХАХАХАХАХА (злодейский смех)");
				}
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button17.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button18.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button19.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button20.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button21.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button22.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button23.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button24.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button25.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button26.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "Ты нашел кнопку поздравляю");
				SketchwareUtil.showMessage(getApplicationContext(), "Ваш секретный код: ".concat(String.valueOf((long)(code))));
			}
		});
		
		button27.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button28.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		button29.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
	}
	
	private void initializeLogic() {
		code = Double.parseDouble(String.valueOf((long)(SketchwareUtil.getRandom((int)(1), (int)(9)))).concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(1), (int)(9)))).concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(1), (int)(9)))).concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(1), (int)(9))))))));
	}
	
}