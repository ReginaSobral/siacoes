package br.edu.utfpr.dv.siacoes.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Set;

interface INegocio<T> {
    
    String getStringSQLfindByDepartment(int id);
    void getStringSQLloadObject(ResultSet rs);
    Connection getStringSQLsave(Connection conn);
}
