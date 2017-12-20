package me.w1992wishes.webgrowth.chapter02.service;

import me.w1992wishes.webgrowth.chapter02.helper.DatabaseHelper;
import me.w1992wishes.webgrowth.chapter02.helper.DatabaseHelper2;
import me.w1992wishes.webgrowth.chapter02.helper.DatabaseHelper3;
import me.w1992wishes.webgrowth.chapter02.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by w1992wishes on 2017/12/18.
 */
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    /**
     * 获取客户列表
     *
     * @return
     */
    public List<Customer> getCustomerList(){
        List<Customer> customerList = new ArrayList<>();
        Connection conn = null;
        try {
            String sql = "SELECT * FROM customer";
            conn = DatabaseHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setContact(rs.getString("contact"));
                customer.setTelephone(rs.getString("telephone"));
                customer.setEmail(rs.getString("email"));
                customer.setRemark(rs.getString("remark"));
                customerList.add(customer);
            }
        }catch (SQLException e) {
            LOGGER.error("execute sql query failure", e);
        }finally {
            DatabaseHelper.closeConnection(conn);
        }
        return customerList;
    }

    /**
     * 获取客户列表 利用DatabaseHelper2，将赋值语句省去
     *
     * @return
     */
    public List<Customer> getCustomerList2(){
        String sql = "SELECT * FROM customer";
        return DatabaseHelper2.queryEntityList(Customer.class, sql);
    }

    /**
     * 获取客户列表 利用DatabaseHelper3
     *
     * @return
     */
    public List<Customer> getCustomerList3(){
        String sql = "SELECT * FROM customer";
        return DatabaseHelper3.queryEntityList(Customer.class, sql);
    }

    /**
     * 获取客户
     * @param id
     * @return
     */
    public Customer getCustomer(long id){
        Connection conn = null;
        Customer customer = null;
        try{
            String sql = "SELECT * FROM customer WHERE id = ?";
            conn = DatabaseHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 1);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setContact(rs.getString("contact"));
                customer.setTelephone(rs.getString("telephone"));
                customer.setEmail(rs.getString("email"));
                customer.setRemark(rs.getString("remark"));
            }
        } catch (SQLException e) {
            LOGGER.error("execute sql query failure", e);
        } finally {
            DatabaseHelper.closeConnection(conn);
        }
        return customer;
    }

    /**
     * 获取客户， 利用DatabaseHelper2
     *
     * @param id
     * @return
     */
    public Customer getCustomer2(long id){
        String sql = "SELECT * FROM customer WHERE id = ?";
        return DatabaseHelper2.queryEntity(Customer.class, sql, 1);
    }

    /**
     * 创建客户
     * @param fieldMap
     * @return
     */
    public boolean createCustomer(Map<String, Object> fieldMap){
        Connection conn = null;
        try{
            String sql = "INSERT INTO customer(name, contact, telephone, email, remark) VALUES(?, ?, ?, ?, ?);";
            conn = DatabaseHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, String.valueOf(fieldMap.get("name")));
            stmt.setString(2, String.valueOf(fieldMap.get("contact")));
            stmt.setString(3, String.valueOf(fieldMap.get("telephone")));
            stmt.setString(4, String.valueOf(fieldMap.get("email")));
            stmt.setString(5, String.valueOf(fieldMap.get("remark")));
            int i = stmt.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            LOGGER.error("execute sql query failure", e);
        } finally {
            DatabaseHelper.closeConnection(conn);
        }
        return false;
    }

    /**
     * 创建客户，利用DatabaseHelper2
     * @param fieldMap
     * @return
     */
    public boolean createCustomer2(Map<String, Object> fieldMap){
        return DatabaseHelper2.insertEntity(Customer.class, fieldMap);
    }

    /**
     * 更新客户
     * @param fieldMap
     * @return
     */
    public boolean updateCustomer2(long id, Map<String, Object> fieldMap){
        Connection conn = null;
        try{
            String sql = "UPDATE customer SET name = ?, contact = ?, telephone = ?, email = ?, remark = ? WHERE id = ?";
            conn = DatabaseHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, String.valueOf(fieldMap.get("name")));
            stmt.setString(2, String.valueOf(fieldMap.get("contact")));
            stmt.setString(3, String.valueOf(fieldMap.get("telephone")));
            stmt.setString(4, String.valueOf(fieldMap.get("email")));
            stmt.setString(5, String.valueOf(fieldMap.get("remark")));
            stmt.setInt(6, 3);
            int i = stmt.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            LOGGER.error("execute sql query failure", e);
        } finally {
            DatabaseHelper.closeConnection(conn);
        }
        return false;
    }

    /**
     * 更新客户，利用DatabaseHelper2
     * @param fieldMap
     * @return
     */
    public boolean updateCustomer(long id, Map<String, Object> fieldMap){
        return DatabaseHelper2.updateEntity(Customer.class, 3, fieldMap);
    }

    /**
     * 删除客户
     * @param id
     * @return
     */
    public boolean deleteCustomer(long id){
        Connection conn = null;
        try{
            String sql = "DELETE  FROM customer WHERE id = ?";
            conn = DatabaseHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 3);
            int i = stmt.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            LOGGER.error("execute sql query failure", e);
        } finally {
            DatabaseHelper.closeConnection(conn);
        }
        return false;
    }

    /**
     * 删除客户，利用DatabaseHelper2
     * @param id
     * @return
     */
    public boolean deleteCustomer2(long id){
        return DatabaseHelper2.deleteEntity(Customer.class, id);
    }
}
