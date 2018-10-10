package com.jzd1997.structure.util;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportExcelUtils {
	public static void createHistory(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setWrapText(true);
		Sheet sheetHistory = wb.createSheet("变更履历");
		sheetHistory.setColumnWidth(0, 8*256);
		sheetHistory.setColumnWidth(1, 12*256);
		sheetHistory.setColumnWidth(2, 12*256);
		sheetHistory.setColumnWidth(3, 25*256);
		sheetHistory.setColumnWidth(4, 15*256);
		sheetHistory.setColumnWidth(5, 40*256);
		sheetHistory.setColumnWidth(6, 40*256);
		for(int i=0;i<25;i++) {
			Row row = sheetHistory.createRow(i);
			Cell cell0 = row.createCell(0);
			Cell cell1 = row.createCell(1);
			Cell cell2 = row.createCell(2);
			Cell cell3 = row.createCell(3);
			Cell cell4 = row.createCell(4);
			Cell cell5 = row.createCell(5);
			Cell cell6 = row.createCell(6);
			cell0.setCellStyle(style);
			cell1.setCellStyle(style);
			cell2.setCellStyle(style);
			cell3.setCellStyle(style);
			cell4.setCellStyle(style);
			cell5.setCellStyle(style);
			cell6.setCellStyle(style);
			if(i==0) {
				cell0.setCellValue("序号");
				cell1.setCellValue("变更日期");
				cell2.setCellValue("变更人");
				cell3.setCellValue("数据表");
				cell4.setCellValue("变更类型");
				cell5.setCellValue("变更内容");
				cell6.setCellValue("变更原因");
			}else {
				cell0.setCellFormula("row()-1");
			}
		}
	}
	
	public static boolean createExcel(Map<String, List<List<String>>> map, String filename) {
		Workbook wb = new XSSFWorkbook(); // or new XSSFWorkbook();
		createHistory(wb);
		// Title cells
		CellStyle style = wb.createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		CellStyle style1 = wb.createCellStyle();
		style1.setBorderBottom(BorderStyle.THIN);
		style1.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style1.setBorderLeft(BorderStyle.THIN);
		style1.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style1.setBorderRight(BorderStyle.THIN);
		style1.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style1.setBorderTop(BorderStyle.THIN);
		style1.setTopBorderColor(IndexedColors.BLACK.getIndex());
		Sheet sheetIndex = wb.createSheet("索引");
		List<String> tbls = new ArrayList<String>();
		for (String key : map.keySet()) {
			Sheet sheet = wb.createSheet(key);
			tbls.add(key);
			Row row = sheet.createRow(0);
			Cell cell0 = row.createCell(0);
			cell0.setCellValue("编号");
			cell0.setCellStyle(style);
			Cell cell1 = row.createCell(1);
			cell1.setCellValue("列名");
			cell1.setCellStyle(style);
			Cell cell2 = row.createCell(2);
			cell2.setCellValue("数据类型");
			cell2.setCellStyle(style);
			Cell cell3 = row.createCell(3);
			cell3.setCellValue("允许为空");
			cell3.setCellStyle(style);
			Cell cell4 = row.createCell(4);
			cell4.setCellValue("主键");
			cell4.setCellStyle(style);
			Cell cell5 = row.createCell(5);
			cell5.setCellValue("默认值");
			cell5.setCellStyle(style);
			Cell cell6 = row.createCell(6);
			cell6.setCellValue("备注");
			cell6.setCellStyle(style);
			List<List<String>> list = map.get(key);
			for (int index = 0; index < list.size(); index++) {
				Row row1 = sheet.createRow(index + 1);
				for (int i = 0; i < 4; i++) {
					Cell cell_0 = row1.createCell(0);
					cell_0.setCellFormula("row()-1");
					cell_0.setCellStyle(style1);
					Cell cell_1 = row1.createCell(1);
					cell_1.setCellValue(list.get(index).get(0));
					cell_1.setCellStyle(style1);
					Cell cell_2 = row1.createCell(2);
					cell_2.setCellValue(list.get(index).get(1));
					cell_2.setCellStyle(style1);
					Cell cell_3 = row1.createCell(3);
					cell_3.setCellValue(list.get(index).get(2));
					cell_3.setCellStyle(style1);
					Cell cell_4 = row1.createCell(4);
					cell_4.setCellValue(list.get(index).get(3));
					cell_4.setCellStyle(style1);
					Cell cell_5 = row1.createCell(5);
					cell_5.setCellValue(list.get(index).get(4));
					cell_5.setCellStyle(style1);
					Cell cell_6 = row1.createCell(6);
					cell_6.setCellValue(list.get(index).get(5));
					cell_6.setCellStyle(style1);
				}
			}
		}
		CreationHelper createHelper = wb.getCreationHelper();
		/* 设置为超链接的样式 */
		CellStyle linkStyle = wb.createCellStyle();
		Font cellFont = wb.createFont();
		cellFont.setUnderline((byte) 1);
		cellFont.setColor(IndexedColors.BLUE.getIndex());
		linkStyle.setFont(cellFont);
		for (int k = 0; k < tbls.size(); k++) {
			Row row = sheetIndex.createRow(k + 1);
			Cell linkCell = row.createCell(1);
			Hyperlink hyperlink = createHelper.createHyperlink(HyperlinkType.DOCUMENT);
			// "#"表示本文档 "明细页面"表示sheet页名称 "A10"表示第几列第几行
			hyperlink.setAddress("#" + tbls.get(k) + "!A1");
			linkCell.setHyperlink(hyperlink);
			// 点击进行跳转
			linkCell.setCellValue(tbls.get(k));
			linkCell.setCellStyle(linkStyle);
		}
		try (OutputStream fileOut = new FileOutputStream(filename)) {
			wb.write(fileOut);
		} catch (Exception e) {

		}
		return true;
	}
}