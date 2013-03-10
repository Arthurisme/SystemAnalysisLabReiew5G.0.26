package SystemAnalysisLabReview5;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 *
 * @author ContEd Student
 */
public class PayRollSystem extends JFrame implements
        ActionListener{

    /**
     * @param args the command line arguments
     */
    private JLabel EmployeeIdL,EmployeeLastNameL,EmployeeFirstNameL, EmployeeSSNL, NumberWorkedHourL, HourlyRateL, TotalIncomeL, NetIncomeL
    				,EmployeePositionIdL,EmployeeQualificationIdL,EmployeeDepartmentIdL,EmployeeHireDateL;
    

    private JTextField EmployeeLastNameTF,EmployeeFirstNameTF,EmployeeSSNTF,NumberWorkedHourTF,HourlyRateTF,TotalIncomeTF,NetIncomeTF,EmployeeIdTF
    				,EmployeePositionIdTF,EmployeeQualificationIdTF,EmployeeDepartmentIdTF,EmployeeHireDateTF;

    private JButton calculateB, exitB;

    private calculateButtonHandler calculateHandler;
    private ExitButtonHandler ebHandler;

    private JMenuBar menuMB =
            new JMenuBar(); //create the menu bar
    private JMenu InputDataM, OutputDataM;
    private JMenuItem EmployeeI, DepartmentI, PositionI, QualificationI, ItemsPayI;
    private JMenuItem OutputPayStubI, ListAllEmployeePayStubI;
    EmployeeSalary salary=new EmployeeSalary();

    java.text.DecimalFormat decimal2Places_format=new java.text.DecimalFormat("0.00");

    private static final int WIDTH =600;
    private static final int HEIGHT = 600;
    private String sql="",temp;
    private JMenuItem mntmNewMenuItem;

    public PayRollSystem(){
//        double d5=19.00;
//        System.out.println(String.format("%.2f",d5));
//        BigDecimal a = new BigDecimal(19.5001);
//        double t1=a.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
//        System.out.println("a.setScale(2,2)=" + a.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
//        java.text.DecimalFormat myformat=new java.text.DecimalFormat("0.00");
//        System.out.println("a.setScale(2,2)=" + myformat.format(t1));
    	EmployeeIdL = new JLabel("Enter Employee's ID: ",SwingConstants.RIGHT);
    	EmployeeFirstNameL = new JLabel("Enter Employee's First Name: ",SwingConstants.RIGHT);
    	EmployeeLastNameL = new JLabel("Enter Employee's Last Name: ",SwingConstants.RIGHT);
        EmployeeSSNL = new JLabel("Enter Employee's SSN: ",SwingConstants.RIGHT);
        EmployeePositionIdL= new JLabel("Enter Employee's Position ID: ",SwingConstants.RIGHT);
        EmployeeQualificationIdL= new JLabel("Enter Employee's Qualification Id: ",SwingConstants.RIGHT);
        EmployeeDepartmentIdL= new JLabel("Enter Employee's Department ID: ",SwingConstants.RIGHT);
        EmployeeHireDateL=new JLabel("Enter Employee's Hire Date: ",SwingConstants.RIGHT);
        NumberWorkedHourL = new JLabel("Enter Number of Worked Hour: ",SwingConstants.RIGHT);
        HourlyRateL = new JLabel("Enter the Rate Hour: ",SwingConstants.RIGHT);
        TotalIncomeL = new JLabel("Total Income: ",SwingConstants.RIGHT);
        NetIncomeL = new JLabel("Net Amount: ",SwingConstants.RIGHT);
        
         //Create the four text fields
        EmployeeIdTF=new JTextField(10);
        EmployeeIdTF.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent arg0) {
        		getEmployeeInfo();
        	}
        });
        EmployeeFirstNameTF = new JTextField(10);
        EmployeeLastNameTF = new JTextField(10);
        EmployeeSSNTF = new JTextField(10);
        EmployeePositionIdTF= new JTextField(10);
        EmployeeQualificationIdTF= new JTextField(10);
        EmployeeDepartmentIdTF= new JTextField(10);
        EmployeeHireDateTF=new JTextField(10);
        NumberWorkedHourTF = new JTextField(10);
        HourlyRateTF = new JTextField(10);
        TotalIncomeTF = new JTextField(10);
        NetIncomeTF = new JTextField(10);

       
        setJMenuBar(menuMB);
        setInputDataMenu();
        setOutputDataMenu();


             //Create Calculate Button
        calculateB = new JButton("Calculate");
        calculateHandler = new calculateButtonHandler();
        calculateB.addActionListener(calculateHandler);

             //Create Exit Button
        exitB = new JButton("Exit");
        ebHandler = new ExitButtonHandler();
        exitB.addActionListener(ebHandler);

             //Set the title of the window
        setTitle("Pay Roll Application");

                 //Get the container
        Container pane = getContentPane();

                 //Set the layout
        pane.setLayout(new GridLayout(13, 2));

             //Place the components in the pane
      pane.add(EmployeeIdL);
      pane.add(EmployeeIdTF);
      pane.add(EmployeeFirstNameL);
      pane.add(EmployeeFirstNameTF);
      pane.add(EmployeeLastNameL);
      pane.add(EmployeeLastNameTF);
      pane.add(EmployeeSSNL);
      pane.add(EmployeeSSNTF);
      pane.add(EmployeePositionIdL);
      pane.add(EmployeePositionIdTF);
      pane.add(EmployeeQualificationIdL);
      pane.add(EmployeeQualificationIdTF);
      pane.add(EmployeeDepartmentIdL);
      pane.add(EmployeeDepartmentIdTF);
      pane.add(EmployeeHireDateL);
      pane.add(EmployeeHireDateTF);
      pane.add(NumberWorkedHourL);
      pane.add(NumberWorkedHourTF);
      pane.add(HourlyRateL);
      pane.add(HourlyRateTF);
      pane.add(TotalIncomeL);
      pane.add(TotalIncomeTF);
      pane.add(NetIncomeL);
      pane.add(NetIncomeTF);
      pane.add(calculateB);
      pane.add(exitB);

             //Set the size of the window and display it
      setSize(WIDTH, HEIGHT);
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("OutputPayStub")){
/*            JOptionPane.showMessageDialog(null, "===========================================================\n" +
                    "|      The Total Earning is " +decimal2Places_format.format(salary.get_TotalIncome())  + "$|\n" +
                    "|      The Fed_Tax Deduction is " + decimal2Places_format.format(salary.get_FedTaxDeduction()) + "$|\n" +
                    "|      The Prv_Tax Deduction is " + decimal2Places_format.format(salary.get_ProvicalTaxDeduction()) + "$|\n" +
                    "|      The QP_Ins Deduction is " + decimal2Places_format.format(salary.get_QpipDeduction()) + "$|\n" +
                    "|      The E_Ins deduction is " + decimal2Places_format.format(salary.get_EI_Deduction()) + "$|\n" +
                    "|      The Qpp deduction is " + decimal2Places_format.format(salary.get_QppDeduction()) + "$|\n" +
                    "|      The Union_d deduction is " + decimal2Places_format.format(salary.get_UnionFee_Deduction()) + "$|\n" +
                    "|      The Total deduction is " + decimal2Places_format.format(salary.get_TotalDeduction()) + "$|\n" +
                    "\n" +
                    "|      The Total Net Amount is " + decimal2Places_format.format(salary.get_NetIncome()) + "$\n" +
                    "===========================================================\n",
                    "OutputPayStub", JOptionPane.PLAIN_MESSAGE);*/
        	temp="===========================================================\n";
        	ArrayList<ItemBean> deductionList=salary.getDeductionList();
        	for(int i=0;i<deductionList.size();i++){
        		temp=temp+"|      "+deductionList.get(i).getItemPay_title()+" is "
        				+decimal2Places_format.format(deductionList.get(i).getItemPay_amount()) + "$|\n";
        	}
        	temp=temp+"|      The Total deduction is " + decimal2Places_format.format(salary.get_TotalDeduction()) + "$|\n";
        	temp=temp+"===========================================================\n";
            JOptionPane.showMessageDialog(null, temp,
                    "OutputPayStub", JOptionPane.PLAIN_MESSAGE);
            
        }
        
        if(e.getActionCommand().equals("Employee")){
        	EmployeeForm eForm1=new EmployeeForm();
        	eForm1.setVisible(true);
        }
        
        if(e.getActionCommand().equals("Department")){
        	DepartmentForm DepartmentForm1=new DepartmentForm();
        	DepartmentForm1.setVisible(true);
        }
        
        if(e.getActionCommand().equals("Qualification")){
        	QualificationForm QualificationForm1=new QualificationForm();
        	QualificationForm1.setVisible(true);
        }
        
        if(e.getActionCommand().equals("Position")){
        	PositionForm PositionForm1=new PositionForm();
        	PositionForm1.setVisible(true);
        }
        
        if(e.getActionCommand().equals("Items Pay")){
        	ItemForm ItemForm1=new ItemForm();
        	ItemForm1.setVisible(true);
        }
      
        
    }

    private void setInputDataMenu() {
        InputDataM = new JMenu("InputData");
        menuMB.add(InputDataM);
        EmployeeI = new JMenuItem("Employee");
        InputDataM.add(EmployeeI);
        EmployeeI.addActionListener(this);

        DepartmentI = new JMenuItem("Department");
        InputDataM.add(DepartmentI);
        DepartmentI.addActionListener(this);
        PositionI = new JMenuItem("Position");
        InputDataM.add(PositionI);
        PositionI.addActionListener(this);

        QualificationI = new JMenuItem("Qualification");
        InputDataM.add(QualificationI);
        QualificationI.addActionListener(this);

        ItemsPayI = new JMenuItem("Items Pay");
        InputDataM.add(ItemsPayI);
        ItemsPayI.addActionListener(this);
    }

    private void setOutputDataMenu() {// private JMenuItem OutputPayStubI, ListAllEmployeePayStubI;
        OutputDataM = new JMenu("OutputData");
        menuMB.add(OutputDataM);
        OutputPayStubI = new JMenuItem("OutputPayStub");
        OutputDataM.add(OutputPayStubI);
        OutputPayStubI.addActionListener(this);
        
        mntmNewMenuItem = new JMenuItem("ListAllEmployees");
        OutputDataM.add(mntmNewMenuItem);
        ListAllEmployeePayStubI = new JMenuItem("ListAllEmployeePayStub");
        OutputDataM.add(ListAllEmployeePayStubI);
        ListAllEmployeePayStubI.addActionListener(this);

    }

    private class calculateButtonHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         

         try{
            //create a instance of EmployeeInfoBean
        	EmployeeInfoBean employeeInfoBean1=new EmployeeInfoBean();
        	employeeInfoBean1.setEmp_id(Integer.parseInt(EmployeeIdTF.getText().trim()));
        	employeeInfoBean1.setEmp_fname(EmployeeFirstNameTF.getText());
        	employeeInfoBean1.setEmp_lname(EmployeeLastNameTF.getText());
        	employeeInfoBean1.setEmp_Ssn(EmployeeSSNTF.getText());
        	employeeInfoBean1.setEmp_position(Integer.parseInt(EmployeePositionIdTF.getText()));
        	employeeInfoBean1.setEmp_QualId(Integer.parseInt(EmployeeQualificationIdTF.getText()));
        	employeeInfoBean1.setD_id(Integer.parseInt(EmployeeDepartmentIdTF.getText()));
        	employeeInfoBean1.setEmp_hireDate(EmployeeHireDateTF.getText());
        	
            salary.set_EmployeeInfo(employeeInfoBean1);
            salary.set_HourlyRate(Double.parseDouble(HourlyRateTF.getText()));
            salary.set_NumberWorkedHour(Double.parseDouble(NumberWorkedHourTF.getText()));
            salary.CalculateNetIncome();
            TotalIncomeTF.setText(""+salary.get_TotalIncome()+"$");
            NetIncomeTF.setText(""+salary.get_NetIncome()+"$");
            
            
         }catch(NumberFormatException e1){
             System.out.println("You input the wrong data!");
         }
         
         
         
      }
   }

   private class ExitButtonHandler implements ActionListener
   {
       public void actionPerformed(ActionEvent e)
       {
           System.exit(0);
       }
   }

    public static void main(String[] args) {
        // TODO code application logic here

        PayRollSystem dia1=new PayRollSystem();
       // dia1.setVisible(true);
        
    }
    
    public void getEmployeeInfo(){
    	try{
    		if((EmployeeIdTF.getText().trim()).equals("")){
    			System.out.println("No ID!");
    		}else{
    			sql="";
    			sql="select * from EmployeeInfo where emp_id="+Integer.parseInt(EmployeeIdTF.getText());
    			EmployeeInfoBeanAction eA1=new EmployeeInfoBeanAction();
    			EmployeeInfoBean searchResult=new EmployeeInfoBean();
    			searchResult=eA1.search(sql);
    			if(searchResult==null){
    				EmployeeIdTF.setText("");
    			}else{
    				EmployeeFirstNameTF.setText(searchResult.getEmp_fname());
        			EmployeeHireDateTF.setText(searchResult.getEmp_hireDate());
        			EmployeeLastNameTF.setText(searchResult.getEmp_lname());
        			EmployeeSSNTF.setText(searchResult.getEmp_Ssn());
        			EmployeePositionIdTF.setText(""+searchResult.getEmp_position());
        			EmployeeQualificationIdTF.setText(""+searchResult.getEmp_QualId());
        			EmployeeDepartmentIdTF.setText(""+searchResult.getD_id());
    			}
    			
    		}
    	}catch(Exception e){
    		
    	}
    }

}
