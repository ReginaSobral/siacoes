package br.edu.utfpr.dv.siacoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.utfpr.dv.siacoes.log.UpdateEvent;
import br.edu.utfpr.dv.siacoes.model.SigacConfig;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.codehaus.groovy.ast.tools.GeneralUtils.stmt;

public class SigacConfigDAO implements INegocio<SigacConfig>{

    
      @Override
    public String getStringSQLfindByDepartment(int id) {
        stmt = "SELECT * FROM sigacconfig WHERE idDepartment = ?";	
	stmt.setInt(1, idDepartment);
        
        return stmt;
    }

    @Override
    public void getStringSQLloadObject() {
        SigacConfig config = new SigacConfig();
		
        config.getDepartment().setIdDepartment(rs.getInt("idDepartment"));
        config.setMinimumScore(rs.getDouble("minimumScore"));
        config.setMaxFileSize(rs.getInt("maxfilesize"));
    }

    @Override
    public Connection getStringSQLsave(Connection conn) {
        PreparedStatement stmt = null;
         boolean insert = (this.findByDepartment(config.getDepartment().getIdDepartment()) == null);
        
        if(insert){
            try {
                stmt = conn.prepareStatement("INSERT INTO sigacconfig(minimumScore, maxfilesize, idDepartment) VALUES(?, ?, ?)");
            } catch (SQLException ex) {
                Logger.getLogger(SigacConfigDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                stmt = conn.prepareStatement("UPDATE sigacconfig SET minimumScore=?, maxfilesize=? WHERE idDepartment=?");
            } catch (SQLException ex) {
                Logger.getLogger(SigacConfigDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        stmt.setDouble(1, config.getMinimumScore());
        stmt.setInt(2, config.getMaxFileSize());
        stmt.setInt(3, config.getDepartment().getIdDepartment());
			
        
      	return (Connection) stmt;
    }

}
