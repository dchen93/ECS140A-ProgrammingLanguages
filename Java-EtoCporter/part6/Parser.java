/* *** This file is given as part of the programming assignment. *** */
import java.util.*;

public class Parser {
	private Deque<Deque<Token>> variableBlocks;
	private LinkedList<VarBlockHolder> variableTableList;
	int currentNestingDepth = 0;

    // tok is global to all these parsing methods;
    // scan just calls the scanner's scan method and saves the result in tok.
    private Token tok; // the current token
    private void scan() {
        tok = scanner.scan();
    }

    private Scan scanner;
    Parser(Scan scanner) {
        this.scanner = scanner;
        this.variableBlocks = new ArrayDeque<Deque<Token>>();
        this.variableTableList = new LinkedList<VarBlockHolder>();
        scan();
        program();
        if( tok.kind != TK.EOF )
            parse_error("junk after logical end of program");
	printingTable();
    }

   /* protected class SortLinkedList implements Comparator{
	@Override
	protected int compare(Integer num1, Integer num2){
		if((int)num1 < (int)num2){
			return 1;
		} else{
			return -1;
		}
	}
    }*/
    
    protected class VarBlockHolder{
    	private LinkedList<VarTableHolder> variableTable;
    	private int nestingDepth;
    	
    	protected class VarTableHolder{
        	private Token variable;
		
        	private LinkedList<Token> varAssign;
        	private LinkedList<Token> varUsed;
        	
        	protected VarTableHolder(Token tk){
        		this.variable = tk;
        		this.varAssign = new LinkedList<Token>();
        		this.varUsed = new LinkedList<Token>();
        	}
        	
        	protected Token getVariable(){
        		return this.variable;
        	}
        	
        	protected void addAssigned(Token num){
			varAssign.add(num);
			
        	}

        	protected void addUsed(Token num){
			
			varUsed.add(num);

        	}
        	protected LinkedList<Token> getAssigned(){
        		return this.varAssign;
        	}
        	protected LinkedList<Token> getUsed(){
        		return this.varUsed;
        	}
        	
    	}
    	protected VarBlockHolder(int num){
    		this.nestingDepth = num;
    		variableTable = new LinkedList<VarTableHolder>();
    	}
    	protected void addVariable(Token tk){
    		variableTable.add(new VarTableHolder(tk));
    	}
    	protected int getNestingDepth(){
    		return this.nestingDepth;
    	}
    	protected void printVarTableHolder(){
         	ListIterator<VarTableHolder> iterate1 = this.variableTable.listIterator();
         	while(iterate1.hasNext()){
         		VarTableHolder holder1 = iterate1.next();
         		System.err.println(holder1.getVariable().string);
				System.err.println("  declared on line "+holder1.getVariable().lineNumber+" at nesting depth "+this.nestingDepth);
         		LinkedList<Token> assign = holder1.getAssigned();
         		LinkedList<Token> used = holder1.getUsed();
			//Collections.sort(assign, new SortLinkedList());
			//Collections.sort(used, new SortLinkedList());
         		ListIterator<Token> iterate2 = assign.listIterator();
         		ListIterator<Token> iterate3 = used.listIterator();
			int trigger = 0;
			if(!iterate2.hasNext())
			{
				System.err.print("  never assigned");
			}
			else
			{
				System.err.print("  assigned to on: ");
				int i = 0;
				int j;
				while(i < assign.size())
				{
					int counter = 0;
					if(trigger == 1)
					{
						System.err.print(" ");
					}
					else
					{
						trigger = 1;
					}
					Token element = assign.get(i); 
					j = i;
					while(j < assign.size())
					{
					
						Token element2 = assign.get(j);
						if(element2.lineNumber == element.lineNumber)
						{
							counter++;
						}
						j++;
					}
					System.err.print(""+element.lineNumber);
					if(counter>1){
						System.err.print("("+counter+")");
					}
					
					i = i + counter;
				}
			}		
       			System.err.println("");
			int trigger2 = 0;
			if(!iterate3.hasNext())
			{
				System.err.print("  never used");
			}
			else
			{
				System.err.print("  used on: ");
				int i = 0;
				int j;
				while(i < used.size())
				{
					int counter = 0;
					if(trigger2 == 1)
					{
						System.err.print(" ");
					}
					else
					{
						trigger2 = 1;
					}
					Token element = used.get(i); 
					j = i;
					while(j < used.size())
					{
					
						Token element2 = used.get(j);
						if(element2.lineNumber == element.lineNumber)
						{
							counter++;
						}
						j++;
					}
					System.err.print(""+element.lineNumber);
					if(counter>1){
						System.err.print("("+counter+")");
					}
					//System.err.print(" ");
					i = i + counter;
				}
			}
				System.err.println("");
         	}
    	}
    	protected boolean addAssigned(){
    		int done = 0;
    		ListIterator<VarTableHolder> iterate = this.variableTable.listIterator();
    		
    		while(iterate.hasNext() && (done == 0)){
    			VarTableHolder holder = iterate.next();
    			if(holder.getVariable().string.equals(tok.string)){
    				holder.addAssigned(tok);
    				done = 1;
    			}
    		}
    		if(done == 1){
    			return true;
    		} else{
    			return false;
    		}
    	}
    	protected boolean addUsed(){
    		int done = 0;
    		ListIterator<VarTableHolder> iterate = this.variableTable.listIterator();
    		
    		while(iterate.hasNext() && (done == 0)){
    			VarTableHolder holder = iterate.next();
    			if(holder.getVariable().string.equals(tok.string)){
    				holder.addUsed(tok);
    				done = 1;
    			}
    		}
    		if(done == 1){
    			return true;
    		} else{
    			return false;
    		}
    	}
    }

