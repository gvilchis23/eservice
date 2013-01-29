<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
        
    <title>Registro de usuario exitoso!!!</title>
    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/baz2008.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/elite.css" />
    
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
								<div class="logo_banco"></div>
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
												<div id="menu" style="width: 190px; ">
													<div class="logo_bancaElite">
														<img src="<%=request.getContextPath()%>/images/estructura/logo_bancaelite.gif" width="186" height="40" />
													</div>
												</div>
											</div>
										</div>					
									</td>
									<td align="center">
										<div id="content" style="height:auto;">
											  	<strong>El registro del Usuario fue exitoso!</strong><br>
											  	<strong>Con los sigueintes datos</strong>
												<table>
											  		<tr>
											  			<td>Nombre: </td>
											  		    <td><bean:write name="registroForm" property="nombre"/></td>
											  		</tr>
											  		<tr>
											  			<td>Usuario: </td>
														<td><bean:write name="registroForm" property="usuario"/></td>
											  		</tr>
											  		<tr>
											  			<td>Contraseņa: </td>
											  			<td><bean:write name="registroForm" property="contrasenia"/></td>
											  		</tr>
											  	</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<div id="pie">
								<div id="ligas_inferiores" class="sligas_inferiores">
									<span class="sligas_infemergencias"> Emergencias: </span>&nbsp;
									<a href="#" class="sligas_inferiores stripeMe">Robo o extravio</a>&nbsp;
									l &nbsp;
									&nbsp;
									<a href="<%=request.getContextPath()%>/elite/recuperarPassword.do?method=init" class="sligas_inferiores stripeMe">Recuperaci&oacute;n de contrase&ntilde;as</a>
									&nbsp; l &nbsp;
									<a href="#"	onClick="javascript:window.open('http://hhi.tzo.com/speed/banco_azteca/hellohelp.nsf/logincc2.htm?ReadForm&dni=&referer=www.bancoazteca.com.mx&pwd=&rows=&hhlang=01&UN=&CC=&EN','','hotkeys=no,screenX=0,screenY=0,left=0,top=0,width=260,height=400');" class="sligas_inferiores stripeMe"> Ayuda en L&iacute;nea
									</a>
								</div>
								<div id="linea_azteca"></div>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
  </body>
</html>
