import java.util.*;

//expression interface -----------------------
interface Expression {
    public void interpret(Stack<Integer> s);
}

// expression interface fin--------------------

// terminal expression number ----------------------------
class TerminalExpression_Number implements Expression {
    private int number;

    public TerminalExpression_Number(int number) {
        this.number = number;
    }

    public void interpret(Stack<Integer> s) {
        s.push(number);
    }
}
// -------------------------------------------------------

// terminal expression plus ---------------------------------
class TerminalExpression_Plus implements Expression {
    public void interpret(Stack<Integer> s) {
        s.push(s.pop() + s.pop());
    }
}
// ----------------------------------------------------------

// terminal expression moins---------------------------------
class TerminalExpression_Minus implements Expression {
    public void interpret(Stack<Integer> s) {
        s.push(-s.pop() + s.pop());
    }
}
// -------------------------------------------------------------

/*
 * Non terminal expression parser qui cree un array d'expression qui contiendra
 * tout les element spliter d'un string puis chacun de ces element va subir un
 * controle
 */
class Parser {
    private ArrayList<Expression> parseTree = new ArrayList<Expression>();

    public Parser(String s) {
        for (String token : s.split(" ")) {
            if (token.equals("+"))
                parseTree.add(new TerminalExpression_Plus());
            else if (token.equals("-"))
                parseTree.add(new TerminalExpression_Minus());
            // ...
            else
                parseTree.add(new TerminalExpression_Number(Integer.parseInt(token)));
        }
    }
    // -----------------------------------------------------------------------------------------

    // permet d 'afficher le resultat contenue dans l'array'
    public int evaluer() {
        Stack<Integer> context = new Stack<Integer>();
        for (Expression e : parseTree)
            e.interpret(context);
        return context.pop();
    }
}

// --------------------------------

/*
 * L'exemple Java suivant montre comment interpréter un langage spécialisé, tel
 * que les expressions en notation polonaise inversée. Dans ce langage, on donne
 * les opérandes avant l'opérateur.
 */
class InterpreterExemple {
    public static void main(String[] args) {
        String expression = "42 3 2 - +";
        Parser p = new Parser(expression);
        System.out.println("'" + expression + "' = " + p.evaluer());
    }
}
// compiler :javac Postfix.java
// executer : java IterpreterExample