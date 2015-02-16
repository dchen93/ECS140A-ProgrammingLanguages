

public class Constant extends Seq{
	protected int num_;
	protected int value_;
	
	public Constant(int num, int value){
		if(num == 0){
			this.num_ = 0;
			this.value_ = 0;
		} else{
			this.num_ = num;
			this.value_ = value;
		}
	}
	
	public String toString(){
		return "[ "+num_+" : "+value_+" ]";
	}
}