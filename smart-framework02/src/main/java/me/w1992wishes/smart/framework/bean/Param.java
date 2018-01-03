package me.w1992wishes.smart.framework.bean;

import me.w1992wishes.smart.framework.util.CastUtil;

import java.util.Map;

/**
 * 请求参数对象
 *
 * Created by w1992wishes
 * on 2017/12/22.
 */
public class Param {

    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 根据参数名获取long型参数
     *
     * @param name
     * @return
     */
    public long getLong(String name){
        return CastUtil.castLong(paramMap.get(name));
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }
}
