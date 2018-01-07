package cn.abtion.neuqercc.common;

/**
 * all of the configuration for this application
 * @author abtion.
 * @since 17/9/22 17:52.
 * email caiheng@hrsoft.net
 */

public class Config {
    /**
     * 验证手机号正则
     */
    public static final String MOBILE_REGEX = "[1][3,4,5,7,8][0-9]{9}$";

    /**
     * 验证邮箱正则
     */
    public static final String EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /**
     * 空字段
     */
    public static final String EMPTY_FIELD = "";

    /**
     * APP Server 网络请求BaseUrl
     */
    /**
     * public static final String APP_SERVER_BASE_URL = "http://oj.marklux.cn/";
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
    public static final int FLAG_HOME = 0;
    public static final int FLAG_TEAM = 1;
    public static final int FLAG_MESSAGE = 2;
    public static final int FLAG_MINE = 3;
    public static final int PAGE_LIMIT = 4;

    /**
     * 赛事页面page标记
     */

    public static final int FLAG_DETAILS = 0;
    public static final int FLAG_RAIDERS = 1;
    public static final int PAGE_COMPETITION_LIMIT = 2;

    public static final String TAB_TITLE_DETAILS="赛事详情";
    public static final String TAB_TITLE_RAIDERS="大神攻略";


    /**
     * 密码长度限制
     */
    public static final int PASSWORD_MIN_LIMIT =6;
    public static final int PASSWORD_MAX_LIMIT =16;


    public static final String KEY_MAIN_FLAG = "keyMainFlag";


    public static final int FLING_MIN_DISTANCE=100;
    public static final int FLING_MIN_VELOCITY=10;

    /**
     * 倒计时及每次回调时间
     */

    public static final long COUNT_DOWN_TIME_TOTAL=60000;
    public static final long COUNT_DOWN_TIME_PER=1000;

    /**
     * team主界面ViewPagerAdapter设置字符串
     */
    public static final String ALL_TEAM ="全部队伍" ;
    public static final String RECOMMAND_TEAM ="推荐队伍" ;

    /**
     * team主界面ViewPager跳转switch不同case
     */
    public static final int TEAM_VIEW_PAGER_ITEME_ZERO=0;
    public static final int TEAM_VIEW_PAGER_ITEME_ONE=1;

    /**
     * tab指示器长度
     */
    public static final int TAB_INDICATOR_LENGTH=50;
    /**
     * viewflipper的downX变量初值
     */
    public static final int VIEW_FLIPPER_INITAIL_VALUE=0;
    /**
     * tab指示器长度
     */
    public static final int TAB_INDICATOR_WIDTH=50;




    /**
     * 轮播图
     */

    public static final int FLIPPER_TIME_INTERVAL=5000;
    public static final int FLIPPER_ACTION_SPEED=200;


    /**
     * 颜色
     */
    public static final int COLOR_MIN=0;
    public static final int COLOR_MAX=255;
    public static final int TITLE_BG_R=255;
    public static final int TITLE_BG_G=108;
    public static final int TITLE_BG_B=97;

    /**
     * 列表一页的item的数量
     */
    public static final int size=20;




    /**
     * intent信息
     */
    public static final String INTENT_EXTRA_HONOR = "honor";
    public static final String INTENT_EXTRA_PERSON_INFORMATION = "personInformation";



    public static final int DIALOG_PADDING_RIGHT_DP = 73;
    public static final int DIALOG_PADDING_BOTTOM_DP = 25;
    public static final int LP_WIDTH = 170;
    public static final int LP_HEIGHT = 300;



    /**
     * 性别标志
     */
    public static final int FLAG_BOY = 0;
    public static final int FLAG_GIRL = 1;




}
