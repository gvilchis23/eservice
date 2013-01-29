package com.bancoazteca.eservice.validator;

public interface Errors {	
	
	String VALIDATION = "Error de Validación";
	String ERROR_GENERAL = "Ha ocurrido un problema al invocar el servicio, por favor intente mas tarde.";
	int EXCEPTION_CODE = -1;
	int EXCEPTION_USER_INVALID = -101;
	int EXCEPTION_USER_BLOCKED = -102;
	int EXCEPTION_USER_EXPIRED = -103;
	int EXCEPTION_USER_OPEN_SESSION = -104;
	int SESSION_USER_NOT_FOUND_CODE = -105;
	int EXCEPTION_USER_NO_ACCOUNT_RMA = -106;
	int EXCEPTION_USER_NO_PAYMENT_RMA = -107;
	int SESSION_APPLICATION_NOT_FOUND_CODE = -201;
	int SESSION_APPLICATION_EXPIRED = -202;
	int VALIDATION_CODE = -300;
	int EXCEPTION_COMMAND_INVALID = -400;
	int EXCEPTION_NEED_ANOTHER_COMMAND = -401;
	int EXCEPTION_CIPHER = -500;
		
	String LOGIN_USUARIO_ERROR = "validator.login.usuario";
	String LOGIN_PASSWORD_ERROR = "validator.login.password";
	String APPLICATION_SESSION_ERROR = "login.application.session.error";
	String LOGIN_APLICACION_ERROR = "validator.login.aplicacion";
	String LOGIN_APLICACION_CERTIFICADO_ERROR = "validator.login.aplicacion.certificado";
	String LOGIN_USUARIO_ACCESO_DENEGADO = "validator.login.acceso.denegado";
	String PAGO_SERVICIO_REFERENCIA_LONGITUD ="pago.servicio.referencia.longitud.";
	
	String MOVIMIENTOS_NUMERO_CUENTA_ERROR = "validator.movimientos.cuenta";
	String MOVIMIENTOS_NUMERO_CUENTA_TAMANIO_ERROR = "validator.movimientos.cuenta.tamanio";
	String MOVIMIENTOS_NUMERO_CUENTA_NUMERICO_ERROR = "validator.movimientos.cuenta.caracter";
	
	String TARJETA_CUENTA_ERROR = "validator.recupera_password.tarjeta_cuenta";
	String NIP_ERROR = "validator.recupera_password.nip";
	String DIA_ERROR = "validator.recupera_password.dia_nacimiento";
	String MES_NACIMIENTO_ERROR = "validator.recupera_password.mes_nacimiento";
	String ANIO_NACIMIENTO_ERROR = "validator.recupera_password.anio_nacimiento";
	String NOMBRES_ERROR = "validator.recupera_password.nombres";
	String APELLIDO_PATERNO_ERROR = "validator.recupera_password.apellido_paterno";
	String APELLIDO_MATERNO_ERROR = "validator.recupera_password.apellido_materno";
	String NEW_PASSWORD_ERROR = "validator.recupera_password.new_password";
	
	String CUENTAS_ERROR = "validator.cuentas";
	String CONCEPTO_ERROR = "validator.concepto";
	String IMPORTE_ERROR = "validator.importe";
	String CUENTAS_NUMERICAS_ERROR = "validator.cuentas.numericas";
	String IMPORTE_DECIMAL_ERROR = "validator.importe.decimal";
	String NUMERO_REFERENCIA_ERROR = "validator.numero.referencia";
	String CLAVE_SEGURIDAD = "validator.clave.seguridad";
	String HUELLA_SEGURIDAD = "validator.huella.seguridad";
	String OPCION_SEGURIDAD = "validator.opcion.seguridad";
	String ID_PREGUNTA = "validator.id.pregunta";
	
	//PAGO SERVICIOS SKY
	String PAGOSERVICIOS_SKY_CUENTA_ERROR = "validator.payment.sky.account.empty";
	String PAGOSERVICIOS_SKY_CUENTA_REF_ERROR = "validator.payment.sky.cuentaReferencia.empty";
	String PAGOSERVICIOS_SKY_CUENTA_REF_CARACTER_ERROR = "validator.payment.sky.cuentaReferencia.character";
	String PAGOSERVICIOS_SKY_CUENTA_REF_SIZE_ERROR = "validator.payment.sky.cuentaReferencia.size";
	String PAGOSERVICIOS_SKY_MONTO_ERROR = "validator.payment.sky.amount";
	String PAGOSERVICIOS_SKY_FORMATOMONTO_ERROR = "validator.payment.sky.amountFormat";
	String PAGOSERVICIOS_SKY_CLAVE_SEGURIDAD = "validator.payment.sky.claveSeguridad.empty";
	String PAGOSERVICIOS_SKY_OPCION_SEGURIDAD = "validator.payment.sky.opcionSeguridad.empty";
	
	String TRANSFERENCIAS_SPEI_CUENTAS_ERROR = "validator.transferencias.spei.cuentas";
	String TRANSFERENCIAS_SPEI_CUENTAS_NUMERICAS_ERROR = "validator.transferencias.spei.cuentas.numericas";
	String TRANSFERENCIAS_SPEI_CUENTA_TAMANIO_ERROR="validator.transferencias.spei.cuenta.tamanio";
	String TRANSFERENCIAS_SPEI_RFC_CURP_ERROR="validator.transferencias.spei.rfccurp.error";
	String TRANSFERENCIAS_SPEI_REFERENCIA_ERROR="validator.transferencias.spei.referencia";
	String TRANSFERENCIAS_SPEI_IMPORTE_ERROR="validator.transferencias.spei.importe";
	String TRANSFERENCIAS_SPEI_IMPORTE_DECIMAL_ERROR="validator.transferencias.spei.importe.decimal";
	String TRANSFERENCIAS_SPEI_SEGURIDAD_ERROR="validator.transferencias.spei.seguridad";
	String TRANSFERENCIAS_SPEI_CLAVESEGURIDAD_ERROR="validator.transferencias.spei.claveseguridad";
	String TRANSFERENCIAS_SPEI_FECHA_APLICACION_REQUERIDA="validator.transferencias.spei.fecha.aplicacion.requerida";
	String ESTADO_DE_CUENTA_ANIO_REQUERIDO="validator.estado.cuenta.anio.requerido";
	String TRANSFERENCIAS_SPEI_FECHA_APLICACION_INCORRECTA="validator.transferencias.spei.fecha.aplicacion.forma";
	String TRANSFERENCIAS_SPEI_FECHA_APLICACION_INVALIDA = "validator.transferencias.spei.fecha.aplicacion.invalida";

	String TRANSFERENCIAS_INTERNACIONALES_CUENTAS_ERROR = "validator.transferencias.internacionales.cuentas";
	String TRANSFERENCIAS_INTERNACIONALES_CUENTAS_NUMERICAS_ERROR = "validator.transferencias.internacionales.cuentas.numericas";
	String TRANSFERENCIAS_INTERNACIONALES_CUENTA_TAMANIO_ERROR="validator.transferencias.internacionales.cuenta.tamanio";
	String TRANSFERENCIAS_INTERNACIONALES_CONCEPTO_ERROR="validator.transferencias.internacionales.concepto";
	String TRANSFERENCIAS_INTERNACIONALES_IMPORTE_ERROR="validator.transferencias.internacionales.importe";
	String TRANSFERENCIAS_INTERNACIONALES_SEGURIDAD_ERROR="validator.transferencias.internacionales.seguridad";
	String TRANSFERENCIAS_INTERNACIONALES_CLAVESEGURIDAD_ERROR="validator.transferencias.internacionales.claveseguridad";
	String TRANSFERENCIAS_INTERNACIONALES_CLAVE_BANCO_EMPTY = "validator.transferencias.internacionales.clave.banco.empty";
	String TRANSFERENCIAS_INTERNACIONALES_NOMBRE_BENEFICIARIO_EMPTY = "validator.transferencias.internacionales.nombre.beneficiario.empty";
	String TRANSFERENCIAS_INTERNACIONALES_APELLIDO_PATERNO_BENEFICIARIO_EMPTY = "validator.transferencias.internacionales.apellido.paterno.beneficiario.empty";
	String TRANSFERENCIAS_INTERNACIONALES_NACIONALIDAD_BENEFICIARIO_EMPTY = "validator.transferencias.internacionales.nacionalidad.beneficiario.empty";
	String TRANSFERENCIAS_INTERNACIONALES_CONCEPTO_TRANSFERENCIA_EMTPY = "validator.transferencias.internacionales.concepto.transferencia.empty";
	String TRANSFERENCIAS_INTERNACIONALES_IMPORTE_DOLARES_EMPTY = "validator.transferencias.internacionales.importe.dolares.empty";
	String TRANSFERENCIAS_INTERNACIONALES_IMPORTE_DOLARES_ERROR = "validator.transferencias.internacionales.importe.dolares.error";
	
