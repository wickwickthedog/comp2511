package unsw.venues;

import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonOutput {
	/**
	 * Static variable single_instance 
	 * of type JsonOutput
	 */
	private static JsonOutput single_instance = null;
	
	private JsonOutput() {
	}
	/**
	 * Create instance of singleton class 
	 * @return single_instance
	 */
	public static JsonOutput getInstance() {
		if (single_instance == null) single_instance = new JsonOutput();
		return single_instance;
	}
	/**
	 * Add a JSONObject to 
	 * exisiting JSONArray
	 * @param jsonArray
	 * @param jsonObject
	 * @return JSONArray
	 */
	public JSONArray addJSONObject(JSONArray jsonArray, JSONObject jsonObject) {
		jsonArray.put(jsonObject);
		return jsonArray;
	}
	/**
	 * Add a JSONArray to
	 * exisiting JSONObject with field
	 * @param jsonObject
	 * @param field
	 * @param jsonArray
	 * @return JSONObject
	 */
	public JSONObject addJSONArray(JSONObject jsonObject, String field, JSONArray jsonArray) {
		jsonObject.put(field, jsonArray);
		return jsonObject;
	}
	/**
	 * Add a String to
	 * existing JSONArray
	 * @param jsonArray
	 * @param data
	 * @return JSONArray
	 */
	public JSONArray setField(JSONArray jsonArray, String data) {
		jsonArray.put(data);
		return jsonArray;
	}
	/**
	 * Add a String to
	 * existing JSONObject with field
	 * @param statement
	 * @param field
	 * @param data
	 * @return JSONObject
	 */
	public JSONObject setField(JSONObject statement, String field, String data) {
		statement.put(field, data);
		return statement;
	}
	/**
	 * Add a LocalDate to
	 * existing JSONObject with field
	 * @param statement
	 * @param field
	 * @param data
	 * @return JSONObject
	 */
	public JSONObject setField(JSONObject statement, String field, LocalDate data) {
		statement.put(field, data);
		return statement;
	}
	/**
	 * Create JSONObject with success message
	 * @return JSONObject
	 */
	public JSONObject successMessage() {
		JSONObject success = new JSONObject();
		success.put("status", "success");
		return success;
	}
	/**
	 * Add success message to
	 * exisiting JSONObject
	 * @param statement
	 * @return JSONObject
	 */
	public JSONObject successMessage(JSONObject statement) {
		statement.put("status", "success");
		return statement;
	}
	/**
	 * Create JSONObject with error message
	 * @return JSONObject
	 */
	public JSONObject errorMessage() {
		JSONObject error = new JSONObject();
		error.put("status", "rejected");
		return error;
	}
	/**
	 * Print JSONObject
	 * @param statement
	 */
	public void jsonObject(JSONObject statement) {
		System.out.println(statement.toString());
	}
	/**
	 * Print JSONArray
	 * @param statement
	 */
	public void jsonArray(JSONArray statement) {
		System.out.println(statement.toString());
	}
}
