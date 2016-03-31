package com.diebold.poc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JavaDbMain {

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
			String script1 = "CREATE TABLE tabela1 (id_tabela1 INT NOT NULL, de_tabela1 VARCHAR(30), CONSTRAINT tabela1_pk PRIMARY KEY (id_tabela1))";

			Statement st = conn.createStatement();
			st.execute(script1);

			st.close();
			st = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			String script2 = "CREATE TABLE tabela2 (id_tabela2 INT NOT NULL, de_tabela2 VARCHAR(30), id_tabela1 INT NOT NULL, CONSTRAINT tabela2_pk PRIMARY KEY (id_tabela2), CONSTRAINT tabela1_fk FOREIGN KEY (id_tabela1) REFERENCES tabela1 (id_tabela1))";

			Statement st = conn.createStatement();
			st.execute(script2);

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