	String TRASPASOS_TERCEROS_CUENTAS_ERROR = "validator.traspasos.terceros.cuentas";
	String TRASPASOS_TERCEROS_CUENTAS_NUMERICAS_ERROR = "validator.traspasos.terceros.cuentas.numericas";
	String TRASPASOS_TERCEROS_CUENTA_TAMANIO_ERROR="validator.traspasos.terceros.cuenta.tamanio";
	String TRASPASOS_TERCEROS_RFC_CURP_ERROR="validator.traspasos.terceros.rfccurp.error";
	String TRASPASOS_TERCEROS_CONCEPTO_ERROR="validator.traspasos.terceros.concepto";
	String TRASPASOS_TERCEROS_IMPORTE_ERROR="validator.traspasos.terceros.importe";
	String TRASPASOS_TERCEROS_IMPORTE_DECIMAL_ERROR="validator.traspasos.terceros.importe.decimal";
	String TRASPASOS_TERCEROS_SEGURIDAD_ERROR="validator.traspasos.terceros.seguridad";
	String TRASPASOS_TERCEROS_CLAVESEGURIDAD_ERROR="validator.traspasos.terceros.claveseguridad";
	
	String PAGO_SERVICIOS_TELMEX_CUENTAS_ERROR = "validator.pagoservicios.telmex.cuentas";
	String PAGO_SERVICIOS_TELMEX_CUENTAS_NUMERICAS_ERROR = "validator.pagoservicios.telmex.cuentas.numericas";
	String PAGO_SERVICIOS_TELMEX_CUENTA_TAMANIO_ERROR="validator.pagoservicios.telmex.cuenta.tamanio";
	String PAGO_SERVICIOS_TELMEX_IMPORTE_ERROR="validator.pagoservicios.telmex.importe";
	String PAGO_SERVICIOS_TELMEX_IMPORTE_DECIMAL_ERROR="validator.pagoservicios.telmex.importe.decimal";
	String PAGO_SERVICIOS_TELMEX_SEGURIDAD_ERROR="validator.pagoservicios.telmex.seguridad";
	String PAGO_SERVICIOS_TELMEX_CLAVESEGURIDAD_ERROR="validator.pagoservicios.telmex.claveseguridad";
	String PAGO_SERVICIOS_TELMEX_TELEFONO_ERROR="validator.pagoservicios.telmex.telefono";
	String PAGO_SERVICIOS_TELMEX_TELEFONO_NUMERICAS_ERROR="validator.pagoservicios.telmex.telefono.numericas";
	String PAGO_SERVICIOS_TELMEX_VERIFICADOR_ERROR="validator.pagoservicios.telmex.verificador";
	String PAGO_SERVICIOS_TELMEX_VERIFICADOR_NUMERICAS_ERROR="validator.pagoservicios.telmex.verificador.numericas";
	String PAGO_SERVICIOS_TELMEX_VERIFICADOR_TAMANIO_ERROR="validator.pagoservicios.telmex.verificador.tamanio";

	//Errores de pago de servcio
	String REFERENCIA_DE_RECIBO_ERROR= "error.payment.maxigas.reference.empty";
	String SELECCCION_DE_CUENTA_DE_CARGO_ERROR="error.payment.maxigas.acount.empty";
	String IMPORTE_PARA_OPERACION_ERROR="error.payment.maxigas.payment.empty";
	String IMPORTE_TIPO_NUMERICO_ERROR="error.payment.maxigas.payment.character";
	String IMPORTE_CERO_ERROR="error.payment.maxigas.payment.zero";
	String LONGITUD_DE_REFERENCIA_ERROR="error.payment.maxigas.reference.size";
	String REFERENCIA_NUMERICA_ERROR="error.payment.maxigas.reference.character";
	String CONCEPTO_PAGO_ERROR="error.payment.maxigas.conceptopago";
	String CLAVE_SEGURIDAD_ERROR="error.payment.maxigas.claveseguridad";
	String OPCION_SEGURIDAD_ERROR="error.payment.maxigas.opcionseguridad";
	
	String ERROR_TELEFONO_FRECUENTE_VACIO="error.pagos.tiempoAire.telefonoFrecuente.vacio";
	String ERROR_TELEFONO_NO_NUMERICO="error.pago.tiempoAire.telefono.noNumerico";
	String ERROR_LONGITUD_NUMERO_TELEFONICO="error.pago.tiempoAire.telefono.longitud";
	
	String PAGO_SERVICIOS_MOVISTAR_CUENTAS_ERROR = "validator.pago.servicio..movistar.cuentas.error";
	String PAGO_SERVICIOS_MOVISTAR_CUENTAS_DECIMAL_ERROR = "validator.pago.servicio..movistar.cuentas.decimal";
	String PAGO_SERVICIOS_MOVISTAR_NUMERO_REFERENCIA_ERROR = "validator.pago.servicio..movistar.referencia.error";
	String PAGO_SERVICIOS_MOVISTAR_NUMERO_REFERENCIA_DECIMAL_ERROR = "validator.pago.servicio..movistar.referencia.decimal";
	String PAGO_SERVICIOS_MOVISTAR_CONCEPTO_PAGO_ERROR = "validator.pago.servicio..movistar.pago.error";
	String PAGO_SERVICIOS_MOVISTAR_IMPORTE_ERROR = "validator.pago.servicio..movistar.importe.error";
	String PAGO_SERVICIOS_MOVISTAR_IMPORTE_DECIMAL_ERROR = "validator.pago.servicio..movistar.importe.decimal";
	String PAGO_SERVICIOS_MOVISTAR_OPCION_SEGURIDAD_ERROR = "validator.pago.servicio..movistar.opcion.seguridad.error";
	String PAGO_SERVICIOS_MOVISTAR_CLAVE_SEGURIDAD_ERROR = "validator.pago.servicio..movistar.clave.seguridad.error";
	
	String PAGO_SERVICIOS_PARAMETRIZABLE_CUENTAS_ERROR = "validator.pago.servicio.parametrizable.cuentas.error";
	String PAGO_SERVICIOS_PARAMETRIZABLE_CUENTAS_DECIMAL_ERROR = "validator.pago.servicio.parametrizable.cuentas.decimal";
	String PAGO_SERVICIOS_PARAMETRIZABLE_NUMERO_REFERENCIA_ERROR = "validator.pago.servicio.parametrizable.referencia.error";
	String PAGO_SERVICIOS_PARAMETRIZABLE_NUMERO_REFERENCIA_DECIMAL_ERROR = "validator.pago.servicio.parametrizable.referencia.decimal";
	String PAGO_SERVICIOS_PARAMETRIZABLE_CONCEPTO_PAGO_ERROR = "validator.pago.servicio.parametrizable.pago.error";
	String PAGO_SERVICIOS_PARAMETRIZABLE_IMPORTE_ERROR = "validator.pago.servicio.parametrizable.importe.error";
	String PAGO_SERVICIOS_PARAMETRIZABLE_IMPORTE_DECIMAL_ERROR = "validator.pago.servicio.parametrizable.importe.decimal";
	String PAGO_SERVICIOS_PARAMETRIZABLE_OPCION_SEGURIDAD_ERROR = "validator.pago.servicio.parametrizable.opcion.seguridad.error";
	String PAGO_SERVICIOS_PARAMETRIZABLE_NUMERO_REFERENCIA_LONGITUD = "validator.pago.servicio.parametrizable.opcion.longitud";
	String PAGO_SERVICIOS_PARAMETRIZABLE_NUMERO_REFERENCIA_LONGITUD_VAR = "validator.pago.servicio.parametrizable.opcion.longitud.var";
	String PAGO_SERVICIOS_PARAMETRIZABLE_NUMERO_REFERENCIA_LONGITUD_VARIABLE = "validator.pago.servicio.parametrizable.opcion.longitud.variable";	
	String PAGO_SERVICIOS_PARAMETRIZABLE_DIGITO_VERIFICADOR_EMPTY = "validator.pago.servicio.parametrizable.digito.verificador.empty";
	String PAGO_SERVICIOS_PARAMETRIZABLE_DIGITO_VERIFICADOR_ERROR = "validator.pago.servicio.parametrizable.digito.verificador.error";
	String PAGO_SERVICIOS_PARAMETRIZABLE_DIGITO_VERIFICADOR_TAMANIO = "validator.pago.servicio.parametrizable.digito.verificador.tamanio";
	
