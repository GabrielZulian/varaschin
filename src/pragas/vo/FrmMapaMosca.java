package pragas.vo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;

import javax.swing.ImageIcon;

public class FrmMapaMosca extends FrmMapaPai implements ActionListener {
	private static final long serialVersionUID = 471522816208580678L;

	public FrmMapaMosca() {
		setTitle(super.getTitle() + " - MOSCA DA FRUTA");
		setFrameIcon(new ImageIcon(getClass().getResource("/icons/icon_mosca_p.gif")));
		atualizaBotoes();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		Object origem = e.getSource();
		int index = 0;
		do {
			if (origem == btnsPontosPomar1.get(index)) {
				FrmDados fr = new FrmDados(index+1, 'M', indice);
				fr.setVisible(true);
				getDesktopPane().add(fr);   
				try {
					fr.setSelected(true);
				}
				catch (PropertyVetoException exc) {}
				FrmMenuGeralPragas.centralizaInternalFrame(fr, getDesktopPane());
				fr.setLocation(fr.getX()+400, fr.getY());
			} 
			index++;
		} while (index<btnsPontosPomar1.size());
	}
	
	public void atualizaBotoes() {
		int aux = 0;
		contBO = contDao.consultaPorTipo('M');
		do {
			indice = contBO.get(aux).getIndiceFinal();
			System.out.println(indice);
			int indiceBotao = contBO.get(aux).frasco.quadra.getNumero()-1;
			if (indice.compareTo(new BigDecimal("0.5")) >= 0) {
				super.btnsPontosPomar1.get(indiceBotao).setIcon(new ImageIcon(getClass().getResource("/icons/icon_ponto_vermelho.gif")));
				super.btnsPontosPomar1.get(indiceBotao).setRolloverIcon(new ImageIcon(getClass().getResource("/icons/icon_ponto_vermelho_hover.gif")));
			} else if (indice.compareTo(new BigDecimal("0.4")) >= 0 && indice.compareTo(new BigDecimal("0.5")) < 0) {
				super.btnsPontosPomar1.get(indiceBotao).setIcon(new ImageIcon(getClass().getResource("/icons/icon_ponto_amarelo.gif")));
				super.btnsPontosPomar1.get(indiceBotao).setRolloverIcon(new ImageIcon(getClass().getResource("/icons/icon_ponto_amarelo_hover.gif")));
			}
			aux++;
		} while (aux<contBO.size());
	}

	@Override
	public void calculaIndice() {
		
	}
}
