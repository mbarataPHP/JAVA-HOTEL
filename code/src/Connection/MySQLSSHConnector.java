package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MySQLSSHConnector {
	public  Session session;
	//connection_db
	public Connection connection_db() throws SQLException {
		Connection connection = null;
		int lport = 3306;
		String driverName = "com.mysql.jdbc.Driver";
		String db2Url = "jdbc:mysql://localhost:"+lport+"/hotel?autoReconnect=true&useSSL=false";

		String dbUsr = "root";
		String dbPwd = "";
		
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection = DriverManager.getConnection(db2Url, dbUsr, dbPwd);
		return connection;
	}
	
	
	public Connection connection_ssh_db() throws SQLException {
		
		Connection connection = null;
        this.session= null;
        
		String host = "192.168.0.111";
		String servUser = "g4";
		String servPwd = "1ALJ826HtE";
		int port = 22;
		
		int lport = 3306;

		String driverName = "com.mysql.jdbc.Driver";
		String db2Url = "jdbc:mysql://localhost:"+lport+"/lorem?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

		String dbUsr = "root";
		String dbPwd = "root";
		
		try {
			JSch jsch = new JSch();
			// Get SSH session
			session = jsch.getSession(servUser, host, port);
			session.setPassword(servPwd);
			java.util.Properties config = new java.util.Properties();
			// Never automatically add new host keys to the host file
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			// Connect to remote server
			session.connect();
			int assinged_port = session.setPortForwardingL(8745, "127.0.0.1", 3306);
			System.out.println("localhost:" + assinged_port + " -> " + "127.0.0.1" + ":" + 3306);
			// Connect to remote database
			//Class.forName(driverName);
			//connection = DriverManager.getConnection(db2Url, dbUsr, null);
			MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setServerName("127.0.0.1");
            dataSource.setPortNumber(8745);
            dataSource.setUser("root");
            dataSource.setAllowMultiQueries(true);

            dataSource.setPassword("root");
            dataSource.setDatabaseName("lorem");

            connection = dataSource.getConnection();
           
			
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return null;
	}

	public void CloseSSHConnection() {
	    if (session != null && session.isConnected()) {
	        System.out.println("Closing SSH Connection");
	        session.disconnect();
	    }
	}

}