/* Part 1 */
c_numbers(N) :-
	course(N,_,_).
c_pl(N) :-
	course(N,programming_languages,_).
c_notpl(N) :-
	course(N,_,_),
	\+ course(N,programming_languages,_).
c_inst60(L) :-
	course(60,_,L).
c_inst60_sorted(L) :-
	course(60,_,Z),
	sort(Z,L).
c_inst20(L) :-
	course(20,_,L).
c_inst20_sorted(L) :-
	course(20,_,Z),
	sort(Z,L).
c_inst_sorted(N,L) :-
	course(N,_,Z),
	sort(Z,L).
c_single_inst(N) :-
	course(N,_,X),
	length(X,1).
c_multi_inst(N) :-
	course(N,_,X),
	\+ length(X,1).
c_exclusive(I,N) :-
	course(N,_,X),
	member(I,X),
	length(X,1).
c_12_inst_1or(N) :-
	course(N,_,X),
	length(X,1) ;
	course(N,_,Y),
	length(Y,2).
c_12_inst_2wo(N) :-
	course(N,_,X),
	length(X,1).

c_12_inst_2wo(N) :-
	course(N,_,X),
	length(X,2).


/* Part 2 */
delete_question("Gprolog's delete is on Page 157 of the 5th edition").

sortappend(L1,L2,Z) :-
	append(L1,L2,A),
	/* sort and remove duplicates */
	sort(A,Z).

/* Part 3 */
distribute(_,[],[]).
distribute(W,[Head|Tail],A) :- 
	/* append [[W,Head]] to [], in order to put extra brackets around unit */
	append([[W,Head]],[],AHead),
	/* recurse into tail */
	distribute(W,Tail,ATail),
	append(AHead,ATail,A).

/* Part 4 */
myfor(L,U,Result) :-
	L =< U,
	L1 is L + 1,
	myfor(L1,U,Res1),
	Result = [L|Res1].
myfor(L,U,[]) :-
	L > U.
crossmyfor(R,H,Z) :-
	/* Use myfor to create 2 lists of integers for cross product calculation */
	myfor(1,R,_X),
	myfor(1,H,_Y),
	/* Calculate cross product of the two lists */
	crossmyforcalc(_X,_Y,Z).

/* Base Case */
crossmyforcalc([],_,[]).
crossmyforcalc([Head|Tail],H,Z) :-
	distribute(Head,H,AHead),
	/* Recurse into Tail of first list */
	crossmyforcalc(Tail,H,ATail),
	append(AHead,ATail,Z).
	
/* Part 5 */

/* a */
getallmeetings([],[]).
getallmeetings([CHead|CTail],Z) :-
	/* Gets the meetings list from CHead */
	last(CHead,ZHead),
	getallmeetings(CTail,ZTail),
	/* Sorts and appends to create the list of all meetings */
	sortappend(ZHead,ZTail,Z).

/* b */
participants(C,Z) :-
	/* Get all meetings to use for assigning names to their meeting */
	getallmeetings(C,L),
	participantsMeetings(C,L,Z).

participantsMeetings(_,[],[]).
participantsMeetings(C,[LHead|LTail],Z) :-
	/* This joins the respective people with their meetings */
	participantsPeople(C,LHead,PList),
	append([[LHead,PList]],[],ZHead),
	participantsMeetings(C,LTail,ZTail),
	append(ZHead,ZTail,Z).

participantsPeople([],_,[]).
participantsPeople([CHead|CTail],L,P) :-
	flatten(CHead,_C),
	/* Checks if current person has a meeting L */
	(  member(L,_C)
    -> 
	    nth(1,CHead,PHead),
	    _flag = 0
    ;  
    	_flag = 1
     ),
	participantsPeople(CTail,L,PTail),
	( _flag is 0
	->
		T = [PHead|PTail],
		sort(T,P)
	; 
		T = PTail,
		sort(T,P)
	).

osched(_,_,[],[]).
osched(MR,MH,C,Z) :-
	/* This is to make sure that we have enough slots for meetings */
	participants(C,Events),
	M = MR * MH,
	length(Events,L),
	L =< M,
	/* This gets the combination of room number and time slot */
	crossmyfor(MR,MH,Hours),
	/* Create the schedule of meetings here */
	oschedCalc(Hours,Events,Z).
oschedCalc(H,[EHead|ETail],List) :-
	/* Using prolog backtracking, select helps create all the permutations of schedules using meeting slots */
	select(Elem,H,R),
	Z1 = [Elem|[EHead]],
	(ETail \== [] 
		-> 
			oschedCalc(R,ETail,Z2), 
			sort([Z1|Z2],List) 
		; 
			List = [Z1]
		). 

xsched(MR,MH,C,Z) :-
	participants(C,Events),
	M = MR * MH,
	length(Events,L),
	L =< M,
	crossmyfor(MR,MH,Hours),
	xschedCalc(Hours,Events,Z).

xschedCalc(H,[EHead|ETail],List) :-
	select(Elem,H,R),
	Z1 = [Elem|[EHead]],
	(ETail \== [] 
		-> 
			xschedCalc(R,ETail,Z2), 
			sort([Z1|Z2],List) 
		; 
			List = [Z1]
		). 
/* This code is not working */
/* xschedDupl([], []).
xschedDupl([H|[T|TR]], [H|[T1|TR1]]) :- 
    xschedCheck(H, [T|TR], T2),
    xschedDupl(T2, [T1|TR1]).

xschedCheck(_, [], []).
xschedCheck([[_|X]|_], [[[_|X]|_]|[T|TR]], [T1|TR1]) :- xschedCheck([[_|X]|_], [T|TR], [T1|TR1]).
xschedCheck([[_|X]|_], [[[_|H]|_]|[T|TR]], [[[_|H]|_]|[T1|TR1]]) :-
	X \== H,
    xschedCheck([[_|X]|_], [T|TR], [T1|TR1]). */









