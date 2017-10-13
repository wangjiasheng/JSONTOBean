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
        Element root = document.addElement("auto").addAttribute("path","c:/test");
        Element login=root.addElement("login")
                .addAttribute("url","https://www.baifubao.com/callback?cmd=1059&callback=phone&phone=15321810823")
                .addAttribute("usernamekey","txtusername")
                .addAttribute("passwordkey","txtpassword")
                .addAttribute("cookiekey","cookietag")
                .addAttribute("tokenkey","token")
                .addAttribute("create","true")
                .addAttribute("username","15711406593")
                .addAttribute("password","812330500");
        login.addElement("json")
        .addAttribute("describe","评论")
        .addAttribute("url","http://yanyi.itingwang.com/seller/getsellercomments")
        .addAttribute("method","POST")
        .addAttribute("name","LoginBean")
        .addAttribute("create","false")
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
