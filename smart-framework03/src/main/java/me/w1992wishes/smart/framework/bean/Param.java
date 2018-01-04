package me.w1992wishes.smart.framework.bean;

import me.w1992wishes.smart.framework.util.CastUtil;
import me.w1992wishes.smart.framework.util.CollectionUtil;
import me.w1992wishes.smart.framework.util.StringUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求参数对象
 *
 * Created by w1992wishes
 * on 2017/12/22.
 */
public class Param {

    private List<FormParam> formParamList;

    private List<FileParam> fileParamList;

    public Param(List<FormParam> formParamList) {
        this.formParamList = formParamList;
    }

    public Param(List<FormParam> formParamList, List<FileParam> fileParamList) {
        this.formParamList = formParamList;
        this.fileParamList = fileParamList;
    }

    /**
     * 获取请求参数映射
     *
     * @return
     */
    public Map<String, Object> getFieldMap(){
        Map<String, Object> fieldMap = new HashMap<>();
        if (CollectionUtil.isNotEmpty(formParamList)){
            for (FormParam formParam : formParamList){
                String fieldName = formParam.getFiledName();
                Object fieldValue = formParam.getFieldValue();
                if (fieldMap.containsKey(fieldName)){
                    fieldValue = fieldMap.get(fieldName) + StringUtil.SEPARATOR + fieldValue;
                }
                fieldMap.put(fieldName, fieldValue);
            }
        }
        return fieldMap;
    }

    /**
     * 获取上传文件映射
     *
     * @return
     */
    public Map<String, List<FileParam>> getFileMap(){
        Map<String, List<FileParam>> fileMap = new HashMap<>();
        if (CollectionUtil.isNotEmpty(fileParamList)){
            for (FileParam fileParam : fileParamList){
                String fieldName = fileParam.getFieldName();
                List<FileParam> fileParamList;
                if (fileMap.containsKey(fieldName)){
                    fileParamList = fileMap.get(fieldName);
                } else {
                    fileParamList = new ArrayList<>();
                }
                fileParamList.add(fileParam);
                fileMap.put(fieldName, fileParamList);
            }
        }
        return fileMap;
    }

    /**
     * 获取所有上传文件
     *
     * @param fieldName
     * @return
     */
    public List<FileParam> getFileList(String fieldName){
        return getFileMap().get(fieldName);
    }

    /**
     * 获取唯一上传文件
     *
     * @param fieldName
     * @return
     */
    public FileParam getFile(String fieldName){
        List<FileParam> fileParamList = getFileList(fieldName);
        if (CollectionUtil.isNotEmpty(fileParamList) && fileParamList.size() == 1){
            return fileParamList.get(0);
        }
        return null;
    }

    /**
     * 验证参数是否为空
     *
     * @return
     */
    public boolean isEmpty(){
        return CollectionUtil.isEmpty(formParamList) && CollectionUtil.isEmpty(fileParamList);
    }

    /**
     * 根据参数名获取string型参数
     *
     * @param name
     * @return
     */
    public String getString(String name){
        return CastUtil.castString(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取double型参数
     *
     * @param name
     * @return
     */
    public double getDouble(String name){
        return CastUtil.castDouble(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取long型参数
     *
     * @param name
     * @return
     */
    public long getLong(String name){
        return CastUtil.castLong(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取int型参数
     *
     * @param name
     * @return
     */
    public int getInt(String name){
        return CastUtil.castInt(getFieldMap().get(name));
    }

    /**
     * 根据参数名获取Boolean型参数
     * @param name
     * @return
     */
    public boolean getBoolean(String name){
        return CastUtil.castBoolean(getFieldMap().get(name));
    }

}
