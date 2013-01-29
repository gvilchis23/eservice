<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:html>
  <head>
    
    <title>Seleccion del tipo de monitoreo</title>
    
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/baz2008.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/elite.css" />
    
	<script> 
 		function habilita()
 		{
 			var habilitar = document.getElementById("fechas").checked;
 			if(habilitar){
    			document.getElementById("diaInicial").disabled = false;
    			document.getElementById("mesInicial").disabled = false;
    			document.getElementById("anioInicial").disabled = false;
				document.getElementById("diaFinal").disabled = false;
    			document.getElementById("mesFinal").disabled = false;
    			document.getElementById("anioFinal").disabled = false;
    		}
    		else{
    			document.getElementById("diaInicial").disabled = true;
    			document.getElementById("diaInicial").value = "";
    			document.getElementById("mesInicial").disabled = true;
    			document.getElementById("mesIni").selected = true;
    			document.getElementById("anioInicial").disabled = true;
    			document.getElementById("anioInicial").value = "";
    			document.getElementById("diaFinal").disabled = true;
    			document.getElementById("diaFinal").value = "";
    			document.getElementById("mesFinal").disabled = true;
    			document.getElementById("mesFin").selected = true;
    			document.getElementById("anioFinal").disabled = true;
    			document.getElementById("anioFinal").value = "";
    		} 
   		}
	</script>
  </head>
  
  <body>
  	<div id="container">
			<div id="principal">
				<table cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td>
							<div id="superior_izquierdo" align="left">
								<img src="<%=request.getContextPath()%>/images/estructura/logo.gif"/>
								<br />
							</div>
							<div id="barra_roja">
								<div id="buscador" align="right">
									<span class="texto_blanco">
										<html:link action="/LogoutAction?metodo=home" style="color: White">Cerrar Sesion</html:link>
									</span>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<table border="0" cellpadding="0" cellspacing="0" width="50%"  style="padding-left: 10px;">
								<tr  valign="top">
									<td class="fondoMenu">
										<div id="menu_all" style=" height: 100%;">
											<div id="menu_display">
												<img src="<%=request.getContextPath()%>/images/estructura/logo_monitoreo.gif" width="186" height="40" />
											</div>
										</div>					
									</td>
									<td align="center">
										<div id="content" style="height:auto;">
											  	<html:form action="ConsultaAction?metodo=realizaMonitoreo" method="post">
  													<table align="center">
	    												<thead>
	    													<tr>
												    		<td align="center"><font color="red"><html:errors/></font></td>
												    		</tr>
												    		<tr>
	    														<td align="center">
												    				<strong>
												    					Tipo de monitoreo
												    				</strong>
												    			</td>
												    		</tr>
												    	</thead>
												    	<tr>
												    		<td><input type="radio" name="consulta" value="1" checked="checked">
												    		Numero de usuarios por aplicacion:</td>
												    	</tr>
												    	<tr>
												    		<td><input type="radio" name="consulta" value="2" onclick="habilita()">
												    		Que usuarios son los que se conectan a diferentes aplicaciones:</td>
												    	</tr>
												    	<tr>
												    		<td><input type="checkbox" name="fechas" id="fechas" onclick="habilita()">
												    		Ingresar fechas	(dd/mm/aaaa) - (dd/mm/aaaa)</td>
												    	</tr>
												    	<tr>
												    		<td align="center">
												    		<input type="text" name="diaInicial" id="diaInicial" maxlength="2" size="2" disabled="disabled">
												    		<select name="mesInicial" id="mesInicial" disabled="disabled">
												    			<option id="mesIni">Mes</option>
												    			<option value="01">Ene</option>
												    			<option value="02">Feb</option>
												    			<option value="03">Mar</option>
												    			<option value="04">Abr</option>
												    			<option value="05">May</option>
												    			<option value="06">Jun</option>
												    			<option value="07">Jul</option>
												    			<option value="08">Ago</option>
												    			<option value="09">Sep</option>
												    			<option value="10">Oct</option>
												    			<option value="11">Nov</option>
												    			<option value="12">Dic</option>
												    		</select>
												    		<input type="text" name="anioInicial" id="anioInicial" maxlength="4" size="4" disabled="disabled">
												    		-
												    		<input type="text" name="diaFinal" id="diaFinal" maxlength="2" size="2" disabled="disabled">
												    		<select name="mesFinal" id="mesFinal" disabled="disabled">
												    			<option id="mesFin">Mes</option>
												    			<option value="01">Ene</option>
												    			<option value="02">Feb</option>
												    			<option value="03">Mar</option>
												    			<option value="04">Abr</option>
												    			<option value="05">May</option>
												    			<option value="06">Jun</option>
												    			<option value="07">Jul</option>
												    			<option value="08">Ago</option>
												    			<option value="09">Sep</option>
												    			<option value="10">Oct</option>
												    			<option value="11">Nov</option>
												    			<option value="12">Dic</option>
												    		</select>
												    		<input type="text" name="anioFinal" id="anioFinal" maxlength="4" size="4" disabled="disabled">
												    		</td>
												    	</tr>
												    	<tr>
												    		<td align="center">
												    			<html:submit>Monitorear</html:submit>
												    		</td>
												    	</tr>
												    </table>
											    </html:form>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<div id="pie">
								<div id="linea_azteca"></div>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html:html>