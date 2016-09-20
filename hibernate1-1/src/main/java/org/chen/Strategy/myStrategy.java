package org.chen.Strategy;

import org.hibernate.cfg.ImprovedNamingStrategy;;

public class myStrategy extends ImprovedNamingStrategy {

	/**
	 * mua
	 */
	private static final long serialVersionUID = 3939791376979069800L;

	public String propertyToColumnName(String propertyName) {
		return propertyName.toUpperCase();
	}

	public String tableName(String tableName) {
		return tableName;
	}

	public String columnName(String columnName) {
		return columnName;
	}

	public String propertyToTableName(String className, String propertyName) {
		return classToTableName(className) + '_' + propertyToColumnName(propertyName);
	}
}
