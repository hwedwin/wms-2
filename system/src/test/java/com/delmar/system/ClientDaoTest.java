/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                   *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                           *
 *****************************************************************************/
package com.delmar.system;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.delmar.sys.dao.ClientDao;
import com.delmar.sys.model.Client;


/**
 * @author 刘大磊 2014年12月19日 上午10:56:22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ClientDaoTest {
	@Autowired
	ClientDao clientDao;
	@Test
	public void testCase()
	{
	
		//addClient();
		searchClient();
		//deleteClient();
		searchClient();
	}
	private void addClient()
	{
		Client client=new Client();
		client.setName("刘大磊");
		client.setRemark("刘大磊");
		client.setValue("delar");
		clientDao.save(client);
	}
	private void searchClient()
	{
		List list=clientDao.selectByExample(null);
		System.out.println(list.size());
		
	}
	public void deleteClient()
	{
		clientDao.deleteByExample(null);
	}
}
