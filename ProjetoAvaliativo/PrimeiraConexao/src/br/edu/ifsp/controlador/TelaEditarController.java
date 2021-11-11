package br.edu.ifsp.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.ifsp.tela.TelaEditar;
import br.edu.ifsp.tela.TelaPrincipal;

public class TelaEditarController implements ActionListener{

private TelaPrincipal tela = null;
	
	public TelaEditarController(TelaPrincipal telaPrincipal) {
		
		tela = telaPrincipal;
		tela.getBtnEdit().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		TelaEditar telaEditar = new TelaEditar();
	}
	
}
