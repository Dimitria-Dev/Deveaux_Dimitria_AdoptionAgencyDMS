package DBHelper;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
/**
 * Children -- The Children class connects to the external database.
 *
 * @author dimitriadeveaux
 */
public class Children extends DBHelper {
	private final String TABLE_NAME = "Children";
	public static final String child_id = "child_id";
	public static final String first_name = "first_name";
	public static final String last_name = "last_name";
	public static final String age = "age";
	public static final String gender = "gender";
	public static final String birthday = "birthday";
	public static final String interest = "interest";
	public static final String allergies = "allergies";
	public static final String adoption_status = "adoption_status";

	public Children() {
		super();
	}


	private String prepareSQL(String fields, String whatField, String whatValue, String sortField, String sort) {
		String query = "SELECT ";
		query += fields == null ? " * FROM " + TABLE_NAME : fields + " FROM " + TABLE_NAME;
		query += whatField != null && whatValue != null ? " WHERE " + whatField + " = \"" + whatValue + "\"" : "";
		query += sort != null && sortField != null ? " order by " + sortField + " " + sort : "";
		return query;
	}

	public void insert(Integer child_id, String first_name, String last_name, Integer age, String gender, String birthday, String interest, String allergies, String adoption_status) {
		first_name = first_name != null ? "\"" + first_name + "\"" : null;
		last_name = last_name != null ? "\"" + last_name + "\"" : null;
		gender = gender != null ? "\"" + gender + "\"" : null;
		birthday = birthday != null ? "\"" + birthday + "\"" : null;
		interest = interest != null ? "\"" + interest + "\"" : null;
		allergies = allergies != null ? "\"" + allergies + "\"" : null;
		adoption_status = adoption_status != null ? "\"" + adoption_status + "\"" : null;
		
		Object[] values_ar = {child_id, first_name, last_name, age, gender, birthday, interest, allergies, adoption_status};
		String[] fields_ar = {Children.child_id, Children.first_name, Children.last_name, Children.age, Children.gender, Children.birthday, Children.interest, Children.allergies, Children.adoption_status};
		String values = "", fields = "";
		for (int i = 0; i < values_ar.length; i++) {
			if (values_ar[i] != null) {
				values += values_ar[i] + ", ";
				fields += fields_ar[i] + ", ";
			}
		}
		if (!values.isEmpty()) {
			values = values.substring(0, values.length() - 2);
			fields = fields.substring(0, fields.length() - 2);
			super.execute("INSERT INTO " + TABLE_NAME + "(" + fields + ") values(" + values + ");");
		}
	}

	public void delete(String whatField, String whatValue) {
		super.execute("DELETE from " + TABLE_NAME + " where " + whatField + " = " + whatValue + ";");
    }

	public void update(String whatField, String whatValue, String whereField, String whereValue) {
		super.execute("UPDATE " + TABLE_NAME + " set " + whatField + " = \"" + whatValue + "\" where " + whereField + " = \"" + whereValue + "\";");
	}

	public ArrayList<ArrayList<Object>> select(String fields, String whatField, String whatValue, String sortField, String sort) {
		return super.executeQuery(prepareSQL(fields, whatField, whatValue, sortField, sort));
	}

	public ArrayList<ArrayList<Object>> getExecuteResult(String query) {
		return super.executeQuery(query);
	}

	public void execute(String query) {
		super.execute(query);
	}

	public DefaultTableModel selectToTable(String fields, String whatField, String whatValue, String sortField, String sort) {
		return super.executeQueryToTable(prepareSQL(fields, whatField, whatValue, sortField, sort));
	}

	/**
	 * This method is used to search for a child's adoption status based on their ID number
	 *
	 * @param childId A child's ID number
	 * @return The child's ID, name and adoption status
	 */
	public String adoptionStatusChildId(int childId) {
		String query = "SELECT * FROM Children WHERE child_id = " + childId;
		ArrayList<ArrayList<Object>> result = executeQuery(query);
		if (result.isEmpty()) {
			return null;
		} else {
			ArrayList<Object> childData = result.get(0);
			return "Child ID: " + childData.get(0) + "\nName: " + childData.get(1) + " " + childData.get(2) + "\nAdoption Status: " + childData.get(8);
		}
	}
}