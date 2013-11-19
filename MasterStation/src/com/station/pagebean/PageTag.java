package com.station.pagebean;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 自定义分页标签类
 * 
 * @author Administrator
 * 
 */
public class PageTag extends SimpleTagSupport {

	// 1、后台处理分页显示的servlet的路径
	// <a href="MusicServlet?page=1">这里面需要三个参变量
	// <a href="-1path-?-2param-=-3value-">用字符串代替
	private String path;

	// 2、传递的参数
	private String param;

	// 3、当前页数
	private int currPage;

	// 4、总页数
	private int totalPage;

	// 5、是否有下拉列表
	private boolean hasSelect;

	// 6、是否有文本框
	private boolean hasTextField;

	public boolean isHasTextField() {
		return hasTextField;
	}

	public void setHasTextField(boolean hasTextField) {
		this.hasTextField = hasTextField;
	}

	public boolean isHasSelect() {
		return hasSelect;
	}

	public void setHasSelect(boolean hasSelect) {
		this.hasSelect = hasSelect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = this.getJspContext().getOut();// 获取Jsp容器

		StringBuffer sb = new StringBuffer();// 处理垃圾
		// 这样所有的字符串就对应一个对象sb
		if (currPage == 1) {
			sb.append("首页");
			sb.append("&nbsp;&nbsp;");
			sb.append("上一页");
			sb.append("&nbsp;&nbsp;");
		} else {
			sb.append("<a href=\"");
			sb.append(path);
			sb.append("&");
			sb.append(param);
			sb.append("=");
			sb.append("1");
			sb.append("\"");
			sb.append(">");
			sb.append("首页");
			sb.append("</a>");
			sb.append("&nbsp;&nbsp;");
			// 源代码换行
			sb.append("\n");

			sb.append("<a href=\"");
			sb.append(path);
			sb.append("&");
			sb.append(param);
			sb.append("=");
			sb.append(currPage - 1);
			sb.append("\"");
			sb.append(">");
			sb.append("上一页");
			sb.append("</a>");
			sb.append("&nbsp;&nbsp;");
			// 源代码换行
			sb.append("\n");
		}

		if (currPage == totalPage) {
			sb.append("下一页");
			sb.append("&nbsp;&nbsp;");
			sb.append("末页");
			sb.append("&nbsp;&nbsp;");
		} else {

			sb.append("<a href=\"");
			sb.append(path);
			sb.append("&");
			sb.append(param);
			sb.append("=");
			sb.append(currPage + 1);
			sb.append("\"");
			sb.append(">");
			sb.append("下一页");
			sb.append("</a>");
			sb.append("&nbsp;&nbsp;");
			// 源代码换行
			sb.append("\n");

			sb.append("<a href=\"");
			sb.append(path);
			sb.append("&");
			sb.append(param);
			sb.append("=");
			sb.append(totalPage);
			sb.append("\"");
			sb.append(">");
			sb.append("末页");
			sb.append("</a>");
		}
		sb.append("&nbsp;&nbsp;");

		if (this.hasSelect) {
			// 对下拉列表表单进行判断
			// <form action="MusicServlet">
			// <select name="page"
			// onchange="javascript:document.forms[0].submit()" >
			sb.append("<form action=\"");
			sb.append(path);
			sb.append("\">");
			sb.append("\n");
			sb.append("<select name=\"");
			sb.append(param);
			sb.append("\"");
			sb.append("onchange=\"javascript:document.forms[0].submit()\">");
			sb.append("\n");

			for (int i = 1; i <= totalPage; i++) {
				sb.append("<option value=\"");
				sb.append(i);
				sb.append("\"");
				sb.append("  ");
				if (i == currPage) {
					sb.append("selected");
				}
				sb.append(">");
				sb.append(i);
				sb.append("</option>");
				sb.append("\n");
			}
			sb.append("</select>");
			sb.append("</form>");
			sb.append("\n");
		}
		if (this.hasTextField) {
			sb.append("<form name=\"myForm\" action=\"");
			sb.append(path);
			sb.append("\">");
			sb.append("\n");
			sb.append("跳转至");
			sb.append("<input type=\"text\" size=\"3\" name=\"");
			sb.append(param);
			// 当光标移到这位置的时候，光标就近到文本框中去了！
			// sb.append("\" onMouseOver=\"javascript:focus()\">");
			sb.append("\">");
			sb.append("</form>");
			sb.append("\n");
			// ~但是想一进来光标就停在文本框中，每次刷新或是点下一页都在这里面，需要如下操作
			sb.append("<script language=\"javascript\">");
			sb.append("document.myForm.elements[0].focus()");
			sb.append("</script>");
			// ~
		}
		out.print(sb);
		// 测试用：System.out.println(sb);
	}

	public static void main(String[] args) throws JspException, IOException {
		// 测试
		// PageTag pt=new PageTag();
		// pt.setPath("MusicServlet");
		// pt.setParam("page");
		// pt.setCurrPage(6);
		// pt.setTotalPage(10);
		// pt.doTag();
	}
}
