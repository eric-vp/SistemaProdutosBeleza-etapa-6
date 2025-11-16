/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import criptografia.Criptografia;
import javax.swing.JOptionPane;
import usuario.Usuario;
import usuario.UsuarioBD;
import view.TelaInicialVIEW;

/**
 *
 * @author ericv
 */
public class LoginController {
    public void login(String login, String senha) {
        if(login.isBlank() || senha.isBlank()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos");
            return;
        }
        
        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setSenha(Criptografia.getMD5(senha));
        
        usuario = UsuarioBD.validarUsusario(usuario);
        
        if(usuario != null){
            TelaInicialVIEW telaInicial = new TelaInicialVIEW(usuario);
            telaInicial.setVisible(true);            
        } else {
            JOptionPane.showMessageDialog(null, "Erro de autenticação! Verifique se os dados estão corretos.");
        }
    }
}
