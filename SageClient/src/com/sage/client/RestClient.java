package com.sage.client;

import java.io.InputStream;

import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

public class RestClient
{
	public static <T> T message(RestRequest request, Class<T> classType)
	{
		RestCommonClient restCommonClient = new RestCommonClient();
		String url = generateUrlByRequest(request);
		String result = restCommonClient.GetStringFromNetwork(url);
		if (classType == String.class) return (T) result;
		T jsonObject = new Gson().fromJson(result, classType);
		return jsonObject;
	}

	private static String generateUrlByRequest(RestRequest request)
	{
		if (request.GetParams.size() == 0) return request.Url;
		request.Url += "?";
		for (BasicNameValuePair getParam : request.GetParams)
			request.Url += getParam.getName() + "=" + getParam.getValue() + "&";
		return request.Url;
	}

	public static InputStream download(RestRequest request)
	{
		RestCommonClient restCommonClient = new RestCommonClient();
		String url = generateUrlByRequest(request);
		InputStream result = restCommonClient.GetStreamFromNetwork(url);
		return result;
	}
}
