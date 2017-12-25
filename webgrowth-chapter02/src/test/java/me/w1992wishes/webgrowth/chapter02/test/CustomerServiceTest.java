package me.w1992wishes.webgrowth.chapter02.test;

import me.w1992wishes.webgrowth.chapter02.helper.DatabaseHelper3;
import me.w1992wishes.webgrowth.chapter02.model.Customer;
import me.w1992wishes.webgrowth.chapter02.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by w1992wishes
 * on 2017/12/18.
 */
public class CustomerServiceTest {
    private final CustomerService customerService;

    public CustomerServiceTest(){
        this.customerService = new CustomerService();
    }

    @Before
    public void init() throws IOException {
        DatabaseHelper3.executeSqlFile("sql/customer_init.sql");
    }

    @Test
    public void getCustomerListTest() throws  Exception{
        List<Customer> customerList = customerService.getCustomerList();
        Assert.assertEquals(2, customerList.size());
    }

    @Test
    public void getCustomerList2Test() throws  Exception{
        List<Customer> customerList = customerService.getCustomerList2();
        Assert.assertEquals(2, customerList.size());
    }

    @Test
    public void getCustomerList3Test() throws  Exception{
        List<Customer> customerList = customerService.getCustomerList3();
        Assert.assertEquals(2, customerList.size());
    }

    @Test
    public void getCustomerTest() throws Exception{
        long id = 1;
        Customer customer = customerService.getCustomer(id);
        Assert.assertNotNull(customer);
    }

    @Test
    public void getCustomer2Test() throws Exception{
        long id = 1;
        Customer customer = customerService.getCustomer2(id);
        Assert.assertNotNull(customer);
    }

    @Test
    public void createCustomerTest() throws Exception{
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("name", "customer100");
        fieldMap.put("contact", "Hank");
        fieldMap.put("telephone", "13233334444");
        fieldMap.put("email", "hank@jmail.com");
        boolean result = customerService.createCustomer(fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void createCustomer2Test() throws Exception{
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("name", "customer200");
        fieldMap.put("contact", "Bob");
        fieldMap.put("telephone", "13233334444");
        fieldMap.put("email", "bob@jmail.com");
        boolean result = customerService.createCustomer2(fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void updateCustomerTest() throws Exception{
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("name", "customer100");
        fieldMap.put("contact", "Hank");
        fieldMap.put("telephone", "13233334444");
        fieldMap.put("email", "bob@jmail.com");
        fieldMap.put("remark", "good boy");
        boolean result = customerService.updateCustomer(3, fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void updateCustomer2Test() throws Exception{
        Map<String, Object> fieldMap = new HashMap<>();
        fieldMap.put("name", "customer101");
        fieldMap.put("contact", "Bob");
        fieldMap.put("telephone", "13233664444");
        fieldMap.put("email", "bob22@jmail.com");
        fieldMap.put("remark", "good boy");
        boolean result = customerService.updateCustomer2(1, fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void deleteCustomer() throws Exception{
        long id = 3;
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);
    }

    @Test
    public void deleteCustomer2() throws Exception{
        long id = 4;
        boolean result = customerService.deleteCustomer2(id);
        Assert.assertTrue(result);
    }
}
