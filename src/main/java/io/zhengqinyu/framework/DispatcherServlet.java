package io.zhengqinyu.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.zhengqinyu.framework.bean.Data;
import io.zhengqinyu.framework.bean.Handler;
import io.zhengqinyu.framework.bean.Param;
import io.zhengqinyu.framework.bean.View;
import io.zhengqinyu.framework.helper.BeanHelper;
import io.zhengqinyu.framework.helper.ConfigHelper;
import io.zhengqinyu.framework.helper.ControllerHelper;
import io.zhengqinyu.framework.helper.RequestHelper;
import io.zhengqinyu.framework.helper.ServletHelper;
import io.zhengqinyu.framework.helper.UploadHelper;
import io.zhengqinyu.framework.util.JsonUtil;
import io.zhengqinyu.framework.util.RefletionUtil;
import io.zhengqinyu.framework.util.StringUtil;

/**
 * 请求转发器
 * 
 * @author ZhengQinyu
 */

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		HelperLoader.init();
		ServletContext servletContext = servletConfig.getServletContext();
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssertPath());
		UploadHelper.init(servletContext);
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletHelper.init(request, response);
		try {
			String requestMethod = request.getMethod().toLowerCase();
			String requestPath = request.getPathInfo();
			if (requestPath.equals("/favicon.ico")) {
				return;
			}
			Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
			if (handler != null) {
				Class<?> controllerClass = handler.getControllerClass();
				Object controolerBean = BeanHelper.getBean(controllerClass);
				Param param;
				if (UploadHelper.isMultipart(request)) {
					param = UploadHelper.createParam(request);
				} else {
					param = RequestHelper.createParam(request);
				}
				Object result;
				Method actionMethod = handler.getActionMethod();
				if (param.isEmpty()) {
					result = RefletionUtil.invokeMethod(controolerBean, actionMethod);
				} else {
					result = RefletionUtil.invokeMethod(controolerBean, actionMethod, param);
				}
				if (result instanceof View) {
					handleViewResult((View) result, request, response);
				} else if (result instanceof Data) {
					handleDataResult((Data) result, request, response);
				}
			}
		} finally {
			ServletHelper.destory();
		}

	}

	private void handleViewResult(View view, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String path = view.getPath();
		if (StringUtil.isNotEmpty(path)) {
			if (path.startsWith("/")) {
				response.sendRedirect(request.getContextPath() + path);
			} else {
				Map<String, Object> model = view.getModel();
				for (Map.Entry<String, Object> entry : model.entrySet()) {
					request.setAttribute(entry.getKey(), entry.getValue());
				}
				request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
			}
		}
	}

	private void handleDataResult(Data data, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Object model = data.getModel();
		if (model != null) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			String json = JsonUtil.toJson(model);
			writer.write(json);
			writer.flush();
			writer.close();
		}
	}

}
