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
<%if(request.getParameter("ftmpkid2")!=null&&!(request.getParameter("ftmpkid2").trim().equals(""))){%>
var isEdit = true;
var parampartyid = <%=request.getParameter("ftmpkid2")%>;
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
	var typename=document.getElementById('typename').value;
	
	
	if(aliasname == ""){
		alert("Please enter aliasname");
		return false;
	} 
	
	else if(typename == ""){
		alert("Please enter typename");
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
			
	    
			document.location = "featureTypeMaster.do";
	    	
			}
			else{
			    	alert("Data Not Saved Successfully");
			}
			
	    }
 }


	

	var aliasname=document.getElementById('aliasname').value;
	var typename=document.getElementById('typename').value;
	

		
		var url = 'FeatureTypeMaster.do?methodtocall=add&aliasname='+aliasname+'&typename='+typename;
		
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
			
	    	
			document.location = "featureTypeMaster.do"; 
	    	
		    }else{
			    	alert("Data Not Saved Successfully");
			}
			
	    }
	  }

	var aliasname=document.getElementById('aliasname').value;
	var typename=document.getElementById('typename').value;
			
		var url = 'FeatureTypeMaster.do?methodtocall=edit&aliasname='+aliasname+'&typename='+typename+'&ftmpkid2='+parampartyid;
		
		xmlhttp.open("POST",url ,true);
		
		xmlhttp.send();

	}
		
	
}



</script>

<div class="row-fluid">
					<div class="span12">
						<div class="box box-bordered box-color">
							<div class="box-title">
								<h3><i class="icon-th-list">Feature Types</i> </h3>
							</div>
							<div class="box-content nopadding">
							<%JSONArray returnarr=null;%>
							<%if(request.getParameter("ftmpkid2")!=null&&!(request.getParameter("ftmpkid2").trim().equals(""))){%>
							<%returnarr=CommonUtil.getAllFeatureTypeMasterEdit(request.getParameter("ftmpkid2"));%>
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
											 <input ID="typename" value="<%=returnobj.get("typename") %>" value= type="text" placeholder="Reference.."   class='ckeditor span12' rows="5" />
                                            
										</div>
									</div>
									
									
								
									
										
									<div class="form-actions">
                                        
                                        <button type="button" class="btn btn-primary" onclick="editsave();">Save</button>
                                        
										<button type="button" class="btn" onclick="document.location = 'featureTypeMaster.do';">Cancel</button>
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
											  <input ID="typename" value="" value= type="text" placeholder="Reference.."  class='ckeditor span12' rows="5" />
                                            </div>
									</div>								
									
									
									<div class="form-actions">
                                        
                                        <button type="button" class="btn btn-primary" onclick="savedata();">Save</button>

										<button type="button" class="btn" onclick="document.location = 'featureTypeMaster.do';">Cancel</button>
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
									<i class="icon-table">Feature Types List</i>
									
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
                                     
				
					<%returnproject =CommonUtil.getAllFeatureTypeMaster();%>
					<%int introw2=0;%>
					<%if(returnproject!=null){%>
							<%for(int xp1=0;xp1<returnproject.length();xp1++){%>

								<%JSONObject objjson2= returnproject.getJSONObject(xp1);%>
									 
								<tr>
								<td><%=objjson2.get("aliasname")%>
									</td>
									<td><%=objjson2.get("typename")%>
									</td>
											
									<td><a href="featureTypeMaster.do?ftmpkid2=<%=objjson2.get("id")%>">Edit</a>
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
																			
								
										