	String PAGO_LUZ_CUENTA_VACIA = "error.payment.luz.reference.empty";
	String PAGO_LUZ_CUENTA_ERROR_LONGUITUD ="error.payment.luz.reference.size";
	String PAGO_LUZ_CUENTA_ERROR_NO_NUMERICO= "error.payment.luz.reference.character";
	String PAGO_LUZ_FECHA_REQUERIDA= "error.payment.luz.date.empty";
	String PAGO_LUZ_FALTA_CUENTA_A_CARGO = "error.payment.luz.account.empty";
	String PAGO_LUZ_CUENTA_SIN_SALDO = "error.payment.luz.account.zero";
	String PAGO_LUZ_FALTA_IMPORTE= "error.payment.luz.payment.empty";
	String PAGO_LUZ_IMPORTE_NUMERICO= "error.payment.luz.payment.character";
	String PAGO_LUZ_IMPORTE_MAYOR_A_CERO = "error.payment.luz.payment.zero";
	String PAGO_LUZ_CLAVE_SEGURIDAD = "validator.pago.servicio.iusacell.clave.seguridad";
	String PAGO_LUZ_OPCION_SEGURIDAD = "validator.pago.servicio.iusacell.opcion.seguridad";
	String PAGO_SERVICIOS_LUZ_RESPONSE = "EliteConstantes.PAGO_SERVICIOS_LUZ_RESPONSE";
	
	String DONATIVOS_CUENTAS_ERROR = "validator.donativos.cuentas.error";
	String DONATIVOS_CUENTAS_DECIMAL_ERROR = "validator.donativos.cuentas.decimal";
	String DONATIVOS_IMPORTE_ERROR = "validator.donativos.importe.error";
	String DONATIVOS_IMPORTE_DECIMAL_ERROR = "validator.donativos.importe.decimal";
	String DONATIVOS_OPCION_SEGURIDAD_ERROR = "validator.donativos.opcion.seguridad.error";
	String DONATIVOS_CLAVE_SEGURIDAD_ERROR = "validator.donativos.clave.seguridad.error";
	String DONATIVOS_HUELLA_SEGURIDAD_ERROR = "validator.donativos.huella.seguridad.error";

	
	String PAGO_SERVICIOS_FRECUENTES_AGREGA_REFERENCIA_ERROR = "validator.frecuentes.pagoservicios.referencia";
	String PAGO_SERVICIOS_FRECUENTES_AGREGA_REFERENCIA_NUMERICAS_ERROR = "validator.frecuentes.pagoservicios.referencia.numericas";
	String PAGO_SERVICIOS_FRECUENTES_AGREGA_SERVICIO_ERROR="validator.frecuentes.pagoservicios.servicio";
	String PAGO_SERVICIOS_FRECUENTES_AGREGA_SERVICIO_INEXISTENTE_ERROR="validator.frecuentes.pagoservicios.servicio.inexistente";	
	String PAGO_SERVICIOS_FRECUENTES_AGREGA_VERIFICADOR_NUMERICAS_ERROR="validator.frecuentes.pagoservicios.verificador.numericas";
	String PAGO_SERVICIOS_FRECUENTES_AGREGA_VERIFICADOR_TAMANIO_ERROR="validator.frecuentes.pagoservicios.referencia.tamanio.error";
	String PAGO_SERVICIOS_FRECUENTES_AGREGA_VERIFICADOR_ESTATUS_EMPTY="validator.pagoservicios.verificador.estatus.vacio";

	String PAGO_SERVICIOS_FRECUENTES_CONSULTA_SERVICIO_ERROR="validator.frecuentes.pagoservicios.servicio";
	String PAGO_SERVICIOS_FRECUENTES_CONSULTA_SERVICIO_INEXISTENTE_ERROR="validator.frecuentes.pagoservicios.servicio.inexistente";
	
	String FRECUENTES_TRASPASOS_ALIAS_ERROR = "validator.frecuentes.traspasos.alias";
	String FRECUENTES_TRASPASOS_CUENTA_ERROR = "validator.frecuentes.traspasos.cuenta";
	String FRECUENTES_TRASPASOS_CLAVE_SEGURIDAD = "validator.frecuentes.traspasos.claveseguridad";
	String FRECUENTES_TRASPASOS_OPCION_SEGURIDAD = "validator.frecuentes.traspasos.opcionseguridad";
		
	String PAGO_SERVICIOS_FRECUENTES_ALIAS_BENEFICIARIO_ERROR ="validator.pago.servicios.frecuentes.alias.beneficiario.error";
	String PAGO_SERVICIOS_FRECUENTES_TOKEN_ERROR ="validator.pago.servicios.frecuentes.token.error";

	String PAGO_SERVICIOS_FRECUENTES_NUMERO_TARJETA_ERROR = "validator.pago.servicios.frecuentes.numero.tarjeta.error";
	String PAGO_SERVICIOS_FRECUENTES_NUMERO_TARJETA_NUMERICA_ERROR = "validator.pago.servicios.frecuentes.numero.tarjeta.numerica.error";
	String PAGO_SERVICIOS_FRECUENTES_NO_POSIBLE_ERROR = "validator.pago.servicios.frecuentes.no.posible.error";
	String PAGO_SERVICIOS_FRECUENTES_NUMERO_TARJETA_LONGITUD_ERROR = "validator.pago.servicios.frecuentes.numero.tarjeta.longitud.error";
	
	String PAGO_SERVICIOS_COMPRA_TIEMPO_AIRE_CUENTA_CARGO = "validator.pago.servicio.compraTiempoAire.cuenta.cargo";
	String PAGO_SERVICIOS_COMPRA_TIEMPO_AIRE_CUENTA_CARGO_NUMERICA_ERROR = "validator.pago.servicio.compraTiempoAire.cuenta.cargo.numerica";
	String PAGO_SERVICIO_COMPRA_TIEMPO_AIRE_MONTO_ERROR = "validator.pago.servicio.compraTiempoAire.monto";
	String PAGO_SERVICIO_COMPRA_TIEMPO_AIRE_MONTO_DECIMAL_ERROR = "validator.pago.servicio.compraTiempoAire.monto.decimal";
	String PAGO_SERVICIO_COMPRA_TIEMPO_AIRE_CLAVE_SEGURIDAD = "validator.pago.servicio.compraTiempoAire.clave.seguridad";
	String PAGO_SERVICIO_COMPRA_TIEMPO_AIRE_OPCION_SEGURIDAD = "validator.pago.servicio.compraTiempoAire.opcion.seguridad";
	String PAGO_SERVICIO_COMPRA_TIEMPO_AIRE_NUMERO_TELEFONICO_ERROR = "error.pago.tiempoAire.telefono.longitud";
	String PAGO_SERVICIO_COMPRA_TIEMPO_AIRE_NUMERO_TELEFONICO_DECIMAL_ERROR = "error.pago.tiempoAire.telefonoFrecuente.vacio";
	String PAGO_SERVICIO_COMPRA_TIEMPO_AIRE_CARRIER_ERROR = "validator.pago.servicio.compraTiempoAire.carrier";
	
	String FRECUENTES_TRANSFERENCIA_NUMERO_CUENTA_ERROR = "validator.frecuente.transferencia.cuenta";
	String FRECUENTES_TRANSFERENCIA_NUMERO_CUENTA_TAMANIO_ERROR = "validator.frecuente.transferencia.cuenta.tamanio";
	String FRECUENTES_TRANSFERENCIA_NUMERO_CUENTA_NUMERICO_ERROR = "validator.frecuente.transferencia.cuenta.caracter";	
	String FRECUENTES_TRANSFERENCIA_NUMERO_BENEFICIARIO_ERROR = "validator.frecuente.transferencia.beneficiario";
	String FRECUENTES_TRANSFERENCIA_NUMERO_SWIFT_ERROR = "validator.frecuente.transferencia.swif";
	String FRECUENTES_TRANSFERENCIA_NUMERO_CLAVE_SEGURIDAD_ERROR = "validator.frecuente.transferencia.clave";
	String FRECUENTES_TRANSFERENCIA_NUMERO_OPCION_SEGURIDAD_ERROR = "validator.frecuente.transferencia.opcion.seguridad";
	String FRECUENTES_TRANSFERENCIA_ALIAS="validator.frecuente.transferencia.alias";
	String FRECUENTES_TRANSFERENCIA_ALIAS_ALFANUMERICO="validator.frecuente.transferencia.alias.alfanumerico";
	String FRECUENTES_TRANSFERENCIA_NOMBRE_LETRAS="validator.frecuente.transferencia.nombre.letras";
	
