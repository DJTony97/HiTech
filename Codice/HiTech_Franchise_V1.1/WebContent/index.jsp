<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, it.HiTech.model.UtenteBean"%>
 <%		



	Collection<?> utenti = (Collection<?>) request.getAttribute("PV"); 
 	String error = (String)request.getAttribute("error");


 	if(utenti == null  && error == null) {
 		response.sendRedirect(response.encodeRedirectURL("./RicercaPV"));
 		return;
 	}
 	String r=(String)request.getParameter("regione");



 	
 %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-Equiv="Cache-Control" Content="no-cache">
	<meta http-Equiv="Pragma" Content="no-cache">
	<meta http-Equiv="Expires" Content="0"> 
	<meta name="author" content="HiTech">
	<script src="jquery-3.5.1.js" type="text/javascript">
	</script>
	<title>HiTech.it</title>
	<link href="layout.css" type="text/css" rel="stylesheet">	
 <script type="text/javascript">
	$(document).ready(function(){

		$.ajax({
			  type: "POST",
			  url: "RegioneGetter",
			  data: "",
			  async: true,
			  success: function(data) {
			    $("#regione").html(data);

			  }
			});
		
		$("#regione").change(function(){

			$.ajax({
				  type: "POST",
				  url: "ProvinciaGetter",
				  data: "regione="+document.getElementById("regione").value,
				  async: true,
				  success: function(data) {
				    $("#provincia").html(data);

				  }
				});
			});
		
		});
	</script> 


</head>
<body>
	
	<header class="top">
		<a href="<%=response.encodeURL("index.jsp")%>"><img src="./images/logo.png"></a>
	</header>
	
	<nav>
		<div id="homeb"  onclick="window.location.href='./index.jsp';"> </div>
		<% if(request.getSession().getAttribute("role")!=null){
			if(request.getSession().getAttribute("role").equals("9")||request.getSession().getAttribute("role").equals("1")){%>
			<div id="logout" onclick="window.location.href='./Logout';"></div>
			<div id="catalogo" onclick="window.location.href='./catalogo.jsp';"></div>
			<div id="cart" onclick="window.location.href='./carrello.jsp';"> </div>
			<%}} %>
		<div id="user" onclick="window.location.href='./AoUpage';"> </div>

	</nav>
	
		
			
			<div id="middle"> 
			<header id="catTitle">
			 Cerca il punto vendita più vicino				
			</header>
			
			<form name="CercaPV" id="CercaPV" method="post" action="RicercaPV">
 				<label for="regione">Seleziona Regione:</label>
				<select name="regione" id="regione"  >
				</select><br>
				<label for="provincia">Seleziona Provincia:</label>
				<select name="provincia" id="provincia"  >
				</select>
				<input type="submit" value="cerca">
				
			</form>
		
			<div class=productName>
			<% 
			if(r==null){
				%> Italia<%}
				else{ String ctgr=(String)request.getParameter("regione"); %> <%=ctgr%>   
				<%
				if((String)request.getParameter("provincia")!=null){
					String ctgr2=": "+(String)request.getParameter("provincia");%>
					<%=ctgr2%><%
					}
				}%></div><%
			utenti = (Collection<?>) request.getAttribute("PV");
			if(utenti.size()<1)
			{
				%>
				<div id="noproducts">Non ci sono punti vendtita nella regione o provincia selezionata !!!</div>
				
				<%
			}
			else {
				
				if(utenti != null && utenti.size() > 0) {
				
					Iterator<?> it  = utenti.iterator();
					while(it.hasNext()) {
						UtenteBean bean = (UtenteBean)it.next();
						%>
						<div class="PVContainer">
						<div class="productName"> Punto Vendita: <%= bean.getNome()%> <%= bean.getCognome() %></div>
						<div class="productName">Indirizo: <%= bean.getCompleteIndirizzo() %></div>
						</div>
			
						<%
					}
				}
			}
			
		
			
			%>
			
			
			
			
			
			
							</div>
	
		
	
	<footer id="last">Copyright &copy; 2020-2021 All Rights Reserved - Unisa - Project HiTech - IS - Antonio Esposito - Maurizio Maria Fabrocile</footer>

</body>
</html>