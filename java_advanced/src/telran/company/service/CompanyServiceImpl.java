package telran.company.service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

import telran.company.dto.DepartmentAvgSalary;
import telran.company.dto.Employee;
import telran.company.dto.SalaryIntervalDistribution;

public class CompanyServiceImpl implements CompanyService {
	
	HashMap<Long, Employee> employeesMap = new HashMap<>();
	
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
		
		long id = empl.id();
		
		if (employeesMap.containsKey(id) ) {
			
			throw new IllegalStateException("Employee already exists" + id);
			
		}
		
		employeesMap.put(id, empl);
		
		addEmployeeSalary(empl);
		addEmployeeDepartment(empl);
		addEmployeeAge(empl);
		
		return empl;
		
	}

	private void addEmployeeAge(Employee empl) {

		LocalDate birthdate = empl.birthDate();
		Set<Employee> set =
				employeesAge.computeIfAbsent(birthdate, k -> new HashSet<>());
		set.add(empl);
		
	}

	private void addEmployeeDepartment(Employee empl) {

		String department = empl.department();
		
		Set<Employee> set = employeesDepartment.computeIfAbsent(department, v -> new HashSet<>());
		set.add(empl);
		
	}

	private void addEmployeeSalary(Employee empl) {
		
		int salary = empl.salary();
		
		Set<Employee> set = employeesSalary.computeIfAbsent(salary, v -> new HashSet<>());
		set.add(empl);
		
	}

	@Override
	/*
	 * removes Employee object from company according to a given ID
	 * in the case an employee with the given ID does not exist
	 * the method must throw IllegalStateException
	 */
	public Employee fireEmployee(long id) { //Method complexity: O[1]
		
		Employee empl = employeesMap.remove(id);
		if (empl == null) {
			
			throw new IllegalStateException("Employee not found" + id);
			
		}
		
		removeEmployeesDepartment(empl);
		removeEmployeesSalary(empl);
		removeEmployeesAge(empl);
		
		return empl;
	}

	private void removeEmployeesAge(Employee empl) {
		
		LocalDate birthDate = empl.birthDate();
		Set<Employee> set = employeesAge.get(birthDate);
		set.remove(empl); //removing reference to being removed employee from the set of employees with the given birth date
		
		if (set.isEmpty()) {
			
			employeesAge.remove(birthDate);
			
		}
		
	}

	private void removeEmployeesSalary(Employee empl) {
		
		int salary = empl.salary();
		Set<Employee> set = employeesSalary.get(salary);
		set.remove(empl);

		if (set.isEmpty()) {
			
			employeesSalary.remove(salary);
			
		}
		
	}

	private void removeEmployeesDepartment(Employee empl) {
		
		String department = empl.department();
		Set<Employee> set = employeesDepartment.get(department);
		set.remove(empl);

		if (set.isEmpty()) {
			
			employeesDepartment.remove(department);
			
		}
		
	}

	@Override
	/*
	 * returns reference to Employee object with a given ID value
	 * in the case employee with the ID does not exist
	 * the method returns null
	 */
	public Employee getEmployee(long id) { //Method complexity: O[1]
		
		
		return employeesMap.get(id);
		
	}

	@Override
	/*
	 * returns list of employee objects working in a given department
	 * in the case none employees in the department, the method returns empty list
	 */
	public List<Employee> getEmployeesByDepartment(String department) { //Method complexity: O[N]
		
		//in the case no employees in the given department the empty collection should be returned
		
		Set<Employee> setEmployeesDep = employeesDepartment.getOrDefault(department, new HashSet<>());
		
		return new ArrayList<>(setEmployeesDep);
		
	}

	@Override
	public List<Employee> getAllEmployees() { //Method complexity: O[LogN]
		
		
		return new ArrayList<>(employeesMap.values());
		
	}

	@Override
	public List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) { //Method complexity: O[LogN]
		
		Collection<Set<Employee>> col = employeesSalary.subMap(salaryFrom, salaryTo).values();
		ArrayList<Employee> res = new ArrayList<>();
		
		for (Set<Employee> set: col) {
			
			res.addAll(set);
			
		}
		
		return res;
		
	}

	@Override
	public List<Employee> getEmployeeByAge(int ageFrom, int ageTo) { //Method complexity: O[N]
		
		LocalDate DateFrom = getBirthDate(ageTo);
		LocalDate DateTo = getBirthDate(ageFrom);
		Collection<Set<Employee>> col = employeesAge.subMap(DateFrom, DateTo).values();
		ArrayList<Employee> res = new ArrayList<>();
	
		for (Set<Employee> set: col) {
			
			res.addAll(set);
			
		}
		
		return res;
		
	}

	private LocalDate getBirthDate(int age) {
		
		return LocalDate.now().minusYears(age);
	}

	@Override
	public List<DepartmentAvgSalary> salaryDistributionByDepartments() { //Method complexity: O[N]
		
		Map<String, Double> map = employeesMap.values().stream().collect(Collectors.groupingBy(e -> e.department(), Collectors.averagingInt(Employee::salary)));
		
		return map.entrySet().stream().map(e -> new DepartmentAvgSalary(e.getKey(), e.getValue().intValue())).toList();
		
	}

	@Override
	public List<SalaryIntervalDistribution> getSalaryDistribution(int interval) { //Method complexity: O[1]
		
		Map<Integer, Long> map = employeesMap.values().stream().collect(Collectors.groupingBy(e -> e.salary() / interval, Collectors.counting()));
		
		return map.entrySet().stream().sorted((e1, e2) -> Integer.compare(e1.getKey(), e2.getKey())).map(e -> new SalaryIntervalDistribution(e.getKey() * interval, e.getKey() * interval + interval, e.getValue())).toList();
		
	}

	@Override
	public Employee updateDepartment(long id, String newDepartment) { //Method complexity: O[1]
		
		Employee empl = fireEmployee(id);
		Employee newEmployee = new Employee(id, empl.name(), empl.salary(), newDepartment, empl.birthDate());
		
		return hireEmployee(newEmployee);
		
	}

	@Override
	public Employee updateSalary(long id, int newSalary) { //Method complexity: O[LogN]
		
		Employee empl = fireEmployee(id);
		Employee newEmployee = new Employee(id, empl.name(), newSalary, empl.department(), empl.birthDate());
		
		return hireEmployee(newEmployee);
		
	}

	@Override
	public void save(String filePath) { //Method complexity: O[N]
		
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filePath))) {
			
			output.writeObject(getAllEmployees());
			
		}
		
		catch (Exception e) {
			
			System.out.println(e.toString());
			throw new RuntimeException(e);
			
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void restore(String filePath) { //Method complexity: O[N]
		
		List<Employee> employees = null;
		
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath))) {
				
			employees = (List<Employee>) input.readObject();
			employees.forEach(this::hireEmployee);
			
		}
		
		catch (FileNotFoundException e) {
			
			System.out.println(filePath + "File doesn't exist");
			
		}
		
		catch (Exception e) {
			
			System.out.println(e.toString());
			throw new RuntimeException(e);
			
		}
		
	}

}
