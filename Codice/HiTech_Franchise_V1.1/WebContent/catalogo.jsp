<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,it.HiTech.model.ProdottoBean,it.HiTech.model.UtenteBean,it.HiTech.model.Carrello,it.HiTech.model.CategoriaBean"%>
 <%
 UtenteBean user = (UtenteBean) request.getSession().getAttribute("utente");

 if(user==null)response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
 ProdottoBean product = (ProdottoBean) request.getAttribute("product");

 


 	Collection<?> products = (Collection<?>) request.getAttribute("products");
	Collection<?> categorie = (Collection<?>) request.getAttribute("categorie"); 
 	String error = (String)request.getAttribute("error");
 	
 	if(products == null && error == null) {
 		response.sendRedirect(response.encodeRedirectURL("./CercaProdotto"));
 		return;
 	}
 	if(categorie==null) {
 		response.sendRedirect(response.encodeRedirectURL("./CercaProdotto"));
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

	<script>
function Azzera(){

	window.location.href = "./CercaProdotto?category=''";
}
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
			 Cerca nel catalogo
			</header>
			<input type="button" onclick="document.getElementById('categories').classList.toggle('active');" value="cerca per categoria" class="starters"/>
			<div id= "categories" >
			<form  name="categoriaS" onchange="<%request.removeAttribute("code") ;%>" action="./CercaProdotto">
			<select name="category" id="category" required>
					<% 


				
				if(categorie != null && categorie.size() > 0) {
				
					Iterator<?> itb  = categorie.iterator();
					while(itb.hasNext()) {
						CategoriaBean cat = (CategoriaBean)itb.next();
						%>
						<option value="<%= cat.getNome()%>"><%= cat.getNome()%></option>
						<% 
					}
				}
			
			%>
				
				</select>
				<input type="submit" value="cerca">
				</form>
				
			
			
			
			
			</div>
			<br>
			<input type="button" onclick="document.getElementById('bycode').classList.toggle('active');" value="cerca per codice" class="starters"/>
			<div id="bycode">
			<form  name="bcode" id="bcode" action="CercaProdotto" >
			<label for="code">Codice Prodotto:</label>
			<input type="text" name="code" id="code" size="20" maxlength="20" required/>
			<input type="submit" value="cerca">
				</form>
			</div>
			<br>
			<input type="button" onclick="Azzera()" value="azzera filtri" class="starters"/>
			<br>
			<div>
			<header id="ProdTitle">
			 Risultati
			</header><% 
			
			if(products.size()<1)
			{
				%>
				<div id="noproducts">Non ci sono prodotti !?!?!</div>
				
				<%
			}
			else {
				
				if(products != null && products.size() > 0) {
				
					Iterator<?> it  = products.iterator();
					while(it.hasNext()) {
						ProdottoBean bean = (ProdottoBean)it.next();
						%>
						<div class="productContainer">
						<div class="productPicture"><a href="<%=response.encodeURL("./prodotto.jsp?code="+ bean.getCodice())%>"><img class="productPicture" src="data:image/jpg;base64,<%= bean.getImg()%>"></a></div>
						<div class="productName"><a href="<%=response.encodeURL("./prodotto.jsp?code="+ bean.getCodice())%>"><%= bean.getNome() %></a></div>
						<div class="productName"><a href="<%=response.encodeURL("./prodotto.jsp?code="+ bean.getCodice())%>"><%= bean.getCodice() %></a></div>
						<div class="productPrice"><a><%= bean.getPrezzo() %>€</a></div>

						</div>
			
						<% 
					}
				}
			}
			
			%></div>
							</div>
		
			
	
	<footer id="last">Copyright &copy; 2020-2021 All Rights Reserved - Unisa - Project HiTech Franchising - IS - Antonio Esposito - Maurizio Maria Fabrocile</footer>

</body>
</html>