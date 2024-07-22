package sqlzhuru;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.naming.NameNotFoundException;

public class SQLzhuru {
	public static void sqlzhuru(){
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String URL="jdbc:mysql://localhost:3306/xuexi";
			String name="root";
			String pwd="YES";
			conn=DriverManager.getConnection(URL, name, pwd);
			stmt=conn.createStatement();
			Scanner in=new Scanner(System.in);
			System.out.println("请输入用户名：");
			String uname=in.nextLine();
			System.out.println("请输入密码：");
			String upwd=in.nextLine();
//			System.out.println(uname);
//			System.out.println(upwd);
//			String sql="select * from login";
			int  count=-1;
     		String sql="select count(*) from login where uname='"+uname+"' and upwd='"+upwd+"'";
//     		String sql ="SELECT count(*) FROM `login` where uname='ou ' or 1=1 -- 'and upwd='yes'" ;
			rs=stmt.executeQuery(sql);
//     		stmt.executeQuery(sql);
//			while(rs.next()) {
//				System.out.print(rs.getString("uname")+"    ");
//				System.out.println(rs.getString("upwd"));
//			}
     		if(rs.next()) {
     			count=rs.getInt(1);
     		}
			if(count>0) {
				System.out.println("登录成功！");
			}else {
				System.out.println("登陆失败！");
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
	    }catch(SQLException e) {
	    	e.printStackTrace();
	    }finally {
	    	if(rs!=null) {
	    		try { rs.close();
	    		}catch(SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	if(stmt!=null) { 
	    		try{stmt.close();
	    		}catch(SQLException e) {
	    			e.printStackTrace();
	    		}
	    		}
	    	if(conn!=null) {
	    		try {conn.close();
	    		}catch(SQLException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    		
	    }
	}
	public static void main(String[] args) {
		sqlzhuru();
	}
}