    private void program() {
		System.out.println("#include <stdio.h>");
		System.out.println("#include <math.h>");
		System.out.println("double c_sqrtFunc(double num);");
	    System.out.print("main()");
        block();
        c_SqrtFunc();
    }

    private void block() {
	System.out.println("{");
    	variableBlocks.push(new ArrayDeque<Token>());
    	variableTableList.add(new VarBlockHolder(currentNestingDepth));
    	if(is(TK.VAR)){
    		declarations();
    	}
    	statement_list_E();
    	
    	variableBlocks.pop();
	//variableTable.pop();
	System.out.println("}");
    }

    private void declarations() {
    	Deque<Token> temp = variableBlocks.peek();
    	VarBlockHolder temp2 = variableTableList.peekLast();
        mustbe(TK.VAR);
        while( is(TK.ID) ) {
        	if(checkExistingVar()){
        		System.err.println("variable "+tok.string+" is redeclared on line "+tok.lineNumber);
        	} else{
        		//Part4
        		//System.err.println("________tok (Declarations):______"+tok.string);
        		//tableAssign();
        		temp.push(tok);
			
			System.out.println("int x_" + tok.string + " = -12345;");
		
			//System.err.println("declared: "+tok.string+" on line: "+tok.lineNumber+" at depth: "+currentNestingDepth);
        		temp2.addVariable(tok);
        	}
            scan();
        }
        mustbe(TK.RAV);
    }
    private void statement_list_E(){
    	while(is(TK.ID) || is(TK.PRINT) || is(TK.IF) || is(TK.DO) || is(TK.FA)){
    		statement_E();
    	}
    }
    private void statement_E(){
    	if(is(TK.ID)){
    		if(iterateDeclared()){
    			assignment_E();
    		} else{
    			printUndeclared();
    		}
    	} else if(is(TK.PRINT)){
    		print_E();
    	} else if(is(TK.IF)){
    		if_E();
    	} else if(is(TK.DO)){
    		do_E();
    	} else if(is(TK.FA)){
    		fa_E();
    	}
    }


