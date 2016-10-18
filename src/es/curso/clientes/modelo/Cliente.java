package es.curso.clientes.modelo;

public class Cliente {
	
	private String idcliente;
	private String nombre;
	private String pais;
	
	public Cliente(String idcliente, String nombre, String pais) {
		super();
		this.idcliente = idcliente;
		this.nombre = nombre;
		this.pais = pais;
	}

	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public String getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(String idcliente) {
		this.idcliente = idcliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Cliente [idcliente=" + idcliente + ", nombre=" + nombre + ", pais=" + pais + "]";
	}

}
