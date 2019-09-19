package br.edu.utfpr.dv.siacoes.dao;
import java.util.Set;

interface INegocio<T> {
    
    void incluir(T objeto) throws Exception;
    Set<T> listar();
    
}
