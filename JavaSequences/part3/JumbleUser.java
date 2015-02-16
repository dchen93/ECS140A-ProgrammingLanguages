

public class JumbleUser{
	
	public static int lengthLongestNDCSS1(Jumble j){
		int longestLength = 0;
		int currentLength = 0;
		int currentElem;
		int nextElem;
		JumbleIt jumbleIterator = new JumbleIt(j);
		
		if(!jumbleIterator.hasNext()){
			return 0;
		} else{
			currentElem = jumbleIterator.next();
			currentLength++;
			longestLength++;
			while(jumbleIterator.hasNext()){
				nextElem = jumbleIterator.next();
				if(currentElem <= nextElem){
					currentLength++;
					if(currentLength > longestLength){
						longestLength = currentLength;
					}
				} else{
					currentLength = 1;
				}
				currentElem = nextElem;
			}
			if(currentLength > longestLength){
				longestLength = currentLength;
			}
		}
		return longestLength;
	}
}