
import java.util.Random;


class Cat {
	String name;
	String type;
	int intimacy=0;

	Cat(String name,String type){
		this.name=name;
		this.type=type;
	}
	Cat(String name,String type,int intimacy){
		this(name,type);
		this.intimacy=intimacy;
	}

	String showStatus(){
		return String.format("%s[%s](%d)",this.name,this.type,this.intimacy);
	}

	void play(){
		System.out.println(this.name+"と遊んだ");
		System.out.println("・・・");
		System.out.println(this.name+"の親密度がアップした");
		this.intimacy++;
	}

	void playToy(String toys){
		System.out.println(this.name+"と"+toys+"で遊んだ");
		System.out.println("・・・");
		System.out.println(this.name+"の親密度がアップした");
		this.intimacy++;
		this.intimacy++;
	}

	void stroll(Random rand){
		String[] GIFT={"リボン","花","羽"};
		System.out.println(this.name+"は散歩にいった");
		System.out.println("・・・");
		System.out.println("夕方に帰ってきた。お帰り！");
		if(this.intimacy>5){
			String gift=GIFT[rand.nextInt(GIFT.length)];
			System.out.println("おや、なにか咥えてる？");
			System.out.println("・・・");
			System.out.println("お土産に"+gift+"ををもらった");
		}
	}
	void favo(){
		this.intimacy++;

	}

	String toCSV(){
		return String.format("%s,%s,%d",this.name,this.type,this.intimacy);
	}

}
