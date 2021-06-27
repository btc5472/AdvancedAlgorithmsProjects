/*********************************************************************
Brandon Cobb
Comp 282 Monday/Wednesday 2pm
Assignment 1
DATE
This program solves many different sudoku puzzles but not all of them.
It does so with...
**********************************************************************/

public class test1a {

	public static void main(String[] args) {

		String s[][] = {
				{ 	"800 000 042",
					"007 059 063",
					"000 000 900",

					"000 900 400",
					"650 080 071",
					"004 003 000",

					"002 000 000",
					"910 620 800",
					"780 000 004" },
					
				{ 	"800 000 042",
					"007 059 063",
					"000 000 900",

					"000 300 400",
					"650 080 071",
					"004 003 000",

					"002 000 000",
					"910 620 800",
					"780 000 004" },
						

				{ 	"002 600 700",
					"800 004 005",
					"007 005 320",

					"109 000 007",
					"000 507 000",
					"600 000 904",

					"058 700 600",
					"200 300 009",
					"003 006 800" }
		};

		String answers[] = {
				"895316742427859163361247985138975426659482371274163598542738619913624857786591234",
				"502630748836274195007005326129463587384597261675000934058700613261358479703006852" };
		String rotateAnswers[] = {
				"795261348814753629632498175567149283923687451148325796286534917351972864479816532",
				"720631085065782030318549762037054026050096073680073540846925317571368294293417658" };
		sudoku p;

		System.out.println("Author: " + sudoku.myName());
		int good = 0;
		for (int i = 0; i < s.length; i++) {
			p = new sudoku(s[i]);
			if (!p.isValid()) {
					System.out.println(p + "\n Invalid board. \n");
			} else {
				System.out.print("#" + i + ": ");
				p.solve();
				if (p.isComplete())
					System.out.print("Solution found.");
				else
					System.out.print("Not done yet.  ");
				if (p.toString2().compareTo(answers[good]) == 0)
					System.out.print(" Answers match.   ");
				else
					System.out.print("   *** NO MATCH ***   ");
				System.out.println("The final board: \n\n" + p);
				p = new sudoku(s[i]);
				p.rotate();
				p.solve();
				if (p.isComplete())
					System.out.print("Solution found.");
				else
					System.out.print("Not done yet.  ");
				if (p.toString2().compareTo(rotateAnswers[good]) == 0)
					System.out.print(" Answers match.    ");
				else
					System.out.print("   *** NO MATCH *** ");
				System.out.println("The final board: \n\n" + p);
				System.out.println();
				good++;
			}
		}
	}

}

/*  Output produced by my program

Author: Alfred E. Newman
#0: Solution found. Answers match.   The final board: 

895 | 316 | 742
427 | 859 | 163
361 | 247 | 985
---------------
138 | 975 | 426
659 | 482 | 371
274 | 163 | 598
---------------
542 | 738 | 619
913 | 624 | 857
786 | 591 | 234

Solution found. Answers match.    The final board: 

795 | 261 | 348
814 | 753 | 629
632 | 498 | 175
---------------
567 | 149 | 283
923 | 687 | 451
148 | 325 | 796
---------------
286 | 534 | 917
351 | 972 | 864
479 | 816 | 532


800 | 000 | 042
007 | 059 | 063
000 | 000 | 900
---------------
000 | 300 | 400
650 | 080 | 071
004 | 003 | 000
---------------
002 | 000 | 000
910 | 620 | 800
780 | 000 | 004

 Invalid board. 

#2: Not done yet.   Answers match.   The final board: 

502 | 630 | 748
836 | 274 | 195
007 | 005 | 326
---------------
129 | 463 | 587
384 | 597 | 261
675 | 000 | 934
---------------
058 | 700 | 613
261 | 358 | 479
703 | 006 | 852

Not done yet.   Answers match.    The final board: 

720 | 631 | 085
065 | 782 | 030
318 | 549 | 762
---------------
037 | 054 | 026
050 | 096 | 073
680 | 073 | 540
---------------
846 | 925 | 317
571 | 368 | 294
293 | 417 | 658

 */
