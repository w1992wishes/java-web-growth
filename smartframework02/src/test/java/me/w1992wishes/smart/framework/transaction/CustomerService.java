package me.w1992wishes.smart.framework.transaction;

import me.w1992wishes.smart.framework.annotation.Service;
import me.w1992wishes.smart.framework.annotation.Transaction;
import me.w1992wishes.smart.framework.helper.DatabaseHelper;

import java.util.List;
import java.util.Map;

/**
 * Created by w1992wishes
 * on 2018/1/2.
 */
@Service
public class CustomerService {

    public List<Customer> getCustomerList(){
        String sql = "SELECT * FROM customer";
        return DatabaseHelper.queryEntityList(Customer.class, sql);
    }

    @Transaction
    public boolean createCustomer(Map<String, Object> fieldMap){
        return DatabaseHelper.insertEntity(Customer.class, fieldMap);
    }
}
