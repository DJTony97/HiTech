<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.HiTech.model.CittaBean" %>
    <%	
 	Collection<?> regioni = (Collection<?>) request.getAttribute("regioni");
 	String error = (String)request.getAttribute("error");String role=(String)request.getSession().getAttribute("role");
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
<html lang="en"><head>
<meta charset="utf-8">
<meta http-Equiv="Cache-Control" Content="no-cache">
<meta http-Equiv="Pragma" Content="no-cache">
<meta http-Equiv="Expires" Content="0">
<title>Register HT</title>
<link rel='stylesheet' href='js-form-validation.css' type='text/css' />
<script src="jquery-3.5.1.js" type="text/javascript">
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
	$("#submit").click(function(){
		 $('#privacy').click(function() {
			    if (!$(this).is(':checked')) {
			    	alert("la tua richiesta di registrazione e' stata presa in carico,"+
					" attendrere qualche giorno per ricevere la mail con le credenziali e le istruzioni per la verifica");
			    }
		
		
		});
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
	$('#email').keyup(function(){
		var myemail = $('#email').val();
		$.post("emailcheck",{"myemail":myemail},function(result){
			$('#result2').html(result);
		});
	});
	$('#cf').keyup(function(){
		var mycf = $('#cf').val();
		var pattern = /^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]+$/;
		if(cf.value.match(pattern)){
			$.post("cfcheck",{"mycf":mycf},function(result){
				$('#result3').html(result);
			});
		}else{
			$('#result3').html('Non hai inserito un CF');
		}
		
	});
});

</script>
<script>
function formValidation()
{
var name = document.registration.name;
var add = document.registration.address;
var cf = document.registration.cf;
var res1= document.registration.result;
var res2= document.registration.result2;

{
		if(Elem_validation(name,4,"Nome")){
			if(Elem_validation(name,4,"Cognome")){
				if(Elem_validation(add,8,"Indirizzo")){
					if(Elem_validation(cf,16,"Codice Fiscale")){
						if (res1=="Avaible" && res2=="Avaible")
							return true;
					}
				}
			}
		}
	}

returnToPreviousPage();
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
	
}
	
	
	

</script>


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
<h1>Registrati!</h1>
<p>Inserisci i tuoi dati qui per registrarti!</p>
<form name='registration'  id='registration' onSubmit="return formValidation();" method="post" action="IscrizioneFranchiser">
<ul>

<li><label for="email">Email:</label></li>
<li><label id="result2" ></label></li>
<li><input type="email" name="email" id="email" size="35" maxlength="40" required/></li>
<li><label for="name">Nome:</label></li>
<li><input type="text" name="name" size="20" maxlength="20" required/></li>
<li><label for="surname">Cognome:</label></li>
<li><input type="text" name="surname" size="20" maxlength="20" required/></li>
<li><label for="cf">CF:</label></li>
<li><label id="result3" ></label></li>
<li><input type="text" name="cf" id="cf" size="20" maxlength="16"  required/></li>
<li><label for="Address">Indirizzo:</label></li>
<li><input type="text" name="Address" size="35" maxlength="40" required/></li>
<li><label for="Regione">Seleziona Regione:</label></li>
<li><select name="Regione" id="Regione" required >
</select></li>
<li><label for="Provincia">Seleziona Provincia:</label></li>
<li><select name="Provincia" id="Provincia" required>
</select></li>
<li><label for="Cap">Seleziona Cap:</label></li>
<li><select name="Cap" id="Cap" required>
</select></li>
<li><label for="City">Seleziona Citta:</label></li>
<li><select name="City" id="City" required>
</select></li>
<li><label for="privacy"> Conferma regolamento sulla privacy</label></li>
<li><input  type="checkbox" name="privacy" id="privacy" required/></li>
</ul>
<input type="submit" name="submit" id="submit" value="Registrati"/>
</form>
</div>
<footer id="last">Copyright &copy; 2020 All Rights Reserved - Unisa - Project HiTech - IS - Antonio Esposito - Maurizio Maria Fabrocile</footer>
</body>
</html>