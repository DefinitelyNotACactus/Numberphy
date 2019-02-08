/**************************************************************************
* Copyright (c) 2001, 2005 David J. Eck                                   *
*                                                                         *
* Permission is hereby granted, free of charge, to any person obtaining   *
* a copy of this software and associated documentation files (the         *
* "Software"), to deal in the Software without restriction, including     *
* without limitation the rights to use, copy, modify, merge, publish,     *
* distribute, sublicense, and/or sell copies of the Software, and to      *
* permit persons to whom the Software is furnished to do so, subject to   *
* the following conditions:                                               *
*                                                                         *
* The above copyright notice and this permission notice shall be included *
* in all copies or substantial portions of the Software.                  *
*                                                                         *
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,         *
* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF      *
* MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  *
* IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY    *
* CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,    *
* TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE       *
* SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.                  *
*                                                                         *
* ----                                                                    *
* (Released under new license, April 2012.)                               *
*                                                                         *
*             David J. Eck                                                *
*             Department of Mathematics and Computer Science              *
*             Hobart and William Smith Colleges                           *
*             300 Pulteney Street                                         *
*             Geneva, NY 14456                                            *
*             eck@hws.edu                                                 *
*             http://math.hws.edu/eck                                     *
**************************************************************************/

// November 2005: Removed processKeyEvent to get rid of bogus "beep" when shift key is pressed.
// (this also lets illegal characters into the input box)
package view;

import edu.hws.jcm.awt.Controller;
import edu.hws.jcm.awt.InputObject;
import edu.hws.jcm.awt.JCMError;
import edu.hws.jcm.data.Cases;
import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.ExpressionProgram;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.ParseError;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.SimpleFunction;
import edu.hws.jcm.draw.DrawString;
import java.awt.AWTEvent;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.TextEvent;
import javax.swing.JPanel;

/**
 * An ExpressionInput is an input box that allows the user
 * input a mathematical expression.  There is an associated
 * object that belongs to the class Expression.  The
 * value of this object can change only when checkInput()
 * is called.  The checkInput() method is usually called by a Controller.
 *    <p>An ExpressionInput will ordinarily be registered with
 * a Controller in TWO ways:  It's added to a Controller
 * with the Controller's add() method.  This makes the Contrller
 * call the ExpressionInput's checkInput() method during the
 * Controller's compute() method.  Secondly, the Controller
 * is set as the "onUserAction" property.  This causes the
 * Controller's compute() method to be called when the user
 * presses return in the ExpressionInput box.  This is optional--
 * you might, for example, only want the Controller to compute()
 * when a Compute button is pressed.  You can also set the
 * ExpressionInput's onTextChange property to a Controller
 * if you want it to compute every time the text in the box
 * changes.
 *    <p>Use the function getFunction() if you want to
 * use an ExpressionInput as a way of inputting a function.
 *
 */
public class ExpressionInput extends JPanel implements InputObject, Value {
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        x0Label = new javax.swing.JLabel();
        x0TextField = new javax.swing.JTextField();
        xrLabel = new javax.swing.JLabel();
        xrTextField = new javax.swing.JTextField();
        toleranceLabel = new javax.swing.JLabel();
        toleranceTextField = new javax.swing.JTextField();
        iterationsLabel = new javax.swing.JLabel();
        iterationsTextField = new javax.swing.JTextField();
        inputPanel = new javax.swing.JPanel();
        functionLabel = new javax.swing.JLabel();
        functionTextField = new javax.swing.JTextField();

        x0Label.setText("X0");
        x0Label.setToolTipText("X inicial");

        x0TextField.setColumns(4);

        xrLabel.setText("Xr");
        xrLabel.setToolTipText("X da direita");

        xrTextField.setColumns(4);

        toleranceLabel.setText("Tol.");
        toleranceLabel.setToolTipText("Tolerancia");

        toleranceTextField.setColumns(4);

        iterationsLabel.setText("Iter.");
        iterationsLabel.setToolTipText("Iteracoes");

        iterationsTextField.setColumns(4);

