package es.curso.clientes.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.curso.clientes.modelo.Cliente;
import es.curso.clientes.modelo.ClienteDAO;
import es.curso.clientes.modelo.DAOException;
import es.curso.clientes.modelo.IClienteDAO;

/**
 * Servlet implementation class Controlador
 */
@WebServlet("*.do")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IClienteDAO clienteDAO;

	public Controlador() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		clienteDAO = new ClienteDAO();
		try {
			clienteDAO.conectar("root", "root");
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		try {
			clienteDAO.cerrar();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.destroy();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String paginaDestino = "index.html";
		switch (request.getServletPath()) {
		case "/login.do":
			paginaDestino = login(request, response);
			break;
		case "/logout.do":
			paginaDestino = logout(request, response);
			break;
		case "/grabar.do":
			paginaDestino = grabarCliente(request, response);
			break;
		case "/nuevo.do":
			paginaDestino = nuevo(request, response);
			break;
		case "/eliminarForm.do":
			paginaDestino = eliminarForm(request, response);
			break;
		case "/eliminar.do":
			paginaDestino = eliminarCliente(request, response);
			break;
		case "/listar.do":
			paginaDestino = listarClients(request, response);
			break;
		default:
			paginaDestino = "errorPage.html";

		}
		request.getRequestDispatcher(paginaDestino).forward(request, response);
	}

	private String listarClients(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Cliente> clientes = null;
		try {
			clientes = (ArrayList<Cliente>) clienteDAO.leerTodos();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("clientes", clientes);
		request.setAttribute("contenido", "vistas/clientsList.jsp");
		return "index.jsp";
	}

	private String eliminarForm(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("contenido", "vistas/eliminarForm.html");
		return "index.jsp";
	}

	private String eliminarCliente(HttpServletRequest request, HttpServletResponse response) {
		String idCliente = request.getParameter("idCliente");
		int result = 0;
		try {
			result = clienteDAO.borrar(idCliente);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		if (result > 0) {
			request.setAttribute("result", "Cliente con ID " + idCliente + " borrado");
		} else {
			request.setAttribute("result", "Cliente con ID " + idCliente + " no existe");
		}
		request.setAttribute("contenido", "vistas/result.jsp");
		return "index.jsp";
	}

	private String nuevo(HttpServletRequest request, HttpServletResponse response) {
		List<String> paises = clienteDAO.getPaises();
		request.setAttribute("paises", paises);
		request.setAttribute("contenido", "vistas/formCliente.jsp");
		return "index.jsp";

	}

	private String grabarCliente(HttpServletRequest request, HttpServletResponse response) {
		Cliente cliente = new Cliente();
		int result = 0;
		cliente.setIdcliente(request.getParameter("idCliente"));
		cliente.setNombre(request.getParameter("nombre"));
		cliente.setPais(request.getParameter("pais"));
		try {

			result = clienteDAO.grabar(cliente);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		if (result > 0)
			request.setAttribute("result", "Nuevo cliente creado");
		else
			request.setAttribute("result", "Cliente no creado");
		request.setAttribute("contenido", "vistas/result.jsp");
		return "index.jsp";
	}

	private String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession(true).invalidate();

		return "index.html";
	}

	private String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getParameter("login") == null || request.getParameter("pass") == null)
			return "index.html";

		String slogin = request.getParameter("login");
		String spass = request.getParameter("pass");
		String paginaDestino;

		if (slogin.equals("ADMIN") && spass.equals("1234")) {
			// Crear la session
			HttpSession ses = request.getSession(true);

			// Almasenar login en session
			ses.setAttribute("login", slogin);

			paginaDestino = "principal.jsp";

		} else
			// Ir al formulario de inicio
			paginaDestino = "index.html";

		return paginaDestino;
	}

}
