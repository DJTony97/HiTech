<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,it.HiTech.model.ProdottoBean,it.HiTech.model.Carrello,it.HiTech.model.CategoriaBean,it.HiTech.model.UtenteBean"%>
 <%
 UtenteBean user = (UtenteBean) request.getSession().getAttribute("utente");

 if(user==null)response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
 	ProdottoBean product = (ProdottoBean) request.getAttribute("product");
 	String error = (String)request.getAttribute("error");
 	if (product==null){
 		response.sendRedirect(response.encodeRedirectURL("./AcquistaProdotto?code="+request.getParameter("code")+"&page=/prodotto.jsp"));
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
	
	<script>


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
			<div id="volantino" onclick="window.location.href='./Volantino.jsp';"></div>
			<div id="catalogo" onclick="window.location.href='./catalogo.jsp';"></div>
			<div id="cart" onclick="window.location.href='./carrello.jsp';"> </div>
			<%}} %>
		<div id="user" onclick="window.location.href='./userpage.jsp';"> </div>

	</nav>
	
			
			
			<div id="middle"> 
			<img id="productBig" src="data:image/jpg;base64,<%= product.getImg()%>">
			<div id="textcontainer">
			<div id="productName"><%= product.getNome() %></div>
			<div id="productCode" ><%= product.getCodice() %></div>
			<label for="quantita"> Quantità Magazzino:</label>
			<div id="quantita"><%=product.getScorte() %></div>
			<div id="productDescription"><%= product.getDescrizione() %></div>
			<div id="productPrice">Prezzo: <a><%= product.getPrezzo() %>€</a></div>
			<%String contatore= request.getAttribute(product.getCodice()).toString();
				int cont=Integer.parseInt(contatore);
			if (cont<product.getScorte()||product.getScorte()==0){ %>
			<input id="add" name="add" type="button" onclick="window.location.href = './AcquistaProdotto?action=addCart&code=<%=product.getCodice() %>&<%=product.getCodice() %>=<%=cont %>'" value="Aggiungi al Carrello">
			<%} else { %>
			<div >Scorte Esaurite</div>
			<%} %>
			</div>
			
			
			

			</div>
			
			
	
	<footer id="last">Copyright &copy; 2020-2021 All Rights Reserved - Unisa - Project HiTech - IS - Antonio Esposito - Maurizio Maria Fabrocile</footer>
</body>
</html>