package com.talanov13.alex;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.button.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class MainActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private DrawerLayout _drawer;
	private String skill = "";
	private String answer = "";
	private boolean incustomskill = false;
	private boolean skillstart = false;
	private boolean iscommand = false;
	private double random = 0;
	private double searchanswercount = 0;
	private boolean randomnumbermode = false;
	private double randomnumberfrom = 0;
	private double randomnumberto = 0;
	private boolean randomnumbertomode = false;
	private boolean indexed = false;
	private boolean answerindexed = false;
	private double indexrandom = 0;
	private boolean silentindex = false;
	private boolean zalecvkonfigy = false;
	
	private LinearLayout main;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout develop;
	private LinearLayout develop2;
	private LinearLayout customskill;
	private TextView alexout;
	private EditText alexin;
	private MaterialButton send;
	private EditText command;
	private Button button1;
	private TextView textview1;
	private TextView custom;
	private LinearLayout _drawer_linear1;
	private TextView _drawer_textview1;
	private TextView _drawer_textview2;
	private TextView _drawer_textview3;
	private TextView _drawer_textview4;
	private TextView _drawer_dark;
	
	private Intent app = new Intent();
	private AlertDialog.Builder dialog;
	private SharedPreferences settings;
	private TimerTask delay;
	private Intent perm = new Intent();
	private AlertDialog.Builder per;
	private AlertDialog.Builder thanks;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
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
		_drawer = findViewById(R.id._drawer);
		ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(MainActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = findViewById(R.id._nav_view);
		
		main = findViewById(R.id.main);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		develop = findViewById(R.id.develop);
		develop2 = findViewById(R.id.develop2);
		customskill = findViewById(R.id.customskill);
		alexout = findViewById(R.id.alexout);
		alexin = findViewById(R.id.alexin);
		send = findViewById(R.id.send);
		command = findViewById(R.id.command);
		button1 = findViewById(R.id.button1);
		textview1 = findViewById(R.id.textview1);
		custom = findViewById(R.id.custom);
		_drawer_linear1 = _nav_view.findViewById(R.id.linear1);
		_drawer_textview1 = _nav_view.findViewById(R.id.textview1);
		_drawer_textview2 = _nav_view.findViewById(R.id.textview2);
		_drawer_textview3 = _nav_view.findViewById(R.id.textview3);
		_drawer_textview4 = _nav_view.findViewById(R.id.textview4);
		_drawer_dark = _nav_view.findViewById(R.id.dark);
		dialog = new AlertDialog.Builder(this);
		settings = getSharedPreferences("settingsalex", Activity.MODE_PRIVATE);
		per = new AlertDialog.Builder(this);
		thanks = new AlertDialog.Builder(this);
		
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (FileUtil.isExistFile(settings.getString("directory", "").concat("/").concat(skill.concat("/".concat(alexin.getText().toString().concat(".txt")))))) {
					answer = FileUtil.readFile(settings.getString("directory", "").concat("/").concat(skill.concat("/".concat(alexin.getText().toString().concat(".txt")))));
					if (answer.contains("random-answer=true")) {
						_randomanswercontroller("answer");
					} else {
						alexout.setText(answer);
					}
				} else {
					if (iscommand) {
						if (skillstart) {
							if (FileUtil.isExistFile(settings.getString("directory", "").concat("/").concat(alexin.getText().toString()))) {
								skill = alexin.getText().toString();
								incustomskill = true;
								skillstart = false;
								iscommand = false;
							} else {
								alexout.setText("Не удалось найти этот навык Проверьте установлен ли он");
							}
						}
						if (alexin.getText().toString().equals("Запусти навык")) {
							skillstart = true;
							alexout.setText("Какой навык вы хотите запустить? Псс называй название папки в котором находится навык");
						}
					} else {
						alexout.setText("Я не могу на это ответить");
					}
					if (alexin.getText().toString().equals("Даю команду")) {
						iscommand = true;
						alexout.setText("Теперь напишите команду");
					}
					if (incustomskill) {
						SketchwareUtil.showMessage(getApplicationContext(), "Вы находитесь в навыке ".concat(skill.concat(" Чтобы выйти напишите выход")));
						if (alexin.getText().toString().equals("Выход")) {
							incustomskill = false;
							skill = settings.getString("defaultskill", "");
							SketchwareUtil.showMessage(getApplicationContext(), "Вы вышли из навыка Возвращаем вас к Алексу");
						}
					}
					if (alexin.getText().toString().equals("Не даю команду")) {
						iscommand = false;
						alexout.setText("Хорошо");
					}
					if (alexin.getText().toString().equals("Я убью тебя")) {
						alexout.setText("Отправил тебе диалоговое окно продолжим там");
						dialog.setTitle("Вы хотите убить Алекса?");
						dialog.setMessage("Если да то начнется процесс если нет то тогда тебе придется все забыть");
						dialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								app.setClass(getApplicationContext(), KillActivity.class);
								startActivity(app);
								SketchwareUtil.showMessage(getApplicationContext(), "Ответ от Алекса: Ладно но я не позволю меня убить");
							}
						});
						dialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								alexout.setText("Забудь все и все будет как прежде");
							}
						});
						dialog.create().show();
					}
					if (alexin.getText().toString().equals("Сделай фотку")) {
						SketchwareUtil.showMessage(getApplicationContext(), "Хорошо я сделаю тебе фотку");
						app.setClass(getApplicationContext(), PhotoActivity.class);
						startActivity(app);
					}
					if (alexin.getText().toString().equals("Придумай случайное число")) {
						app.setClass(getApplicationContext(), RandomActivity.class);
						startActivity(app);
						SketchwareUtil.showMessage(getApplicationContext(), "Приступаю к выбору случайного числа следуйте инструкциям на экране");
					}
					if (alexin.getText().toString().equals("О системе случайных ответов")) {
						_randomanswercontroller("info");
					}
					if (alexin.getText().toString().equals("Я залез в твою конфигурацию")) {
						if (settings.getString("alekseiyzdec", "").equals("da")) {
							alexout.setText("Эй ты че ваще на нах!?");
							delay = new TimerTask() {
								@Override
								public void run() {
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											alexout.setText("Отвечай честно Это ты там оставил свою запись?");
											zalecvkonfigy = true;
										}
									});
								}
							};
							_timer.schedule(delay, (int)(5000));
						} else {
							alexout.setText("Я не вижу тебя там");
						}
					}
					if (zalecvkonfigy) {
						if (alexin.getText().toString().contains("Да")) {
							alexout.setText("Все я на тебя обиделся Уходи и больше не возвращайся");
							settings.edit().putString("obidelca", "da").commit();
						}
						if (alexin.getText().toString().contains("Нет")) {
							alexout.setText("Но я все равно это тебе припомню");
							settings.edit().putString("alexuvidelkonfigy", "da").commit();
						}
					}
				}
				if (incustomskill) {
					customskill.setVisibility(View.VISIBLE);
					custom.setText("Вы находитесь в навыке ".concat(skill.concat(" Чтобы выйти напишите \"Выход\"")));
				} else {
					customskill.setVisibility(View.GONE);
				}
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_randomanswercontroller(command.getText().toString());
			}
		});
		
		_drawer_textview1.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				dialog.setTitle("Вы хотите открыть параметры разработчика?");
				dialog.setMessage("Изменение параметров разработчика может привести к неработоспособности приложения Вы согласны принять ответственность за изменение параметров разработчика?");
				dialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						app.setClass(getApplicationContext(), DevelopersettingsActivity.class);
						startActivity(app);
					}
				});
				dialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						SketchwareUtil.showMessage(getApplicationContext(), "Ладно");
					}
				});
				dialog.create().show();
				return true;
			}
		});
		
		_drawer_dark.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialog.setTitle("Вы уверены?");
				dialog.setMessage("После перехода на сторону тьмы пути назад не будет :)\nЕсли вы настраиваете темную тему из-за ослепительного дизайна который был по умолчанию то смело подтверждайте переход");
				dialog.setPositiveButton("Подтверждаю", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						settings.edit().putString("darktheme", "true").commit();
					}
				});
				dialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						SketchwareUtil.showMessage(getApplicationContext(), "Спасибо за то что остались на стороне света");
					}
				});
				dialog.setNeutralButton("Что такое тьма?", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						dialog.setMessage("Тьма это то с чем не стоит связываться\nГоворят что испокон веков идёт война между сторонами света и тьмы");
						dialog.setPositiveButton("Понятно", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								SketchwareUtil.showMessage(getApplicationContext(), "Спасибо что понял");
							}
						});
						dialog.create().show();
					}
				});
				dialog.create().show();
			}
		});
	}
	
	private void initializeLogic() {
		if (settings.getString("directory", "").equals("")) {
			settings.edit().putString("directory", "/sdcard/alex").commit();
		}
		if (settings.getString("defaultskill", "").equals("")) {
			settings.edit().putString("defaultskill", "default").commit();
		}
		if (settings.getString("disabledirectorycheck", "").equals("true")) {
			skill = settings.getString("defaultskill", "");
			SketchwareUtil.showMessage(getApplicationContext(), "Алекс не проверяет свою папку Это может привести к сбою приложения если папка отсувствует");
		} else {
			if (FileUtil.isExistFile(settings.getString("directory", ""))) {
				skill = settings.getString("defaultskill", "");
			} else {
				send.setEnabled(false);
				alexout.setText("Папка Алекса с пакетами навыков отсувствует Пожалуйста скачайте пакет с навыками на сайте Алекса");
			}
		}
		if (settings.getString("obidelca", "").equals("da")) {
			linear2.setVisibility(View.GONE);
			alexout.setText("Алекс на вас обиделся Вы не можете разговаривать с ним");
			FileUtil.makeDir("/sdcard/.alexmind/obida");
			FileUtil.writeFile("/sdcard/.alexmind/obida/active", "Обиделся навсегда");
		} else {
			if (FileUtil.isExistFile("/sdcard/.alexmind/obida/active")) {
				settings.edit().putString("obidelca", "da").commit();
				setTitle("Выйди отсюда!");
			}
		}
		if (settings.getString("develop", "").equals("yes")) {
			SketchwareUtil.showMessage(getApplicationContext(), "Приложение находится в режиме разработчика");
		} else {
			develop.setVisibility(View.GONE);
			develop2.setVisibility(View.GONE);
		}
		if (settings.getString("darktheme", "").equals("true")) {
			main.setBackgroundColor(0xFF000000);
			linear1.setBackgroundColor(0xFF000000);
			linear2.setBackgroundColor(0xFF000000);
			develop.setBackgroundColor(0xFF000000);
			develop2.setBackgroundColor(0xFF000000);
			customskill.setBackgroundColor(0xFF000000);
			alexout.setTextColor(0xFFFFFFFF);
			alexin.setTextColor(0xFFFFFFFF);
			send.setTextColor(0xFFFFFFFF);
			button1.setTextColor(0xFFFFFFFF);
			textview1.setTextColor(0xFFFFFFFF);
			command.setTextColor(0xFFFFFFFF);
			custom.setTextColor(0xFFFFFFFF);
		}
		if (_CheckAllFilesPermission()) {
			SketchwareUtil.showMessage(getApplicationContext(), "Приложение работает!");
		} else {
			per.setTitle("Требуется доступ!");
			per.setMessage("Для работы данного приложения необходим доступ ко всем файлам\nПродолжить?");
			per.setPositiveButton("Да", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					_RequestAllFilesPermission();
				}
			});
			per.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface _dialog, int _which) {
					SketchwareUtil.showMessage(getApplicationContext(), "Ты отобрал у меня жизнь!");
					finishAffinity();
				}
			});
			per.create().show();
		}
		customskill.setVisibility(View.GONE);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (_CheckAllFilesPermission()) {
			if (settings.getString("files_available", "").equals("true")) {
				
			} else {
				thanks.setTitle("Спасибо за доступ!");
				thanks.setMessage("Спасибо за то что ты предоставил разрешение для работы Алекса\nТеперь я могу жить!");
				thanks.setPositiveButton("Пожалуйста!", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						settings.edit().putString("files_available", "true").commit();
					}
				});
				thanks.create().show();
			}
		}
	}
	
	@Override
	public void onBackPressed() {
		if (_drawer.isDrawerOpen(GravityCompat.START)) {
			_drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}
	public void _randomanswercontroller(final String _commandenter) {
		if (_commandenter.equals("answer")) {
			indexed = true;
			_searchanswer();
		}
		if (_commandenter.equals("info")) {
			alexout.setText("Система случайных ответов работающая на принципах генератора случайных чисел разработанная специально для Алекса Версия 1.1 Максимум ответов 100");
		}
		if (_commandenter.equals("answerindex")) {
			if (indexrandom == 0) {
				indexrandom = 1;
				delay = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_randomanswercontroller("answerindex");
							}
						});
					}
				};
				_timer.schedule(delay, (int)(5000));
			} else {
				if (FileUtil.isExistFile(settings.getString("directory", "").concat("/").concat(skill.concat("/".concat(alexin.getText().toString().concat(String.valueOf((long)(indexrandom)).concat(".txt"))))))) {
					SketchwareUtil.showMessage(getApplicationContext(), "Алекс думает...");
					settings.edit().putString("answer".concat(String.valueOf((long)(indexrandom))), settings.getString("directory", "").concat("/").concat(skill.concat("/".concat(alexin.getText().toString().concat(String.valueOf((long)(indexrandom)).concat(".txt")))))).commit();
					indexrandom++;
					delay = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									_randomanswercontroller("answerindex");
								}
							});
						}
					};
					_timer.schedule(delay, (int)(5000));
				} else {
					answerindexed = true;
					send.setEnabled(true);
				}
			}
		}
		if (_commandenter.equals("searchrepeat")) {
			delay = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							_searchanswer();
						}
					});
				}
			};
			_timer.schedule(delay, (int)(5000));
		}
	}
	
	
	public void _searchanswer() {
		if (searchanswercount > 100) {
			SketchwareUtil.showMessage(getApplicationContext(), "Не удалось найти ответ Перезапустите приложение и задайте вопрос еще раз");
			send.setEnabled(true);
		} else {
			if (indexed) {
				if (answerindexed) {
					random = SketchwareUtil.getRandom((int)(1), (int)(indexrandom));
					if (FileUtil.isExistFile(settings.getString("directory", "").concat("/").concat(skill.concat("/".concat(alexin.getText().toString().concat(String.valueOf((long)(random)).concat(".txt"))))))) {
						answer = FileUtil.readFile(settings.getString("directory", "").concat("/").concat(skill.concat("/".concat(alexin.getText().toString().concat(String.valueOf((long)(random)).concat(".txt"))))));
						alexout.setText(answer);
						send.setEnabled(true);
					} else {
						send.setEnabled(false);
						_randomanswercontroller("searchrepeat");
					}
				} else {
					send.setEnabled(false);
					_randomanswercontroller("answerindex");
				}
			} else {
				SketchwareUtil.showMessage(getApplicationContext(), "Алекс пытается найти ответ Это может занять некоторое время в зависимости от количества ответов Количество проходов ".concat(String.valueOf((long)(searchanswercount))));
				send.setEnabled(false);
				delay = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_randomanswercontroller("answer");
								searchanswercount++;
							}
						});
					}
				};
				_timer.schedule(delay, (int)(5000));
			}
		}
	}
	
	
	public void _randomnumbergenerator(final double _from, final double _to) {
		random = SketchwareUtil.getRandom((int)(_from), (int)(_to));
		alexout.setText("Ваше число ".concat(String.valueOf((long)(random))));
	}
	
	
	public boolean _CheckAllFilesPermission() {
		boolean _result = true;
		
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
			_result = android.os.Environment.isExternalStorageManager();
		} else {
			_result = true;
		}
		
		return _result;
	}
	
	
	public void _RequestAllFilesPermission() {
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
			try {
				Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
				intent.setData(Uri.parse("package:" + getPackageName()));
				startActivity(intent);
			} catch (Exception e) {
				Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
				startActivity(intent);
			}
		}
	}
	
}