package com.manar.ass2;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {//This Activity to make an account
    public static final String NAME="NAME";
    public static final String PASS="PASS";
    private EditText name;
    private EditText password;
    private EditText confirmPassword;
    private Button btn;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setUpViews();
        setupshared();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1=name.getText().toString();
                String str2=password.getText().toString();
                String str3=confirmPassword.getText().toString();
                if(!(str1.isEmpty()&&str2.isEmpty()&&str3.isEmpty())){
                        if (str2.equals(str3)) {
                            editor.putString(NAME, str1);
                            editor.putString(PASS, str2);
                            editor.putString("Confirm", str3);
                            editor.commit();
                            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Confirm password not equal password", Toast.LENGTH_SHORT).show();
                        }
                    }

                else{
                    Toast.makeText(getApplicationContext(), "Enter Correct Data", Toast.LENGTH_SHORT).show();
                }

                if(!(str1.isEmpty()&&str2.isEmpty())){
                    Intent intent =new Intent(RegisterActivity.this,MainActivity.class);
                    intent.putExtra("NAME",str1);
                    intent.putExtra("PASS",str2);
                    startActivity(intent);
                }
            }
        });
        checkPrefs();
    }

    public void checkPrefs(){
          String str1=prefs.getString(NAME,"");
          String str2=prefs.getString(PASS,"");
          String str3=prefs.getString("Confirm","");
          name.setText(str1);
          password.setText(str2);
          confirmPassword.setText(str3);
    }

    public void setupshared(){
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor=prefs.edit();

    }
    public void setUpViews(){
        name=findViewById(R.id.name);
        password=findViewById(R.id.password);
        confirmPassword=findViewById(R.id.confirmPassword);
        btn=findViewById(R.id.btn);

    }


}