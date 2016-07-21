package com.loonggg.materialdesigndemo;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ToolbarActivity extends BaseActivity {
    private Toolbar toolbar;
    private TextInputLayout email_textlayout;
    private EditText email_et;
    private TextInputEditText pwdEt;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        //action menu操作菜单按钮的点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.share:
                        Toast.makeText(ToolbarActivity.this, "分享", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.setting:
                        Toast.makeText(ToolbarActivity.this, "设置", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        //导航按钮的点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn = (Button) findViewById(R.id.btn);

        email_textlayout = (TextInputLayout) findViewById(R.id.email_textlayout);
        email_et = (EditText) findViewById(R.id.email_et);

        pwdEt = (TextInputEditText) findViewById(R.id.pwd_et);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!email_et.getText().toString().equals("loonggg@qq.com")) {
                    //设置错误信息可用
                    email_textlayout.setErrorEnabled(true);
                    //设置错误信息
                    email_textlayout.setError("邮箱不正确");
                } else {
                    email_textlayout.setErrorEnabled(false);
                }
                if (pwdEt.getText().toString().equals("000000")) {

                } else {
                    pwdEt.setError("密码不正确");
                }
            }
        });
    }

}
