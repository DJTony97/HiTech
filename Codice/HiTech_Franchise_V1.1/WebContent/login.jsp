<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%		String role=(String)request.getSession().getAttribute("role");
	if(role!=null) {
		if(role.equals("1")) {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
			return;
		}
		else if(role.equals("9")){
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}
	 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Log-in HT</title>
<script>
function formValidation()
{
var uid = document.registration.userid;
var passid = document.registration.passid;

if(Elem_validation(uid,4,"Username")){
	if(passid_validation(passid,8)){
		return true;
	}
}
event.preventDefault(); 
validateMyForm();
return false;
} 
	
	function Elem_validation(el,min,el_name)
	{
	var el_len = el.value.length;
	if (el_len < min)
	{
	alert(el_name+' deve essere composto da almeno '+min+' caratteri!');
	el.focus();
	return false;
	}
	else{
		
		var letters = /^[0-9a-zA-Z]+$/;
		if(el.value.match(letters))
		{
		return true;
		}
		else
		{
		alert(el_name+' può contenere solo caratteri alfanumerici');
		el.focus();
		return false;
		}
		
	}
			
	}
	function passid_validation(passid,min)
{
var passid_len = passid.value.length;
if (passid_len < min)
{
alert("Password deve essere composto da almeno "+min+" caratteri!");
passid.focus();
return false;
}
return true;
}




</script>
<link rel='stylesheet' href='js-form-validation.css' type='text/css' />
</head>
<body onload="document.registration.userid.focus();">
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
<h1>Effettua il login!</h1>
<p>Inserisci i tuoi dati qui per accedere!</p>
<form name='login' onSubmit="return formValidation();" method="post" action="Login">
<ul>
<li><label for="userid">Username:</label></li>
<li><input type="text" name="userid" id="userid"size="20" maxlength="20" required /><% if(request.getAttribute("error")!=null){ %> <a> Nome Utente o Password errati. Riprova!</a><%}%></li>
<li><label for="passid">Password:</label></li>
<li><input type="password" name="passid" id="passid" size="20" maxlength="45" required /></li>
</ul>
<input type="submit" name="submit" value="Accedi!"/>
</form>
<a id="rg" href="./register.jsp"> Oppure registrati cliccando qui!</a>
</div>
<footer id="last">Copyright &copy; 2020 All Rights Reserved - Unisa - Project HiTech - IS - Antonio Esposito - Maurizio Maria Fabrocile</footer>
</body>

</html>