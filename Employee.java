package com.techlabs.DB;

import java.util.Date;

public class Employee {
	int EMPNO;
    String ENAME;
    String JOB;
    int Manager;
    String HIREDATE;
    double SAL;
    double COMM;
    int DEPTNO;

    public Employee(int EMPNO, String ENAME, String JOB, int Manager, String HIREDATE, double SAL, double COMM, int DEPTNO) {
        this.EMPNO = EMPNO;
        this.ENAME = ENAME;
        this.JOB = JOB;
        this.Manager = Manager;
        this.HIREDATE = HIREDATE;
        this.SAL = SAL;
        this.COMM = COMM;
        this.DEPTNO = DEPTNO;
    }
}
