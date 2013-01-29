<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<html>
	<head>
		<title>Usuarios Conectados a Diferentes Aplicaciones</title>

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
											<div id="menu_display">
												<img src="<%=request.getContextPath()%>/images/estructura/logo_monitoreo.gif" width="186" height="40" />
											</div>
										</div>
									</td>
									<td align="center">
										<div id="content" style="height: auto;">
											<h1 align="center">
												Usuarios conectados a diferentes aplicaciones
											</h1>
											<table border="5" align="center">
												<thead>
													<tr>
														<td align="center">
															<strong> Aplicacion </strong>
														</td>
														<td align="center">
															<strong> Nombre </strong>
														</td>
														<td align="center">
															<strong> Numero Conexiones </strong>
														</td>
													</tr>
												</thead>
												<logic:iterate id="list" name="lista">
													<tr>
														<td>
															<bean:write name="list" property="aplicacion" />
														</td>
														<td>
															<bean:write name="list" property="nombre" />
														</td>
														<td align="center">
															<bean:write name="list" property="numero_conexiones" />
														</td>
													</tr>
												</logic:iterate>
											</table>
											<a
												href="<%=request.getContextPath()%>/monitoreo/SeleccionMonitoreo.jsp">
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
