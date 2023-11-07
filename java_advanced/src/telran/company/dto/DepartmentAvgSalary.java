package telran.company.dto;

import java.io.*;

public record DepartmentAvgSalary(String department, int salary) implements Serializable, Comparable<DepartmentAvgSalary> {

	@Override
	public int compareTo(DepartmentAvgSalary o) {
		
		return department.compareTo(o.department);
		
	}

}
