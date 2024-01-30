package com.billingwebapp.commons;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import com.billingwebapp.domain.TblBrandMaster;
import com.billingwebapp.domain.TblCategoryMaster;
import com.billingwebapp.domain.TblFeatureMaster;
import com.billingwebapp.domain.TblFeatureTypeMaster;
import com.billingwebapp.domain.TblProductFeatures;
import com.billingwebapp.domain.TblProductInventory;
import com.billingwebapp.domain.TblProductMaster;
import com.billingwebapp.util.QuickUtil;
import com.sun.research.ws.wadl.Request;


public class CommonUtil {
	   
	public static String Imagepath="C:\\BillingWebAppImageUpload\\";
	public static JSONArray getAllFeatureTypeMaster(){
		 
		 List<TblFeatureTypeMaster> tftm = QuickUtil.retrieveALLwithHB(new TblFeatureTypeMaster(), "TblFeatureTypeMaster", "");//("select * from Student");

			JSONObject json = null;
			JSONArray jsonarr = null;
			
			jsonarr = new JSONArray();
			
			if (tftm.size() > 0)
			{
				int intRow = 0;
				
				
				//ArrayList<HashMap> list=new ArrayList<HashMap>();
				//list.toString(); 
				
				for (TblFeatureTypeMaster tftmtemp : tftm) {
					
					//map=new HashMap<String,String>();
					json = new JSONObject();
					
					//HashMap <String,HashMap> mapbase = new HashMap<String,HashMap>();
					json.put("id", tftmtemp.getFeatureTypeId());	
					json.put("aliasname", tftmtemp.getFeatureTypeAliasname());
					json.put("typename", tftmtemp.getFeatureTypeName());
					
					jsonarr.put(intRow, json);
					
					
					intRow++;
					
				}
				
				
			   /* System.out.println("ListJson: "+ json.toString());
				System.out.println("Listarray: "+ jsonarr.toString());*/
				
				
				return jsonarr;
			}
			return null;
	 }


public static JSONArray getAllFeatureTypeMasterEdit(String catpkid) {
	
	
 
	 List<TblFeatureTypeMaster> tftm = QuickUtil.retrieveWherClause(new TblFeatureTypeMaster(), "TblFeatureTypeMaster", "featureTypeId='" + catpkid + "'");//("select * from TblFeatureTypeMaster");

			JSONObject json = null;
			JSONArray jsonarr = null;
			
			jsonarr = new JSONArray();
			
			if (tftm.size() > 0)
			{
				int intRow = 0;  
				
				for (TblFeatureTypeMaster tftmtemp : tftm) {
					
					
					json = new JSONObject();
					
					
					json.put("id", tftmtemp.getFeatureTypeId());	
					json.put("aliasname", tftmtemp.getFeatureTypeAliasname());
					json.put("typename", tftmtemp.getFeatureTypeName());
					
					jsonarr.put(intRow, json);
					
					
					intRow++;
					
				}
				
				
								
	 	return jsonarr;
	 }
	 return jsonarr;
	 }

public static JSONArray getAllFeatureMaster(){
	 
	 List<TblFeatureMaster> tfm = QuickUtil.retrieveALLwithHB(new TblFeatureMaster(), "TblFeatureMaster", "");

		JSONObject json = null;
		JSONArray jsonarr = null;
		
		jsonarr = new JSONArray();
		
		if (tfm.size() > 0)
		{
			int intRow = 0;
			
			
			//ArrayList<HashMap> list=new ArrayList<HashMap>();
			//list.toString(); 
			
			for (TblFeatureMaster fmtemp : tfm) {
				
				//map=new HashMap<String,String>();
				json = new JSONObject();
				
				//HashMap <String,HashMap> mapbase = new HashMap<String,HashMap>();
				json.put("id", fmtemp.getFeatureId());	
				json.put("aliasname", fmtemp.getFeatureAliasname());
				json.put("name", fmtemp.getFeatureName());
				json.put("type", fmtemp.getFeatureTypeId());
				jsonarr.put(intRow, json);
				
				
				intRow++;
				
			}
			
			
			/*System.out.println("ListJson: "+ json.toString());
			System.out.println("Listarray: "+ jsonarr.toString());
			*/
			
			return jsonarr;
		}
		return null;
}


public static JSONArray getAllFeatureMasterEdit(String fmpkid2) {



List<TblFeatureMaster> tfm = QuickUtil.retrieveWherClause(new TblFeatureMaster(), "TblFeatureMaster", "featureId='" + fmpkid2 + "'");

		JSONObject json = null;
		JSONArray jsonarr = null;
		
		jsonarr = new JSONArray();
		
		if (tfm.size() > 0)
		{
			int intRow = 0;  
			
			for (TblFeatureMaster fmtemp : tfm) {
				
				
				json = new JSONObject();
				
				
				json.put("id", fmtemp.getFeatureId());	
				json.put("aliasname", fmtemp.getFeatureAliasname());
				json.put("name", fmtemp.getFeatureName());
				json.put("type", fmtemp.getFeatureTypeId());
				jsonarr.put(intRow, json);
				
				
				intRow++;
				
			}
			
			
							
	return jsonarr;
}
return jsonarr;
}

public static JSONArray getAllCategory(){
	 
	 List<TblCategoryMaster> ctm = QuickUtil.retrieveALLwithHB(new TblCategoryMaster(), "TblCategoryMaster", "");

		JSONObject json = null;
		JSONArray jsonarr = null;
		
		jsonarr = new JSONArray();
		
		if (ctm.size() > 0)
		{
			int intRow = 0;
			
			
			//ArrayList<HashMap> list=new ArrayList<HashMap>();
			//list.toString(); 
			
			for (TblCategoryMaster ctmtemp : ctm) {
				
				//map=new HashMap<String,String>();
				json = new JSONObject();
				
				//HashMap <String,HashMap> mapbase = new HashMap<String,HashMap>();
				json.put("id", ctmtemp.getCategoryId());	
				json.put("aliasname", ctmtemp.getCategoryAliasname());
				json.put("name", ctmtemp.getCategoryName());
				
				jsonarr.put(intRow, json);
				
				
				intRow++;
				
			}
			
			
			/*System.out.println("ListJson: "+ json.toString());
			System.out.println("Listarray: "+ jsonarr.toString());
			*/
			
			return jsonarr;
		}
		return null;
}


public static JSONArray getAllCategoryEdit(String catpkid) {



List<TblCategoryMaster> ctm = QuickUtil.retrieveWherClause(new TblCategoryMaster(), "TblCategoryMaster", "categoryId='" + catpkid + "'");//("select * from TblCategoryMaster");

		JSONObject json = null;
		JSONArray jsonarr = null;
		
		jsonarr = new JSONArray();
		
		if (ctm.size() > 0)
		{
			int intRow = 0;  
			
			for (TblCategoryMaster ctmtemp : ctm) {
				
				
				json = new JSONObject();
				
				
				json.put("id", ctmtemp.getCategoryId());	
				json.put("aliasname", ctmtemp.getCategoryAliasname());
				json.put("name", ctmtemp.getCategoryName());
				
				
				jsonarr.put(intRow, json);
				
				
				intRow++;
				
			}
			
			
							
	return jsonarr;
}
return jsonarr;
}


public static JSONArray getAllBrand(){
	 
	 List<TblBrandMaster> bm = QuickUtil.retrieveALLwithHB(new TblBrandMaster(), "TblBrandMaster", "");

		JSONObject json = null;
		JSONArray jsonarr = null;
		
		jsonarr = new JSONArray();
		
		if (bm.size() > 0)
		{
			int intRow = 0;
			
			
			//ArrayList<HashMap> list=new ArrayList<HashMap>();
			//list.toString(); 
			
			for (TblBrandMaster bmtemp : bm) {
				
				//map=new HashMap<String,String>();
				json = new JSONObject();
				
				//HashMap <String,HashMap> mapbase = new HashMap<String,HashMap>();
				json.put("id", bmtemp.getBrandId());	
				json.put("aliasname", bmtemp.getBrandAliasname());
				json.put("brandName", bmtemp.getBrandName());
				
				jsonarr.put(intRow, json);
				
				
				intRow++;
				
			}
			
			
			/*System.out.println("ListJson: "+ json.toString());
			System.out.println("Listarray: "+ jsonarr.toString());
			*/
			
			return jsonarr;
		}
		return null;
}


public static JSONArray getAllBrandEdit(String branpkId) {



List<TblBrandMaster> bm = QuickUtil.retrieveWherClause(new TblBrandMaster(), "TblBrandMaster", "brandId='" + branpkId + "'");//("select * from TblBrandMaster");

		JSONObject json = null;
		JSONArray jsonarr = null;
		
		jsonarr = new JSONArray();
		
		if (bm.size() > 0)
		{
			int intRow = 0;  
			
			for (TblBrandMaster bmtemp : bm) {
				
				
				json = new JSONObject();
				
				
				json.put("id", bmtemp.getBrandId());	
				json.put("aliasname", bmtemp.getBrandAliasname());
				json.put("brandName", bmtemp.getBrandName());
				
				jsonarr.put(intRow, json);
				
				
				intRow++;
				
			}
			
			
							
	return jsonarr;
}
return jsonarr;
}


public static JSONArray getAllProducts(){
	 
	 List<TblProductMaster> pm = QuickUtil.retrieveALLwithHB(new TblProductMaster(), "TblProductMaster", "");

		JSONObject json = null;
		JSONArray jsonarr = null;
		
		jsonarr = new JSONArray();
		
		if (pm.size() > 0)
		{
			int intRow = 0;
			
			
			//ArrayList<HashMap> list=new ArrayList<HashMap>();
			//list.toString(); 
			
			for (TblProductMaster pmtemp : pm) {
				
				//map=new HashMap<String,String>();
				json = new JSONObject();
				
				json.put("id", pmtemp.getProductId() );	
				json.put("productname", pmtemp.getProductName());
				json.put("aliasname", pmtemp.getProductAliasname());
				json.put("brandName", pmtemp.getBrandId());
				json.put("inventory", pmtemp.getProductInventory());
				json.put("idinputuploadfile", pmtemp.getProductImage());
				 
				
				jsonarr.put(intRow, json);
				
				
				intRow++;
				
			}
			
			
			/*System.out.println("ListJson: "+ json.toString());
			System.out.println("Listarray: "+ jsonarr.toString());
			*/
			
			return jsonarr;
		}
		return null;
}


public static JSONArray getAllProductEdit(String pmpkid) {

System.out.println("CommonUtil.getAllProductEdit()");

List<TblProductMaster> pm = QuickUtil.retrieveWherClause(new TblProductMaster(), "TblProductMaster", "productId='" + pmpkid + "'");//("select * from TblBrandMaster");

		JSONObject json = null;
		JSONArray jsonarr = null;
		
		jsonarr = new JSONArray();
		
		if (pm.size() > 0)
		{
			int intRow = 0;  
			
			for (TblProductMaster pmtemp : pm) {
				
				
				json = new JSONObject();
				
				
				json.put("id", pmtemp.getProductId() );	
				json.put("productname", pmtemp.getProductName());
				json.put("aliasname", pmtemp.getProductAliasname());
				json.put("brandName", pmtemp.getBrandId());
				json.put("inventory", pmtemp.getProductInventory());
				json.put("idinputuploadfile", pmtemp.getProductImageName());
				
				
				JSONArray arrforprodfeatures=new JSONArray();
				
				List<TblProductFeatures> retrieveWherClause = QuickUtil.retrieveWherClause(new TblProductFeatures(), "TblProductFeatures", "productId='" + pmpkid + "'");
				for (TblProductFeatures tblProductFeatures : retrieveWherClause) {
					int fId = tblProductFeatures.getFeatureId();
					JSONObject localjson=new JSONObject();
					
					
					List<TblFeatureMaster> retrieveWherClause1 = QuickUtil.retrieveWherClause(new TblFeatureMaster(), "TblFeatureMaster", "featureId='" + fId + "'");
					for (TblFeatureMaster tblFeatureMaster : retrieveWherClause1) {
						int ftId = tblFeatureMaster.getFeatureTypeId();
						String featureName=tblFeatureMaster.getFeatureName();
						
						
					List<TblFeatureTypeMaster> retrieveWherClause2 = QuickUtil.retrieveWherClause(new TblFeatureTypeMaster(), "TblFeatureTypeMaster", "featureTypeId='" + ftId + "'");	
					for (TblFeatureTypeMaster tblFeatureTypeMaster : retrieveWherClause2) 
					{
						String typename=tblFeatureTypeMaster.getFeatureTypeName();
						localjson.put("typename", typename );	
						
					}
						localjson.put("typeid", ftId );	
						localjson.put("name", featureName );	
						
						
					}
					localjson.put("id", fId );
					arrforprodfeatures.put(localjson);	
				}
				
				
				
				json.put("arrforprodfeatures", arrforprodfeatures);
				
				jsonarr.put(intRow, json);
				
				
				intRow++;
				//System.out.println("Hiiiiii");
				System.out.println(jsonarr.toString());
				
			}
			
			
							
	return jsonarr;
}
return jsonarr;
}

public static boolean saveFeature(String product_id,HttpServletRequest request) throws ParseException {
	
	//System.out.println("CommonUtil.saveFeature()");
	try{
	JSONArray objarr = new JSONArray(request.getParameter("getfeature")!=null&&request.getParameter("getfeature")!=""?request.getParameter("getfeature"):"");
	
	
	JSONObject object = null;

    for (int intloop=0;intloop<objarr.length();intloop++){	
    object = objarr.getJSONObject(intloop);
    
    TblProductFeatures objtemp = new TblProductFeatures();
	
	//saving
	objtemp.setProductId(product_id);		
	objtemp.setFeatureId(Integer.parseInt(object.getString("id")));;
	
		
    

	QuickUtil.saveToNew(objtemp);
    
    }
    return true;
}catch(Exception ex){
	//System.out.println("CommonUtil.saveFeature()");
	//System.out.println(ex.getMessage());
	ex.printStackTrace();
	return false;
}

    
}


public static boolean saveInventory(String product_id,HttpServletRequest request) throws ParseException {
	
	//System.out.println("CommonUtil.saveInventory()");
	try{
		//JSONArray objarr = new JSONArray(request.getParameter("inventory")!=null&&request.getParameter("inventory")!=""?request.getParameter("inventory"):"");
		
	/*JSONArray objarr = new JSONArray();
	
	
	JSONObject object = null;

    for (int intloop=0;intloop<objarr.length();intloop++){	
    object = objarr.getJSONObject(intloop);
    */
    TblProductInventory invtemp = new TblProductInventory();
	
	//saving
    invtemp.setProdId(product_id);		
    invtemp.setProdInvInventory(Integer.parseInt(request.getParameter("inventory")));;
	
		
    

	QuickUtil.saveToNew(invtemp);
    
   
    return true;
}catch(Exception ex){
	//System.out.println("CommonUtil.saveFeature()");
	//System.out.println(ex.getMessage());
	ex.printStackTrace();
	return false;
}

    
}


}
