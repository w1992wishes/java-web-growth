package me.w1992wishes.webgrowth.chapter03.controller;

import me.w1992wishes.smart.framework.annotation.Action;
import me.w1992wishes.smart.framework.annotation.Controller;
import me.w1992wishes.smart.framework.annotation.Inject;
import me.w1992wishes.smart.framework.bean.Data;
import me.w1992wishes.smart.framework.bean.Param;
import me.w1992wishes.smart.framework.bean.View;
import me.w1992wishes.webgrowth.chapter03.model.Customer;
import me.w1992wishes.webgrowth.chapter03.service.CustomerService;

import java.util.List;
import java.util.Map;

/**
 * 使用开发的smart-framework作为开发框架
 *
 * Created by w1992wishes
 * on 2017/12/25.
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
        List<Customer> customerList = customerService.getCustomerList3();
        return new View("customer.jsp").addModel("customerList", customerList);
    }

    /**
     * 显示客户基本信息
     *
     * @param param
     * @return
     */
    @Action("get:/customer_show")
    public View show(Param param){
        long id = param.getLong("id");
        Customer customer = customerService.getCustomer2(id);
        return new View("customer_show.jsp").addModel("customer", customer);
    }

    /**
     * 调转创建客户界面
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
        Map<String, Object> fieldMap = param.getParamMap();
        boolean result = customerService.createCustomer2(fieldMap);
        return new Data(result);
    }

    /**
     * 跳转编辑页面
     *
     * @param param
     * @return
     */
    @Action("get:/customer_edit")
    public View customer_edit(Param param){
        long id = param.getLong("id");
        Customer customer = customerService.getCustomer2(id);
        return new View("customer_edit.jsp").addModel("customer", customer);
    }

    /**
     * 处理编辑请求
     *
     * @param param
     * @return
     */
    @Action("post:/customer_edit")
    public Data editCustomer(Param param){
        long id = param.getLong("id");
        Map<String, Object> fieldMap = param.getParamMap();
        boolean result = customerService.updateCustomer2(id, fieldMap);
        return new Data(result);
    }


    /**
     * 删除
     *
     * @param param
     * @return
     */
    @Action("get:/customer_delete")
    public Data deleteCustomer(Param param){
        long id = param.getLong("id");
        boolean result = customerService.deleteCustomer2(id);
        return new Data(result);
    }

}
