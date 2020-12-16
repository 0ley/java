/*
	作者：凌涛
	作用：可连接数据库进行，查询、更新等操作
 */


package registrationinformationmanagement;
//package 运动员报名信息管理;
import java.sql.*; // Needed for JDBC classes
import java.util.ArrayList;


public class Access_AthleteDB {
	private static Connection con;
	private static Statement sta;
	private static ResultSet rs;
	
	//连接数据库
	public static void Connect() {
		try {
			// 加载注册相应驱动
			Class.forName(DBVars.DRIVER_NAME);

			// 创建连接
			con = DriverManager.getConnection(DBVars.URL,DBVars.USER,DBVars.PASSWORD);

			// 显示结果
			System.out.println("数据库连接成功");
		} 
        catch (ClassNotFoundException e) {
			System.out.println("加载驱动出错：" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("连接数据库出错：" + e.getMessage());
		}
	}

	// 查询abcTable, 并将查询的结果以ArrayList类型返回
	@SuppressWarnings("null")

 static ArrayList<AthleteScore> getRecordsAthlete(String sql) {
		ArrayList<AthleteScore> recordsList = new ArrayList<AthleteScore>();
		
		try {
			int i = 0;
			
			// 创建statement对象
			sta = con.createStatement();
			Statement sta2 = con.createStatement();

			// 把SELECT语句传给DBMS,执行查询操作
			rs = sta.executeQuery(sql);

			while (rs.next()) {
				// 根据字段名称获得各个字段的值
				String str = rs.getString(2);

				String sql2 = "select * from athletes where student_id ='" + str+ "'";
				ResultSet rs2 = sta2.executeQuery(sql2);
				while (rs2.next()) {
					i++;
					AthleteScore abcObj = new AthleteScore(rs2.getString(1),rs2.getString(2),/*Sex.valueOf*/(rs2.getString(3)),rs2.getString(4),rs2.getString(5),i + "",rs.getString(3),rs.getString(4));
					recordsList.add(abcObj);
					break;
				}
				
			}
			System.out.println(i);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recordsList;
	}

	public static ArrayList<AthleteScore> getRecordsAthleteMany(String sql,String sex,String academy,String items,String name) {
		ArrayList<AthleteScore> recordsList = new ArrayList<AthleteScore>();

		try {
			int i = 0;

			// 创建statement对象
			sta = con.createStatement();
			Statement sta2 = con.createStatement();

			// 把SELECT语句传给DBMS,执行查询操作
			String sqlScore = sql;
			if(!items.equals("all_item"))
				sqlScore += " where (sports_item ='" + items+ "')" ;
			sqlScore+=" ORDER  BY score ASC";
			
			
//			System.out.println(sqlScore);//用来检测我的数据库语句是否正常
			
			rs = sta.executeQuery(sqlScore);
			
			while (rs.next()) {
				// 根据字段名称获得各个字段的值
				String str = rs.getString(2);
				
				String sql2 = "select * from athletes where (student_id ='" + str+ "')";
				if(!sex.equals("allSex")) 	sql2 +=" and (sex = '" + sex +"')";
				if(!academy.equals("all_school"))	sql2 +=" and (academy = '" + academy +"')";
				if(!name.equals(""))	sql2 += " and (name = '" + name +"')";
				
//				System.out.println(sql2);//用来检测我的数据库语句是否正常
				ResultSet rs2 = sta2.executeQuery(sql2);
				while (rs2.next()) {
					i++;
					AthleteScore abcObj = new AthleteScore(rs2.getString(2),rs2.getString(3),/*Sex.valueOf*/(rs2.getString(4)),rs2.getString(5),rs2.getString(6),i + "",rs.getString(3),rs.getString(4));
					recordsList.add(abcObj);
					break;
				}

			}
			System.out.println(i);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recordsList;
	}

	public static ArrayList<AcademyAdmin> getRecordsAcademyAdmin(String sql){
		ArrayList<AcademyAdmin> recordsList = new ArrayList<AcademyAdmin>();
		try {
			// 创建statement对象
			sta = con.createStatement();

			// 把SELECT语句传给DBMS,执行查询操作
			rs = sta.executeQuery(sql);
			
			while (rs.next()) {
				// 根据字段名称获得各个字段的值
				AcademyAdmin AcademyAdminObj = new AcademyAdmin(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6));
				recordsList.add(AcademyAdminObj);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recordsList;
	}
	
	
	
	//查询
	public static void select(String sql) {
		try {
 			sta = con.createStatement();

 			// 执行查询操作
 			rs = sta.executeQuery(sql);

 			
 			// 显示结果
 			while(rs.next()){
				System.out.print(rs.getInt(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getString(3)+"\t");
				System.out.print(rs.getString(4)+"\t");
				System.out.println(rs.getString(5));
 			}
 		}
 		catch (SQLException e) {
 			System.out.println("查询出错：" + e.getMessage());
 		}
	}
	
	//该方法根据提供的增删改查操作，由传过来的SQL语句决定什么操作
	public static void operateDB(String sql) {
		// 存放与数据库操作相关的记录数
		int numOfRecords; 
		try {
			// 创建statement对象
			sta = con.createStatement();
			
			// 执行操作
			numOfRecords = sta.executeUpdate(sql);

			// 显示结果
			System.out.println("本次操作受影响的记录数为： " + numOfRecords);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	//删除
	public static void delete(String deleteSql) {
		try {
			System.out.println(deleteSql);
			sta=con.createStatement();
			sta.executeUpdate(deleteSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//添加
	public static void add(String addSql) {
		try {
			System.out.println(addSql);
			sta=con.createStatement();
			sta.executeUpdate(addSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//修改
	public static void fix(String fixSql) {
		try {
			System.out.println(fixSql);
			sta=con.createStatement();
			sta.executeUpdate(fixSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 查询数据库，并返回结果集
	public static ResultSet getResultSet(String sql){
		try {
			// 创建statement对象(第二个参数非常重要，不然无法通过结果集更新数据库)
			sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			// 把SELECT语句传给DBMS,执行查询操作
			rs = sta.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return rs;
	  } 
	
	//关闭数据库
	public static void closeConnection() {
		// 如果连接不为空，关闭连接对象
		if (con != null) {
			try {
				// 如果语句对象不为空，关闭语句对象
				if (sta != null) {
					// 如果结果集不为空，关闭结果集对象
					if (rs != null) {
						rs.close();
					}
					sta.close();
				}
				con.close();
				System.out.println("数据库连接关闭");
			} catch (SQLException e) {
				System.out.println("数据库关闭出错：" + e.getMessage());
			}
		}
	}




	
	
}
