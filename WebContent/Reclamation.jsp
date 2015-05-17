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
					<li class='active'><a href="GetReclamation">Reclamation</a></li>
					<li><a href="Doc">Gere Dossier</a></li>
					<li class="dropdown "><a href="#"
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
 	Utilisateur editu = (Utilisateur) request.getAttribute("editu");	
 	String lbl="",lbltype="",lblzone="";

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
		
			<div class="col-lg-8">
				<h2>Reclamations</h2>
				<table class="table">

					<th>Nom Prenom</th>
					<th>Libelle</th>
					<th>Type Reclamation</th>
					<th>Action</th>
					<%
						@SuppressWarnings("unchecked")
							    List<Reclamation> recs = (List<Reclamation>) request.getAttribute("recs");
								if(recs != null){
									for(Reclamation r : recs){
										out.println("<tr>");
										
										out.println("<td>");
										out.println(r.getUtilisateur().getNom() + " " + r.getUtilisateur().getPrenom());
										out.println("</td>");
										
										
										out.println("<td>");
										out.println(r.getLibelle());
										out.println("</td>");
										
										
										out.println("<td>");
										out.println(r.getTyperec());
										out.println("</td>");
										
										out.println("<td><a data-toggle='tooltip' title='Verifier' href='?ver="+ r.getId() +"'><span class='glyphicon glyphicon-ok'></span></a>  | <a data-toggle='tooltip' title='Cloturer' href='?del="+ r.getId() +"'><span class='glyphicon glyphicon-trash'></span></a></td>");
										
										out.println("</tr>");
									}
								}
					%>
				</table>
			</div>
		</div>
		<div class="row">
					<%
								Reclamation r = (Reclamation) request.getAttribute("rec");
					
									if(r != null){
										out.println("<div class='col-lg-10'><h2>Detail</h2><table class='table'><th>Nom Prenom</th><th>Cin</th><th>Adress</th><th>Tel</th><th>Marque Compteur</th><th>Type Compteur</th><th>Consomation</th><th>Action</th>");		
										out.println("<tr>");
										out.println("<td>");
										out.println(r.getUtilisateur().getNom() + " " + r.getUtilisateur().getPrenom());
										out.println("</td>");
										
										
										out.println("<td>");
										out.println(r.getUtilisateur().getCin());
										out.println("</td>");
										
										out.println("<td>");
										out.println(r.getUtilisateur().getAdress());
										out.println("</td>");
										
										out.println("<td>");
										out.println(r.getUtilisateur().getTel());
										out.println("</td>"); 
										
										out.println("<td>");
										for(Compteur c : r.getUtilisateur().getCompteurs()){
											out.println(c.getMarque() + " |");
										}
										out.println("</td>");
										
										out.println("<td>");
										for(Compteur c : r.getUtilisateur().getCompteurs()){
											out.println(c.getType() + " |");
										}
										out.println("</td>");
										
										out.println("<td>");
										for(Compteur c : r.getUtilisateur().getCompteurs()){
											for(Consomation cs : c.getConsomations()){
												out.println( cs.getAnne() + ": " + cs.getConsoannuel() + " |");
											}
										}
										out.println("</td>");
										
										out.println("<td> <a data-toggle='tooltip' title='Creer dossier' href='Doc?uid="+ r.getUtilisateur().getId() +"'><span class='glyphicon glyphicon-plus'></span></a></td>");
										
										out.println("</tr>");
										out.println("</table></div>");
									}
										
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