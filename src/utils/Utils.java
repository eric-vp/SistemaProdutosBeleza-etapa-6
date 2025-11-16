/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Pichau
 */
public class Utils {
    public int confirmar(String texto) {
        Object[] opcoes = {"Sim", "Não"};
        
        int opcao = JOptionPane.showOptionDialog(
            null, 
            texto, 
            "Confirmação", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcoes,
            opcoes[0]);
        
        return opcao;
    }
        
    public int getLinhaSelecionada(JTable tabela, String nomeItem) {
        int linha = tabela.getSelectedRow();
        
        if(linha == -1) {
            JOptionPane.showMessageDialog(null, "É necessário selecionar " + nomeItem + ".");
        }        
        return linha;
    }
    
    public static void limparCampos(JTextComponent... campos) {
        for (JTextComponent campo : campos) {
            campo.setText("");
        }
    }
}
