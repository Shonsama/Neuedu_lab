package com.neuedu.lab.utils;


import com.alibaba.fastjson.JSONObject;
import com.neuedu.lab.LabApplication;
import com.neuedu.lab.token.Tokenizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ConstantUtils {
    public static Logger logger = LogManager.getLogger(LabApplication.class);

    public static BigDecimal convertToNegtive(BigDecimal source){
        return source.multiply(new BigDecimal(-1));
    }

    public static Integer convertToNegtive(Integer source){
        return Integer.valueOf(source*(-1));
    }


    /**
     * 返回统一格式化
     *
     */
    public static JSONObject responseSuccess(Object data) {
        JSONObject result = new JSONObject();
        result.put("code", ConstantDefinition.SUCCESS_CODE);
        result.put("data", data);
        result.put("msg",ConstantDefinition.SUCCESS_MESSAGE);
        return result;
    }
    public static JSONObject responseSuccess(String msg, Object data) {
        JSONObject result = new JSONObject();
        result.put("code", ConstantDefinition.SUCCESS_CODE);
        result.put("data", data);
        result.put("msg", msg);
        return result;
    }
    public static JSONObject responseSuccess() {
        JSONObject result = new JSONObject();
        result.put("code", ConstantDefinition.SUCCESS_CODE);
        result.put("data", null);
        result.put("msg", ConstantDefinition.SUCCESS_MESSAGE);
        return result;
    }
    public static JSONObject responseFail(Object data) {
        JSONObject result = new JSONObject();
        result.put("code", ConstantDefinition.FAIL_CODE);
        result.put("data", data);
        result.put("msg", ConstantDefinition.FAIL_MESSAGE);
        return result;
    }

    public static JSONObject responseFail(){
        JSONObject result = new JSONObject();
        result.put("code", ConstantDefinition.FAIL_CODE);
        result.put("data", null);
        result.put("msg", ConstantDefinition.FAIL_MESSAGE);
        return result;
    }
    public static JSONObject responseFail(String msg, Object data) {
        JSONObject result = new JSONObject();
        result.put("code", ConstantDefinition.FAIL_CODE);
        result.put("data", data);
        result.put("msg",msg);
        logger.info(msg);
        return result;
    }



    public static String generateToken(String id) {
        //生成 token
        Map<String, Object> payload = new HashMap<>();
        Date date = new Date();
        payload.put("user_account", id);// user ID 植入token
        payload.put("startTime", date.getTime());//生成时间
        payload.put("expiryTime", date.getTime() + ConstantDefinition.EXPIRY_TIME);//过期时间1小时

        return Tokenizer.createToken(payload);
    }

    //获取系统初始时间
    public static Date getSystemInitializeTime() throws ParseException {
        String date = "2019-06-01 00:00:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new Date(String.valueOf(format.parse(date)));
    }
}
