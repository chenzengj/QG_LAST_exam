package com.example.utils;


import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class JsonUtils {
    //private static final Gson gson = new Gson();
    //private static ObjectMapper objectMapper = new ObjectMapper();
    public static void writeJsonResponse(HttpServletResponse response, Object data) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(JSON.toJSONString(data));//将data对象转化为json格式的字符串，并输出到servlet的响应流中
        out.flush();//刷新输出流
        out.close();
    }

    public static Map<String, Object> createJsonResponse(boolean success, String message, Object data) {
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("success", success);
        jsonResponse.put("message", message);
        jsonResponse.put("data", data);
        return jsonResponse;
    }
}
