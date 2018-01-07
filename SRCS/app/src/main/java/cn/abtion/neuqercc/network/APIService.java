package cn.abtion.neuqercc.network;

import java.util.List;

import cn.abtion.neuqercc.account.models.LoginRequest;
import cn.abtion.neuqercc.rain.models.InitAllTeamDateResponse;
import cn.abtion.neuqercc.rain.models.InitAllTeamResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * retrofit service interface.
 * @author abtion.
 * @since 17/9/22 18:04.
 * email caiheng@hrsoft.net
 */

public interface APIService {
    /**
     * 登录
     */
    @POST("saiyou/public/index.php/login")
    Call<APIResponse> login(@Body LoginRequest loginRequest);

//    void initAllteam();

//    /**
//     * 注册
//     */
//    @POST("saiyou/public/index.php/register")
//    Call<APIResponse> register(@Body RegisterR)


    /**
     * 查询多支队伍GET请求
     */
    @GET("saiyou/public/index.php/teams/show")
    Call<APIResponse<InitAllTeamDateResponse<List<InitAllTeamResponse>>>> initAllTeam(@Query("page")int page, @Query("size")int teamSize);

}
