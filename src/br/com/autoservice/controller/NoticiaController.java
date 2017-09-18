package br.com.autoservice.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.autoservice.dao.NoticiaDao;
import br.com.robson.rest.Noticia;
import br.com.robson.rest.NoticiaBase;

public class NoticiaController {
	
	private NoticiaDao dao = new NoticiaDao();
	private static NoticiaController uniqueInstance = new NoticiaController(); 
	
	
	public static NoticiaController getInstance() { 
		if (uniqueInstance == null) uniqueInstance = new NoticiaController(); 
		return uniqueInstance;
	}
	
	public List<Noticia> listar(){
		
		List<Noticia> lista = new ArrayList<Noticia>();
		List<NoticiaBase> listaBase = dao.listar();
		
		if(listaBase!=null && !listaBase.isEmpty())
			lista=toListNoticia(listaBase);
		
		return lista;
	}
	
	public void salvar(Noticia noticia){
		
		NoticiaBase base = toNoticiaBase(noticia);
		dao.inserir(base);
		
	}
	
	public void apagar(String codigo){
		
		NoticiaBase base = new NoticiaBase();
		base.setCodigo(Long.parseLong(codigo));
		dao.excluir(base);
		
	}
	
	private NoticiaBase toNoticiaBase(Noticia noticia){
		NoticiaBase item = new NoticiaBase();
		item.setAutor(noticia.getAutor());
		item.setDecricao(noticia.getDecricao());
		item.setFoto(noticia.getFoto());
		item.setTitulo(noticia.getTitulo());
		item.setUrl(noticia.getUrl());
		return item;
	}
	
	private List<Noticia> toListNoticia(List<NoticiaBase> listaBase){
		List<Noticia> lista = new ArrayList<Noticia>();
		
		for (NoticiaBase noticia : listaBase) {
			Noticia item = new Noticia();
			item.setAutor(noticia.getAutor());
			item.setCodigo(noticia.getCodigo().intValue());
			item.setDecricao(noticia.getDecricao());
			item.setFoto(noticia.getFoto());
			item.setTitulo(noticia.getTitulo());
			item.setUrl(noticia.getUrl());
			lista.add(item);
		}
		return lista;
	}

}
