import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CatApp {

	public static void main(String[] args) throws Exception{
		// TODO 自動生成されたメソッド・スタブ
		Random rand=new Random();
		Scanner sc=new Scanner(System.in);
		final String[] TYPES={"黒","白","三毛","茶トラ","ロシアン"};
		final String[] TOYS={"ねこじゃらし","ボール","ぬいぐるみ"};
		File file=new File("catlist.csv");
		//↓Catクラスで作った猫を入れる場所
		ArrayList<Cat> list;
		if(file.exists()){
			//続きから遊ぶ
			list=loadFile(file);
		}else{
			//初回
			list=new ArrayList<>();
		}
		//list=new ArrayList<>();
		System.out.println("＊＊猫集め＊＊");
		while(true){
			System.out.print("1.集める 2.遊ぶ 3.お散歩 4.おやつ 5.終了>>");
			int select=sc.nextInt();
			if(select==5){
				//終了
				System.out.println("＊＊結果＊＊");
				for(Cat c:list){
					System.out.println(c.showStatus());

				}
				System.out.println("おしまい。また遊んでね");
				saveFile(file,list);
				sc.close();
				return;
			}else
			if(select==1){
				//集める
				String type=TYPES[rand.nextInt(TYPES.length)];
				System.out.printf("%s猫を見つけた!%n",type);
				System.out.print("この猫に名前を付けてください");
				String name=sc.next();
				Cat cat=new Cat(name,type);
				list.add(cat);
				System.out.println(cat.name+"が仲間に加わった");
			}else
			if(select==2){
				//遊ぶ
				if(list.size()==0){
					System.out.println("まだ遊ぶ猫がいません。。。");
					continue;
				}
				System.out.print("1:おもちゃで遊ぶ 2:そのまま遊ぶ>>");
				int choice=sc.nextInt();
				for(int i=0;i<list.size();i++){
					System.out.printf("%d・・%s%n",i,list.get(i).showStatus());
				}
				System.out.print("どの猫と遊びますか？>>");
				int no=sc.nextInt();
				if(choice==1){
					String toys=TOYS[rand.nextInt(TOYS.length)];
					list.get(no).playToy(toys);
					sortCat(list);
				}else
				if(choice==2){
					list.get(no).play();
					sortCat(list);
				}

			}else
			if(select==3){
				//散歩
				if(list.size()==0){
					System.out.println("まだ猫がいません。。。");
					continue;
				}
				for(int i=0;i<list.size();i++){
					System.out.printf("%d・・%s%n",i,list.get(i).showStatus());
				}
				System.out.print("どの猫がお出かけしますか？>>");
				int no=sc.nextInt();
				list.get(no).stroll(rand);

			}else
			if(select==4){
				if(list.size()==0){
					System.out.println("まだ猫がいません。。。");
					continue;
				}
				System.out.println("みんなにおやつをあげた");
				System.out.println("・・・");
				System.out.println("お腹いっぱい！");
				System.out.println("親密度がアップした");
				for(int i=0;i<list.size();i++){
					list.get(i).favo();
				}
			}
		}
	}

	static void sortCat(ArrayList<Cat> list){
		for(int i=0;i<list.size();i++){
			for(int j=0;j<list.size();j++){
				if(list.get(i).intimacy>list.get(j).intimacy){
					//入れ替えのため一旦tempへ代入
					Cat temp=list.get(i);
					list.set(i,list.get(j));
					list.set(j, temp);
				}
			}
		}
	}

	static ArrayList<Cat> loadFile(File file) throws Exception{
		ArrayList<Cat> list=new ArrayList<>();
		FileInputStream fis=new FileInputStream(file);
		InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
		BufferedReader br=new BufferedReader(isr);

		String line;

		while((line=br.readLine())!=null){
			String[] values=line.split(",");
			String name=values[0];
			String type=values[1];
			int intimacy=Integer.parseInt(values[2]);
			Cat c=new Cat(name,type,intimacy);
			list.add(c);
		}
		br.close();
		return list;
	}

	static void saveFile(File file,ArrayList<Cat> list) throws Exception{
		FileOutputStream fos=new FileOutputStream(file);
		OutputStreamWriter osw=new OutputStreamWriter(fos,"UTF-8");
		BufferedWriter bw=new BufferedWriter(osw);

		for(Cat c:list){
			bw.write(c.toCSV());
			bw.newLine();

		}
		bw.close();
	}

}
