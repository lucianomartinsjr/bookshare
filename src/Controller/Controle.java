package Controller;

import java.util.ArrayList;

import Model.EditorasBEAN;
import Model.EditorasDAO;

public class Controle {
	public Controle() {
	}

	public void addEditora(EditorasBEAN editora) {
		EditorasDAO.getInstance().create(editora);
	}

	public void updateEditora(EditorasBEAN editora) {
		EditorasDAO.getInstance().update(editora);
	}

	public void deleteEditora(EditorasBEAN editora) {
		EditorasDAO.getInstance().delete(editora);
	}

	public EditorasBEAN findEditoras(int id) {
		return EditorasDAO.getInstance().findEditoras(id);
	}

	public int findIdEditora(EditorasBEAN editora) {
		return EditorasDAO.getInstance().findId(editora);
	}

	public Boolean isExist(int id) {
		return EditorasDAO.getInstance().isExist(id);
	}

	public ArrayList<EditorasBEAN> listaEditoras() {
		return EditorasDAO.getInstance().findAllEditoras();
	}
}