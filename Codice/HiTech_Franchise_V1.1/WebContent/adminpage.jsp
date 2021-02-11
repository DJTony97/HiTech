<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8" import="java.util.*,it.HiTech.model.UtenteBean,it.HiTech.model.CategoriaBean" %>
    
    <%
    UtenteBean user = (UtenteBean) request.getSession().getAttribute("utente");
    String role=(String)request.getSession().getAttribute("role");
    Collection<?> categorie = (Collection<?>) request.getAttribute("categorie");
    if(user==null)response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
    else if(role.equals("9")){
 		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/userpage.jsp");
 		dispatcher.forward(request, response);
 		return;
 	}
 	if(categorie==null){

 		response.sendRedirect(response.encodeRedirectURL("./CategoryGetter?page=/adminpage.jsp"));
 		return;
 	}%>


<!DOCTYPE html>
<html>
<head>
<meta http-Equiv="Cache-Control" Content="no-cache">
<meta http-Equiv="Pragma" Content="no-cache">
<meta http-Equiv="Expires" Content="0">
<title>Admin-page</title>

<link rel='stylesheet' href='js-form-validation.css' type='text/css' />
<script>
function formValidation()
{
var codice = document.registration.codeid;
var nome = document.registration.nameid;
var marca = document.registration.marcaid;
var descrizione = document.registration.descriptionid;
var prezzo = document.registration.prezzoid;



}
</script>
<script type="text/javascript">
	$(document).ready(function(){
 		$('#codeid').keyup(function(){
			var myemail = $('#codeid').val();
				$.post("prodcheck",{"codeid":myemail},function(result){
				$('#result').html(result);
			});
		});
 	});</script>
</head>
<body>
<header class="top">
		<a href="<%=response.encodeURL("index.jsp")%>"><img src="./images/logo.png"></a>
	</header>
<nav>
		<div id="menu" onclick="toggleSidebar()"> </div>
		<div id="homeb"  onclick="window.location.href='./index.jsp';"> </div>
		<% if(request.getSession().getAttribute("role")!=null){
			if(request.getSession().getAttribute("role").equals("9")||request.getSession().getAttribute("role").equals("1")){%>
			<div id="logout" onclick="window.location.href='./Logout';"></div>
			<div id="volantino" onclick="window.location.href='./Volantino.jsp';"></div>
			<div id="catalogo" onclick="window.location.href='./catalogo.jsp';"></div>
			<div id="cart" onclick="window.location.href='./carrello.jsp';"> </div>
			<%}} %>
		<div id="user" onclick="window.location.href='./AoUpage';"> </div>

	</nav>
	<div id="bContent">
	<h1>Clicca sui bottoni per aprire le opzioni :</h1>
			<input type="button" onclick="document.getElementById('first').classList.toggle('active');" value="Inserisci prodotto" class="starters"/>
			<form name="newprod"  method="post" action="Administration?action=newprod" enctype="multipart/form-data">
			<div class="formcont" id="first">
			<ul>
				<li><label for="codeid">Codice:</label></li>
				<li><input type="text" name="codeid" size="20" maxlength="20" required/></li>
				<li><label  id="result" ></label></li>
				<li><label for="nameid">Nome:</label></li>
				<li><input type="text" name="nameid" size="20" maxlength="50" required/></li>
				<li><label for="marcaid">Marca:</label></li>
				<li><input type="text" name="marcaid" size="20" maxlength="15" required/></li>
				<li><label for="descriptionid">Descrizione:</label></li>
				<li><input type="text" name="descriptionid" size="20" maxlength="500" required/></li>
				<li><label for="prezzoid">Prezzo:</label></li>
				<li><input type="text" name="prezzoid" size="20" maxlength="45" required/></li>
				<li><label for="file">Foto Prodotto:</label></li>
				<li><input type="file" name="file" multiple /></li> 
				
 			</ul>
				<input type="submit" value="Crea">
			</div>
			</form>
			<input type="button" onclick="document.getElementById('second').classList.toggle('active');" value="Cancella prodotto" class="starters"/>
			<form name="delprod"  method="post" action="Administration?action=delprod">
			<div class="formcont" id="second">
			<ul>
				<li><label for="codeid">Codice:</label></li>
				<li><input type="text" name="codeid" size="20" maxlength="20" required/></li>
				<li><label  id="result" ></label></li>
			</ul>
				<input type="submit" value="Cancella" >
			</div>
			</form>
			<input type="button" onclick="document.getElementById('third').classList.toggle('active');" value="Aggiorna prezzo" class="starters"/>
			<form name="aggprod"  method="post" action="Administration?action=aggprod">
			<div class="formcont" id="third">
			<ul>
				<li><label for="codeid">Codice:</label></li>
				<li><input type="text" name="codeid" size="20" maxlength="20" required/></li>
				<li><label  id="result" ></label></li>
				<li><label for="prezzoid3">prezzo:</label></li>
				<li><input type="text" name="prezzoid3" size="20" maxlength="20" required/></li>
				
			</ul>
				<input type="submit" value="aggiorna">
			</div>
		</form>
					<input type="button" onclick="document.getElementById('fifth').classList.toggle('active');" value="Aggiungi Nuova Categoria" class="starters"/>
			<form name="addcat"  method="post" action="Administration?action=addcat">
			<div class="formcont" id="fifth">
			<ul>
				<li><label for="nomeCat">Nome Nuova Categoria:</label></li>
				<li><input type="text" name="nomeCat" size="20" maxlength="20" required/></li>
				
			</ul>
				<input type="submit" value="Aggiungi Categoria">
			</div>
		</form>
		<input type="button" onclick="document.getElementById('fourth').classList.toggle('active');" value="Inserisci categoria Prodotto" class="starters"/>
			<form name="prodcat"  method="post" action="Administration?action=prodcat">
			<div class="formcont" id="fourth">
			<ul>
				<li><label for="codeProduct">Codice Prodotto:</label></li>
				<li><input type="text" name="codeProduct" size="20" maxlength="20" required/></li>
				<li><label  id="result" ></label></li>
				<li><label for="categoriain">Seleziona Categoria:</label></li>
				<li><select name="categoriain" id="categoriain" required>
					<% 
			categorie = (Collection<?>) request.getAttribute("categorie");

				
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
				
				</select></li>
			</ul>
				<input type="submit" value="aggiorna">
			</div>
		</form>
		<div>
		<input type="button" onclick="window.location.href='./userpage.jsp'" value="Visualizza Pagina Utente" class="starters"/>
		</div>

		</div>
		<footer id="last">Copyright &copy; 2020 All Rights Reserved - Unisa - Project HiTech - TSW - Gioacchino Saraceno - Antonio Esposito - IS - Antonio Esposito - Maurizio Maria Fabrocile</footer>
</body>
</html>