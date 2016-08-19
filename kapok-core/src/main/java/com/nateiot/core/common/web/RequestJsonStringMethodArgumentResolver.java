package com.nateiot.core.common.web;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;

/**
 * @author Will WM. Zhang
 *
 */
public class RequestJsonStringMethodArgumentResolver extends
		RequestParamMethodArgumentResolver {

	private ObjectMapper mapper = new ObjectMapper();

	public RequestJsonStringMethodArgumentResolver() {
		super(null, true);
	}

	public boolean supportsParameter(MethodParameter parameter) {
		return true;
//		return super.supportsParameter(parameter);
		// if (parameter.hasParameterAnnotation(RequestJsonParam.class)) {
		// return true;
		// }
		// return false;
	}

//	@Override
//	protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
//		RequestJsonParam annotation = parameter
//				.getParameterAnnotation(RequestJsonParam.class);
//		return new RequestJsonParamNamedValueInfo(annotation);
//
//	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Object resolveName(String name, MethodParameter parameter,
			NativeWebRequest webRequest) throws Exception {

		if (!"JsonString".equals(webRequest.getParameter("paramType"))) {
			return super.resolveName(name, parameter, webRequest);
		}

		String[] paramValues = webRequest.getParameterValues(name);
		Class<?> paramType = parameter.getParameterType();
		if (paramValues == null) {
			return null;
		}

		try {
			if (paramValues.length == 1) {
				String text = paramValues[0];
				Type type = parameter.getGenericParameterType();

				if (MapWapper.class.isAssignableFrom(paramType)) {
					MapWapper<?, ?> jsonMap = (MapWapper<?, ?>) paramType
							.newInstance();

					MapType mapType = (MapType) getJavaType(HashMap.class);

					if (type instanceof ParameterizedType) {
						mapType = (MapType) mapType
								.narrowKey((Class<?>) ((ParameterizedType) type)
										.getActualTypeArguments()[0]);
						mapType = (MapType) mapType
								.narrowContentsBy((Class<?>) ((ParameterizedType) type)
										.getActualTypeArguments()[1]);
					}
					jsonMap.setInnerMap(mapper.<Map> readValue(text, mapType));
					return jsonMap;
				}

				JavaType javaType = getJavaType(paramType);

				if (Collection.class.isAssignableFrom(paramType)) {
					javaType = javaType
							.narrowContentsBy((Class<?>) ((ParameterizedType) type)
									.getActualTypeArguments()[0]);
				}

				return mapper.readValue(paramValues[0], javaType);
			}

		} catch (Exception e) {
			throw new JsonMappingException(
					"Could not read request json parameter", e);
		}

		throw new UnsupportedOperationException(
				"too many request json parameter '" + name
						+ "' for method parameter type [" + paramType
						+ "], only support one json parameter");
	}

	@SuppressWarnings("deprecation")
	protected JavaType getJavaType(Class<?> clazz) {
		return TypeFactory.type(clazz);
	}

//	@Override
//	protected void handleMissingValue(String paramName,
//			MethodParameter parameter) throws ServletException {
//		String paramType = parameter.getParameterType().getName();
//		throw new ServletRequestBindingException(
//				"Missing request json parameter '" + paramName
//						+ "' for method parameter type [" + paramType + "]");
//	}

//	private class RequestJsonParamNamedValueInfo extends NamedValueInfo {
//
//		private RequestJsonParamNamedValueInfo() {
//			super("", false, null);
//		}
//
//		private RequestJsonParamNamedValueInfo(RequestJsonParam annotation) {
//			super(annotation.value(), annotation.required(), null);
//		}
//	}

}