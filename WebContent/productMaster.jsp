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
var arrfeature=new Array();
var lengthfeature = 0;
<%JSONArray returnproject=null;%>
<%JSONArray returnarr=null;%>
<%if(request.getParameter("pmpkid")!=null&&!(request.getParameter("pmpkid").trim().equals(""))){%>
var isEdit = true;
var parampartyid = <%=request.getParameter("pmpkid")%>;
<%returnarr=CommonUtil.getAllProductEdit(request.getParameter("pmpkid"));%>
<%JSONObject returnobjx1=returnarr.getJSONObject(0);%>
arrfeature='<%= returnobjx1.getJSONArray("arrforprodfeatures") %>';
lengthfeature=<%=returnobjx1.getJSONArray("arrforprodfeatures").length()%>;
<%}else{%>
var isEdit = false;	
<%}%>


function notifycall(){

	jNotify("Added..");
}

$(document).ready(function(){
	
	  // simple Notify box call
	  jNotify();
	 
	  // simple Error box call
	  jError();
	 
	  // simple Success box call
	  jSuccess();
	 
	  // more complex Notify box call
	  jNotify(
	    'Here the message !!<br />You can write HTML code, <strong>bold</strong>,...',
	    {
	      autoHide : true, // added in v2.0
	      TimeShown : 1000,
	      HorizontalPosition : 'center',
	      onCompleted : function(){ // added in v2.0
	      alert('jNofity is completed !');
	    }
	  }
	  ); 

	  // close jNotify


	   
	}); // close document.ready


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
	var productname=document.getElementById('productname').value;
	var inventory=document.getElementById('inventory').value;
	var typename=document.getElementById('typename').value;
	var name=document.getElementById('name').value;
	var brandName=document.getElementById('brandName').value;	
	
	
	if(productname == ""){
		alert("Please enter Product name");
		return false;
	} 

	else if(aliasname == ""){
		alert("Please enter alias name");
		return false;
	}
	else if(inventory == ""){
		alert("Please enter inventory");
		return false;
	}
	else if(!inventory.match(/^\d+/)){
		alert("Please enter Only number in in inventory");
		return false;
	} 
	
	else if(brandName == "0"){
		alert("Please select brandName");
		return false;
	} 

	else if(typename == "0"){
		alert("Please select feature type");
		return false;
	} 
	else if(name == "0"){
		alert("Please select feture");
		return false;
	} 
	else
		{
	return true;
		}
}



var arrparti=new Array(); 
var arrpartiaddons=new Array();


var lengthparti = 0;
var lengthpartiaddons = 0;

var totalamt = 0;

function addfeaturegrid(){

	if(document.getElementById('typename').value == "0"){
		alert("Please select feature typename");
		return false;
	}
	if(document.getElementById('name').value == "0"){
		alert("Please select feature name");
		return false;
	}
	
	addtoarrayfeature();
	
	updatefeaturegrid();

	//document.getElementById('id').value=0;
	document.getElementById('typename').value="";
	document.getElementById('name').value="";
	notifycall();
		
}

function addtoarrayfeature(){
   // alert(document.getElementById('typename').options[document.getElementById('typename').selectedIndex].text);
	if(checkduplicatefeature(document.getElementById('typename').value)){
	var objtype=new Object();
	objtype.id=document.getElementById('name').value;
	objtype.typename = document.getElementById('typename').options[document.getElementById('typename').selectedIndex].text;
	objtype.name=document.getElementById('name').options[document.getElementById('name').selectedIndex].text;
	
	arrfeature[lengthfeature] = objtype;
	lengthfeature = lengthfeature + 1;
	}else{
		alert("Already added");
	}
}

function getfeature(){
	
	var griddata='';
	griddata = griddata + '[';
	for(i=0;i<arrfeature.length;i++){
		
		var objtype=new Object();
		objtype=arrfeature[i];
				
		griddata = griddata + '{';
		griddata = griddata + '"id":' + '"'+ objtype.id + '",';
		griddata = griddata + '"typename":' + '"'+ objtype.typename + '",';
		griddata = griddata + '"name":' + '"'+ objtype.name + '",';
		

		griddata = griddata + '}';
		if(i!=arrfeature.length-1){
			griddata = griddata + ',';
		}
		
	}
	griddata = griddata + ']';
	
	return griddata;
	
}





 function displayNames()
{
	 
	 xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  document.getElementById("name").options.length = 0;
		    	
		    	var arr=JSON.parse(xmlhttp.responseText);
		    	
		    	for(i=0;i<arr.length;i++)
			    	{
		    		var obj=new Object();
		    		obj.name=arr[i].name;
		    		obj.id=arr[i].id;
		    		
		    		var x = document.getElementById("name");
		    	    var option = document.createElement("option");
		    	    option.text = obj.name;
		    	    option.value=obj.id;
		    	    x.add(option);
		            
			    	}
			
	    }
	  }
