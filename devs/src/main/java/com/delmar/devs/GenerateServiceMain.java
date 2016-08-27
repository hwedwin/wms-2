/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.devs;

import com.delmar.core.dto.TableMetaDataDto;
import com.delmar.devs.ftl.FreeMarkerHelper;
import com.delmar.devs.model.GenModelDto;
import com.delmar.devs.model.ServiceModel;
import com.delmar.utils.DateTimeDecorator;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.*;

/**
 * @author 刘大磊 2014年12月22日 下午12:44:44
 */
public class GenerateServiceMain {
	private TableMetaDataDto tableMetaDataDto;
	private GenModelDto model;
	List<ServiceModel> lineList;
	public GenerateServiceMain(TableMetaDataDto tableMetaDataDto, GenModelDto model) {
		this.tableMetaDataDto=tableMetaDataDto;
		this.model=model;
		lineList=new ArrayList<ServiceModel>();

		if(model.getIncludeModelList()!=null)
		{
			for(GenModelDto m:model.getIncludeModelList())
			{
				ServiceModel sm=new ServiceModel();
				sm.setModel(m.getModelName());
				sm.setModule(m.getModule());
				lineList.add(sm);
			}
		}
	}


	public  void generateInterface()
	{
	   Map root = new HashMap();
		Date date=new Date();
		String interfacepackage="com.delmar."+model.getModule()+".service";
		String modelpackage="com.delmar."+model.getModule()+".model";
		String datetime=DateTimeDecorator.dateToLongString(date);
		root.put("packagename", interfacepackage);
		root.put("modelpackage", modelpackage);
		root.put("datetime", datetime);
		root.put("lineList",lineList);
		//gc.setTime(date);
		try
		{


				root.put("modelname", model.getModelName());
				FreeMarkerHelper.getInstance().outFile("serviceInterface.ftl",root,"src/main/java/"+interfacepackage.replace(".", "/")+"/"+model.getModelName()+"Service.java");

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public   void generateServiceclass()
	{
		Map root = new HashMap();
		Date date=new Date();
		String classpackage="com.delmar."+model.getModule()+".service.impl";
		String modelpackage="com.delmar."+model.getModule()+".model";
		String datetime=DateTimeDecorator.dateToLongString(date);
		root.put("packagename", "com.delmar."+model.getModule()+".service.impl");
	
		root.put("modelpackage", modelpackage);
		root.put("datetime", datetime);


		root.put("lineList",lineList);
		//gc.setTime(date);
		try
		{

				root.put("interfacefulldaoname", "com.delmar."+model.getModule()+".dao."+model.getModelName()+"Dao");
				//toUpperCase
				root.put("interfacefullservicename", "com.delmar."+model.getModule()+".service."+model.getModelName()+"Service");

				root.put("modelname", model.getModelName());
				String bgnChar=model.getModelName().substring(0,1);
				
				root.put("repositoryname", bgnChar.toLowerCase()+model.getModelName().substring(1)+"Dao");
				root.put("mappername", "com.delmar."+model.getModule()+".mybatis.sql."+model.getModelName()+"Mapper");
				String modelname2=model.getModelName().substring(0,1).toLowerCase()+model.getModelName().substring(1);
				System.out.println("modelname2="+modelname2);
				root.put("serviceName", "@Service(\""+modelname2+"Service\")");

				FreeMarkerHelper.getInstance().outFile("serviceClass.ftl",root,"src/main/java/"+classpackage.replace(".", "/")+"/"+model.getModelName()+"ServiceImpl.java");

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
