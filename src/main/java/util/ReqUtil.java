package util;



//package org.chwin.firefighting.apiserver.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.NonNull;


/**
 * 请求封装
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
public class ReqUtil {
	protected final Log logger = LogFactory.getLog(getClass());
	public static int start=0;
	public static Integer pageNo=1;
	public static int pageSize=20;
	
	
	public static void main(String[] args) {
		System.out.println("--f");
	}
	public static	String expressChar = "$";
	public static String getWhere(Map reqM) {
		// LinkedHashMap<String , String> m=Maps.newLinkedHashMap();
		List<String> li = Lists.newArrayList();
		reqM.forEach(new BiConsumer<String, String>() {

			@Override
			public void accept(String tit, String val) {
				String colTrim = tit.trim();

//				if (trim.endsWith("_start@")) // fake col support for between
//				{
//					String col = (trim.substring(0, trim.length() - 7));
//					li.add(col + ">='" + u + "'");
//				} else if (trim.endsWith("_end@")) // fake col support for between
//				{
//					String col = (trim.substring(0, trim.length() - 5));
//					li.add(col + "<='" + u + "'");
//				}

				// col@start
				
				if (!colTrim.startsWith(expressChar) && colTrim.contains(expressChar) && !colTrim.endsWith(expressChar)) // udf
				{

					int at = colTrim.indexOf(expressChar);
					String col = colTrim.substring(0, at);
					String fun = colTrim.substring(at + 1);
					//// BETWEEN in exist like is _null
					if (fun.trim().toLowerCase().equals("in")) {
						String string = col + " " + fun + "(" + val.trim() + ")";
						li.add(string);
					} else if (fun.trim().toLowerCase().equals("start")) {

						li.add(col + ">='" + val + "'");
					} else if (fun.trim().toLowerCase().equals("end")) {

						li.add(col + "<='" + val + "'");
					} else if (fun.trim().toLowerCase().equals("date_from_unixtime")) {
						String string = fun + "(" + col + ") =" + " '" + val.trim() + "' ";
						li.add(string);
					} else {//// c=fun(u)
						String string = col + " =" + fun + "('" + val.trim() + "')";
						li.add(string);
					}

				} else if (!colTrim.contains(expressChar)) { // normal col
					// BETWEEN in exist like is _null
					// m.put(t, u);
					if (val.contains(expressChar)) {
						int at = val.indexOf(expressChar);
						String data = val.substring(0, at).trim();
						String fun = val.substring(at + 1).trim().toLowerCase();
						if (fun.equals("in") || fun.equals("not in") || fun.equals("exist")) {
							String string = colTrim + " " + fun + "(" + data + ") ";
							li.add(string);
						} else if (fun.equals("between"))
						// || first_token.equals("in") || first_token.equals("exist")||
						// first_token.equals("like") || first_token.equals("is null")
						{
							data=getBtween(data);
							data=data.replaceAll(",", " and ");
							li.add(colTrim + " between " + data);
 
						}else if (fun.equals("like"))
						{
							li.add(colTrim + " like '" + data+"'");
						}else {
							
						}
					}

					else
						li.add(tit + "='" + val + "'");
				}

			}
		});

//		m.forEach((new BiConsumer<String, String>(){
//
//			@Override
//			public void accept(String t, String u) {
//				 
//				
//			}});
		return Joiner.on(" and ").join(li);
	}

	protected static String getBtween(String data) {
		List<String> li=Lists.newArrayList();
		String[] a=data.split(",");
		for (String s : a) {
			li.add("'"+s+"'");
		}
		return Joiner.on(" and ").join(li);
	}
	/**
	 * 获取基础路劲
	 * @param req
	 * @return
	 */
	public static String getBasePath(HttpServletRequest req) {
		String path = req.getContextPath();
		String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path;
		return basePath;
	}
		/**
	 * 获取session
	 * 
	 * @param request
	 * @return
	 */
	public static HttpSession getCRSession(HttpServletRequest request) {
		return request.getSession();
	}


	
	public static Long getLong(String name, HttpServletRequest req) {
		return Long.parseLong(req.getParameter(name));
	}

	public static Integer getInt(String name, HttpServletRequest req) {
		return Integer.parseInt(req.getParameter(name));
	}

	public static Map getMap(@NotNull @NonNull HttpServletRequest req) {
		Map params = new HashMap();
		Map p = req.getParameterMap();
		Iterator itor = p.keySet().iterator();
		while (itor.hasNext()) {
			String key = (String) itor.next();
			String[] values = req.getParameterValues(key);
			if (values != null && values.length > 0) {
				if (values.length > 1) {
					params.put(key, values);
				} else {
					params.put(key, values[0]);
				}
			} else {
				params.put(key, "");
			}

		}
		return params;
	}
	
	/**
	 * 将请求参数封装为Dto
	 * 
	 * @param request
	 * @return
	 */

	/**
	 * 获取参数
	 * @param url
	 * @return
	 */
//	public static String getParamFromURl(String url,String surl) {
//		String param=null;
//		if(StringUtil.isNotEmpty(url)){
//			String tempUrl = url.substring(url.lastIndexOf(surl), url.length());
//			String params[] = tempUrl.split("\\/");
//			if(params.length==3){
//			   param = params[2];
//			}
//		}
//		StringUtil.xprint("param="+param);
//		return param;
//	}
	
	//ATI q224
public	static	ThreadLocal<HttpServletResponse> resp=new ThreadLocal();

//{
//	if(params.get()==null)
//		params.set(Maps.newLinkedHashMap());
//}

	/**
	 * 直接输出.
	 * 
	 * @param contentType
	 *            内容的类型.html,text,xml的值见后，json为"text/x-json;charset=UTF-8"
	 */

//	/**
//	 * 直接输出纯字符串.
//	 */
//	public static void renderText(String text) {
//		render(text, "text/plain");
//	}
//
//	/**
//	 * 直接输出纯HTML.
//	 */
//	public static void renderHtml(String text) {
//		render(text, "text/html");
//	}

	/**
	 * 直接输出纯XML.
	 */
	public static void renderXML(String text) {
		render(text, "text/xml");
	}

	private static void render(String text, String s) {
	}

	public static String getWhere(HttpServletRequest req) {
		LinkedHashMap m = Maps.newLinkedHashMap();

		Map reqM = ReqUtil.getMap(req);
		String where = getWhere(reqM);
		if( where.trim().length()>3)
			return " where "+where;
		return "";
	}


	public static String orderExp(HttpServletRequest req) {
		String orderExp = "  ";
		if(req.getParameter("$orderby")!=null)
			  orderExp=" order by "+req.getParameter("$orderby");
		return orderExp;
	}

	public static String selectExp(HttpServletRequest req ) {
		String select = " * ";
		if (req.getParameter("$select") != null)
			select = req.getParameter("$select");
		return select;
	}

 
	
	public static void setHttpHead(HttpServletResponse res) {
		try {
			res.addHeader("Access-Control-Allow-Origin", "*");
			res.setHeader("Content-Type", "application/json;charset=UTF-8");

		} catch (Exception e) {
			// logger.info("",e);
		}
	}
 
}
