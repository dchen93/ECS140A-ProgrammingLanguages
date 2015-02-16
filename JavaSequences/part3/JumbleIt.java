

public class JumbleIt implements SeqIt{
	private Jumble sequence_;
	private int curElem_;
	
	public JumbleIt(Jumble s){
		this.sequence_ = s;
		this.curElem_ = 0;
	}
	
	public boolean hasNext(){
		if(curElem_ < sequence_.values_.length){
			return true;
		} else{
			return false;
		}
	}
	public int next(){
		if(curElem_ >= sequence_.values_.length){
			System.err.println("JumbleIt called past end");
			System.exit(1);
		} 
		int temp = sequence_.values_[curElem_];
		curElem_++;
		return temp;
	}
	
}