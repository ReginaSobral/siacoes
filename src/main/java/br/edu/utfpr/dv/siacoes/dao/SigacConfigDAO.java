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

	
	/*public SigacConfig findByDepartment(int idDepartment) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = ConnectionDAO.getInstance().getConnection();
			stmt = conn.prepareStatement("SELECT * FROM sigacconfig WHERE idDepartment = ?");
		
			stmt.setInt(1, idDepartment);
			
			rs = stmt.executeQuery();
			
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
	
	public int save(int idUser, SigacConfig config) throws SQLException{
		boolean insert = (this.findByDepartment(config.getDepartment().getIdDepartment()) == null);
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try{
			conn = ConnectionDAO.getInstance().getConnection();
			
			if(insert){
				stmt = conn.prepareStatement("INSERT INTO sigacconfig(minimumScore, maxfilesize, idDepartment) VALUES(?, ?, ?)");
			}else{
				stmt = conn.prepareStatement("UPDATE sigacconfig SET minimumScore=?, maxfilesize=? WHERE idDepartment=?");
			}
			
			stmt.setDouble(1, config.getMinimumScore());
			stmt.setInt(2, config.getMaxFileSize());
			stmt.setInt(3, config.getDepartment().getIdDepartment());
			
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
	
	private SigacConfig loadObject(ResultSet rs) throws SQLException{
	//	SigacConfig config = new SigacConfig();
		
		config.getDepartment().setIdDepartment(rs.getInt("idDepartment"));
		config.setMinimumScore(rs.getDouble("minimumScore"));
		config.setMaxFileSize(rs.getInt("maxfilesize"));
		
		return config;
	}
*/

  

}
