<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,it.HiTech.model.ProdottoBean,it.HiTech.model.Carrello,it.HiTech.model.CategoriaBean,it.HiTech.model.UtenteBean"%>
 <%
 	String error = (String)request.getAttribute("error");
 UtenteBean user = (UtenteBean) request.getSession().getAttribute("utente");

 if(user==null)response.sendRedirect(response.encodeRedirectURL("./login.jsp"));


 @SuppressWarnings("unchecked")
 Carrello<ProdottoBean> cart = (Carrello<ProdottoBean>)request.getSession().getAttribute("carrello");

	
	 
	 if (cart==null){
		cart = new Carrello<ProdottoBean>();
		request.getSession().setAttribute("carrello", cart);
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
	<script src="jquery-3.5.1.js" type="text/javascript">
</script>
	<title>HiTech.it</title>
	
	<link href="layout.css" type="text/css" rel="stylesheet">	
	
	<script type="text/javascript">

	function deleteCart(a){
		window.location.href = "./ConfermaAcquisto?action=deleteCart&page=/index.jsp&id=none&choice="+a+"";
		
	}
	function clearCart(){
		
		window.location.href = "./ConfermaAcquisto?action=clearCart&page=/carrello.jsp";
	}
	function OrderNow(){
		
		window.location.href = "./ConfermaAcquisto?action=OrderNow&page=/carrello.jsp";
	}
	</script>
	<script type="text/javascript">
	$(document).ready(function(){

		$.ajax({
			  type: "POST",
			  url: "RegioneGetter",
			  data: "",
			  async: true,
			  success: function(data) {
			    $("#Regione").html(data);

			  }
			});
		$("#OrderConfirm").submit(function(){
			alert("Ordine Effettuato Correttamente")
		});
		$("#Regione").change(function(){

			$.ajax({
				  type: "POST",
				  url: "ProvinciaGetter",
				  data: "regione="+document.getElementById("Regione").value,
				  async: true,
				  success: function(data) {
				    $("#Provincia").html(data);

				  }
				});
		});
		$("#Provincia").change(function(){
			$.ajax({
				  type: "POST",
				  url: "CapGetter",
				  data: "provincia="+document.getElementById("Provincia").value,
				  async: true,
				  success: function(data) {
				    $("#Cap").html(data);

				  }
				});
		});
			$("#Cap").change(function(){
				

				$.ajax({
					  type: "POST",
					  url: "CityGetter",
					  data: "CAP="+document.getElementById("Cap").value,
					  async: true,
					  success: function(data) {
					    $("#City").html(data);

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

			
			<% 
			List<ProdottoBean> prodcart = cart.getItems();

			if(prodcart.size() == 0) {
				%>
				<div id="carrelloVuoto">
				<% if(request.getSession().getAttribute("role")!=null){
				if(request.getSession().getAttribute("role").equals("9")||request.getSession().getAttribute("role").equals("1")) {%>
				Non hai ancora aggiunto nulla al tuo carrello! Cosa aspetti?
				<% }}else{%> Fai log-in o registrati per visualizzare il tuo carrello personale!<% }%>
				</div>
				<%
			}
			else{
				List<ProdottoBean> cartlist = cart.getItems();
				Iterator<?> it  = cartlist.iterator();
				%><div class="cartBox"><%
				int i=0;
				while(it.hasNext()){
					ProdottoBean bean = (ProdottoBean)it.next();
					%>
					<div class="cartContainer">
					<div class="cartCancel" onclick="deleteCart(<%=i+1 %>)"><img class="cartCancel" src="./images/cross.png"></div>
					<div class="cartPicture"><a href="<%=response.encodeURL("./prodotto.jsp?code="+ bean.getCodice())%>"><img class="cartPicture" src="data:image/jpg;base64,<%= bean.getImg()%>"></a></div>
					<div class="cartName"><a href="<%=response.encodeURL("./prodotto.jsp?code="+ bean.getCodice())%>"><%= bean.getNome() %></a></div>
					<div class="cartPrice"><a><%= bean.getPrezzo() %>€</a></div>
					
					</div>
		
					<% 
					
				i++;
				}	
				%>
				<input type="button" onclick="clearCart()" value="Svuota Carrello"/>
				<input type="button" onclick="document.getElementById('bycode').classList.toggle('active');" value="Concludi Ordine"/>				
				<div class="bycode" id="bycode">
					<form action="OrderNow()" name='OrderConfirm'  id='OrderConfirm' method="post">
						<label for="RCliente">Riferimento Cliente:</label><br>
						<input type="email" name="RCliente" id="RCliente" size="35" maxlength="445" required/><br>
						<label for="MPagamento">Metodo di Pagamento:</label><br>
						<select name="MPagamento" id="MPagamento" >
							<option value="Pagato">Pagato</option>
							<option value="Contanti Alla Consegna">Contanti Alla Consegna</option>
							<option value="Con Carta Alla Consegna">Con Carta Alla Consegna</option>
						</select><br>
						<label for="MSpedizione">Metodo di Spedizione/Consegna:</label><br>
						<select name="MSpedizione" id="MSpedizione" >
							<option value="Ritiro in Negozio">Ritiro in Negozio</option>
							<option value="Consegna a Domicilio">Consegna a Domicilio</option>
						</select><br>
						<label for="Address">Indirizzo:</label><br>
						<input type="text" name="Address" size="35" maxlength="40" required/><br>
						<label for="Regione">Seleziona Regione:</label><br>
						<select name="Regione" id="Regione" >
						</select><br>
						<label for="Provincia">Seleziona Provincia:</label><br>
						<select name="Provincia" id="Provincia"  >
						</select><br>
						<label for="Cap">Seleziona Cap:</label><br>
						<select name="Cap" id="Cap"  >
						</select><br>
						<label for="City">Seleziona Citta:</label><br>
						<select name="City" id="City" >
						</select><br>
						<input type="submit" name="submit" value="Conferma Ordine"/>
					</form>
				</div>
			</div>

				<%
			}
				
			%>
	</div>
	
	<footer id="last">Copyright &copy; 2020-2021 All Rights Reserved - Unisa - Project HiTech - IS - Antonio Esposito - Maurizio Maria Fabrocile</footer>
</body>
</html>