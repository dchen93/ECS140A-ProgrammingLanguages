/* *** This file is given as part of the programming assignment. *** */

public class Parser {

    // tok is global to all these parsing methods;
    // scan just calls the scanner's scan method and saves the result in tok.
    private Token tok; // the current token
    private void scan() {
        tok = scanner.scan();
    }

    private Scan scanner;
    Parser(Scan scanner) {
        this.scanner = scanner;
        scan();
        program();
        if( tok.kind != TK.EOF )
            parse_error("junk after logical end of program");
    }

    private void program() {
        block();
    }

    private void block() {
        // you'll need to add some code here
    	//IF statement for the optional declarations in the syntax diagram
    	if(is(TK.VAR)){
    		declarations();
    	}
    	statement_list_E();
    }

    private void declarations() {
        mustbe(TK.VAR);
        while( is(TK.ID) ) {
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
    		assignment_E();
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
    	mustbe(TK.ID);
    	mustbe(TK.ASSIGN);
    	expression_E();
    }
    private void print_E(){
    	mustbe(TK.PRINT);
    	expression_E();
    }
    private void if_E(){
    	mustbe(TK.IF);
    	guarded_Commands_E();
    	mustbe(TK.FI);
    }
    private void do_E(){
    	mustbe(TK.DO);
    	guarded_Commands_E();
    	mustbe(TK.OD);
    }
    private void fa_E(){
    	mustbe(TK.FA);
    	mustbe(TK.ID);
    	mustbe(TK.ASSIGN);
    	expression_E();
    	mustbe(TK.TO);
    	expression_E();
    	if(is(TK.ST)){
    		mustbe(TK.ST);
    		expression_E();
    	}
    	commands_E();
    	mustbe(TK.AF);
    }
    private void guarded_Commands_E(){
    	guarded_Command_E();
    	while(is(TK.BOX)){
    		mustbe(TK.BOX);
    		guarded_Command_E();
    	}
    	if(is(TK.ELSE)){
    		mustbe(TK.ELSE);
    		commands_E();
    	}
    }
    private void guarded_Command_E(){
    	expression_E();
    	commands_E();
    }
    private void commands_E(){
    	mustbe(TK.ARROW);
    	block();
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
    	} else if(is(TK.LT)){
    		mustbe(TK.LT);
    	} else if(is(TK.GT)){
    		mustbe(TK.GT);
    	} else if(is(TK.NE)){
    		mustbe(TK.NE);
    	} else if(is(TK.LE)){
    		mustbe(TK.LE);
    	} else if(is(TK.GE)){
    		mustbe(TK.GE);
    	}
    }
    private void addop_E(){
    	if(is(TK.PLUS)){
    		mustbe(TK.PLUS);
    	} else if(is(TK.MINUS)){
    		mustbe(TK.MINUS);
    	}
    }
    private void multop_E(){
    	if(is(TK.TIMES)){
    		mustbe(TK.TIMES);
    	} else if(is(TK.DIVIDE)){
    		mustbe(TK.DIVIDE);
    	}
    }
    private void factor_E(){
    	if(is(TK.LPAREN)){
    		mustbe(TK.LPAREN);
    		expression_E();
    		mustbe(TK.RPAREN);
    	} else if(is(TK.ID)){
    		mustbe(TK.ID);
    	} else if(is(TK.NUM)){
    		mustbe(TK.NUM);
    	}
    }
    // you'll need to add a bunch of methods here

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
}
