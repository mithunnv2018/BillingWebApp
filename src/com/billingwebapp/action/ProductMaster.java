package com.billingwebapp.action;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONArray;
import org.json.JSONObject;

import com.billingwebapp.commons.CommonUtil;
import com.billingwebapp.domain.TblFeatureMaster;
import com.billingwebapp.domain.TblProductMaster;
import com.billingwebapp.util.QuickUtil;



public class ProductMaster extends DispatchAction{
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//System.out.print("abc");
		
		try {
			
			FileItem uploadfile = uploadfile(mapping, form, request, response);
			long currentTimeMillis = System.currentTimeMillis();

			String filename = String.valueOf(currentTimeMillis + uploadfile.getName());//FieldName());
			List list=new ArrayList();
			if(uploadfile!=null)
			{
				
				byte[] objattach = uploadfile.get();

				FileInputStream fileInputStream = null;

				String projectImagePath =CommonUtil.Imagepath;

				//System.out.println("projectImagePath=" + projectImagePath);
				String savedpath=projectImagePath + filename;
				//System.out.println("Full path2="+savedpath);
				FileOutputStream fileOuputStream = new FileOutputStream(
						savedpath);
				fileOuputStream.write(objattach);
				fileOuputStream.close();
				
				list.add(savedpath);
				

			}
			
			
			String imgpath=CommonUtil.Imagepath;
			String savedpath=imgpath + filename;
			
			
			
			TblProductMaster tpm=new TblProductMaster();
			
			String proId=QuickUtil.doGetNextPK("TblProductMaster");
			tpm.setProductId(proId);
			tpm.setProductName(request.getParameter("productname"));
			tpm.setProductAliasname(request.getParameter("aliasname"));
			tpm.setProductInventory(Integer.parseInt(request.getParameter("inventory")));
			tpm.setBrandId(Integer.parseInt(request.getParameter("brandName")));
			tpm.setProductImage(savedpath);
			tpm.setProductImageName(filename);
			
			
			
			QuickUtil.saveToNew(tpm);
			boolean saveFeature=CommonUtil.saveFeature(proId, request);
			boolean saveInventory=CommonUtil.saveInventory(proId, request);
			response.setContentType("text/xml; charset=UTF-8");
			response.getWriter().write("true");
			
			
		} catch (Exception e) {
			 System.err.println("Sorry err"+e.getMessage());
			 e.printStackTrace();
		}
		return null;
	}




	public FileItem uploadfile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		try {
 
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			ByteArrayOutputStream output=new ByteArrayOutputStream();
			List<FileItem> fields = upload.parseRequest(request);
			   //Debug.logWarning("Number of fields: " + fields.size() + "<br/><br/>",module);
			   Iterator<FileItem> it = fields.iterator();
			   while (it.hasNext()) {
				    FileItem fileItem = it.next();
				    
				    boolean isFormField = fileItem.isFormField();
				     if (!isFormField) {
//				    	 fileName=fileItem.getName();
				    	 byte[] bs = fileItem.get();
				    	 //System.out.println("The image is recieved here "+bs.length+" File name ="+fileItem.getName());
				     
				    	 return fileItem;
				     }
			   }
			   
			
			//response.setContentType("text/xml; charset=UTF-8");
			
			//r11esponse.getWriter().write("true");
			
		} catch (Exception e) {
			System.out.println("Sorry exception"+e.getMessage());
			e.printStackTrace();
		}
		
		return null;
//		return super.execute(mapping, form, request, response);
	}
	
	
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
		
