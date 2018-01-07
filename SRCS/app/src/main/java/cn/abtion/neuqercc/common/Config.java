package cn.abtion.neuqercc.common;

/**
 * all of the configuration for this application
 * @author abtion.
 * @since 17/9/22 17:52.
 * email caiheng@hrsoft.net
 */

public class Config {



    /**
     * 列表一页的item的数量
     */
    public static final int size=20;

    /**
     * tab指示器长度
     */
    public static final int TAB_INDICATOR_LENGTH=65;




    public static final String KEY_MAIN_FLAG = "keyMainFlag";

    /**
     * 验证手机号正则
     */
    public static final String MOBILE_REGEX = "[1][3,4,5,7,8][0-9]{9}$";

    /**
     * 验证邮箱正则
     */
    public static final String EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /**
     * APP Server 网络请求BaseUrl
     */
    public static final String APP_SERVER_BASE_URL = "http://www.thmaoqiu.cn/";

    /**
     * APP Server 网络请求连接超时时间，单位：s
     */
    public static final int APP_SERVER_CONNECT_TIME_OUT = 15;

    /**
     * 启动页面到首页启动延时，单位：毫秒
     */
    public static final int SPLASH_GO_MAIN_DELAY = 1500;

    /**
     * MainActivity的page标记
     */
    public static final int FLAG_SUN = 0;
    public static final int FLAG_CLOUD = 1;
    public static final int FLAG_RAIN = 2;
    public static final int FLAG_SNOW = 3;
    public static final int PAGE_LIMIT = 4;

    /**
     * Cloud里面的Tablayout 为两个Title设置字符串
     */
    public static final String HARVEST_CLOUD="反思而有所得";
    public static final String IMPROVEMENT_CLOUD="升华";


    /**
     * Cloud主界面ViewPager跳转switch不同case
     */
    public static final int HARVEST_VIEW_PAGER_CLOUD_ZERO=0;
    public static final int IMPROVEMENT_VIEW_PAGER_CLOUD_ONE=1;


    /**
     * 空字段
     */
    public static final String EMPTY_FIELD="";


    /**
     * 密码长度限制
     */
    public static final int PASSWORD_MIN_LIMIT =6;
    public static final int PASSWORD_MAX_LIMIT =16;




}
