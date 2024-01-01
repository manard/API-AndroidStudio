package com.manar.ass2;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {//Log in Activity if you have an account
    public static final String saveName="saveName";
    public static final String savePass="savePass";

    private EditText name;
    private EditText password;
    private CheckBox chk;
    private TextView createTxt;//Creat Account text,this if you dont have an account
    private Button btn;//log in button

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
        setUpShared();
        Intent intent=getIntent();
        btn.setOnClickListener(new View.OnClickListener() {//set Action for log in Button
            @Override
            public void onClick(View v) {
                String strName = name.getText().toString();
                String strPass = password.getText().toString();
                String str1=prefs.getString(RegisterActivity.NAME,"");
                String str2=prefs.getString(RegisterActivity.PASS,"");
                if (strName.equals(str1) && strPass.equals(str2)) {
                    Intent intent = new Intent(MainActivity.this, chooseActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Create Account please!", Toast.LENGTH_SHORT).show();
                }
                if(chk.isChecked()){
                    Toast.makeText(getApplicationContext(), "checked!", Toast.LENGTH_SHORT).show();
                    editor.putString("saveName",strName);
                    editor.putString("savePass",strPass);
                    editor.commit();
                }
            }
        });
        checkPrefs();
        createTxt.setOnClickListener(new View.OnClickListener() {//if you want to create Account then go to Register Activity
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    public void setUpViews(){//set up views
        createTxt = findViewById(R.id.createTxt);
        btn=findViewById(R.id.btn);
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        chk=findViewById(R.id.chk);
        String text = "Create Account";
        SpannableString content = new SpannableString(text);
        content.setSpan(new UnderlineSpan(), 0, text.length(), 0);//to make line under text
        createTxt.setText(content);

    }
    public void setUpShared(){//set up SharedPreference
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor=prefs.edit();
    }
    public void checkPrefs(){
        String str1=prefs.getString(RegisterActivity.NAME,"");
        String str2=prefs.getString(RegisterActivity.PASS,"");
        name.setText(str1);
        password.setText(str2);
        chk.setChecked(true);
    }

}
