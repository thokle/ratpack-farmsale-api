package com.kleistit.farmapi.config;


import ratpack.core.handling.Context;
import ratpack.core.handling.Handler;
import ratpack.core.http.MutableHeaders;

public class
CORSHandler implements Handler {

	public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
	public static final String ACCESS_CONTROL_ALLOW_HEADERS_VALUE = "x-requested-with, origin, content-type, accept, token, X-Custom-Header";

	public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
	public static final String ACCESS_CONTROL_ALLOW_ORIGIN_VALUE = "*";


	public static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
	public static final String ACCESS_CONTROL_ALLOW_METHODS_VALUE = "GET, POST, DELETE, OPTIONS";

	@Override
	public void handle(Context ctx) throws Exception {
		MutableHeaders headers = ctx.getResponse().getHeaders();
		headers.set(ACCESS_CONTROL_ALLOW_ORIGIN, ACCESS_CONTROL_ALLOW_ORIGIN_VALUE);
		headers.set(ACCESS_CONTROL_ALLOW_HEADERS, ACCESS_CONTROL_ALLOW_HEADERS_VALUE);
		headers.set(ACCESS_CONTROL_ALLOW_METHODS, ACCESS_CONTROL_ALLOW_METHODS_VALUE);
		ctx.next();
	}
	
}
