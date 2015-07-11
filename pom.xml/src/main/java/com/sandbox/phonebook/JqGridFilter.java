package com.sandbox.phonebook;

import java.util.ArrayList;

/**
 * Превращаем JSON объект с фильтрами jqGrid вида
 * 
 * <pre>
 * {"groupOp":"AND","rules":[{"field":"firstName","op":"eq","data":"Alisa"}]}
 * </pre>
 * 
 * в обычный Java объект
 */
public class JqGridFilter {
	// Исходный JSON фильтров
	private String source;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getGroupOp() {
		return groupOp;
	}

	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}

	public ArrayList<Rule> getRules() {
		return rules;
	}

	public void setRules(ArrayList<Rule> rules) {
		this.rules = rules;
	}

	private String groupOp
	;
	// Здесь храним набор правил фильтрации
	private ArrayList<Rule> rules;

	public JqGridFilter() {
		super();
	}

	public JqGridFilter(String source) {
		super();
		this.source = source;
	}

	/**
	 * Класс для работы с правилами объединения фильтров
	 */
	public static class Rule {
		private String junction;
		private String field;

		public String getJunction() {
			return junction;
		}

		public void setJunction(String junction) {
			this.junction = junction;
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getOp() {
			return op;
		}

		public void setOp(String op) {
			this.op = op;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		private String op;
		private String data;

		public Rule() {
		}

		/**
		 * 
		 * @param junction
		 *            Связь фильтров
		 * @param field
		 *            Поле
		 * @param op
		 *            Оператор
		 * @param data
		 *            Значение
		 */
		public Rule(String junction, String field, String op, String data) {
			super();
			this.junction = junction;
			this.field = field;
			this.op = op;
			this.data = data;
		}

	}

}