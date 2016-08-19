package com.nateiot.base.common;

import java.util.Map;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;

public class TemplateUtil {

	private static FreeMarkerConfigurer freeMarkerConfigurer;
	
	public TemplateUtil(FreeMarkerConfigurer freeMarkerConfigurer) {
		TemplateUtil.freeMarkerConfigurer = freeMarkerConfigurer;
	}

	public static String getHtmlText(String template, Map<String, Object> model)
			throws Exception {
		Template tpl = TemplateUtil.freeMarkerConfigurer.getConfiguration().getTemplate(template);
		return FreeMarkerTemplateUtils.processTemplateIntoString(tpl, model);
	}

}
