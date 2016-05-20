package com.my.volleycachedemo.volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.my.volleycachedemo.BaseApplication;
import com.my.volleycachedemo.R;

/**
 * Created by Risky on 15/10/21.
 */
public class VolleySingleton {

    private RequestQueue queue;
    private ImageLoader imageLoader;

    private VolleySingleton() {
        queue = getQueue();
        imageLoader = new ImageLoader(queue, new DoubleCache());
    }

    private static final class SingletonHolder {
        private static final VolleySingleton instance = new VolleySingleton();
    }

    public static VolleySingleton getInstance() {
        return SingletonHolder.instance;
    }

    private RequestQueue getQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(BaseApplication.context);
        }
        return queue;
    }

    public static final String TAG = "VolleySingleton";

    public <T> void _addRequest(Request<T> request) {
        request.setTag(TAG);
        queue.add(request);
    }

    public <T> void _addRequest(Request<T> request, Object tag) {
        request.setTag(tag);
        queue.add(request);
    }

    public void _addRequest(String url, Response.Listener<String> listener,
                            Response.ErrorListener error) {
        StringRequest stringRequest = new StringRequest(url, listener, error);
        _addRequest(stringRequest);
    }

    public <T> void _addRequest(String url, Class<T> clazz, Response.Listener<T> success, Response.ErrorListener failed) {
        GsonRequest<T> request = new GsonRequest<T>(url, clazz, success, failed);
        _addRequest(request);
    }

    public void removeRequest(Object tag) {
        queue.cancelAll(tag);
    }

    public ImageLoader _getImageLoader() {
        return imageLoader;
    }

    public static void addRequest(String url, Response.Listener<String> listener,
                                  Response.ErrorListener error) {
        getInstance()._addRequest(url, listener, error);
    }

    public static <T> void addRequest(String url, Class<T> clazz, Response.Listener<T> success, Response.ErrorListener failed) {
        getInstance()._addRequest(url, clazz, success, failed);
    }

    public static ImageLoader getImageLoader(){
        return getInstance()._getImageLoader();
    }

    /**
     * 设置网络图片的方法
     *
     * @param networkImageView  //NetworkImageView
     * @param url               //图片的url
     */
    public static void setNetImage(NetworkImageView networkImageView, String url) {
        networkImageView.setDefaultImageResId(R.mipmap.default_background);
        networkImageView.setErrorImageResId(R.mipmap.ic_launcher);
        networkImageView.setImageUrl(url, getImageLoader());
    }
}
