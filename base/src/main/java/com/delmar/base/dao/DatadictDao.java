/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.dao;

import java.util.List;

import com.delmar.base.model.Datadict;
import com.delmar.core.dao.CoreDao;

/**
 * @author 刘大磊 2014-12-22 13:26:54
 */
public interface DatadictDao extends CoreDao<Datadict> {

	/**
	 * @param typeId
	 * @return
	 */
	List<Datadict> getDatadictListByTypeId(Integer typeId,Integer clientId);
	void updateIndexOrder(Integer indexOrder, Integer id);
}
