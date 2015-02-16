/* The JumbleIt class is the iterator for the Jumble sequence
 * 
 */

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
	public int next() throws UsingIteratorPastEndException{
		if(curElem_ >= sequence_.values_.length){
			throw new UsingIteratorPastEndException("= caught UsingIteratorPastEndException from JumbleIt");
		} 
		int temp = sequence_.values_[curElem_];
		curElem_++;
		return temp;
	}
	
}