

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
			try{
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
			} catch(UsingIteratorPastEndException e){
				System.err.print(e.getMessage());
			}
			
		}
		return longestLength;
	}
	
	public static int lengthLongestNDCSS2(Jumble j){
		int longestLength = 0;
		int currentLength = 0;
		int currentElem;
		int nextElem;
		JumbleIt jumbleIterator = new JumbleIt(j);
		
		try{
			currentElem = jumbleIterator.next();
			currentLength++;
			longestLength++;
			while(true){
				try{
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
					continue;
				} catch(UsingIteratorPastEndException e){
					break;
				}
			}
			if(currentLength > longestLength){
				longestLength = currentLength;
			}
		} catch(UsingIteratorPastEndException e){
			//Not sure if anything needs to go here
		}
		
	return longestLength;
	}
}