package com.billingwebapp.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONArray;
import org.json.JSONObject;

import com.billingwebapp.commons.CommonUtil;
import com.billingwebapp.domain.TblBillMaster;
import com.billingwebapp.domain.TblPartyMaster;
import com.billingwebapp.domain.TblProductBillPurchase;
import com.billingwebapp.domain.TblProductMaster;
import com.billingwebapp.domain.TblUserMaster;
import com.billingwebapp.util.QuickUtil;

@Path("/billingWebApp")
public class MyApp {
private boolean loginVerify;
/**
 * Does login feature
 * 
 * @param username
 * @param password
 * @return
 */
@GET
@Path("login")
@Produces(MediaType.APPLICATION_JSON)

public Response login(@QueryParam("username") String username, @QueryParam("password") String password)
{
	JSONArray jsonarray=new JSONArray();
	JSONObject json =null;
	try
	{
		//new TblUserMaster(userId, userName, userUsername, userEmailId, userPassword, userDeviceType, userDeviceId, userStatus)
		String whereclauseLogin = "userUsername='"+ username+"' AND userPassword='" + password + "' ";
		
		List<TblUserMaster> wherClause1=QuickUtil.retrieveWherClause(new TblUserMaster(), "TblUserMaster", whereclauseLogin);
		if(wherClause1.size()>0)
		{
			int ind;
			for(ind=0; ind<wherClause1.size();ind++)
			{
				TblUserMaster usermaster=wherClause1.get(ind);
				json=new JSONObject();
				
				json.put("result", usermaster.getUserId());
				jsonarray.put(ind,json);
			}
			
		}
		else
		{
			json=new JSONObject();
			String result="Fail";
			json.put("result", result);
			jsonarray.put(json);
			return Response.status(Status.ACCEPTED).entity(jsonarray.toString()).build();
		}
			
				
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return Response.status(Status.ACCEPTED).entity(jsonarray.toString()).build();
	
	
	
	}

@GET
@Path("saveDeviceId")
@Produces(MediaType.APPLICATION_JSON)
/**
 * 
 * Saves device id and device type
 * 
 * @param userid
 * @param deviceid
 * @param devicetype
 * @return
 */
public Response saveDeviceId(@QueryParam("userid") String userid,@QueryParam("deviceid") String deviceid,@QueryParam("devicetype") String devicetype)
{
	
	try
	{
		//new TblUserMaster(userId, userName, userUsername, userEmailId, userPassword, userDeviceType, userDeviceId, userStatus)
		String whereclauseDeviceId =  "userId='" + userid + "' ";
		
		List<TblUserMaster> wherClause1=QuickUtil.retrieveWherClause(new TblUserMaster(), "TblUserMaster", whereclauseDeviceId);
		if(wherClause1.size()>0)
		{
			
			System.out.println("Whercl: "+whereclauseDeviceId);
			System.out.println("DeviceId :"+deviceid);
			TblUserMaster tum=new TblUserMaster();
			tum=wherClause1.get(0);
			tum.setUserDeviceId(deviceid);
			tum.setUserDeviceType(devicetype);
			QuickUtil.updateToOld(tum);
		}
		else
		{
			String result="Fail";
			return Response.status(200).entity(result).build();
		}
			
				
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	String result="Success";
	return Response.status(200).entity(result).build();
		
	}

@GET
@Path("productMaster")
@Produces(MediaType.APPLICATION_JSON)
/**
 * Displays productlist by product id
 * @param prodId
 * @return
 */
public Response productMaster(@QueryParam("prodId") String prodId)
{
	JSONArray jsonarray=new JSONArray();
	JSONObject json =null;
	try
	{
		String whereclauseProdList =  "productId='" + prodId + "' ";
		List<TblProductMaster> wherClause1=QuickUtil.retrieveWherClause(new TblProductMaster(), "TblProductMaster", whereclauseProdList);
		if(wherClause1.size()>0)
		{
			int ind;
			for(ind=0; ind<wherClause1.size();ind++)
			{
				TblProductMaster tbltpm=wherClause1.get(ind);
				json=new JSONObject();
				json.put("ProdId", tbltpm.getProductId());
				json.put("ProdName", tbltpm.getProductName());
				json.put("Aliasname", tbltpm.getProductAliasname());
				json.put("Inventory", tbltpm.getProductInventory());
				json.put("Image", tbltpm.getProductImage());
				json.put("BrandId", tbltpm.getBrandId());
				json.put("CategoryId", tbltpm.getCategoryId());
				json.put("Price", tbltpm.getProductPrice());
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
@Path("productMasterAll")
@Produces(MediaType.APPLICATION_JSON)
/**
 * 
 * Display all Product list
 * @return
 */
public Response productMasterAll()
{
	
	JSONObject json = null;
	JSONArray jsonarr=new JSONArray();
	String sql="SELECT a.product_id, a.product_name, a.brand_id, c.brand_name,c.brand_image_name, a.product_aliasname, a.product_image, a.product_inventory, a.product_image_name, a.product_price, a.category_id, b.category_name  FROM tbl_product_master a INNER JOIN tbl_category_master b ON a.category_id=b.category_id INNER JOIN tbl_brand_master c ON c.brand_id =a.brand_id";
	List createSQLQuery = QuickUtil.CreateSQLQuery(sql);
	
	
	for(int i=0;i<createSQLQuery.size();i++)
	{
		Object[] row=(Object[]) createSQLQuery.get(i);
		String productid=(String) row[0];//prod id
		String product_name=(String)row[1];
		Integer brand_id=(Integer) row[2];
		String brand_name=(String)row[3];
		String brand_image_name=(String)row[4];
		String product_aliasname=(String)row[5];
		String product_image=(String)row[6];
		Integer product_inventory=(Integer)row[7];
		String product_image_name=(String)row[8];
		Double product_price=(Double)row[9];
		Integer category_id=(Integer)row[10];
		String category_name=(String)row[11];
		
		String brandImagePath=CommonUtil.ImgPathWS+brand_image_name;
		
		json=new JSONObject();
		json.put("ProdId", productid);
		json.put("ProdName", product_name);
		json.put("Aliasname", product_aliasname);
		json.put("Inventory", product_inventory);
		json.put("ProductImage", product_image);
		json.put("ProductImageName", product_image_name);
		json.put("BrandId", brand_id);
		json.put("BrandName", brand_image_name);
		json.put("BrandImage", brand_name);
		json.put("BrandImagePath", brandImagePath);
	    json.put("CategoryId", category_id);
		json.put("categoryName", category_name);
		json.put("Price", product_price);
		jsonarr.put(json);
	
	
	}
	
	return Response.status(Status.ACCEPTED).entity(jsonarr.toString()).build();
}

@GET
@Path("serchProduct")
@Produces(MediaType.APPLICATION_JSON)

public Response serchProduct(@QueryParam("userid") String userid,@QueryParam("searchText") String searchText,@QueryParam("categoryId") String categoryId,@QueryParam("brandId") String brandId)
{
	

	JSONObject json = null;
	JSONArray jsonarr=new JSONArray();
	String sql=null;
try 
{
	
		 if(brandId!=null && categoryId==null)
		{
			//System.out.println("Brand: "+brandId);
			sql="SELECT a.product_id, a.product_name, a.brand_id, c.brand_name, a.product_aliasname, a.product_image, a.product_inventory, a.product_image_name, a.product_price, a.category_id, b.category_name FROM tbl_product_master a INNER JOIN tbl_category_master b ON a.category_id=b.category_id INNER JOIN tbl_brand_master c ON c.brand_id =a.brand_id WHERE a.product_id IN ( SELECT product_id FROM tbl_product_master WHERE product_name LIKE '%"+searchText+"%' AND brand_id='"+brandId+"' )";
		}
		else if(categoryId!=null && brandId==null)
		{
			//System.out.println("categoryId : "+categoryId);
	        sql="SELECT a.product_id, a.product_name, a.brand_id, c.brand_name, a.product_aliasname, a.product_image, a.product_inventory, a.product_image_name, a.product_price, a.category_id, b.category_name FROM tbl_product_master a INNER JOIN tbl_category_master b ON a.category_id=b.category_id INNER JOIN tbl_brand_master c ON c.brand_id =a.brand_id WHERE a.product_id IN ( SELECT product_id FROM tbl_product_master WHERE product_name LIKE '%"+searchText+"%' AND category_id ='"+categoryId+"')";
		}
		else if(brandId!=null && categoryId!=null)
	 	{
			sql="SELECT a.product_id, a.product_name, a.brand_id, c.brand_name, a.product_aliasname, a.product_image, a.product_inventory, a.product_image_name, a.product_price, a.category_id, b.category_name FROM tbl_product_master a INNER JOIN tbl_category_master b ON a.category_id=b.category_id INNER JOIN tbl_brand_master c ON c.brand_id =a.brand_id WHERE a.product_id IN ( SELECT product_id FROM tbl_product_master WHERE product_name LIKE '%"+searchText+"%' AND brand_id='"+brandId+"' AND category_id ='"+categoryId+"')";
	 	}
		 //System.out.println(sql);
			List createSQLQuery = QuickUtil.CreateSQLQuery(sql);
			
				
			for(int i=0;i<createSQLQuery.size();i++)
			{
				Object[] row=(Object[]) createSQLQuery.get(i);
				String productid=(String) row[0];//prod id
				String product_name=(String)row[1];
				Integer brand_id=(Integer) row[2];
				String brand_name=(String)row[3];
				String product_aliasname=(String)row[4];
				String product_image=(String)row[5];
				Integer product_inventory=(Integer)row[6];
				String product_image_name=(String)row[7];
				Double product_price=(Double)row[8];
				Integer category_id=(Integer)row[9];
				String category_name=(String)row[10];
				
				json=new JSONObject();
				json.put("ProdId", productid);
				json.put("ProdName", product_name);
				json.put("Aliasname", product_aliasname);
				json.put("Inventory", product_inventory);
				json.put("Image", product_image);
				json.put("ImageName", product_image_name);
				json.put("BrandId", brand_id);
				json.put("BrandName", brand_name);
			    json.put("CategoryId", category_id);
				json.put("categoryName", category_name);
				json.put("Price", product_price);
				jsonarr.put(json);
			
			}
} 
catch (Exception e) {
	// TODO Auto-generated catch block
	System.out.println("Error");
	e.printStackTrace();
}
	
	
	return Response.status(Status.ACCEPTED).entity(jsonarr.toString()).build();
}

@GET
@Path("serchProductWithFeature")
@Produces(MediaType.APPLICATION_JSON)
/**
 * FINAL 
 * Search product by filtering
 * @param userid
 * @param searchText
 * @param categoryId
 * @param brandId
 * @param featureId
 * @return
 */
public Response serchProductWithFeature(@QueryParam("userid") String userid,@QueryParam("searchText") String searchText,@QueryParam("categoryId") String categoryId,@QueryParam("brandId") String brandId,@QueryParam("featureId") String featureId)
{
	

	JSONObject json = null;
	JSONArray jsonarr=new JSONArray();
	
	String sql=null;
	String q2=null;
	String q3=null;
	
	JSONArray featureArr=null;
	try 
	{
		
		String q1= "SELECT a.product_id, a.product_name, a.brand_id, a.product_aliasname, a.product_image, a.product_inventory, a.product_image_name, a.product_price, a.category_id, b.feature_id FROM tbl_product_master a INNER JOIN tbl_product_features b ON a.product_id=b.product_id INNER JOIN tbl_feature_master c ON b.feature_id=c.feature_id WHERE "; 
		if(featureId!=null)
		{
		featureArr = new JSONArray(featureId);
		JSONObject objfeature = null;
		
		 for (int j=0;j<featureArr.length();j++)
		 {	 
			 objfeature = featureArr.getJSONObject(j);
			String feature=(String) objfeature.get("featureId");
		System.out.println(feature);
			
		
		 q2= "b.feature_id="+feature ;
		 }
		}
		else
		{
			q2="";
		}
		 if( featureId!=null && brandId!=null && categoryId==null)
			{	
	     q3=" AND  a.product_id IN ( SELECT product_id FROM tbl_product_master WHERE product_name LIKE '%"+searchText+"%' AND brand_id='"+brandId+"')";
			}
		 else if(featureId!=null &&categoryId!=null && brandId==null)
		 {
			 q3=" AND  a.product_id IN ( SELECT product_id FROM tbl_product_master WHERE product_name LIKE '%"+searchText+"%' AND category_id ='"+categoryId+"')";
		 }
		 else if(brandId!=null && categoryId!=null && featureId!=null )
		 {
			 q3=" AND  a.product_id IN ( SELECT product_id FROM tbl_product_master WHERE product_name LIKE '%"+searchText+"%' AND brand_id='"+brandId+"' AND category_id ='"+categoryId+"')";
		 }
		 else if(featureId!=null && categoryId==null && brandId==null)
		 {
			 q3=" AND  a.product_id IN ( SELECT product_id FROM tbl_product_master WHERE product_name LIKE '%"+searchText+"%')";
		 }
		 else if(brandId!=null && featureId==null && categoryId==null)
		 {
			 q3="  a.product_id IN ( SELECT product_id FROM tbl_product_master WHERE product_name LIKE '%"+searchText+"%' AND brand_id='"+brandId+"')";
		 }
		 else if(categoryId!=null && featureId==null &&brandId==null)
		 {
			 q3="  a.product_id IN ( SELECT product_id FROM tbl_product_master WHERE product_name LIKE '%"+searchText+"%' AND category_id ='"+categoryId+"')";
		 }
		 else if(featureId==null && categoryId==null && brandId==null)
		 {
			 q3="   a.product_id IN ( SELECT product_id FROM tbl_product_master WHERE product_name LIKE '%"+searchText+"%')";
		 }
		
		sql=q1+q2+q3;	
		System.out.println(sql);
		List createSQLQuery = QuickUtil.CreateSQLQuery(sql);
		for(int i=0;i<createSQLQuery.size();i++){
			Object[] row=(Object[]) createSQLQuery.get(i);
			String productid=(String) row[0];
			String product_name=(String)row[1];
			Integer brand_id=(Integer) row[2];
			String product_aliasname=(String)row[3];
			String product_image=(String)row[4];
			Integer product_inventory=(Integer)row[5];
			String product_image_name=(String)row[6];
			Double product_price=(Double)row[7];
			Integer category_id=(Integer)row[8];
			Integer feature_id=(Integer)row[9];
			
			json=new JSONObject();
			json.put("ProdId", productid);
			json.put("ProdName", product_name);
			json.put("Aliasname", product_aliasname);
			json.put("Inventory", product_inventory);
			json.put("Image", product_image);
			json.put("ImageName", product_image_name);
			json.put("BrandId", brand_id);
			json.put("CategoryId", category_id);
			json.put("FeatureId", feature_id);
			json.put("Price", product_price);
			jsonarr.put(json);
		
		}
	
	}
	catch(Exception e)
	{
		System.out.println("Error");
		e.printStackTrace();
	}
	return Response.status(Status.ACCEPTED).entity(jsonarr.toString()).build();
}


@GET
@Path("partyMasterAdd")
@Produces(MediaType.APPLICATION_JSON)
/**
 * 
 * Adds party master
 * 
 * @param partyPrefix
 * @param partyFname
 * @param partyMname
 * @param partyLname
 * @param partyName
 * @param partyEmailid
 * @param partyPhone
 * @param partyAddress1
 * @param partyAddress2
 * @param partyPincode
 * @param userId
 * @return
 */
public Response partyMasterAdd(@QueryParam("partyPrefix") String partyPrefix,
		@QueryParam("partyFname") String partyFname,
		@QueryParam("partyMname") String partyMname,
		@QueryParam("partyLname") String partyLname,
		@QueryParam("partyName") String partyName,
		@QueryParam("partyEmailid") String partyEmailid,
		@QueryParam("partyPhone") String partyPhone,
		@QueryParam("partyAddress1") String partyAddress1,
		@QueryParam("partyAddress2") String partyAddress2,
		@QueryParam("partyPincode") String partyPincode,
		@QueryParam("userId") String userId)
{
try
{
	String whereclauseverify = "userId='"+ userId +"'";

System.out.println("whereclauseverify: "+whereclauseverify);
List<TblUserMaster> wherClause1=QuickUtil.retrieveWherClause(new TblUserMaster(), "TblUserMaster", whereclauseverify);
if(wherClause1.size()>0)
{
	/*System.out.println("partyPrefix :"+partyPrefix);
	System.out.println("partyFname: "+partyFname);
	System.out.println("partyMname: "+partyMname);
	System.out.println("partyLname: "+partyLname);
	System.out.println("partyName: "+partyName);
	System.out.println("partyEmailid: "+partyEmailid);
	System.out.println("partyPhone: "+partyPhone);
	System.out.println("partyAddress1: "+partyAddress1);
	System.out.println("partyAddress2: "+partyAddress2);
	System.out.println("partyPincode: "+partyPincode);
	System.out.println("userId: "+userId);
	*/
	TblPartyMaster tpm=new TblPartyMaster();
	
	
	String partyId=QuickUtil.doGetNextPK("TblPartyMaster");
	tpm.setPartyId(partyId);
	tpm.setPartyPrefix(partyPrefix);
	tpm.setPartyFname(partyFname);
	tpm.setPartyMname(partyMname);
	tpm.setPartyLname(partyLname);
	partyName=partyPrefix+" "+partyFname+" "+partyMname+" " +partyLname;
	tpm.setPartyName(partyName);
	tpm.setPartyEmailid(partyEmailid);
	tpm.setPartyPhone(partyPhone);
	tpm.setPartyAddress1(partyAddress1);
	tpm.setPartyAddress2(partyAddress2);
	tpm.setPartyPincode(Integer.parseInt(partyPincode));
	tpm.setUserId(userId);
	
	
	QuickUtil.saveToNew(tpm);
}
else
{
	String result="Fail";
	return Response.status(200).entity(result).build();
}
}
catch(Exception e)
{
	e.printStackTrace();
}
String result="Success";
return Response.status(200).entity(result).build();


}

@GET
@Path("partyMasterEdit")
@Produces(MediaType.APPLICATION_JSON)
/**
 * 
 * Edits party master
 * 
 * @param partyId
 * @param partyPrefix
 * @param partyFname
 * @param partyMname
 * @param partyLname
 * @param partyName
 * @param partyEmailid
 * @param partyPhone
 * @param partyAddress1
 * @param partyAddress2
 * @param partyPincode
 * @param userId
 * @return
 */
public Response partyMasterEdit(@QueryParam("partyId") String partyId,
		@QueryParam("partyPrefix") String partyPrefix,
		@QueryParam("partyFname") String partyFname,
		@QueryParam("partyMname") String partyMname,
		@QueryParam("partyLname") String partyLname,
		@QueryParam("partyName") String partyName,
		@QueryParam("partyEmailid") String partyEmailid,
		@QueryParam("partyPhone") String partyPhone,
		@QueryParam("partyAddress1") String partyAddress1,
		@QueryParam("partyAddress2") String partyAddress2,
		@QueryParam("partyPincode") String partyPincode,
		@QueryParam("userId") String userId)
{
	try
	{
		String whereclauseverify = "userId='"+ userId +"'";

		List<TblUserMaster> wherClause1=QuickUtil.retrieveWherClause(new TblUserMaster(), "TblUserMaster", whereclauseverify);
		if(wherClause1.size()>0)
		{//new TblUserMaster(userId, userName, userUsername, userEmailId, userPassword, userDeviceType, userDeviceId, userStatus)
		String whereclauseEdit =  "partyId='"+ partyId +"'";
		
		List<TblPartyMaster> wherClause2=QuickUtil.retrieveWherClause(new TblPartyMaster(), "TblPartyMaster", whereclauseEdit);
		if(wherClause2.size()>0)
		{
			
			
			TblPartyMaster tpm=new TblPartyMaster();
			tpm=wherClause2.get(0);
			tpm.setPartyPrefix(partyPrefix);
			tpm.setPartyFname(partyFname);
			tpm.setPartyMname(partyMname);
			tpm.setPartyLname(partyLname);
			partyName=partyPrefix+" "+partyFname+" "+partyMname+" " +partyLname;
			tpm.setPartyName(partyName);
			tpm.setPartyEmailid(partyEmailid);
			tpm.setPartyPhone(partyPhone);
			tpm.setPartyAddress1(partyAddress1);
			tpm.setPartyAddress2(partyAddress2);
			tpm.setPartyPincode(Integer.parseInt(partyPincode));
			tpm.setUserId(userId);
			
			QuickUtil.updateToOld(tpm);
		}
		else
		{
			String result="Fail";
			return Response.status(200).entity(result).build();
		}
			
				
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	String result="Success";
	return Response.status(200).entity(result).build();
}

@GET
@Path("partyMasterRetrieveAllById")
@Produces(MediaType.APPLICATION_JSON)
/**
 * retrieve party master by Id
 * 
 * @param partyId
 * @param userId
 * @return
 */
public Response partyMasterRetrieveAllById(@QueryParam("partyId") String partyId,@QueryParam("userId") String userId)
{
	JSONArray jsonarray=new JSONArray();
	JSONObject json =null;
	try
	{

		String whereclauseverify = "userId='"+ userId +"'";


		List<TblUserMaster> wherClause1=QuickUtil.retrieveWherClause(new TblUserMaster(), "TblUserMaster", whereclauseverify);
		if(wherClause1.size()>0)
		{
		
		String whereclausePartyList =   "partyId='"+ partyId+"' AND userId='" + userId + "' ";
		List<TblPartyMaster> wherClause2=QuickUtil.retrieveWherClause(new TblPartyMaster(), "TblPartyMaster", whereclausePartyList);
		if(wherClause2.size()>0)
		{
			int ind;
			for(ind=0; ind<wherClause2.size();ind++)
			{
				TblPartyMaster tpm=wherClause2.get(ind);
				json=new JSONObject();
				json.put("Prefix", tpm.getPartyPrefix());
				json.put("Fname", tpm.getPartyFname());
				json.put("Mname", tpm.getPartyMname());
				json.put("Lname", tpm.getPartyLname());
				json.put("Name", tpm.getPartyName());
				json.put("Emailid", tpm.getPartyEmailid());
				json.put("Phone", tpm.getPartyPhone());
				json.put("Address1", tpm.getPartyAddress1());
				json.put("Address2", tpm.getPartyAddress2());
				json.put("PinCode", tpm.getPartyPincode());
				
				
				jsonarray.put(ind,json);
			}
			
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
@Path("partyMasterRetrieveAll")
@Produces(MediaType.APPLICATION_JSON)
/**
 * 
 * Retrieve all party master
 * 
 * @param userId
 * @return
 */
public Response partyMasterRetrieveAll(@QueryParam("userId") String userId)
{
	JSONArray jsonarray=new JSONArray();
	JSONObject json =null;
	try
	{

		String whereclauseverify = "userId='"+ userId +"'";


		List<TblUserMaster> wherClause1=QuickUtil.retrieveWherClause(new TblUserMaster(), "TblUserMaster", whereclauseverify);
		if(wherClause1.size()>0)
		{
		List<TblPartyMaster> wherClause2=QuickUtil.retrieveWherClauseOrderby(new TblPartyMaster(), "TblPartyMaster", "order by partyId DESC");
		if(wherClause2.size()>0)
		{
			int ind;
			for(ind=0; ind<wherClause2.size();ind++)
			{
				TblPartyMaster tpm=wherClause2.get(ind);
				json=new JSONObject();
				json.put("Prefix", tpm.getPartyPrefix());
				json.put("Fname", tpm.getPartyFname());
				json.put("Mname", tpm.getPartyMname());
				json.put("Lname", tpm.getPartyLname());
				json.put("Name", tpm.getPartyName());
				json.put("Emailid", tpm.getPartyEmailid());
				json.put("Phone", tpm.getPartyPhone());
				json.put("Address1", tpm.getPartyAddress1());
				json.put("Address2", tpm.getPartyAddress2());
				json.put("PinCode", tpm.getPartyPincode());
				json.put("partyId", tpm.getPartyId());
				jsonarray.put(ind,json);
			}
			
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
@Path("billMasterAdd")
@Produces(MediaType.APPLICATION_JSON)
/**
 * 
 * Add bill
 * 
 * @param billDate
 * @param billNote
 * @param Discount
 * @param userId
 * @param ProductList
 * @param billType
 * @param partyId
 * @param productId
 * @param prodBillRate
 * @param prodBillQuantity
 * @return
 */
public Response billMasterAdd(@QueryParam("billDate") String billDate,
		@QueryParam("billNote") String billNote,
		@QueryParam("Discount") String Discount,
		@QueryParam("userId") String userId,
		@QueryParam("ProductList") String ProductList,
		@QueryParam("billType") String billType,
		@QueryParam("partyId") String partyId,
		@QueryParam("productId") String productId,
		@QueryParam("prodBillRate") String prodBillRate,
		@QueryParam("prodBillQuantity") String prodBillQuantity)
{
		
	Double billSubTotal=0.0;
	Double TotalprodBillAmount=0.0;

	JSONArray productArr=null;
			try
				{
				boolean loginVerify2 = CommonUtil.loginVerify(userId);
			
				
				if(loginVerify2==true)
				{
				/*String whereclauseVerify =   "partyId='"+ partyId+"'";
				List<TblPartyMaster> partyList=QuickUtil.retrieveWherClause(new TblPartyMaster(), "TblPartyMaster", whereclauseVerify);
				if(partyList.size()>0)
				{*/
				String billId=QuickUtil.doGetNextPK("TblBillMaster");
				
						productArr = new JSONArray(ProductList);
						//System.out.println("ProductList : "+productArr.toString());
						//System.out.println("productArrLength: "+productArr.length());
						JSONObject objfeature = null;
			
						//Double prodBillAmnt=null;
						for (int j=0;j<productArr.length();j++)
						{	 
							objfeature = productArr.getJSONObject(j);
				 
							String prodId=objfeature.getString("productId");
							//Double rate=Double.parseDouble("prodBillRate");
							String Rate=objfeature.getString("prodBillRate");
							Integer quantity=Integer.parseInt(objfeature.getString("prodBillQuantity"));
					
							//System.out.println("prodId: "+prodId);
							//System.out.println("Rate: "+Rate);
							//System.out.println("quantity: "+quantity);
							TblProductBillPurchase pbp=new TblProductBillPurchase();
							
					
							pbp.setBillId(billId);
							
							pbp.setProductId(prodId);
					
					        Double rate=Double.parseDouble(Rate);
							pbp.setProdBillRate(rate);
							
							
							pbp.setProdBillQuantity(quantity);
							
						    Double prodBillAmount=rate*quantity;
						   TotalprodBillAmount=TotalprodBillAmount+ prodBillAmount;
							pbp.setProdBillAmount(prodBillAmount);
							
							QuickUtil.saveToNew(pbp);
										
						}	
							
						TblBillMaster tbm=new TblBillMaster();
							
							tbm.setBillId(billId);
											
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
							Date parse = simpleDateFormat.parse(billDate);
							tbm.setBillDate(parse);
							tbm.setBillNote(billNote);
							
							billSubTotal=billSubTotal+TotalprodBillAmount;
							tbm.setBillSubTotal(billSubTotal);
							
							Double discount=Double.valueOf(Discount);
							tbm.setBillDiscount(discount);
							
							Double billTotal=billSubTotal-discount;
							tbm.setBillTotal(billTotal);
							
							Double billTax=0.0;
							tbm.setBillTax(billTax);
							
							Double billTaxTotal=billTotal+billTax;
							tbm.setBillTaxTotal(billTaxTotal);
							
							Double otherCharges=0.0;
							tbm.setBillOtherCharges(0);
							
							Double billFinalTotal=billTaxTotal+otherCharges;
							tbm.setBillFinalTotal(billFinalTotal);
							
							tbm.setPartyId(partyId);
							tbm.setBillStatus("ACTIVE");
							tbm.setBillType(billType);
							
							QuickUtil.saveToNew(tbm);
				}
				else
				{
					String result="Fail";
					return Response.status(200).entity(result).build();
				}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				String result="Success";
				return Response.status(200).entity(result).build();
				}

@GET
@Path("billMasterEdit")
@Produces(MediaType.APPLICATION_JSON)
/**
 * Edit Bill
 * 
 * @param billDate
 * @param billNote
 * @param Discount
 * @param userId
 * @param ProductList
 * @param billType
 * @param partyId
 * @param productId
 * @param prodBillRate
 * @param prodBillQuantity
 * @param billId
 * @return
 */
public Response billMasterEdit(@QueryParam("billDate") String billDate,
		@QueryParam("billNote") String billNote,
		@QueryParam("Discount") String Discount,
		@QueryParam("userId") String userId,
		@QueryParam("ProductList") String ProductList,
		@QueryParam("billType") String billType,
		@QueryParam("partyId") String partyId,
		@QueryParam("productId") String productId,
		@QueryParam("prodBillRate") String prodBillRate,
		@QueryParam("prodBillQuantity") String prodBillQuantity,
		@QueryParam("billId") String billId)
{
	Double billSubTotal=0.0;
	Double TotalprodBillAmount=0.0;	JSONArray productArr=null;
	try
	{	
	
		
		boolean loginVerify2 = CommonUtil.loginVerify(userId);

		if(loginVerify2==true)
		{
		String wherVerify="partyId='"+partyId+"'";
		List<TblPartyMaster> partyList=QuickUtil.retrieveWherClause(new TblPartyMaster(), "TblPartyMaster", wherVerify);
		if(partyList.size()>0)
		{
		
			String wherBillEdit="billId='"+billId+"'";
			List<TblBillMaster> billMasterList=QuickUtil.retrieveWherClause(new TblBillMaster(), "TblBillMaster", wherBillEdit);
			if(billMasterList.size()>0)
			{
				String wherPurchaseEdit="billId='"+billId+"'";
				List<TblProductBillPurchase> billPurchaseList=QuickUtil.retrieveWherClause(new TblProductBillPurchase(), "TblProductBillPurchase", wherPurchaseEdit);
				if(billPurchaseList.size()>0)
				{
				
				//Double prodBillAmnt=null;
					TblProductBillPurchase pbp=new TblProductBillPurchase(); 
					for(int k=0;k<billPurchaseList.size();k++)
					{
						
						pbp=billPurchaseList.get(k);
						QuickUtil.deleteFromDB("TblProductBillPurchase", "billId='" + billId + "'");
					}
					productArr = new JSONArray(ProductList);
					//System.out.println("ProductList : "+productArr.toString());
					//System.out.println("productArrLength: "+productArr.length());
					JSONObject objfeature = null;
		
					for (int j=0;j<productArr.length();j++)
					{	
					
					objfeature = productArr.getJSONObject(j);
		 
					String prodId=objfeature.getString("productId");
					//Double rate=Double.parseDouble("prodBillRate");
					String Rate=objfeature.getString("prodBillRate");
					Integer quantity=Integer.parseInt(objfeature.getString("prodBillQuantity"));
			
					//System.out.println("prodId: "+prodId);
					//System.out.println("Rate: "+Rate);
					//System.out.println("quantity: "+quantity);
					
					
					pbp.setBillId(billId);
					
					pbp.setProductId(prodId);
			
			        Double rate=Double.parseDouble(Rate);
					pbp.setProdBillRate(rate);
					
					
					pbp.setProdBillQuantity(quantity);
					
				    Double prodBillAmount=rate*quantity;
				   TotalprodBillAmount=TotalprodBillAmount + prodBillAmount;
					pbp.setProdBillAmount(prodBillAmount);
					
					QuickUtil.saveToNew(pbp);
				}
				
				TblBillMaster tbm=new TblBillMaster();
				tbm=billMasterList.get(0);
				
				tbm.setBillId(billId);
								
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Date parse = simpleDateFormat.parse(billDate);
				tbm.setBillDate(parse);
				tbm.setBillNote(billNote);
				
				billSubTotal=billSubTotal+TotalprodBillAmount;
				tbm.setBillSubTotal(billSubTotal);
				
				Double discount=Double.valueOf(Discount);
				tbm.setBillDiscount(discount);
				
				Double billTotal=billSubTotal-discount;
				tbm.setBillTotal(billTotal);
				
				Double billTax=0.0;
				tbm.setBillTax(billTax);
				
				Double billTaxTotal=billTotal+billTax;
				tbm.setBillTaxTotal(billTaxTotal);
				
				Double otherCharges=0.0;
				tbm.setBillOtherCharges(0);
				
				Double billFinalTotal=billTaxTotal+otherCharges;
				tbm.setBillFinalTotal(billFinalTotal);
				
				tbm.setPartyId(partyId);
				tbm.setBillStatus("ACTIVE");
				tbm.setBillType(billType);
				
				QuickUtil.updateToOld(tbm);
				
			}
		}
	}
		}	else
		{
			String result="Fail";
			return Response.status(200).entity(result).build();
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	String result="Success";
	return Response.status(200).entity(result).build();
	}

				}
