/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.sys.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.sys.dao.UserThirdPartyDao;
import com.delmar.sys.model.UserThirdParty;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2015-05-29 15:11:15
 */
@Repository("userThirdPartyDao") 
public class UserThirdPartyDaoMybatis extends CoreDaoMyBatis<UserThirdParty> implements UserThirdPartyDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.sys.mybatis.sql.UserThirdPartyMapper";
	}



}
