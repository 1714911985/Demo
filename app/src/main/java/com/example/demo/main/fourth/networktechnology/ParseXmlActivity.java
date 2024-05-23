package com.example.demo.main.fourth.networktechnology;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.demo.R;
import com.example.demo.main.fourth.networktechnology.auxiliary.MyHandler;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.net.ContentHandler;
import java.util.Objects;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ParseXmlActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnPull;
    private Button btnSax;
    private final static int PULL_MODEL = 1;
    private final static int SAX_MODEL = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_xml);
        initView();

        Toolbar toolbar = findViewById(R.id.tb_parse_xml);
        //toolbar的返回按钮
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        btnPull = findViewById(R.id.btn_pull);
        btnPull.setOnClickListener(this);
        btnSax = findViewById(R.id.btn_sax);
        btnSax.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_pull) {
            sendRequestWithOkHttp(PULL_MODEL);
        } else if (v.getId() == R.id.btn_sax) {
            sendRequestWithOkHttp(SAX_MODEL);
        }
    }

    private void sendRequestWithOkHttp(int model) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://10.0.0.10/get_data.xml")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    if (responseData != null) {
                        switch (model) {
                            case PULL_MODEL:
                                parseXMLWithPull(responseData);
                                break;
                            case SAX_MODEL:
                                parseXMLWithSAX(responseData);
                                break;
                            default:
                                break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseXMLWithPull(String responseData) {
        try {
            //拿工厂  拿解析器  读数据  拿事件类型
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(new StringReader(responseData));
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";

            //如果文档没有结束
            //节点为开始的节点  赋值
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                if (eventType == XmlPullParser.START_TAG) {
                    switch (nodeName) {
                        case "id":
                            id = xmlPullParser.nextText();
                            break;
                        case "name":
                            name = xmlPullParser.nextText();
                            break;
                        case "version":
                            version = xmlPullParser.nextText();
                            break;
                        default:
                            break;
                    }
                    //节点为结束的节点  打印日志
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (Objects.equals(nodeName, "app")) {
                        Log.e("PULL解析XML", "id : " + id);
                        Log.e("PULL解析XML", "name : " + name);
                        Log.e("PULL解析XML", "version : " + version);
                    }
                }
                //下一个节点
                eventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

    }

    private void parseXMLWithSAX(String responseData) {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            XMLReader xmlReader = saxParserFactory.newSAXParser().getXMLReader();
            xmlReader.setContentHandler(new MyHandler());
            xmlReader.parse(new InputSource(new StringReader(responseData)));
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }
}