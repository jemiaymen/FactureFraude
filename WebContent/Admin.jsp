<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,com.model.*"%>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Administrateur | Gere Utilisateur</title>

<!-- Bootstrap -->
<link href="/FactureFraude/bootstrapjsp/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/FactureFraude/js/jquery-1.11.2.min.js"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="//oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="//oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<style media="screen" type="text/css">
body {
	padding-top: 70px;
	padding-bottom: 30px;
}

.logo {
	margin-top: -18px;
	height: 311%;
}
</style>
</head>
<body>

	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><img class="logo"
					src="/FactureFraude/img/logo.png"></a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					
					<li class="dropdown active"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown">Admin<span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="Admin">Gere Utilisateur</a></li>
							<li><a href="Signin">Valider</a></li>
						</ul></li>

				</ul>
				<ul class="nav navbar-nav pull-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <%
 	Utilisateur u = (Utilisateur) request.getAttribute("user");


  	if (u != null) {
  		out.println(u.getNom() + " " + u.getPrenom());
  	}
 %><span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="Logout">Logout</a></li>
						</ul></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<div class="container ">

		<div class="row">
			<div class="col-lg-5">

				<form class="form-signin" role="form" method="post" action="Admin">
					<h2 class="form-signin-heading">Ajouter Utilisateur</h2>

					<label>Poste</label> <select class="form-control" name="poste"
						required>
						<option value="admin">Administrateur</option>
						<option value="agent">Agent</option>
						<option value="abonnee">Abonnee</option>
					</select> <label class="sr-only">Prenom</label> <input type="text"
						class="form-control" placeholder="Prenom" name="prenom" required>

					<label class="sr-only">Nom</label> <input type="text"
						class="form-control" placeholder="Nom" name="nom" required>

					<label class="sr-only">Cin</label> <input
						title="Please enter Just number at least 8" type="text"
						class="form-control" placeholder="Cin" name="cin"
						pattern=".[0-9].{6,}" required
						onchange=" this.setCustomValidity(this.validity.patternMismatch ? this.title: ''); ">

					<input title="Password must contain at least 6 characters"
						type="password" class="form-control" placeholder="Mot de passe"
						name="pw" required pattern=".{6,}"
						onchange="
        this.setCustomValidity(this.validity.patternMismatch ? this.title : '');
        form.repw.pattern = this.value;
        ">
					<input type="password" class="form-control"
						placeholder="Re Mot de passe" name="repw" required
						title="Please enter the same Password as above"
						onchange="
        this.setCustomValidity(this.validity.patternMismatch ? this.title : '');
        ">

					<label class="sr-only">Tel</label> <input
						title="Please enter Just number at least 8" type="text"
						class="form-control" pattern=".[0-9].{6,}" placeholder="Tel"
						name="tel" required
						onchange=" this.setCustomValidity(this.validity.patternMismatch ? this.title: ''); ">



					<label class="sr-only">Adress</label>
					<textarea class="form-control" placeholder="Adress" name="adress">
					</textarea>
					<br>
					<button class="btn btn-lg btn-info form-control" type="submit">
						Ajouter</button>
				</form>
			</div>

			<div class="col-lg-7">
				<h2>Tous Utilisateurs</h2>
				<table class="table">

					<th>Nom Prenom</th>
					<th>Cin</th>
					<th>Adress</th>
					<th>Poste</th>
					<th>Tel</th>
					<th>Action</th>
					<%
						@SuppressWarnings("unchecked")
							    List<Utilisateur> users = (List<Utilisateur>) request.getAttribute("users");
								if(users != null){
									for(Utilisateur us : users){
										out.println("<tr>");
										
										out.println("<td>");
										out.println(us.getNom() + " " + us.getPrenom());
										out.println("</td>");
										
										
										out.println("<td>");
										out.println(us.getCin());
										out.println("</td>");
										
										
										out.println("<td>");
										out.println(us.getAdress());
										out.println("</td>");
										
										out.println("<td>");
										out.println(us.getPoste());
										out.println("</td>");
										
										
										
										
										
										out.println("<td>");
										out.println(us.getTel());
										out.println("</td>");
										
										
										out.println("<td> <a data-toggle='tooltip' title='Supprimer' href='?del="+us.getId() +"'><span class='glyphicon glyphicon-trash'></span></a> </td>");
										
										out.println("</tr>");
									}
								}
					%>
				</table>
			</div>
		</div>
	</div>

	<script src="/FactureFraude/bootstrapjsp/bootstrap/js/bootstrap.min.js"></script>
	<script>
		$(function() {
			$('[data-toggle="tooltip"]').tooltip()
		})
	</script>


</body>
</html>