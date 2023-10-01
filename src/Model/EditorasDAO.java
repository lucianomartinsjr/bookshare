package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EditorasDAO {
	private static EditorasDAO instance;

	private EditorasDAO() {
		MySQLDAO.getConnection();
	}

	public static EditorasDAO getInstance() {
		if (instance == null) {
			instance = new EditorasDAO();
		}
		return instance;
	}

	public long create(EditorasBEAN editoras) {
		String query = "INSERT INTO editoras (razaoSocial) VALUES (?)";
		return MySQLDAO.executeQuery(query, editoras.getRazaoSocial());
	}

	public void update(EditorasBEAN editoras) {
		String query = "UPDATE CONTATOS SET razaoSocial=? WHERE idEditora = ?";
		MySQLDAO.executeQuery(query, editoras.getRazaoSocial(), editoras.getStatus(),
				editoras.getId());

	}

	public void delete(EditorasBEAN editoras) {
		MySQLDAO.executeQuery("DELETE FROM CONTATOS WHERE idEditora = ?", editoras.getId());
	}

	public ArrayList<EditorasBEAN> findAllEditoras() {
		return listaEditoras("SELECT * FROM editoras ORDER BY Nome");
	}

	public ArrayList<EditorasBEAN> listaEditoras(String query) {
		ArrayList<EditorasBEAN> lista = new ArrayList<EditorasBEAN>();
		ResultSet rs = null;
		rs = MySQLDAO.getResultSet(query);
		try {
			while (rs.next()) {
				lista.add(new EditorasBEAN(rs.getInt("idEditora"), rs.getString("razaoSocial"), rs.getString("status")));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public EditorasBEAN findEditoras(int id) {
		EditorasBEAN result = null;
		ResultSet rs = null;
		rs = MySQLDAO.getResultSet("SELECT * FROM editoras WHERE id=?", id);
		try {
			if (rs.next()) {
				result = new EditorasBEAN(rs.getInt("idEditora"), rs.getString("razaoSocial"), rs.getString("status"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

public int findId(EditorasBEAN editora){
int result = 0;
ResultSet rs = null;
rs = MySQLDAO.getResultSet("SELECT * FROM editoras WHERE razaoSocial= ? and status = ?",
			editora.getRazaoSocial(),
			editora.getStatus());
try{
if(rs.next()){
result = rs.getInt("idEditora");
}
rs.close ();
}catch(SQLException e){
e.printStackTrace();
}
return result;
}

	public Boolean isExist(int idEditora) {
		Boolean result = false;
		ResultSet rs = null;
		rs = MySQLDAO.getResultSet("SELECT * FROM editoras WHERE idEditora= ?", idEditora);
		try {
			if (rs.next()) {
				result = true;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}