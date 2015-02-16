public class Plus{

	////Here is the master plus fuction, all of the other commented out functions will no longer be used
    public static Seq plus(Seq a, Seq b)
    {
        boolean isDelta = true;
        int currentElem1 = 0;
        int currentElem2 = 0;
        int length = 0;
        int currArrayElement = 0;
        int lengtha = 0;
        int lengthb = 0;
        int delta = 999;

        SeqIt seqIterator1 = a.createSeqIt();
        SeqIt seqIterator2 = b.createSeqIt();

        //iterators used to find length
        SeqIt seqIterator3 = a.createSeqIt();
        SeqIt seqIterator4 = b.createSeqIt();

        if(seqIterator3.hasNext())
        {
            try
            {
                 while(seqIterator3.hasNext())
                 {
                    lengtha += 1;
                    seqIterator3.next();
                    
                 }
            }
            catch(UsingIteratorPastEndException e)
            {
                System.err.print(e.getMessage());
            }

        }
        if(seqIterator4.hasNext())
        {
            try
            {
                 while(seqIterator4.hasNext())
                 {
                    lengthb += 1;
                    seqIterator4.next();
                    
                 }
            }
            catch(UsingIteratorPastEndException e)
            {
                System.err.print(e.getMessage());
            }

        }
        if(lengtha<lengthb)
        {
            length = lengtha;
        }
        else
        {
            length = lengthb;
        }
        int[] seq = new int[length];


        if(!seqIterator1.hasNext()||!seqIterator2.hasNext()){
            return new Constant(0,0);
        } 
        if(!seqIterator1.hasNext()&&!seqIterator2.hasNext()){
            return new Constant(0,0);
        }
        else
        {
            //here we want to add both sequences together so we later can compare them as a whole in order to simplify the sequence. 
            try
            {
                while(seqIterator1.hasNext() && seqIterator2.hasNext())
                {
                    currentElem1 = seqIterator1.next();
                    currentElem2 = seqIterator2.next();
                    seq[currArrayElement] = currentElem1+currentElem2;
                    currArrayElement = currArrayElement+1;
                }
            } 
            catch(UsingIteratorPastEndException e)
            {
                System.err.print(e.getMessage());
            }
            
        }
        if(length==1)
        {
            return new Constant(1,seq[0]);
        }
        if(length>0)
        {
            delta = seq[1]-seq[0];
        }
        for(int i = 0; i<length; i++)
        {
            if(i>0)
            {
                if((delta != 999) && (delta != ((seq[i]) - (seq[i-1]))))
                {
                    isDelta = false;
                }
                delta = seq[i]-seq[i-1];
            }
            
        }
        //we can check how the delta is changing. If it is always 0 then we have a constant, if it is a constant delta aside from 0 then we have a delta, otherwise we have a jumble
        if(delta == 0 && isDelta == true)
        {
            return new Constant(length,seq[0]);
        }
        else if(isDelta == true)
        {
            return new Delta(length, seq[0], delta);
        }
        else
        {
            return new Jumble(seq);
        }

    }
}