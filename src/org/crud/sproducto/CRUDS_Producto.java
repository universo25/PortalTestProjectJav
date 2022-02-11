package org.crud.sproducto;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDS_Producto {
	
	static Connection connection;
	static String driver ="oracle.jdbc.driver.OracleDriver";
	static String URL ="jdbc:oracle:thin:@localhost:1521:orcl";
	
	public static void connectionOracleDataBase()throws IOException, SQLException{
		try {
			Class.forName(driver).newInstance();
			System.out.println("CARGO DRIVER CORRECTAMENTE");
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			
		}
		try {
			connection = DriverManager.getConnection(URL,"System","Kali96ger");
			System.out.println("CONEXION EXITOSA A ORACLE DATA BASE");
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	public static void agregarS_Producto(int id, String name,String shortDesc,int longTextId,
			int imageId,float suggestedWhlsl,String whlsl)throws IOException,SQLException{
		
		try {
			connectionOracleDataBase();
			//CONSULTA
			String sql = "INSERT INTO S_PRODUCT(ID, NAME, SHORT_DESC, LONGTEXT_ID, IMAGE_ID, SUGGESTED_WHLSL_PRICE, WHLSL_UNITS) "
					+ "VALUES (?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, shortDesc);
			ps.setInt(4, longTextId);
			ps.setInt(5, imageId);
			ps.setFloat(6, suggestedWhlsl);
			ps.setString(7, whlsl);
			ps.executeQuery();
			ps.close();
			connection.close();
			System.out.println("INSERTO:" + id + ", " + name +", "+ shortDesc+", "+ 
					longTextId+", "+imageId+", "+suggestedWhlsl+", "+whlsl);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
public static void actualizarS_Producto(String name,String shortDesc,int longTextId,
		int imageId,float suggestedWhlsl,String whlsl, int id)throws IOException,SQLException{
		
		try {
			connectionOracleDataBase();
			//CONSULTA
			String sql = "UPDATE S_PRODUCT SET NAME = ?, SHORT_DESC = ?, LONGTEXT_ID = ?, IMAGE_ID = ?,"
					+ "SUGGESTED_WHLSL_PRICE = ?, WHLSL_UNITS = ? WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, shortDesc);
			ps.setInt(3, longTextId);
			ps.setInt(4, imageId);
			ps.setFloat(5, suggestedWhlsl);
			ps.setString(6, whlsl);
			ps.setInt(7, id);
			ps.executeQuery();
			ps.close();
			connection.close();
			System.out.println("ACTUALIZO: " + id + ", " + name + ", " + shortDesc + ", " + longTextId
					+ ", " + imageId + ", " + suggestedWhlsl + ", " + whlsl);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
public static void eliminarS_Producto(int id)throws IOException,SQLException{
	
	try {
		connectionOracleDataBase();
		//CONSULTA
		String sql = "DELETE FROM S_PRODUCT WHERE ID = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeQuery();
		ps.close();
		connection.close();
		System.out.println("Elimino:" + id);
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("Exception: " + e.getMessage());
	}
}

public static void consultaIndividualS_Producto(int id)throws IOException,SQLException{
	
	try {
		connectionOracleDataBase();
		//CONSULTA
		String sql = "SELECT * FROM S_PRODUCT WHERE ID = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rSet= ps.executeQuery();
		if(rSet.next()) {
			System.out.println(rSet.getInt("id") + ", " + rSet.getString("name") + ", " + rSet.getString("short_desc")+ ", " 
			+ rSet.getInt("longtext_id")+ ", "  +  rSet.getInt("image_id")+ ", "  + rSet.getFloat("suggested_whlsl_price")+ ", " 
			+ rSet.getString("whlsl_units"));
		}
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("Exception: " + e.getMessage());
	}
}

public static void consultaGeneralS_Producto()throws IOException,SQLException{
	
	try {
		connectionOracleDataBase();
		//CONSULTA
		String sql = "SELECT * FROM S_PRODUCT";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rSet= ps.executeQuery();
		while(rSet.next()) {
			System.out.println(rSet.getInt("id") + ", " + rSet.getString("name") + ", " + rSet.getString("short_desc")+ ", " 
					+ rSet.getInt("longtext_id")+ ", "  +  rSet.getInt("image_id")+ ", "  + rSet.getFloat("suggested_whlsl_price")+ ", " 
					+ rSet.getString("whlsl_units"));
		}
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("Exception: " + e.getMessage());
	}
}

public static void invocaProcedimientoAlmacenado(int id, String name,String shortDesc,int longTextId,
		int imageId,float suggestedWhlsl,String whlsl)throws IOException,SQLException{
	
	try {
		connectionOracleDataBase();
		CallableStatement cs = connection.prepareCall("CALL producto(?,?,?,?,?,?,?)");
		cs.setInt(1, id);
		cs.setString(2, name);
		cs.setString(3, shortDesc);
		cs.setInt(4, longTextId);
		cs.setInt(5, imageId);
		cs.setFloat(6, suggestedWhlsl);
		cs.setString(7, whlsl);
		cs.execute();
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("Exception: " + e.getMessage());
	}
}

	public static void main(String[] agrs) throws IOException,SQLException {
		//connectionOracleDataBase();
		//agregarS_Producto(2, "SHORT", "DESCUENTO", 1, 1,500, "NULL");
		//actualizarS_Producto("Short", "No aplica", 1, 1, 300, "Null", 2);
		//eliminarS_Producto(2);;
		//consultaIndividualS_Producto(2);
		
		invocaProcedimientoAlmacenado(2, "camisa","DESCUENTO", 1, 1,500, "NULL");
		consultaGeneralS_Producto();
	}
}
