package br.com.autoservice.mb;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

public class Teste {
	
	public static void main(String[] args) {
		expExcel("C:/log/test.xls");
	}
	
	public static void expExcel(String nomeArquivo) {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet firstSheet = workbook.createSheet("Aba1");

		HSSFFont fontBranca = workbook.createFont();
		fontBranca.setFontHeightInPoints((short) 16);
        fontBranca.setColor(HSSFColor.WHITE.index);
        fontBranca.setBoldweight(Font.BOLDWEIGHT_BOLD);
        
        HSSFFont fontBrancaMenor = workbook.createFont();
        fontBrancaMenor.setFontHeightInPoints((short) 10);
        fontBrancaMenor.setColor(HSSFColor.WHITE.index);
        fontBrancaMenor.setBoldweight(Font.BOLDWEIGHT_BOLD);
        
        
		FileOutputStream fos = null;

		try {
		fos = new FileOutputStream(new File(nomeArquivo));

		List<String> lista = new ArrayList<>();
		lista.add("re");
		lista.add("tr");
		lista.add("wq");
		lista.add("ew");
		
		HSSFRow rowa = firstSheet.createRow(0);
		rowa.createCell(1);

		// criar titulo
		HSSFRow row0 = firstSheet.createRow(1);
		HSSFCellStyle style = workbook.createCellStyle();

		style.setFont(fontBranca);
		style.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		
		row0.createCell(0).setCellValue("teste de arquivo"+" 345");
		row0.getCell(0).setCellStyle(style);
		
		//nome das colunas
		HSSFRow row1 = firstSheet.createRow(2);
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		style2.setFont(fontBrancaMenor);
		
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		

		row1.createCell(0).setCellValue("Sistema");
		row1.createCell(1).setCellValue("Módulo");
		row1.createCell(2).setCellValue("Diretório");
		row1.createCell(3).setCellValue("Arquivo");
		row1.createCell(4).setCellValue("Ambiente");
		row1.createCell(5).setCellValue("Tipo Arquivo");
		row1.createCell(6).setCellValue("Ação");
		row1.createCell(7).setCellValue("Data");
		row1.createCell(8).setCellValue("Revisão SVN");
		row1.createCell(9).setCellValue("Autor");
		row1.createCell(10).setCellValue("Motivo da alteração");
		
		row1.getCell(0).setCellStyle(style2);
		row1.getCell(1).setCellStyle(style2);
		row1.getCell(2).setCellStyle(style2);
		row1.getCell(3).setCellStyle(style2);
		row1.getCell(4).setCellStyle(style2);
		row1.getCell(5).setCellStyle(style2);
		row1.getCell(6).setCellStyle(style2);
		row1.getCell(7).setCellStyle(style2);
		row1.getCell(8).setCellStyle(style2);
		row1.getCell(9).setCellStyle(style2);
		row1.getCell(10).setCellStyle(style2);
		
		firstSheet.autoSizeColumn(0);
		firstSheet.autoSizeColumn(1);
		firstSheet.autoSizeColumn(2);
		firstSheet.autoSizeColumn(3);
		firstSheet.autoSizeColumn(4);
		firstSheet.setColumnWidth(5, 10000);
		firstSheet.autoSizeColumn(6);
		firstSheet.autoSizeColumn(7);
		firstSheet.autoSizeColumn(8);
		firstSheet.autoSizeColumn(9);
		firstSheet.autoSizeColumn(10);
		
		
		HSSFCellStyle style3 = workbook.createCellStyle();
		style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
		
		int i = 3;
		
		for (String cd : lista) {
		HSSFRow row = firstSheet.createRow(i);

		row.createCell(0).setCellValue(cd);
		row.createCell(1).setCellValue(cd.toString());
		row.createCell(2).setCellValue(cd);
		row.createCell(3).setCellValue(cd.toString());
		row.createCell(4).setCellValue(cd);
		row.createCell(5).setCellValue("fdasçfajsfkldas/fdsafdasfdsafdas/fdasfdasfasd/fdsafasdf/ds/tewtrewtrew.txt");
		row.createCell(6).setCellValue("fdsafçljsaf/fdsajfklda/artiovo.txt");
		row.createCell(7).setCellValue(cd.toString());
		row.createCell(8).setCellValue(cd.toString());
		row.createCell(9).setCellValue(cd.toString());
		row.createCell(10).setCellValue(cd.toString());
		
		row.getCell(0).setCellStyle(style3);
		row.getCell(1).setCellStyle(style3);
		row.getCell(2).setCellStyle(style3);
		row.getCell(3).setCellStyle(style3);
		row.getCell(4).setCellStyle(style3);
		row.getCell(5).setCellStyle(style3);
		row.getCell(6).setCellStyle(style3);
		row.getCell(7).setCellStyle(style3);
		row.getCell(8).setCellStyle(style3);
		row.getCell(9).setCellStyle(style3);
		row.getCell(10).setCellStyle(style3);

		i++;

		} // fim do for
		
		firstSheet.addMergedRegion(new CellRangeAddress(
	            1, //first row (0-based)
	            1, //last row  (0-based)
	            0, //first column (0-based)
	            10  //last column  (0-based)
	    ));

		workbook.write(fos);

		} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Erro ao exportar arquivo");
		} finally {
		try {
		fos.flush();
		fos.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
		} // fim do metodo exp

}
