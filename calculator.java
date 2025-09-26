import javax.script.ScriptException;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class calculator implements ActionListener{
    static JTextField tf;
    public static String number = "";
    public static String[] textfieldSplit = new String[50];
    public static String textfieldShow = "";
    public static int splitIndex = 0;

    calculator() {

        JFrame f = new JFrame();
        JPanel p = new JPanel();
        JPanel background = new JPanel();
        tf = new JTextField();

        // set background panel
        background.setBounds(0,0,600,800);
        background.setBackground(new Color(133, 166, 212));

        // set fonts
        Font font = new Font("Arial Rounded MT Bold",Font.BOLD,26);
        UIManager.put("Button.font", new Font("Arial Rounded MT Bold",Font.BOLD,26));
        tf.setFont(new Font("Arial Rounded MT Bold",Font.PLAIN,45));

        // set text field
        tf.setBounds(40, 50, 500, 100);
        Border border = BorderFactory.createLineBorder(Color.black, 5);
        tf.setBorder(border);
        tf.setEditable(false);
        f.add(tf);

        // setting panel for buttons
        p.setBounds(40, 200, 500, 400);
        p.setBackground(new Color(133, 166, 212));
        f.add(p);

        // creating button for numbers
        JButton numbers[] = new JButton[10];
        for (int splitIndex = 0; splitIndex < 10; splitIndex++) {
            numbers[splitIndex] = new JButton(String.valueOf(splitIndex));
            numberAction(numbers[splitIndex], String.valueOf(splitIndex));
        }

        // creating button for operations
        JButton sumBtn = new JButton("+");
        JButton minusBtn = new JButton("-");
        JButton multipltBtn = new JButton("x");
        JButton dotBtn = new JButton(".");
        JButton equalBtn = new JButton("=");
        JButton divideBtn = new JButton("/");

        // add buttons on buttons panel
        p.add(numbers[1]);p.add(numbers[2]);p.add(numbers[3]);p.add(sumBtn);
        p.add(numbers[4]);p.add(numbers[5]);p.add(numbers[6]);p.add(minusBtn);
        p.add(numbers[7]);p.add(numbers[8]);p.add(numbers[9]);p.add(multipltBtn);
        p.add(dotBtn);p.add(numbers[0]);p.add(equalBtn);p.add(divideBtn);

        // create and set crl and del and neg buttons on frame
        JButton deleteBtn = new JButton("del");
        JButton clearBtn = new JButton("crl");
        JButton negetiveBtn = new JButton("-");
        deleteBtn.setBounds(40, 620, 150, 100);
        clearBtn.setBounds(215, 620, 150, 100);
        negetiveBtn.setBounds(390, 620, 150, 100);
        f.add(deleteBtn);
        f.add(clearBtn);
        f.add(negetiveBtn);

        // set action for dot button with method
        numberAction(dotBtn, ".");
        // set action for operators with method
        operatorAction(sumBtn, "+");
        operatorAction(minusBtn, "-");
        operatorAction(multipltBtn, "x");
        operatorAction(divideBtn, "/");

        // set action for crl button
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textfieldShow = "";
                tf.setText(" "+textfieldShow);
            }
        });

        // set action for del button
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textfieldShow = textfieldShow.substring(0,textfieldShow.length()-1);
                tf.setText(" "+textfieldShow);
            }
        });

        // set action for negative button
        negetiveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i=textfieldShow.length()-1; i>=0; i--){
                    if(!(Character.isDigit(textfieldShow.charAt(i))) && !(textfieldShow.charAt(i) == '.')){
                        textfieldShow = textfieldShow.substring(0,i+1)+"(-"+textfieldShow.substring(i+1)+")";
                        break;
                    }
                    else if(i==0){
                        textfieldShow = "(-"+textfieldShow+")";
                    }
                }
                tf.setText(" "+textfieldShow);
            }
        });

        // set action for equal button
        equalBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // split the numbers and operators of textfieldShow for calculate it with calculate method
                for(int i=0; i<textfieldShow.length(); i++){
                    if((Character.isDigit(textfieldShow.charAt(i))) || (textfieldShow.charAt(i) == '.') || (textfieldShow.charAt(i) == '-' && i ==0)){
                        number += textfieldShow.charAt(i);
                    }
                    else if(textfieldShow.charAt(i) == '('){
                        i++;
                        while(textfieldShow.charAt(i) != ')'){
                            number += textfieldShow.charAt(i);
                            i++;
                        }
                    }
                    else{
                        textfieldSplit[splitIndex] = number;
                        number="";
                        textfieldSplit[splitIndex+1]= String.valueOf(textfieldShow.charAt(i));
                        splitIndex += 2;
                    }
                }
                textfieldSplit[splitIndex] = number;
                number="";
                splitIndex++;

                //calculate the result with method class(calculate a string of numbers and operators)
                try {
                    textfieldSplit = method.calculate(textfieldSplit,splitIndex).clone();
                } catch (ScriptException ex) {
                    throw new RuntimeException(ex);
                }

                // Back to the initial settings
                splitIndex=0;

                // show the result on textfield
                textfieldShow = textfieldSplit[0];
                tf.setText(" "+textfieldShow);
            }
        });

        // setting frame
        f.setSize(600, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p.setLayout(new GridLayout(4, 4, 10, 10));
        f.setLayout(null);
        f.setVisible(true);
        f.add(background);

    }

    // method of set action for numbers buttons
    public void numberAction(JButton btn, String c) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textfieldShow = textfieldShow+c;
                tf.setText(" "+textfieldShow);
            }
        });
    }

    // method of set action for operators buttons
    public void operatorAction(JButton btn, String s) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textfieldShow = textfieldShow + s;
                tf.setText(" "+textfieldShow);
            }
        });
    }

    public static void main(String[] args) {
        new calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}