    private void assignment_E(){
    	VarBlockHolder temp = variableTableList.peekLast();
//Part4: moved tableAssign
//System.err.println("________tok (assignment):______"+tok.string);
    	tableAssigned();
        System.out.print("x_"+tok.string + " = ");
    	mustbe(TK.ID);
	//tableAssign();
    	mustbe(TK.ASSIGN);
    	expression_E();
        System.out.println(";");
    }
    private void print_E(){
        System.out.print("printf(\"%d\\n\",");
    	mustbe(TK.PRINT);
    	expression_E();
        System.out.println(");");
    }
    private void if_E(){
        System.out.print("if");
    	mustbe(TK.IF);
    	guarded_Commands_E();
        System.out.print("");
    	mustbe(TK.FI);
    }
    private void do_E(){
        System.out.println("while(1){\n\tif");
    	mustbe(TK.DO);
        guarded_Commands_E();
        if(is(TK.BOX)){
            guarded_Commands_E();
        }
    	System.out.println("else{\n\tbreak;\n}");
        System.out.println("}");
    	mustbe(TK.OD);
    }
    private void fa_E(){
    	mustbe(TK.FA);
        String variable1, num1, num2;
    	VarBlockHolder temp = variableTableList.peekLast();
    	tableAssigned();
        variable1 = tok.string;
        System.out.print("for(x_"+variable1);
    	mustbe(TK.ID);
        System.out.print(" = ");
    	mustbe(TK.ASSIGN);
    	expression_E();
    	mustbe(TK.TO);
        System.out.print("; x_"+variable1+" <= ");
    	expression_E();
    	if(is(TK.ST)){
            System.out.print("; x_"+variable1+"+= (");
    		mustbe(TK.ST);
    		expression_E();
            System.out.print("+1 ? 1 : 2))");
    	} else{
            System.out.print("; x_"+variable1+"++)");
        }
    	commands_E();
    	mustbe(TK.AF);
    }
    private void guarded_Commands_E(){
    	guarded_Command_E();
    	while(is(TK.BOX)){
            System.out.print("else if");
    		mustbe(TK.BOX);
    		guarded_Command_E();
    	}
    	if(is(TK.ELSE)){
            System.out.print("else");
    		mustbe(TK.ELSE);
    		commands_E();
    	}
    }
    private void guarded_Command_E(){
        System.out.print("(");
    	expression_E();
        System.out.print(")");
    	commands_E();
    }
    private void commands_E(){
    	mustbe(TK.ARROW);
    	currentNestingDepth = currentNestingDepth+1;
    	block();
    	currentNestingDepth = currentNestingDepth-1;
    }
    private void expression_E(){
    	simple_E();
    	if(is(TK.EQ) || is(TK.LT) || is(TK.GT) || is(TK.NE) || is(TK.LE) || is(TK.GE)){
    		relop_E();
    		simple_E();
    	}
    }
    private void simple_E(){
    	term_E();
    	while(is(TK.PLUS) || is(TK.MINUS)){
    		addop_E();
    		term_E();

    	}
    }
    private void term_E(){
    	factor_E();
    	while(is(TK.TIMES) || is(TK.DIVIDE)){
    		multop_E();
    		factor_E();
    	}
    }
    private void relop_E(){
    	if(is(TK.EQ)){
    		mustbe(TK.EQ);
            System.out.print("== ");
    	} else if(is(TK.LT)){
    		mustbe(TK.LT);
            System.out.print("< ");
    	} else if(is(TK.GT)){
    		mustbe(TK.GT);
            System.out.print("> ");
    	} else if(is(TK.NE)){
    		mustbe(TK.NE);
            System.out.print("!= ");
    	} else if(is(TK.LE)){
    		mustbe(TK.LE);
            System.out.print("<= ");
    	} else if(is(TK.GE)){
    		mustbe(TK.GE);
            System.out.print(">= ");
    	}
    }
    private void addop_E(){
    	if(is(TK.PLUS)){
            System.out.print("+ ");
    		mustbe(TK.PLUS);
    	} else if(is(TK.MINUS)){
            System.out.print("- ");
    		mustbe(TK.MINUS);
    	}
    }
    private void multop_E(){
    	if(is(TK.TIMES)){
    		mustbe(TK.TIMES);
            System.out.print("* ");
    	} else if(is(TK.DIVIDE)){
            System.out.print("/ ");
    		mustbe(TK.DIVIDE);
    	}
    }
    private void factor_E(){
    	if(is(TK.LPAREN)){
            System.out.print("( ");
    		mustbe(TK.LPAREN);
    		expression_E();
            System.out.print(") ");
    		mustbe(TK.RPAREN);
    	} else if(is(TK.ID)){
            //System.out.print(tok.string);
    		tableUsed();
    		if(iterateDeclared()){
    			System.out.print("x_"+tok.string+" ");
    			mustbe(TK.ID);
    		} else{
    			printUndeclared();
    		}
    		
    	} else if(is(TK.NUM)){
            System.out.print(tok.string+" ");
    		mustbe(TK.NUM);
    	} else if(is(TK.SQRT)){
    		System.out.print("(int)c_sqrtFunc(");
    		mustbe(TK.SQRT);
    		expression_E();
    		System.out.print(")");
    	} else if(is(TK.SQR)){
    		System.out.print("(int)pow(");
    		mustbe(TK.SQR);
    		expression_E();
    		System.out.print(",2)");
    	}
    }
    // is current token what we want?
    private boolean is(TK tk) {
        return tk == tok.kind;
    }

