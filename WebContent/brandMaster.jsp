 <%@ page import="com.billingwebapp.commons.CommonUtil" %> 
<%@ page import="org.json.JSONObject"%>
<%@ page import="org.json.JSONArray"%>
<%@ page import="java.util.Random"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<script language="javascript">
var xmlhttp=null; 
<%JSONArray returnproject=null;%>
<%if(request.getParameter("branpkId")!=null&&!(request.getParameter("branpkId").trim().equals(""))){%>
var isEdit = true;
var parampartyid = <%=request.getParameter("branpkId")%>;
<%}else{%>
var isEdit = false;	
<%}%>


function loaddoc(){
	
	if (window.XMLHttpRequest)
	  {
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	
}

loaddoc();



function validation(){
	loaddoc();
	
	var aliasname=document.getElementById('aliasname').value;
	var brandName=document.getElementById('brandName').value;
	
	
	if(aliasname == ""){
		alert("Please enter aliasname");
		return false;
	} 
	
	else if(brandName == ""){
		alert("Please enter brandName");
		return false;
	} 
	
	else
		{
	return true;
		}
}
function savedata(){

	if(validation()==true){
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		    if(xmlhttp.responseText=="true")
			    {
		    	alert("Data Saved Successfully");
			
	    
			document.location = "brandMaster.do";
	    	
			}
			else{
			    	alert("Data Not Saved Successfully");
			}
			
	    }
 }


	

	var aliasname=document.getElementById('aliasname').value;
	var brandName=document.getElementById('brandName').value;
	

		
		var url = 'BrandMaster.do?methodtocall=add&aliasname='+aliasname+'&brandName='+brandName;
		
		xmlhttp.open("POST",url ,true);
		
		xmlhttp.send();

	}
		
	}
	


function editsave(){

	//alert("editsave");
	if(validation()==true){
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		    if(xmlhttp.responseText=="true"){
		    	alert("Data Saved Successfully");
			
	    	
			document.location = "brandMaster.do"; 
	    	
		    }else{
			    	alert("Data Not Saved Successfully");
			}
			
	    }
	  }

	var aliasname=document.getElementById('aliasname').value;
	var brandName=document.getElementById('brandName').value;
			
		var url = 'BrandMaster.do?methodtocall=edit&aliasname='+aliasname+'&brandName='+brandName+'&branpkId='+parampartyid;
		
		xmlhttp.open("POST",url ,true);
		
		xmlhttp.send();

	}
		
	
}



</script>

<div class="row-fluid">
					<div class="span12">
						<div class="box box-bordered box-color">
							<div class="box-title">
								<h3><i class="icon-th-list">Brands</i> </h3>
							</div>
							<div class="box-content nopadding">
							<%JSONArray returnarr=null;%>
							<%if(request.getParameter("branpkId")!=null&&!(request.getParameter("branpkId").trim().equals(""))){%>
							<%returnarr=CommonUtil.getAllBrandEdit(request.getParameter("branpkId"));%>
							<%JSONObject returnobj=returnarr.getJSONObject(0);%>
							
						<form action="" method="POST" class="form-horizontal form-bordered">
						              
						              
									
									 <div class="control-group">
									<label for="textfield" class="control-label">Alias Name</label>
										<div class="controls">
											 <input ID="aliasname" value="<%=returnobj.get("aliasname") %>" value= type="text" placeholder="Reference.."   class='ckeditor span12' rows="5" />
                                            
										</div>
									</div>
									
									
									 <div class="control-group">
									<label for="textfield" class="control-label">Name</label>
										<div class="controls">
											 <input ID="brandName" value="<%=returnobj.get("brandName") %>" value= type="text" placeholder="Reference.."   class='ckeditor span12' rows="5" />
                                            
										</div>
									</div>
									
									
								
									
										
									<div class="form-actions">
                                        
                                        <button type="button" class="btn btn-primary" onclick="editsave();">Save</button>
                                        
										<button type="button" class="btn" onclick="document.location = 'brandMaster.do';">Cancel</button>
									</div>
									</form>
									
								<%}else{%>

<form action="" method="POST" class="form-horizontal form-bordered">
<div class="control-group"> 
																
																									
										
											<div class="control-group">
										<label for="textfield" class="control-label">Alias Name</label>
										<div class="controls">
                                            <input ID="aliasname" value="" value= type="text" placeholder="Reference.."  class='ckeditor span12' rows="5" />   
										</div>
									</div>
										
										
										<div class="control-group">
										<label for="textfield" class="control-label">Name</label>
										<div class="controls">
											  <input ID="brandName" value="" value= type="text" placeholder="Reference.."  class='ckeditor span12' rows="5" />
                                            </div>
									</div>								
									
									
									<div class="form-actions">
                                        
                                        <button type="button" class="btn btn-primary" onclick="savedata();">Save</button>

										<button type="button" class="btn" onclick="document.location = 'brandMaster.do';">Cancel</button>
									</div>
								</form>
								
								<%} %>
							</div>
						</div>
					</div>
				</div>
								
								<div id="diverror"></div>
							
                
                <div class="row-fluid">
					<div class="span12">
					<div class="box box-bordered box-color">
						<div class="box">
							<div class="box-title">
								<h3>
									<i class="icon-table">Brand List</i>
									
								</h3>
							</div>
							<div id="Grid1" class="box-content nopadding">
								<table class="table table-hover table-nomargin table-bordered dataTable-columnfilter dataTable">
									<thead>
										<tr class="thefilter">
											<th>Alias Name</th>
											<th>Name</th>
                                           	<th>Edit</th>
										</tr>
										<tr>
										   <th>Alias Name</th>
											<th>Name</th>
             
											<th>Edit</th>
										</tr>
									</thead>
                                    <tbody>
                                     
				
					<%returnproject =CommonUtil.getAllBrand();%>
					<%int introw2=0;%>
					<%if(returnproject!=null){%>
							<%for(int xp1=0;xp1<returnproject.length();xp1++){%>

								<%JSONObject objjson2= returnproject.getJSONObject(xp1);%>
									 
								<tr>
								<td><%=objjson2.get("aliasname")%>
									</td>
									<td><%=objjson2.get("brandName")%>
									</td>
											
									<td><a href="brandMaster.do?branpkId=<%=objjson2.get("id")%>">Edit</a>
									</td>
								</tr>
								<%introw2=introw2+1; %>
				  			<%}%>
				  	<%}%>                                                                            
					 
                                    </tbody>
									
								</table>
							</div>
						</div>
					</div>
				</div>
				</div>				
																			
								
										