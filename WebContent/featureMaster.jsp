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
<%if(request.getParameter("fmpkid2")!=null&&!(request.getParameter("fmpkid2").trim().equals(""))){%>
var isEdit = true;
var parampartyid = <%=request.getParameter("fmpkid2")%>;
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
	var name=document.getElementById('name').value;
	var typename=document.getElementById('typename').value;
	
	if(aliasname == ""){
		alert("Please enter aliasname");
		return false;
	} 
	
	else if(name == ""){
		alert("Please enter name");
		return false;
	} 
	else if(typename == "0"){
		alert("Please select feature type");
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
			
	    
			document.location = "featureMaster.do";
	    	
			}
			else{
			    	alert("Data Not Saved Successfully");
			}
			
	    }
 }


	

	var aliasname=document.getElementById('aliasname').value;
	alert(aliasname);
	var name=document.getElementById('name').value;
	var typename=document.getElementById('typename').value;

		
		var url = 'FeatureMaster.do?methodtocall=add&aliasname='+aliasname+'&name='+name+'&typename='+typename;
		
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
			
	    	
			document.location = "featureMaster.do"; 
	    	
		    }else{
			    	alert("Data Not Saved Successfully");
			}
			
	    }
	  }

	var aliasname=document.getElementById('aliasname').value;
	var name=document.getElementById('name').value;
	var typename=document.getElementById('typename').value;
			
		var url = 'FeatureMaster.do?methodtocall=edit&aliasname='+aliasname+'&name='+name+'&typename='+typename+'&fmpkid2='+parampartyid;
		alert(url);
		xmlhttp.open("POST",url ,true);
		
		xmlhttp.send();

	}
		
	
}



</script>

<div class="row-fluid">
					<div class="span12">
						<div class="box box-bordered box-color">
							<div class="box-title">
								<h3><i class="icon-th-list">Feature</i> </h3>
							</div>
							<div class="box-content nopadding">
							<%JSONArray returnarr=null;%>
							<%if(request.getParameter("fmpkid2")!=null&&!(request.getParameter("fmpkid2").trim().equals(""))){%>
							<%returnarr=CommonUtil.getAllFeatureMasterEdit(request.getParameter("fmpkid2"));%>
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
											 <input ID="name" value="<%=returnobj.get("name") %>" value= type="text" placeholder="Reference.."   class='ckeditor span12' rows="5" />
                                            
										</div>
									</div>
									
									
								
								<div class="control-group">
									<label for="textfield" class="control-label">Feature Type</label>
									
									<div class="controls"><%returnproject =CommonUtil.getAllFeatureTypeMaster();%>
					                        
					                       
								           
								           
									<select id="typename" name="typename" class='ckeditor span12' >
									 
					                       <%int introw2=0;%>
					                       <%if(returnproject!=null){%>
							               <%for(int xp1=0;xp1<returnproject.length();xp1++){%>

								           <%JSONObject objjson2= returnproject.getJSONObject(xp1);%>
								           <%if(objjson2.get("id").equals(returnobj.get("type"))){ %>
								         <option value="<%=objjson2.get("id")%>" selected><%=objjson2.get("typename")%></option>
								         <%}else{ %>
									<option value="<%=objjson2.get("id")%>"><%=objjson2.get("typename")%></option>
									  <%} %>
									<%introw2=introw2+1; %>
				  			<%}%>
				  	<%}%>              
									
									</select>
									</div>
									</div>
								
									
										
									<div class="form-actions">
                                        
                                        <button type="button" class="btn btn-primary" onclick="editsave();">Save</button>
                                        
										<button type="button" class="btn" onclick="document.location = 'featureMaster.do';">Cancel</button>
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
											  <input ID="name" value="" value= type="text" placeholder="Reference.."  class='ckeditor span12' rows="5" />
                                            </div>
									</div>								
									
											
										<div class="control-group">
									<label for="textfield" class="control-label">Feature Type</label>
									<div class="controls"><%returnproject =CommonUtil.getAllFeatureTypeMaster();%>
					                        
					                       
								           
								           
									<select id="typename" name="typename" class='ckeditor span12' >
									 <option value="0">Select</option>
					                       <%int introw2=0;%>
					                       <%if(returnproject!=null){%>
							               <%for(int xp1=0;xp1<returnproject.length();xp1++){%>

								           <%JSONObject objjson2= returnproject.getJSONObject(xp1);%>
								         
									<option value="<%=objjson2.get("id")%>"><%=objjson2.get("typename")%></option>
									
									<%introw2=introw2+1; %>
				  			<%}%>
				  	<%}%>             
									
									</select>
									</div>
									</div>							
									                                                               
					
									
									
									<div class="form-actions">
                                        
                                        <button type="button" class="btn btn-primary" onclick="savedata();">Save</button>

										<button type="button" class="btn" onclick="document.location = 'featureMaster.do';">Cancel</button>
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
									<i class="icon-table">Feature List</i>
									
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
                                     
				
					<%returnproject =CommonUtil.getAllFeatureMaster();%>
					<%int introw2=0;%>
					<%if(returnproject!=null){%>
							<%for(int xp1=0;xp1<returnproject.length();xp1++){%>

								<%JSONObject objjson2= returnproject.getJSONObject(xp1);%>
									 
								<tr>
								<td><%=objjson2.get("aliasname")%>
									</td>
									<td><%=objjson2.get("name")%>
									</td>
											
									<td><a href="featureMaster.do?fmpkid2=<%=objjson2.get("id")%>">Edit</a>
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
																			
								
										