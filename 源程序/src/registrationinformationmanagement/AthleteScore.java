/*
	作者：凌涛
	作用：继承Athlete类,运动员运动赛事的记录，可有多条记录.
*/



package registrationinformationmanagement;

public class AthleteScore extends Athlete{
	//AthleteScore类的属性
	private String rank;
	private String sportsItem;
	private String score; 
	
	//AthleteScore类的方法
	public AthleteScore(String studentId, String name, String sex, String academy, String telephone,String rank,String sportsItem,String score) {
		// TODO Auto-generated constructor stub
		super(studentId,name,sex,academy,telephone);
		this.rank = rank;
		this.sportsItem = sportsItem;
		this.score = score;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}

	public void setSportsItem(String sportsItem) {
		this.sportsItem = sportsItem;
	}
	
	public void setScore(String score) {
		this.score = score;
	}

	public String getRank() {
		return rank;
	}
	
	public String getSportsItem() {
		return sportsItem;
	}
	
	public String getScore() {
		return score;
	}
}
