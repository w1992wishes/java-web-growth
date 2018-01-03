package me.w1992wishes.smart.framework.transaction;

import me.w1992wishes.smart.framework.HelperLoader;
import me.w1992wishes.smart.framework.helper.BeanHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by w1992wishes
 * on 2018/1/2.
 */
public class TransactionTest {

    private CustomerService customerService;

    @Before
    public void init(){
        HelperLoader.init();
        customerService = BeanHelper.getBean(CustomerService.class);
    }

    @Test
    public void getCustomers(){
        List<Customer> customers = customerService.getCustomerList();
        Assert.assertEquals(3, customers.size());
    }

    @Test
    public void createCustomers(){
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("name", "customer200");
        fieldMap.put("contact", "Bob");
        fieldMap.put("telephone", "13233334444");
        fieldMap.put("email", "bob@jmail.com");
        boolean result = customerService.createCustomer(fieldMap);
        Assert.assertTrue(result);
    }

}
