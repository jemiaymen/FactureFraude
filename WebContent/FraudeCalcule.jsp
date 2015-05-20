<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,com.model.*"%>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Agent | Calcul Fraude</title>

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
					<li ><a href="Doc">Gere Dossier</a></li>
					<li class='active'><a href="CalculFraude">Calcule Fraude</a></li>
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
			
			
			<%
									@SuppressWarnings("unchecked")
		    						List<Compteur> cs = (List<Compteur>) request.getAttribute("cs");
					
									
										out.println("<div class='col-lg-10'><h2>Compteurs</h2><table class='table'><th>Nom Prenom</th><th>Tel</th><th>Cin</th><th>Marque Compteur</th><th>Type Compteur</th><th>Date Activation</th><th>Action</th>");		
										for(Compteur r : cs){
										out.println("<tr>");
										out.println("<td>");
										out.println(r.getUtilisateur().getNom() + " " + r.getUtilisateur().getPrenom());
										out.println("</td>");

										
										out.println("<td>");
										out.println(r.getUtilisateur().getTel());
										out.println("</td>");
										
										out.println("<td>");
										out.println(r.getUtilisateur().getCin());
										out.println("</td>"); 										
										
										out.println("<td>");
										out.println(r.getMarque());
										out.println("</td>");
										
										out.println("<td>");
										out.println(r.getType());
										out.println("</td>");
										

										out.println("<td>");
										out.println(r.getDataactivation());
										out.println("</td>");
										
										out.println("<td> <a data-toggle='tooltip' title='Suivie Consomation' href='?suiv="+ r.getUtilisateur().getId() + "&suivc="+ r.getId() +"'><span class='glyphicon glyphicon-log-in'></span></a> | <a data-toggle='tooltip' title='Facturation' href='Facturation?cid="+ r.getId() +"'><span class='glyphicon glyphicon-usd'></span></a></td>");
										
										out.println("</tr>");
										}
										out.println("</table></div>");
									
										
					%>
					<%
									@SuppressWarnings("unchecked")
		    						List<Consomation> conso = (List<Consomation>) request.getAttribute("conso");
										
		    						if(conso != null){
		    							out.println("<div class='col-lg-10'><h2>Consomation</h2><table class='table'><th>Marque Compteur</th><th>Type Compteur</th><th>Consomation compteur</th><th>Consomation Annuel</th><th>Annee</th><th>Action</th>");		
										for(Consomation r : conso){
										out.println("<tr>");
										out.println("<td>");
										out.println(r.getCompteur().getMarque());
										out.println("</td>");

										
										out.println("<td>");
										out.println(r.getCompteur().getType());
										out.println("</td>");
										
										out.println("<td>");
										out.println(r.getConsocompteur());
										out.println("</td>"); 										
										
										out.println("<td>");
										out.println(r.getConsoannuel());
										out.println("</td>");
										
										out.println("<td>");
										out.println(r.getAnne());
										out.println("</td>");

										
										out.println("<td> <a data-toggle='tooltip' title='Calcule Fraude' href='?calc="+ r.getId() +"&suiv=" + r.getCompteur().getUtilisateur().getId() + "&suivc="+ r.getCompteur().getId()+ "'><span class='glyphicon glyphicon-list-alt'></span></a> </td>");
										
										out.println("</tr>");
										}
										out.println("</table></div>");
		    						}
		    						
		    		String tau = (String) request.getAttribute("tau");
		    		
		    		if (tau != null){
		    			out.println( "<div class='col-lg-10'><h3>" + tau + "</h3></div>");
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