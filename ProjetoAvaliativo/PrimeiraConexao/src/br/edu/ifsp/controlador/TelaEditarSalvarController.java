package br.edu.ifsp.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.edu.ifsp.dao.PessoaDAO;
import br.edu.ifsp.modelo.Pessoa;
import br.edu.ifsp.tela.TelaEditar;

public class TelaEditarSalvarController implements ActionListener{

	private TelaEditar tela = null;
	
	public TelaEditarSalvarController(TelaEditar telaEditar) {
		
		tela = telaEditar;
		tela.getBtnSave().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Pessoa pessoa = new Pessoa();
		
		pessoa.setId(Integer.parseInt(tela.getId()));
		pessoa.setNome(tela.getFldName().getText());
		pessoa.setIdade(Integer.parseInt(tela.getFldAge().getText()));
		pessoa.setEndereco(tela.getFldAdress().getText());

		new PessoaDAO().editarPessoa(pessoa);
		
		JOptionPane.showMessageDialog(null, tela.getFldName().getText() + " foi alterado.");
		
		tela.dispose();
	}

}
