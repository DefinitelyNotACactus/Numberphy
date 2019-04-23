/** ************************************************************************
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
 ************************************************************************* */
// November 2005: Removed processKeyEvent to get rid of bogus "beep" when shift key is pressed.
// (this also lets illegal characters into the input box)
package view;

import data.Constants;
import data.Hermite;
import data.IntervalFunctionComposition;
import data.Lobatto;
import data.MethodsEnum;
import data.Spline;
import edu.hws.jcm.awt.Controller;
import edu.hws.jcm.awt.InputObject;
import edu.hws.jcm.awt.JCMError;
import edu.hws.jcm.data.Cases;
import edu.hws.jcm.data.Expression;
import edu.hws.jcm.data.ExpressionProgram;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.ParseError;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.SimpleFunction;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.data.Variable;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.TextEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ExpressionInput extends JPanel implements Value, InputObject {
    private static final boolean DEBUG = false;
    
    private MethodsEnum method;
    private final Application app;
    //Input Lines                       
    private JPanel x0Line;
    private JPanel xRLine;
    private JPanel itrLine;
    private JPanel tolLine;
    private JPanel caLine;
    //Input Components
    private JButton btAnalysis;
    private JButton btCompute;
    private JLabel functionLabel;
    private JTextField functionTextField;
    private JPanel inputPanel;
    private JLabel iterationsLabel;
    private JTextField iterationsTextField;
    private JLabel toleranceLabel;
    private JTextField toleranceTextField;
    private JLabel x0Label;
    private JTextField x0TextField;
    private JLabel xRLabel;
    private JTextField xrTextField;
    /**
     * The Expression associate with this input box. Class EI is a private
     * nested class.
     */
    protected EI expr;

    /**
     * A parser for parsing the user's input expression. If this is null, a
     * default parser will be used and only constant expressions will be
     * allowed.
     */
    private Parser parser;

    //protected boolean hasChanged;
    protected String previousContents;

    /**
     * True if an error should be thrown when checkInput() is called, but the
     * content of the box is not a legal expression. Otherwise, the expression
     * will become a constant expression with value Double.NaN.
     */
    protected boolean throwErrors;

    private Controller onUserAction;   // If this is non-null, the compute() method
    //   of onUserAction is called when the user
    //   presses return in this input-box.

    private Controller onTextChange;   // If this is non-null, the compute() method
    //   of onTextChange is called when the text
    //   in this input box changes

    /**
     * Error message from the most recent time the input was checked by a call
     * to checkInput(). If this is null, then no error occurred.
     */
    protected String errorMessage;

    private long serialNumber;         // This goes up by one every time checkInput()
    //   is called and finds a change in the
    //   user's input;
    
    private double x0; //xL for Ridders
    private double xR;
    private int iterations;
    private double tolerance;
    //Interpolation fields
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
    //Hermite Fields
    private double[] dX;
    //Splines fields
    private JPanel dHeaderLine;
    private JPanel dLine;
    private JTextField d0TextField;
    private JTextField dNTextField;
    private double d0;
    private double dN;
    private EI[] expressions;
    private IntervalFunctionComposition ifc;
    private final InputEventManager input_event;
    private double[] limits;
    private boolean searched;
    
    /**
     * Create an ExpressionInputBox with initial contents given by
     * initialValue.(If initialValue is null, the empty string is used.) If p is
     * not null, then p will be used to parse the contents of the box.
     *
     * @param method
     * @param initialValue initial contents of ExpressionInputBox.
     * @param p if non-null, this parser will be used to parse contents of the
     * ExpressionInputBox.
     * @param app
     */
    public ExpressionInput(MethodsEnum method, String initialValue, Parser p, Application app) {
        if (initialValue == null) {
            initialValue = "";
        }
        
        this.method = method;
        this.app = app;
        expr = new EI();
        throwErrors = true;
        input_event = new InputEventManager(this);
        setParser(p);  // (Sets previousContents to null, so checkInput() will actually check the input.)

        if (method == MethodsEnum.HERMITE || method == MethodsEnum.SPLINES) {
            initInputTable(); // Interpolation table
        } else {
            initInput(); // Default input
            functionTextField.setText(initialValue);
            checkInput();  // Won't throw an error, since throwErrors is false.
        }
        performInputEvent();
    }
    
    private void initInput() {
        boolean gauss = (method == MethodsEnum.GAUSS);
        inputPanel = new JPanel();
        inputPanel.setBackground(Constants.BLUE);
        
        functionLabel = createLabel("f(x)", Constants.BLUE);
        inputPanel.add(functionLabel);
        
        functionTextField = new JTextField();
        functionTextField.setFont(Constants.HELVETICA);
        functionTextField.addActionListener(this::textFieldActionPerformed);
        inputPanel.add(functionTextField);
        
        x0Line = new JPanel();
        x0Line.setBackground(Constants.BLUE);
        x0Line.setLayout(TableInput.GRID_2);
        x0Label = createLabel("", Constants.BLUE);;
        x0TextField = new JTextField();
        
        x0Label.setText(gauss ? "A" : "X inicial");
        x0Label.setToolTipText("X inicial");
        
        x0TextField.setFont(Constants.HELVETICA);
        x0TextField.setText(gauss ? "-1" : "1");
        x0TextField.addActionListener(this::textFieldActionPerformed);
        
        x0Line.add(x0Label);
        x0Line.add(x0TextField);
        inputPanel.add(x0Line);
        
        if(method == MethodsEnum.RIDDERS || gauss) {
            xRLine = new JPanel();
            xRLine.setBackground(Constants.BLUE);
            xRLine.setLayout(TableInput.GRID_2);
            xRLabel = createLabel("", Constants.BLUE);
            xrTextField = new JTextField();
            
            xRLabel.setText(gauss? "B" : "X Direito");
            xRLabel.setToolTipText(gauss? "X final" : "X da direita");
            
            xrTextField.setFont(Constants.HELVETICA);
            xrTextField.setText(gauss? "1" : "5");
            xrTextField.addActionListener(this::textFieldActionPerformed);
            
            if(method == MethodsEnum.RIDDERS) {
                x0Label.setText("X Esquerdo");
                x0Label.setToolTipText("X da Esquerda");
            }
            xRLine.add(xRLabel);
            xRLine.add(xrTextField);
            inputPanel.add(xRLine);
        }
        
        if(!gauss) {
            tolLine = new JPanel();
            tolLine.setBackground(Constants.BLUE);
            tolLine.setLayout(TableInput.GRID_2);
            toleranceLabel = createLabel("Tolerância", Constants.BLUE);
            toleranceTextField = new JTextField();

            toleranceLabel.setToolTipText("Tolerância");

            toleranceTextField.setFont(Constants.HELVETICA);
            toleranceTextField.setText("0.001");
            toleranceTextField.addActionListener(this::textFieldActionPerformed);

            tolLine.add(toleranceLabel);
            tolLine.add(toleranceTextField);
            inputPanel.add(tolLine);
        }
        
        itrLine = new JPanel();
        itrLine.setBackground(Constants.BLUE);
        itrLine.setLayout(TableInput.GRID_2);
        iterationsLabel = createLabel("", Constants.BLUE);
        iterationsTextField = new JTextField();
        
        iterationsLabel.setText(gauss? "Num. Pontos" : "Iterações");
        iterationsLabel.setToolTipText(gauss? "Número de Pontos" : "Número de Iterações");
        
        iterationsTextField.setFont(Constants.HELVETICA);
        iterationsTextField.setText(gauss? "3" : "10");
        iterationsTextField.addActionListener(this::textFieldActionPerformed);
        
        itrLine.add(iterationsLabel);
        itrLine.add(iterationsTextField);
        inputPanel.add(itrLine);
        
        caLine = new JPanel();
        caLine.setBackground(Constants.BLUE);
        caLine.setLayout(TableInput.GRID_2);
        btAnalysis = new JButton();
        btCompute = new JButton();
        
        btAnalysis.setBackground(Constants.WHITE);
        btAnalysis.setForeground(Constants.BLUE);
        btAnalysis.setFont(Constants.HELVETICA);
        btAnalysis.setText("Análise");
        btAnalysis.addActionListener(this::btAnalysisActionPerformed);

        btCompute.setBackground(Constants.WHITE);
        btCompute.setForeground(Constants.BLUE);
        btCompute.setFont(Constants.HELVETICA);
        btCompute.setText("Computar");
        btCompute.addActionListener(this::textFieldActionPerformed);
        
        caLine.add(btAnalysis);
        caLine.add(btCompute);
        inputPanel.add(caLine);
        
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        
        setBackground(Constants.GRAY);        
        add(inputPanel);
    }                       

    private void textFieldActionPerformed(ActionEvent evt) {
        performInputEvent();
    }

    private void btAnalysisActionPerformed(ActionEvent evt) {
        input_event.drawTable(input_event.getPoints());
    }

    private void initInputTable() {
        inputPanel = new JPanel();
        inputPanel.setBackground(Constants.BLUE);
        
        limits = new double[4];
        table = new JPanel();
        table.setLayout(new BoxLayout(table, BoxLayout.Y_AXIS));

        topLine = new JPanel();
        topLine.setLayout(method == MethodsEnum.HERMITE ? TableInput.GRID_3 : TableInput.GRID_2);
        topLine.setBackground(Constants.BLUE);
        topLine.add(createLabel("X", Constants.BLUE));
        topLine.add(createLabel("Y", Constants.BLUE));
        if(method == MethodsEnum.HERMITE) {
            topLine.add(createLabel("dX", Constants.BLUE));
        }
        table.add(topLine);

        tableLines = new ArrayList<>();
        TableInput input;
        for (int i = 0; i < 2; i++) {
            input = new TableInput(method == MethodsEnum.HERMITE);
            input.setXInput("" + (i));
            input.setYInput("" + (i*0.2 + (0.5/(i+1))));
            tableLines.add(input);
            table.add(input);
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
        btRemove.setEnabled(false);
        bottomLine.add(btRemove);

        btCompute = new JButton();
        btCompute.setText("Computar");
        btCompute.setBackground(Constants.WHITE);
        btCompute.setForeground(Constants.BLUE);
        btCompute.setFont(Constants.HELVETICA);
        btCompute.addActionListener(this::btComputeActionPerformed);
        bottomLine.add(btCompute);

        if (method == MethodsEnum.SPLINES) {
            dHeaderLine = new JPanel();
            dHeaderLine.setLayout(TableInput.GRID_2);
            dHeaderLine.setBackground(Constants.BLUE);
            dHeaderLine.add(createLabel("d0", Constants.BLUE));
            dHeaderLine.add(createLabel("dN", Constants.BLUE));
            table.add(dHeaderLine);

            dLine = new JPanel();
            dLine.setLayout(TableInput.GRID_2);
            dLine.setBackground(Constants.BLUE);

            d0TextField = new JTextField();
            d0TextField.setFont(Constants.HELVETICA);
            d0TextField.setBackground(Constants.WHITE);
            d0TextField.setText("1");
            dLine.add(d0TextField);
            dNTextField = new JTextField();
            dNTextField.setFont(Constants.HELVETICA);
            dNTextField.setBackground(Constants.WHITE);
            dNTextField.setText("2");
            dLine.add(dNTextField);
            table.add(dLine);
        }
        table.add(bottomLine);

        searchLine = new JPanel();
        searchLine.setLayout(TableInput.GRID_2);
        searchTextField = new JTextField();
        searchTextField.setFont(Constants.HELVETICA);
        searchTextField.setBackground(Constants.WHITE);
        searchTextField.addActionListener(this::searchTextFieldActionPerformed);
        searchLine.add(searchTextField);
        searchYLabel = createLabel("", Constants.BLUE);
        searchLine.add(searchYLabel);
        searchLine.setBackground(Constants.BLUE);

        inputPanel.add(table);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        setBackground(Constants.GRAY);        
        add(inputPanel);
    }

    private void reloadInputTable(boolean remove) {
        table.remove(searchLine);
        if (remove) {
            for (int i = tableLines.size() - 1, j = tableLines.size() - 2; i > j; i--) {
                table.remove(tableLines.remove(i));
            }
            if (tableLines.size() <= 2) {
                btRemove.setEnabled(false);
            }
            if (!btAdd.isEnabled()) {
                btAdd.setEnabled(true);
            }
        } else {
            table.remove(bottomLine);
            tableLines.add(new TableInput(method == MethodsEnum.HERMITE));
            table.add(tableLines.get(tableLines.size()-1));
            if (method == MethodsEnum.SPLINES) {
                table.add(dHeaderLine);
                dNTextField.setText("");
                d0TextField.setText("");
                table.add(dLine);
            }
            table.add(bottomLine);
            if (tableLines.size() > 2) {
                btRemove.setEnabled(true);
                if (tableLines.size() == 16) {
                    btAdd.setEnabled(false);
                }
            }
        }
        table.revalidate();
    }

    private void btAddActionPerformed(ActionEvent evt) {
        reloadInputTable(false);
        searched = false;
    }

    private void btRemoveActionPerformed(ActionEvent evt) {
        reloadInputTable(true);
        searched = false;
    }

    private void btComputeActionPerformed(ActionEvent evt) {
        performInputEvent();
    }

    private void searchTextFieldActionPerformed(ActionEvent evt) {
        try {
            double X = Double.parseDouble(searchTextField.getText());
            if(X > limits[0] && X < limits[1]) {
                double Y = ifc.eval(X);
                searchYLabel.setText(String.format("%.10f", Y));
                if(!searched) {
                    searched = true;
                } else {
                    input_event.removeLastDrawable();
                }
                input_event.drawCrossHair(X, Y, Constants.BLUE);
            } else {
                searchYLabel.setText("X fora do intervalo");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(getParent(), "Verifique os parâmetros informados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Set the parser that is used to parse the user's input strings. If this is
     * null, then a default parser is used that will only allow constant
     * expressions.
     *
     * @param p parser to register with user's input strings.
     */
    public void setParser(Parser p) {
        parser = (p == null) ? new Parser() : p;
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
     * Get a function of one variable whose value at a real number x is computed
     * by assigning x to the variable v and then returning the value of the
     * expression associated with this ExpressionInput. Of couse, v should be
     * one of the variables registered with the Parser for this ExpressionInput,
     * or else in can never occur in the expression. Note that the value of the
     * variable v changes TEMPORARILY when the function is evaluated. (So you
     * should not use a variable where setting the value has a side effect, such
     * as the variable associated with a SliderVariable.)
     *
     * @param v The function that is returned in a function of this variable.
     * @return
     */
    public Function getFunction(Variable v) {
        return new SimpleFunction(expr, v);
    }

    /**
     * Get a function of one or more variables whose value at arguments x1, x2,
     * ... is computed by assigning the x's to the variables and then returning
     * the value of the expression associated with this ExpressionInput. Of
     * couse, each v[i] should be one of the variables registered with the
     * Parser for this ExpressionInput. Note that the value of the variables
     * change TEMPORARILY when the function is evaluated.
     *
     * @param v The function that is returned is a function of the variables in
     * this array.
     * @return
     */
    public Function getFunction(Variable[] v) {
        return new SimpleFunction(expr, v);
    }

    /**
     * Get the latest expression inserted by the user.
     *
     * @return Get the latest expression inserted by the user.
     */
    public String getFunctionString() {
        return previousContents;
    }

    /**
     * Return the current value of the expression associated with this
     * ExpressionInput.
     */
    @Override
    public double getVal() {
        return expr.getVal();
    }

    /**
     * If the parameter c is non-null, then its compute method will be called
     * whenever the user presses the return key while typing in this text-input
     * box.
     *
     * @param c
     */
    public void setOnUserAction(Controller c) {
        onUserAction = c;
        enableEvents(AWTEvent.ACTION_EVENT_MASK);
    }

    /**
     * Return the Controller, if any, that is notified when the user presses
     * return in this text-input box.
     *
     * @return
     */
    public Controller getOnUserAction() {
        return onUserAction;
    }

    /**
     * Method required by InputObject interface; in this class, it simply calls
     * setOnUserAction(c).This is meant to be called by JCMPanel.gatherInputs().
     *
     * @param c
     */
    @Override
    public void notifyControllerOnChange(Controller c) {
        setOnUserAction(c);
    }

    /**
     * If the parameter, c, is non-null, then its compute method will be called
     * whenever the text in this input box changes.Furthermore, the throwErrors
     * property will be set to false, to avoid throwing multiple errors while
     * the user is typing. (You can change it back to true if you want by
     * calling setThrowErrors(true).) It would only rarely make sense to use
     * this feature.
     *
     * @param c
     */
    public void setOnTextChange(Controller c) {
        onTextChange = c;
        enableEvents(AWTEvent.TEXT_EVENT_MASK);
        if (c != null) {
            throwErrors = false;
        }
    }

    /**
     * Return the Controller, if any, that is notified whenever the text in this
     * input box changes
     *
     * @return
     */
    public Controller getOnTextChange() {
        return onTextChange;
    }

    /**
     * Set the throwErrors property.When this is true, a JCMError can be thrown
     * when checkInput() is called an a parse error is found in the contents of
     * the input box. If throwErrors is false, no error is thrown. Instead, the
     * expression is set to a constant expression with value Double.NaN.
     *
     * @param throwErrors
     */
    public void setThrowErrors(boolean throwErrors) {
        this.throwErrors = throwErrors;
    }

    /**
     * Return the value of the throwErrors property, which determines whether
     * errors can be thrown when checkInput() is called.
     *
     * @return
     */
    public boolean getThrowErrors() {
        return throwErrors;
    }

    /**
     * Get error message from previous call to checkInput().Returns null if and
     * only if there was no error.
     *
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    //---------------- Some implementation details -------------------------------------------------
    /**
     * Get the expression from the box, maybe throw a JBCError if a ParseError
     * occurs. This is meant to be called by a Controller, in general. The
     * expression associated with this ExpressionInput can only change when this
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
        } catch (ParseError e) {
            expr.exp = null;
            if (throwErrors) {
                errorMessage = "Error in expression: " + e.getMessage();
                functionTextField.setCaretPosition(e.context.pos);
                requestFocus();
                throw new JCMError(e.getMessage(), this);
            } else {
                errorMessage = "Error in expression at position " + e.context.pos + ": " + e.getMessage();
            }
        }
    }

    /**
     * Returns functionTextField text.
     *
     * @return
     */
    public String getText() {
        return functionTextField.getText();
    }

    /**
     * Set the text displayed in this input box.This overrides TextField.setText
     * to make sure that the expression will be recomputed the next time
     * checkInput() is called.
     *
     * @param str
     */
    public void setText(String str) {
        functionTextField.setText(str);
        //hasChanged = true;
        previousContents = null;
    }

    /**
     * Overridden to call onUserAction.compute() if onUserAction is
     * non-null.This is not meant to be called directly.
     *
     * @param evt
     */
    public void processTextEvent(TextEvent evt) {
        if (onTextChange != null) {
            onTextChange.compute();
        }
    }

    protected class SimpleFunctionEI extends SimpleFunction {
        
        private double xi, xf;

        public SimpleFunctionEI(Expression e, Variable v, double xi, double xf) {
            super(e, v);
            this.xi = xi;
            this.xf = xf;
        }

        public SimpleFunctionEI(Expression e, Variable[] v, double xi, double xf) {
            super(e, v);
            this.xi = xi;
            this.xf = xf;
        }

        /**
         * Find the value of the function at the argument values argument[0],
         * argument[1]....Information about "cases" is stored in the Cases parameter, if it is non-null.See the Cases class for more information.
         * @param arguments
         * @param cases
         * @return 
         */
        @Override
        public double getValueWithCases(double[] arguments, Cases cases) {
            if (arguments != null && arguments.length > 0 &&
                    arguments[0] >= xi && arguments[0] <= xf)
                return super.getValueWithCases(arguments, cases);
            else return Double.NaN;
        }
    }
  
    /**
     * The expression associated with an ExpressionInput belongs to this class.
     * So is any derivative of such a function. Note that derivatives must be
     * recomputed when the expression changes. This is done via "lazy
     * evaluation", that is, only when necessary. When a derivative is used, it
     * tests whether it is out of date by comparing its serialNumber to the
     * serial number of the expression that it is the derivative of. If they
     * don't match, then the expression is recomputed and the serial number is
     * updated. The serial number and defintion of the main expresssion is
     * changed by checkInput() whenever the user's input has changed.
     */
    protected class EI implements Expression {

        /**
         * The actual expression, or null if the expression is undefined. If
         * this is a derivative of another EI, this will be recomputed as
         * necessary when the expression is used in some way.
         */
        ExpressionProgram exp;

        /**
         * This is null for the original expression input by the user. If this
         * EI was formed by taking the derivative of anotehr EI, that EI is
         * stored here.
         */
        EI derivativeOf;

        /**
         * Which Variable is this a derivative with respect to? If derivativeOf
         * is null, so is wrt.
         */
        Variable wrt;

        /**
         * For the original expression input by the user, this goes up by one
         * each time checkInput() is called and finds a change in the user's
         * input. For derivative EI, this is the serial number of "derivativeOf"
         * at the time this derivative expression was last computed.
         */
        int serialNumber;

        EI() {
            serialNumber = -1; // Forces exp to be computed the first time it is needed.
        }

        @Override
        public double getVal() {
            checkForChanges();
            if (exp == null) {
                return Double.NaN;
            }
            return exp.getVal();
        }

        @Override
        public double getValueWithCases(Cases c) {
            checkForChanges();
            if (exp == null) {
                return Double.NaN;
            }
            return exp.getValueWithCases(c);
        }

        @Override
        public String toString() {
            checkForChanges();
            if (exp == null) {
                return "(undefined)";
            }
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
                    if (errorMessage != null) {
                        exp = null;
                    } else {
                        exp = (ExpressionProgram) derivativeOf.exp.derivative(wrt);
                    }
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

    public double getd0() {
        return d0;
    }
    
    public double getdN() {
        return dN;
    }
    
    public int getN() {
        return tableLines.size();
    }
    
    /** 
     * Creates a SimpleFunction object with the given function string
     * @param function The function string
     * @return A SimpleFunction object with the given string.
     */
    public SimpleFunction createFunction(String function) {
        return createFunction(new EI(), function);
    }

    public SimpleFunction createFunction(EI ei, String function) {
        ei.serialNumber++;
        ei.exp = getParser().parse(function);
        return new SimpleFunction(ei, getApplication().getVariable());
    }
    
    public SimpleFunctionEI createFunctionEI(EI ei, String function, double xi, double xf) {
        ei.serialNumber++;
        ei.exp = getParser().parse(function);
        return new SimpleFunctionEI(ei, getApplication().getVariable(), xi, xf);
    }
    
    /**
     * @return the app
     */
    public Application getApplication() {
        return app;
    }

    /**
     * Event called when the user updates the input.
     *
     * @param event Event
     */
    public void setInputEvent(MethodImplementation event) {
        input_event.setInputEvent(event);
    }

    /**
     * Called when user presses Enter or Compute. 
     * By default, invokes InputEvent, and then invokes the input computation and exhibition.
     */
    public void performInputEvent() {
        Function f;
        String p;
        double xi, xf;
        switch(method) {
            case HALLEY:
            case RIDDERS:
                try {
                    iterations = Integer.parseInt(iterationsTextField.getText());
                    x0 = Double.parseDouble(x0TextField.getText());
                    if (method == MethodsEnum.RIDDERS) {
                        xR = Double.parseDouble(xrTextField.getText());
                    }
                    tolerance = Double.parseDouble(toleranceTextField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(getParent(), "Erro ao processar os parametros!");
                }
                if (onUserAction != null) {
                    onUserAction.compute();
                }
                input_event.invokeInputUpdate();
                break;
                
            case SPLINES:
                if (parseTable()) {
                    searchTextField.setText("");
                    searchYLabel.setText("Procurar f(x)");
                    expressions = new EI[tableLines.size()];
                    table.add(searchLine);
                    table.revalidate();
                    input_event.invokeInputUpdate();
                    Spline s = new Spline();
                    String[] pol = s.Intepolate(x, y, d0, dN);
                    for (int ip = 0, n = 0; ip < pol.length; ip++, n++) {
                        p = pol[ip];
                        if (p == null) {
                            continue;
                        }
                        if(DEBUG) {
                            System.out.println(p);
                        }
                        xi = tableLines.get(ip).parseX();
                        xf = tableLines.get(ip + 1).parseX();
                        f = createFunctionEI(expressions[n] = new EI(), p, xi, xf);
                        if (ip == 0) {
                            ifc = new IntervalFunctionComposition(f);
                        } else {
                            ifc.stackFunction(f, xi);
                        }
                        input_event.drawFunction(f, Constants.GREEN, false);
                    }
                    setBoundingBox();
                } else {
                    JOptionPane.showMessageDialog(getParent(), "Verifique os parâmetros informados.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                break;
              
            case HERMITE:
                if (parseTable()) {
                    searchTextField.setText("");
                    searchYLabel.setText("Procurar f(x)");
                    expressions = new EI[tableLines.size()];
                    table.add(searchLine);
                    table.revalidate();
                    input_event.invokeInputUpdate();
                    p = Hermite.interpolate(x, y, dX);
                    if(DEBUG) {
                        System.out.println(p);
                    }
                    xi = limits[0];
                    xf = limits[1];
                    f = createFunctionEI(expressions[0] = new EI(), p, xi, xf);
                    input_event.drawFunction(f, Constants.GREEN, false);
                    setBoundingBox();
                } else {
                    JOptionPane.showMessageDialog(getParent(), "Verifique os parâmetros informados.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                break;
                
            case GAUSS:
                Lobatto l = new Lobatto(this, "x^2 + x + 6", -1.0, 1.0, 5);
                l.weight(5, l.roots(5));
                break;
        }
    }
    
    private void setBoundingBox() {
        for (int i = 0; i < tableLines.size(); i++) {
            input_event.drawCrossHair(x[i], y[i], Constants.PURPLE);
        }
        for(int i = 0; i < 4; i++) {
            if(i%2 != 0) {
                limits[i] += 1;
            } else {
                limits[i] -= 1;
            }
        }
        input_event.setLimits(limits);
    }
    
    /** Parses the table fields and checks if it contains a valid double.
     * 
     * @return True if there's no invalid value, otherwise, false.
     */
    private boolean parseTable() {
        int i = 0;
        boolean hermite = (method == MethodsEnum.HERMITE);
        x = new double[tableLines.size()];
        y = new double[tableLines.size()];
        if(hermite) {
            dX = new double[tableLines.size()];
        }
        for (TableInput t : tableLines) {
            try {
                x[i] = t.parseX();
                y[i] = t.parseY();
                if(hermite) {
                    dX[i] = t.parseDx();
                }
                if (i == 0) {
                    if(!hermite) {
                        d0 = Double.parseDouble(d0TextField.getText());
                        dN = Double.parseDouble(dNTextField.getText());
                    }
                    limits[0] = x[i];
                    limits[1] = x[i];
                    limits[2] = y[i];
                    limits[3] = y[i];
                } else {
                    if(x[i] > limits[1]) {
                        limits[1] = x[i];
                    } else if(x[i] < limits[0]) {
                        limits[0] = x[i];
                    }
                    if(y[i] > limits[3]) {
                        limits[3] = y[i];
                    } else if(y[i] < limits[2]) {
                        limits[2] = y[i];
                    }
                }     
                i++;
            } catch (NumberFormatException ex) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Invocado quando o usuário clica em algum ponto do gráfico.
     *
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
     *
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
        if (label.getBackground() == Constants.BLUE) {
            label.setForeground(Constants.WHITE);
        }
        return label;
    }
}
