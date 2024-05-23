package com.example.demo.main.fourth.networktechnology.auxiliary;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Objects;

/**
 * Author: Eccentric
 * Created on 2024/5/22 16:30.
 * Description: com.example.demo.main.fourth.networktechnology.auxiliary.MyHandler
 */
public class MyHandler extends DefaultHandler {
    private String nodeName = "";
    private StringBuilder id;
    private StringBuilder name;
    private StringBuilder version;


    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        //记录当前节点
        nodeName = localName;
        Log.e("startElement", "uri : " + uri);
        Log.e("startElement", "localName : " + localName);
        Log.e("startElement", "qName : " + qName);
        Log.e("startElement", "attributes : " + attributes.toString());

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (Objects.equals(localName, "app")) {
            Log.e("startElement", "id : " + id.toString().trim());
            Log.e("startElement", "name : " + name.toString().trim());
            Log.e("startElement", "version : " + id.toString().trim());
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        switch (nodeName) {
            case "id":
                id.append(ch, start, length);
                break;
            case "name":
                name.append(ch, start, length);
                break;
            case "version":
                version.append(ch, start, length);
                break;
            default:
                break;
        }
    }
}
