package telran.company.dto;

import java.io.*;

public record DepartmentAvgSalary(String department, int salary) implements Serializable {

}
