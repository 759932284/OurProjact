package com.lanou.yindongge.music.pineapple.my;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseFragment;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * Created by dllo on 17/2/18.
 */

public class MyFragment extends BaseFragment implements View.OnClickListener{

    private static final String NUMBER = "123456";
    private static final String PASSWORD = "123456";
    private LinearLayout scan, qq;
    private Button smsCode, login;
    private EditText number, password;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView(View view) {
        scan = byView(R.id.scan);
        smsCode = byView(R.id.sms_code);
        login = byView(R.id.login);
        number = byView(R.id.number);
        password = byView(R.id.password);
        qq = byView(R.id.QQ);
    }

    @Override
    public void initData() {

        SMSSDK.initSDK(context, "1bc28ede6b21a",
                "81305c9fe8b947d7b6bf9c8847c6ec62");

        setListener();
    }

    private void setListener() {
        scan.setOnClickListener(this);
        smsCode.setOnClickListener(this);
        login.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scan:
                startActivityForResult(new Intent(context, CaptureActivity.class), 0);
                break;
            case R.id.sms_code:
                //打开注册页面
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
                        // 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            @SuppressWarnings("unchecked")
                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone = (String) phoneMap.get("phone");
                            // 提交用户信息（此方法可以不调用）
//                            registerUser(country, phone);
                        }
                    }
                });
                registerPage.show(context);
                break;
            case R.id.login:
                String num = number.getText().toString();
                String psw = password.getText().toString();
                if (num.equals(NUMBER) && psw.equals(PASSWORD)) {
                    Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "登录失败,账号或密码错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.QQ:

        }
    }
}
