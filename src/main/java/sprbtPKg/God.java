package sprbtPKg;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class God {

	public static DriverManagerDataSource getConnDs() {
	 
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/mysql?allowMultiQueries=true"; // 创建一个表示数据库路径的字符串
			String username = "root"; // 创建一个表示数据库用户名的字符串
			String password = ""; // 创建一个表示数据库密码的字符串
			DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
			return dataSource;
		}
}
