package com.techlabs.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	Connection conn;
    Statement stmt;

    public DBConnection() {
        conn = null;
        stmt = null;
    }
   
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user= root&password=Ri5@ksaxena&useSSL=false");
           // System.out.println("Connection established successfully");
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createCompanyDb()  {
        connect();
        try {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS Company");
            stmt.executeUpdate("USE Company");
            System.out.println("Database Created Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	closeConnection();
            //conn.close();
        }
    }
    public void createDepartmentTable() {
        connect();
        try {
            stmt.executeUpdate("USE Company");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Department (DEPTNO INT PRIMARY KEY, DNAME VARCHAR(255), LOC VARCHAR(255))");
            System.out.println("Department Table Created Succeefully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	closeConnection();
           // conn.close();
        }
    }
    public void createEmployeeTable()  {
        connect();
        try {
            stmt.executeUpdate("USE Company");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Employee (EMPNO INT PRIMARY KEY, ENAME VARCHAR(255), JOB VARCHAR(255), Manager INT, HIREDATE DATE, SAL FLOAT, COMM FLOAT, DEPTNO INT)");
            System.out.println("Employee Table Created Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	closeConnection();
        	//conn.close();
        }
    }
    public void insertDepartment(Department department) {
        connect();
        try {
            stmt.executeUpdate("USE Company");
            stmt.executeUpdate("INSERT INTO Department (DEPTNO, DNAME, LOC) VALUES (" + department.DEPTNO + ", '" + department.DNAME + "', '" + department.LOC + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    public void insertEmployee(Employee employee) throws SQLException {
        connect();
        try {
            stmt.executeUpdate("USE Company");
            stmt.executeUpdate("INSERT INTO Employee (EMPNO, ENAME, JOB, Manager, HIREDATE, SAL, COMM, DEPTNO) VALUES (" + employee.EMPNO + ", '" + employee.ENAME + "', '" + employee.JOB + "', " + employee.Manager + ", '" + employee.HIREDATE + "', " + employee.SAL + ", " + employee.COMM + ", " + employee.DEPTNO + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    public ResultSet getEmployeesByDepartment(String departmentName) {
        connect();
        ResultSet rs = null;
        try {
            stmt.executeUpdate("USE Company");
            rs = stmt.executeQuery("SELECT * FROM Employee INNER JOIN Department ON Employee.DEPTNO = Department.DEPTNO WHERE Department.DNAME = '" + departmentName + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    public void updateEmployeeCommission() {
        connect();
        try {
            stmt.executeUpdate("USE Company");
            stmt.executeUpdate("UPDATE Employee SET COMM = COMM *1.2");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    public void insertEmployeeInHR() {
        connect();
        try {
            stmt.executeUpdate("USE Company");
            stmt.executeUpdate("INSERT INTO Employee (EMPNO, ENAME, JOB, Manager, HIREDATE, SAL, COMM, DEPTNO) VALUES (7912, 'Suraj Agarwal', 'HRMANAGER', 7834, '2023-01-01', 5040.00, 400.00, 50)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    public void deleteEmployeesWithSalaryLessThan(double salary) {
        connect();
        try {
            stmt.executeUpdate("USE Company");
            stmt.executeUpdate("DELETE FROM Employee WHERE SAL < " + salary);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    public ResultSet getAllEmployees() {
        connect();
        ResultSet rs = null;
        try {
            stmt.executeUpdate("USE Company");
            rs = stmt.executeQuery("SELECT * FROM Employee");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    public ResultSet getAllDepartments() {
        connect();
        ResultSet rs = null;
        try {
            stmt.executeUpdate("USE Company");
            rs = stmt.executeQuery("SELECT * FROM Department");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private void closeConnection() {
        try {
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
