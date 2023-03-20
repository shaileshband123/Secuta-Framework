package com.unity.stepdefs;

import java.util.List;
import java.util.Map;

import com.unity.utilities.DBUtils;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class DBSteps extends BaseSteps {

	@When("^user executes query 'update \"([^\"]*)\" set \"([^\"]*)\" = \"([^\"]*)\" where \"([^\"]*)\" = \"([^\"]*)\"'$")
	public void user_executes_query_update_set_where(String tableName, String columnName, String columnValue,
			String conditionColumnName, String conditionalValue) {
		DBUtils dbUtils = new DBUtils();
		dbUtils.executeUpdate("update " + tableName + " set " + columnName + " = " + columnValue + " where "
				+ conditionColumnName + " = " + conditionalValue);
	}

	@When("^user executes db query \"([^\"]*)\"$")
	public void user_executes_db_query(String query) {
		DBUtils dbUtils = new DBUtils();
		dbUtils.executeQuery(query);
	}

	@When("^user executes db update query \"([^\"]*)\"$")
	public void user_executes_db_update_query_something(String query) {
		DBUtils dbUtils = new DBUtils();
		dbUtils.executeUpdate(query);
	}

	@And("^user executes db query \"([^\"]*)\" and extracts data for column \"([^\"]*)\" as \"([^\"]*)\"$")
	public void user_executes_db_query_and_extracts_data_for_column_something_as_something(String query,
			String columnName, String variableName) {
		DBUtils dbUtils = new DBUtils();
		query = getVariableValue(query);
		Map<String, List<String>> dbData = dbUtils.executeQuery(query);
		scenarioData.get().put(variableName, dbData.get(columnName).get(0));
	}

}