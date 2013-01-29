<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<html>
	<head>
		<title> Monitor Service </title>

		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/baz2008.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/elite.css" />

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

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
							<table border="0" cellpadding="0" cellspacing="0" width="50%"
								style="padding-left: 10px;">
								<tr valign="top">
									<td class="fondoMenu">
										<div id="menu_all" style="height: 100%;">
											<img src="<%=request.getContextPath()%>/images/estructura/logo_monitoreo.gif" width="186" height="40" />
										</div>
									</td>
									<td align="center">
										<div id="content" style="height: auto;">
											<h1 align="center">
												Lista de usuarios registrados
											</h1>
											<table border="5" align="center">
												<thead>
													<tr>
														<td align="center">
															<strong> Aplicaci&oacute;n </strong>
														</td>
														<td align="center">
															<strong> Nombre de Usuario </strong>
														</td>
													</tr>
												</thead>
												<logic:iterate id="resultadoConsulta" name="Constantes.RESULTADOS_CONSULTA">
													<tr>
														<td align="left">
															<bean:write name="resultadoConsulta" property="aplicacion" />&nbsp;
														</td>
														<td align="left">
															<bean:write name="resultadoConsulta" property="nombre" />&nbsp;
														</td>
													</tr>
												</logic:iterate>
											</table>
											<br />
											<a
												href="<%=request.getContextPath()%>/monitor/seleccionMonitoreo.jsp">
												<strong>Regresar</strong> </a>

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
