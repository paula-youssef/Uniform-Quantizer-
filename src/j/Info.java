package j;

public class Info {
	
	public double mid,start,end;
	public int name;
	public Info(){
		
	}
	public Info(double s ,double e, int n){
		start = s ;
		end = e ;
		name = n;
		
	}
	
	//public void range(){}
	
	public void mid(){
		mid = (( start + end ) / 2 );
	}

}
