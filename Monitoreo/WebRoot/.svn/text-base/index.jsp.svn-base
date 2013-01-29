
<%@page import="com.bancoazteca.monitoreo.utileria.Constantes"%><%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html>
	<head>
		<title>Logueo</title>
	
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
											<html:form action="LoginAction?metodo=home" method="post">
											<table width="100%" cellpadding="0" cellspacing="5">
												<tr>
													<td class="textoCorrido" valign="top" align="center">
														<p class="Titulo">LOGIN</p>
														<p>* Para poder ingresar a monitoreo se requiere estar logeado.</p>
														<p class="SubTitulo">Ingrese su usuario y contraseña.</p>
														<table width="1%" align="center" border="0" cellpadding="0" cellspacing="0">
															<tr>
																<td valign="top" align="center" background="<%=request.getContextPath()%>/images/estructura/div_login04.gif">
																	<img src="<%=request.getContextPath()%>/images/estructura/div_login01.gif" width="12" height="14"/>
																</td>
																<td class="textoBold" valign="middle" align="center" background="<%=request.getContextPath()%>/images/estructura/div_login02.gif">
																	<table width="100%" border="0" cellpadding="2" cellspacing="2">
																		<tr>
																			<td valign="middle" align="left">
																				<img src="<%=request.getContextPath()%>/images/bullets/flecha.gif" width="9" height="8"/>
																				<img src="<%=request.getContextPath()%>/images/bullets/flecha.gif" width="9" height="8"/>  
																				INGRESE A SU CUENTA  
																			</td>
																		</tr>
																	</table>
																</td>
																<td valign="top" align="center"	background="<%=request.getContextPath()%>/images/estructura/div_login05.gif">
																	<img src="<%=request.getContextPath()%>/images/estructura/div_login03.gif" width="12" height="14"/>
																</td>
															</tr>
															<tr>
																<td valign="middle" align="center" background="<%=request.getContextPath()%>/images/estructura/div_login04.gif"/>
																	<img src="<%=request.getContextPath()%>/images/estructura/div_login04.gif" width="12" height="14"/>
																</td>
																<td valign="middle" align="center">
																	<table width="180" align="center" cellpadding="0" cellspacing="3">
																		<tr>
																			<td class="textoUsuario" valign="middle" align="right">
																				Usuario:
																			</td>
																			<td valign="middle" align="left">
																				<html:text property="usuario" value=""/>															
																			</td>
																		</tr>
																		<tr>
																			<td class="textoUsuario" valign="middle" align="right">
																				Contraseña:
																			</td>
																			<td valign="middle" align="left">
																				<html:password property="contrasenia" value=""/>
																			</td>
																		</tr>
																		<tr>
																			<td colspan="2" align="center">
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
																			<td valign="middle" align="center" colspan="2">
																				<html:submit>Ingresar</html:submit>
																			</td>
																		</tr>
																	</table>
																</td>
																<td valign="middle" align="center" background="<%=request.getContextPath()%>/images/estructura/div_login05.gif"/>
																	<img src="<%=request.getContextPath()%>/images/estructura/div_login05.gif" width="12" height="14"/>
																</td>
															</tr>
															<tr>
																<td valign="middle" align="center">
																	<img src="<%=request.getContextPath()%>/images/estructura/div_login06.gif" width="12" height="14"/>
																</td>
																<td valign="middle" align="center" />
																	<img src="<%=request.getContextPath()%>/images/estructura/div_login07.gif" width="300" height="14"/>
																</td>
																<td valign="middle" align="center">
																	<img src="<%=request.getContextPath()%>/images/estructura/div_login08.gif" width="12" height="14"/>
																</td>
															</tr>
															<tr>
																<td>
																	<div id="messageBullet"></div>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
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
</html>