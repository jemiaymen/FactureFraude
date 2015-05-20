<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,com.model.*"%>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Agent | Gere Dossier</title>

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
					<li ><a href="GetReclamation">Reclamation</a></li>
					<li class='active'><a href="Doc">Gere Dossier</a></li>
					<li ><a href="CalculFraude">Calcule Fraude</a></li>
					<li ><a href="Facturation">Facturation</a></li>
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
			<div class="col-lg-6">
				<% 
				Utilisateur usr = (Utilisateur) request.getAttribute("usr");
				Dossier doc = (Dossier) request.getAttribute("doc");
				%>
				<form class="form-signin" role="form" method="post" action="Doc">
					<h2 class="form-signin-heading"> <% if(doc != null) out.print("Modifier Dossier"); else out.print("Ajouter Dossier"); %></h2>
					

					<label class="sr-only"></label> <input type="text"
						class="form-control" placeholder="Nom Prenom" name="nom" value="<% if(usr != null) out.print(usr.getNom() + " "  + usr.getPrenom() ); else if(doc != null) out.print(doc.getUtilisateur().getNom() + " "  + doc.getUtilisateur().getPrenom() ); %>" required readonly>
					<input type="hidden" name="uid" value="<% if(usr != null) out.print(usr.getId()); %>">
					<input type="hidden" name="mdid" value="<% if(doc != null) out.print(doc.getId()); %>">
					<label class="sr-only">Description</label> <input type="text"
						class="form-control" placeholder="Description" name="des" value="<% if(doc != null) out.print(doc.getDes());%>" required autofocus>
						
					<br>
					<button class="btn btn-lg btn-info form-control" type="submit">
					<% if(doc != null) out.print("Modifier"); else out.print("Ajouter"); %>
					</button>
				</form>
			</div>
			
			<%
									@SuppressWarnings("unchecked")
		    						List<Dossier> docs = (List<Dossier>) request.getAttribute("docs");
					
									
										out.println("<div class='col-lg-6'><h2>Dossiers</h2><table class='table'><th>Nom Prenom</th><th>Adress</th><th>Tel</th><th>Description Dossier</th><th>Action</th>");		
										for(Dossier r : docs){
										out.println("<tr>");
										out.println("<td>");
										out.println(r.getUtilisateur().getNom() + " " + r.getUtilisateur().getPrenom());
										out.println("</td>");

										
										out.println("<td>");
										out.println(r.getUtilisateur().getAdress());
										out.println("</td>");
										
										out.println("<td>");
										out.println(r.getUtilisateur().getTel());
										out.println("</td>"); 										
										
										out.println("<td>");
										out.println(r.getDes());
										out.println("</td>");

										
										out.println("<td> <a data-toggle='tooltip' title='Suivie' href='CalculFraude?suiv="+ r.getUtilisateur().getId() +"'><span class='glyphicon glyphicon-send'></span></a> | <a data-toggle='tooltip' title='Modifier' href='?mod="+ r.getId() +"'><span class='glyphicon glyphicon-pencil'></span></a></td>");
										
										out.println("</tr>");
										}
										out.println("</table></div>");
									
										
					%>
			
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