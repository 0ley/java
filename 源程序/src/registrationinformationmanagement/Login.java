/*
	作者:黄译田
	作用：用来显示登陆界面
*/


package registrationinformationmanagement;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.*;

public class Login extends Application{

    static Connection con;
	static Statement sta;
	static ResultSet rs,rs1,rs2;//数据库相关变量
	public int systemORacademy = 0;//判断当前用户是什么身份，系统管理员0，学院管理员1
	public String login_user_id;//父类的变量可以被子类访问，但方法内不能定义public之类的，只能在方法外定义作用域
	public String login_user_telephone;
	public String login_user_career;
	public String login_username;
	public String login_user_academy;
	private Stage stage;
	private Button button_confirm;
	private RadioButton rb_system ;
	private RadioButton rb_academy;
	private PasswordField tf_password;
	private TextField tf_username;
	//stage1
	public void start(Stage stage1) throws Exception {
		
		//********连接数据库********
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sports_meeting_system", "root","");
		    sta = con.createStatement();
		}
		//之前死活找不到的bug！忘了放mysql驱动！！！啊！！
		catch (ClassNotFoundException e) { 
			System.out.println("加载驱动出错：" + e.getMessage());
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//*****Login界面总面板*****
		StackPane pane_picture = new StackPane();//背景pane，放图片
		BorderPane borderPane_Login = new BorderPane();//叠在StackPane上面，放各个组件
		
		
		//放背景图，css语句 使图片跟随面板的大小变化而变化
		pane_picture.setStyle("-fx-background-image:url('images/BackgroundPhoto.jpg');-fx-background-repeat:no-repeat;-fx-background-size:100% 100%;-fx--moz-background-size:100% 100%;");
		borderPane_Login.setStyle("-fx-background-color:#E6E6FA;-fx-background-insets:50 50 20 50;");
		
		//***borderPane.top 由一个水平面板组成***
		ImageView icon_login = new ImageView(new Image("images/icon_login.png"));//login图标
		icon_login.setFitHeight(60);
		icon_login.setFitWidth(60);//设置图片大小
		Label label_loginInterface = new Label("登     录",icon_login);
		label_loginInterface.setContentDisplay(ContentDisplay.TOP);
		label_loginInterface.setFont(Font.font("Arial", FontWeight.BOLD,FontPosture.REGULAR,20));//文字：登陆界面
		HBox icon_hPane = new HBox(15);		
		icon_hPane.getChildren().addAll(label_loginInterface);
		icon_hPane.setPadding(new Insets(40,20,0,20));
		icon_hPane.setAlignment(Pos.CENTER);//放icon和文字的hPane
		borderPane_Login.setTop(icon_hPane);//如上的hPane放在border.top
		
		//***borderPane.center 由一个vPane组成***
		
		//*账号输入 第1行pane*
		Text text_username = new Text("账号 : ");
		text_username.setFont(Font.font("Arial", FontWeight.NORMAL,FontPosture.REGULAR,14));
		tf_username = new TextField();
		tf_username.setPrefSize(150,30);//账号输入的文字和文本框
		
		HBox hPane_username = new HBox(15);		
		hPane_username.getChildren().addAll(text_username,tf_username);
		hPane_username.setPadding(new Insets(0,20,0,20));
		hPane_username.setAlignment(Pos.CENTER);//水平面板
		
		//*密码输入  第2行pane*
		Text text_password = new Text("密码 : ");
		text_password.setFont(Font.font("Arial", FontWeight.NORMAL,FontPosture.REGULAR,14));
		tf_password = new PasswordField();
		tf_password.setPrefSize(150,30);
		tf_password.setPromptText("初始密码123456");//密码输入的文字和密码框
		HBox hPane_password = new HBox(15);		
		hPane_password.getChildren().addAll(text_password,tf_password);
		hPane_password.setPadding(new Insets(0,20,0,20));
		hPane_password.setAlignment(Pos.CENTER);//水平面板
		
		//*选择管理员权限  第3行pane* isSelected()仿法
		ToggleGroup group = new ToggleGroup();//单选按钮的一个组
		rb_system = new RadioButton("系统管理员");
		rb_academy = new RadioButton("学院管理员");
		rb_system.setToggleGroup(group);
		rb_academy.setToggleGroup(group);
		HBox hPane_admin = new HBox(15);
		hPane_admin.getChildren().addAll(rb_system, rb_academy);
		hPane_admin.setPadding(new Insets(0,20,0,20));
		hPane_admin.setAlignment(Pos.CENTER);//水平面板
		
		//*确认登录  第4行pane*
		button_confirm = new Button("登     录");
		button_confirm.setTextFill(Color.WHITE);
		button_confirm.setPrefSize(150,35);
		button_confirm.setStyle("-fx-base:#3394C6");//登录按钮
		HBox hPane_confirm = new HBox(15);
		hPane_confirm.getChildren().addAll(button_confirm);
		hPane_confirm.setPadding(new Insets(0,20,0,20));
		hPane_confirm.setAlignment(Pos.CENTER);//水平面板
		
		//**4行pane添加到vPane**
		VBox vPane_center = new VBox(20);	
		vPane_center.getChildren().addAll(hPane_username,hPane_password,hPane_admin,hPane_confirm);
		vPane_center.setPadding(new Insets(0,20,40,20));
		vPane_center.setAlignment(Pos.CENTER);
		
		//***vPane添加到border.center***
		borderPane_Login.setCenter(vPane_center);
		borderPane_Login.setPadding(new Insets(40,0,10,0));
		
		//***borderPane添加到stackPane***
		pane_picture.getChildren().addAll(borderPane_Login);
		
		
		//********action********
		rb_system.setOnKeyPressed(e->{
			if(e.getCode() == KeyCode.ENTER) {
				if(rb_system.selectedProperty().getValue())
					rb_system.selectedProperty().setValue(false);
				else
					rb_system.selectedProperty().setValue(true);	
			}
		});
		rb_academy.setOnKeyPressed(e->{
			if(e.getCode() == KeyCode.ENTER) {
				if(rb_academy.selectedProperty().getValue())
					rb_academy.selectedProperty().setValue(false);
				else
					rb_academy.selectedProperty().setValue(true);	
			}
		});
		//确认登录：判断输入的账号是否已注册、密码是否正确，正确则跳转到功能页面，否则报错
		button_confirm.setOnAction(e->loginClick());
		button_confirm.setOnKeyPressed(e->loginClick());
		
		//*****舞台*****
		Scene scene = new Scene(pane_picture,667,375);
		stage = stage1;
		stage.setTitle("运动会成绩管理系统");
		stage.setScene(scene);
		stage.show();
	}
	public void loginClick() {
		{
			int f = 0;//f是判断是否能成功登录的条件，f=1表示账号密码正确
			try
			{
				if(rb_system.isSelected())
				{
					rs = sta.executeQuery("SELECT user_name,password FROM system_admin");//系统管理员的账号密码
					systemORacademy = 0; //是系统管理员
				}
				else if(rb_academy.isSelected())
				{
					rs = sta.executeQuery("SELECT user_name,password FROM academy_admin");//表示所有管理端的账号密码
					systemORacademy = 1; //是学院管理员
				}	
				while(rs.next())
				{
					if(tf_username.getText().equals(rs.getString("user_name"))&&tf_password.getText().equals(rs.getString("password")))
				    {
				        f = 1;
				        //记录当前登录的id
				        login_username = rs.getString("user_name"); //获取user_name列的值
				        
				        //获取当前登录用户的所有其他信息
						//ResultSet是一个结果集，要想读出来，必须要rs.next()方法才行
				        if(systemORacademy == 0) //系统管理员
				        {
							rs = sta.executeQuery("SELECT id,telephone,career FROM system_admin WHERE user_name='"+login_username+"' ");
							while(rs.next())
							{
					 
					        	login_user_id = rs.getString("id");
					        	login_user_telephone = rs.getString("telephone");
					        	login_user_career = rs.getString("career");
							}
				        }
						else if(systemORacademy == 1) //学院管理员
				        {
							rs = sta.executeQuery("SELECT id,telephone,career,academy FROM academy_admin WHERE user_name='"+login_username+"' ");
							while(rs.next())
							{
					        	
					        	login_user_id = rs.getString("id");
					        	login_user_telephone = rs.getString("telephone");
					        	login_user_career = rs.getString("career");
					        	login_user_academy = rs.getString("academy");
								
							}
				        }
				        //不能统一在这里调用login_user_id = rs.getString("id");login_user_career = rs2.getString("career");
				        //必须每条紧跟在rs2 = sta.executeQuery后面！
				        
				        break;
				    }
			    }
				//测试语句
				System.out.print("login页面当前用户id：");
				System.out.print(login_user_id+"\n");
				
				
				if(f==1)//满足条件则直接跳转到功能页面
				{
//					跳转到个人信息修改页面
					if(systemORacademy == 0) {
						AthleteApplicationForm athleteApplicationForm= new AthleteApplicationForm(this.systemORacademy,this.login_user_id,this.login_user_telephone,this.login_user_career,this.login_username);
						athleteApplicationForm.start(new Stage());
					}
					if(systemORacademy == 1) {
						CompetitionEnquiry athleteApplicationForm= new CompetitionEnquiry(this.systemORacademy,this.login_user_id,this.login_user_telephone,this.login_user_career,this.login_username,this.login_user_academy);
						athleteApplicationForm.start(new Stage());
					}
//					Information openInformation = new Information(new TextField(this.login_user_id));
//					openInformation.start(new Stage());
					stage.hide(); //隐藏login页面
				}
				else //不满足条件则弹出提示框
				{
					Alert alert = new Alert(Alert.AlertType.INFORMATION,"该用户名不存在或"
							+ "密码错误！\n请重新登录\n");
					alert.setTitle("登录错误提示");
					alert.show();
					//将输入框清空
					tf_password.setText("");
					tf_username.setText("");
				}
			}
			catch(Exception a)
			{
				System.out.println(a.getMessage());	
			}
		}
	}
	
    public static void main(String[] args) throws SQLException
    {
		launch(args);
		try
		{    //关闭资源
		    rs.close();  //关闭ResultSet对象
		    sta.close();  //关闭Statement对象
		    con.close();  //关闭Connection对象
		}
		catch (Exception c) 
		{ 
			System.out.print(c.getMessage()); 
		}
	}
}
