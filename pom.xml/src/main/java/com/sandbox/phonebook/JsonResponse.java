package com.sandbox.phonebook;

import java.io.Serializable;
import java.util.List;

/**
 * Простой объект для поддержки свойств jQgrid ридера (jsonReader).
 * 
 * @see <a
 *      href="http://www.trirand.com/jqgridwiki/doku.php?id=wiki:retrieving_data#json_data">Формат
 *      данных jQgrid JSON</a>
 */

public class JsonResponse<T extends Serializable> {

	/**
	 * Номер страницы
	 */
	private String page;
 
	/**
	 * Общее число страниц
	 */
	private String total;

	/**
	 * Общее число записей
	 */
	private String records;

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getRecords() {
		return records;
	}

	public void setRecords(String records) {
		this.records = records;
	}

	/**
	 * Список строк набора данных
	 */
	private List<T> rows;

	public JsonResponse() {
	}

	public JsonResponse(String page, String total, String records, List<T> rows) {
		super();
		this.page = page;
		this.total = total;
		this.records = records;
		this.setRows(rows);
	}

	@Override
	public String toString() {
		return "JsonResponse [page=" + page + ", total=" + total + ", records="
				+ records + "]";
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}