	String PAGO_SERVICIOS_AZTECAWEB_REFERENCIA_DE_RECIBO_VALIDATOR= "validator.payment.aztecaweb.reference.empty";
	String PAGO_SERVICIOS_AZTECAWEB_SELECCCION_DE_CUENTA_DE_CARGO_VALIDATOR="validator.payment.aztecaweb.acount.empty";
	String PAGO_SERVICIOS_AZTECAWEB_IMPORTE_PARA_OPERACION_VALIDATOR="validator.payment.aztecaweb.payment.empty";
	String PAGO_SERVICIOS_AZTECAWEB_IMPORTE_TIPO_NUMERICO_VALIDATOR="validator.payment.aztecaweb.payment.character";
	String PAGO_SERVICIOS_AZTECAWEB_IMPORTE_CERO_VALIDATOR="validator.payment.aztecaweb.payment.zero";
	String PAGO_SERVICIOS_AZTECAWEB_LONGITUD_DE_REFERENCIA_VALIDATOR="validator.payment.aztecaweb.reference.size";
	String PAGO_SERVICIOS_AZTECAWEB_REFERENCIA_NUMERICA_VALIDATOR="validator.payment.aztecaweb.reference.character";
	String PAGO_SERVICIOS_AZTECAWEB_CONCEPTO_PAGO_VALIDATOR="validator.payment.aztecaweb.conceptopago";
	String PAGO_SERVICIOS_AZTECAWEB_CLAVE_SEGURIDAD_VALIDATOR="validator.payment.aztecaweb.claveseguridad";
	String PAGO_SERVICIOS_AZTECAWEB_OPCION_SEGURIDAD_VALIDATOR="validator.payment.aztecaweb.opcionseguridad";
	
	
	String CATALOGOS_CIUDADES_PAIS_NULO="validator.catalogos.pais.empty";
	
	String SWIFT_ABA_PAIS_NULO="validator.swiftaba.pais.empty";
	String SWIFT_ABA_CIUDADES_NULO="validator.swiftaba.ciudad.empty";
	
	String ESTADO_CUENTA_ERROR = "validator.estado.cuenta.cuenta.error";
	String ESTADO_CUENTA_DECIMAL_ERROR = "validator.estado.cuenta.cuenta.decimal";
	String ESTADO_CUENTA_PERIODO_ERROR = "validator.estado.cuenta.periodo.error";
	String CONSULTA_FRECUENTES_TRANSFERENCIAS_TIPO_NULO="validator.frecuentes.transferencias.tipo.nulo";
	String CONSULTA_FRECUENTES_TRANSFERENCIAS_TIPO_INVALIDO="validator.frecuentes.transferencias.tipo.invalido";
	String ESTADO_CUENTA_ANIO_VALIDATION = "validator.estado.cuenta.anio";
	String ESTADO_CUENTA_MES_VALIDATION = "validator.estado.cuenta.mes";
	
	String TRANSFERENCIAS_SPEI_BANCO_INVALIDO = "Por favor ingrese un Banco valido.";
	
	String VALIDATOR_CONSULTA_MISFINANZAS_CUENTA_EMPTY="validator.consulta.misFinanzas.cuenta.empty";
	String VALIDATOR_CONSULTA_MISFINANZAS_RANGO_EMPTY="validator.consulta.misFinanzas.rango.empty";
	String VALIDATOR_CONSULTA_MISFINANZAS_FECHA_EMPTY="validator.consulta.misFinanzas.fecha.empty";
	String VALIDATOR_CONSULTA_MISFINANZAS_FECHA_INICIAL_EMPTY = "validator.consulta.misFinanzas.fecha.inicial.empty";
	String VALIDATOR_CONSULTA_MISFINANZAS_FECHA_FINAL_EMPTY = "validator.consulta.misFinanzas.fecha.final.empty";
	String VALIDATOR_CONSULTA_MISFINANZAS_FECHA_FORMATO_ERROR  = "bibliotecarecibos.fecha.formato";
	String VALIDATOR_CONSULTA_MISFINANZAS_FECHAS_COMPARACION_ERROR = "validator.consulta.misFinanzas.fechas.comparacion.error";
	String VALIDATOR_CONSULTA_MISFINANZAS_FECHA_ACTUAL_COMPARACION_ERROR = "validator.consulta.misFinanzas.fecha.actual.comparacion.error";
	String VALIDATOR_CONSULTA_MISFINANZAS_CUENTA_INVALID = "validator.consulta.misFinanzas.cuenta.invalid";
	
	String VALIDATOR_PAYMENT_PAGOTARJETACREDITO_ACCOUNT_EMPTY="validator.payment.pagoTarjetaCredito.account.empty";
	String VALIDATOR_PAYMENT_PAGOTARJETACREDITO_TARJETA_EMPTY="validator.payment.pagoTarjetaCredito.tarjeta.empty";
	String VALIDATOR_PAYMENT_PAGOTARJETACREDITO_PAYMENT_EMPTY="validator.payment.pagoTarjetaCredito.payment.empty";
	String VALIDATOR_PAYMENT_PAGOTARJETACREDITO_PAYMENT_CHARACTER="validator.payment.pagoTarjetaCredito.payment.character";
	String VALIDATOR_PAYMENT_PAGOTARJETACREDITO_PAYMENT_ZERO="validator.payment.pagoTarjetaCredito.payment.zero";
	String VALIDATOR_PAYMENT_PAGOTARJETACREDITO_SECURITY_CLAV_EMPTY="validator.payment.pagoTarjetaCredito.security.clav.empty";
	String VALIDATOR_PAYMENT_PAGOTARJETACREDITO_SECURITY_HUELLA_EMPTY="validator.payment.pagoTarjetaCredito.security.huella.empty";
	String VALIDATOR_PAYMENT_PAGOTARJETACREDITO_SECURITY_OPTION_EMPTY="validator.payment.pagoTarjetaCredito.security.option.empty";
	
	// Errores Reportos Plazo Tasa
	String ERROR_PLAZO_INVERSION_EMPTY="validator.reportos.consultaPlazoTasa.plazoinversion.empty";
	String ERROR_TASA_INVERSION_EMPTY="validator.reportos.consultaPlazoTasa.tasainversion.empty";
	String ERROR_FECHA_OPERACION_EMPTY="validator.reportos.consultaPlazoTasa.fechaoperacion.empty";
	String ERROR_FECHA_OPERACION_INVALID="validator.reportos.consultaPlazoTasa.fechaoperacion.invalid";
	String ERROR_HORA_OPERACION_EMPTY="validator.reportos.consultaPlazoTasa.horaoperacion.empty";
	String ERROR_HORA_OPERACION_INVALID_FORMAT="validator.reportos.consultaPlazoTasa.horaoperacion.invalid.format";
	String ERROR_HORA_OPERACION_INVALID_DATA="validator.reportos.consultaPlazoTasa.horaoperacion.invalid.data";
	
	// cuenta mercado de dinero
	String VALIDATOR_ACCOUNT_AGREE="validator.account.agree";
	String VALIDATOR_TAS_PLAZO_TASA="validator.tas.plazo_tasa";
	String VALIDATOR_TAS_CUENTA_CARGO="validator.tas.cuentacargo";
	String VALIDATOR_CUENTA_MERCADO_CLAVE_SEGURIDAD="validator.cuenta.mercado.claveseguridad";
	String VALIDATOR_CUENTA_MERCADO_OPCION_SEGURIDAD="validator.cuenta.mercado.opcionseguridad";
	String VALIDATOR_TAS_MONTO="validator.tas.monto.invalido";
	String VALIDATOR_TAS_MONTO_MINIMO="validator.tas.monto.minimo";
	String VALIDATOR_TAS_MONTO_INVALID_FORMAT="validator.tas.monto.invalid.format";
	
