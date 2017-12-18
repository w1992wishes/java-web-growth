package me.w1992wishes.webgrowth.chapter02.service;

import me.w1992wishes.webgrowth.chapter02.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * Created by w1992wishes on 2017/12/18.
 */
public class CustomerService {

    /**
     * 获取客户列表
     * @param key
     * @return
     */
    public List<Customer> getCustomerList(String key){
        return null;
    }

    /**
     * 获取客户
     * @param id
     * @return
     */
    public Customer getCustomer(long id){
        return null;
    }

    /**
     * 创建客户
     * @param fieldMap
     * @return
     */
    public boolean createCustomer(Map<String, Object> fieldMap){
        return false;
    }

    /**
     * 更新客户
     * @param fieldMap
     * @return
     */
    public boolean updateCustomer(long id, Map<String, Object> fieldMap){
        return false;
    }

    /**
     * 删除客户
     * @param id
     * @return
     */
    public boolean deleteCustomer(long id){
        return false;
    }
}
