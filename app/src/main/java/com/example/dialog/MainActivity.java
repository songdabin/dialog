package com.example.dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b1, b2, b3, b4, b5;
    int selectedMenu = 0;
    String menu[] ={"chicken", "pizza", "spaghetti"};
    boolean checked[] = {true, true, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            displayDialog();
        }
        else if (v.getId() == R.id.button2) {
            displayDialog2();
        }
        else if (v.getId() == R.id.button3) {
            displayDialog3();
        }
        else if (v.getId() == R.id.button4) {
            displayDialog4();
        }
        else if (v.getId() == R.id.button5) {
            showDatePicker();
        }
    }

    private void displayDialog() {
        Toast.makeText(this, "Dialog1", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("기본대화상자1 ");
        dlg.setMessage("This is basic chat message.");
        dlg.setIcon(R.mipmap.ic_launcher);
        dlg.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                displayToast(null);
            }
        });
        dlg.show();
    }
    private void displayDialog2() {
        Toast.makeText(this, "Dialog2", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("기본대화상자2 ");
        dlg.setSingleChoiceItems(menu, selectedMenu, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedMenu = which;
            }
        });
        dlg.setIcon(R.mipmap.ic_launcher);
        dlg.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                displayToast(menu[selectedMenu]+"가 선택되었습니다!");
            }
        });
        dlg.show();
    }

    private void displayDialog3() {
        Toast.makeText(this, "Dialog3", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("기본대화상자3 ");
        dlg.setMultiChoiceItems(menu, checked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                String msg ="";
                checked[which] = isChecked;
                for (int i=0; i<3; i++) {
                    if (checked[i] == true) {
                        msg += menu[i];
                        if (i != 2) msg+=" ,";
                    }

                }
                if (msg != null) displayToast(msg+"가 선택되었습니다");
            }
        });
        dlg.setIcon(R.mipmap.ic_launcher);
        dlg.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                displayToast(menu[selectedMenu]+"가 선택되었습니다!");
            }
        });
        dlg.show();
    }

    private void displayDialog4() {
        View view = View.inflate(this, R.layout.dialog, null);
        final EditText editText = view.findViewById(R.id.etMsg);

        Toast.makeText(this, "Dialog4", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("user customizing dialog");

        dlg.setIcon(R.mipmap.ic_launcher);
        dlg.setView(view);
        dlg.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                displayToast("입력하신 메세지 : " + editText.getText().toString());
            }
        });
        dlg.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dlg.show();
    }

    private void displayToast(String msg) {
        if (msg == null) msg = "Clicked!";
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    public void showDatePicker() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string + "/" + day_string + "/" + year_string);
        Toast.makeText(this, "Date: " + dateMessage, Toast.LENGTH_SHORT).show();
    }
}