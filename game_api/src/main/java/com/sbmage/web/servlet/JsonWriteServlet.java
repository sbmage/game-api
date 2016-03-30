package com.sbmage.web.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JsonWriteServlet extends HttpServlet {
	private static final long serialVersionUID = -3447677902841669854L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//JSONObject result = (JSONObject) request.getAttribute("result");
		String result = request.getParameter("result");
		System.out.println("[ATT]" + request.getAttribute("result"));
		System.out.println("[PARAM]" + request.getParameter("result"));
		BufferedInputStream bin = null;
		BufferedOutputStream bos = null;

		try{
			response.reset();
			response.setContentType("text/html");
			response.setHeader("Content-Length", "" + result.toString().getBytes("utf-8").length);

			bin = new BufferedInputStream(new ByteArrayInputStream(result.toString().getBytes("utf-8")));
			bos = new BufferedOutputStream(response.getOutputStream());

			byte[] buf = new byte[2048];
			int read = 0;

			while ((read = bin.read(buf)) != -1) {
				bos.write(buf, 0, read);
			}

			bos.flush();
		} catch(IOException e) {
			//e.printStackTrace();
		} finally {
			bos.close();
			bin.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//JSONObject result = (JSONObject) request.getAttribute("result");
		String result = request.getParameter("result");
		System.out.println("[ATT]" + request.getAttribute("result"));
		System.out.println("[PARAM]" + request.getParameter("result"));
		BufferedInputStream bin = null;
		BufferedOutputStream bos = null;

		try{
			response.reset() ;
			response.setContentType("text/html");
			response.setHeader("Content-Length", "" + result.toString().getBytes("utf-8").length);

			bin = new BufferedInputStream(new ByteArrayInputStream(result.toString().getBytes("utf-8")));
			bos = new BufferedOutputStream(response.getOutputStream());

			byte[] buf = new byte[2048];
			int read = 0;

			while ((read = bin.read(buf)) != -1) {
				bos.write(buf, 0, read);
			}

			bos.flush();
		} catch(IOException e) {
			//e.printStackTrace();
		} finally {
			bos.close();
			bin.close();
		}
	}
}
