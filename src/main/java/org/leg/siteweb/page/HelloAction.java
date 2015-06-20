package org.leg.siteweb.page;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.leg.library.type.Record;
import org.leg.siteweb.common.Storage;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 日志记录动作
 */
public class HelloAction extends ActionSupport {
	private static final long serialVersionUID = 5165050349215752795L;

	/**
	 * 日志对象
	 */
	protected static Logger logger = Logger.getLogger(HelloAction.class);

	/**
	 * 执行请求动作
	 */
    public String execute() throws Exception {
		Record record = Storage.executor().load("SELECT * FROM T_Caption WHERE ID = 2");
		// output("Caption = " + record.get("Caption"));
		ServletActionContext.getContext().put("message", "Caption = " + record.get("Caption"));
		return "ftl";
    }

	/**
	 * 输出控制台内容
	 * 
	 * @param content 控制台内容
	 */
	public void output(String content) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		}
		catch (IOException ex) {
			logger.error("call Response.getWriter() failed", ex);
			return;
		}
		writer.write(content);
		writer.close();
	}
}
