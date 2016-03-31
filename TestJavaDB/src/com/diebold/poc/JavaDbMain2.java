package com.diebold.poc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JavaDbMain2 {

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
			String script1 = "INSERT INTO tabela1 (id_tabela1, de_tabela1) VALUES (1, 'TESTE 1')";

			Statement st = conn.createStatement();
			st.executeUpdate(script1);

			st.close();
			st = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			String script2 = "INSERT INTO tabela2 (id_tabela2, de_tabela2, id_tabela1) VALUES (2, 'TESTE 2', 1)";

			Statement st = conn.createStatement();
			st.executeUpdate(script2);

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
