<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8" import="java.util.*,it.HiTech.model.UtenteBean" %>
    
    <%
    UtenteBean user = (UtenteBean) request.getSession().getAttribute("utente");
    
    if(user==null)response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
    
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-Equiv="Cache-Control" Content="no-cache">
<meta http-Equiv="Pragma" Content="no-cache">
<meta http-Equiv="Expires" Content="0">
<title>User-page</title>
<link rel='stylesheet' href='js-form-validation.css' type='text/css' />
<script type="text/javascript">
function ConfermaOperazione() {
	var richiesta = window.confirm("Dimissioni Franchiser continuare?");
	if (richiesta==true){
		return;
	}

}
function NotificaConferma() {
	var conferma = window.alert("Dimissioni Franchiser eseguita");
	return conferma;
	
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
	<div id="bContent">
	<h1>Utente:</h1>
			<img id="userBig" src="./images/man.png">
			<div id="textcontainer">
			<div id="userUserName" class="us">Username: <%= user.getUsername() %></div>
			<div id="userName" class="us">Nome: <%= user.getNome() %></div>
			<div id="userSurname" class="us">Cognome: <%= user.getCognome() %></div>
			<div id="userCF" class="us">Codice Fiscale: <%= user.getCF() %></div>
			<div id="userEmail" class="us">Email: <%= user.getEmail() %></div>
			<div id="userIndirizzo" class="us">Indirizzo: <%= user.getCompleteIndirizzo()%></div>
			</div>
			<div>
				<input type="button" onclick="window.location.href='./orderpage.jsp'" value="Visualizza Ordini Utente" class="starters"/>
				<input type="button" onclick="document.getElementById('dimissioni').classList.toggle('active');" value="Dimissioni Franchiser" class="starters"/>
				<form name="delUser"  method="post" action="./Dimissioni" onSubmit="NotificaConferma()">
			<div class="formcont" id="dimissioni">
			<ul>
				<li><label for="motivazioni">Descrizione:</label></li>
				<li><input type="text" placeholder="Inserisci motivo delle dimissioni" name="motivazioni" size="20" maxlength="500" required/></li>
			</ul>
			<input type="submit" value="Dimettiti" onClick="ConfermaOperazione()" >
			</div>
			</form>
			</div>
						
				
			
			</div>

	<footer id="last">Copyright &copy; 2020 All Rights Reserved - Unisa - Project HiTech - IS - Antonio Esposito - Maurizio Maria Fabrocile</footer>	
</body>
</html>