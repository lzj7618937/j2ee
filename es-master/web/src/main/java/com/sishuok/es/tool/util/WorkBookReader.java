/**
 *
 */
package com.sishuok.es.tool.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author Jay
 *
 */
public class WorkBookReader {
	public static String getCellString(Cell cell) {
		String cellValue = "";
		try {
			switch (cell.getCellType()) {
				case 0: // 数字日期型
					if (org.apache.poi.hssf.usermodel.HSSFDateUtil
							.isCellDateFormatted(cell)) {
						// 日期格式
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						cellValue = sdf.format(cell.getDateCellValue());
					} else {
						cellValue = (int) cell.getNumericCellValue() + "";
						BigDecimal bd = new BigDecimal(cellValue);
						cellValue = bd.toPlainString(); // 数字格式, 避免出現科學符號
					}
					break;
				case 1:
					// 字串型
					cellValue = cell.getStringCellValue();
					break;
				case 2:
					// 公式型
					cellValue = cell.getNumericCellValue() + "";
					break;
				case 3:
					// 空白型
					cellValue = "";
					break;
				case 4:
					// bool型
					cellValue = cell.getBooleanCellValue() + "";
					break;
				case 5:
					// Error
					cellValue = "";
					break;
			}
		} catch (Exception err2) {
			cellValue = "";
		}
		return cellValue;
	}

