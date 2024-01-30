package com.billingwebapp.rest;

import java.util.List;

import javax.persistence.OrderBy;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONArray;
import org.json.JSONObject;

import com.billingwebapp.domain.TblCategoryMaster;
import com.billingwebapp.domain.TblFeatureMaster;
import com.billingwebapp.domain.TblFeatureTypeMaster;
import com.billingwebapp.util.QuickUtil;

@Path("/billingWebApp")
public class MyApp {
@GET
@Path("featureTypeMaster")
@Produces(MediaType.APPLICATION_JSON)
public Response featureTypeMaster()
{
	JSONArray jsonarray=new JSONArray();
	JSONObject json =null;
	try
	{
		List<TblFeatureTypeMaster> wherClause1=QuickUtil.retrieveWherClauseOrderby(new TblFeatureTypeMaster(), "TblFeatureTypeMaster", "order by featureTypeId DESC");
		if(wherClause1.size()>0)
		{
			int ind;
			for(ind=0; ind<wherClause1.size();ind++)
			{
				TblFeatureTypeMaster tblftm=wherClause1.get(ind);
				json=new JSONObject();
				json.put("aliasname", tblftm.getFeatureTypeAliasname());
				json.put("name", tblftm.getFeatureTypeName());
				jsonarray.put(ind,json);
			}
			
		}
		
		
				
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return Response.status(Status.ACCEPTED).entity(jsonarray.toString()).build();
	}
@GET
@Path("featureMaster")
@Produces(MediaType.APPLICATION_JSON)
public Response featureMaster()
{
	JSONObject json=null;
	JSONArray jarr=new JSONArray();
	try
	{
		List<TblFeatureMaster> wherClause2=QuickUtil.retrieveWherClauseOrderby(new TblFeatureMaster(), "TblFeatureMaster", "order by featureId");
		if(wherClause2.size()>0)
		{
			int indfm;
			for(indfm=0;indfm<wherClause2.size();indfm++)
			{
				TblFeatureMaster tfm=wherClause2.get(indfm);
				json=new JSONObject();
				json.put("name", tfm.getFeatureName());
				json.put("aliasname", tfm.getFeatureAliasname());
				json.put("type", tfm.getFeatureTypeId());
				jarr.put(indfm, json);
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return Response.status(Status.ACCEPTED).entity(jarr.toString()).build();
	}


@GET
@Path("category")
@Produces(MediaType.APPLICATION_JSON)
public Response category()
{
	JSONObject json=null;
	JSONArray jarr=new JSONArray();
	try
	{
		List<TblCategoryMaster> wherClause3=QuickUtil.retrieveWherClauseOrderby(new TblCategoryMaster(), "TblCategoryMaster", "order by categoryId");
		if(wherClause3.size()>0)
		{
			int catInd;
			for(catInd=0;catInd<wherClause3.size();catInd++)
			{
				TblCategoryMaster tcm=wherClause3.get(catInd);
				json=new JSONObject();
				json.put("name", tcm.getCategoryName());
				json.put("aliasname", tcm.getCategoryAliasname());
				jarr.put(catInd, json);
			}
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return Response.status(Status.ACCEPTED).entity(jarr.toString()).build();}

}
