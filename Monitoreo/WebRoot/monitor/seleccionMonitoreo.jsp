
<%@page import="com.bancoazteca.monitoreo.utileria.Constantes"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html:html>
  <head>
    
    <title> Monitor Service </title>
    
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/baz2008.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/elite.css" />
	<script language="JavaScript" src="<%=request.getContextPath()%>/js/calendar_eu.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/calendar.css"/>


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
											  	<html:form action="ConsultaMonitorAction?metodo=realizaMonitoreo" method="post" styleId="monitoreoForm">
  													<table align="center">
												    	<tr>
	    													<td align="center">	 
												    			<p class="Titulo">Tipo de monitoreo</p>
												    			<br />
												    		</td>
												    	</tr>												    	
												    	
												    	<tr>
												    		<td align="center">
												    			Fecha Inicial: 
												    			<input type="text" name="fechaInicial" id="fechaInicial" maxlength="12" size="12" readonly="readonly" >
														    		<script language="JavaScript">
																		var A_CALTPL = {
																			'months' : ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
																			'weekdays' : ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa'],
																			'yearscroll': true,
																			'weekstart': 0,
																			'centyear'  : 70,
																			'imgpath' : '<%=request.getContextPath()%>/images/calendario/'
																		}
																		
																		new tcal ({
																			// form name
																			'formname': 'monitoreoForm',
																			// input name
																			'controlname': 'fechaInicial'
																		}, A_CALTPL );
																	
																	</script>
																&nbsp;&nbsp;
																Fecha Final: 
												    			<input type="text" name="fechaFinal" id="fechaFinal" maxlength="12" size="12" readonly="readonly" >
														    		<script language="JavaScript">
																		var A_CALTPL = {
																			'months' : ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
																			'weekdays' : ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa'],
																			'yearscroll': true,
																			'weekstart': 0,
																			'centyear'  : 70,
																			'imgpath' : '<%=request.getContextPath()%>/images/calendario/'
																		}
																		
																		new tcal ({
																			// form name
																			'formname': 'monitoreoForm',
																			// input name
																			'controlname': 'fechaFinal'
																		}, A_CALTPL );
																	
																	</script>
																<br />
												    		</td>
												    	</tr>
												    	<tr>
												    		<td>
												    			<br />
												    			<input type="radio" name="consulta" value="1" checked="checked">
												    			Total de usuarios registrados por aplicacion:</td>
												    	</tr>
												    	<tr>
												    		<td><input type="radio" name="consulta" value="2" >
												    		Lista de usuarios registrados:</td>
												    	</tr>
												    	<tr>
												    		<td><input type="radio" name="consulta" value="3" >
												    		Total de conexiones realizadas por usuario:</td>
												    	</tr>
												    	<tr>
												    		<td><input type="radio" name="consulta" value="4" >
												    		Total de operaciones realizadas:</td>
												    	</tr>
												    	<tr>
												    		<td><input type="radio" name="consulta" value="5" >
												    		Lista de operaciones realizadas por usuario:</td>
												    	</tr>
												    	<tr>
												    		<td><input type="radio" name="consulta" value="6" >
												    		Lista de operaciones realizadas por usuario con monto:</td>
												    	</tr>
												    	<tr>
															<td align="center">
																<logic:present name="<%=Constantes.ERROR %>">
																	<br />
																	<font color="red">
																		<logic:iterate id="error" name="<%=Constantes.ERROR %>">
																			<bean:write name="error" property="value"/>
																		</logic:iterate>
																	</font>
																	<br>
																</logic:present>
															</td>
														</tr>
														<tr>
															<td colspan="2" align="center">
																<font color="red">
																	<html:errors/>
																</font>
															</td>
														</tr>
												    	<tr>
												    		<td align="center">
												    			<br />
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