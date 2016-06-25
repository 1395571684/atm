package atm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
           //添加
	public int save(){
		Connection conn=null;
		Statement stat=null;
		int ret=0;
		try {
			//1.驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.连接
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr", "Oracle");
			//3.操作
			stat=conn.createStatement();
			//4.发送或返回
			ret=stat.executeUpdate("insert into t_user(id,username,password)values(1,'hh','123')");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return ret;
	}
        //删除
	public int delete(){
		Connection conn=null;
		Statement stat=null;
		int ret=0;
		try {
			//1.驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.连接
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr", "Oracle");
			//3.操作
			stat=conn.createStatement();
			//4.发送或返回
			ret=stat.executeUpdate("delete t_user where id=1");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return ret;
	}
        //更改
	public int update(){
		Connection conn=null;
		Statement stat=null;
		int ret=0;
		try {
			//1.驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.连接
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr", "Oracle");
			//3.操作
			stat=conn.createStatement();
			//4.发送或返回
			ret=stat.executeUpdate("update t_user set id=4 where id=2");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			try {
				stat.close();
				conn.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return ret;
	}
	public List<User> search(){
		Connection con=null;
		Statement stat=null;
		ResultSet rs=null;
		List<User>  userList = new ArrayList<User>();
		try {
			//1.驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2.连接
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr", "Oracle");
			stat=con.createStatement();
			rs=stat.executeQuery("select * from t_user");
			while(rs.next()){
				//把数据库的行导入user类
				User user=new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString(3));
				//把类加入到集合中
				userList.add(user);
				}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			try {
				rs.close();
				stat.close();
				con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return userList;
	}
	public static void main(String[] args) {
		UserDao userDao=new UserDao();
		int ret=userDao.save();
		userDao.delete();
		userDao.update();
		List<User> list=userDao.search();
		for (User user : list) {
			System.out.println(user);
		}		
	}
}
