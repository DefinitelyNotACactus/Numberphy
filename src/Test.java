import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

public class Test {
    
    static double halley(String function, double x0, double tolmin, int nitr) {
        
        double xn = x0;
        double tolmax = tolmin + 1;
        int itr = 0;
        
        Function f = new Function("f(x) = " + function);
        /*
        Expression e = new Expression("f(" + xn + ")", f);
        
        Expression e1 = new Expression("der( " + f.getFunctionExpressionString() + ", x, " + xn + ")");
        
        Expression e2 = new Expression("der( der( " + f.getFunctionExpressionString() + ", x, x), x, " + xn + ")");
        */
        while(tolmax > tolmin && itr < nitr) {
            
            Expression e = new Expression("f(" + xn + ")", f);
            Expression e1 = new Expression("der( " + f.getFunctionExpressionString() + ", x, " + xn + ")");
            Expression e2 = new Expression("der( der( " + f.getFunctionExpressionString() + ", x, x), x, " + xn + ")");
            
            x0 = xn;
            xn = xn - (2*e.calculate()*e1.calculate())/
                    (2*e1.calculate()*e1.calculate() - e.calculate()*e2.calculate());
            itr++;
            
            if(xn != 0) {
                tolmax = Math.abs((xn - x0)/xn);
            }
  
            /*e = new Expression("f(" + xn + ")", f);
            e1 = new Expression("der( " + f.getFunctionExpressionString() + ", x, " + xn + ")");
            e2 = new Expression("der( der( " + f.getFunctionExpressionString() + ", x, x), x, " + xn + ")");*/
        }
        
        return xn;
    }
    
    public static void main(String[] args) {
        
        System.out.println(halley("x^(2) + 2/7*x + 1/49", 1.0, 0.0001, 100));
    
    }
}