try {
			
			FileItem uploadfile = uploadfile(mapping, form, request, response);
			
			List list=new ArrayList();
			if(uploadfile!=null)
			{
				long currentTimeMillis = System.currentTimeMillis();

				String filename = String.valueOf(currentTimeMillis + uploadfile.getName());//FieldName());
				byte[] objattach = uploadfile.get();

				FileInputStream fileInputStream = null;

				String projectImagePath ="G:\\ATULWORKS\\SamudayAdmin\\WebContent\\ImageUpload\\";

				//System.out.println("projectImagePath=" + projectImagePath);
				String savedpath=projectImagePath + filename;
				//System.out.println("Full path2="+savedpath);
				FileOutputStream fileOuputStream = new FileOutputStream(
						savedpath);
				fileOuputStream.write(objattach);
				fileOuputStream.close();
				
				list.add(savedpath);
				
		   
		 //System.out.println(Integer.parseInt(request.getParameter("pmpkid")));
			TblProductMaster tpm =new TblProductMaster();
			
			
			String pmpkid=request.getParameter("pmpkid");
			List<TblProductMaster> prodList = QuickUtil.retrieveWherClause(new TblProductMaster(), "TblProductMaster", "productId='" + pmpkid + "'");
			tpm  = prodList.get(0);
			tpm.setProductId(request.getParameter("pmpkid"));
			
	 
			
			QuickUtil.updateToOld(tpm);
			response.setContentType("text/xml; charset=UTF-8");
			response.getWriter().write("true");
			}
			
			else if(uploadfile==null)
			{
				//System.out.println(Integer.parseInt(request.getParameter("pmpkid")));
				
				//System.out.println("aaaa");
				TblProductMaster tpm =new TblProductMaster();
				String pmpkid=request.getParameter("pmpkid");
				List<TblProductMaster> prodList = QuickUtil.retrieveWherClause(new TblProductMaster(), "TblProductMaster", "productId='" + pmpkid + "'");
				tpm  = prodList.get(0);
				tpm.setProductId(request.getParameter("id"));
				
				tpm.setProductName(request.getParameter("productname"));
				tpm.setProductAliasname(request.getParameter("aliasname"));
				tpm.setProductInventory(Integer.parseInt(request.getParameter("inventory")));
				tpm.setBrandId(Integer.parseInt(request.getParameter("brandName")));
				//tpm.setProductImage(savedpath);
				//tpm.setProductImageName(filename);
				
				
				
				QuickUtil.updateToOld(tpm);
				response.setContentType("text/xml; charset=UTF-8");
				response.getWriter().write("true");
				
			}
			
		}
		catch(Exception e)
		{
			//System.out.println("edit"+e.getMessage());
			e.printStackTrace();
		}
		
		
		
		return null;
		
	}
	
	public ActionForward FeatureMaster(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		
		//System.out.println(request.getParameter("typename"));
		try{
		TblFeatureMaster tbfm=new TblFeatureMaster();
		
		String typeId=request.getParameter("typename");
		
		List<TblFeatureMaster> TypeList=QuickUtil.retrieveWherClause(new TblFeatureMaster(), "TblFeatureMaster", "featureTypeId='" + typeId + "'");
		//tbfm=TypeList.get(0);
		//System.out.println(TypeList.size());
		JSONObject json = null;
		JSONArray jsonarr = null;
		
		jsonarr = new JSONArray();
		
		if (TypeList.size() > 0)
		{
			int intRow = 0;
						
			
			for (TblFeatureMaster TypeListTemp : TypeList) {
				
				
				json = new JSONObject();
				
				
				json.put("id", TypeListTemp.getFeatureId());	
				json.put("aliasname", TypeListTemp.getFeatureAliasname());
				json.put("name", TypeListTemp.getFeatureName());
				json.put("type", TypeListTemp.getFeatureTypeId());
				jsonarr.put(intRow, json);
				
				
				intRow++;
				
			}
			
		response.setContentType("text/xml; charset=UTF-8");
		response.getWriter().write(jsonarr.toString());
	//	System.out.println(jsonarr.toString());
	//	System.out.println("end");
	}
		}
		catch(Exception e)
		{
			//System.out.println("hi");
			e.printStackTrace();
			System.out.println("error");
		}
		return null;
	
	}
	
	public ActionForward displayGrid(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
				System.out.println("ProductMaster.displayGrid()");
				try {
					//System.out.println("ProductMaster.displayGrid()");
					//TblProductMaster prodMast=new TblProductMaster();
					System.out.println(request.getParameter("pmpkid"));
					
					String pkid=request.getParameter("pmpkid");
					 //CommonUtil.getAllProductEdit(pkid);
					
					 JSONArray gridarray=CommonUtil.getAllProductEdit(pkid);
					 
					 response.setContentType("text/xml; charset=UTF-8");
					response.getWriter().write(gridarray.toString());
					
					System.out.println("GridArray:"+gridarray.toString());
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
     			
     			return null;
}

}