	//cuenta socio plus
	String VALIDATOR_MONTO_MINIMO="validator.account.agree";
	String VALIDATOR_CUENTA_ORIGEN_EMPTY="validator.cuenta.origen.empty";
	String VALIDATOR_BENEFICIARIOS_EMPRY="validator.beneficiarios.empty";
	String VALIDATOR_ACCOUNT_PARTNER_PLUS_AMOUNT="validator.account.partner.plus.amount";
	String VALIDATOR_ACCOUNT_PARTNER_PLUS_AMOUNT_DECIMAL="validator.account.partner.plus.amount.decimal";
	String VALIDATOR_ACCOUNT_PARTNER_PLUS_ZERO="validator.account.partner.plus.zero";
	String VALIDATOR_ACCOUNT_PARTNER_PLUS_ACCOUNT_AMOUNT="validator.account.partner.plus.account.amount";
	String VALIDATOR_CUENTA_CARGO_EMPTY="validator.cuenta.cargo.empty";
	String VALIDATOR_NIVELES_SEGURIDAD_NIVEL_INVALIDO="validator.niveles.seguridad.nivel.invalido";
	String VALIDATOR_NIVELES_SEGURIDAD_TRANS_MISMO_INVALIDO="validator.niveles.seguridad.transfMismo.invalido";
	String VALIDATOR_NIVELES_SEGURIDAD_TRANS_OTRO_INVALIDO="validator.niveles.seguridad.transfOtro.invalido";
	String VALIDATOR_NIVELES_SEGURIDAD_TRANS_INTER_INVALIDO="validator.niveles.seguridad.transfInter.invalido";
	String VALIDATOR_NIVELES_SEGURIDAD_PAGO_SERVICIOS_INVALIDO="validator.niveles.seguridad.pagoServicios.invalido";
	//Cambio nip
	String VALIDATOR_NIP_REQUERIDO="validator.nip.requerido";
	String VALIDATOR_NIP_NUMERIC="validator.nip.numerico";
	String VALIDATOR_CONFIRMACION_NIP="validator.confirmacion.nip";
	String VALIDATOR_NUEVO_NIP_SIN_CAMBIOS="validator.nuevo.nip.nochanges";
	
	//Activacion Express
	String VALIDATOR_ACTIVACION_EXPRESS_TARJETA_INVALIDO = "validator.activacion.tarjeta.requerido";
	String VALIDATOR_ACTIVACION_EXPRESS_LONGITUD_TARJETA_INVALIDO = "validator.activacion.longitud.tarjeta.requerido";
	String VALIDATOR_ACTIVACION_EXPRESS_OPERACION_INVALIDO = "validator.activacion.operacion.requerido";
	String VALIDATOR_ACTIVACION_EXPRESS_CLAVE_INVALIDO = "validator.activacion.clave.requerido";
	String VALIDATOR_ACTIVACION_EXPRESS_ALIAS_INVALIDO = "validator.activacion.alias.requerido";
	String VALIDATOR_ACTIVACION_EXPRESS_CONTRASENA_INVALIDO = "validator.activacion.contrasena.requerido";
	String VALIDATOR_ACTIVACION_EXPRESS_COMPARACION_CONTRASENA_INVALIDO = "validator.activacion.comparacion.contrasena.requerido";
	String VALIDATOR_ACTIVACION_EXPRESS_CONFIRMACION_INVALIDO = "validator.activacion.confirmacion.requerido";
	String VALIDATOR_ACTIVACION_EXPRESS_NUMEROTOKEN_INVALIDO = "validator.activacion.numeroToken.requerido";
	String VALIDATOR_ACTIVACION_EXPRESS_CELULAR_INVALIDO = "validator.activacion.celular.requerido";
	String VALIDATOR_ACTIVACION_EXPRESS_COMPANIA_INVALIDO = "validator.activacion.compania.requerido";	
	String VALIDATOR_ACTIVACION_EXPRESS_IDENTIFICADOR = "validator.activacion.identificador.requerido";
	
	//Envio Mail
	String VALIDATOR_ENVIO_MAIL_CORREO_ELECTRONICO = "validator.enviomail.correoelectronico.requerido";
	String VALIDATOR_ENVIO_MAIL_MENSAJE = "validator.enviomail.mensaje.requerido";
	String VALIDATOR_ENVIO_MAIL_TIPO_ENVIO = "validator.enviomail.tipoenvio.requerido";
	String VALIDATOR_ENVIO_MAIL_VALIDO= "validator.enviomail.correoelectronico.erroneo";


	//Dinero Express
	String VALIDATOR_DINERO_EXPRESS_CUENTA_CARGO = "validator.dineroexpress.cuenta_cargo.requerido";
	String VALIDATOR_DINERO_EXPRESS_CUENTA_CARGO_INVALIDO = "validator.dineroexpress.cuenta_cargo.invalido";
	String VALIDATOR_DINERO_EXPRESS_NOMBRE_BENEFICIARIO = "validator.dineroexpress.nombre_beneficiario.requerido";
	String VALIDATOR_DINERO_EXPRESS_APELLIDO_PATERNO = "validator.dineroexpress.apellido_paterno.requerido";
	String VALIDATOR_DINERO_EXPRESS_APELLIDO_MATERNO = "validator.dineroexpress.apellido_materno.requerido";
	String VALIDATOR_DINERO_EXPRESS_PAIS = "validator.dineroexpress.pais.requerido";
	String VALIDATOR_DINERO_EXPRESS_ESTADO = "validator.dineroexpress.estado.requerido";
	String VALIDATOR_DINERO_EXPRESS_CIUDAD = "validator.dineroexpress.ciudad.requerido";
	String VALIDATOR_DINERO_EXPRESS_MONTO_ENVIAR = "validator.dineroexpress.monto_enviar.requerido";
	String VALIDATOR_DINERO_EXPRESS_COMISION = "validator.dineroexpress.comision.requerido";
	String VALIDATOR_DINERO_EXPRESS_DESCUENTO = "validator.dineroexpress.descuento.requerido";
	String VALIDATOR_DINERO_EXPRESS_SUBTOTAL = "validator.dineroexpress.subtotal.requerido";
	String VALIDATOR_DINERO_EXPRESS_IMPUESTOS = "validator.dineroexpress.impuestos.requerido";
	String VALIDATOR_DINERO_EXPRESS_TOTAL_PAGAR = "validator.dineroexpress.total_pagar.requerido";
	String VALIDATOR_DINERO_EXPRESS_FECHA_NACIMIENTO = "validator.dineroexpress.fecha_nacimiento.requerido";
	String VALIDATOR_DINERO_EXPRESS_NOMBRE_CORTO = "validator.dineroexpress.nombre_corto.requerido";
	String VALIDATOR_DINERO_EXPRESS_NOMBRE_CORTO_LARGO = "validator.dineroexpress.nombrecorto.largo.requerido";
	
	String VALIDATOR_DINERO_EXPRESS_CUENTA_CORREO = "validator.dineroexpress.cuentacorreo.requerido";
	String VALIDATOR_DINERO_EXPRESS_MENSAJE_CORREO = "validator.dineroexpress.mensajecorreo.requerido";
	

    String VALIDATOR_DINERO_EXPRESS_AGENTE="validator.dineroexpress.agente.requerido";
    
	String ESTATUS_CUENTAS_CUENTA_CARGO_ERROR = "validator.estatuscuentas.cuenta_cargo.requerido";
	String ESTATUS_CUENTAS_TOKEN_ERROR = "validator.estatuscuentas.token.requerido";
	String VALIDA_TOKEN_USER_ERROR = "validator.validatoken.user.requerido";
	String VALIDA_TOKEN_TOKEN_ERROR = "validator.validatoken.token.requerido";
	
	String VALIDA_IDUSUARIO = "validator.conciliacion.idusuario";
	
	String CUENTAS_LIBERAR_NOMINA_OPCION_SEGURIDAD_TOKEN_ERROR="validator.validatoken.token.requerido";
	String CUENTAS_LIBERAR_NOMINA_OPCION_SEGURIDAD_HUELLA_ERROR="validator.valida.recibos.huella.requerida";
	String CUENTAS_LIBERAR_NOMINA_COLECCION_RECIBOS_HUELLA_ERROR="validator.valida.recibos.requeridos";
	
	String VALIDA_NUMERO_PEDIDO = "validator.rastreopedido.numero_pedido";
	String VALIDA_NUMERO_PEDIDO_DECIMAL = "validator.rastreopedido.numero_pedido.decimal";
	
