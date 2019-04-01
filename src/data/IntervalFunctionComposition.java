/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import edu.hws.jcm.data.Function;
import java.util.Stack;

/**
 *
 * @author vk
 */
public class IntervalFunctionComposition {
    
        private class Tuple<T,U> {
            private final T t;
            private final U u;

            public Tuple(T key, U value) {
                this.t = key;
                this.u = value;
            }

            /**
             * @return the t
             */
            public T getKey() {
                return t;
            }

            /**
             * @return the u
             */
            public U getValue() {
                return u;
            }
            
        }
        
        private final Stack<Tuple<Double, Function>> stack;
        
        public IntervalFunctionComposition(Function initial)
        {
            stack = new Stack<>();
            stack.add(new Tuple(Double.NEGATIVE_INFINITY, initial));
        }
        
        public void stackFunction(Function func, double xi) {
            stack.add(new Tuple(xi, func));
        }
        
        public double eval(double x) {
            for (int i = stack.size() - 1; i >= 0; i--)
            {
                Tuple<Double, Function> t = stack.get(i);
                if (x > t.getKey())
                    return t.getValue().getVal(new double[] { x });
            }
            return Double.NaN;
        }
}
