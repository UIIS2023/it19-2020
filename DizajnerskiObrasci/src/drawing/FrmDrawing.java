package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Shape;

import java.awt.GridBagLayout;
import javax.swing.JToggleButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.FlowLayout;

public class FrmDrawing extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	public DefaultListModel<Shape> dlm=new DefaultListModel<Shape>(); 
	int sclick=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
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
	public FrmDrawing() {
		setTitle("Lazic Dijana IT19-2020");
		PnlDrawing pnlDrawing=new PnlDrawing(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	
	//------------------South--------------------------------
		
		JPanel pnlSouth = new JPanel();
		pnlSouth.setBackground(Color.red);
		contentPane.add(pnlSouth, BorderLayout.SOUTH);
		
		JButton btnModify = new JButton("Modify");
		btnModify.setBackground(Color.LIGHT_GRAY);
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.modifyShape();
				
			}
		});
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sclick=6;
			}
		});
		pnlSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnSelect.setBackground(Color.LIGHT_GRAY);
		pnlSouth.add(btnSelect);
		pnlSouth.add(btnModify);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlDrawing.delete();
			
			}
		});
		btnDelete.setBackground(Color.LIGHT_GRAY);
				pnlSouth.add(btnDelete);
		
//-----------------North------------------------------	
	    JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(Color.red);
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		
		JToggleButton tglBtnPoint = new JToggleButton("Point");
		buttonGroup.add(tglBtnPoint);
		tglBtnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sclick = 1;
			}
		});
		pnlNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		tglBtnPoint.setBackground(Color.LIGHT_GRAY);
		pnlNorth.add(tglBtnPoint);
		
		
		JToggleButton tglBtnLine = new JToggleButton("Line");
		buttonGroup.add(tglBtnLine);
		tglBtnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sclick = 2;
			}
		});
		tglBtnLine.setBackground(Color.LIGHT_GRAY);
		pnlNorth.add(tglBtnLine);
		
		JToggleButton tglBtnRectangle = new JToggleButton("Rectangle");
		buttonGroup.add(tglBtnRectangle);
		tglBtnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sclick = 3;
			}
		});
		tglBtnRectangle.setBackground(Color.LIGHT_GRAY);
		pnlNorth.add(tglBtnRectangle);
		
		JToggleButton tglBtnCircle = new JToggleButton("Circle");
		buttonGroup.add(tglBtnCircle);
		tglBtnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sclick = 4;
			}
		});
		tglBtnCircle.setBackground(Color.LIGHT_GRAY);
		pnlNorth.add(tglBtnCircle);
		
		JToggleButton tglBtnDonut = new JToggleButton("Donut");
		buttonGroup.add(tglBtnDonut);
		tglBtnDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sclick = 5;
			}
		});
		tglBtnDonut.setBackground(Color.LIGHT_GRAY);
		pnlNorth.add(tglBtnDonut);
//************************West********************************************************************************************//	
		JPanel pnlWest = new JPanel();
		pnlWest.setForeground(Color.GRAY);
		pnlWest.setBackground(Color.lightGray);
		contentPane.add(pnlWest, BorderLayout.WEST);
		GridBagLayout gbl_pnlWest = new GridBagLayout();
		gbl_pnlWest.columnWidths = new int[] {150};
		gbl_pnlWest.rowHeights = new int[]{2, 0};
		gbl_pnlWest.columnWeights = new double[]{0.0};
		gbl_pnlWest.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnlWest.setLayout(gbl_pnlWest);
		
		JScrollPane scrllPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.anchor = GridBagConstraints.NORTHWEST;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		pnlWest.add(scrllPane, gbc_scrollPane);
		
		JList listShapes = new JList();
		listShapes.setModel(dlm);
		scrllPane.setViewportView(listShapes);
		
//--------------------Center----------------------------
		
		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		pnlDrawing.setSize(new Dimension(20,40));
		pnlDrawing.setPreferredSize(new Dimension(200,400));
		contentPane.add(pnlDrawing);
		
	
	}

	public DefaultListModel<Shape> getDlm() {
		return dlm;
	}

	public void setDlm(DefaultListModel<Shape> dlm) {
		this.dlm = dlm;
	}

	public int getSclick() {
		return sclick;
	}

	public void setSclick(int sclick) {
		this.sclick = sclick;
	}

	/*private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}*/
}
