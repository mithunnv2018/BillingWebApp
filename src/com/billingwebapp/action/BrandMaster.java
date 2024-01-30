package com.billingwebapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.billingwebapp.domain.TblBrandMaster;
import com.billingwebapp.util.QuickUtil;

public class BrandMaster extends DispatchAction{

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {
		try
		{
			TblBrandMaster tbm=new TblBrandMaster();
			tbm.setBrandAliasname(request.getParameter("aliasname"));
			tbm.setBrandName(request.getParameter("brandName"));
			QuickUtil.saveToNew(tbm);
			response.setContentType("text/xml; charset=UTF-8");
			response.getWriter().write("true");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		try
		{
			TblBrandMaster tbm=new TblBrandMaster();
			tbm.setBrandId(Integer.parseInt(request.getParameter("branpkId")));
			tbm.setBrandAliasname(request.getParameter("aliasname"));
			tbm.setBrandName(request.getParameter("brandName"));
			QuickUtil.updateToOld(tbm);
			response.setContentType("text/xml; charset=UTF-8");
			response.getWriter().write("true");
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
}
