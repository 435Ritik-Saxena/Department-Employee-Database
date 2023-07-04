package com.techlabs.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException  {
		// TODO Auto-generated method stub
		DBConnection dbConnection = new DBConnection();
		dbConnection.createCompanyDb();
		dbConnection.createDepartmentTable();
        dbConnection.createEmployeeTable();
        
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Insert Records into Department");
            System.out.println("2. Insert Records into Employee");
            System.out.println("3. Display all employees in a particular department");
            System.out.println("4. Update records of employees (Increase commission by 20%)");
            System.out.println("5. Insert new employee in the HR department");
            System.out.println("6. Delete employees having salary less than a specified amount");
            System.out.println("7. Display all employees");
            System.out.println("8. Display all departments");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter Department Details:");
                    System.out.print("DEPTNO: ");
                    int deptNo = scanner.nextInt();
                    System.out.print("DNAME: ");
                    String dname = scanner.next();
                    System.out.print("LOC: ");
                    String loc = scanner.next();

                    Department department = new Department(deptNo, dname, loc);
                    dbConnection.insertDepartment(department);
                    System.out.println("Department inserted successfully.");
                    break;
                case 2:
                    System.out.println("Enter Employee Details:");
                    System.out.print("EMPNO: ");
                    int empNo = scanner.nextInt();
                    System.out.print("ENAME: ");
                    String ename = scanner.next();
                    System.out.print("JOB: ");
                    String job = scanner.next();
                    System.out.print("Manager: ");
                    int manager = scanner.nextInt();
                    System.out.print("HIREDATE: ");
                    String hireDate = scanner.next();
                    System.out.print("SAL: ");
                    double sal = scanner.nextDouble();
                    System.out.print("COMM: ");
                    double comm = scanner.nextDouble();
                    System.out.print("DEPTNO: ");
                    int deptNoEmp = scanner.nextInt();

                    Employee employee = new Employee(empNo, ename, job, manager, hireDate, sal, comm, deptNoEmp);
                    dbConnection.insertEmployee(employee);
                    System.out.println("Employee inserted successfully.");
                    break;
                case 3:
                    System.out.print("Enter Department Name: ");
                    String departmentName = scanner.next();
                    ResultSet employeesByDepartment = dbConnection.getEmployeesByDepartment(departmentName);
                    while (employeesByDepartment.next()) {
                        int empNoDept = employeesByDepartment.getInt("EMPNO");
                        String enameDept = employeesByDepartment.getString("ENAME");
                        String jobDept = employeesByDepartment.getString("JOB");
                        int managerDept = employeesByDepartment.getInt("Manager");
                        String hireDateDept = employeesByDepartment.getString("HIREDATE");
                        double salDept = employeesByDepartment.getDouble("SAL");
                        double commDept = employeesByDepartment.getDouble("COMM");
                        int deptNoDept = employeesByDepartment.getInt("DEPTNO");

                        System.out.println("EMPNO: " + empNoDept);
                        System.out.println("ENAME: " + enameDept);
                        System.out.println("JOB: " + jobDept);
                        System.out.println("Manager: " + managerDept);
                        System.out.println("HIREDATE: " + hireDateDept);
                        System.out.println("SAL: " + salDept);
                        System.out.println("COMM: " + commDept);
                        System.out.println("DEPTNO: " + deptNoDept);
                        System.out.println("---------------------");
                    }
                    break;
                case 4:
                    dbConnection.updateEmployeeCommission();
                    System.out.println("Employee commission updated successfully.");
                    break;
                case 5:
                    dbConnection.insertEmployeeInHR();
                    System.out.println("New employee inserted into HR department.");
                    break;
                case 6:
                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();
                    dbConnection.deleteEmployeesWithSalaryLessThan(salary);
                    System.out.println("Employees with salary less than " + salary + " deleted successfully.");
                    break;
                case 7:
                  ResultSet allEmployees = dbConnection.getAllEmployees();
                  while (allEmployees.next()) {
        	         System.out.println("EMPNO: " + allEmployees.getInt("EMPNO"));
        	         System.out.println("ENAME: " + allEmployees.getString("ENAME"));
        	         System.out.println("JOB: " + allEmployees.getString("JOB"));
        	         System.out.println("Manager: " + allEmployees.getInt("Manager"));
        	         System.out.println("HIREDATE: " + allEmployees.getString("HIREDATE"));
        	         System.out.println("SAL: " + allEmployees.getDouble("SAL"));
        	         System.out.println("COMM: " + allEmployees.getDouble("COMM"));
        	         System.out.println("DEPTNO: " + allEmployees.getInt("DEPTNO"));
        	         System.out.println("---------------------");
                  }
    	          break;
                case 8:
                    ResultSet allDepartments = dbConnection.getAllDepartments();
                    while (allDepartments.next()) {
                        

                        System.out.println("DEPTNO: " + allDepartments.getInt("DEPTNO"));
                        System.out.println("DNAME: " + allDepartments.getString("DNAME"));
                        System.out.println("LOC: " + allDepartments.getString("LOC"));
                        System.out.println("---------------------");
                    }
                    break;
                case 9:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 9);    

	}

}