	String BLOQUEO_FIRMA_NUMERO_SERIE = "validator.bloqueofirma.numero_serie";
	String BLOQUEO_FIRMA_NUMERO_SERIE_DECIMAL = "validator.bloqueofirma.numero_serie.decimal";
	String BLOQUEO_METODO = "validator.bloqueofirma.metodo";
	String BLOQUEO_METODO_INVALID = "validator.bloqueofirma.metodo.invalid";
	String BLOQUEO_TOKEN = "validator.bloqueofirma.token";
	
	String BLOQUEO_TARJETA_NUMERO = "validator.bloqueotarjeta.numerotarjeta";
	
	String ACTIVACION_FIRMA_TIPO_VALIDACION = "validator.activacion.firma.tipo_validacion";
	String ACTIVACION_FIRMA_TIPO_VALIDACION_INVALID = "validator.activacion.firma.tipo_validacion.invalid";
	String ACTIVACION_FIRMA_NUMERO_SERIE = "validator.activacion.firma.numero_serie";
	String ACTIVACION_FIRMA_NUMERO_SERIE_DECIMAL = "validator.activacion.firma.numero_serie.decimal";
	String ACTIVACION_FIRMA_NUMERO_SERIE_LONGITUD = "validator.activacion.firma.numero_serie.longitud";	
	String ACTIVACION_FIRMA_CLAVE_SEGURIDAD = "validator.activacion.firma.clave_seguridad";
	String ACTIVACION_CORREO_ELECTRONICO_VACIO = "validator.activacion.firma.correo_electronico";
	String ACTIVACION_CORREO_INVALIDO = "validator.activacion.firma.correo_invalido";
	String ACTIVACION_NUMEROS_TELEFONICOS = "validator.activacion.firma.numeros_telefonicos";
	String ACTIVACION_NUMERO_CELULAR_INVALID = "validator.activacion.firma.numero_celular.invalido";  
	String ACTIVACION_NUMERO_TELEFONO_INVALID = "validator.activacion.firma.numero_telefonico.invalido"; 
	String ACTIVACION_CONTRATO_ACEPTO = "validator.activacion.firma.contrato_acepto";
	String ACTIVACION_CONTRATO_ACEPTO_INVALID = "validator.activacion.firma.contrato_acepto.invalid";
	String ACTIVACION_CUENTA_CARGO = "validator.activacion.firma.cuenta_cargo";
	String ACTIVACION_CUENTA_CARGO_DECIMAL = "validator.activacion.firma.cuenta_cargo.decimal";
	String ACTIVACION_NIP = "validator.activacion.firma.nip";

	String SINCRONIZACION_FIRMA_TIPO_VALIDACION = "validator.sincronizacion.firma.tipo_validacion";
	String SINCRONIZACION_FIRMA_NUMERO_SERIE = "validator.sincronizacion.firma.numero_serie";
	String SINCRONIZACION_FIRMA_NUMERO_SERIE_DECIMAL = "validator.sincronizacion.firma.numero_serie.decimal";
	String SINCRONIZACION_FIRMA_CLAVE_SEGURIDAD = "validator.sincronizacion.firma.clave_seguridad";
	
	
	String VALIDA_SOLICITID_DISPOSITIVO_TIPO_DISPOSITIVO_EMPTY="validator.solicituddispositivo.tipodispositivo.empty";
	String VALIDA_SOLICITID_DISPOSITIVO_TIPO_DISPOSITIVO_INVALIDO = "validator.solicituddispositivo.tipodispositivo.invalido";
	String VALIDA_SOLICITID_DISPOSITIVO_CUENTA_CARGO_EMPTY="validator.solicituddispositivo.cuentacargo.empty";
	String VALIDA_SOLICITID_DISPOSITIVO_CUENTA_CARGO_DECIMAL = "validator.solicituddispositivo.cuentacargo.decimal";
	String VALIDA_SOLICITID_DISPOSITIVO_DIRECCION_EMPTY="validator.solicituddispositivo.direccion.empty";
	String VALIDA_SOLICITID_DISPOSITIVO_ESTADO_EMPTY="validator.solicituddispositivo.estado.empty";
	String VALIDA_SOLICITID_DISPOSITIVO_CIUDAD_EMPTY="validator.solicituddispositivo.ciudad.empty";
	String VALIDA_SOLICITID_DISPOSITIVO_COLONIA_EMPTY="validator.solicituddispositivo.colonia.empty";
	String VALIDA_SOLICITID_DISPOSITIVO_CP_EMPTY="validator.solicituddispositivo.cp.empty";
	String VALIDA_SOLICITID_DISPOSITIVO_CP_DECIMAL = "validator.solicituddispositivo.cp.decimal";
	String VALIDA_SOLICITID_DISPOSITIVO_TELEFONO_EMPTY="validator.solicituddispositivo.telefono.empty";
	String VALIDA_SOLICITID_DISPOSITIVO_TELEFONO_NUMERIC="validator.solicituddispositivo.telefono.numeric";
	String VALIDA_SOLICITID_DISPOSITIVO_TELEFONO_LENGHT="validator.solicituddispositivo.telefono.length";
	String VALIDA_SOLICITID_DISPOSITIVO_NOMBRE_1_EMPTY="validator.solicituddispositivo.nombre1.empty";
	String VALIDA_SOLICITID_DISPOSITIVO_PATERNO_1_EMPTY="validator.solicituddispositivo.paterno1.empty";
	String VALIDA_SOLICITID_DISPOSITIVO_MATERNO_1_EMPTY="validator.solicituddispositivo.materno1.empty";
	String VALIDA_SOLICITID_DISPOSITIVO_NOMBRE_2_EMPTY="validator.solicituddispositivo.nombre2.empty";
	String VALIDA_SOLICITID_DISPOSITIVO_PATERNO_2_EMPTY="validator.solicituddispositivo.paterno2.empty";
	String VALIDA_SOLICITID_DISPOSITIVO_MATERNO_2_EMPTY="validator.solicituddispositivo.materno2.empty";
	String VALIDA_SOLICITID_DISPOSITIVO_NIP_EMPTY="validator.solicituddispositivo.nip.empty";
	
		String VALIDA_ESTADO_CUENTA_MERCADO_DINERO_EMPTY = "validator.estadocuenta.mercadodinero.empty";
	String VALIDA_ESTADO_CUENTA_MERCADO_DINERO_NUMERO_CONTRATO_NUMERIC = "validator.estadocuenta.mercadodinero.numerocontrato.numeric";
	String VALIDA_ESTADO_CUENTA_MERCADO_DINERO_FECHA_INVALID = "validator.estadocuenta.mercadodinero.fecha.invalid";

	//CHEQUES Y CHEQUERAS
	String LIBERACION_CHEQUE_OPCION_SEGURIDAD_ERROR = "validator.liberacion.cheque.opcionseguridad.error";
	String LIBERACION_CHEQUE_NUMERO_CHEQUE = "validator.liberacion.cheque.numerocheque";
	String LIBERACION_CHEQUE_MONTO_CHEQUE = "validator.liberacion.cheque.montocheque";
	String LIBERACION_CHEQUE_TIPO_SOLICITUD = "validator.liberacion.cheque.tiposolicitud";
	String LIBERACION_CHEQUE_TIPO_SOLICITUD_INVALID = "validator.liberacion.cheque.tiposolicitud.invalid";
	String LIBERACION_CHEQUE_TIPO_CHEQUERA = "validator.liberacion.cheque.tipochequera";
	String LIBERACION_CHEQUE_NUMERO_CHEQUERA = "validator.liberacion.cheque.numerochequera";
	String LIBERACION_CHEQUE_NUMERO_CUENTA_EMPTY = "validator.liberacion.cheque.numerocuenta.empty";
	String LIBERACION_CHEQUE_NUMERO_CUENTA_DECIMAL = "validator.liberacion.cheque.numerocuenta.decimal";

	String ACTIVACION_CHEQUERA_OPCION_SEGURIDAD_ERROR = "validator.chequera.opcion.seguridad.error";
	String ACTIVACION_CHEQUERA_TIPO_SOLICITUD = "validator.chequera.activacion.tipo_solicitud";
	String ACTIVACION_CHEQUERA_TIPO_SOLICITUD_INVALID = "validator.chequera.activacion.tipo_solicitud.invalid";
	String ACTIVACION_CHEQUERA_CUENTA_CARGO = "validator.chequera.activacion.cuenta_cargo";
	String ACTIVACION_CHEQUERA_CUENTA_CARGO_NUMERICA = "validator.chequera.activacion.cuenta_cargo.numerica";
	String ACTIVACION_CHEQUERA_ID_CHEQUERA = "validator.chequera.activacion.chequera_id";
	String ACTIVACION_CHEQUERA_ID_CHEQUERA_NUMERICA = "validator.chequera.activacion.chequera_id.numerica";

