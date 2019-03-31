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

import data.MethodsEnum;
import data.Constants;
import data.Spline;

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
import edu.hws.jcm.draw.Graph1D;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.TextEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        x0Label = new javax.swing.JLabel();
        x0TextField = new javax.swing.JTextField();
        xrLabel = new javax.swing.JLabel();
        xrTextField = new javax.swing.JTextField();
        toleranceLabel = new javax.swing.JLabel();
        toleranceTextField = new javax.swing.JTextField();
        iterationsLabel = new javax.swing.JLabel();
        iterationsTextField = new javax.swing.JTextField();
        btAnalysis = new javax.swing.JButton();
        inputPanel = new javax.swing.JPanel();
        functionLabel = new javax.swing.JLabel();
        functionTextField = new javax.swing.JTextField();

        x0Label.setFont(Constants.HELVETICA);
        x0Label.setText("X0");
        x0Label.setToolTipText("X inicial");

        x0TextField.setColumns(4);
        x0TextField.setFont(Constants.HELVETICA);
        x0TextField.setText("1");
        x0TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldActionPerformed(evt);
            }
        });

        xrLabel.setFont(Constants.HELVETICA);
        xrLabel.setText("xR");
        xrLabel.setToolTipText("X da direita");

        xrTextField.setColumns(4);
        xrTextField.setFont(Constants.HELVETICA);
        xrTextField.setText("5");
        xrTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldActionPerformed(evt);
            }
        });

        toleranceLabel.setFont(Constants.HELVETICA);
        toleranceLabel.setText("Tol.");
        toleranceLabel.setToolTipText("Tolerancia");

        toleranceTextField.setColumns(4);
        toleranceTextField.setFont(Constants.HELVETICA);
        toleranceTextField.setText("0.001");
        toleranceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldActionPerformed(evt);
            }
        });

        iterationsLabel.setFont(Constants.HELVETICA);
        iterationsLabel.setText("Iter.");
        iterationsLabel.setToolTipText("Iteracoes");

        iterationsTextField.setColumns(4);
        iterationsTextField.setFont(Constants.HELVETICA);
        iterationsTextField.setText("10");
        iterationsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldActionPerformed(evt);
            }
        });

        btAnalysis.setBackground(Constants.WHITE);
        btAnalysis.setFont(Constants.HELVETICA);
        btAnalysis.setText("Análise");
        btAnalysis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnalysisActionPerformed(evt);
            }
        });

        setBackground(Constants.GRAY);

        inputPanel.setBackground(Constants.GRAY);

        functionLabel.setFont(Constants.HELVETICA);
        functionLabel.setText("f(x)");
        inputPanel.add(functionLabel);

        functionTextField.setColumns(15);
        functionTextField.setFont(Constants.HELVETICA);
        functionTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldActionPerformed(evt);
            }
        });
        inputPanel.add(functionTextField);

        if(method == MethodsEnum.RIDDERS) {
            x0Label.setText("xL");
            x0Label.setToolTipText("Limite da Esquerda");
        }
        inputPanel.add(x0Label);
        inputPanel.add(x0TextField);
        if(method == MethodsEnum.RIDDERS) {
            inputPanel.add(xrLabel);
            inputPanel.add(xrTextField);
        }
        inputPanel.add(toleranceLabel);
        inputPanel.add(toleranceTextField);
        inputPanel.add(iterationsLabel);
        inputPanel.add(iterationsTextField);
        inputPanel.add(btAnalysis);

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
    }// </editor-fold>                        

    private void textFieldActionPerformed(ActionEvent evt) {                                          
        performInputEvent();
    }                                         

    private void btAnalysisActionPerformed(ActionEvent evt) {                                           
        input_event.drawTable(input_event.getPoints());
    }                                          


    // Variables declaration - do not modify                     
    private javax.swing.JButton btAnalysis;
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
    // End of variables declaration                   

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
   private Parser parser;

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
   
    private MethodsEnum method;
    private final Application app;
    private double x0; //xL for Ridders
    private double xR;
    private int iterations;
    private double tolerance;
    //Interpolation fields
    private JButton btCompute;
    private JButton btAdd;
    private JButton btRemove;
    private JPanel topLine;
    private JPanel table;
    private List<TableInput> tableLines;
    private JPanel bottomLine;
    private JPanel searchLine;
    private JTextField searchTextField;
    private JLabel searchYLabel;
    private double[] x;
    private double[] y;
    //Splines fields
    private JPanel dHeaderLine;
    private JPanel dLine;
    private JTextField d0TextField;
    private JTextField dNTextField;
    private double d0;
    private double dN;
    private EI[] expressions;
    private final InputEventManager input_event;
   
   /**
    * Create an ExpressionInputBox with initial contents given by initialValue.(If initialValue is null, the empty string is used.)  If p is not null,
 then p will be used to parse the contents of the box.
    *
    * @param method
    * @param initialValue initial contents of ExpressionInputBox.
    * @param p if non-null, this parser will be used to parse contents of the ExpressionInputBox.
    * @param app
    */
    public ExpressionInput(MethodsEnum method, String initialValue, Parser p, Application app) {
        this.method = method;
        this.app = app;
        initComponents();
        expr = new EI();
        if (initialValue == null) {
            initialValue = "";
        }
        
        if(method == MethodsEnum.HERMITE || method == MethodsEnum.SPLINES) {
             initInputTable();
        } else {
            functionTextField.setText(initialValue);
        }
        
        setParser(p);  // (Sets previousContents to null, so checkInput() will actually check the input.)
        checkInput();  // Won't throw an error, since throwErrors is false.
        throwErrors = true;
        input_event = new InputEventManager(this);
        performInputEvent();
    }
   
    private void initInputTable() {
         table = new JPanel();
         table.setLayout(new BoxLayout(table, BoxLayout.Y_AXIS));
         
         topLine = new JPanel();
         topLine.setLayout(TableInput.GRID);
         topLine.setBackground(Constants.BLUE);
         topLine.add(createLabel("X", Constants.BLUE));
         topLine.add(createLabel("Y", Constants.BLUE));
         table.add(topLine);
         
         tableLines = new LinkedList<>();
         for(int i = 0; i < 4; i++) {
              tableLines.add(new TableInput());
              table.add(tableLines.get(i));
         }

         bottomLine = new JPanel();
         bottomLine.setLayout(new GridLayout(0, 3));
         bottomLine.setBackground(Constants.BLUE);

         btAdd = new JButton();
         btAdd.setText("+");
         btAdd.setBackground(Constants.WHITE);
         btAdd.setForeground(Constants.BLUE);
         btAdd.setFont(Constants.HELVETICA);
         btAdd.addActionListener(this::btAddActionPerformed);
         bottomLine.add(btAdd);

         btRemove = new JButton();
         btRemove.setText("-");
         btRemove.setBackground(Constants.WHITE);
         btRemove.setForeground(Constants.BLUE);
         btRemove.setFont(Constants.HELVETICA);
         btRemove.addActionListener(this::btRemoveActionPerformed);
         btRemove.setEnabled((method != MethodsEnum.SPLINES));
         bottomLine.add(btRemove);

         btCompute = new JButton();
         btCompute.setText("Computar");
         btCompute.setBackground(Constants.WHITE);
         btCompute.setForeground(Constants.BLUE);
         btCompute.setFont(Constants.HELVETICA);
         btCompute.addActionListener(this::btComputeActionPerformed);
         bottomLine.add(btCompute);

         if(method == MethodsEnum.SPLINES) {
             dHeaderLine = new JPanel();
             dHeaderLine.setLayout(TableInput.GRID);
             dHeaderLine.setBackground(Constants.BLUE);
             dHeaderLine.add(createLabel("d0", Constants.BLUE));
             dHeaderLine.add(createLabel("dN", Constants.BLUE));
             table.add(dHeaderLine);
             
             dLine = new JPanel();
             dLine.setLayout(TableInput.GRID);
             dLine.setBackground(Constants.BLUE);
             
             d0TextField = new JTextField(10);
             d0TextField.setFont(Constants.HELVETICA);
             d0TextField.setBackground(Constants.WHITE);
             dLine.add(d0TextField);
             dNTextField = new JTextField(10);
             dNTextField.setFont(Constants.HELVETICA);
             dNTextField.setBackground(Constants.WHITE);
             dLine.add(dNTextField);
             table.add(dLine);
         }
         table.add(bottomLine);
         
         searchLine = new JPanel();
         searchLine.setLayout(TableInput.GRID);
         searchTextField = new JTextField(10);
         //searchTextField.setToolTipText("X desejado");
         searchTextField.setFont(Constants.HELVETICA);
         searchTextField.setBackground(Constants.WHITE);
         searchTextField.addActionListener(this::searchTextFieldActionPerformed);
         searchLine.add(searchTextField);
         searchYLabel = createLabel("", Constants.BLUE);
         searchLine.add(searchYLabel);
         searchLine.setBackground(Constants.BLUE);
         
         inputPanel.removeAll();
         inputPanel.add(table);
     }

      private void reloadInputTable(boolean remove) {
          table.remove(searchLine);
          int modifier;
          if(method == MethodsEnum.HERMITE) {
              modifier = 1;
          } else {
              modifier = 4;
          }
          if(remove) {  
              for(int i = tableLines.size()-1, j = tableLines.size()-(modifier+1); i > j; i--) {
                  table.remove(tableLines.remove(i));
              }
              if(tableLines.size() <= 4 && method == MethodsEnum.SPLINES || tableLines.size() <= 2 && method == MethodsEnum.HERMITE) {
                  btRemove.setEnabled(false);
              }
              if(!btAdd.isEnabled()) {
                  btAdd.setEnabled(true);
              }
          } else {
              table.remove(bottomLine);
              for(int i = 0, s = tableLines.size(); i < modifier; i++) {
                  tableLines.add(new TableInput());
                  table.add(tableLines.get(s + i));
              }
              if(method == MethodsEnum.SPLINES) {
                   table.add(dHeaderLine);
                   dNTextField.setText("");
                   d0TextField.setText("");
                   table.add(dLine);
              }
              table.add(bottomLine);
              if(tableLines.size() > 4 || tableLines.size() > 2 && method == MethodsEnum.HERMITE) {
                  btRemove.setEnabled(true);
                  if(tableLines.size() == 16) {
                      btAdd.setEnabled(false);
                  }
              }
          }
          table.revalidate();
      }

      private void btAddActionPerformed(ActionEvent evt) {
         reloadInputTable(false);
      }

      private void btRemoveActionPerformed(ActionEvent evt) {
         reloadInputTable(true);
      }

      private void btComputeActionPerformed(ActionEvent evt) {
         if(parseTable()) {
            searchTextField.setText("");
            searchYLabel.setText("Procurar f(x)");
            expressions = new EI[tableLines.size()];
            table.add(searchLine);
            table.revalidate();
            switch(method){
                case SPLINES:
                    Spline s = new Spline();
                    String[] pol = s.Intepolate(x, y, d0, dN);
                    int n = 0;
                    Function f;
                    input_event.invokeInputUpdate();
                    for(String p : pol) {
                        //expressions[n] = new EI();
                        //expressions[n].serialNumber++;
                        //expressions[n].exp = getParser().parse(p);

                        f = new SimpleFunction(expressions[n], getApplication().getVariable());
                        input_event.drawFunction(f, Constants.GREEN, false);
                        n++;
                    }
                    
                    for(int i = 0; i < tableLines.size(); i++) {
                        input_event.drawCrossHair(x[i], y[i], Constants.RED);
                    }
                    break;
            }
         } else {
             JOptionPane.showMessageDialog(getParent(), "Verifique os parâmetros informados.", "Erro", JOptionPane.ERROR_MESSAGE);
         }
     }
      
      private void searchTextFieldActionPerformed(ActionEvent evt) {
          //TODO: handle it
      }
      
      private boolean parseTable() {
          int i = 0;
          x = new double[tableLines.size()];
          y = new double[tableLines.size()];
          for(TableInput t : tableLines) {
              try {
                  if(i == 0) {
                      d0 = Double.parseDouble(d0TextField.getText());
                      dN = Double.parseDouble(dNTextField.getText());
                  }
                  x[i] = t.parseX();
                  y[i] = t.parseY();
                  i++;
              } catch(NumberFormatException ex) {
                  return false;
              }
          }
          return true;
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
     * @return the parser
     */
    public Parser getParser() {
        return parser;
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
     * @return 
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
     * @return 
    */
   public Function getFunction(Variable[] v) {
      return new SimpleFunction(expr,v);
   }
   
   /**
    * Get the latest expression inserted by the user.
    * @return Get the latest expression inserted by the user.
    */
   public String getFunctionString() {
       return previousContents;
   }

   /**      
    * Return the current value of the expression associated with
    * this ExpressionInput.
    */
    @Override
   public double getVal() {
      return expr.getVal();
   }
   
   /**   
    * If the parameter c is non-null, then its compute method will be called whenever
    * the user presses the return key while typing in this text-input box.
     * @param c
    */
   public void setOnUserAction(Controller c) {
      onUserAction = c;
      enableEvents(AWTEvent.ACTION_EVENT_MASK);
   }

   /**   
    * Return the Controller, if any, that is notified when the user 
    * presses return in this text-input box.
     * @return 
    */
   public Controller getOnUserAction() {
      return onUserAction;
   }

   /**
    * Method required by InputObject interface; in this class, it simply calls
    * setOnUserAction(c).This is meant to be called by JCMPanel.gatherInputs().
     * @param c
    */
    @Override
    public void notifyControllerOnChange(Controller c) {
       setOnUserAction(c);
    }

   /**   
    * If the parameter, c, is non-null, then its compute method will be called whenever
    * the text in this input box changes.Furthermore, the throwErrors
 property will be set to false, to avoid throwing multiple errors
 while the user is typing.  (You can change it back to true if
 you want by calling setThrowErrors(true).)  It would only rarely make sense to
 use this feature.
     * @param c
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
     * @return 
    */
   public Controller getOnTextChange() {
      return onTextChange;
   }

   /**   
    * Set the throwErrors property.When this is true, a JCMError can be thrown
 when checkInput() is called an a parse error is found in the contents of the input box.  If throwErrors is false, no error is thrown.  Instead,
 the expression is set to a constant expression with value Double.NaN.
     * @param throwErrors
    */
   public void setThrowErrors(boolean throwErrors) {
      this.throwErrors = throwErrors;
   }
   
   /**
    * Return the value of the throwErrors property, which determines whether errors
    * can be thrown when checkInput() is called.
     * @return 
    */
   public boolean getThrowErrors() {
      return throwErrors;
   }

   /**   
    * Get error message from previous call to checkInput().Returns null if and only if there was no error.
     * @return
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
    @Override
   public void checkInput() {
      boolean hasChanged = previousContents == null || !previousContents.equals(functionTextField.getText());
      if (!hasChanged) {
         return;
      }
      expr.serialNumber++;
      String contents = functionTextField.getText();
      try {
         expr.exp = getParser().parse(contents);
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
         } else {
            errorMessage = "Error in expression at position " + e.context.pos + ": " + e.getMessage();
         }
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
    * Set the text displayed in this input box.This overrides TextField.setText 
 to make sure that the expression will be recomputed the next time
 checkInput() is called.   
     * @param str
    */
   public void setText(String str) {
      functionTextField.setText(str);
      //hasChanged = true;
      previousContents = null;
   }
    
   /**  
    * Overridden to call onUserAction.compute() if onUserAction is non-null.This is not meant to be called directly.
     * @param evt
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
      
      @Override
      public double getVal() {
         checkForChanges();
         if (exp == null)
            return Double.NaN;
         return exp.getVal();
      }
      
      @Override
      public double getValueWithCases(Cases c) {
         checkForChanges();
         if (exp == null)
            return Double.NaN;
         return exp.getValueWithCases(c);
      }
      
      @Override
      public String toString() {
         checkForChanges();
         if (exp == null)
            return "(undefined)";
         return exp.toString();
      }
      
      @Override
      public Expression derivative(Variable wrt) {
         EI deriv = new EI();
         deriv.derivativeOf = this;
         deriv.wrt = wrt;
         return deriv;
      }
      
      @Override
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

    /** 
    * @return the method 
    */
    public MethodsEnum getMethod() {
        return method;
    }
    
    public double getx0() { //Returns xL for Ridders
        return x0;
    }
    
    public double getXr() {
        return xR;
    }
    
    public double getTolerance() {
        return tolerance;
    }
    
    public int getIterations() {
        return iterations;
    }
    
    public double[] getXArray() {
        return x;
    }
    
    public double[] getYArray() {
        return y;
    }
    
    public int getN() {
        return tableLines.size();
    }
    
    /**
     * @return the app
     */
    public Application getApplication() {
        return app;
    }
    
    /**
     * Event called when the user updates the input.
     * @param event Event
     */
    public void setInputEvent(MethodImplementation event) {
        input_event.setInputEvent(event);
    }
    
    /**
     * Invocado quando o usuário pressiona Enter.
     * Por padrão, invoca InputEvent e, em seguida, invoca a computação e exibição do input.
     */
    public void performInputEvent() {
        try {
            iterations = Integer.parseInt(iterationsTextField.getText());
            x0 = Double.parseDouble(x0TextField.getText());
            if(method == MethodsEnum.RIDDERS) {
                xR = Double.parseDouble(xrTextField.getText());
            }
            tolerance = Double.parseDouble(toleranceTextField.getText());
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(getParent(), "Erro ao processar os parametros!");
        }
        if (onUserAction != null) {
            onUserAction.compute();
        }
        input_event.invokeInputUpdate();
    }
    
    /**
     * Invocado quando o usuário clica em algum ponto do gráfico.
     * @param x
     * @param y
     */
    public void performPointClickedEvent(double x, double y) {
        input_event.invokePointClickedEvent(x, y);
    }
    
    public JTextField getFunctionTextField() {
        return functionTextField;
    }
    
    /**
     * Creates a new JLabel using properties from the Constants class.
     * @param text The JLabel text
     * @param background Color background (Preferably from the Constants)
     * @return A JLabel with the properties and parameters.
     */
    public static JLabel createLabel(String text, Color background) {
        JLabel label = new JLabel();
        label.setFont(Constants.HELVETICA);
        label.setText(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBackground(background);
        label.setOpaque(true);
        if(label.getBackground() == Constants.BLUE) {
            label.setForeground(Constants.WHITE);
        }
        return label;
    }
}