	/**
	 * 读取excel文件
	 *
	 * @param fileIn
	 *            //excel文件路径
	 */
	public static ArrayList<SheetContent> ReadWorkBookSheet(InputStream fileIn) {
		ArrayList<SheetContent> ls = new ArrayList<SheetContent>();
		HSSFWorkbook wb = null;
		try {
			POIFSFileSystem fs = new POIFSFileSystem(fileIn);
			wb = new HSSFWorkbook(fs);
			// 获取工作表中的个数
			int sheetNum = wb.getNumberOfSheets();
			for (int i = 0; i < sheetNum; i++) {
				SheetContent sheetContent = new SheetContent();
				// 取出第i个工作表
				HSSFSheet sheetFileInfo = wb.getSheetAt(i);
				// 取得第i个工作表的sheet名字
				sheetContent.setSheetName(wb.getSheetName(i));
				// 取出工作表中的可用的行数
				int rows = sheetFileInfo.getPhysicalNumberOfRows();
				sheetContent.setRowNum(rows);
				// 取出第一行数据
				HSSFRow row = sheetFileInfo.getRow(0);
				// 获得列数
				int titleNum = row.getLastCellNum();
				sheetContent.setColNum(titleNum);
				String[][] sheetRows = new String[rows][titleNum];
				for (int j = 0; j < rows; j++) {
					HSSFRow sheetrows = sheetFileInfo.getRow(j);
					//for (int k = 0; k < sheetrows.getLastCellNum(); k++) {
					for (int k = 0; k < titleNum; k++) {
						HSSFCell cell = sheetrows.getCell(k);
						sheetRows[j][k] = getCellString(cell);
					}
					sheetContent.setSheetContent(sheetRows);
				}
				ls.add(sheetContent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}

	/**
	 * 读取excel文件
	 *
	 * @param filePath
	 *            //excel文件路径
	 */
	public static ArrayList<SheetContent> ReadWorkBookSheet(String filePath) {
		ArrayList<SheetContent> ls = new ArrayList<SheetContent>();
		HSSFWorkbook wb = null;
		FileInputStream fileIn = null;
		try {
			fileIn = new FileInputStream(filePath);
			POIFSFileSystem fs = new POIFSFileSystem(fileIn);
			wb = new HSSFWorkbook(fs);
			// 获取工作表中的个数
			int sheetNum = wb.getNumberOfSheets();
			for (int i = 0; i < sheetNum; i++) {
				SheetContent sheetContent = new SheetContent();
				// 取出第i个工作表
				HSSFSheet sheetFileInfo = wb.getSheetAt(i);
				// 取得第i个工作表的sheet名字
				sheetContent.setSheetName(wb.getSheetName(i));
				// 取出工作表中的可用的行数
				int rows = sheetFileInfo.getPhysicalNumberOfRows();
				sheetContent.setRowNum(rows);
				// 取出第一行数据
				HSSFRow row = sheetFileInfo.getRow(0);
				// 获得列数
				int titleNum = row.getLastCellNum();
				sheetContent.setColNum(titleNum);
				String[][] sheetRows = new String[rows][titleNum];
				for (int j = 0; j < rows; j++) {
					HSSFRow sheetrows = sheetFileInfo.getRow(j);
					for (int k = 0; k < sheetrows.getLastCellNum(); k++) {
						HSSFCell cell = sheetrows.getCell(k);
						sheetRows[j][k] = getCellString(cell);
					}
					sheetContent.setSheetContent(sheetRows);
				}
				ls.add(sheetContent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}

	/**
	 * 读取excel文件
	 *
	 * @param filePath
	 *            //excel文件路径
	 * @throws Exception
	 */
	public static ArrayList<SheetContent> ReadExcelFile(String filePath)
			throws Exception {
		ArrayList<SheetContent> ls = new ArrayList<SheetContent>();
		if (filePath.contains(".xls") || filePath.contains(".xlsx")) {
			Workbook wb = null;
			try {
				wb = new HSSFWorkbook(new FileInputStream(filePath));
				ls = WorkBookReader.ReadHSSFWorkBookSheet(wb);
			} catch (IOException e) {
				try {
					wb = new XSSFWorkbook(filePath);
					ls = WorkBookReader.ReadHSSFWorkBookSheet(wb);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					throw new ReadExcelFileException(filePath
							+ "is not 2003/2007 excel!");
				}
			}
		} else {
			throw new ReadExcelFileException(filePath
					+ "is not 2003/2007 excel!");
		}
		return ls;
	}

	/**
	 * 读取2003格式excel文件
	 *
	 * @param wb
	 *            //excel文件路径
	 */
	public static ArrayList<SheetContent> ReadHSSFWorkBookSheet(Workbook wb) {
		ArrayList<SheetContent> ls = new ArrayList<SheetContent>();
		try {
			// 获取工作表中的个数
			int sheetNum = wb.getNumberOfSheets();
			for (int i = 0; i < sheetNum; i++) {
				SheetContent sheetContent = new SheetContent();
				// 取出第i个工作表
				HSSFSheet sheetFileInfo = (HSSFSheet) wb.getSheetAt(i);
				// 取得第i个工作表的sheet名字
				sheetContent.setSheetName(wb.getSheetName(i));
				// 取出工作表中的可用的行数
				int rows = sheetFileInfo.getPhysicalNumberOfRows();
				sheetContent.setRowNum(rows);
				// 取出第一行数据
				HSSFRow row = sheetFileInfo.getRow(0);
				// 获得第1行的列数
				int titleNum = row.getLastCellNum();
				sheetContent.setColNum(titleNum);
				String[][] sheetRows = new String[rows][];
				for (int j = 0; j < rows; j++) {
					HSSFRow sheetrows = sheetFileInfo.getRow(j);
					int colNum = sheetrows.getLastCellNum();
					String[] sheetRow;
					if(colNum > 0) {
						sheetRow = new String[colNum];
						for (int k = 0; k < sheetrows.getLastCellNum(); k++) {
							HSSFCell cell = sheetrows.getCell(k);
							sheetRow[k] = getCellString(cell);
						}
					}
					else {
						sheetRow = new String[1];
						sheetRow[0] = "";
					}
					sheetRows[j] = sheetRow;
					sheetContent.setSheetContent(sheetRows);
				}
				ls.add(sheetContent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}

	/**
	 * 读取2007格式excel文件
	 *
	 * @param filePath
	 *            //excel文件路径
	 */
	public static ArrayList<SheetContent> ReadXSSFWorkBookSheet(Workbook wb) {
		ArrayList<SheetContent> ls = new ArrayList<SheetContent>();
		try {
			// 获取工作表中的个数
			int sheetNum = wb.getNumberOfSheets();
			for (int i = 0; i < sheetNum; i++) {
				SheetContent sheetContent = new SheetContent();
				// 取出第i个工作表
				XSSFSheet sheetFileInfo = (XSSFSheet) wb.getSheetAt(i);
				// 取得第i个工作表的sheet名字
				sheetContent.setSheetName(wb.getSheetName(i));
				// 取出工作表中的可用的行数
				int rows = sheetFileInfo.getPhysicalNumberOfRows();
				sheetContent.setRowNum(rows);
				// 取出第一行数据
				XSSFRow row = sheetFileInfo.getRow(0);
				// 获得列数
				int titleNum = row.getLastCellNum();
				sheetContent.setColNum(titleNum);
				String[][] sheetRows = new String[rows][titleNum];
				for (int j = 0; j < rows; j++) {
					XSSFRow sheetrows = sheetFileInfo.getRow(j);
					for (int k = 0; k < sheetrows.getLastCellNum(); k++) {
						XSSFCell cell = sheetrows.getCell(k);
						sheetRows[j][k] = getCellString(cell);
					}
					sheetContent.setSheetContent(sheetRows);
				}
				ls.add(sheetContent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}
}

class ReadExcelFileException extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ReadExcelFileException() {
	}

	public ReadExcelFileException(String msg) {
		super(msg);
	}
}