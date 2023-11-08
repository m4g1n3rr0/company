package telran.company.service;

import java.time.LocalDate;
import java.util.*;

import telran.company.dto.DepartmentAvgSalary;
import telran.company.dto.Employee;
import telran.company.dto.SalaryIntervalDistribution;

public class CompanyServiceImpl implements CompanyService {
	
	/***********************************************************/
	HashMap<String, Set<Employee>> employeesDepartment = new HashMap<>();
	//key - department, value- Set of employees working in the department
	
	/*************************************************************/
	
	TreeMap<Integer, Set<Employee>> employeesSalary = new TreeMap<>();
	//key - salary, value - set of employees having the salary value
	
	/****************************************************************/
	
	TreeMap<LocalDate, Set<Employee>> employeesAge = new TreeMap<>();
	//key birth date; value set of employees born at the
	
	@Override
	/*
	 * adds new employee into a company
	 * in the case an Employee with the given id already exists, 
	 * the exception IllegalStateException must be thrown
	 * returns reference to the being added Employee object 
	 */
	public Employee hireEmployee(Employee empl) { //Method complexity: O[1]
		
		
		return null;
		
	}

	@Override
	/*
	 * removes Employee object from company according to a given ID
	 * in the case an employee with the given ID does not exist
	 * the method must throw IllegalStateException
	 */
	public Employee fireEmployee(long id) { //Method complexity: O[1]
		
		
		return null;
	}

	@Override
	/*
	 * returns reference to Employee object with a given ID value
	 * in the case employee with the ID does not exist
	 * the method returns null
	 */
	public Employee getEmployee(long id) { //Method complexity: O[1]
		
		
		return null;
	}

	@Override
	/*
	 * returns list of employee objects working in a given department
	 * in the case none employees in the department, the method returns empty list
	 */
	public List<Employee> getEmployeesByDepartment(String department) { //Method complexity: O[N]
		
		
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() { //Method complexity: O[LogN]
		
		
		return null;
	}

	@Override
	public List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) { //Method complexity: O[LogN]
		
		
		return null;
	}

	@Override
	public List<Employee> getEmployeeByAge(int ageFrom, int ageTo) { //Method complexity: O[N]
		
		
		return null;
	}

	@Override
	public List<DepartmentAvgSalary> salaryDistributionByDepartments() { //Method complexity: O[N]
		
		
		return null;
	}

	@Override
	public List<SalaryIntervalDistribution> getSalaryDistribution(int interval) { //Method complexity: O[1}
		
		
		return null;
	}

	@Override
	public Employee updateDepartment(long id, String newDepartment) { //Method complexity: O[1]
		
		
		return null;
	}

	@Override
	public Employee updateSalary(long id, int newSalary) { //Method complexity: O[LogN]
		
		
		return null;
	}

	@Override
	public void save(String filePath) { //Method complexity: O[N]
		
		
		
	}

	@Override
	public void restore(String filePath) { //Method complexity: O[N]
		
		
		
	}

}