var typename=document.getElementById('typename').value;

var url = 'ProductMaster.do?methodtocall=FeatureMaster&typename='+typename;

xmlhttp.open("POST",url ,true);

xmlhttp.send();


}
 



	

function getdisplayGrid()
{
	alert("21"+parampartyid);
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		   
		    	alert(xmlhttp.responseText);
			
	    }
	  }
	            
		var url='ProductMaster.do?methodtocall=displayGrid&pmpkid='+parampartyid;
		alert(url);	
		xmlhttp.open("POST",url ,true);

		xmlhttp.send();
}
		

function checkduplicatefeature(refid){
	
	for(i=0;i<arrfeature.length;i++){
		
		var objtype=new Object();
		objtype=arrfeature[i];
		if(objtype.typename==refid){
			return false;
		}
		
	}
	return true;
	
}


function updatefeaturegrid(){

	//alert("updatefeaturegrid");
	totalamt = 0;
	var bodystr="";
   	bodystr = bodystr+'<table class="table table-hover table-nomargin">';
	bodystr = bodystr+'<thead><tr ><th>Feature Type</th><th>Feature Name</th><th>Delete</th> </thead>';
	bodystr = bodystr+' <tbody>';
	
	for(i=0;i<arrfeature.length;i++){
		var objtype=new Object();
		objtype=arrfeature[i];
		
		bodystr = bodystr+'<tr>';
		bodystr = bodystr+'<td>' +objtype.typename;
		bodystr = bodystr+'</td>';
		bodystr = bodystr+'<td>'+objtype.name;
		bodystr = bodystr+'</td>';
		bodystr = bodystr+'<td><a href="javascript:void(0);" onclick="deletetoarrayfeature('+ i + ');">Delete</a>';
		bodystr = bodystr+'</td>';
		bodystr = bodystr+'</tr>';
		
	}
 
   	bodystr = bodystr+'</tbody></table>';

   	document.getElementById('GridFeature').innerHTML=bodystr;
	//$("#GridFeature").html(bodystr);
	
	alert("jjjjjj"+bodystr+document.getElementById("GridFeature"));
}


function deletetoarrayfeature(index1){
	var arrfeaturetemp=new Array();
	alert("aarr"+arrfeature.length);
	var introwx = 0;
	for(i=0;i<arrfeature.length;i++){
		var objtype=new Object();
		objtype=arrfeature[i];
		
		if(index1!=i){
		
		arrfeaturetemp[introwx] = objtype;
		introwx = introwx + 1;
		}
	}
	arrfeature = arrfeaturetemp;
	lengthfeature = introwx;
	
	updatefeaturegrid();
		
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
			
	    
			document.location = "productMaster.do";
	    	
			}
			else{
			    	alert("Data Not Saved Successfully");
			}
			
	    }
 }


	

	var aliasname=document.getElementById('aliasname').value;
	var productname=document.getElementById('productname').value;
	var inventory=document.getElementById('inventory').value;
	
	var brandName=document.getElementById('brandName').value;	
	var file=document.getElementById('idinputuploadfile').files[0];
	var fd = new FormData();

	if (file != null) {
		fd.append("filetext", file.name);
		fd.append("fileToUpload", file);
	}

		
		var url = 'ProductMaster.do?methodtocall=add&aliasname='+aliasname+'&productname='+productname+'&inventory='+inventory+'&brandName='+brandName+'&getfeature=' + getfeature();
		alert(url);
		xmlhttp.open("POST",url ,true);
		
		xmlhttp.send(fd);

	}
		
	}
	

