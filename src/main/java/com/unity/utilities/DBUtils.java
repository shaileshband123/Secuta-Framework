package com.unity.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.unity.constants.AppConstants;

public class DBUtils {

	private static Logger log = LogManager.getLogger();
	String connectionUrl = null;

	public DBUtils() {
		switch (AppConstants.APPLICATION_ENV.toLowerCase()) {
		case "qc191":
			connectionUrl = AppConstants.UNITY_AO_QC191;
			break;
		default:
			Assert.fail("*****Database enviroment is not properly set*****\n");
			break;
		}
		// add cases for different environments
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			Assert.fail("Oracle Driver class could not be loaded. Please check build path or dependency to ojdbc jar");
		}
	}

	public Map<String, List<String>> executeQuery(String query) {
		Map<String, List<String>> dbResultsMap = new HashMap<>();
		List<String> dbColumns = new ArrayList<>();
		List<String> dbColumnValues = new ArrayList<>();

		ResultSetMetaData rmd = null;
		try (Connection con = DriverManager.getConnection(connectionUrl);
				PreparedStatement stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery()) {
			rmd = rs.getMetaData();
			for (int colIndex = 1; colIndex <= rmd.getColumnCount(); colIndex++)
				dbColumns.add(rmd.getColumnName(colIndex));
			String columnValue = null;

			while (rs.next()) {
				for (int i = 0; i < dbColumns.size(); i++) {
					columnValue = formatValue(rs.getString(dbColumns.get(i)));
					dbColumnValues.add(columnValue);
					if (!dbResultsMap.containsKey(dbColumns.get(i))) {
						dbResultsMap.put(dbColumns.get(i), dbColumnValues);
						dbColumnValues = new ArrayList<>();
					} else {
						dbResultsMap.get(dbColumns.get(i)).addAll(dbColumnValues);
						dbColumnValues = new ArrayList<>();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getLocalizedMessage());
		}
		return dbResultsMap;
	}

	/**
	 * This method formats given DB column value to match with the UI value
	 * 
	 * @param columnValue
	 * @return
	 */
	private String formatValue(String columnValue) {
		if (columnValue != null) {
			if (columnValue.contains("0:0:0")) {
				columnValue = columnValue.split(" ")[0];
			}
			if (columnValue.contains(".00")) {
				columnValue = columnValue.replace(".00", "");
			}
		} else {
			columnValue = "";
		}
		return columnValue;
	}

	public void executeUpdate(String query) {
		Pattern pattern = Pattern.compile(".*update|insert|delete.*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(query);
		if (!matcher.find()) {
			Assert.fail("Query should be of DML type : " + query);
		}
		try (Connection con = DriverManager.getConnection(connectionUrl);
				PreparedStatement stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			int rowCount = stmt.executeUpdate();
			Assert.assertTrue(rowCount > 0, "No Rows found for the given query");
			log.info(rowCount + " row(s) successfully updated");
		} catch (Exception e) {
			log.catching(e);
			Assert.fail("", e.getCause());
		}
	}
}
