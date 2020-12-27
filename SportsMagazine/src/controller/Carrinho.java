package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Produto;

public class Carrinho {
	
	private static List<Produto> produtos = new ArrayList<Produto>();
	
	public void addProdutos(Produto... ps) {
		for (Produto p : ps) {
			produtos.add(p);
		}
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public void removeProduto(Produto p) {
		Iterator<Produto> itProduto = produtos.iterator();
		
		while (itProduto.hasNext()) {
			Produto produto = itProduto.next();
			if (produto.getProduto().equals(p.getProduto()) && produto.getPreco() == p.getPreco()) {
				itProduto.remove();
			}
		}
	}
}
