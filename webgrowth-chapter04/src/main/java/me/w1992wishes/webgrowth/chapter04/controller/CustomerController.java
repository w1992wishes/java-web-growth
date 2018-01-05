package me.w1992wishes.webgrowth.chapter04.controller;

import me.w1992wishes.smart.framework.annotation.Action;
import me.w1992wishes.smart.framework.annotation.Controller;
import me.w1992wishes.smart.framework.annotation.Inject;
import me.w1992wishes.smart.framework.bean.Data;
import me.w1992wishes.smart.framework.bean.FileParam;
import me.w1992wishes.smart.framework.bean.Param;
import me.w1992wishes.smart.framework.bean.View;
import me.w1992wishes.webgrowth.chapter04.model.Customer;
import me.w1992wishes.webgrowth.chapter04.service.CustomerService;

import java.util.List;
import java.util.Map;

/**
 * Created by w1992wishes
 * on 2018/1/4.
 */
@Controller
public class CustomerController {

    @Inject
    private CustomerService customerService;

    /**
     * 进入客户列表界面
     *
     * @return
     */
    @Action("get:/customer")
    public View index(){
        List<Customer> customerList = customerService.getCustomerList();
        return new View("customer.jsp").addModel("customerList", customerList);
    }

    /**
     * 跳转创建客户界面
     *
     * @param param
     * @return
     */
    @Action("get:/customer_create")
    public View create(Param param){
        return new View("customer_create.jsp");
    }

    /**
     * 处理创建客户请求
     *
     * @param param
     * @return
     */
    @Action("post:/customer_create")
    public Data createCustomer(Param param){
        Map<String, Object> fieldMap = param.getFieldMap();
        FileParam fileParam = param.getFile("photo");
        boolean result = customerService.createCustomer(fieldMap, fileParam);
        return new Data(result);
    }

}
