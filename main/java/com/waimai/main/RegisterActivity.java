package com.waimai.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.waimai.dao.UsermanageDAO;
import com.waimai.model.Waimai_usermanage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private AppCompatButton registerButton;
    private AppCompatEditText registerEditview1, registerEditview2, registerEditview3;
    private Toolbar toolbar;
    private AppCompatImageView registerReturn;
    private Pattern patternUser,patternPassword;
    private Matcher matcherUser,matcherPassword;
    private String yzUser = "^[A-Za-z0-9_]{4,20}$",yzPassword = "^[A-Za-z0-9]{6,20}$";
    private UsermanageDAO usermanageDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = (Toolbar) findViewById(R.id.register_toolbar);

        usermanageDAO = new UsermanageDAO(RegisterActivity.this);
        patternUser = Pattern.compile(yzUser);
        patternPassword = Pattern.compile(yzPassword);

        registerEditview1 = (AppCompatEditText) findViewById(R.id.register_editText1);
        registerEditview2 = (AppCompatEditText) findViewById(R.id.register_editText2);
        registerEditview3 = (AppCompatEditText) findViewById(R.id.register_editText3);

        registerReturn = (AppCompatImageView) findViewById(R.id.register_return);
        registerReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        registerButton = (AppCompatButton) findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matcherUser = patternUser.matcher(registerEditview1.getText().toString());
                matcherPassword = patternPassword.matcher(registerEditview2.getText().toString());
                if (matcherUser.matches() == true) {
                     if (matcherPassword.matches() == true){
                         if (registerEditview2.getText().toString().equals(registerEditview3.getText().toString())){
                             if (usermanageDAO.getCount() == 0){
                                 Waimai_usermanage usermanage = new Waimai_usermanage(
                                         0,
                                         registerEditview1.getText().toString(),
                                         registerEditview2.getText().toString(),
                                         0,
                                         0
                                 );
                                 usermanageDAO.add(usermanage);
                                 Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                                 startActivity(intent);
                                 finish();
                             }else {
                                 alertDialogSend("注册错误","本机已有用户注册，请勿重复注册！ ");
                             }
                         }else {
                             alertDialogSend("验证错误","密码与确认密码不相同！");
                         }
                     }else {
                         alertDialogSend("密码错误","密码必须大于6位小于20位，且只能输入数字/英文！");
                     }
                } else {
                    alertDialogSend("用户名错误","用户名必须大于4位小于20位，且只能输入数字/英文/下划线！");
                }
            }
        });
    }

    private void alertDialogSend(String str, String str1) {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        builder.setTitle(str).setMessage(str1).setPositiveButton(" 确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();
    }
}
