package util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpUtilV5v44 {

	public static Map getParamMap2Mybatis(HttpServletRequest req) {
//		 

		// ---序列化传递cookie getParameterMap session
		Map m = new LinkedHashMap();
		m.put("getParameterMap", req.getParameterMap());
		m.put("getParameterMapFstVal", getParameterMapFstVal(req));
		m.put("getCookies", req.getCookies());
		m.put("cookie", req.getCookies());
		m.put("getSession", serizSession(req));
		m.put("session", serizSession(req));
		m.put("session_m", serizSession(req));
		m.put("v", 7);

		return m;

	}

	/**
	 * 解析第三方返回值
	 *
	 * @param request 请求
	 * @return map
	 */
	private static Map getParameterMapFstVal(HttpServletRequest request) {

		Map map = new HashMap();
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() != 0) {
					map.put(paramName, paramValue);
				}

			}
		}
		return map;
	}

	// 序列化session
	private static Object serizSession(HttpServletRequest req) {
		Map m = new LinkedHashMap();
		HttpSession session = req.getSession();

		// 获取session中所有的键值
		Enumeration<?> enumeration = session.getAttributeNames();
		// 遍历enumeration中的
		while (enumeration.hasMoreElements()) {
			// 获取session键值
			String name = enumeration.nextElement().toString();
			// 根据键值取session中的值
			Object value = session.getAttribute(name);
			m.put(name, value);
		}
		return m;

	}

}