	String LIBERACION_CHEQUERA_ID_CHEQUERA = "validator.chequera.liberacion.chequera_id";
	String LIBERACION_CHEQUERA_ID_CHEQUERA_NUMERICA = "validator.chequera.liberacion.chequera_id.numerica";
	
	String VALIDATOR_CONSULTA_CHEQUES_NUMERO_CUENTA_EMPTY="validator.consultacheques.numerocuenta.empty";
	String VALIDATOR_CONSULTA_CHEQUES_NUMERO_CUENTA_DECIMAL="validator.consultacheques.numerocuenta.decimal";
	String VALIDATOR_CONSULTA_CHEQUES_DESCRIPCION_CHEQUERA_EMPTY="validator.consultacheques.descripcionchequera.empty";
	String VALIDATOR_CONSULTA_CHEQUES_ESTADO_CHEQUES_EMPTY="validator.consultacheques.estadocheques.empty";
	String VALIDATOR_CONSULTA_CHEQUES_TIPO_SOLICITUD_EMPTY="validator.consultacheques.tiposolicitud.empty";
	String VALIDATOR_CONSULTA_CHEQUES_TIPO_SOLICITUD_INCORRECT="validator.consultacheques.tiposolicitud.incorrecto";
	
	String EXTRAVIO_CHEQUES_TIPO_VALIDACION="validator.robocheques.tipovalidacion.incorrecto";
	
	String VALIDATOR_ALERTA_CELULAR_TIPO_NUMERO_CUENTA_INCORRECTA = "validator.alertacelular.tipo.numerocuenta.incorrecta";
	String VALIDATOR_ALERTACELULAR_NUMEROCEL_EMPTY = "validator.alertacelular.numerocel.empty";
	String VALIDATOR_ALERTACELULAR_NUMEROCEL_NO_NUMEROS = "validator.alertacelular.numerocel_no_numeros";
	String VALIDATOR_ALERTACELULAR_COMPANIA_EMPTY = "validator.alertacelular.compania.empty";
	String VALIDATOR_ALERTACELULAR_CHKBOX_AMBOS_EMPTY = "validator.alertacelular.chkbox.ambos.empty";
	String VALIDATOR_ALERTACELULAR_MIN_EMPTY = "validator.alertacelular.min.empty";
	String VALIDATOR_ALERTACELULAR_MIN_LOW = "validator.alertacelular.min.low";
	String VALIDATOR_ALERTACELULAR_MIN_NODIGIT = "validator.alertacelular.min.nodigit";
	String VALIDATOR_ALERTACELULAR_BTNSUBMIT = "validator.alertacelular.btnsubmit";
	String VALIDATOR_ALERTACELULAR_CONFIRMACION = "validator.alertacelular.confirmacion";
	String VALIDATOR_ALERTACELULAR_NUMEROCUENTA_INCORRECTO = "validator.alertacelular.numerocuenta.incorrecto";
	String VALIDATOR_ALERTACELULAR_SEGURIDAD_ERROR = "validator.alertacelular.seguridad";
	String VALIDATOR_ALERTACELULAR_CLAVE_ACTIVACION_EMPTY =  "validator.alertacelular.clave_activacion_empty";
	String VALIDATOR_ALERTACELULAR_NUMEROCEL_LENGTH_ERROR = "validator.alertacelular.numerocel.length.error";
	
	String ACTUALIZA_DATOS_PREGUNTA_SECRETA_EMPTY = "validator.actualizaDatos.pregunta.secreta.empty";
	String ACTUALIZA_DATOS_RESPUESTA_PREGUNTA_SECRETA_EMPTY = "validator.actualizaDatos.respuesta.pregunta.secreta.empty";
	String ACTUALIZA_DATOS_CONFIRMACION_RESPUESTA_PREGUNTA_SECRETA = "validator.actualizaDatos.confirmacion.respuesta.pregunta.secreta";
	String ACTUALIZA_DATOS_EMAIL_EMPTY = "validator.actualizaDatos.email.empty";
	String ACTUALIZA_DATOS_EMAIL_INVALIDO = "validator.actualizaDatos.email.invalido";
	String ACTUALIZA_DATOS_CELULAR_EMPTY = "validator.actualizaDatos.celular.empty";
	String ACTUALIZA_DATOS_CELULAR_INVALIDO = "validator.actualizaDatos.celular.invalido";
	String ACTUALIZA_DATOS_COMPANIA_EMPTY = "validator.actualizaDatos.compania.empty";
	String ACTUALIZA_DATOS_COMPANIA_INVALIDO = "validator.actualizaDatos.compania.invalido";
	String ACTUALIZA_DATOS_RECIBIR_EMAIL_EMPTY = "validator.actualizaDatos.recibir.email.empty";
	String ACTUALIZA_DATOS_RECIBIR_EMAIL_INVALIDO = "validator.actualizaDatos.recibir.email.invalido";
	
	String CHANGE_PASSWORD_OLD_EMPTY = "validator.change.password.old.empty";
	String CHANGE_PASSWORD_NEW_EMPTY = "validator.change.password.new.empty";
	String CHANGE_PASSWORD_NEW_CONFIRMATION_EMPTY = "validator.change.password.new.confirmation.empty";
	String CHANGE_PASSWORD_NEW_CONFIRMATION_ERROR = "validator.change.password.new.confirmation.error";
	
	//Validacion de Rango de fechas en movimientos
	
	String VALIDATOR_CONSULTA_MOVIMIENTOS_RANGO_EMPTY="validator.consulta.movimientos.rango.empty";
	
	
	String VALIDATOR_CONSULTA_MOVIMIENTOS_FECHA_INICIAL_EMPTY = "validator.consulta.movimientos.fecha.inicial.empty";
	String VALIDATOR_CONSULTA_MOVIMIENTOS_FECHA_FINAL_EMPTY = "validator.consulta.movimientos.fecha.final.empty";
	String VALIDATOR_CONSULTA_MOVIMIENTOS_FECHA_FORMATO_ERROR  = "bibliotecarecibos.fecha.formato";
	String VALIDATOR_CONSULTA_MOVIMIENTOS_FECHAS_COMPARACION_ERROR = "validator.consulta.movimientos.fechas.comparacion.error";
	String VALIDATOR_CONSULTA_MOVIMIENTOS_FECHA_ACTUAL_COMPARACION_ERROR = "validator.consulta.movimientos.fecha.actual.comparacion.error";
	String VALIDATOR_CONSULTA_MOVIMIENTOS_CUENTA_INVALID = "validator.consulta.movimientos.cuenta.invalid";
	
	//Validacion de referencias frecuentes tiempo aire
	String VALIDATOR_FRECUENTES_TIEMPOAIRE_ALIAS_VACIO = "validator.frecuentes.tiempoAire.alias.vacio";
	String VALIDATOR_FRECUENTES_TIEMPOAIRE_ALIAS_ERROR = "validator.frecuentes.tiempoAire.alias.error";
	String VALIDATOR_FRECUENTES_TIEMPOAIRE_ALIAS_LONGITUD = "validator.frecuentes.tiempoAire.alias.longitud";
	
	//Validacion de localizacion de sucursal
	String VALIDATOR_LOCALIZACION_SUCURSAL_IDPAIS_VACIO = "validator.localizacion.sucursal.idpais.vacio";
	String VALIDATOR_LOCALIZACION_SUCURSAL_IDPAIS_ERROR = "validator.localizacion.sucursal.idpais.error";
	String VALIDATOR_LOCALIZACION_SUCURSAL_IDESTADO_VACIO = "validator.localizacion.sucursal.idestado.vacio";
	String VALIDATOR_LOCALIZACION_SUCURSAL_IDESTADO_ERROR = "validator.localizacion.sucursal.idestado.error";
	String VALIDATOR_LOCALIZACION_SUCURSAL_IDMUNICIPIO_VACIO = "validator.localizacion.sucursal.idmunicipio.vacio";
	String VALIDATOR_LOCALIZACION_SUCURSAL_IDMUNICIPIO_ERROR = "validator.localizacion.sucursal.idmunicipio.error";
	String VALIDATOR_LOCALIZACION_SUCURSAL_CANALES_VACIO = "validator.localizacion.sucursal.canales.vacio";
	String VALIDATOR_LOCALIZACION_SUCURSAL_NUMEROTIENDA_VACIO = "validator.localizacion.sucursal.numerotienda.vacio";
	String VALIDATOR_LOCALIZACION_SUCURSAL_NUMEROTIENDA_ERROR = "validator.localizacion.sucursal.numerotienda.error";
	String VALIDATOR_LOCALIZACION_SUCURSAL_CODIGOPOSTAL_VACIO = "validator.localizacion.sucursal.codigopostal.vacio";
	String VALIDATOR_LOCALIZACION_SUCURSAL_CODIGOPOSTAL_ERROR = "validator.localizacion.sucursal.codigopostal.error";
	String VALIDATOR_LOCALIZACION_SUCURSAL_PALABRA_VACIO = "validator.localizacion.sucursal.palabra.vacio";
	String VALIDATOR_LOCALIZACION_TIPO_SERVICIO_VACIO = "validator.localizacion.tipo.servicio.vacio";
	
