package com.diebold.poc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JavaDbMain3 {

	public static void main(String[] args) {
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		String url = "jdbc:derby:ng_poc_db;territory=pt_BR;create=true";

		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url);
			System.out.println("Conexão estabelecida...");
		} catch (Throwable e) {
			e.printStackTrace();
		}

		try {
			String sql = "SELECT a.id_tabela2, a.de_tabela2, a.id_tabela1, b.de_tabela1 FROM tabela2 a "
					+ "INNER JOIN tabela1 b ON (a.id_tabela1 = b.id_tabela1) "
					+ "ORDER BY a.id_tabela2, a.id_tabela1 ASC";

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				System.out.println(rs.getInt("id_tabela2") + " - " + rs.getString("de_tabela2") + " | " + rs.getInt("id_tabela1") + " - " + rs.getString("de_tabela1"));
			}

			rs.close();
			rs = null;

			st.close();
			st = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("Fechando conexão...");

			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
