/*
	作者: 黄译田
	作用：用来显示系统管理员和学院管理员的个人系统，并且支持修改
*/


package registrationinformationmanagement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//管理管注册面板
class Information extends Login{
    public void start(Stage stage2) throws Exception{
        //*****舞台*****
        Scene scene = new Scene(getInformationPane(),700,500);
        stage2.setTitle("个人信息修改");
        stage2.setScene(scene);
        stage2.show();
    }
    
    private int systemORacademy;
	private String login_user_id, login_user_telephone, login_user_career, login_username;
	public String login_user_academy;
	public Information(int systemORacademy,String login_user_id,String login_user_telephone,String login_user_career,String login_username)
	{
		this.systemORacademy = systemORacademy;
		this.login_user_id = login_user_id;
		this.login_user_telephone = login_user_telephone;
		this.login_user_career = login_user_career;
		this.login_username = login_username;
	}
    public Pane getInformationPane() {

        //测试语句
        System.out.print("个人信息修改页面当前用户id：");
        System.out.print(login_user_id+"\n");

        //*****界面总面板*****
        StackPane pane_information = new StackPane();
        VBox vpane_information = new VBox(20);

        //背景图片,颜色的css
//        pane_information.setStyle("-fx-background-image:url('images/BackgroundPhoto.jpg');-fx-background-repeat:no-repeat;-fx-background-size:100% 100%;-fx--moz-background-size:100% 100%;");
        vpane_information.setStyle("-fx-background-color:#E6E6FA;-fx-background-insets:0 80 0 80;");

        //***vPane 由多个水平面板组成***

        //*标题 第1行pane*
        Label label_general = new Label("个人信息修改");
        label_general.setFont(Font.font("Arial", FontWeight.BOLD,FontPosture.REGULAR,20));

        //*显示id 第2行pane*
        Text text_id = new Text("  ID  : ");
        text_id.setFont(Font.font("Arial", FontWeight.NORMAL,FontPosture.REGULAR,14));
        TextField tf_id = new TextField();
        tf_id.setText(login_user_id);//父类变量，当前用户id
        tf_id.setEditable(false);//不可更改
        tf_id.setPrefSize(150,30);//文本框
        HBox hPane_id = new HBox(15);
        hPane_id.getChildren().addAll(text_id ,tf_id);
        hPane_id.setPadding(new Insets(0,20,0,20));
        hPane_id.setAlignment(Pos.CENTER);//水平面板

        //*显示career（不可更改） 第3行pane*
        Text text_career = new Text("身份 : ");
		text_career.setFont(Font.font("Arial", FontWeight.NORMAL,FontPosture.REGULAR,14));
		TextField tf_career = new TextField();
		tf_career.setText(login_user_career);//父类变量，当前用户telephone
		tf_career.setEditable(false);//不可更改
		tf_career.setPrefSize(150,30);//文本框
		HBox hPane_career = new HBox(15);		
		hPane_career.getChildren().addAll(text_career ,tf_career);
		hPane_career.setPadding(new Insets(0,20,0,20));
		hPane_career.setAlignment(Pos.CENTER);//水平面板	

        //*显示telephone 第4行pane*
        Text text_telephone = new Text("电话 : ");
		text_telephone.setFont(Font.font("Arial", FontWeight.NORMAL,FontPosture.REGULAR,14));
		TextField tf_newTelephone = new TextField();
		tf_newTelephone.setText(login_user_telephone);//父类变量，当前用户telephone

		tf_newTelephone.setPrefSize(150,30);//文本框
		HBox hPane_telephone = new HBox(15);		
		hPane_telephone.getChildren().addAll(text_telephone ,tf_newTelephone);
		hPane_telephone.setPadding(new Insets(0,20,0,20));
		hPane_telephone.setAlignment(Pos.CENTER);//水平面板

        //*输入新账号 第5行pane*
		Text text_newUsername = new Text("账号 : ");
		text_newUsername.setFont(Font.font("Arial", FontWeight.NORMAL,FontPosture.REGULAR,14));
		TextField tf_newUsername = new TextField();
		tf_newUsername.setPrefSize(150,30);//文本框
		tf_newUsername.setText(login_username);
		HBox hPane_newUsername = new HBox(15);		
		hPane_newUsername.getChildren().addAll(text_newUsername,tf_newUsername);
		hPane_newUsername.setPadding(new Insets(0,20,0,20));
		hPane_newUsername.setAlignment(Pos.CENTER);//水平面板
		
		//*修改密码 第6行pane*
		Text text_newPassword = new Text("密码 : ");
		text_newPassword.setFont(Font.font("Arial", FontWeight.NORMAL,FontPosture.REGULAR,14));//文字
		PasswordField tf_newPassword = new PasswordField();
		tf_newPassword.setPrefSize(150,30);
		tf_newPassword.setPromptText("请输入密码");//密码框
		HBox hPane_newPassword = new HBox(15);		
		hPane_newPassword.getChildren().addAll(text_newPassword,tf_newPassword);
		hPane_newPassword.setPadding(new Insets(0,20,0,20));
		hPane_newPassword.setAlignment(Pos.CENTER);//水平面板

        //*重复新密码 第7行pane*
        Text text_repeat = new Text("重复 : ");
        text_repeat.setFont(Font.font("Arial", FontWeight.NORMAL,FontPosture.REGULAR,14));//文字
        PasswordField tf_repeat = new PasswordField();
        tf_repeat.setPrefSize(150,30);
        tf_repeat.setPromptText("请重新输入一遍密码");//密码框
        HBox hPane_repeat = new HBox(15);
        hPane_repeat.getChildren().addAll(text_repeat,tf_repeat);
        hPane_repeat.setPadding(new Insets(0,20,0,20));
        hPane_repeat.setAlignment(Pos.CENTER);//水平面板

        //*确认按钮 第8行pane*
        Button button_confirmR = new Button("确认修改");
        button_confirmR.setTextFill(Color.WHITE);
        button_confirmR.setPrefSize(150,30);
        button_confirmR.setStyle("-fx-base:#3394C6");//确认修改按钮
        HBox hPane_confirmR = new HBox(15);
        hPane_confirmR.getChildren().addAll(button_confirmR);
        hPane_confirmR.setPadding(new Insets(0,20,0,20));
        hPane_confirmR.setAlignment(Pos.CENTER);//水平面板

        //***所有pane添加到vPane***
        vpane_information.getChildren().addAll(label_general,hPane_id,hPane_telephone,hPane_career,hPane_newUsername,hPane_newPassword,hPane_repeat,hPane_confirmR);
        vpane_information.setAlignment(Pos.CENTER);
        vpane_information.setPadding(new Insets(20,20,20,20));

        //***vPane添加到stackPane***
        pane_information.getChildren().addAll(vpane_information);
        //********action********

        //确认注册：若两次输入的密码一致则将账号密码添加到数据库，否则报错
        button_confirmR.setOnAction(e->{

        	String newTelephone = tf_newTelephone.getText();
			String newUsername = tf_newUsername.getText();
			String newPassword = tf_newPassword.getText();
			
			String repeat = tf_repeat.getText();//记录三个文本框的内容

            if(newPassword.equals(repeat)&&!newPassword.equals(""))//若两次密码内容一致，添加到数据库
            {
                try//弹出成功提示
                {
                	if(systemORacademy == 0)
					{
						int a = sta.executeUpdate("UPDATE system_admin SET telephone ='"+newTelephone+ "', user_name='"+newUsername+"',password='"+newPassword+"' WHERE id='"+this.login_user_id+"'");
					}
					else if(systemORacademy == 1)
					{
						int a = sta.executeUpdate("UPDATE academy_admin SET telephone ='"+newTelephone+ "', user_name='"+newUsername+"',password='"+newPassword+"' WHERE id='"+this.login_user_id+"'");
					}
						Alert alert1 = new Alert(Alert.AlertType.INFORMATION,"修改成功！");
				    alert1.setTitle("修改成功");
				    alert1.show();
//                    stage2.hide();
                }
                catch(Exception a)
                {
                    System.out.println(a.getMessage());
                }
            }

            else //若两次密码内容不一致，弹出错误提示  并将密码框内容清空
            {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION,"两次输入的密码不一样或输入为空！\n请重新设置密码\n");
                alert2.setTitle("两次输入不一致");
                alert2.show();
                tf_newPassword.setText("");
                tf_repeat.setText("");
            }
        });
        return pane_information;
    }
}