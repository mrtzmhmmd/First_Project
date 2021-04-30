package controller;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Employee;

public class EmployeeManager {
	private static SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

	public Integer addEmployee(Employee employee) {
		Session session = SESSION_FACTORY.openSession();
		Transaction tx = null;
		Integer employeeId = null;
		try {
			tx = session.beginTransaction();
			employeeId = (Integer) session.save(employee);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return employeeId;
	}

	public List<Employee> listEmployees() {
		Session session = SESSION_FACTORY.openSession();
		List<Employee> employees = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			employees = session.createQuery("FROM Employee").list();
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return employees;
	}

	public void updateEmplyee(int employeeId, int salary) {
		Session session = SESSION_FACTORY.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Employee employee = session.get(Employee.class, employeeId);
			employee.setSalary(salary);
			session.update(employee);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteEmployee(Employee employee) {
		Session session = SESSION_FACTORY.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(employee);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}

	public Employee getEmployeeById(int employeeId) {
		Session session = SESSION_FACTORY.openSession();
		Transaction tx = null;
		Employee employee = null;
		try {
			tx = session.beginTransaction();
			employee = session.get(Employee.class, employeeId);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return employee;
	}
}