	String VALIDATOR_INVERSION_GANAREMAS_MONTO_VACIO = "validator.inversion.ganaremas.monto.vacio";
	String VALIDATOR_INVERSION_GANAREMAS_MONTO_ERROR = "validator.inversion.ganaremas.monto.error";
	String VALIDATOR_INVERSION_GANAREMAS_TIPOPLAZO_VACIO = "validator.inversion.ganaremas.tipoplazo.vacio";
	String VALIDATOR_INVERSION_GANAREMAS_INSTRUCCION_VENCIMIENTO_VACIO = "validator.inversion.ganaremas.instruccion.vencimiento.vacio";
	
	String VALIDATOR_INVERSION_AZTECA_OPCION_INVERSION_VACIO = "validator.inversion.azteca.opcion.inversion.vacio";
	String VALIDATOR_INVERSION_AZTECA_PORCENTAJE_BENEFICIARIO_VACIO = "validator.inversion.azteca.porcentaje.beneficiario.vacio";
	String VALIDATOR_INVERSION_AZTECA_PORCENTAJE_BENEFICIARIO_BALANCE = "validator.inversion.azteca.porcentaje.beneficiario.balance";
	String VALIDATOR_INVERSION_AZTECA_PORCENTAJE_BENEFICIARIO_LONGITUD = "validator.inversion.azteca.porcentaje.beneficiario.longitud";
	String VALIDATOR_INVERSION_AZTECA_PORCENTAJE_BENEFICIARIO_ZERO = "validator.inversion.azteca.porcentaje.beneficiario.zero";
	String VALIDATOR_INVERSION_AZTECA_NIP_CUENTA_VACIO = "validator.inversion.azteca.nip.cuenta.vacio";
	String VALIDATOR_INVERSION_AZTECA_NIP_CUENTA_LONGITUD = "validator.inversion.azteca.nip.cuenta.longitud";
	String VALIDATOR_INVERSION_AZTECA_NIP_CONFIRMACION_CUENTA_VACIO = "validator.inversion.azteca.nip.confirmacion.cuenta.vacio";
	String VALIDATOR_INVERSION_AZTECA_NIP_CONFIRMACION_CUENTA_LONGITUD = "validator.inversion.azteca.nip.confirmacion.cuenta.longitud";
	String VALIDATOR_INVERSION_AZTECA_NIP_CUENTA_NOTEQUAL = "validator.inversion.azteca.nip.cuenta.notequal";
	String VALIDATOR_INVERSION_AZTECA_SUMA_PORCENTAJE_ERROR = "validator.inversion.azteca.suma.porcentaje.error";
	String VALIDATOR_INVERSION_AZTECA_NOMBRE_EMPTY = "validator.inversion.azteca.nombre.empty";
	String VALIDATOR_INVERSION_AZTECA_APELLIDO_PATERNO_EMPTY = "validator.inversion.azteca.apellido.paterno.empty";
	String VALIDATOR_INVERSION_AZTECA_APELLIDO_MATERNO_EMPTY = "validator.inversion.azteca.apellido.materno.empty";

	//ALTAS USUARIO
	String ALTAS_USUARIOS_CUENTA_CARGO_ERROR = "validator.altas.usuarios.cuenta.cargo";
	String ALTAS_USUARIOS_CUENTA_CARGO_NUMERICA_ERROR = "validator.altas.usuarios.cuenta.cargo.numerica";
	String ALTAS_USUARIOS_NIP_ERROR = "validator.altas.usuarios.nip";
	String ALTAS_USUARIOS_NIP_NUMERICA_ERROR = "validator.altas.usuarios.nip.numerica";
	String ALTAS_USUARIOS_FECHA_ERROR = "validator.altas.usuarios.fecha";
	String ALTAS_USUARIOS_DIA_NUMERICA_ERROR = "validator.altas.usuarios.dia.numerica";
	String ALTAS_USUARIOS_MES_NUMERICA_ERROR = "validator.altas.usuarios.mes.numerica";
	String ALTAS_USUARIOS_ANO_NUMERICA_ERROR = "validator.altas.usuarios.ano.numerica";
	String ALTAS_USUARIOS_NOMBRE_ERROR = "validator.altas.usuarios.nombre";
	String ALTAS_USUARIOS_APEPATERNO_ERROR = "validator.altas.usuarios.paterno";
	String ALTAS_USUARIOS_APEMATERNO_ERROR = "validator.altas.usuarios.materno";
	String ALTAS_USUARIOS_ALIAS_ERROR = "validator.altas.alias";
	String ALTAS_USUARIOS_EMAIL_ERROR = "validator.altas.email";
	String ALTAS_USUARIOS_PREGUNTA_ERROR = "validator.altas.pregunta";
	String ALTAS_USUARIOS_RESPUESTA_ERROR = "validator.altas.respuesta";
	String ALTAS_USUARIOS_CONTRASENA_ERROR = "validator.altas.contrasena";
	String ALTAS_USUARIOS_CONFIRMAR_ERROR = "validator.altas.confirmar";
	
	String VALIDATOR_OPERACIONES_FRECUENTES_ALIAS_EMPTY = "validator.operaciones.frecuentes.alias.empty";
	String VALIDATOR_OPERACIONES_FRECUENTES_ALIAS_LONG = "validator.operaciones.frecuentes.alias.long";
	String VALIDATOR_OPERACIONES_FRECUENTES_CLAVE_OPERACION_EMPTY = "validator.operaciones.frecuentes.clave.operacion.empty";
	String VALIDATOR_OPERACIONES_FRECUENTES_CLAVE_OPERACION_ERROR = "validator.operaciones.frecuentes.clave.operacion.error";
	String VALIDATOR_OPERACIONES_FRECUENTES_OPERACION_FRECUENTE_EMPTY = "validator.operaciones.frecuentes.operacion.frecuente.empty";
	
	String MEDIOS_PAGO_DISPOSICION_EFECTIVO_CAJERO_NUM_TARJETA_EMPTY="medios.pago.disposicion.efectivo.cajero.num.tarjeta.empty";
	String MEDIOS_PAGO_DISPOSICION_EFECTIVO_CAJERO_MONTO_EMPTY="medios.pago.disposicion.efectivo.cajero.monto.empty";
	String MEDIOS_PAGO_DISPOSICION_EFECTIVO_CAJERO_TOKEN_EMPTY="medios.pago.disposicion.efectivo.cajero.token_empty";
	String MEDIOS_PAGO_DISPOSICION_EFECTIVO_CAJERO_TOKEN_FORMATO="medios.pago.disposicion.efectivo.cajero.token_formato";
	String MEDIOS_PAGO_DISPOSICION_EFECTIVO_CAJERO_HUELLA_EMPTY="medios.pago.disposicion.efectivo.cajero.huella_empty";
	
	String VALIDATOR_FOTO_UNICA_CUENTA_EMPTY = "validator.foto.unica.cuenta.empty";
	String VALIDATOR_FOTO_UNICA_CUENTA_ERROR = "validator.foto.unica.cuenta.error";
	String VALIDATOR_FOTO_UNICA_CUENTA_LONGITUD = "validator.foto.unica.cuenta.longitud";
	String VALIDATOR_FOTO_UNICA_SUCURSAL_EMPTY = "validator.foto.unica.sucursal.empty";
	String VALIDATOR_FOTO_UNICA_SUCURSAL_ERROR = "validator.foto.unica.sucursal.error";
	String VALIDATOR_FOTO_UNICA_SUCURSAL_LONGITUD = "validator.foto.unica.sucursal.longitud";
}


