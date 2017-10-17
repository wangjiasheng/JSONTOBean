package com.wjs.lib.xml.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JSONPast
{
	public final static String empty="   ";
	/**
	 * @param comment 生成的JSONBean注释
	 * @param doGet   JSON原串
	 * @param packName 存放路径(包名+Bean)
	 * @param beanName 生成的JSONBean的类名
	 * @return 生成的JSONBean字符串代码
	 */
	public static StringBuffer parseJSON(String comment,String doGet,String packName,String beanName){
		StringBuffer packBuffer=new StringBuffer();//导入包的StringBuffer
		StringBuffer codeBuffer=new StringBuffer();
		packBuffer.append("/**\r\n *"+comment+"\r\n */\r\n");
		packBuffer.append("package "+packName+";\r\n");
		packBuffer.append("import java.util.List;\r\n");
		packBuffer.append("import java.util.ArrayList;\r\n");
		packBuffer.append("import org.json.JSONObject;\r\n");
		packBuffer.append("import org.json.JSONArray;\r\n");
		packBuffer.append("import org.json.JSONException;\r\n");
		codeBuffer.append("public class "+beanName+""+"\r\n");//创建ParseJSON类
		codeBuffer.append("{"+"\r\n");
		pastJSONObject(1,packName+"."+beanName,packBuffer,doGet,codeBuffer);
		addJSONValue(doGet,beanName,codeBuffer);
		codeBuffer.append("\r\n}");
		return packBuffer.append(codeBuffer.toString());
	}
	private static void addJSONValue(String doGet,String beanName,StringBuffer buffer) {
		buffer.append(empty+"public static "+beanName+" parseJSON(String createBean)");
		buffer.append("\r\n"+empty+"{"+"\r\n");
		addJSONObjectValue(2,"jsonobj",doGet,beanName,buffer);
		buffer.append("\r\n"+empty+"}");
	}
	private static void addJSONObjectValue(int count,String key,String doGet,String beanName,StringBuffer buffer)
	{
		String tab="";
		for(int i=0;i<count;i++)
		{
			tab+=empty;
		}
		buffer.append(tab+beanName+" name=new "+beanName+"();\r\n");
		buffer.append(tab+"try \r\n"+tab+"{\r\n");
		buffer.append(tab+empty+"JSONObject "+key+"=new JSONObject(createBean);\r\n");
		////////////////////////////添加无效的代码其他地方自行修改////////////////////////////////
		buffer.append(tab+"   if("+key+".getInt(\"code\")!=10000)\r\n");
		buffer.append(tab+"   {\r\n");
		buffer.append(tab+"       name.setCode("+key+".getInt(\"code\"));\r\n");
		buffer.append(tab+"       name.setMessage("+key+".getString(\"message\"));\r\n");
		buffer.append(tab+"       return name;\r\n");
		buffer.append(tab+"   }\r\n");
		////////////////////////////////////////////////////////////
		pastJS(++count,key,"name",doGet,buffer);
		buffer.append(tab+empty+"return "+"name"+";");
		buffer.append(tab+"\r\n"+tab+"}\r\n"+tab+"catch (JSONException e) \r\n"+tab+"{\r\n"+tab+empty+"// TODO Auto-generated catch block\r\n"+tab+empty+"e.printStackTrace();\r\n"+tab+"}\r\n");
		buffer.append(tab+"return null;");
	}
	public static void pastJS(int count,String key,String name,String doGet,StringBuffer buffer)//javabean解析生成
	{
		Map<String,String> map=new HashMap<String,String>();
		String tab="";
		for(int i=0;i<count;i++)
		{
			tab+=empty;
		}
		try 
		{
			JSONObject jsonObject=new JSONObject(doGet);
			Iterator<String> keys = jsonObject.keys();
			while(keys.hasNext())
			{
				String next = keys.next();
				if(jsonObject.get(next) instanceof JSONArray)
				{
					String randkey=next+"List";//命名
					map.put(randkey, jsonObject.getString(next));
				}
				else if(jsonObject.get(next) instanceof JSONObject)
				{
					buffer.append(tab+next+" "+next+"Value=new "+next+"();\r\n");
					buffer.append(tab+"JSONObject "+"jb"+next+key+" ="+key+".getJSONObject(\""+next+"\");"+"\r\n");
					pastJS(count, "jb"+next+key,next+"Value",jsonObject.getString(next), buffer);
					buffer.append(tab+name+".set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"("+next+"Value"+");\r\n");
				}
				else if(jsonObject.get(next) instanceof String)
				{
					String parseValue=key+".getString(\""+next+"\")";
					buffer.append(tab+name+".set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"("+parseValue+");\r\n");
				}
				else if(jsonObject.get(next) instanceof Integer)
				{
					String parseValue=key+".getInt(\""+next+"\")";
					buffer.append(tab+name+".set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"("+parseValue+");\r\n");
				}
				else if(jsonObject.get(next) instanceof Float)
				{
					String parseValue=key+".getFloat(\""+next+"\")";
					buffer.append(tab+name+".set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"("+parseValue+");\r\n");
				}
				else if(jsonObject.get(next) instanceof Double)
				{
					String parseValue=key+".getDouble(\""+next+"\")";
					buffer.append(tab+name+".set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"("+parseValue+");\r\n");
				}
				else if(jsonObject.get(next) instanceof Long)
                {
					String parseValue=key+".getLong(\""+next+"\")";
					buffer.append(tab+name+".set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"("+parseValue+");\r\n");
                }
				else if(jsonObject.get(next) instanceof Byte)
                {
					String parseValue=key+".getByte(\""+next+"\")";
					buffer.append(tab+name+".set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"("+parseValue+");\r\n");
                }
				else if(jsonObject.get(next) instanceof Short)
                {
					String parseValue=key+".getShort(\""+next+"\")";
					buffer.append(tab+name+".set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"("+parseValue+");\r\n");
                }
				else if(jsonObject.get(next) instanceof Character)
                {
					String parseValue=key+".getCharacter(\""+next+"\")";
					buffer.append(tab+name+".set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"("+parseValue+");\r\n");
                }
				else if(jsonObject.get(next) instanceof Boolean)
                {
					String parseValue=key+".getBoolean(\""+next+"\")";
					buffer.append(tab+name+".set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"("+parseValue+");\r\n");
                }
				else
				{
					String parseValue=key+".getString(\""+next+"\")";
					buffer.append(tab+name+".set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"("+parseValue+");\r\n");
				}
			}
			Set<String> keySet = map.keySet();
			Iterator<String> iterator = keySet.iterator();
			while(iterator.hasNext())
			{
				String next = iterator.next();
				String string = map.get(next);
				pastJA(count,key, next,name,string, buffer);
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}
	public static void pastJA(int count,String key,String next,String name,String doGet,StringBuffer buffer)//javabean解析生成
	{
		String tab="";
		for(int i=0;i<count;i++)
		{
			tab+=empty;
		}
		try {
			JSONArray jsonarray=new JSONArray(doGet);
			for(int i=0;i<(jsonarray.length()>0?1:0);i++)
			{
				if(jsonarray.get(i) instanceof JSONObject)
				{
					buffer.append(tab+"//------------------------\r\n");
					buffer.append(tab+"List<"+next.substring(0,next.length()-4)+"> "+next+"= new ArrayList<"+next.substring(0,next.length()-4)+">();"+"\r\n");
					buffer.append(tab+"JSONArray "+next.substring(0,next.length()-4)+"jsonarray"+"="+key+".getJSONArray(\""+next.substring(0,next.length()-4)+"\");\r\n");
					buffer.append(tab+"for(int "+next.substring(0,next.length()-4)+"jsonarraytemp"+"=0;"+next.substring(0,next.length()-4)+"jsonarraytemp"+"<"+next.substring(0,next.length()-4)+"jsonarray"+".length();"+next.substring(0,next.length()-4)+"jsonarraytemp"+"++)\r\n");
					buffer.append(tab+"{\r\n");
					buffer.append(tab+empty+next.substring(0,next.length()-4)+" "+next.substring(0,next.length()-4)+"item"+"=new "+next.substring(0,next.length()-4)+"();\r\n");
					buffer.append(tab+empty+"JSONObject "+next.substring(0,next.length()-4)+"jsonarrayitem="+next.substring(0,next.length()-4)+"jsonarray.getJSONObject("+next.substring(0,next.length()-4)+"jsonarraytemp"+");\r\n");
					pastJS(++count,next.substring(0,next.length()-4)+"jsonarrayitem",next.substring(0,next.length()-4)+"item",jsonarray.getString(i),buffer);
					buffer.append(tab+empty+next+".add("+next.substring(0,next.length()-4)+"item"+");\r\n");
					buffer.append(tab+"}\r\n");
				}
				if(jsonarray.get(i) instanceof JSONArray)
				{
					buffer.append("@@@@pastJA has error");
				}
			}
			if(jsonarray.length()>0)
			{
				buffer.append(tab+name+".set"+next.substring(0,next.length()-4).substring(0,1).toUpperCase()+next.substring(1,next.length()-4)+"("+next+");\r\n");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param count 代码缩进空格数量
	 * @param packName 存放JSONBean的路径(包名+bean)
	 * @param packBuffer　生成的JSONbean的内部类导包
	 * @param doGet 将要解析的JSON原串
	 * @param codeBuffer 代码容器，用来存储生成的代码
	 */
	private static void pastJSONObject(int count,String packName,StringBuffer packBuffer,String doGet,StringBuffer codeBuffer) //生成JSONobject的JavaBean对象
	{
		Map<String,String> jsonBeanObj=new HashMap<String,String>();//装有JSONArray的Key和Value
		Map<String,String> jsonArrayObj=new HashMap<String, String>();
		StringBuilder JSONBeanFeildbuilder=new StringBuilder();//生成JSONBean成员变量容器
		StringBuilder JSONBeanMethodbuilder=new StringBuilder();//生成的JSONBean方法容器
		String tab=""; //生成缩进常量
		for(int i=0;i<count;i++)
		{
			tab+=empty;
		}
		try
		{
			count++;
			JSONObject ob=new JSONObject(doGet);
			Iterator<String> keys = ob.keys();
			while(keys.hasNext())
			{
				String next = keys.next();
				if(ob.get(next) instanceof JSONArray)//生成JSONAray的set和get方法
				{
					if(count>2)//生成第二层以上内部类需要导入包
					{
						JSONArray jsa=ob.getJSONArray(next);
						if(jsa.length()>0)
						{
							packBuffer.append("import "+packName+"."+next+";\r\n");
						}
						else
						{
							packBuffer.append("//TODO JSON解析失败！"+next+"的长度为零导致解析不完整\r\n");
						}
					}
					JSONBeanFeildbuilder.append(tab+"private List<"+next+"> "+next+";"+"\r\n");
					jsonBeanObj.put(next, ob.getString(next));
					JSONBeanMethodbuilder.append(tab+"public void set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"(List<"+next+"> value"+")");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"this."+next+"=value;");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
					JSONBeanMethodbuilder.append(tab+"public List<"+next+"> get"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"()");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"return "+next+";");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
				}
				else if(ob.get(next) instanceof JSONObject)
				{
					JSONBeanFeildbuilder.append(tab+"private "+next+" "+next+";\r\n");
					jsonArrayObj.put(next, ob.getString(next));
					JSONBeanMethodbuilder.append(tab+"private void set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"("+next+" value)\r\n"+tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"this."+next+"=value;\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
					JSONBeanMethodbuilder.append(tab+"public "+next+" get"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"()");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"return "+next+";");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
				}
				else if(ob.get(next) instanceof String)
				{
					JSONBeanFeildbuilder.append(tab+"private String "+next+";"+"\r\n");
					JSONBeanMethodbuilder.append(tab+"public void set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"(String value"+")");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"this."+next+"=value;");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
					JSONBeanMethodbuilder.append(tab+"public String get"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"()");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"return "+next+";");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
				}
				else if(ob.get(next) instanceof Integer)
				{
					JSONBeanFeildbuilder.append(tab+"private int "+next+";"+"\r\n");
					JSONBeanMethodbuilder.append(tab+"public void set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"(int value"+")");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"this."+next+"=value;");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
					JSONBeanMethodbuilder.append(tab+"public int get"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"()");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"return "+next+";");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
				}
				else if(ob.get(next) instanceof Float)
				{
					JSONBeanFeildbuilder.append(tab+"private float "+next+";"+"\r\n");
					JSONBeanMethodbuilder.append(tab+"public void set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"(float value"+")");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"this."+next+"=value;");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
					JSONBeanMethodbuilder.append(tab+"public float get"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"()");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"return "+next+";");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
				}
				else if(ob.get(next) instanceof Double)
				{
					JSONBeanFeildbuilder.append(tab+"private double "+next+";"+"\r\n");
					JSONBeanMethodbuilder.append(tab+"public void set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"(double value"+")");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"this."+next+"=value;");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
					JSONBeanMethodbuilder.append(tab+"public double get"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"()");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"return "+next+";");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
				}
				else if(ob.get(next) instanceof Long)
				{
					JSONBeanFeildbuilder.append(tab+"private long "+next+";"+"\r\n");
					JSONBeanMethodbuilder.append(tab+"public void set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"(long value"+")");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"this."+next+"=value;");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
					JSONBeanMethodbuilder.append(tab+"public long get"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"()");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"return "+next+";");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
				}
				else if(ob.get(next) instanceof Short)
				{
					JSONBeanFeildbuilder.append(tab+"private short "+next+";"+"\r\n");
					JSONBeanMethodbuilder.append(tab+"public void set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"(short value"+")");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"this."+next+"=value;");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
					JSONBeanMethodbuilder.append(tab+"public short get"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"()");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"return "+next+";");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
				}
				else if(ob.get(next) instanceof Byte)
				{
					JSONBeanFeildbuilder.append(tab+"private byte "+next+";"+"\r\n");
					JSONBeanMethodbuilder.append(tab+"public void set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"(byte value"+")");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"this."+next+"=value;");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
					JSONBeanMethodbuilder.append(tab+"public byte get"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"()");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"return "+next+";");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
				}
				else if(ob.get(next) instanceof Character)
				{
					JSONBeanFeildbuilder.append(tab+"private char "+next+";"+"\r\n");
					JSONBeanMethodbuilder.append(tab+"public void set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"(char value"+")");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"this."+next+"=value;");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
					JSONBeanMethodbuilder.append(tab+"public char get"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"()");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"return "+next+";");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
				}
				else if(ob.get(next) instanceof Character)
				{
					JSONBeanFeildbuilder.append(tab+"private boolean "+next+";"+"\r\n");
					JSONBeanMethodbuilder.append(tab+"public void set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"(boolean value"+")");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"this."+next+"=value;");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
					JSONBeanMethodbuilder.append(tab+"public boolean get"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"()");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"return "+next+";");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
				}
				else
				{
					JSONBeanFeildbuilder.append(tab+"///////////////空值用String代替://////////////////\r\n");
					JSONBeanFeildbuilder.append(tab+"private String "+next+";"+"\r\n");
					JSONBeanMethodbuilder.append(tab+"public void set"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"(String value"+")");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"this."+next+"=value;");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
					JSONBeanMethodbuilder.append(tab+"public String get"+String.valueOf(next.charAt(0)).toUpperCase()+next.substring(1,next.length())+"()");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"{\r\n");
					JSONBeanMethodbuilder.append(tab+empty+"return "+next+";");
					JSONBeanMethodbuilder.append("\r\n");
					JSONBeanMethodbuilder.append(tab+"}\r\n");
				}
			}
			codeBuffer.append(JSONBeanFeildbuilder.toString());
			codeBuffer.append(JSONBeanMethodbuilder.toString());
			Set<String> keySet = jsonBeanObj.keySet();
			Iterator<String> iterator = keySet.iterator();
			while(iterator.hasNext())
			{
				String nextKey = iterator.next();
				String nextValue =jsonBeanObj.get(nextKey);
				pastJsonArray(count,packName,packBuffer,nextKey,codeBuffer,nextValue);
			}
			Set<String> keySet2 = jsonArrayObj.keySet();
			Iterator<String> iterator2 = keySet2.iterator();
			while(iterator2.hasNext())
			{
				String nextKey = iterator2.next();
				String nextValue = jsonArrayObj.get(nextKey);
				codeBuffer.append("//jsonObj CreateClass\r\n");
				codeBuffer.append(tab+"public static class "+nextKey+"\r\n"+tab+"{\r\n");//生成内部类
				pastJSONObject(count, packName+"."+nextKey, packBuffer, nextValue, codeBuffer);
				codeBuffer.append(tab+"}\r\n");
			}
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}
	}
	public static void pastJsonArray(int count,String packName,StringBuffer packBuffer,String key,StringBuffer codeBuffer,String json)
	{
		String tab="";
		for(int i=0;i<count-1;i++)
		{
			tab+=empty;
		}
		try
		{
			JSONArray sjon=new JSONArray(json);
			codeBuffer.append("//jsonArray CreateClass\r\n");
			codeBuffer.append(tab+"public static class " +key+"\r\n");
			codeBuffer.append(tab+"{"+"\r\n");
			for(int i=0;i<(sjon.length()>0?1:0);i++)//只需要解析一层，生成javaBean对象目录
			{
				if(sjon.get(i) instanceof JSONObject)
				{
					pastJSONObject(count,packName+"."+key,packBuffer,sjon.getString(i),codeBuffer);
				}
				if(sjon.get(i) instanceof JSONArray)
				{
					codeBuffer.append("@@@@pastJsonArray has error");
				}
			}
			codeBuffer.append(tab+"}"+"\r\n");
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}
	}
}
