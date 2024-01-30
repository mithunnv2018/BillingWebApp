package com.billingwebapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;



import com.billingwebapp.domain.TblCategoryMaster;
import com.billingwebapp.util.QuickUtil;

public class Category extends DispatchAction{


	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {
		try
		{
			TblCategoryMaster tcm=new TblCategoryMaster();
			tcm.setCategoryAliasname(request.getParameter("aliasname"));
			tcm.setCategoryName(request.getParameter("name"));
			QuickUtil.saveToNew(tcm);
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
			
			TblCategoryMaster tcm=new TblCategoryMaster();
			tcm.setCategoryId(Integer.parseInt(request.getParameter("catpkid")));
			tcm.setCategoryAliasname(request.getParameter("aliasname"));
			tcm.setCategoryName(request.getParameter("name"));
			
			
			QuickUtil.updateToOld(tcm);
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
