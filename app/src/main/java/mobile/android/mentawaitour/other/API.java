package mobile.android.mentawaitour.other;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;


public class API {
    public static String BASE_URL = "http://45.33.110.186/api/";
    public static String TOKEN = "pengenkementawaidong";

    private static AsyncHttpClient getHttpClient() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setMaxRetriesAndTimeout(1, 30);
        return client;
    }

    private static SyncHttpClient getSyncHttpClient() {
        SyncHttpClient client = new SyncHttpClient();
        client.setMaxRetriesAndTimeout(1, 30);
        return client;
    }

    public static void get(String url, RequestParams params, boolean async, AsyncHttpResponseHandler responseHandler) {
        if (async) getSyncHttpClient().get(url, params, responseHandler);
        else getHttpClient().get(url, params, responseHandler);
    }

    public static void post(Context context, String url, JSONObject params, AsyncHttpResponseHandler responseHandler) {
        StringEntity entity = null;
        try {
            entity = new StringEntity(params.toString());
        } catch (UnsupportedEncodingException e) {
            return;
        }
        getHttpClient().post(context, url, entity, "application/json", responseHandler);
    }

    public static String getErrorMessage(int statusCode, JSONObject errorResponse) {
        if (statusCode >= 500) return "Sorry, we had trouble processing your request, Please try again later";
        if (statusCode == 403) return "It seems like your session has expired. Please logout and login again.";
        if (statusCode == 0) return "No Network Connection Available";
        try {
            if (!errorResponse.isNull("error_description")) return errorResponse.getString("error_description");
        } catch (JSONException e) {
            return "Sorry, we had trouble processing your request, Please try again later";
        }

        return null;
    }

    public static RequestParams getBaseParams() {
        RequestParams params = new RequestParams();
        params.put("token", API.TOKEN);

        return params;
    }

}
