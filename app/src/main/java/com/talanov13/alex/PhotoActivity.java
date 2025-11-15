package com.talanov13.alex;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Build;
import android.provider.MediaStore;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.appbar.AppBarLayout;
import java.io.*;
import java.io.File;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class PhotoActivity extends AppCompatActivity {
	
	public final int REQ_CD_PHOTO = 101;
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private double randomtext = 0;
	
	private TextView textview1;
	private ImageView imageview1;
	private TextView textview2;
	
	private Intent photo = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	private File _file_photo;
	private AlertDialog.Builder dialog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.photo);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
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
		imageview1 = findViewById(R.id.imageview1);
		textview2 = findViewById(R.id.textview2);
		_file_photo = FileUtil.createNewPictureFile(getApplicationContext());
		Uri _uri_photo;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			_uri_photo = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", _file_photo);
		} else {
			_uri_photo = Uri.fromFile(_file_photo);
		}
		photo.putExtra(MediaStore.EXTRA_OUTPUT, _uri_photo);
		photo.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		dialog = new AlertDialog.Builder(this);
		
		textview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialog.setTitle("Как перевернуть фотку?");
				dialog.setMessage("Если фотка перевернута на бок нажми на бок Если фотка перевернута вверх ногами нажми вверх ногами");
				dialog.setPositiveButton("На бок", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						imageview1.setRotation((float)(90));
						SketchwareUtil.showMessage(getApplicationContext(), "Хорошо");
					}
				});
				dialog.setNegativeButton("Не переворачивать", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						SketchwareUtil.showMessage(getApplicationContext(), "Ладно");
					}
				});
				dialog.setNeutralButton("Вверх ногами", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						imageview1.setRotation((float)(180));
						SketchwareUtil.showMessage(getApplicationContext(), "Хорошо");
					}
				});
				dialog.create().show();
			}
		});
	}
	
	private void initializeLogic() {
		startActivityForResult(photo, REQ_CD_PHOTO);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_PHOTO:
			if (_resultCode == Activity.RESULT_OK) {
				String _filePath = _file_photo.getAbsolutePath();
				
				imageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_filePath, 1024, 1024));
				randomtext = SketchwareUtil.getRandom((int)(1), (int)(10));
				if (String.valueOf((long)(randomtext)).equals("1")) {
					textview1.setText("Отличная фотка не так ли?");
				}
				if (String.valueOf((long)(randomtext)).equals("2")) {
					textview1.setText("Прикольная фотка");
				}
				if (String.valueOf((long)(randomtext)).equals("3")) {
					textview1.setText("Крутая фотка если бы я смогла сделать такую фотку я была бы крутая Ответ от создателя: Не забудьте что Алекс это она а не он");
				}
				if (String.valueOf((long)(randomtext)).equals("4")) {
					textview1.setText("Самая лучшая фотка на свете!");
				}
				if (String.valueOf((long)(randomtext)).equals("5")) {
					textview1.setText("Как вам такая фотка?");
				}
				if (String.valueOf((long)(randomtext)).equals("6")) {
					textview1.setText("Если бы я была фотографом я бы сделала такую фотку");
				}
				if (String.valueOf((long)(randomtext)).equals("7")) {
					textview1.setText("Я сделала фотку Как вам?");
				}
				if (String.valueOf((long)(randomtext)).equals("8")) {
					textview1.setText("Вот вам такая фотка");
				}
				if (String.valueOf((long)(randomtext)).equals("9")) {
					textview1.setText("Красивая фотка");
				}
				if (String.valueOf((long)(randomtext)).equals("10")) {
					textview1.setText("Самая крутая фотка на свете!");
				}
			}
			else {
				textview1.setText("Можно закрыть нажав на кнопку назад так как фотка не была сделана");
				imageview1.setVisibility(View.GONE);
			}
			break;
			default:
			break;
		}
	}
	
}