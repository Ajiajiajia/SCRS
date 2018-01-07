package cn.abtion.neuqercc.network;

import java.util.List;

import java.util.List;
import java.util.Map;

import cn.abtion.neuqercc.account.models.LoginRequest;
import cn.abtion.neuqercc.account.models.UpdatePasswordRequest;
import cn.abtion.neuqercc.account.models.RegisterRequest;
import cn.abtion.neuqercc.account.models.SmsRequest;
import cn.abtion.neuqercc.home.activities.CompetitionActivity;
import cn.abtion.neuqercc.home.fragments.HomeFragment;
import cn.abtion.neuqercc.home.models.InitContestRecylerViewDataRequest;
import cn.abtion.neuqercc.home.models.InitContestRecylerViewItemRequest;
import cn.abtion.neuqercc.home.models.InitCrouselFigureRequest;
import cn.abtion.neuqercc.home.models.RaidersAndDetailsRequest;
import cn.abtion.neuqercc.team.models.EstablishTeamRequest;
import cn.abtion.neuqercc.team.models.InitAllTeamDataResponse;
import cn.abtion.neuqercc.team.models.InitAllTeamResponse;
import cn.abtion.neuqercc.team.models.InitRecommendTeamDataResponse;
import cn.abtion.neuqercc.team.models.InitRecommendTeamResponse;
import cn.abtion.neuqercc.mine.models.FeedBackRequest;
import cn.abtion.neuqercc.mine.models.GoodAtRequest;
import cn.abtion.neuqercc.mine.models.MineTeamInformationRequest;
import cn.abtion.neuqercc.mine.models.MineTeamListResponse;
import cn.abtion.neuqercc.mine.models.PersonInformationResponse;
import cn.abtion.neuqercc.mine.models.ShowHonorResponse;
import cn.abtion.neuqercc.mine.models.StudentGradeRequest;
import cn.abtion.neuqercc.mine.models.TeamMemberResponse;
import cn.abtion.neuqercc.mine.models.UpdateTeamInformationRequest;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * retrofit service interface.
 *
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

    /**
     * 验证码
     */

    @POST("saiyou/public/index.php/sms")
    Call<APIResponse> captch(@Body SmsRequest smsRequest);

    /**
     * 注册
     */
    @POST("saiyou/public/index.php/register")
    Call<APIResponse> register(@Body RegisterRequest registerRequest);

    /**
     * 重新设置密码
     */
    @POST("saiyou/public/index.php/forgot")
    Call<APIResponse> updatePassword(@Body UpdatePasswordRequest updatePasswordRequest);

    /**
     * 轮播图GET请求
     */
    @GET("saiyou/public/index.php/carousel/show")
    Call<APIResponse<List<InitCrouselFigureRequest>>> initCrouselFigure();


    /**
     * 比赛详情列表GET请求
     */
    @GET("saiyou/public/index.php/descs/show")
    Call<APIResponse<InitContestRecylerViewDataRequest<List<InitContestRecylerViewItemRequest>>>> initContestRecylerView(@Query("page")int page,@Query("size")int size);


    /**
     * 大神攻略和比赛详情内容GET
     */
    @GET("saiyou/public/index.php/raider/show" )
    Call<APIResponse<RaidersAndDetailsRequest>> getRaidersAndDetails(@Query("id")int contestItemId);

    /**
     * 搜索队伍POST请求
     */
    @POST("saiyou/public/index.php/team/search" )
    Call<APIResponse<List<InitAllTeamResponse>>> searchTeam(@Query("content")String seachTeamName);

    /**
     * 查询多支队伍GET请求
     */
    @GET("saiyou/public/index.php/teams/show" )
    Call<APIResponse<InitAllTeamDataResponse<List<InitAllTeamResponse>>>> initAllTeam(@Query("page")int page, @Query("size") int teamSize);

    /**
     * 查询推荐队伍队伍GET请求
     */
    @GET("saiyou/public/index.php/team/recommend" )
    Call<APIResponse<InitRecommendTeamDataResponse<List<InitRecommendTeamResponse>>>> initRecommendTeam(@Query("phone")String contestName,@Query("page")int page, @Query("size") int teamSize);


    /**
     * 创建队伍POST请求
     */
    @POST("saiyou/public/index.php/team/add")
    Call<APIResponse> establishTeam(@Body EstablishTeamRequest establishTeamRequest);



    /**
     * 搜索比赛POST请求
     */
    @POST("saiyou/public/index.php/desc/search" )
    Call<APIResponse<List<InitContestRecylerViewItemRequest>>> searchContest(@Query("content")String seachContestName);


    /**
     * 获取个人资料
     */
    @GET("saiyou/public/index.php/user/show")
    Call<APIResponse<PersonInformationResponse>> personalInformation(@Query("phone") String phone);


    /**
     * 提交修改资料
     *
     * @return
     */
    @Multipart
    @POST("saiyou/public/index.php/user/edit")
    Call<APIResponse> uploadPersonInformation(@QueryMap Map<String, Object> map, @Part MultipartBody.Part pic);


    /**
     * 获取荣誉证书
     *
     * @param phone
     * @return
     */

    @GET("saiyou/public/index.php/user/glory_show")
    Call<APIResponse<List<ShowHonorResponse>>> showHonorRequest(@Query("phone") String phone);


    /**
     * 添加证书
     *
     * @return
     */
    @Multipart
    @POST("saiyou/public/index.php/user/glory_add")
    Call<APIResponse> addHonor(@Query("phone") String phone, @QueryMap Map<String, Object> map, @Part MultipartBody.Part glory_pic);


    /**
     * 编辑证书
     *
     * @return
     */
    @Multipart
    @POST("saiyou/public/index.php/user/glory_edit")
    Call<APIResponse> uploadHonor(@Query("phone") String phone, @QueryMap Map<String, Object> map, @Part MultipartBody.Part glory_pic);

    /**
     * 删除荣誉墙
     *
     * @param phone
     * @param order
     * @return
     */
    @DELETE("saiyou/public/index.php/user/glory_del")
    Call<APIResponse> deleteHonor(@Query("phone") String phone, @Query("order") int order);


    /**
     * 提交意见反馈
     *
     * @return
     */
    @POST("saiyou/public/index.php/feedback")
    Call<APIResponse> feedBack(@Body FeedBackRequest feedBackRequest);


    /**
     * 我的队伍列表
     *
     * @return
     */
    @GET("saiyou/public/index.php/user/team_list")
    Call<APIResponse<List<MineTeamListResponse>>> mineTeamList(@Query("phone") String phone);

    /**
     * 我的队伍信息
     */
    @GET("saiyou/public/index.php/team/show")
    Call<APIResponse<MineTeamInformationRequest>> mineTeamInformation(@Query("team_id") int team_id);

    /**
     * 我的队伍成员
     *
     * @return
     */
    @GET("saiyou/public/index.php/team/member_show")
    Call<APIResponse<List<TeamMemberResponse>>> mineTeamMember(@Query("team_id") int team_id);


    /**
     * 修改我的队伍信息
     *
     * @return
     */
    @POST("saiyou/public/index.php/team/edit")
    Call<APIResponse> updateTeamInformation(@Body UpdateTeamInformationRequest updateTeamInformationRequest);


    /**
     * 获取默认年级选项
     *
     * @return
     */
    @GET("saiyou/public/index.php/other/year_show")
    Call<APIResponse<List<StudentGradeRequest>>> studentGradeRequest();


    /**
     * 获取默认擅长领域
     *
     * @return
     */
    @GET("saiyou/public/index.php/other/field_show")
    Call<APIResponse<List<GoodAtRequest>>> goodAtRequest();


}
