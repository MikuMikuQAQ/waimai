package com.waimai.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.waimai.dao.UsermanageDAO;

public class LoginActivity extends AppCompatActivity {

    private AppCompatButton buttonLogin,buttonSign;

    private AppCompatEditText loginEdit1,loginEdit2;

    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final UsermanageDAO usermanageDAO = new UsermanageDAO(LoginActivity.this);

        toolbar = (Toolbar) findViewById(R.id.login_toolbar);
        buttonLogin = (AppCompatButton) findViewById(R.id.buttonLogin);
        loginEdit1 = (AppCompatEditText) findViewById(R.id.login_edit1);
        loginEdit2 = (AppCompatEditText) findViewById(R.id.login_edit2);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginEdit1.getText().toString().isEmpty() || loginEdit2.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                }else {
                    if (usermanageDAO.getCount() == 0){
                        Toast.makeText(LoginActivity.this, "该用户未注册，请注册！", Toast.LENGTH_SHORT).show();
                    }else {
                        if (usermanageDAO.find().getUsername().equals(loginEdit1.getText().toString())) {
                            if (usermanageDAO.find().getPassword().equals(loginEdit2.getText().toString())) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "用户名错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        buttonSign = (AppCompatButton) findViewById(R.id.buttonSign);
        buttonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
