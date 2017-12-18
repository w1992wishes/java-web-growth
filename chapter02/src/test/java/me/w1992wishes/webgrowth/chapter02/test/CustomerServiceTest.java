package me.w1992wishes.webgrowth.chapter02.test;

import me.w1992wishes.webgrowth.chapter02.model.Customer;
import me.w1992wishes.webgrowth.chapter02.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by w1992wishes on 2017/12/18.
 */
public class CustomerServiceTest {
    private final CustomerService customerService;

    public CustomerServiceTest(){
        this.customerService = new CustomerService();
    }

    @Before
    public void init(){
        //todo 初始化数据库
    }

    @Test
    public void getCustomerListTest() throws  Exception{
        List<Customer> customerList = customerService.getCustomerList("");
        Assert.assertEquals(2, customerList.size());
    }

    @Test
    public void getCustomerTest() throws Exception{
        long id = 1;
        Customer customer = customerService.getCustomer(id);
        Assert.assertNotNull(customer);
    }

    @Test
    public void createCustomerTest() throws Exception{
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("name", "customer100");
        fieldMap.put("contact", "Bob");
        fieldMap.put("telephone", "13233334444");
        boolean result = customerService.createCustomer(fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void updateCustomerTest() throws Exception{
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("telephone", "11011101110");
        boolean result = customerService.updateCustomer(1, fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void deleteCustomer() throws Exception{
        long id = 1;
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);
    }
}
