package util;

import java.util.*;

import javax.swing.table.AbstractTableModel;

public class ModeloTabelaDiaSafrista extends AbstractTableModel {
	private static final long serialVersionUID = 3139088923058514968L;
	
	private ArrayList<Object> linhas = null;
	private String[] colunas = null;
	private boolean[] colEditavel;

	public ModeloTabelaDiaSafrista(ArrayList<Object> lin, String[] col, boolean[] editavel) {
		setLinhas(lin);
		setColunas(col);
		colEditavel = editavel;
	}

	public ArrayList<Object> getLinhas() 
	{
		return linhas;
	}

	public void setLinhas(ArrayList<Object> dados) 
	{
		linhas = dados;
	}

	public String[] getColunas() 
	{
		return colunas;
	}

	public void setColunas(String[] nomes)
	{
		colunas = nomes;
	}

	public int getColumnCount() 
	{
		return colunas.length;
	}

	public int getRowCount() 
	{
		return linhas.size();
	}

	public String getColumnName(int numCol)
	{
		return colunas[numCol];
	}

	public boolean isCellEditable(int numLin, int numCol)
	{
			return colEditavel[numCol];
	} 

	public Object getValueAt(int numLin, int numCol) 
	{
		Object[] linha = (Object[]) getLinhas().get(numLin);
		return linha[numCol];
	}

	public void setValueAt(Object dado, int numLin, int numCol)
	{
		Object[] linha = (Object[])getLinhas().get(numLin);
		
		if (numCol==0 && dado.equals("N")) {
			linha[5] = (double)0;
			linha[1] = false;
		} else if (numCol==0 && dado.equals("M/T")) {
			linha[5] = Double.valueOf(getValueAt(numLin, 5).toString()) / 2;
		}
		
		linha[numCol] = dado;
		fireTableDataChanged();
	}

	public void addRow(Object[] dados)
	{
		getLinhas().add(dados);
		fireTableDataChanged();
	} 

	public void removeRow(int numLin)
	{
		getLinhas().remove(numLin);
		fireTableDataChanged();
	}

	public Class<? extends Object> getColumnClass(int numCol)
	{
		Object[] linha = (Object[])getLinhas().get(0);
		return linha[numCol].getClass();
	}
}

