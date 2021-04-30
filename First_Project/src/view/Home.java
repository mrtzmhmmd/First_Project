package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.EmployeeManager;
import model.Employee;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Home() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		EmployeeManager EM = new EmployeeManager();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\r\n" + "<meta charset=\"utf-8\">\r\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">\r\n"
				+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n"
				+ "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\r\n"
				+ "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>\r\n"
				+ "<title>First Project</title>\n</head>");
		out.println("<body>");
		out.println(
				"<nav class='navbar navbar-expand-sm bg-dark navbar-dark fixed-top'>\n" + "<ul class='navbar-nav'>\n"
						+ "<li class='nav-item'>\n<a class='nav-link' href='#add-employee'>Add</a>\n</li>\n"
						+ "<li class='nav-item'>\n<a class='nav-link' href='#edit-employee'>Edit</a>\n</li>\n"
						+ "<li class='nav-item'>\n<a class='nav-link' href='#delete-employee'>Delete</a>\n</li>\n"
						+ "<li class='nav-item'>\n<a class='nav-link' href='#show-employee'>Show</a>\n</li>\n"
						+ "</ul>\n</nav>\n" + "<div class='container-fluid' style='margin-top:80px'>\n"
						+ "<h2>Simoorgh Project</h2>\n" + "<div class='container-fluid' id='add-employee'>\n"
						+ "<form>\n" + "<h3>Add Employee</h3>\n" + "<label for='txt-add-name'>Name:</label>\n"
						+ "<input type='text' id='txt-add-name' name='txt-add-name' placeholder='Name'><br>\n"
						+ "<label for='txt-add-family'>Family:</label>\n"
						+ "<input type='text' id='txt-add-family' name='txt-add-family' placeholder='Family'><br>\n"
						+ "<label for='txt-add-salary'>Salary:</label>\n"
						+ "<input type='number' id='txt-add-salary' name='txt-add-salary' placeholder='Salary'><br>\n"
						+ "<button name='submit-add-employee'>Add Employee</button>");
		if (request.getParameter("submit-add-employee") != null) {
			if (request.getParameter("txt-add-name") != "") {
				if (request.getParameter("txt-add-family") != "") {
					if (request.getParameter("txt-add-salary") != "") {
						String name = request.getParameter("txt-add-name");
						String family = request.getParameter("txt-add-family");
						int salary = Integer.valueOf(request.getParameter("txt-add-salary"));
						int empId = EM.addEmployee(new Employee(name, family, salary));
						out.println("<script>");
						out.println("alert('ID is: " + empId + "');");
						out.println("</script>");
					} else {
						out.println("<script>");
						out.println("alert('Enter salary');");
						out.println("</script>");
					}
				} else {
					out.println("<script>");
					out.println("alert('Enter family');");
					out.println("</script>");
				}
			} else {
				out.println("<script>");
				out.println("alert('Enter name');");
				out.println("</script>");
			}
		}
		out.println("</form>\n</div>");
		out.println("<div class='container-fluid' id='edit-employee'>");
		out.println("<h3>Edit Employee</h3>");
		out.println("<form>");
		out.println("<label for='txt-edit-id'>ID:</label>");
		out.println("<input type='number' name='txt-edit-id' id='txt-edit-id' placeholder='ID' /><br>");
		out.println("<label for='txt-edit-salary'>Salary:</label>");
		out.println("<input type='number' name='txt-edit-salary' id='txt-edit-salary' placeholder='Salary' /><br>");
		out.println("<button name=btn-edit-employee>Edit Employee</button>");
		if (request.getParameter("btn-edit-employee") != null) {
			if (request.getParameter("txt-edit-id") != "") {
				int employeeId = Integer.valueOf(request.getParameter("txt-edit-id"));
				Employee employee = EM.getEmployeeById(employeeId);
				if (employee != null) {
					if (request.getParameter("txt-edit-salary") != "") {
						int salary = Integer.valueOf(request.getParameter("txt-edit-salary"));
						EM.updateEmplyee(employeeId, salary);
						out.println("<script>");
						out.println("alert('Successfully changed!');");
						out.println("</script>");
					} else {
						out.println("<script>");
						out.println("alert('Enter salary');");
						out.println("</script>");
					}
				} else {
					out.println("<script>");
					out.println("alert('ID not found!');");
					out.println("</script>");
				}
			} else {
				out.println("<script>");
				out.println("alert('Enter ID');");
				out.println("</script>");
			}
		}
		out.println("</form>");
		out.println("</div>");

		out.println("<div class='container-fluid' id='delete-employee'>");
		out.println("<h3>Delete Employee</h3>");
		out.println("<form>");
		out.println("<label for='txt-delete-id'>ID:</label>");
		out.println("<input type='number' name='txt-delete-id' id='txt-delete-id' placeholder='ID' /><br>");
		out.println("<button name=btn-delete-employee>Delete Employee</button>");
		if (request.getParameter("btn-delete-employee") != null) {
			if (request.getParameter("txt-delete-id") != "") {
				int employeeId = Integer.valueOf(request.getParameter("txt-delete-id"));
				Employee employee = EM.getEmployeeById(employeeId);
				if (employee != null) {
					EM.deleteEmployee(employee);
					out.println("<script>");
					out.println("alert('Successfully Delete!');");
					out.println("</script>");
				} else {
					out.println("<script>");
					out.println("alert('ID not found!');");
					out.println("</script>");
				}
			} else {
				out.println("<script>");
				out.println("alert('Enter ID');");
				out.println("</script>");
			}
		}
		out.println("</form>");
		out.println("</div>");

		out.println("<div class='container-fluid' id='show-employee'>");
		out.println("<h3>Show Employee</h3>");
		out.println("<form>");
		out.println("<label for='txt-show-id'>ID:</label>");
		out.println("<input type='number' name='txt-show-id' id='txt-show-id' placeholder='ID' /><br>");
		out.println("<button name=btn-show-employee>Show Employee</button>");
		if (request.getParameter("btn-show-employee") != null) {
			if (request.getParameter("txt-show-id") != "") {
				int employeeId = Integer.valueOf(request.getParameter("txt-show-id"));
				Employee employee = EM.getEmployeeById(employeeId);
				if (employee != null) {
					out.println("<label>" + employee.getEmployeeId() + ", " + employee.getName() + ", "
							+ employee.getFamily() + ", " + employee.getSalary() + "</label>");
				} else {
					out.println("<script>");
					out.println("alert('ID not found!');");
					out.println("</script>");
				}
			} else {
				out.println("<script>");
				out.println("alert('Enter ID');");
				out.println("</script>");
			}
		}
		out.println("</form>");
		out.println("</div>");
		out.println("</div>\n</body>\n</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void printTable(List<Employee> employees, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out = response.getWriter();
		out.println("<table><caption>Employees</caption>");
		out.println("<thead><tr><th>ID</th><th>Name</th><th>Family</th><th>Salary</th></tr></thead>");
		for (Employee employee : employees) {
			System.out.println(employee);
			out.println("<tr><td>" + employee.getEmployeeId() + "</td><td>" + employee.getName() + "</td><td>"
					+ employee.getFamily() + "</td><td>" + employee.getSalary() + "</td></tr>");
		}
		out.println("</table>");
	}
}