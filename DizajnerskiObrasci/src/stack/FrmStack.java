package stack;

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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class FrmStack extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<Rectangle> dlmStack = new DefaultListModel<Rectangle>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStack frame = new FrmStack();
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
	public FrmStack() {
		setTitle("LazicDijanaIT19/2020");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCenter = new GridBagLayout();
		gbl_pnlCenter.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_pnlCenter.rowHeights = new int[]{0, 0, 0};
		gbl_pnlCenter.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlCenter.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		pnlCenter.setLayout(gbl_pnlCenter);
		
		JScrollPane ScrollPaneStack = new JScrollPane();
		GridBagConstraints gbc_ScrollPaneStack = new GridBagConstraints();
		gbc_ScrollPaneStack.fill = GridBagConstraints.BOTH;
		gbc_ScrollPaneStack.gridx = 4;
		gbc_ScrollPaneStack.gridy = 1;
		pnlCenter.add(ScrollPaneStack, gbc_ScrollPaneStack);
		
		JList listStack = new JList();
		ScrollPaneStack.setViewportView(listStack);
		listStack.setModel(dlmStack);
		
		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		GridBagLayout gbl_pnlNorth = new GridBagLayout();
		pnlNorth.setBackground(Color.MAGENTA);
		gbl_pnlNorth.columnWidths = new int[]{0, 0};
		gbl_pnlNorth.rowHeights = new int[]{0, 0};
		gbl_pnlNorth.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_pnlNorth.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnlNorth.setLayout(gbl_pnlNorth);
		
		JLabel lblStack = new JLabel("Stack");
		GridBagConstraints gbc_lblStack = new GridBagConstraints();
		gbc_lblStack.gridwidth = 0;
		gbc_lblStack.gridheight = 0;
		gbc_lblStack.gridx = 0;
		gbc_lblStack.gridy = 0;
		pnlNorth.add(lblStack, gbc_lblStack);
		
		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		pnlSouth.setBackground(Color.MAGENTA);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setForeground(Color.MAGENTA);
		btnAdd.setBackground(Color.BLACK);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgAdd dlgAdd = new DlgAdd(); //dijalog za Add
				dlgAdd.setVisible(true);
			
					if(dlgAdd.isOk())
					{
						try 
						{
							int x = Integer.parseInt(dlgAdd.getTxtX().getText());
							int y = Integer.parseInt(dlgAdd.getTxtY().getText());
							int width = Integer.parseInt(dlgAdd.getTxtWidth().getText());
							int height = Integer.parseInt(dlgAdd.getTxtHeight().getText());
							
							Rectangle r = new Rectangle(new Point(x,y),width,height);
							
							dlmStack.add(0,r);
						} 
						catch(Exception NumberFormatException)
						{
							JOptionPane.showMessageDialog(null, "You didn't enter a number!");
						}
					}
				}});
		pnlSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlSouth.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.MAGENTA);
		btnDelete.setBackground(Color.BLACK);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if (dlmStack.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "List is empty!");
				}
				else
				{
					DlgAdd dlgAdd = new DlgAdd(); //povezivanje dijaloga
					Point p = dlmStack.getElementAt(0).getUpperLeftPoint(); //uzimanje vrijednosti na 0 poziciji
					int width = dlmStack.getElementAt(0).getWidth();
					int height = dlmStack.getElementAt(0).getHeight();
					
					dlgAdd.getTxtX().setText(Integer.toString(p.getX())); //stavlja posljednji napisan
					dlgAdd.getTxtY().setText(Integer.toString(p.getY()));
					dlgAdd.getTxtWidth().setText(Integer.toString(width));
					dlgAdd.getTxtHeight().setText(Integer.toString(height));
					dlgAdd.setVisible(true);
					
					if (dlgAdd.isOk())
					{
						dlmStack.removeElementAt(0); //brise
					}
				
			}
		}});
		pnlSouth.add(btnDelete);}
	
			}
		
	
	


