package br.edu.utfpr.dv.siacoes.dao;

import br.edu.utfpr.dv.siacoes.log.UpdateEvent;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.sql.Statement;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.utfpr.dv.siacoes.log.UpdateEvent;

import java.util.Set;

public abstract class TemplateDAO<T> {

    protected abstract void getStringSQLloadObject();

    protected abstract void ormloadObject(PreparedStatement statement, T objeto) throws SQLException;

    public final Set<T> loadObject(ResultSet rs) throws SQLException{
        var config = new HashSet<T>();            
getStringSQLloadObject(ResultSet rs)
        return config;
    }

    protected abstract Connection getStringSQLsave(Connection conn);

    protected abstract void ormsave(ResultSet result, Set<T> resultado) throws SQLException;

    public final int save(int idUser, Set<T> config) throws SQLException{
        boolean insert = (this.findByDepartment(config.getDepartment().getIdDepartment()) == null);
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            Set<T> resultado = new HashSet<T>();
            conn = ConnectionDAO.getInstance().getConnection(conn);
              String sql = getStringSQLsave();
            
            PreparedStatement statement = conn.prepareStatement(sql);
            ormIncluir(statement, objeto);
            
            
            

            stmt.execute();

            new UpdateEvent(conn).registerUpdate(idUser, config);

            return config.getDepartment().getIdDepartment();
      
        }finally{
            if((stmt != null) && !stmt.isClosed())
                    stmt.close();
            if((conn != null) && !conn.isClosed())
                    conn.close();
        }
    }
    
    
    protected abstract String getStringSQLfindByDepartment(int id);

    protected abstract void ormfindByDepartment(ResultSet result, Set<T> resultado) throws SQLException;

    public final Set<T> findByDepartment(int idDepartment) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        var resultado = new HashSet<T>();
        try{
            conn = ConnectionDAO.getInstance().getConnection();       

            String sql = getStringSQLfindByDepartment();

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            ormfindByDepartment(result, resultado);
            if(rs.next()){
                    return this.loadObject(rs);
            }else{
                    return null;
            }
        }finally{
        if((rs != null) && !rs.isClosed())
                rs.close();
        if((stmt != null) && !stmt.isClosed())
                stmt.close();
        if((conn != null) && !conn.isClosed())
                conn.close();
        }
    }
    
}
