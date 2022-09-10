package com.kleistit.farmapi.config;


import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.MutableHeaders;

public class
CORSHandler implements Handler {

	public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
	public static final String ACCESS_CONTROL_ALLOW_HEADERS_VALUE = "x-requested-with, Origin, content-type, accept, token, X-Custom-Header, username, password, email, streetname, postalCode";

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
