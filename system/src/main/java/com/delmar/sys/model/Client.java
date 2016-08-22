package com.delmar.sys.model;

import com.delmar.core.model.CoreModel;

public class Client extends CoreModel {

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_client.value
     *
     * @mbggenerated
     */
    private String value;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_client.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_client.remark
     *
     * @mbggenerated
     */
    private String remark;
    
    
    
    public Client()
    {
    	
    }
    
    public Client(Integer id,String name)
    {
    	this.id=id;
    	this.name=name;
    }    

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_client.id
     *
     * @return the value of sys_client.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_client.id
     *
     * @param id the value for sys_client.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_client.value
     *
     * @return the value of sys_client.value
     *
     * @mbggenerated
     */
    public String getValue() {
        return value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_client.value
     *
     * @param value the value for sys_client.value
     *
     * @mbggenerated
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_client.name
     *
     * @return the value of sys_client.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_client.name
     *
     * @param name the value for sys_client.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_client.remark
     *
     * @return the value of sys_client.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_client.remark
     *
     * @param remark the value for sys_client.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}