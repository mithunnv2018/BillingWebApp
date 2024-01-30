package com.billingwebapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.billingwebapp.domain.TblFeatureTypeMaster;
import com.billingwebapp.util.QuickUtil;


public class FeatureTypeMaster extends DispatchAction{

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {
		try
		{
			TblFeatureTypeMaster tftm=new TblFeatureTypeMaster();
			tftm.setFeatureTypeAliasname(request.getParameter("aliasname"));
			tftm.setFeatureTypeName(request.getParameter("typename"));
			QuickUtil.saveToNew(tftm);
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
			
			TblFeatureTypeMaster tftm=new TblFeatureTypeMaster();
			tftm.setFeatureTypeId(Integer.parseInt(request.getParameter("ftmpkid2")));
			tftm.setFeatureTypeAliasname(request.getParameter("aliasname"));
			tftm.setFeatureTypeName(request.getParameter("typename"));
			
			
			QuickUtil.updateToOld(tftm);
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