function editsave(){

	//alert("editsave");
	if(validation()==true){
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		   // alert(xmlhttp.responseText);
		    if(xmlhttp.responseText=="true"){
		    	alert("Data Saved Successfully");
			
	    	
			document.location = "productMaster.do"; 
	    	
		    }else{
			    	alert("Data Not Saved Successfully");
			}
			
	    }
	  }

	var aliasname=document.getElementById('aliasname').value;
	var productname=document.getElementById('productname').value;
	var inventory=document.getElementById('inventory').value;
	var brandName=document.getElementById('brandName').value;	
	var file=document.getElementById('idinputuploadfile').files[0];
	var fd = new FormData();

	if (file != null) {
		fd.append("filetext", file.name);
		fd.append("fileToUpload", file);
	}

		
		var url = 'ProductMaster.do?methodtocall=edit&aliasname='+aliasname+'&productname='+productname+'&inventory='+inventory+'&brandName='+brandName+'&pmpkid='+parampartyid;
		
		xmlhttp.open("POST",url ,true);
		
		xmlhttp.send(fd);

	}
		
	
}


</script>

<div class="row-fluid">
					<div class="span12">
						<div class="box box-bordered box-color">
							<div class="box-title">
								<h3><i class="icon-th-list">Products</i> </h3>
							</div>
							<div class="box-content nopadding">
							
							<%if(request.getParameter("pmpkid")!=null&&!(request.getParameter("pmpkid").trim().equals(""))){%>
							
							<%JSONObject returnobj=returnarr.getJSONObject(0);%>
							
							<%-- <% out.println(returnobj);%> --%>
						<%-- <script type="text/javascript">
						arrfeature=<%= returnobj.getJSONArray("arrforprodfeatures") %>;
						alert(arrfeature[0].typename);
						updatefeaturegrid();
						</script>  --%>
						<form action="" method="POST" class="form-horizontal form-bordered">
						              
						              
						              
									 <div class="control-group">
									<label for="textfield" class="control-label">Product Name</label>
										<div class="controls">
											 <input ID="productname" value="<%=returnobj.get("productname") %>" value= type="text" placeholder="Reference.."   class='ckeditor span12' rows="5" />
                                            
										</div>
									</div>
									
								
									
									 <div class="control-group">
									<label for="textfield" class="control-label">Alias Name</label>
										<div class="controls">
											 <input ID="aliasname" value="<%=returnobj.get("aliasname") %>" value= type="text" placeholder="Reference.."   class='ckeditor span12' rows="5" />
                                            
										</div>
									</div>
									
								
									
									 <div class="control-group">
									<label for="textfield" class="control-label">Inventory</label>
										<div class="controls">
											 <input ID="inventory" value="<%=returnobj.get("inventory") %>" value= type="text" placeholder="Reference.."   class='ckeditor span12' rows="5" readonly="readonly" />
                                            
										</div>
									</div>
							
											
														
														<div class="control-group">
										<label for="textfield" class="control-label">Image</label>
										<div class="controls"><div><%=returnobj.opt("idinputuploadfile")%></div>
											<input type="file" name="idinputuploadfile[]"  id="idinputuploadfile" />
                                          
                                            
										</div>
									</div>
									
									
								
									
									
										<div class="control-group">
									<label for="textfield" class="control-label">Product Brand</label>
									
									<div class="controls"><%returnproject =CommonUtil.getAllBrand();%>
					                        
					                       
								           
								           
									<select id="brandName" name="brandName" class='ckeditor span12' >
									 
					                       <%int introw2=0;%>
					                       <%if(returnproject!=null){%>
							               <%for(int xp1=0;xp1<returnproject.length();xp1++){%>

								           <%JSONObject objjson2= returnproject.getJSONObject(xp1);%>
								           <%if(objjson2.get("id").equals(returnobj.get("brandName"))){ %>
								         <option value="<%=objjson2.get("id")%>" selected><%=objjson2.get("brandName")%></option>
								         <%}else{ %>
									<option value="<%=objjson2.get("id")%>"><%=objjson2.get("brandName")%></option>
									  <%} %>
									<%introw2=introw2+1; %>
				  			<%}%>
				  	<%}%>              
									
									</select>
									</div>
									</div>						
									
													
									
							
                            <div class="control-group">
										<label for="textfield" class="control-label"><b>Add Feature: -</b></label>
										
									</div>
								<div class="control-group">
										<label for="textarea" class="control-label">Select Feature Type</label>
										<div class="controls">
                                                                                          
                                            <select id="typename" class="typename" onchange="displayNames();">
                                            <option value="0" selected="selected">--Select--</option>
                                        <%returnproject =CommonUtil.getAllFeatureTypeMaster();%>
										
										<%if(returnproject!=null){%>
											<%for(int xp1=0;xp1<returnproject.length();xp1++){%>

												<%JSONObject objjson2= returnproject.getJSONObject(xp1);%>
													
													<option value="<%=objjson2.get("id")%>" ><%=objjson2.get("typename")%></option>
													
									  			<%}%>
										<%}%> 
                                            
                                            
                                            
                                            </select>
                                                                                                                              

										</div>
									</div>
										<div class="control-group">
										<label for="textarea" class="control-label">Select Feature Name</label>
										<div class="controls">
                                                                                          
                                            <select id="name" class="name" >
                                            <option value="0" selected="selected">--Select--</option>
                                      <%--   <%returnproject =CommonUtil.getAllFeatureMaster();%>
										
										<%if(returnproject!=null){%>
											<%for(int xp1=0;xp1<returnproject.length();xp1++){%>

												<%JSONObject objjson2= returnproject.getJSONObject(xp1);%>
													
													<option value="<%=objjson2.get("id")%>" ><%=objjson2.get("name")%></option>
													
									  			<%}%>
										<%}%>  --%>
                                          
                                            
                                            
                                            </select>
                                                                                                                              

										</div>
									</div>
								
								<div class="control-group">
										
										<div class="controls">
											<!--<input type="text" name="textfield" id="textfield" placeholder="Text input" class="input-xlarge">-->
                                             <button type="button" class="btn btn-primary" onclick="addfeaturegrid();">Add</button>
                                            
										</div>
									</div>


                             <div class="control-group">
										
										
											<!--<input type="text" name="textfield" id="textfield" placeholder="Text input" class="input-xlarge">-->
											
                                            <div id="GridFeature" class="box-content nopadding"></div>
                                           			<div id="Grid1" class="box-content nopadding">
								<table class="table table-hover table-nomargin">
									<thead>
										
										<tr>
										   <th>Feature Type</th>
											<th>Feature Name</th>
             
											<th>Edit</th>
										</tr>
									</thead>
                                    <tbody>
                                     
				
					<%//returnproject =CommonUtil.getAllProductEdit(request.getParameter("pmpkid"));%>
					
					<% JSONArray gridarray= returnobj.getJSONArray("arrforprodfeatures"); %>
					
					<%if(returnproject!=null){%>
							<%for(int xp1=0;xp1<gridarray.length();xp1++){%>

								<%JSONObject objjson2= gridarray.getJSONObject(xp1);%>
									 
								<tr>
								<td><%=objjson2.get("typename")%>
									</td>
									<td><%=objjson2.get("name")%>
									</td>
											
									<td><a href="javascript:void(0);" onclick="deletetoarrayfeature(<%=xp1%>);">Delete</a>
									</td>
								</tr>
								<%introw2=introw2+1; %>
				  			<%}%>
				  	<%}%>                  
				
                                    </tbody>
									
								</table>
							</div>							
									</div>

										
									<div class="form-actions">
                                        
                                        <button type="button" class="btn btn-primary" onclick="editsave();">Save</button>
                                        
										<button type="button" class="btn" onclick="document.location = 'productMaster.do';">Cancel</button>
									</div>
									</form>
									
								<%}else{%>
				


<form action="" method="POST" class="form-horizontal form-bordered">
<div class="control-group"> 
												<div class="control-group">
										<label for="textfield" class="control-label">Product Name</label>
										<div class="controls">
                                            <input ID="productname" value="" value= type="text" placeholder="Reference.."  class='ckeditor span12' rows="5" />   
										</div>
									</div>
											
									 				
																									
										
											<div class="control-group">
										<label for="textfield" class="control-label">Alias Name</label>
										<div class="controls">
                                            <input ID="aliasname" value="" value= type="text" placeholder="Reference.."  class='ckeditor span12' rows="5" />   
										</div>
									</div>
										
										
										                       
					
									
									
									<div class="control-group">
										<label for="textfield" class="control-label">Inventory</label>
										<div class="controls">
											
                                            <input ID="inventory" value="" value= type="text" placeholder="Reference.."  class='ckeditor span12' rows="5" />
                                            
										</div>
									</div>
									
									
																	
									
									<div class="control-group">
										<label for="textfield" class="control-label">Image</label>
										<div class="controls">
											<input type="file" name="idinputuploadfile[]"  id="idinputuploadfile"/>
                                          
                                            
										</div>
									</div>
													
															
											
									<div class="control-group">
									<label for="textfield" class="control-label">Brand</label>
									<div class="controls"><%returnproject =CommonUtil.getAllBrand();%>
					                        
					                       
								           
								           
									<select id="brandName" name="brandName" class='ckeditor span12' >
									 <option value="0" selected="selected">Select</option>
					                       <%int introw2=0;%>
					                       <%if(returnproject!=null){%>
							               <%for(int xp1=0;xp1<returnproject.length();xp1++){%>

								           <%JSONObject objjson2= returnproject.getJSONObject(xp1);%>
								         
									<option value="<%=objjson2.get("id")%>"><%=objjson2.get("brandName")%></option>
									
									<%introw2=introw2+1; %>
				  			<%}%>
				  	<%}%>             
									
									</select>
									</div>
									</div>							
									                                        								
																
									
									
								
								<div id="diverror"></div>
							



                            <div class="control-group">
										<label for="textfield" class="control-label"><b>Add Feature: -</b></label>
										
									</div>
								<div class="control-group">
										<label for="textarea" class="control-label">Select Feature Type</label>
										<div class="controls">
                                                                                          
                                            <select id="typename" class="typename" onchange="displayNames();">
                                            <option value="0" selected="selected">--Select--</option>
                                        <%returnproject =CommonUtil.getAllFeatureTypeMaster();%>
										
										<%if(returnproject!=null){%>
											<%for(int xp1=0;xp1<returnproject.length();xp1++){%>

												<%JSONObject objjson2= returnproject.getJSONObject(xp1);%>
													
													<option value="<%=objjson2.get("id")%>" ><%=objjson2.get("typename")%></option>
													
									  			<%}%>
										<%}%> 
                                            
                                            
                                            
                                            </select>
                                                                                                                              

										</div>
									</div>
										<div class="control-group">
										<label for="textarea" class="control-label">Select Feature Name</label>
										<div class="controls">
                                                                                          
                                            <select id="name" class="name" >
                                            <option value="0" selected="selected">--Select--</option>
                                      <%--   <%returnproject =CommonUtil.getAllFeatureMaster();%>
										
										<%if(returnproject!=null){%>
											<%for(int xp1=0;xp1<returnproject.length();xp1++){%>

												<%JSONObject objjson2= returnproject.getJSONObject(xp1);%>
													
													<option value="<%=objjson2.get("id")%>" ><%=objjson2.get("name")%></option>
													
									  			<%}%>
										<%}%>  --%>
                                          
                                            
                                            
                                            </select>
                                                                                                                              

										</div>
									</div>
								
								<div class="control-group">
										
										<div class="controls">
											<!--<input type="text" name="textfield" id="textfield" placeholder="Text input" class="input-xlarge">-->
                                             <button type="button" class="btn btn-primary" onclick="addfeaturegrid();">Add</button>
                                            
										</div>
									</div>


                             <div class="control-group">
										
										
											<!--<input type="text" name="textfield" id="textfield" placeholder="Text input" class="input-xlarge">-->
                                            <div id="GridFeature" class="box-content nopadding"></div>
                                           								
									</div>

                                    <div class="form-actions">
                                        
                                        <button type="button" class="btn btn-primary" onclick="savedata();">Save</button>

										<button type="button" class="btn" onclick="document.location = 'productMaster.do';">Cancel</button>
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
									<i class="icon-table">Product List</i>
									
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
                                     
				
					<%returnproject =CommonUtil.getAllProducts();%>
					<%int introw2=0;%>
					<%if(returnproject!=null){%>
							<%for(int xp1=0;xp1<returnproject.length();xp1++){%>

								<%JSONObject objjson2= returnproject.getJSONObject(xp1);%>
									 
								<tr>
								<td><%=objjson2.get("aliasname")%>
									</td>
									<td><%=objjson2.get("productname")%>
									</td>
									<td><a href="productMaster.do?pmpkid=<%=objjson2.get("id")%>">Edit</a>
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
																			
								
										