package com.billingwebapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.billingwebapp.domain.TblFeatureMaster;
import com.billingwebapp.util.QuickUtil;

public class FeatureMaster extends DispatchAction{

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try
		{
			

			
			TblFeatureMaster tfm=new TblFeatureMaster();
			tfm.setFeatureAliasname(request.getParameter("aliasname"));
			tfm.setFeatureName(request.getParameter("name"));
			tfm.setFeatureTypeId(Integer.parseInt(request.getParameter("typename")));
			QuickUtil.saveToNew(tfm);
			response.setContentType("text/xml; charset=UTF-8");
			response.getWriter().write("true");
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		try
		{
			
		
			TblFeatureMaster tfm=new TblFeatureMaster();
			tfm.setFeatureId(Integer.parseInt(request.getParameter("fmpkid2")));
			tfm.setFeatureAliasname(request.getParameter("aliasname"));
			tfm.setFeatureName(request.getParameter("name"));
			tfm.setFeatureTypeId(Integer.parseInt(request.getParameter("typename")));
			//tfm.setFeatureTypeId(Integer.parseInt(request.getParameter("featureTypeId")));
			
			QuickUtil.updateToOld(tfm);
			response.setContentType("text/xml; charset=UTF-8");
			response.getWriter().write("true");
			
		}
		catch(Exception e)
		{
			System.out.println("edit"+e.getMessage());
			e.printStackTrace();
		}
		
		
		
		return null;
		
	}
}
