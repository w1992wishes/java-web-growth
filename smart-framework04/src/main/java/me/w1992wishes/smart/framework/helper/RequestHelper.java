package me.w1992wishes.smart.framework.helper;

import me.w1992wishes.smart.framework.bean.FormParam;
import me.w1992wishes.smart.framework.bean.Param;
import me.w1992wishes.smart.framework.util.ArrayUtil;
import me.w1992wishes.smart.framework.util.CodecUtil;
import me.w1992wishes.smart.framework.util.StreamUtil;
import me.w1992wishes.smart.framework.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 请求助手类
 *
 * Created by w1992wishes
 * on 2018/1/4.
 */
public final class RequestHelper {

    /**
     * 创建请求对象
     *
     * @param request
     * @return
     */
    public static Param createParam(HttpServletRequest request) throws IOException {
        List<FormParam> formParamList = new ArrayList<>();
        formParamList.addAll(parseParameterNames(request));
        formParamList.addAll(parseInputStream(request));
        return new Param(formParamList);
    }

    private static List<FormParam> parseParameterNames(HttpServletRequest request) throws IOException{
        List<FormParam> formParamList = new ArrayList<>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()){
            String fieldName = paramNames.nextElement();
            String[] fieldValues = request.getParameterValues(fieldName);
            if (ArrayUtil.isNotEmpty(fieldValues)){
                Object fieldValue;
                if (fieldValues.length == 1){
                    fieldValue = fieldValues[0];
                } else {
                    StringBuilder sb = new StringBuilder("");
                    for (int i=0; i< fieldValues.length; i++){
                        sb.append(fieldValues[i]);
                        if (i != fieldValues.length - 1){
                            sb.append(StringUtil.SEPARATOR);
                        }
                    }
                    fieldValue = sb.toString();
                }
                formParamList.add(new FormParam(fieldName, fieldValue));
            }
        }
        return formParamList;
    }

    private static List<FormParam> parseInputStream(HttpServletRequest request) throws IOException {
        List<FormParam> formParamList = new ArrayList<>();
        String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
        if (StringUtil.isNotEmpty(body)){
            String[] kvs = StringUtil.splitString(body, "&");
            for (String kv : kvs){
                String[] array = StringUtil.splitString(kv, "=");
                if (ArrayUtil.isNotEmpty(array) && array.length == 2){
                    String fieldName = array[0];
                    String fieldValue = array[1];
                    formParamList.add(new FormParam(fieldName, fieldValue));
                }
            }
        }
        return formParamList;
    }

}
