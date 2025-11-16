/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import relatorio.Relatorio;
import relatorio.RelatorioDAO;

/**
 *
 * @author ericv
 */
public class RelatorioController {
    public Relatorio pesquisarRelatorio(String inicioStr, String fimStr) {
        if (inicioStr.isBlank() || fimStr.isBlank()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            return null;
        }
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate inicio = LocalDate.parse(inicioStr, formatter);
            LocalDate fim = LocalDate.parse(fimStr, formatter);
            
            RelatorioDAO relatorioDAO = new RelatorioDAO();
            return relatorioDAO.gerarRelatorio(inicio, fim);
            
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Data inválida. Use o formato dd/MM/yyyy.");
            return null;
        }
    }
}
