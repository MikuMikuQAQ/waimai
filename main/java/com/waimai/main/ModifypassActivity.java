package com.waimai.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.waimai.dao.UsermanageDAO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModifypassActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AppCompatEditText editText1,editText2,editText3;
    private AppCompatImageView passwordReturn,passwordSave;
    private UsermanageDAO usermanageDAO;
    private String yzPassword = "^[A-Za-z0-9]{6,20}$";
    private Pattern patternPassword;
    private Matcher matcherPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifypass);

        usermanageDAO = new UsermanageDAO(ModifypassActivity.this);
        patternPassword = Pattern.compile(yzPassword);

        toolbar = (Toolbar) findViewById(R.id.password_toolbar);

        editText1 = (AppCompatEditText) findViewById(R.id.password_edit1);
        editText2 = (AppCompatEditText) findViewById(R.id.password_edit2);
        editText3 = (AppCompatEditText) findViewById(R.id.password_edit3);

        passwordReturn = (AppCompatImageView) findViewById(R.id.password_return);
        passwordSave = (AppCompatImageView) findViewById(R.id.password_save);

        passwordReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        passwordSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                matcherPassword = patternPassword.matcher(editText2.getText().toString());
                if (editText1.getText().toString().equals(usermanageDAO.find().getPassword())){
                    if (matcherPassword.matches()){
                        if (editText2.getText().toString().equals(editText3.getText().toString())) {
                            usermanageDAO.updatePwd(editText2.getText().toString(),0);
                            Toast.makeText(ModifypassActivity.this,"密码更新成功",Toast.LENGTH_SHORT).show();
                            editText1.setText("");
                            editText2.setText("");
                            editText3.setText("");
                        }else {
                            alertDialogSend("验证错误","新密码与确认密码不相同！");
                        }
                    }else {
                        alertDialogSend("密码错误","密码必须大于6位小于20位，且只能输入数字/英文！");
                    }
                }else{
                    alertDialogSend("验证错误","旧密码错误！");
                }
            }
        });
    }

    private void alertDialogSend(String str, String str1) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ModifypassActivity.this);
        builder.setTitle(str).setMessage(str1).setPositiveButton(" 确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();
    }
}
