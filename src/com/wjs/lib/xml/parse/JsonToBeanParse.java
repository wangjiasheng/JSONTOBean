package com.wjs.lib.xml.parse;

import com.wjs.lib.xml.ben.Auto;
import com.wjs.lib.xml.ben.Json;
import com.wjs.lib.xml.ben.Login;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonToBeanParse {
    public static void main(String[] args)
    {
        parseXML("E:\\intellij idea code\\jsontobean.xml");
    }
    public static Auto parseXML(String path)
    {
        Auto auto=new Auto();
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(path);
            Element rootElement=document.getRootElement();                        //AUTO
            for (Iterator<Attribute> jsoniterator = rootElement.attributeIterator(); jsoniterator.hasNext();) {
                Attribute jsonattribute = jsoniterator.next();
                switch (jsonattribute.getName())
                {
                    case "path":
                        auto.setPath(jsonattribute.getValue());
                        break;
                }
            }
            List<Login> loginList=new ArrayList<Login>();
            auto.setLogn(loginList);
            for (Iterator<Element> it = rootElement.elementIterator(); it.hasNext();) {
                Element element = it.next();                                     //LOGIN
                Iterator<Element> loginelementIterator=element.elementIterator();
                Login login=new Login();
                for (Iterator<Attribute> jsoniterator = element.attributeIterator(); jsoniterator.hasNext();) {
                    Attribute jsonattribute = jsoniterator.next();
                    switch (jsonattribute.getName())
                    {
                        case "url":
                            login.setUrl(jsonattribute.getValue());
                            break;
                        case "usernamekey":
                            login.setUsernamekey(jsonattribute.getValue());
                            break;
                        case "passwordkey":
                            login.setPasswordkey(jsonattribute.getValue());
                            break;
                        case "cookiekey":
                            login.setCookiekey(jsonattribute.getValue());
                            break;
                        case "tokenkey":
                            login.setTokenkey(jsonattribute.getValue());
                            break;
                        case "create":
                            login.setCreate(jsonattribute.getValue());
                            break;
                        case "username":
                            login.setUsername(jsonattribute.getValue());
                            break;
                        case "password":
                            login.setPassword(jsonattribute.getValue());
                            break;
                    }
                }
                List<Json> jsonitemList=new ArrayList<Json>();
                while(loginelementIterator.hasNext())
                {
                    Element jsonelement=loginelementIterator.next();
                    Json jsonitem=new Json();
                    for (Iterator<Attribute> jsoniterator = jsonelement.attributeIterator(); jsoniterator.hasNext();) {
                        Attribute jsonattribute = jsoniterator.next();
                        switch(jsonattribute.getName())
                        {
                            case "describe":
                                jsonitem.setDescribe(jsonattribute.getValue());
                                break;
                            case "url":
                                jsonitem.setUrl(jsonattribute.getValue());
                                break;
                            case "method":
                                jsonitem.setMethod(jsonattribute.getValue());
                                break;
                            case "name":
                                jsonitem.setName(jsonattribute.getValue());
                                break;
                            case "create":
                                jsonitem.setCreate(jsonattribute.getValue());
                                break;
                        }
                    }
                    jsonitemList.add(jsonitem);
                }
                login.setJson(jsonitemList);
                loginList.add(login);
            }
            System.out.println(auto);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return auto;
    }
}
