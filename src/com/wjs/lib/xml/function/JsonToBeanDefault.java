package com.wjs.lib.xml.function;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class JsonToBeanDefault {
    public final static  void main(String[] args)
    {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("auto").addAttribute("path","E:\\intellij idea code\\src").addAttribute("package","com.wjs.test");
        Element login=root.addElement("login")
            .addAttribute("url","http://10.232.128.113:8180/v1/app/login")
            .addAttribute("method","POST")
            .addAttribute("usernamekey","loginNo")
            .addAttribute("passwordkey","loginPwd")
            .addAttribute("cookiekey","cookietag")
            .addAttribute("tokenkey","token")
            .addAttribute("create","true")
            .addAttribute("username","15321810886")
            .addAttribute("password","Wang520")
            .addAttribute("loginType","01")
            .addAttribute("create","true")
             ;
        login.addElement("json")
            .addAttribute("describe","评论")
            .addAttribute("url","http://yanyi.itingwang.com/seller/getsellercomments")
            .addAttribute("method","POST")
            .addAttribute("name","LoginBean")
            .addAttribute("create","true")
            ;
        try {
            OutputFormat outputFormat = OutputFormat.createPrettyPrint();
            outputFormat.setEncoding("UTF-8");
            outputFormat.setSuppressDeclaration(false); //是否生产xml头
            outputFormat.setIndent(true); //设置是否缩进
            outputFormat.setIndent("    "); //以四个空格方式实现缩进
            outputFormat.setNewlines(true); //设置是否换行
            outputFormat.setNewLineAfterDeclaration(false);//解决声明下空行问题
            FileWriter stringWriter  = new FileWriter("jsontobean.xml");
            XMLWriter xmlWriter = new XMLWriter(stringWriter, outputFormat);
            xmlWriter.write(document);
            stringWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
