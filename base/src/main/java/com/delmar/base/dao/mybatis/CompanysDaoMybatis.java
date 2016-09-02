/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.base.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.base.dao.CompanysDao;
import com.delmar.base.model.Companys;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2015-03-18 11:41:34
 */
@Repository("companysDao") 
public class CompanysDaoMybatis extends CoreDaoMyBatis<Companys> implements CompanysDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.base.mybatis.sql.CompanysMapper";
	}



}
