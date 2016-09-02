/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.crm.dao;

import com.delmar.crm.model.Customer;
import com.delmar.core.dao.CoreDao;

/**
 * @author 刘大磊 2015-01-23 17:47:07
 */
public interface CustomerDao extends CoreDao<Customer> {
	
	public String getNamebyId(Integer id);
}
