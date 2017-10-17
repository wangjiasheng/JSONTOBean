package com.wjs.lib.xml.parse;

import com.wjs.lib.xml.ben.Auto;
import com.wjs.lib.xml.ben.Json;
import com.wjs.lib.xml.ben.Login;
import com.wjs.lib.xml.utils.JSONCreate;
import com.wjs.lib.xml.utils.NetworkUtis;
import com.wjs.lib.xml.utils.ParamsUtil;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonToBeanParse {
    public static void main(String[] args)
    {
        Auto auto=parseXML("E:\\intellij idea code\\jsontobean.xml");
        //System.out.println(auto);
        String path=auto.getPath();
        List<Login> paths=auto.getLogn();
        for(int i=0;i<paths.size();i++)
        {
            Login login=paths.get(i);
            LoginIn(auto,login);
        }
    }
    public static void LoginIn(Auto auto,Login login)
    {
        String create=login.getCreate();
        if(create.equals("true"))
        {
            String loginMethod=login.getMethod();
            switch (loginMethod)
            {
                case "get":
                case "GET":
                case "Get":
                    Map<String,String> getparam=new HashMap<String,String>();
                    getparam.put(login.getUsernamekey(),login.getUsername());
                    getparam.put(login.getPasswordkey(),login.getPassword());
                    getparam.putAll(login.getExt());
                    String jsonget=NetworkUtis.sendGet(login.getUrl(), ParamsUtil.parseParams(getparam));
                    if(login.getTokenkey()!=null)
                    {
                        String token=parseToken(jsonget);
                        List<Json> mList=login.getJson();
                        for(int j=0;j<mList.size();j++)
                        {
                            Json mJson=mList.get(j);
                            parseJSON(auto,token,mJson);
                        }
                    }
                    break;
                case "post":
                case "POST":
                case "Post":
                    Map<String,String> postparam=new HashMap<String,String>();
                    postparam.put(login.getUsernamekey(),login.getUsername());
                    postparam.put(login.getPasswordkey(),login.getPassword());
                    postparam.putAll(login.getExt());
                    System.out.println("Post:"+login.getUrl()+"?"+ParamsUtil.parseParams(postparam));
                    String jsonpost= NetworkUtis.sendPost(login.getUrl(),ParamsUtil.parseParams(postparam));
                    if(login.getTokenkey()!=null)
                    {
                        String token=parseToken(jsonpost);
                        List<Json> mList=login.getJson();
                        for(int j=0;j<mList.size();j++)
                        {
                            Json mJson=mList.get(j);
                            parseJSON(auto,token,mJson);
                        }
                    }
                    break;
            }
        }
    }
    public static String parseJSON(Auto auto,String token,Json mJson)
    {
        String isCreate=mJson.getCreate();
        if("true".equals(isCreate)) {
            String method = mJson.getMethod();
            switch (method) {
                case "get":
                case "GET":
                case "Get":
                    Map<String, String> getparam = new HashMap<String, String>();
                    getparam.putAll(mJson.getExt());
                    System.out.println("Get:" + mJson.getUrl() + "?" + ParamsUtil.parseParams(getparam));
                    String jsonget = NetworkUtis.sendGet(mJson.getUrl(), ParamsUtil.parseParams(getparam));
                    new JSONCreate().creaetBean(auto.getAppPackage(),jsonget,mJson.getName(),auto.getPath(),false);
                    //parseJSON(jsonget);
                    break;
                case "post":
                case "POST":
                case "Post":
                    Map<String, String> postparam = new HashMap<String, String>();
                    postparam.putAll(mJson.getExt());
                    System.out.println("Post:" + mJson.getUrl() + "?" + ParamsUtil.parseParams(postparam));
                    String jsonpost = NetworkUtis.sendPost(mJson.getUrl(), ParamsUtil.parseParams(postparam));
                    new JSONCreate().creaetBean(auto.getAppPackage(),jsonpost,mJson.getName(),mJson.getDescribe(),auto.getPath(),false);
                    break;
            }
        }
        return null;
    }
    /*public static String getRequest(String request)
    {
        return null;
    }
    public static String postRequest(String request)
    {
        return null;
    }

    */
    public static String parseToken(String json)
    {
        Pattern p = Pattern.compile("\"token\":\"(.*?)\"");
        Matcher m = p.matcher(json);
        if (m.find()) {
            return m.group(1);
        } ;
        return null;
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
                    case "package":
                        auto.setAppPackage(jsonattribute.getValue());
                        break;
                }
            }
            List<Login> loginList=new ArrayList<Login>();
            auto.setLogn(loginList);
            for (Iterator<Element> it = rootElement.elementIterator(); it.hasNext();) {
                Element element = it.next();                                     //LOGIN
                Iterator<Element> loginelementIterator=element.elementIterator();
                Login login=new Login();
                Map<String,String> loginExt=new HashMap<String,String>();
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
                        case "method":
                            login.setMethod(jsonattribute.getValue());
                            break;
                        default:
                            loginExt.put(jsonattribute.getName(),jsonattribute.getValue());
                            break;
                    }
                }
                login.setExt(loginExt);
                List<Json> jsonitemList=new ArrayList<Json>();
                while(loginelementIterator.hasNext())
                {
                    Element jsonelement=loginelementIterator.next();
                    Json jsonitem=new Json();
                    Map<String,String> jsonExt=new HashMap<String,String>();
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
                            default:
                                jsonExt.put(jsonattribute.getName(),jsonattribute.getValue());
                                break;
                        }
                    }
                    jsonitem.setExt(jsonExt);
                    jsonitemList.add(jsonitem);
                }
                login.setJson(jsonitemList);
                loginList.add(login);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return auto;
    }
}
