/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.quota.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import com.delmar.utils.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.enumdef.ModeType;
import com.delmar.base.model.Currency;
import com.delmar.base.model.Datadict;
import com.delmar.base.model.Nameaccounts;
import com.delmar.base.model.Unit;
import com.delmar.base.service.ChargenameService;
import com.delmar.base.service.CurrencyService;
import com.delmar.base.service.NameaccountsService;
import com.delmar.base.service.PortService;
import com.delmar.base.service.UnitService;
import com.delmar.quota.service.QuotaService;
import com.delmar.quota.service.busmodel.QuotaBusModel;
import com.delmar.quota.service.busmodel.QuotaBusModelResult;
import com.delmar.quota.service.busmodel.QuotaBusParam;
import com.delmar.rate.model.Ratedetail;
import com.delmar.rate.model.Ratemarkup;
import com.delmar.rate.model.Ratemaster;
import com.delmar.rate.service.RatedetailService;
import com.delmar.rate.service.RatemarkupService;
import com.delmar.rate.service.RatemasterService;
import com.delmar.utils.DmLog;

/**
 * @author 刘大磊 2014年12月25日 下午2:01:45
 */
@Service("airQuotaService")
public class AirQuotaServiceImpl implements QuotaService{
    private DmLog logger =DmLog.getLogger("InfoLogger."+getClass().getName()); 
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private RatemasterService ratemasterService;
	@Autowired
	private RatedetailService ratedetailService;
	@Autowired
	private ChargenameService chargenameService;
	@Autowired
	private PortService portService;
	@Autowired
	private UnitService unitService;
	@Autowired
	private RatemarkupService ratemarkupService;
	@Autowired
	private NameaccountsService nameaccountsService;//
	/** (non-Javadoc)
	 * @throws DataNotFondException 
	 * @see com.delmar.quota.service.QuotaService#quote(com.delmar.quota.service.busmodel.QuotaBusParam)
	 * 
	 * 1.	重量（KG）
	 *	2.	体积 (CBM)
	 *	3.	货类
	 *	4.	始发港
	 *	5.	目的港
	 *
	 */
	public QuotaBusModelResult quote(QuotaBusParam param)
			throws QuotaParamException, DataNotFondException {

		QuotaBusModelResult qbmr=new QuotaBusModelResult();
		//根据 始发港 目的港 获得 报价单
		//始发港
		StringBuilder sb=new StringBuilder();
	    if(!portService.validatePortMode(param.getPolPort().getId(), ModeType.Air.toString()))
		{
	    	sb.append("发货港输入不正确");
		}
	    if(!portService.validatePortMode(param.getPoaPort().getId(),  ModeType.Air.toString()))
	    {
	    	sb.append("目的港输入不正确");
	    }
	    if(StringUtil.isNotEmpty(sb.toString()))
	    {
	    
	    		throw new QuotaParamException(sb.toString());
	    
	    }
	    Map<String,Object> modelSearch=new HashMap<String,Object>();
    	modelSearch.put("pol", param.getPolPort().getId());
    	modelSearch.put("poa", param.getPoaPort().getId());
    	modelSearch.put("mode", ModeType.Air.toString());
    	modelSearch.put("clientId", param.getProvider().getId());
    	modelSearch.put("accessString", " flag = 0 and isActive=1 ");
		List<Ratemaster> ratemasterList=ratemasterService.selectByExample(modelSearch);
		//取最低报价报给客户
    	TotalQuota minTotalQuota=null;
    	
    	for(Ratemaster rm:ratemasterList)
    	{
    		try
    		{
    			TotalQuota totalQuota=caculateQuota(rm,param);
    			//设置备注
    			String remark="运输天数:"+String.valueOf(rm.getTranstime())+"  "+rm.getRemark();
    			totalQuota.setRemark(remark);        			
    			if(minTotalQuota==null)
        		{
        			minTotalQuota=totalQuota;
        		}
        		else
        		{
        			if(minTotalQuota.getTotalPrice().compareTo(totalQuota.getTotalPrice())<0)
        			{
        				minTotalQuota=totalQuota;
        			}
        		}
    		}
    		catch(DataNotFondException e)
    		{
    			logger.error(e);
    		}
    		
    		
    	
    	}
	    if(minTotalQuota==null)
	    {
	    	throw	new DataNotFondException("找不到报价");
	    }
	    qbmr.setMasterId(minTotalQuota.getMasterrateId());	    
    	qbmr.setQuotaList(minTotalQuota.getQuotaList());
    	qbmr.setRemark(minTotalQuota.getRemark());
		return qbmr;
		

	}
	/**
	 * 获取报价单总价格
	 * @param rm
	 * @return
	 * @throws DataNotFondException 
	 */
	private TotalQuota caculateQuota(Ratemaster master,QuotaBusParam param)throws QuotaParamException, DataNotFondException 
	{
		BigDecimal weightd=param.getWeight();
		
		double wh=weightd.doubleValue();
		
		Integer kgID=0;
		String kgCode=null;
		if(wh<45)
		{
			//kgID=Datadict.KG_MKGS_ID;
			kgCode=Datadict.KG_MKGS_CODE;
		}
		else if(wh>=45&&wh<100)
		{
			//kgID=Datadict.KG_45KGS_ID;
			kgCode=Datadict.KG_45KGS_CODE;
		}
		else if(wh>=100&&wh<300)
		{
			//kgID=Datadict.KG_100KGS_ID;
			kgCode=Datadict.KG_100KGS_CODE;
		}
		else if(wh>=300&&wh<500)
		{
			//kgID=Datadict.KG_300KGS_ID;
			kgCode=Datadict.KG_300KGS_CODE;
		}
		else if(wh>=500&&wh<1000)
		{
			//kgID=Datadict.KG_500KGS_ID;
			kgCode=Datadict.KG_500KGS_CODE;
		}
		else if(wh>=1000)
		{
			//kgID=Datadict.KG_1000KGS_ID;
			kgCode=Datadict.KG_1000KGS_CODE;
		}

		
		try {
			kgID=chargenameService.GetIdByCode(kgCode);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		//chargenameService.selectByExample(codeMap);
		//step 01 取单价
		Ratedetail freightdetail=null;
		List<Ratedetail> detailList=null;
		List<QuotaBusModel> quotaList=new ArrayList<QuotaBusModel>();	
		HashMap currMap=new HashMap();
		HashMap currCurrMap=new HashMap();		
		
		
		BigDecimal totalPrice=new BigDecimal(0);

	     Map<String,Object>modelSearch=new HashMap<String,Object>();
	      modelSearch.put("rRateMasterId", master.getId());
	   	 //也需要加上费用名称
	   	  modelSearch.put("baseChargenameId", kgID);		   	   	 
	   	  modelSearch.put("freighttype", Datadict.FREIGHTTYPE_FREIGHT);
	   	  modelSearch.put("accessString", "flag = 0 and CONVERT(char(19), getdate(), 120) >=CONVERT(char(19), effectbgndate, 120) and CONVERT(char(19), getdate(), 120) <= CONVERT(char(19), effectenddate, 120) ");	   	  
	     freightdetail= ratedetailService.getByExample(modelSearch);
	 	  if(freightdetail==null)
	      {
		    throw new DataNotFondException("找不到报价");   	 		 
	      }
	 	  
	      modelSearch=new HashMap<String,Object>();
	      modelSearch.put("rRateMasterId", master.getId());
	      //运费附加费
	      modelSearch.put("freighttype", Datadict.FREIGHTTYPE_ADDITIONAL);		
	      modelSearch.put("accessString", "flag = 0 and CONVERT(char(19), getdate(), 120) >=CONVERT(char(19), effectbgndate, 120) and CONVERT(char(19), getdate(), 120) <= CONVERT(char(19), effectenddate, 120) ");
	      detailList= ratedetailService.selectByExample(modelSearch);   
	   	  detailList.add(freightdetail);

		
		
		
		for (int i=0;i<detailList.size();i++)
		{
			Ratedetail objDetail=(Ratedetail)detailList.get(i);
			BigDecimal price=null;
			BigDecimal unitNumber=null;
			//计算出金额
			//需要区分整箱和拼箱，如果是拼箱是需要计算计费重的

			//判断是运费还是运费附加费
			if (Datadict.FREIGHTTYPE_FREIGHT==objDetail.getFreighttype().intValue())
			{
			   //计算计费重	
				BigDecimal chWeight=getCHW(param.getWeight(),param.getVolume());
				unitNumber=chWeight;
			}
			else  //运费附加费
			{
				/*
				 * PerContainer		PerShipment  PerCustom  PerGW  PerVolume  PerCHW  Per20  Per40
 				   Per40HC  Per45
				 * */
				unitNumber=getUnitNumber(param,objDetail.getBaseUnitId());
			}
			
			//当前的价格
			price=objDetail.getPrice().multiply(unitNumber);
			//根据最大值，最小值，取最终价格
			price=this.ratedetailService.caculatePrice(price, objDetail.getMinvalue(), objDetail.getMaxvalue());
			//如果是浮动的
			if(Datadict.BEFIXED_FLOAT.equals(objDetail.getBefixed()))
			{
				//下面找到相应的浮动规则
				Map<String,Object> nameaccountsparam=new HashMap<String,Object>();
				nameaccountsparam.put("code", param.getUser().getUsername());
				Nameaccounts nameaccounts=nameaccountsService.getByExample(nameaccountsparam);
				Ratemarkup ratemarkup=ratemarkupService.getRatemarkup(nameaccounts.getId(), master.getId(), objDetail.getBaseChargenameId(),objDetail.getFreighttype());
				if (ratemarkup!=null)
				{
				   price=getMarkupPrice(price,ratemarkup);	
				}
		 	 }
			
		   	Currency currency= currencyService.selectByPrimaryKey(objDetail.getBaseCurrencyId());
			if (currMap.containsKey(currency.getCode()))
			{
				BigDecimal currPrice=(BigDecimal)currMap.get(currency.getCode());
				currPrice=currPrice.add(price);
				currMap.put(currency.getCode(), currPrice);
				
				totalPrice=totalPrice.add(price.multiply(currency.getRate()));
			}
			else
			{
				currMap.put(currency.getCode(), price);
				currCurrMap.put(currency.getCode(), currency);
				totalPrice=totalPrice.add(price.multiply(currency.getRate()));				
			}
		}
		
		
		Iterator currIt=currMap.keySet().iterator();
		while (currIt.hasNext())
		{
		  String keyStr=(String)currIt.next();
		  BigDecimal keyValue=(BigDecimal)currMap.get(keyStr);
		  Currency currencyValue=(Currency)currCurrMap.get(keyStr);
		  
		  QuotaBusModel qbm=new QuotaBusModel();
		  qbm.setCurrency(keyStr);
		  qbm.setCurr(currencyValue);
		  qbm.setPrice(keyValue);
		  quotaList.add(qbm);
		}
			
	   	TotalQuota tq=new TotalQuota(master.getId(),quotaList);
		return tq;	
		
		
	}
	
	
	private BigDecimal getMarkupPrice(BigDecimal price,Ratemarkup ratemarkup)
	{

		if(ratemarkup.getMarkuptype()==0)
		{
			return price.multiply(new BigDecimal(1).add(ratemarkup.getMarkupamount()));
		}
		else
		{
			return price.add(ratemarkup.getMarkupamount());
		}
		
	}
	
	
	private BigDecimal getCHW(BigDecimal gw,BigDecimal volume)
	{
		BigDecimal chWeight=volume.divide(new BigDecimal(167),BigDecimal.ROUND_HALF_DOWN);
		if(chWeight.compareTo(gw)>=0)
		{
			return chWeight;
			
		}
		else
		{
			return gw;
		}		
	}
	
	private BigDecimal getUnitNumber(QuotaBusParam param,Integer unitId)
	{
		/*
		 * PerShipment PerCustom PerGW PerVolume PerCHW 
		 * */
    	Unit objUnit=unitService.selectByPrimaryKey(unitId);
    	if (objUnit==null)
    		return new BigDecimal(0);
		
		String unitType=objUnit.getCode();
		
		if (unitType.equalsIgnoreCase("PerShipment"))
		{
			return new BigDecimal(1);
		}
		else if (unitType.equalsIgnoreCase("PerCustom"))
		{
			return new BigDecimal(1);
		}
		else if (unitType.equalsIgnoreCase("PerGW"))
		{
			return param.getWeight();
		}
		else if (unitType.equalsIgnoreCase("PerVolume"))
		{
			return param.getVolume();
		}
		else if (unitType.equalsIgnoreCase("PerCHW"))
		{
			return getCHW(param.getWeight(),param.getVolume());
		}
		else
		{
			return new BigDecimal(0);
		}
		
			
		
			
	}

	



}
