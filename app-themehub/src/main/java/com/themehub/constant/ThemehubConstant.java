package com.themehub.constant;

public final class ThemehubConstant {


    // =============================================================================================
    // CODICI DI ERRORE CLIENT E SERVER
    // =============================================================================================

    // CLIENT ERRORS
    public static final String BAD_REQUEST = "400";
    public static final String UNAUTHORIZED = "401";
    public static final String FORBIDDEN = "403";
    public static final String NOT_FOUND = "404";
    public static final String METHOD_NOT_ALLOWED = "405";
    public static final String NOT_ACCEPTABLE = "406";
    public static final String CONFLICT = "409";
    public static final String UNPROCESSABLE_ENTITY = "422";
    public static final String EXPECTATION_FAILED = "417";
    public static final Float NR_VUELTO_DEFAULT = (float) 0;

    // SERVER ERRORS
    public static final String INTERNAL_SERVER_ERROR = "500";
    public static final String NOT_IMPLEMENTED = "501";
    public static final String BAD_GATEWAY = "503";
    public static final String SERVICE_UNAVAILABLE = "504";
    public static final String GATEWAY_TIMEOUT = "505";
    public static final String NOT_VALIDATED = "506";

    // ERRORS
    public static final String PREFIX_SERVER_ERROR = "SRV";
    public static final String PREFIX_CLIENT_ERROR = "CLI";

    // STATE
    public static final String STATE_ACTIVE = "1";
    public static final String STATE_INACTIVE = "0";

    //API VERSION
    public final static String API_VERSION = "/v1";

    //THEMEHUB SYSTEM PATH
    public static final String RESOURCE_GENERIC = API_VERSION + "/app-themehub";
    public static final String RESOURCE_THEMES = "/themes";
    public static final String RESOURCE_THEMES_THEME = "/theme";
    public static final String RESOURCE_FINDBYNAME = "/findByName";
    public static final String RESOURCE_GENERIC_ID = "/{id}";
    public static final String RESOURCE_GENERIC_USER = "/{user}";
    public static final String RESOURCE_GENERIC_START_DATE = "/{startDate}";
    public static final String RESOURCE_GENERIC_END_DATE= "/{endDate}";


    public static final String RESOURCE_USERS = "/users";
    public static final String RESOURCE_USERS_USER = "/user";

    public static final String RESOURCE_CATEGORIES = "/categories";
    public static final String RESOURCE_CATEGORIES_CATEGORY = "/category";

    public static final String CLIENT_FRONTEND ="*";

    public static final String TAB_NAME_USER = "t_user" ;
    public static final String TAB_NAME_TAG = "t_tag" ;

    public static final String SEQ_NAME_USER = "dbo";

    public static final String RESOURCE_TAGS = "/tags";
    public static final String RESOURCE_TAGS_TAG= "/tag";

    public static final String RESOURCE_REVIEWS = "/reviews";
    public static final String RESOURCE_REVIEWS_REVIEW = "/review";

    public static final String RESOURCE_PURCHASES = "/purchases";
    public static final String RESOURCE_PURCHASES_PURCHASE = "/purchase";

    public static final String RESOURCE_CARTS = "/carts";
    public static final String RESOURCE_CARTS_CART = "/cart";

    // ==========================================================
    // QUERY
    // ==========================================================



    public static final String SP_SEARCH_USER = "select * from dbo.fn_search_user(?1,?2)";
    public static final String SP_SEARCH_USER_COUNT = "select count(*) from dbo.fn_search_user(?1,?2)";//due parametri

    public static final String SP_SAVE_USER = "select * from dbo.fn_save_user(?1,?2,?3,?4,?5,?6,?7)";
    public static final String SP_SAVE_USER_COUNT = "select * from dbo.fn_save_user(?1,?2,?3,?4,?5,?6,?7)"; // sette parametri




}