        setBackground(Color.lightGray);

        functionLabel.setText("f(x)");
        inputPanel.add(functionLabel);

        functionTextField.setColumns(15);
        functionTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                functionTextFieldActionPerformed(evt);
            }
        });
        inputPanel.add(functionTextField);

        if(method == Methods.RIDDERS) {
            x0Label.setText("xL");
            x0Label.setToolTipText("Limite da Esquerda");
        }
        inputPanel.add(x0Label);
        inputPanel.add(x0TextField);
        if(method == Methods.RIDDERS) {
            inputPanel.add(xrLabel);
            inputPanel.add(xrTextField);
        }
        inputPanel.add(toleranceLabel);
        inputPanel.add(toleranceTextField);
        inputPanel.add(iterationsLabel);
        inputPanel.add(iterationsTextField);
        setBackground(Color.lightGray);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(inputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(inputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void functionTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_functionTextFieldActionPerformed
        if (onUserAction != null) {
            onUserAction.compute();
        }
    }//GEN-LAST:event_functionTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel functionLabel;
    private javax.swing.JTextField functionTextField;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JLabel iterationsLabel;
    private javax.swing.JTextField iterationsTextField;
    private javax.swing.JLabel toleranceLabel;
    private javax.swing.JTextField toleranceTextField;
    private javax.swing.JLabel x0Label;
    private javax.swing.JTextField x0TextField;
    private javax.swing.JLabel xrLabel;
    private javax.swing.JTextField xrTextField;
    // End of variables declaration//GEN-END:variables

    /**
    * The Expression associate with this input box.  
    * Class EI is a private nested class.
    */
   protected EI expr;

   /**
    * A parser for parsing the user's input
    * expression.  If this is null,
    * a default parser will be used and
    * only constant expressions will
    * be allowed.   
    */
   protected Parser parser;

   //protected boolean hasChanged;
   protected String previousContents;   
   

   /**  
    * True if an error should be thrown
    * when checkInput() is called,
    * but the content of the box is not
    * a legal expression.  Otherwise, the
    * expression will become a constant
    * expression with value Double.NaN.   
    */
   protected boolean throwErrors;
                                      
   private Controller onUserAction;   // If this is non-null, the compute() method
                                      //   of onUserAction is called when the user
                                      //   presses return in this input-box.
   
   private Controller onTextChange;   // If this is non-null, the compute() method
                                      //   of onTextChange is called when the text
                                      //   in this input box changes
                                   
   /**
    * Error message from the most recent
    * time the input was checked by a
    * call to checkInput().  If this is
    * null, then no error occurred.
    */
   protected String errorMessage;
                                      
   private long serialNumber;         // This goes up by one every time checkInput()
                                      //   is called and finds a change in the
                                      //   user's input;
   
   private Methods method;
   private double x0;
   private double xR;
   private int iterations;
   private double moe;
   
   /**
    * Create an ExpressionInputBox with initial contents given by initialValue.(If initialValue is null, the empty string is used.)  If p is not null,
 then p will be used to parse the contents of the box.
    *
    * @param method
    * @param initialValue initial contents of ExpressionInputBox.
    * @param p if non-null, this parser will be used to parse contents of the ExpressionInputBox.
    */
   public ExpressionInput(Methods method, String initialValue, Parser p) {
        this.method = method;
        initComponents();
        expr = new EI();
        if (initialValue == null) {
            initialValue = "";
        }
        functionTextField.setText(initialValue);
        setParser(p);  // (Sets previousContents to null, so checkInput() will actually check the input.)
        checkInput();  // Won't throw an error, since throwErrors is false.
        throwErrors = true;
   }
   
   /**   
    * Set the parser that is used to parse the user's input strings.
    * If this is null, then a default parser is used that will
    * only allow constant expressions.
    *
    * @param p parser to register with user's input strings.
    */
   public void setParser(Parser p) {
      parser = (p == null)? new Parser() : p;
      //hasChanged = true;  // force re-compute when checkInput() is next called.
      previousContents = null;
   }
   
   /**
    * Get the Expression associated with this ExpressionInput.
    *
    */
   public Expression getExpression() {
      return expr;
   }

   /**      
    * Get a function of one variable whose value at a real number
    * x is computed by assigning x to the variable v and then
    * returning the value of the expression associated with this
    * ExpressionInput.  Of couse, v should be one of the variables
    * registered with the Parser for this ExpressionInput, or else
    * in can never occur in the expression.
    *    Note that the value of the variable v changes TEMPORARILY
    * when the function is evaluated.  (So you should not use
    * a variable where setting the value has a side effect,
    * such as the variable associated with a SliderVariable.)
    *
    * @param v The function that is returned in a function of this variable.
    */    
   public Function getFunction(Variable v) {
      return new SimpleFunction(expr,v);
   }
      
   /**
    * Get a function of one or more variables whose value at arguments
    * x1, x2, ... is computed by assigning the x's to the variables and then
    * returning the value of the expression associated with this
    * ExpressionInput.  Of couse, each v[i] should be one of the variables
    * registered with the Parser for this ExpressionInput.
    *    Note that the value of the variables change TEMPORARILY
    * when the function is evaluated.
    *
    * @param v The function that is returned is a function of the variables in this array.
    */
   public Function getFunction(Variable[] v) {
      return new SimpleFunction(expr,v);
   }

   /**      
    * Return the current value of the expression associated with
    * this ExpressionInput.
    */
   public double getVal() {
      return expr.getVal();
   }
   
   /**   
    * If the parameter c is non-null, then its compute method will be called whenever
    * the user presses the return key while typing in this text-input box.
    */
   public void setOnUserAction(Controller c) {
      onUserAction = c;
      enableEvents(AWTEvent.ACTION_EVENT_MASK);
   }

   /**   
    * Return the Controller, if any, that is notified when the user 
    * presses return in this text-input box.
    */
   public Controller getOnUserAction() {
      return onUserAction;
   }

   /**
    * Method required by InputObject interface; in this class, it simply calls
    * setOnUserAction(c).  This is meant to be called by JCMPanel.gatherInputs().
    */
    public void notifyControllerOnChange(Controller c) {
       setOnUserAction(c);
    }

   /**   
    * If the parameter, c, is non-null, then its compute method will be called whenever
    * the text in this input box changes.  Furthermore, the throwErrors
    * property will be set to false, to avoid throwing multiple errors
    * while the user is typing.  (You can change it back to true if
    * you want by calling setThrowErrors(true).)  It would only rarely make sense to
    * use this feature.
    */
   public void setOnTextChange(Controller c) {
      onTextChange = c;
      enableEvents(AWTEvent.TEXT_EVENT_MASK);
      if (c != null)
         throwErrors = false;
   }
   
   /**
    * Return the Controller, if any, that is notified whenever the text
    * in this input box changes
    */
   public Controller getOnTextChange() {
      return onTextChange;
   }

   /**   
    * Set the throwErrors property.  When this is true, a JCMError can be thrown
    * when checkInput() is called an a parse error is found in the contents of the input box.
    * If throwErrors is false, no error is thrown.  Instead,
    * the expression is set to a constant expression with value Double.NaN.
    */
   public void setThrowErrors(boolean throwErrors) {
      this.throwErrors = throwErrors;
   }
   
   /**
    * Return the value of the throwErrors property, which determines whether errors
    * can be thrown when checkInput() is called.
    */
   public boolean getThrowErrors() {
      return throwErrors;
   }

   /**   
    * Get error message from previous call to checkInput().
    * Returns null if and only if there was no error.
    */
   public String getErrorMessage() {
      return errorMessage;
   }
   
   
   //---------------- Some implementation details -------------------------------------------------

   /**      
    * Get the expression from the box, maybe throw a JBCError
    * if a ParseError occurs.  This is meant to be called by a Controller, in general.
    * The expression associated with this ExpressionInput can only change when this
    * method is called; it DOES NOT change continuously as the user types.
    */
   public void checkInput() {
      boolean hasChanged = previousContents == null || !previousContents.equals(functionTextField.getText());
      if (!hasChanged)
         return;
      expr.serialNumber++;
      String contents = functionTextField.getText();
      try {
         expr.exp = parser.parse(contents);
         errorMessage = null;
         previousContents = functionTextField.getText();
      }
      catch (ParseError e) {
         expr.exp = null;
         if (throwErrors) {
            errorMessage = "Error in expression: " + e.getMessage();
            functionTextField.setCaretPosition(e.context.pos);
            requestFocus();
            throw new JCMError(e.getMessage(),this);
         }
         else
            errorMessage = "Error in expression at position " + e.context.pos + ": " + e.getMessage();
      }
   }   

   /**
    * Returns functionTextField text.
    * @return 
    */
   public String getText() {
       return functionTextField.getText();
   }
   
   /**   
    * Set the text displayed in this input box.  This overrides TextField.setText 
    * to make sure that the expression will be recomputed the next time
    * checkInput() is called.   
    */
   public void setText(String str) {
      functionTextField.setText(str);
      //hasChanged = true;
      previousContents = null;
   }
    
   /**  
    * Overridden to call onUserAction.compute() if onUserAction is non-null.
    * This is not meant to be called directly.
    */
   public void processTextEvent(TextEvent evt) {
      if (onTextChange != null) {
        onTextChange.compute();
      }
   }

   /**   
    * The expression associated with an ExpressionInput belongs to this class.
    * So is any derivative of such a function.  Note that derivatives
    * must be recomputed when the expression changes.  This is done
    * via "lazy evaluation", that is, only when necessary.  When
    * a derivative is used, it tests whether it is out of date
    * by comparing its serialNumber to the serial number of the
    * expression that it is the derivative of.  If they don't match,
    * then the expression is recomputed and the serial number is updated.
    * The serial number and defintion of the main expresssion is changed by
    * checkInput() whenever the user's input has changed.
    */
   protected class EI implements Expression {
      /**
       * The actual expression, or null if the
       * expression is undefined.  If this is a
       * derivative of another EI, this will be
       * recomputed as necessary when the expression is used
       * in some way.
       */
      ExpressionProgram exp;

      /**
       * This is null for the original expression input by the
       * user.  If this EI was formed by taking the derivative
       * of anotehr EI, that EI is stored here. 
       */
      EI derivativeOf;

      /**
       * Which Variable is this a derivative with respect to?
       * If derivativeOf is null, so is wrt.      
       */
      Variable wrt;

      /**
       * For the original expression input by the user, this
       * goes up by one each time checkInput() is called and
       * finds a change in the user's input.  For derivative 
       * EI, this is the serial number of "derivativeOf" at
       * the time this derivative expression was last computed.      
       */
      int serialNumber;
                         
      EI() {
          serialNumber = -1; // Forces exp to be computed the first time it is needed.
      }
      public double getVal() {
         checkForChanges();
         if (exp == null)
            return Double.NaN;
         return exp.getVal();
      }
      public double getValueWithCases(Cases c) {
         checkForChanges();
         if (exp == null)
            return Double.NaN;
         return exp.getValueWithCases(c);
      }
      public String toString() {
         checkForChanges();
         if (exp == null)
            return "(undefined)";
         return exp.toString();
      }
      
      public Expression derivative(Variable wrt) {
         EI deriv = new EI();
         deriv.derivativeOf = this;
         deriv.wrt = wrt;
         return deriv;
      }
      
      public boolean dependsOn(Variable x) {
         checkForChanges();
         return exp.dependsOn(x);
      }
      
      void checkForChanges() {
         if (derivativeOf != null) {
            derivativeOf.checkForChanges();
            if (serialNumber != derivativeOf.serialNumber) {
               serialNumber = derivativeOf.serialNumber;
               if (errorMessage != null)
                  exp = null;
               else
                  exp = (ExpressionProgram)derivativeOf.exp.derivative(wrt);
            }
         }
      }
   }  // end nested class EI
}
