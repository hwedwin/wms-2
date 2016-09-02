/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.dao.CompanysTrlDao;
import com.delmar.base.model.CompanysTrl;
import com.delmar.base.service.CompanysTrlService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-03-18 11:41:34
 */
@Service("companysTrlService")
public class CompanysTrlServiceImpl extends CoreServiceImpl<CompanysTrl> implements
		CompanysTrlService {
	@Autowired
	private CompanysTrlDao companysTrlDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<CompanysTrl> getCoreDao() {
		return companysTrlDao;
	}
	public void deleteCompanysTrlList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			companysTrlDao.deleteByPrimaryKey(id);
		}
	}
	
}
