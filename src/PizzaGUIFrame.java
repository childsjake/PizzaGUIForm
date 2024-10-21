import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.ArrayList;

public class PizzaGUIFrame extends JFrame
{
    JPanel mainPnl;

    JPanel controlPnl;
    JButton orderBtn;
    JButton clearBtn;
    JButton quitBtn;

    JPanel displayPnl;
    JTextArea displayTA;
    JScrollPane scroller;

    JPanel optionsPnl;

    JPanel crustPnl;
    JRadioButton thinRB;
    JRadioButton regularRB;
    JRadioButton deepDishRB;
    ButtonGroup crustBtnGroup;

    JPanel sizePnl;
    JComboBox sizeCmbBx;

    JPanel toppingPnl;
    JCheckBox ectoChkBx;
    JCheckBox ashChkBx;
    JCheckBox boneChkBx;
    JCheckBox ghoulChkBx;
    JCheckBox manChkBx;
    JCheckBox frogChkBx;

    double tax = 0.07;
    double taxAmount;
    double smallPrice = 8;
    double mediumPrice = 12;
    double largePrice = 16;
    double superPrice = 20;
    double sizePrice;
    String sizeType;
    double toppingPrice = 1.00;
    double totalToppingsPrice;
    ArrayList<String> toppingsList = new ArrayList<>();
    String crustType;
    double subtotalPrice;
    double totalPrice;
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    int i = 0;


    public PizzaGUIFrame()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createOptionsPanel();
        mainPnl.add(optionsPnl, BorderLayout.NORTH);

        CreateDisplayPanel();
        mainPnl.add(displayPnl, BorderLayout.CENTER);

        CreateControlPanel();
        mainPnl.add(controlPnl, BorderLayout.SOUTH);

        add(mainPnl);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        setSize(screenWidth * 1 / 4, screenHeight * 5 / 8);
        setLocation(screenWidth / 2, screenHeight / 8);
        setTitle("Pizza Order Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void CreateControlPanel()
    {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1,3));

        orderBtn = new JButton("Order");
        orderBtn.setFont(new Font("Serif", Font.BOLD, 24));
        orderBtn.addActionListener((ActionEvent ae) ->
        {
            displayTA.setText("");
            crustType = "";
            totalToppingsPrice = 0;

            toppingsList.clear();
            sizePrice = 0;
            sizeType = "";
            subtotalPrice = 0;
            totalPrice = 0;
            taxAmount = 0;

            if (thinRB.isSelected())
            {
                crustType = "Thin";
            }
            if (regularRB.isSelected())
            {
                crustType = "Regular";
            }
            if (deepDishRB.isSelected())
            {
                crustType = "Deep-Dish";
            }

            if (ectoChkBx.isSelected())
            {
                totalToppingsPrice = totalToppingsPrice + toppingPrice;
                toppingsList.add("Ectoplasm");
            }
            if (ashChkBx.isSelected())
            {
                totalToppingsPrice = totalToppingsPrice + toppingPrice;
                toppingsList.add("Demon Ash");
            }
            if (boneChkBx.isSelected())
            {
                totalToppingsPrice = totalToppingsPrice + toppingPrice;
                toppingsList.add("Bone Shards");
            }
            if (ghoulChkBx.isSelected())
            {
                totalToppingsPrice = totalToppingsPrice + toppingPrice;
                toppingsList.add("Ghoul Bacon");
            }
            if (manChkBx.isSelected())
            {
                totalToppingsPrice = totalToppingsPrice + toppingPrice;
                toppingsList.add("Man Sausage");
            }
            if (frogChkBx.isSelected())
            {
                totalToppingsPrice = totalToppingsPrice + toppingPrice;
                toppingsList.add("Frog Sausage");
            }
            if (totalToppingsPrice < 1)
            {
                JOptionPane.showMessageDialog(null, "Please add at least one topping!");
                return;
            }

            if (sizeCmbBx.getSelectedIndex() == 0)
            {
                sizeType = "Small";
                sizePrice = smallPrice;
            }
            if (sizeCmbBx.getSelectedIndex() == 1)
            {
                sizeType = "Medium";
                sizePrice = mediumPrice;
            }
            if (sizeCmbBx.getSelectedIndex() == 2)
            {
                sizeType = "Large";
                sizePrice = largePrice;
            }
            if (sizeCmbBx.getSelectedIndex() == 3)
            {
                sizeType = "Super";
                sizePrice = superPrice;
            }

            subtotalPrice = totalToppingsPrice + sizePrice;
            taxAmount = subtotalPrice * tax;
            totalPrice = subtotalPrice + taxAmount;

            displayTA.setFont(new Font("Serif", Font.BOLD, 22));
            displayTA.append("===================================================\n");
            displayTA.setFont(new Font("Serif", Font.PLAIN, 22));
            displayTA.append("Size: " + sizeType + "   " + "Crust: " + crustType + "                                  ");
            displayTA.append(formatter.format(sizePrice) + "\n");

            while (i < toppingsList.size())
            {
                displayTA.append(toppingsList.get(i) + "                                                               "
                        + formatter.format(toppingPrice) + "\n");
                i++;
            }
            i = 0;
            displayTA.append("\n" + "\n");

            displayTA.setFont(new Font("Serif", Font.BOLD, 22));
            displayTA.append("Sub-Total: " + "                                                               ");
            displayTA.setFont(new Font("Serif", Font.PLAIN, 22));
            displayTA.append(formatter.format(subtotalPrice) + "\n");

            displayTA.setFont(new Font("Serif", Font.BOLD, 22));
            displayTA.append("Tax: " + "                                                                        ");
            displayTA.setFont(new Font("Serif", Font.PLAIN, 22));
            displayTA.append(formatter.format(taxAmount) + "\n" + "\n");
            displayTA.append("---------------------------------------------------------------------------------------\n" + "\n");

            displayTA.setFont(new Font("Serif", Font.BOLD, 22));
            displayTA.append("Total: " + "                                                                      ");
            displayTA.setFont(new Font("Serif", Font.PLAIN, 22));
            displayTA.append(formatter.format(totalPrice) + "\n" + "\n");
            displayTA.append("===================================================\n");

        });

