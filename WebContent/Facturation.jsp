<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,com.model.*"%>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Agent | Facturation</title>

<!-- Bootstrap -->
<link href="/FactureFraude/bootstrapjsp/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">


<script src="/FactureFraude/js/jquery-1.11.2.min.js"></script>
<script src="/FactureFraude/js/jquery.print.js"></script>

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
					<li ><a href="CalculFraude">Calcule Fraude</a></li>
					<li class='active'><a href="Facturation">Facturation</a></li>
				</ul>
				<ul class="nav navbar-nav pull-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <%
 	Utilisateur u = (Utilisateur) request.getAttribute("user");
	Compteur c = (Compteur) request.getAttribute("c");
	Facture fact = (Facture) request.getAttribute("fact");
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
			
			<div class="col-lg-4">

				<form class="form-signin" role="form" method="post" action="Facturation">
					<h2 class="form-signin-heading"><% if(fact != null) out.print("Modifier Facture"); else out.print("Ajouter Facture");  %></h2>
					
					<label class="sr-only">Numero de Service</label>
					 <input type="text" class="form-control" pattern="[0-9]{1,}" placeholder="Numero de Service" name="nsr" required 
					 value="<% if(fact != null) out.print(fact.getNservice());%>">
					
					<label class="sr-only">Type Service</label>
					 <input type="text" class="form-control" placeholder="Type Service" name="tsr" required
					 value="<% if(fact != null) out.print(fact.getTypeservice());%>">
					
					
					<label class="sr-only">Nombre de Moins</label>
					 <input type="text" class="form-control" pattern="[0-9]{1,}" placeholder="Nombre de Moins" name="nm" required 
					 value="<% if(fact != null) out.print(fact.getNbrmois());%>">
					
					
					<label class="sr-only">District</label>
					 <input type="text" class="form-control" placeholder="District" name="dsrt" required
					 value="<% if(fact != null) out.print(fact.getDistrict());%>">
					 
					<label class="sr-only">RIP</label>
					 <input type="text" class="form-control" pattern="[0-9]{1,}" placeholder="RIP" name="rip" required 
					 value="<% if(fact != null) out.print(fact.getRip());%>">
					
					<label class="sr-only">RIB</label>
					 <input type="text" class="form-control" pattern="[0-9]{1,}" placeholder="RIB" name="rib" required 
					 value="<% if(fact != null) out.print(fact.getRib());%>">
					
					<label class="sr-only">Detail Consomation</label>
					 <input type="text" class="form-control" pattern="[0-9]{1,}" placeholder="Detail Consomation" name="dconso" required 
					 value="<% if(fact != null) out.print(fact.getDetailconso());%>">
					
					<label class="sr-only">Total Consomation</label>
					 <input type="text" class="form-control" pattern="[0-9]{1,}" placeholder="Total Consomation" name="tconso" required 
					 value="<% if(fact != null) out.print(fact.getTotalconso());%>">
					
					<label class="sr-only">Taxe</label>
					 <input type="text" class="form-control" pattern="[0-9]{1,}" placeholder="Taxe" name="taxe" required 
					 value="<% if(fact != null) out.print(fact.getTaxe());%>">
					
					<label class="sr-only">Sold</label>
					 <input type="text" class="form-control" pattern="[0-9]{1,}" placeholder="Sold" name="sold" required 
					 value="<% if(fact != null) out.print(fact.getSold());%>">
					
					<label class="sr-only">Montan</label>
					 <input type="text" class="form-control" pattern="[0-9]{1,}" placeholder="Montan" name="mnt" required 
					 value="<% if(fact != null) out.print(fact.getMontan());%>">
					
					<input type="hidden" value='<% if(c != null) out.print(c.getId()) ; else if (fact != null) out.print(fact.getCompteur().getId()); %>' name="cid" >
					
					<input type="hidden" value='<% if (fact != null) out.print(fact.getId()); else out.print("yes"); %>' name="<% if (fact != null) out.print("fid");else out.print("fadd"); %>" >
					

					<br>
					<button class="btn btn-lg btn-info form-control" type="submit">
					<% if(fact != null) out.print("Modifier"); else out.print("Ajouter");  %>
					</button>
				</form>
			</div>
			
			<%
									@SuppressWarnings("unchecked")
		    						List<Facture> fc = (List<Facture>) request.getAttribute("fct");
					
									if (fc != null){
										out.println("<div class='col-lg-8'><h2>Factures</h2><table class='table table-bordered'><th>Nom Prenom</th><th>Compteur Marque</th><th>Montan</th><th>Detail Consomation</th><th>Total Consomation</th><th>Action</th>");		
										for(Facture r : fc){
										out.println("<tr  id='printc" + r.getId() + "'>");
										out.println("<td>");
										out.println(r.getCompteur().getUtilisateur().getNom() + " " + r.getCompteur().getUtilisateur().getPrenom());
										out.println("</td>");

										
										out.println("<td>");
										out.println(r.getCompteur().getMarque());
										out.println("</td>");
										
										out.println("<td>");
										out.println(r.getMontan());
										out.println("</td>"); 										
										
										out.println("<td>");
										out.println(r.getDetailconso());
										out.println("</td>");

										out.println("<td>");
										out.println(r.getTotalconso());
										out.println("</td>"); 
										
										out.println("<td> <a data-toggle='tooltip' title='Service Comptable' href='?comp="+ r.getId() +"'><span class='glyphicon glyphicon-send'></span></a> | <a data-toggle='tooltip' title='Modifier' href='?mod="+ r.getId() +"'><span class='glyphicon glyphicon-pencil'></span></a> | <a data-toggle='tooltip' title='Imprimer' href='?print="+ r.getId() +"'><span class='glyphicon glyphicon-print'></span></a></td>");
										
										out.println("</tr>");
										}
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
		
		<% 
			String p = (String) request.getAttribute("printsrc");
		
			if(p != null) out.print(p);
		%>
	</script>


</body>
</html>