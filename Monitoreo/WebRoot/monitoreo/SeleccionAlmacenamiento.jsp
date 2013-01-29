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
											  	<html:form action="SeleccionAction?metodo=redireccion" method="post">
  													<table align="center">
	    												<thead>
	    													<tr>
												    		<td align="center"><font color="red"><html:errors/></font></td>
												    		</tr>
												    		<tr>
	    														<td align="center">
	    															<p class="Titulo">Selección Origen de la Información</p>
	    															<br />
												    			</td>
												    		</tr>
												    	</thead>
												    	<tr>
												    		<td><input type="radio" name="opcion" value="1" checked="checked">
												    			Almacenamiento de Información a partir de 25 Agosto 2010:
												    			<br />
												    		</td>
												    	</tr>
												    	<tr>
												    		<td><input type="radio" name="opcion" value="2" onclick="habilita()">
												    			Almacenamiento de Información anterior:
												    			<br />
												    		</td>
												    	</tr>
												    	<tr>
												    		<td align="center">
												    			<br />
												    			<html:submit>Consultar</html:submit>
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