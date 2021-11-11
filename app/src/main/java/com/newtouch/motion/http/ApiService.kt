package com.newtouch.motion.http

import com.newtouch.motion.entity.*
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {
    /**
     * 获取首页文章数据
     */
    @GET("/article/list/{page}/json")
    fun getHomeList(@Path("page") pageNo: Int): Observable<BaseResponse<ArticleEntity>>

    /**
     * 获取首页置顶文章数据
     */
    @GET("/article/top/json")
    fun getTopList(): Observable<BaseResponse<MutableList<ArticleEntity.DatasBean>>>

    /**
     * banner
     */
    @GET("/banner/json")
    fun getBanner(): Observable<BaseResponse<MutableList<BannerEntity>>>

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/user/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<BaseResponse<UserEntity>>

    @GET("/user/logout/json")
    fun logout(): Observable<BaseResponse<Any>>

    /**
     * 获取收藏文章数据
     */
    @GET("/lg/collect/list/{page}/json")
    fun getCollectData(@Path("page") pageNo: Int):
            Observable<BaseResponse<CollectEntity>>

    /**
     * 获取个人积分
     */
    @GET("/lg/coin/userinfo/json")
    fun getIntegral(): Observable<BaseResponse<IntegralEntity>>

    /**
     * 收藏
     */
    @POST("/lg/collect/{id}/json")
    fun collect(@Path("id") id: Int): Observable<BaseResponse<Any>>

    /**
     * 取消收藏
     */
    @POST("/lg/uncollect_originId/{id}/json")
    fun unCollect(@Path("id") id: Int): Observable<BaseResponse<Any>>

    /**
     * 获取项目tab
     */
    @GET("/project/tree/json")
    fun getProjectTabList(): Observable<BaseResponse<MutableList<TabEntity>>>

    /**
     * 获取项目tab
     */
    @GET("/wxarticle/chapters/json  ")
    fun getAccountTabList(): Observable<BaseResponse<MutableList<TabEntity>>>

    /**
     * 获取项目列表
     */
    @GET("/project/list/{pageNum}/json")
    fun getProjectList(@Path("pageNum") pageNum: Int, @Query("cid") cid: Int)
            : Observable<BaseResponse<ArticleEntity>>

    /**
     * 获取公众号列表
     */
    @GET("/wxarticle/list/{id}/{pageNum}/json")
    fun getAccountList(@Path("id") cid: Int, @Path("pageNum") pageNum: Int)
            : Observable<BaseResponse<ArticleEntity>>


    /**
     * 获取项目tab
     */
    @POST("/article/query/{pageNum}/json")
    fun search(@Path("pageNum") pageNum: Int, @Query("k") k: String)
            : Observable<BaseResponse<ArticleEntity>>

    /**
     * 体系
     */
    @GET("/tree/json")
    fun getSystemList(): Observable<BaseResponse<MutableList<SystemListEntity>>>


    /**
     * 获取项目tab
     */
    @GET("/article/list/{pageNum}/json")
    fun getSystemArticle(@Path("pageNum") pageNum: Int, @Query("cid") cid: Int)
            : Observable<BaseResponse<ArticleEntity>>

    /**
     * 导航
     */
    @GET("/navi/json")
    fun getNavigation(): Observable<BaseResponse<MutableList<NavigationEntity>>>

    /**
     * 排名
     */
    @GET("/coin/rank/{pageNum}/json")
    fun getRank(@Path("pageNum") pageNum: Int): Observable<BaseResponse<RankEntity>>

    /**
     * 积分记录
     */
    @GET("/lg/coin/list/{pageNum}/json")
    fun getIntegralRecord(@Path("pageNum") pageNum: Int): Observable<BaseResponse<IntegralRecordEntity>>

    /**
     * 我分享的文章
     */
    @GET("/user/lg/private_articles/{pageNum}/json")
    fun getMyArticle(@Path("pageNum") pageNum: Int): Observable<BaseResponse<MyArticleEntity>>

    /**
     * 我分享的文章
     */
    @POST("/lg/user_article/delete/{id}/json")
    fun deleteMyArticle(@Path("id") id: Int): Observable<BaseResponse<Any>>

    /**
     * 分享文章
     */
    @POST("/lg/user_article/add/json")
    fun shareArticle(
        @Query("title") title: String,
        @Query("link") link: String
    ): Observable<BaseResponse<Any>>

    /**
     * 注册
     */
    @POST("/user/register")
    fun register(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("repassword") repassword: String
    ): Observable<BaseResponse<Any>>

    /**
     * 获取验证码接口
     */
    @GET("/api/authorization/verification/code")
    fun getCode(@Query("mobile") mobile: String): Observable<BaseResponse<VerifiCodeEntity>>

    /**
     * 验证码登录
     */
    @POST("/api/authorization/oauth/token")
    fun toLogin(
        @Header("Authorization") authorization: String = "Basic dG50bGlua2luZzp0bnRsaW5raW5nMTIzKio=",
        @Query("grant_type") grant_type: String = "sms_code",
        @Query("scope") scope: String = "all",
        @Query("smsCode") smsCode: String,
        @Query("mobile") mobile: String,
        @Query("device") device: String = "Android"
    ): Observable<ToLoginEntity>


//    http://192.168.0.224:8081/api/gateway/motion-community/github/repository/category 开源分类


    /**
     * 码力语言列表
     */
    @GET("https://ttchain.tntlinking.com/api/codemart/sys/dict/group/lang?group=lang")
    fun getCodeLang(
        @Header("Authorization") authorization: String = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJjcnQiOiIxNjM0ODg2OTI1NDY0IiwidXNlcl9pZCI6ImE3NTdiZDU5LWRhMTUtNDM2OS1hNzViLWRlNDI4NWJhZGQ4YyIsInNjb3BlIjpbImFsbCJdLCJtb2JpbGUiOiIxNTUyNzg2NDE2MiIsImV4cCI6MTYzNDkxNTcyNSwiZGV2aWNlIjoiUEMiLCJqdGkiOiJhN2JkNjRmMi02ZWQ2LTRkZDAtYmJjMS0xMGYzMDFkOGZmYjAiLCJjbGllbnRfaWQiOiJ0bnRsaW5raW5nIn0.c3GQ-Wd5C1XYaMds-sUlX68lqZGE5LzpuAR-CFECuaQNQpumx0EKROsqxQVxFksM2NvWy5ir2iGp1iWWp_cgBozc0t5W2qP3A9oUfrEme30zg3_UNxwDlLJl_i76D-SX7Jlg-IItjlvjefuFTycjO-Ft3pRglBVDVldjs7crDo4KQs12Tm4SZ8bT4X-2MBZhHex3pSB4EPw6lhgjbS4LxGUkYjH9DQSLSTdEZB9LvhDaP9gkUOhyn9NR13oq6IUOmopPfGYzt6kAL32-jwaMyi5OH0aiwmcbvbNix3Sxcv5LXOK_sT41pNgDw7EX5o_ettjusWlM4vM0-lJ4R0wQWQ"
    ): Observable<BaseResponse<MutableList<DictGroupEntity>>>

    /**
     * 码力技术列表
     */
    @GET("https://ttchain.tntlinking.com/api/codemart/label_user_customize/all")
    fun getCodemart(
        @Header("Authorization") authorization: String = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJjcnQiOiIxNjM0ODg2OTI1NDY0IiwidXNlcl9pZCI6ImE3NTdiZDU5LWRhMTUtNDM2OS1hNzViLWRlNDI4NWJhZGQ4YyIsInNjb3BlIjpbImFsbCJdLCJtb2JpbGUiOiIxNTUyNzg2NDE2MiIsImV4cCI6MTYzNDkxNTcyNSwiZGV2aWNlIjoiUEMiLCJqdGkiOiJhN2JkNjRmMi02ZWQ2LTRkZDAtYmJjMS0xMGYzMDFkOGZmYjAiLCJjbGllbnRfaWQiOiJ0bnRsaW5raW5nIn0.c3GQ-Wd5C1XYaMds-sUlX68lqZGE5LzpuAR-CFECuaQNQpumx0EKROsqxQVxFksM2NvWy5ir2iGp1iWWp_cgBozc0t5W2qP3A9oUfrEme30zg3_UNxwDlLJl_i76D-SX7Jlg-IItjlvjefuFTycjO-Ft3pRglBVDVldjs7crDo4KQs12Tm4SZ8bT4X-2MBZhHex3pSB4EPw6lhgjbS4LxGUkYjH9DQSLSTdEZB9LvhDaP9gkUOhyn9NR13oq6IUOmopPfGYzt6kAL32-jwaMyi5OH0aiwmcbvbNix3Sxcv5LXOK_sT41pNgDw7EX5o_ettjusWlM4vM0-lJ4R0wQWQ"
    ): Observable<BaseResponse<MutableList<LabelCustomEntity>>>

    /**
     * 码力商品列表
     */
    @GET("https://ttchain.tntlinking.com/api/codemart/product/page?labelIds=2c9780827bf34b0e017c15c07235017e&lang=&price=&page=0&size=10&sort=")
    fun getCodeGoodsList(
        @Header("Authorization") authorization: String = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJjcnQiOiIxNjM0ODg2OTI1NDY0IiwidXNlcl9pZCI6ImE3NTdiZDU5LWRhMTUtNDM2OS1hNzViLWRlNDI4NWJhZGQ4YyIsInNjb3BlIjpbImFsbCJdLCJtb2JpbGUiOiIxNTUyNzg2NDE2MiIsImV4cCI6MTYzNDkxNTcyNSwiZGV2aWNlIjoiUEMiLCJqdGkiOiJhN2JkNjRmMi02ZWQ2LTRkZDAtYmJjMS0xMGYzMDFkOGZmYjAiLCJjbGllbnRfaWQiOiJ0bnRsaW5raW5nIn0.c3GQ-Wd5C1XYaMds-sUlX68lqZGE5LzpuAR-CFECuaQNQpumx0EKROsqxQVxFksM2NvWy5ir2iGp1iWWp_cgBozc0t5W2qP3A9oUfrEme30zg3_UNxwDlLJl_i76D-SX7Jlg-IItjlvjefuFTycjO-Ft3pRglBVDVldjs7crDo4KQs12Tm4SZ8bT4X-2MBZhHex3pSB4EPw6lhgjbS4LxGUkYjH9DQSLSTdEZB9LvhDaP9gkUOhyn9NR13oq6IUOmopPfGYzt6kAL32-jwaMyi5OH0aiwmcbvbNix3Sxcv5LXOK_sT41pNgDw7EX5o_ettjusWlM4vM0-lJ4R0wQWQ"
    ): Observable<BaseResponse<CodeEnergyEntity>>


    /**
     * 获取开源热门数据
     */
    @GET("http://192.168.0.224:8081/api/gateway/motion-community/github/repository/category")
    fun getCategoryList(): Observable<BaseResponse<MutableList<OpenCodeCategoryEntity>>>

    /**
     * 获取开源热门数据
     */
    @GET("http://192.168.0.224:8081/api/gateway/motion-community/github/repository/hot?pageNum=1&pageSize=10&categoryId=2")
    fun getHotCodeList(
        @Query("categoryId") categoryId: String,
        @Query("pageNum") pageNum: String,
        @Query("pageSize") pageSize: String = "10"
    ): Observable<BaseResponse<HotCodeListEntity>>

    /**
     * 获取开源新星数据
     */
    @GET("  http://192.168.0.224:8081/api/gateway/motion-community/github/repository/newstar?pageNum=1&pageSize=10&categoryId=2")
    fun getNewStarList(
        @Query("categoryId") categoryId: String,
        @Query("pageNum") pageNum: String,
        @Query("pageSize") pageSize: String = "10"
    ): Observable<BaseResponse<HotCodeListEntity>>
}