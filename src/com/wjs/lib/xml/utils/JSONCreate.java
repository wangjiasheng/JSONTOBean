package com.wjs.lib.xml.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONCreate {
	 public void creaetBean(String packege,String json,String beanname,String docname,String workdir,boolean replace)
	    {
		 StringBuffer buffer = JSONPast.parseJSON(docname,json,packege,beanname);
	        System.out.println(beanname+":sucess");
	        System.out.println(workdir+"\\"+packege.replaceAll("\\.","\\\\")+"\\"+beanname+".java");
	        try
	        {
	            File file=new File(workdir+"\\"+packege.replaceAll("\\.","\\\\")+"\\"+beanname+".java");
	            File parent=file.getParentFile();
	            if(!parent.exists())
				{
					parent.mkdirs();
				}
	            if(!file.exists())
	            {
	                file.createNewFile();
	            }
	            else if(!replace)
	            {
	            	System.err.println("javaBean已经存在");
	            	return;
	            }
	            FileOutputStream fileos=new FileOutputStream(file);
	            OutputStreamWriter writer=new OutputStreamWriter(fileos);
	            BufferedWriter bwBufferedWriter=new BufferedWriter(writer);
	            bwBufferedWriter.write(buffer.toString());

	            fileos.flush();
	            writer.flush();
	            bwBufferedWriter.flush();
	            fileos.close();
	            writer.close();
	            bwBufferedWriter.close();
	        }
	        catch (IOException ex)
	        {
	            ex.printStackTrace();
	        }
	    }
	 public void creaetBean(String packege,String json,String beanname,String workdir,boolean replace)
	    {
		  creaetBean(packege, json, beanname,null, workdir, replace);
	    }
}
