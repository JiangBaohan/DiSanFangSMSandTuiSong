package com.example.demo12_sms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * 步骤:看集成文档
 * 短信验证再真实开发中的思想逻辑:我们得到用户的手机号
 * 通过网络把用户的手机号发送给服务端,然后由服务器把短信验证码发送给客户端,客户端填写好了
 * 验证码,通过网络请求把手机号和用户填写的验证码发送给服务器;
 * <p>
 * 服务器逻辑:会把得到的数据,进行比对,没有问题,会返回给客户端登录或者注册成功的数据,也就是说
 * 真实开发中,大部分的业务逻辑,由服务器去完成,客户端无非是在用户输入手机号的时候,进行
 * 正则表达式的格式规范,以及后续的网络操作
 * 我们客户端实际就是把用户输入的数据发送给服务端,服务端返回数据给我们客户端,我们客户端拿到数据进行展示;
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EventHandler eventHandler;
    private boolean boolShowInDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.tv);
        button.setOnClickListener(this);
         /*
        * 3.2、通过代码配置：
        如果选择通过代码配置，则不需要继承MobApplication，只要在使用SMSSDK之前，调用以下代码：
        // 通过代码注册你的AppKey和AppSecret
        MobSDK.init(context, "你的AppKey", "你的AppSecret");*/
        // 如果希望在读取通信录的时候提示用户，可以添加下面的代码，并且必须在其他代码调用之前，否则不起作用；如果没这个需求，可以不加这行代码

        SMSSDK.setAskPermisionOnReadContact(boolShowInDialog);
        // 创建EventHandler对象
        // 处理你自己的逻辑
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable) data;
                    String msg = throwable.getMessage();
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                } else {
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        // 处理你自己的逻辑
                        //解析注册结果,进行判断是否成功
                        //注册成功后返回的数据
                        HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                        //得到手机的注册国家
                        String country = (String) phoneMap.get("country");
                        //得到手机的注册国家
                        String phone = (String) phoneMap.get("phone");
                        System.out.println("国家" + country + "手机信息" + phone);
                        // 网络把得到数据发送给我们自己的服务器,那么整个流程就走完.
                    }
                }
            }
        };
        // 注册监听器,与下面的监听相同
        //SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    public void onClick(View view) {
        //打开注册界面
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(eventHandler);
        //显示注册的面板
        registerPage.show(MainActivity.this);
    }

    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}
