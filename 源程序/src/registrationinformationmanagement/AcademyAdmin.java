/*
	作者：熊志豪
	作用：系统管理员管理学院管理员时，新建学院管理员对象用到的类
*/

package registrationinformationmanagement;

public class AcademyAdmin {
	//学院管理员属性
	private  String userName;
	private  String password;
	private  String telephone;
	private  String academy;	
	//private  String career;
	
	public  AcademyAdmin(String userName,String password,String telephone,String academy) {
		super();
		this.userName=userName;
		this.password=password;
		this.telephone=telephone;
		this.academy=academy;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}
	
	
}
