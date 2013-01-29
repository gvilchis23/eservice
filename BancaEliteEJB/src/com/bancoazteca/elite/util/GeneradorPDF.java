package com.bancoazteca.elite.util;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import com.bancoazteca.elite.ejb.inversiones.beans.AsignacionesComprobanteTO;
import com.bancoazteca.elite.ejb.inversiones.beans.ComprobanteInversionReportosResponseTO;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class GeneradorPDF {
	
	private final static String ENVIROMENT_KEY = "sistema.ambiente";
	
	public static ByteArrayOutputStream  generadorPDFComprobanteReportos(ByteArrayOutputStream  outputStream, ComprobanteInversionReportosResponseTO comprobanteTO) throws DocumentException, MalformedURLException, IOException{
			
        Document document=new Document();
		
		PdfWriter.getInstance(document,outputStream);
		
		String ambiente = System.getProperty(ENVIROMENT_KEY);
		String ruta = "";
		if(ambiente.equalsIgnoreCase("produccion")){
			
			ruta = "/home/portalsl/jboss-4.0.2/server/default/conf/elite/produccion/produccion/generadorPDF/comprobanteInversionReportos/";
			
		}else{
			ruta = "C:/generadorPDF/comprobanteInversionReportos/";
		}
		
		
		document.open();
		
		Rectangle pageSize = document.getPageSize();
		float width=pageSize.getWidth();
		float height=pageSize.getHeight();
		Paragraph paragraph=new Paragraph();
		Image imageBaz=Image.getInstance(ruta+"/baz.bmp");
		imageBaz.scalePercent(50);
		imageBaz.setAbsolutePosition(40,height-80);
		
		document.add(imageBaz);
		
		
		Image imageElite=Image.getInstance(ruta+"/logo_bancaelite.gif");
		imageElite.scalePercent(50);
		imageElite.setAbsolutePosition(imageBaz.getWidth()-50,imageBaz.getAbsoluteY());
		document.add(imageElite);
		
		putEnter(document, paragraph);
		putEnter(document, paragraph);
		putEnter(document, paragraph);
		
		Image imageLine = Image.getInstance(ruta+"/carminLine.jpg");
		imageLine.setAlignment(imageLine.ALIGN_CENTER);
		imageLine.scalePercent(90);
		document.add(imageLine);
		
		putEnter(document,paragraph);
		
		FontFactory.registerDirectory("resources/in_action/chapter08");
		Font font=FontFactory.getFont("cmr10", 8);
		FontFactory.registerDirectories();
		
		paragraph=new  Paragraph("",font);
		DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.FULL);
		String fecha=dateFormat.format(new Date());
		paragraph.add("FECHA: "+fecha);
		paragraph.setAlignment(paragraph.ALIGN_RIGHT);
		document.add(paragraph);
		
		putEnter(document,paragraph);
		
		paragraph=new Paragraph(); 
		paragraph.add("CONFIRMACION DE OPERACION DE MERCADO DE DINERO");
		paragraph.setAlignment(paragraph.ALIGN_CENTER);
		document.add(paragraph);
		
		putEnter(document, paragraph);
		putEnter(document, paragraph);
		
		font=FontFactory.getFont("cmr10", 8);
		Phrase phrase=new Phrase();
		
		float[] widths1 = { .88f,.05f,3f };
		
		Font dataFont=FontFactory.getFont("garamond bold", BaseFont.CP1252,
				BaseFont.EMBEDDED, 10, Font.TIMES_ROMAN, new CMYKColor(255,255,255,255));
		
		PdfPTable _pdfPTable=new PdfPTable(widths1);
		_pdfPTable.setHorizontalAlignment(_pdfPTable.ALIGN_LEFT);
		
		PdfPCell _cell=null;
		
		putCellToTable("NOMBRE DEL CLIENTE", font, phrase, _cell, _pdfPTable);
		putCellToTable(":", font, phrase, _cell, _pdfPTable);
		putCellToTable(comprobanteTO.getNombre_cliente(), dataFont, phrase, _cell, _pdfPTable);
		_pdfPTable.completeRow();		
		putCellToTable("R.F.C.", font, phrase, _cell, _pdfPTable);
		putCellToTable(":", font, phrase, _cell, _pdfPTable);
		putCellToTable(comprobanteTO.getRfc(), dataFont, phrase, _cell, _pdfPTable);
		_pdfPTable.completeRow();
		putCellToTable("DIRECCION", font, phrase, _cell, _pdfPTable);
		putCellToTable(":", font, phrase, _cell, _pdfPTable);
		putCellToTable(comprobanteTO.getDireccion(), dataFont, phrase, _cell, _pdfPTable);
		_pdfPTable.completeRow();
		putCellToTable("COLONIA", font, phrase, _cell, _pdfPTable);
		putCellToTable(":", font, phrase, _cell, _pdfPTable);
		putCellToTable(comprobanteTO.getColonia(), dataFont, phrase, _cell, _pdfPTable);
		_pdfPTable.completeRow();
		putCellToTable("CIUDAD", font, phrase, _cell, _pdfPTable);
		putCellToTable(":", font, phrase, _cell, _pdfPTable);
		putCellToTable(comprobanteTO.getCiudad(), dataFont, phrase, _cell, _pdfPTable);
		_pdfPTable.completeRow();
		putCellToTable("C.P.", font, phrase, _cell, _pdfPTable);
		putCellToTable(":", font, phrase, _cell, _pdfPTable);
		putCellToTable(comprobanteTO.getCodigo_postal(), dataFont, phrase, _cell, _pdfPTable);
		_pdfPTable.completeRow();
		putCellToTable("No. CONTRATO", font, phrase, _cell, _pdfPTable);
		putCellToTable(":", font, phrase, _cell, _pdfPTable);
		putCellToTable(comprobanteTO.getNumero_contrato(), dataFont, phrase, _cell, _pdfPTable);
		_pdfPTable.completeRow();
		putCellToTable("PROMOTOR", font, phrase, _cell, _pdfPTable);
		putCellToTable(":", font, phrase, _cell, _pdfPTable);
		putCellToTable(comprobanteTO.getPromotor(), dataFont, phrase, _cell, _pdfPTable);
		_pdfPTable.completeRow();
		
		
		document.add(_pdfPTable);
		
		putTextLine("POR MEDIO DE LA PRESENTE CONFIRMAMOS LA OPERACION DE COMPRA EN REPORTO," +
					" REALIZADA CON USTEDES DE ACUERDO A LAS "
					+"SIGUIENTES CONDICIONES:", phrase, document, font);
		
		putEnter(document, paragraph);
		
		widths1=new float[]{1.15f,.05f,3f};
		_pdfPTable=new PdfPTable(widths1);
		_pdfPTable.setHorizontalAlignment(_pdfPTable.ALIGN_LEFT);
		
		putCellToTable("FOLIO DE ORDEN", font, phrase, _cell, _pdfPTable);
		putCellToTable(":", font, phrase, _cell, _pdfPTable);
		putCellToTable(comprobanteTO.getFolio_orden(), dataFont, phrase, _cell, _pdfPTable);
		putCellToTable("FECHA DE OPERACION", font, phrase, _cell, _pdfPTable);
		putCellToTable(":", font, phrase, _cell, _pdfPTable);
		putCellToTable(comprobanteTO.getFecha_concentracion(), dataFont, phrase, _cell, _pdfPTable);
		putCellToTable("FECHA DE VENCIMIENTO", font, phrase, _cell, _pdfPTable);
		putCellToTable(":", font, phrase, _cell, _pdfPTable);
		putCellToTable(comprobanteTO.getFecha_liquidacion(), dataFont, phrase, _cell, _pdfPTable);
		putCellToTable("IMPORTE DE LA INVERSION", font, phrase, _cell, _pdfPTable);
		putCellToTable(":", font, phrase, _cell, _pdfPTable);
		putCellToTable(comprobanteTO.getImporte_inversion(), dataFont, phrase, _cell, _pdfPTable);
		putCellToTable("PLAZO", font, phrase, _cell, _pdfPTable);
		putCellToTable(":", font, phrase, _cell, _pdfPTable);
		putCellToTable(comprobanteTO.getPlazo(), dataFont, phrase, _cell, _pdfPTable);
		putCellToTable("TASA", font, phrase, _cell, _pdfPTable);
		putCellToTable(":", font, phrase, _cell, _pdfPTable);
		putCellToTable(comprobanteTO.getTasa(), dataFont, phrase, _cell, _pdfPTable);
		putCellToTable("INTERES/PREMIO ", font, phrase, _cell, _pdfPTable);
		putCellToTable(":", font, phrase, _cell, _pdfPTable);
		putCellToTable(comprobanteTO.getInteres_premio(), dataFont, phrase, _cell, _pdfPTable);
		putCellToTable("MONTO AL VENCIMIENTO", font, phrase, _cell, _pdfPTable);
		putCellToTable(":", font, phrase, _cell, _pdfPTable);
		putCellToTable(comprobanteTO.getMonto_al_vencimiento(), dataFont, phrase, _cell, _pdfPTable);
		putCellToTable("ISR", font, phrase, _cell, _pdfPTable);
		putCellToTable(":", font, phrase, _cell, _pdfPTable);
		putCellToTable(comprobanteTO.getIsr(), dataFont, phrase, _cell, _pdfPTable);
		_pdfPTable.completeRow();
		document.add(_pdfPTable);
		
		putEnter(document, paragraph);
		putTextLine("ASIGNACION DE VALORES :", phrase, document, font);
		
		
		PdfPTable pdfPTable=new PdfPTable(10);
		pdfPTable.setHorizontalAlignment(pdfPTable.ALIGN_CENTER);
		pdfPTable.setWidthPercentage(100);
		pdfPTable.setWidths(new float[]{.3f,.3f,.3f,.3f,.3f,.2f,.3f,.8f,.3f,.5f});
		PdfPCell cell=new PdfPCell();
		
		font=FontFactory.getFont("cmr10", 7);
		
		Locale LOCALE_MX = new Locale("es","mx");
		
		
		
		putCellToTable("Tipo oper.", font, phrase, cell, pdfPTable,3);
		putCellToTable("Moneda", font, phrase, cell, pdfPTable,3);
		putCellToTable("Tipo valor", font, phrase, cell, pdfPTable,3);
		putCellToTable("Emisora", font, phrase, cell, pdfPTable,3);
		putCellToTable("Serie oper.", font, phrase, cell, pdfPTable,3);
		putCellToTable("Tasa", font, phrase, cell, pdfPTable,3);
		putCellToTable("Titulos", font, phrase, cell, pdfPTable,3);
		putCellToTable("Precio", font, phrase, cell, pdfPTable,3);
		putCellToTable("Tipo de cambio", font, phrase, cell, pdfPTable,3);
		putCellToTable("Monto", font, phrase, cell, pdfPTable,3);
		pdfPTable.completeRow();
		
		Collection<AsignacionesComprobanteTO> listaAsignaciones = comprobanteTO.getColeccionAsignaciones();
		Double d=null;
		
		for (AsignacionesComprobanteTO asignacionesComprobanteTO : listaAsignaciones) {
			putCellToTable(asignacionesComprobanteTO.getTipoOperacion(), font, phrase, cell, pdfPTable,cell.NO_BORDER);
			putCellToTable(asignacionesComprobanteTO.getMoneda(), font, phrase, cell, pdfPTable,cell.NO_BORDER);
			putCellToTable(asignacionesComprobanteTO.getTipoValor(), font, phrase, cell, pdfPTable,cell.NO_BORDER);
			putCellToTable(asignacionesComprobanteTO.getEmisora(), font, phrase, cell, pdfPTable,cell.NO_BORDER);
			putCellToTable(asignacionesComprobanteTO.getSerie(), font, phrase, cell, pdfPTable,cell.NO_BORDER);
			putCellToTable(asignacionesComprobanteTO.getTasaOperativa(), font, phrase, cell, pdfPTable,cell.NO_BORDER);
			putCellToTable(asignacionesComprobanteTO.getTitulos(), font, phrase, cell, pdfPTable,cell.NO_BORDER);
			putCellToTable("$"+asignacionesComprobanteTO.getPrecio(), font, phrase, cell, pdfPTable,cell.NO_BORDER);
			putCellToTable(asignacionesComprobanteTO.getTipoCambio(), font, phrase, cell, pdfPTable,cell.NO_BORDER);
			putCellToTable(asignacionesComprobanteTO.getMonto(), font, phrase, cell, pdfPTable,cell.NO_BORDER);
		//	d=Double.parseDouble(asignacionesComprobanteTO.getMonto());
		//	putCellToTable(numberFormat.format(d), font, phrase, cell, pdfPTable,cell.NO_BORDER);
		}
		
		
		pdfPTable.completeRow();
		
		putCellToTable("", font, phrase, cell, pdfPTable,cell.NO_BORDER);
		putCellToTable("", font, phrase, cell, pdfPTable,cell.NO_BORDER);
		putCellToTable("", font, phrase, cell, pdfPTable,cell.NO_BORDER);
		putCellToTable("", font, phrase, cell, pdfPTable,cell.NO_BORDER);
		putCellToTable("", font, phrase, cell, pdfPTable,cell.NO_BORDER);
		putCellToTable("", font, phrase, cell, pdfPTable,cell.NO_BORDER);
		putCellToTable("--------------", font, phrase, cell, pdfPTable,cell.NO_BORDER);
		putCellToTable("", font, phrase, cell, pdfPTable,cell.NO_BORDER);
		putCellToTable("", font, phrase, cell, pdfPTable,cell.NO_BORDER);
		putCellToTable("--------------", font, phrase, cell, pdfPTable,cell.NO_BORDER);
		pdfPTable.completeRow();
		
		putCellToTable("TOTALES", font, phrase, cell, pdfPTable,cell.NO_BORDER);
		putCellToTable("", font, phrase, cell, pdfPTable,cell.NO_BORDER);
		putCellToTable("", font, phrase, cell, pdfPTable,cell.NO_BORDER);
		putCellToTable("", font, phrase, cell, pdfPTable,cell.NO_BORDER);
		putCellToTable("", font, phrase, cell, pdfPTable,cell.NO_BORDER);
		putCellToTable("", font, phrase, cell, pdfPTable,cell.NO_BORDER);
		putCellToTable(comprobanteTO.getTotal_titulos(), font, phrase, cell, pdfPTable,cell.NO_BORDER);
		putCellToTable("", font, phrase, cell, pdfPTable,cell.NO_BORDER);
		putCellToTable("", font, phrase, cell, pdfPTable,cell.NO_BORDER);
		putCellToTable(comprobanteTO.getTotal_importe(), font, phrase, cell, pdfPTable,cell.NO_BORDER);
		
		pdfPTable.completeRow();
		
		document.add(pdfPTable);
		putEnter(document, paragraph);
		
		font=FontFactory.getFont("cmr10", 8);
		font.setColor(Color.GRAY);
		putTextLine("LA PRESENTE CONFIRMACION REPRESENTA LOS DATOS ORIGINALES CON QUE FUE PACTADA LA OPERACION, " +
				"SOBRE EL PARTICULAR AGRADECEREMOS QUE CUALQUIER ACLARACION" +
				" U OBSERVACION EN RELACION CON LA OPERACION DE REFERENCIA NOS LA HAGA LLEGAR POR ESCRITO " +
				"O VIA TELEFONICA EN ATENCION A:", phrase, document, font);
		
		putEnter(document, paragraph);
		
		
		font=FontFactory.getFont("cmr10", 6);
		
		putTextLine("BANCO AZTECA", phrase, document, font);
		putTextLine("AV. INSURGENTES SUR NO. 3579", phrase, document, font);
		putTextLine("COL. LA JOYA TLALPAN 14000, MEXICO, D.F.", phrase, document, font);
		putTextLine("R.F.C. BAI-020523-6Y8", phrase, document, font);
		putTextLine("TEL. 8582-7000, EXT'S. 70340/70342/70527", phrase, document, font);
		putEnter(document, paragraph);
		putTextLine("* Este documento se constituye como una referencia de los términos en los que se realizó la operación de pago automático de servicios, el único comprobante oficial que emite Banco Azteca es el estado de cuenta.", phrase, document, font);
		
		
		imageLine = Image.getInstance(ruta+"/carminLine.jpg");
		imageLine.setAlignment(imageLine.ALIGN_LEFT);
		imageLine.scaleAbsoluteWidth(400);
		imageLine.setAbsolutePosition(38,80);
		document.add(imageLine);
		
		
		Image image = Image.getInstance(ruta+"/lineazteca.gif");
		image.setAlignment(imageLine.ALIGN_RIGHT);
		image.scalePercent(50);
		
		
		image.setAbsolutePosition(width-image.getWidth()+59, 40);
		document.add(image);
		
		image=Image.getInstance(ruta+"/www.gif");
		image.scalePercent(50);
		image.setAbsolutePosition(40,40);
		document.add(image);
		document.close();	

	return outputStream;
}


	private static void putTextLine(String texto,Phrase phrase,Document document,Font font) throws DocumentException{
		phrase=new Phrase(texto+"\n",font);
		document.add(phrase);
	}
	
	private static void putEnter(Document document,Paragraph paragraph) throws DocumentException{
		paragraph=new Paragraph();
		paragraph.add("\n");
		document.add(paragraph);
	}
	
	private static void putCellToTable(String content,Font font, Phrase phrase, PdfPCell cell, PdfPTable pdfPTable,int border) {
		phrase=new Phrase(content,font);
		cell=new PdfPCell(phrase);
		cell.setBorder(border);
		ColumnText columnText=cell.getColumn();
		columnText.setAlignment(cell.ALIGN_CENTER);
		pdfPTable.addCell(cell);
	}
	
	private static void putCellToTable(String content,Font font, Phrase phrase, PdfPCell cell, PdfPTable pdfPTable) {
		phrase=new Phrase(content,font);
		cell=new PdfPCell(phrase);
		cell.setBorder(PdfPCell.NO_BORDER);
		pdfPTable.addCell(cell);
	}

	

}