        clearBtn = new JButton("Clear");
        clearBtn.setFont(new Font("Serif", Font.BOLD, 24));
        clearBtn.addActionListener((ActionEvent ae) ->
        {
            displayTA.setText("");

            totalToppingsPrice = 0;
            totalPrice = 0;
            taxAmount = 0;
            subtotalPrice = 0;
            i = 0;
            ectoChkBx.setSelected(false);
            ashChkBx.setSelected(false);
            boneChkBx.setSelected(false);
            ghoulChkBx.setSelected(false);
            manChkBx.setSelected(false);
            frogChkBx.setSelected(false);
            regularRB.setSelected(true);
            sizeCmbBx.setSelectedIndex(1);
        });

        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Serif", Font.BOLD, 24));
        quitBtn.addActionListener((ActionEvent ae) ->
        {
            int selectedOption = JOptionPane.showConfirmDialog(null, "Do you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        });

        controlPnl.add(orderBtn);
        controlPnl.add(clearBtn);
        controlPnl.add(quitBtn);

    }

    private void CreateDisplayPanel()
    {
        displayPnl = new JPanel();
        displayPnl.setLayout(new GridLayout(1, 1));
        displayPnl.setBorder(new TitledBorder(new EtchedBorder(), "Receipt"));
        displayTA = new JTextArea(10,15);
        displayTA.setEditable(false);
        displayTA.setFont(new Font("Serif", Font.PLAIN, 22));
        scroller = new JScrollPane(displayTA);
        displayPnl.add(scroller);
    }

    private void createOptionsPanel()
    {
        optionsPnl = new JPanel();
        optionsPnl.setLayout(new BorderLayout());

        CreateCrustPanel();
        optionsPnl.add(crustPnl, BorderLayout.WEST);

        CreateSizePanel();
        optionsPnl.add(sizePnl, BorderLayout.CENTER);

        CreateToppingPanel();
        optionsPnl.add(toppingPnl, BorderLayout.EAST);

    }

    private void CreateToppingPanel()
    {
        toppingPnl = new JPanel();
        toppingPnl.setLayout(new GridLayout(3,2));
        toppingPnl.setBorder(new TitledBorder(new EtchedBorder(), "Toppings"));

        ectoChkBx = new JCheckBox("Ectoplasm");
        ectoChkBx.setFont(new Font("Serif", Font.PLAIN, 20));
        ashChkBx = new JCheckBox("Demon Ash");
        ashChkBx.setFont(new Font("Serif", Font.PLAIN, 20));
        boneChkBx = new JCheckBox("Bone Shards");
        boneChkBx.setFont(new Font("Serif", Font.PLAIN, 20));
        ghoulChkBx = new JCheckBox("Ghoul Bacon");
        ghoulChkBx.setFont(new Font("Serif", Font.PLAIN, 20));
        manChkBx = new JCheckBox("Man Sausage");
        manChkBx.setFont(new Font("Serif", Font.PLAIN, 20));
        frogChkBx = new JCheckBox("Frogmen Legs");
        frogChkBx.setFont(new Font("Serif", Font.PLAIN, 20));

        toppingPnl.add(ectoChkBx);
        toppingPnl.add(ashChkBx);
        toppingPnl.add(boneChkBx);
        toppingPnl.add(ghoulChkBx);
        toppingPnl.add(manChkBx);
        toppingPnl.add(frogChkBx);
    }

    private void CreateSizePanel()
    {
        sizePnl = new JPanel();
        sizePnl.setBorder(new TitledBorder(new EtchedBorder(), "Size"));
        sizeCmbBx = new JComboBox();
        sizeCmbBx.addItem("Small");
        sizeCmbBx.addItem("Medium");
        sizeCmbBx.addItem("Large");
        sizeCmbBx.addItem("Super");
        sizeCmbBx.setSelectedIndex(1);
        sizeCmbBx.setFont(new Font("Serif", Font.PLAIN, 20));
        sizeCmbBx.setAlignmentX(Component.CENTER_ALIGNMENT);
        sizeCmbBx.setAlignmentY(Component.CENTER_ALIGNMENT);
        sizePnl.add(sizeCmbBx);
    }

    private void CreateCrustPanel()
    {
        crustPnl = new JPanel();
        crustPnl.setLayout(new GridLayout(3,1));
        crustPnl.setBorder(new TitledBorder(new EtchedBorder(), "Crust Type"));

        thinRB = new JRadioButton("Thin");
        thinRB.setFont(new Font("Serif", Font.PLAIN, 20));
        regularRB = new JRadioButton("Regular");
        regularRB.setFont(new Font("Serif", Font.PLAIN, 20));
        deepDishRB = new JRadioButton("Deep-Dish");
        deepDishRB.setFont(new Font("Serif", Font.PLAIN, 20));

        crustBtnGroup = new ButtonGroup();
        crustBtnGroup.add(thinRB);
        crustBtnGroup.add(regularRB);
        crustBtnGroup.add(deepDishRB);
        regularRB.setSelected(true);

        crustPnl.add(thinRB);
        crustPnl.add(regularRB);
        crustPnl.add(deepDishRB);

    }

}
