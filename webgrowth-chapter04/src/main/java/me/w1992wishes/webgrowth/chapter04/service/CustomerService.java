package me.w1992wishes.webgrowth.chapter04.service;

import me.w1992wishes.smart.framework.annotation.Service;
import me.w1992wishes.smart.framework.annotation.Transaction;
import me.w1992wishes.smart.framework.bean.FileParam;
import me.w1992wishes.smart.framework.helper.DatabaseHelper;
import me.w1992wishes.smart.framework.helper.UploadHelper;
import me.w1992wishes.webgrowth.chapter04.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * Created by w1992wishes
 * on 2018/1/4.
 */
@Service
public class CustomerService {

    @Transaction
    public boolean createCustomer(Map<String, Object> fieldMap, FileParam fileParam) {
        boolean result = DatabaseHelper.insertEntity(Customer.class, fieldMap);
        if (result){
            UploadHelper.uploadFile("/tmp/upload/", fileParam);
        }
        return result;
    }

    /**
     * 获取客户列表 利用DatabaseHelper3
     *
     * @return
     */
    public List<Customer> getCustomerList(){
        String sql = "SELECT * FROM customer";
        return DatabaseHelper.queryEntityList(Customer.class, sql);
    }
}
