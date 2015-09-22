/**
 *
 */
package com.sishuok.es.tool.util;

/**
 * @author Jay
 *
 */
public class SheetContent {

	private String sheetName; //sheet页的名字
	private int colNum;  //第1行列的数目
	private int rowNum;  //行的数目
	private String[][] sheetContent; //sheet的内容

	/**
	 * @return the sheetContent
	 */
	public String[][] getSheetContent() {
		return sheetContent;
	}
	/**
	 * @param sheetContent the sheetContent to set
	 */
	public void setSheetContent(String[][] sheetContent) {
		this.sheetContent = sheetContent;
	}
	/**
	 * @return the sheetName
	 */
	public String getSheetName() {
		return sheetName;
	}
	/**
	 * @param sheetName the sheetName to set
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	/**
	 * @return the colNum
	 */
	public int getColNum() {
		return colNum;
	}
	/**
	 * @param colNum the colNum to set
	 */
	public void setColNum(int colNum) {
		this.colNum = colNum;
	}
	/**
	 * @return the rowNum
	 */
	public int getRowNum() {
		return rowNum;
	}
	/**
	 * @param rowNum the rowNum to set
	 */
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
}