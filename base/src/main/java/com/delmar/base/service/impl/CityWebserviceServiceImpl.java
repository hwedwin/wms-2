/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import com.delmar.base.dao.CityDao;
import com.delmar.base.dao.CityWebserviceDao;
import com.delmar.base.model.CityWebservice;
import com.delmar.base.service.CityWebserviceService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 刘大磊 22015-03-16 17:51:25
 */
@Service("cityWebserviceService")
public class CityWebserviceServiceImpl extends CoreServiceImpl<CityWebservice> implements
		CityWebserviceService {
	@Autowired
	private CityWebserviceDao cityWebserviceDao;
	@Autowired
	private CityDao cityDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<CityWebservice> getCoreDao() {
		return cityWebserviceDao;
	}
	public void deleteCityWebserviceList(Integer[] ids)
	{
		if(ids!=null) {
			for (Integer id : ids) {
				cityWebserviceDao.deleteByPrimaryKey(id);
			}
		}
	}

	
}
