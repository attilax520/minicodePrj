package other;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class TestRdsCli {
	public static void main(String[] args) throws Exception {

		Map m = new LinkedHashMap();
		m.put("$mthd", "set");
		m.put("p1", "k11");
		m.put("p2", "v22");

		String prm_json = JSON.toJSONString(m, true);
		// 使用基本编码
		String base64encodedString = Base64.getEncoder().encodeToString(prm_json.getBytes("gbk"));
		System.out.println("Base64 编码字符串 (基本) :" + base64encodedString);
		ApplicationCli.main(base64encodedString.split("分隔符"));
	}

	@Test
	public void tetGet() throws Exception {
		Map m = new LinkedHashMap();
		m.put("$mthd", "get");
		m.put("p1", "k11");
		m.put("p2", "v22");

		String prm_json = JSON.toJSONString(m, true);
		// 使用基本编码
		String base64encodedString = Base64.getEncoder().encodeToString(prm_json.getBytes("gbk"));
		System.out.println("Base64 编码字符串 (基本) :" + base64encodedString);
		ApplicationCli.main(base64encodedString.split("分隔符"));

	}

	@Test
	public void tetCmdInvoke() throws Exception {

		String java = "C:\\Program Files\\Java\\jre1.8.0_131\\bin\\java.exe";
		String sprbtCfgPath = "C:\\Users\\ATI\\eclipse-workspace\\rdsprj\\rdsSprbtCfg";
		String clspath = "C:\\Users\\ATI\\eclipse-workspace\\rdsprj\\target\\classes";
		String jarpath_libRds = "C:\\Users\\ATI\\eclipse-workspace\\rdsprj\\libRds\\*";
		String main_cls = "rdsPKg.ApplicationCli";

		List<String> cmdParms = new ArrayList<String>();
		cmdParms.add(java);
		cmdParms.add("-cp");
		cmdParms.add(sprbtCfgPath + ";" + clspath + ";" + jarpath_libRds);
		cmdParms.add(main_cls);
	 

		// ---------add parm and encode prms
		Map m = new LinkedHashMap();
		m.put("$mthd", "get");
		m.put("p1", "k11");
		m.put("p2", "v22");
		System.out.println("main_cls_params:"+JSON.toJSONString(m, true));
		String enc_main_cls_params = Base64.getEncoder().encodeToString(JSON.toJSONString(m, true).getBytes("gbk"));
		cmdParms.add(enc_main_cls_params);
		
		String[] array = cmdParms.stream().toArray(String[]::new);
		System.out.println(JSON.toJSONString(array, true));
		Process process = Runtime.getRuntime().exec(array);

		// ---std ouput
		List<String> readLines = IOUtils.readLines(process.getInputStream());
		System.out.println(JSON.toJSONString(readLines,true));
		if (readLines.size() > 0) {
			String rzt = readLines.get(readLines.size()-1);
			System.out.println("--rzt:" + rzt);

		}

		// -----err output
		List<String> errLine = IOUtils.readLines(process.getErrorStream());
		if (errLine.size() > 0)
			System.out.println("--err:" + JSON.toJSONString(errLine, true));

	}

}
