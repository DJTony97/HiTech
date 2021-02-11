<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,it.HiTech.model.OrdineBean"%>
<%
 	String error = (String)request.getAttribute("error");
 	Collection<?> orders = (Collection<?>) request.getAttribute("orders");


 	if(orders == null && error == null) {

 		response.sendRedirect(response.encodeRedirectURL("./OrdineControl"));
 		return;
 	}
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
	
	<title>HiTech.it</title>

	<link href="layout.css" type="text/css" rel="stylesheet">	
	<link href="prodlayout.css" type="text/css" rel="stylesheet">

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
			<Header>
			Ordini
			</Header>
			<% 
			if(orders.size()<1)
			{
				%>
				<div id="noorder">Non ci sono Ordini !?!?!</div>
				
				<%
			}
			else {
				if(orders != null && orders.size() > 0) {
					
					Iterator<?> itc  = orders.iterator();
				%>
					<table>
					<thead>
					<tr>
					<th>Codice</th>
					<th>Riferimento Cliente</th>
					<th>Data</th>
					<th>Metodo Di Pagamento</th>
					<th>Numero Di Spedizione</th>
					<th>Corriere</th>
					<th>Metodo di Spedizione</th>
					<th>Indirizzo</th>
					<th>Città</th>
					<th>CAP</th>
					<th>Provincia</th>
					<th>Regione</th>
					</tr>
					</thead>
					<tbody>
					<%
					while(itc.hasNext()) {
						OrdineBean bean = (OrdineBean)itc.next();
						%>
						<tr>
						<td><%= bean.getCodice() %></td>
						<td><%= bean.getRifCliente() %></td>
						<td><%= bean.getData() %></td>
						<td><%= bean.getMetodoPagamento() %></td>
						<td><%= bean.getNumero_Spedizione() %></td>
						<td><%= bean.getCorriere() %> </td>
						<td><%= bean.getMetodo_Spedizione() %></td>
						<td><%= bean.getIndirizzo() %></td>
						<td><%= bean.getCitta() %></td>
						<td><%= bean.getCap() %></td>
						<td><%= bean.getProvincia() %></td>
						<td><%= bean.getRegione()%></td>		
						</tr>			
					<%
					}
					%>
					
					</tbody>
					</table>
					<%}} %>
			
			
			
		

			
			

	
	<footer id="last">Copyright &copy; 2020 All Rights Reserved - Unisa - Project HiTech - TSW - Gioacchino Saraceno - Antonio Esposito - IS - Antonio Esposito - Maurizio Maria Fabrocile</footer>

</body>
</html>