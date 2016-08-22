package com.delmar.base.model;

import java.math.BigDecimal;

import com.delmar.core.model.CoreModel;

/**
 * @author 刘大磊 2014年12月22日 上午9:46:57
 */
public class Currency extends CoreModel{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_currency.code
     *
     * @mbggenerated
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_currency.cname
     *
     * @mbggenerated
     */
    private String cname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_currency.ename
     *
     * @mbggenerated
     */
    private String ename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column base_currency.remark
     *
     * @mbggenerated
     */
    private String remark;
    
    
    /**
     * 汇率
     */
    private BigDecimal rate;
    /**
	 * @return the rate
	 */
	public BigDecimal getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_currency.id
     *
     * @return the value of base_currency.id
     *
     * @mbggenerated
     */
    @Override
	public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_currency.id
     *
     * @param id the value for base_currency.id
     *
     * @mbggenerated
     */
    @Override
	public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_currency.code
     *
     * @return the value of base_currency.code
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_currency.code
     *
     * @param code the value for base_currency.code
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_currency.cname
     *
     * @return the value of base_currency.cname
     *
     * @mbggenerated
     */
    public String getCname() {
        return cname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_currency.cname
     *
     * @param cname the value for base_currency.cname
     *
     * @mbggenerated
     */
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_currency.ename
     *
     * @return the value of base_currency.ename
     *
     * @mbggenerated
     */
    public String getEname() {
        return ename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_currency.ename
     *
     * @param ename the value for base_currency.ename
     *
     * @mbggenerated
     */
    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column base_currency.remark
     *
     * @return the value of base_currency.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column base_currency.remark
     *
     * @param remark the value for base_currency.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}