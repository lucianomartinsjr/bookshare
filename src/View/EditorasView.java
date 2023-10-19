package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Model.EditorasBEAN;
import Model.EditorasDAO;

public class EditorasView extends JFrame {

    private JTextField razaoSocialTextField;
    private JTextField statusTextField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTable editorasTable;
    private DefaultTableModel tableModel;

    private EditorasDAO editorasDAO;

    public EditorasView() {
        editorasDAO = EditorasDAO.getInstance();
        initComponents();
  
    }

    private void initComponents() {
    	 setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         setTitle("Editoras CRUD");
      // Criação do painel principal
         JPanel mainPanel = new JPanel(new GridBagLayout());
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.insets = new Insets(5, 5, 5, 5);  // Margens entre os componentes
         gbc.fill = GridBagConstraints.HORIZONTAL; // Preenche a largura horizontalmente

         // Adiciona os componentes ao layout
         gbc.gridx = 0;
         gbc.gridy = 0;
         mainPanel.add(new JLabel("Razão Social:"), gbc);

         gbc.gridx = 1;
         gbc.gridy = 0;
         gbc.weightx = 1.0; // Faz com que este componente preencha o espaço disponível na horizontal
         razaoSocialTextField = new JTextField();
         mainPanel.add(razaoSocialTextField, gbc);

         gbc.gridx = 0;
         gbc.gridy = 1;
         gbc.weightx = 0.0; // Redefine o peso para 0 para o próximo componente
         mainPanel.add(new JLabel("Status:"), gbc);

         gbc.gridx = 1;
         gbc.gridy = 1;
         gbc.weightx = 1.0; // Faz com que este componente preencha o espaço disponível na horizontal
         statusTextField = new JTextField();
         mainPanel.add(statusTextField, gbc);

         gbc.gridx = 0;
         gbc.gridy = 2;
         gbc.gridwidth = 2; // Este botão ocupa duas colunas
         gbc.weightx = 1.0; // Faz com que este componente preencha o espaço disponível na horizontal
         addButton = new JButton("Adicionar");
         mainPanel.add(addButton, gbc);

         gbc.gridx = 0;
         gbc.gridy = 3;
         mainPanel.add(new JLabel("Tabela de Editoras:"), gbc);

         gbc.gridx = 0;
         gbc.gridy = 4;
         gbc.gridwidth = 2;
         gbc.weightx = 1.0;
         editorasTable = new JTable();
         mainPanel.add(new JScrollPane(editorasTable), gbc);

         // Adiciona o painel principal à janela
         getContentPane().add(mainPanel);

         pack(); // Ajusta o tamanho da janela automaticamente com base no conteúdo
         setLocationRelativeTo(null); // Centraliza a janela
         
         addButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 addEditora();
             }
         });
         
         
     }
  

    private void updateTableData() {
        // Atualiza os dados da tabela com os dados do banco de dados
        ArrayList<EditorasBEAN> editorasList = editorasDAO.findAllEditoras();
        tableModel.setRowCount(0); // Limpa os dados existentes na tabela

        for (EditorasBEAN editora : editorasList) {
            // Adiciona uma nova linha à tabela com os dados da editora
            tableModel.addRow(new Object[]{editora.getId(), editora.getRazaoSocial(), editora.getStatus()});
        }

        editorasTable.setModel(tableModel);
    }

    private void addEditora() {
        // Adiciona uma nova editora ao banco de dados com base nos dados inseridos
        String razaoSocial = razaoSocialTextField.getText();
        String status = statusTextField.getText();

        EditorasBEAN editora = new EditorasBEAN(0, razaoSocial, status);
        long result = editorasDAO.create(editora);

        if (result > 0) {
            JOptionPane.showMessageDialog(this, "Editora adicionada com sucesso!");
            updateTableData();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar editora.");
        }
    }

    private void updateEditora() {
        // Atualiza os dados de uma editora no banco de dados com base nos dados inseridos
        int selectedRowIndex = editorasTable.getSelectedRow();

        if (selectedRowIndex != -1) {
            int idEditora = (int) editorasTable.getValueAt(selectedRowIndex, 0);
            String razaoSocial = razaoSocialTextField.getText();
            String status = statusTextField.getText();

            EditorasBEAN editora = new EditorasBEAN(idEditora, razaoSocial, status);
            editorasDAO.update(editora);

            JOptionPane.showMessageDialog(this, "Editora atualizada com sucesso!");
            updateTableData();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma editora para atualizar.");
        }
    }

    private void deleteEditora() {
        // Exclui uma editora do banco de dados com base na linha selecionada na tabela
        int selectedRowIndex = editorasTable.getSelectedRow();

        if (selectedRowIndex != -1) {
            int idEditora = (int) editorasTable.getValueAt(selectedRowIndex, 0);
            EditorasBEAN editora = editorasDAO.findEditoras(idEditora);

            if (editora != null) {
                editorasDAO.delete(editora);
                JOptionPane.showMessageDialog(this, "Editora excluída com sucesso!");
                updateTableData();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir editora.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma editora para excluir.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EditorasView().setVisible(true);
        });
    }
}