    //ensure current token is tk and skip over it.
    //Modified the output of mustbe to match the correct output.
    private void mustbe(TK tk) {
        if( ! is(tk) ) {
            //System.err.println( "mustbe: want " + tk + ", got " +
            //                        tok);
            //parse_error( "missing token (mustbe)" );
        	parse_error( "factor" );
        }
        scan();
    }

    private void parse_error(String msg) {
        System.err.println( "can't parse: line "
                            + tok.lineNumber + " " + msg );
        System.exit(1);
    }
    //Checks to see if a variable is being redeclared (within its block)
    private boolean checkExistingVar(){
    	Deque<Token> temp = variableBlocks.peek();
    	Iterator<Token> iterate = temp.iterator();
    	while(iterate.hasNext()){
    		Token token = iterate.next();
    		if(token.string.equals(tok.string)){
    			return true;
    		}
    	}
    	return false;
    }
    //Checks to see if a variable has already been declared (looks through current and all upper level stacks)
    private boolean iterateDeclared(){
    	Iterator<Deque<Token>> descendingIterate = variableBlocks.descendingIterator();
    	while(descendingIterate.hasNext()){
    		Deque<Token> temp = descendingIterate.next();
    		Iterator<Token> iterate = temp.iterator();
    		while(iterate.hasNext()){
        		Token token = iterate.next();
        		if(token.string.equals(tok.string)){
        			return true;
        		}
        	}
    	}
    	return false;
    }
    //Prints out that the variable was not declared and exits program
    private void printUndeclared(){
    	System.err.println("undeclared variable "+tok.string+" on line "+tok.lineNumber);
		System.exit(1);
    }
    private void printingTable(){
   	 ListIterator<VarBlockHolder> iterate = variableTableList.listIterator();
        while(iterate.hasNext()){
        	VarBlockHolder holder = iterate.next();
        	holder.printVarTableHolder();
        }
    }
    private void tableAssigned(){
    	int maxDepth = currentNestingDepth;
    	int done = 0;
    	boolean[] depthVisited = new boolean[variableTableList.size()];
    	
    	ListIterator<VarBlockHolder> iterate = variableTableList.listIterator(variableTableList.size());
    	while(iterate.hasPrevious() && (done == 0)){
    		VarBlockHolder holder = iterate.previous();
    		if(maxDepth >= holder.getNestingDepth() && depthVisited[holder.getNestingDepth()] != true){
    			if(holder.addAssigned()){
    				done = 1;
    			} else{
    				depthVisited[holder.getNestingDepth()] = true;
    				maxDepth--;
    			}
    		}
    	}
    }
    private void tableUsed(){
    	int maxDepth = currentNestingDepth;
    	int done = 0;
    	boolean[] depthVisited = new boolean[variableTableList.size()];
    	
    	ListIterator<VarBlockHolder> iterate = variableTableList.listIterator(variableTableList.size());
    	while(iterate.hasPrevious() && (done == 0)){
    		VarBlockHolder holder = iterate.previous();
    		if(maxDepth >= holder.getNestingDepth() && depthVisited[holder.getNestingDepth()] != true){
    			if(holder.addUsed()){
    				done = 1;
    			} else{
    				depthVisited[holder.getNestingDepth()] = true;
    				maxDepth--;
    			}
    		}
    	}
    	
	}
    
    private void c_SqrtFunc(){
    	System.out.println("double c_sqrtFunc(double num){");
    	System.out.println("    if(num < 0){");
    	System.out.println("        return 0;");
    	System.out.println("    } else{");
    	System.out.println("        return sqrt(num);");
    	System.out.println("    }");
    	System.out.println("}");
    }
}
