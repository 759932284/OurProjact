package com.lanou.yindongge.music.pineapple.my;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lanou.yindongge.music.pineapple.R;
import com.lanou.yindongge.music.pineapple.base.BaseFragment;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * Created by dllo on 17/2/18.
 */

public class MyFragment extends BaseFragment implements View.OnClickListener, PlatformActionListener{

    private static final String NUMBER = "123456";
    private static final String PASSWORD = "123456";
    private LinearLayout scan, qq, share;
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
        share = byView(R.id.share);
    }

    @Override
    public void initData() {

        SMSSDK.initSDK(context, "1bc28ede6b21a",
                "81305c9fe8b947d7b6bf9c8847c6ec62");


        ShareSDK.initSDK(context);
        setListener();
    }

    private void setListener() {
        scan.setOnClickListener(this);
        smsCode.setOnClickListener(this);
        login.setOnClickListener(this);
        qq.setOnClickListener(this);
        share.setOnClickListener(this);
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
                mobLogin();
                break;
            case R.id.share:
                showShare();
        }
    }
    private void showShare() {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
        oks.setTitle("");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
//        oks.setTitleUrl(songUrl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("说点什么吧:");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        oks.setImageUrl("http://p2.qhimg.com/t01c529bfd9870e4075.png");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://bolo.163.com/new/home");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("我的bolo");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://bolo.163.com/new/home");
        // 启动分享GUI
        oks.show(context);
    }

    public void mobLogin() {
        Platform platform = ShareSDK.getPlatform(QQ.NAME);
        if (platform == null) {
            Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
            return;
        }

        // 第二次登录会进入的方法, 不在走网页, 而是直接从本地数据库中取数据
        if (platform.isAuthValid()) {
            String name = platform.getDb().getUserName();
            String icon = platform.getDb().getUserIcon();
            Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
            Log.d("LoginActivity", name);

        }
        else {
            platform.setPlatformActionListener(this);  // 回调接口返回
            platform.SSOSetting(false);
            platform.showUser(null);
        }
    }

    // 第一次登录会进入的方法
    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Log.d("zzz", "123:" + 123);
        platform.removeAccount();
        Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
//        finish();
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel(Platform platform, int i) {
        Toast.makeText(context, "取消登录", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
