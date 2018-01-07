package cn.abtion.neuqercc.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.abtion.neuqercc.NEUQerCCApplication;
import cn.abtion.neuqercc.common.Config;
import cn.abtion.neuqercc.common.constants.CacheKey;
import cn.abtion.neuqercc.network.converter.ResponseConverterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * @author abtion.
 * @since 17/9/22 18:07.
 * email caiheng@hrsoft.net
 */

public final class RestClient {
    private static Retrofit retrofit;
    private static APIService service;
    private static OkHttpClient okHttpClient;

    /**
     * API 请求生成器
     *
     * @return APIService
     */
    public static APIService getService() {
        if (service == null) {
            service = getRetrofit().create(APIService.class);
        }
        return service;
    }

    /**
     * 构造Retrofit
     *
     * @return retrofit
     */
    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.APP_SERVER_BASE_URL)
                    .addConverterFactory(ResponseConverterFactory.create())
                    .client(getClient())
                    .build();
        }
        return retrofit;
    }

    /**
     * 构造OKHttp客户端，相关参数设置
     *
     * @return OKHttp客户端
     */
    private static OkHttpClient getClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            String token = (NEUQerCCApplication.getInstance().getCacheUtil().getString(CacheKey.TOKEN)
                                    == null ? "token" : NEUQerCCApplication.getInstance().getCacheUtil().getString(CacheKey
                                    .TOKEN));
                            Request request = chain.request().newBuilder()
                                    .header("Token", token)
                                    .build();
                            return chain.proceed(request);
                        }
                    })
                    .connectTimeout(Config.APP_SERVER_CONNECT_TIME_OUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
        }
        return okHttpClient;
    }
}

