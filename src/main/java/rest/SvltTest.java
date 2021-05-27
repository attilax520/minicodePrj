package rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
//import com.attilax.compress.ZipUtil;
//import com.attilax.io.pathx;
//import com.attilax.util.ExUtil;
//import com.attilax.util.Global;
//import com.attilax.util.linuxUtil;
//import com.attilax.util.cli.SSHHelper;
//import com.attilax.util.cli.cliRet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
//import com.jcraft.jsch.SftpProgressMonitor;

// 
@WebServlet(name = "UploadServlet1", urlPatterns = { "/sv1", "/sv2" }, loadOnStartup = 1)
public class SvltTest extends HttpServlet {
	protected static Logger logger = LoggerFactory.getLogger(SvltTest.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");

		ServletOutputStream out = response.getOutputStream();

		out.write("halo".getBytes());

	}

// 

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
