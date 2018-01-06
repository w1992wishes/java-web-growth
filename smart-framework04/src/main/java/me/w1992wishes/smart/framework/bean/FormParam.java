package me.w1992wishes.smart.framework.bean;

/**
 * 封装表单参数
 *
 * Created by w1992wishes
 * on 2018/1/3.
 */
public class FormParam {

    private String filedName;
    private Object fieldValue;

    public FormParam(String filedName, Object fieldValue) {
        this.filedName = filedName;
        this.fieldValue = fieldValue;
    }

    public String getFiledName() {
        return filedName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

}
