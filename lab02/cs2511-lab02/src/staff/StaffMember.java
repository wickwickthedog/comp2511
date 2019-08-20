package staff;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * A staff member
 * @author Harvey
 * references https://www.baeldung.com/java-datetimeformatter
 */
public class StaffMember {
	public String name;
	public double salary;
	public LocalDate hireDate;
	public LocalDate endDate;
	
	public StaffMember(String name, double salary, LocalDate hireDate, LocalDate endDate) {
		setName(name);
		setSalary(salary);
		setHireDate(hireDate);
		setEndDate(endDate);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	private void setSalary(double salary) {
		this.salary = salary;
	}

	public String getHireDate() {
		return DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(hireDate);
	}

	private void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public String getEndDate() {
		return DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(endDate);
	}

	private void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		StaffMember otherObj = (StaffMember) obj;
		return this.name.equals(otherObj.name) && this.salary == otherObj.salary;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "NAME:" + getName() + "\t SALARY:AUD$" + getSalary() + "\t START_DATE:" + getHireDate() + "\t END_DATE:" + getEndDate(); 
		return str;
	}
}

