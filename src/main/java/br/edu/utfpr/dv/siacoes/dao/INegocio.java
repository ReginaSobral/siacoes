package br.edu.utfpr.dv.siacoes.dao;
import java.sql.ResultSet;
import java.util.Set;

interface INegocio<T> {
    
    String getStringSQLfindByDepartment(int id);
    void getStringSQLloadObject();
    String getStringSQLsave(Connection conn);
}
