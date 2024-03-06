package sort;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class FrmSort extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
    protected DefaultListModel<Rectangle> dlm=new DefaultListModel<Rectangle>();
    protected ArrayList<Rectangle> listRect = new ArrayList<Rectangle>();
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSort frame = new FrmSort();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmSort() {
		setTitle("Lazic Dijana IT19/2020");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCenter = new GridBagLayout();
		gbl_pnlCenter.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlCenter.rowHeights = new int[]{0, 0, 0, 0};
		gbl_pnlCenter.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlCenter.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		pnlCenter.setLayout(gbl_pnlCenter);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 7;
		gbc_scrollPane.gridy = 2;
		pnlCenter.add(scrollPane, gbc_scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		list.setModel(dlm);   //povezivanje dlm sa listom !!!!!
		
		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		GridBagLayout gbl_pnlNorth = new GridBagLayout();
		gbl_pnlNorth.columnWidths = new int[]{0, 0};
		gbl_pnlNorth.rowHeights = new int[]{0, 0};
		gbl_pnlNorth.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_pnlNorth.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnlNorth.setLayout(gbl_pnlNorth);
		
		JLabel lblSort = new JLabel("SORT");
		lblSort.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblSort = new GridBagConstraints();
		gbc_lblSort.gridwidth = 0;
		gbc_lblSort.gridheight = 0;
		gbc_lblSort.gridx = 0;
		gbc_lblSort.gridy = 0;
		pnlNorth.add(lblSort, gbc_lblSort);
		pnlNorth.setBackground(Color.CYAN);
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(Color.CYAN);
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		                             
                                      // ADD //
		
		JToggleButton tglbtnAdd = new JToggleButton("Add");
		tglbtnAdd.setForeground(Color.CYAN);
		tglbtnAdd.setBackground(Color.BLACK);
		tglbtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgAdd dlgAddRect = new DlgAdd();
				dlgAddRect.setVisible(true);
				
				if(dlgAddRect.isOk()) { 
				// dlm.addElement(dlgAddRect.getTxtX().getText()+" "+dlgAddRect.getTxtY().getText()+" "+dlgAddRect.getTxtWidth().getText()+" "+dlgAddRect.getTxtHeight().getText());
					try {
						int x = Integer.parseInt(dlgAddRect.getTxtX().getText());
						int y = Integer.parseInt(dlgAddRect.getTxtY().getText());
						int height = Integer.parseInt(dlgAddRect.getTxtHeight().getText());
						int width = Integer.parseInt(dlgAddRect.getTxtWidth().getText());
						Rectangle r = new Rectangle(new Point(x,y), width, height);
						dlm.addElement(r);// 0 dodajemo na pocetak liste-na prvo mesto
						listRect.add(0, r);
				
					}
					catch (Exception numberFormatException) {
						JOptionPane.showMessageDialog(null, "You didn't enter correct number");
					}
				}
			}
		});
		pnlSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonGroup.add(tglbtnAdd);
		pnlSouth.add(tglbtnAdd);
		
		                                // SORT //
		
		JToggleButton tglbtnSort = new JToggleButton("Sort");
		tglbtnSort.setForeground(Color.CYAN);
		tglbtnSort.setBackground(Color.BLACK);
		tglbtnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dlm.isEmpty()){
					JOptionPane.showMessageDialog(null, "List is empty!");
				}
				else {
					listRect.sort(null); //sortiranje liste
					dlm.clear(); //da se ocisti sve sto je pisalo do tada u jListu
					for(int k=0; k<listRect.size();k++) //prolazi se kroz celu listu i ispisuje se od najmanje do najvece
					{
						dlm.addElement(listRect.get(k));
					}
				}
				
				
			}
			
		});
		
		buttonGroup.add(tglbtnSort);
		pnlSouth.add(tglbtnSort);
	}

}