package mvc;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import command.Command;
import drawing.PnlDrawing;
import geometry.Shape;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;

public class DrawingFrame extends JFrame {
	
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public DefaultListModel<Command> dlmCommand=new DefaultListModel<Command>(); 
	

	public DefaultListModel<Command> getDlm() {
		return dlmCommand;
	}
	
	private DefaultListModel<String> dlmString=new DefaultListModel<>();

	private DrawingController controller;
	private DrawingView view= new DrawingView();;
	
	//public  Color shapeColor;
	private Color fillColor = Color.WHITE;


//-------------------------Buttons--------------------------------------------------------------------------------------
	protected JToggleButton tglBtnPoint = new JToggleButton("Point");
	protected JToggleButton tglBtnLine = new JToggleButton("Line");
	protected JToggleButton tglBtnRectangle = new JToggleButton("Rectangle");
	protected JToggleButton tglBtnCircle = new JToggleButton("Circle");
	protected JToggleButton tglBtnDonut = new JToggleButton("Donut");
	protected JToggleButton tglBtnHexagon = new JToggleButton("Hexagon");
	protected JToggleButton btnSelect = new JToggleButton("Select");

	protected JButton btnDelete = new JButton("Delete");
	protected JButton btnModify = new JButton("Modify");
	private JTextField txtBorderColor;
	private JTextField txtInnerColor;
	
	public Color borderFill;
	public Color innerFill;
	private final JButton btnToBack = new JButton("To Back");
	private final JButton btnToFront = new JButton("ToFront");
	private final JButton btnBringToBack = new JButton("BringToBack");
	private final JButton btnBringToFront = new JButton("BringToFront");
	private final JButton btnUndo = new JButton("Undo");
	private final JButton btnRedo = new JButton("Redo");
	private final JPanel panel = new JPanel();
	private final JButton btnSave = new JButton("Save");
	private final JButton btnLoad = new JButton("Load");
	private final JPanel panel_1 = new JPanel();
	private final JButton btnLoadStep = new JButton("Load Step");
	private JList listString = new JList(dlmString);

	
	
	public DrawingFrame() {
		
			setTitle("Lazic Dijana IT19-2020");
		
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 783, 516);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			
//*********************North-Shapes buttons****************************************************************//
			
			 JPanel pnlNorth = new JPanel();
				pnlNorth.setBackground(Color.red);
				contentPane.add(pnlNorth, BorderLayout.NORTH);
				
				buttonGroup.add(tglBtnPoint);
				pnlNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				tglBtnPoint.setBackground(Color.LIGHT_GRAY);
				pnlNorth.add(tglBtnPoint);
				
		
				buttonGroup.add(tglBtnLine);
				tglBtnLine.setBackground(Color.LIGHT_GRAY);
				pnlNorth.add(tglBtnLine);
				
				
				buttonGroup.add(tglBtnRectangle);
				tglBtnRectangle.setBackground(Color.LIGHT_GRAY);
				pnlNorth.add(tglBtnRectangle);
				
				
				buttonGroup.add(tglBtnCircle);
				tglBtnCircle.setBackground(Color.LIGHT_GRAY);
				pnlNorth.add(tglBtnCircle);
				
				
				buttonGroup.add(tglBtnDonut);
				tglBtnDonut.setBackground(Color.LIGHT_GRAY);
				pnlNorth.add(tglBtnDonut);
				
				pnlNorth.add(tglBtnHexagon);
				buttonGroup.add(tglBtnHexagon);
				tglBtnHexagon.setBackground(Color.LIGHT_GRAY);
				
				buttonGroup.add(btnSelect);
		
//*********************************West-Log*****************************************************************
				
				JPanel pnlWest = new JPanel();
				pnlWest.setForeground(Color.GRAY);
				pnlWest.setBackground(Color.LIGHT_GRAY);
				contentPane.add(pnlWest, BorderLayout.WEST);
				GridBagLayout gbl_pnlWest = new GridBagLayout();
				gbl_pnlWest.columnWidths = new int[]{258, 0};
				gbl_pnlWest.rowHeights = new int[]{130, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
				gbl_pnlWest.columnWeights = new double[]{1.0, Double.MIN_VALUE};
				gbl_pnlWest.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
				pnlWest.setLayout(gbl_pnlWest);
				
				JScrollPane scrllPane = new JScrollPane();
				GridBagConstraints gbc_scrllPane = new GridBagConstraints();
				gbc_scrllPane.insets = new Insets(0, 0, 5, 0);
				gbc_scrllPane.anchor = GridBagConstraints.NORTHWEST;
				gbc_scrllPane.gridx = 0;
				gbc_scrllPane.gridy = 0;
				pnlWest.add(scrllPane, gbc_scrllPane);
				
				
				//listString.setModel(dlmString);
				scrllPane.setViewportView(listString);
				
				GridBagConstraints gbc_panel_1 = new GridBagConstraints();
				gbc_panel_1.gridheight = 6;
				gbc_panel_1.insets = new Insets(0, 0, 5, 0);
				gbc_panel_1.fill = GridBagConstraints.BOTH;
				gbc_panel_1.gridx = 0;
				gbc_panel_1.gridy = 4;
				panel_1.setBackground(Color.LIGHT_GRAY);
				pnlWest.add(panel_1, gbc_panel_1);
				panel_1.add(btnUndo);
				btnUndo.setBackground(Color.RED);
				btnUndo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.undo();
					}
				});
				btnUndo.setForeground(Color.BLACK);
				btnUndo.setEnabled(false);
				panel_1.add(btnRedo);
				btnRedo.setBackground(Color.RED);
				btnRedo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.redo();
					}
				});
				btnRedo.setVerticalAlignment(SwingConstants.TOP);
				btnRedo.setEnabled(false);
				
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.gridheight = 2;
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = 0;
				gbc_panel.gridy = 10;
				panel.setBackground(Color.LIGHT_GRAY);
				pnlWest.add(panel, gbc_panel);
				btnLoad.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Object[] choice = { "Load Log", "Load Drawing"};
						int optionChosen=JOptionPane.showOptionDialog(null, "Choose What To Save!", "Load File!",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, choice, choice[0]);
						controller.load(optionChosen);
					}
				});
				
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Object[] choice = { "Save Log", "Save Drawing"};
						int optionChosen=JOptionPane.showOptionDialog(null, "Choose What To Save!", "Save File!",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, choice, choice[0]);
						controller.save(optionChosen);
					}
				});
				btnSave.setBackground(new Color(152, 251, 152));
				
				panel.add(btnSave);
				btnLoad.setBackground(new Color(152, 251, 152));
				panel.add(btnLoad);
				btnLoadStep.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.loadStep();
					}
				});
				btnLoadStep.setBackground(new Color(152, 251, 152));
				btnLoadStep.setEnabled(false);
				panel.add(btnLoadStep);
		
//************************South-Modify/Delete*****************************************************************************		

				JPanel pnlSouth = new JPanel();
				pnlSouth.setBackground(Color.red);
				contentPane.add(pnlSouth, BorderLayout.SOUTH);
				
				
				btnModify.setEnabled(false);
				btnModify.setBackground(Color.LIGHT_GRAY);
				btnModify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.modifyShape();
						
					}
				});
				
				pnlSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				btnSelect.setBackground(Color.LIGHT_GRAY);
				pnlSouth.add(btnSelect);
				pnlSouth.add(btnModify);
				
				btnDelete.setEnabled(false);
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.delete();
					
					}
				});
				btnDelete.setBackground(Color.LIGHT_GRAY);
						pnlSouth.add(btnDelete);	
				
			
//----------------------East--colors---------------------------------------------------------------------------------//
						JPanel pnlEast = new JPanel();
						contentPane.add(pnlEast, BorderLayout.EAST);
						GridBagLayout gbl_pnlEast = new GridBagLayout();
						gbl_pnlEast.columnWidths = new int[]{57, 0};
						gbl_pnlEast.rowHeights = new int[]{21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
						gbl_pnlEast.columnWeights = new double[]{1.0, Double.MIN_VALUE};
						gbl_pnlEast.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
						pnlEast.setLayout(gbl_pnlEast);
						
						JButton btnBorderColor = new JButton("Border color");
						btnBorderColor.setBackground(Color.LIGHT_GRAY);
						borderFill=Color.BLACK;

						//txtBorderColor.setBackground(Color.BLACK);
						btnBorderColor.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(borderFill==null) {
									borderFill=Color.BLACK;
								}else {
								 borderFill = JColorChooser.showDialog(null, "Choose a border color", borderFill);
								 txtBorderColor.setBackground(borderFill);
								}
								/*if(shapeColor==null) {
									shapeColor=Color.BLACK;
								}else {
									shapeColor=JColorChooser.showDialog(null, "Color--BORDER--Color", shapeColor);
									txtBorderColor.setBackground(shapeColor);
								}*/
							}
								
						});
						GridBagConstraints gbc_btnBorderColor = new GridBagConstraints();
						gbc_btnBorderColor.insets = new Insets(0, 0, 5, 0);
						gbc_btnBorderColor.gridx = 0;
						gbc_btnBorderColor.gridy = 0;
						pnlEast.add(btnBorderColor, gbc_btnBorderColor);
						
						txtBorderColor = new JTextField();
						txtBorderColor.setForeground(Color.BLACK);
						txtBorderColor.setBackground(Color.BLACK);

						txtBorderColor.setHorizontalAlignment(SwingConstants.CENTER);
						txtBorderColor.setEnabled(false);
						txtBorderColor.setEditable(false);
						txtBorderColor.setText("");
						txtBorderColor.setBackground(borderFill);
						GridBagConstraints gbc_txtBorderColor = new GridBagConstraints();
						gbc_txtBorderColor.insets = new Insets(0, 0, 5, 0);
						gbc_txtBorderColor.fill = GridBagConstraints.HORIZONTAL;
						gbc_txtBorderColor.gridx = 0;
						gbc_txtBorderColor.gridy = 1;
						pnlEast.add(txtBorderColor, gbc_txtBorderColor);
						txtBorderColor.setColumns(10);
						
						JButton btnInnerColor = new JButton("Inner  color");
						btnInnerColor.setBackground(Color.LIGHT_GRAY);
						innerFill=Color.WHITE;
						//txtInnerColor.setBackground(Color.WHITE);
						btnInnerColor.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(innerFill==null) {
									innerFill=Color.WHITE;
								}else {
								innerFill=JColorChooser.showDialog(null, "Choose inner color", innerFill);
								txtInnerColor.setBackground(innerFill);
								}
							}
						});
						GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
						gbc_btnInnerColor.insets = new Insets(0, 0, 5, 0);
						gbc_btnInnerColor.gridx = 0;
						gbc_btnInnerColor.gridy = 2;
						pnlEast.add(btnInnerColor, gbc_btnInnerColor);
						
						txtInnerColor = new JTextField();
						txtInnerColor.setHorizontalAlignment(SwingConstants.CENTER);
						txtInnerColor.setEnabled(false);
						txtInnerColor.setEditable(false);
						txtInnerColor.setText("");
						txtInnerColor.setBackground(innerFill);
						GridBagConstraints gbc_txtInnerColor = new GridBagConstraints();
						gbc_txtInnerColor.insets = new Insets(0, 0, 5, 0);
						gbc_txtInnerColor.fill = GridBagConstraints.HORIZONTAL;
						gbc_txtInnerColor.gridx = 0;
						gbc_txtInnerColor.gridy = 3;
						pnlEast.add(txtInnerColor, gbc_txtInnerColor);
						txtInnerColor.setColumns(10);
						
						btnToBack.setEnabled(false);
						GridBagConstraints gbc_btnToBack = new GridBagConstraints();
						gbc_btnToBack.insets = new Insets(0, 0, 5, 0);
						gbc_btnToBack.gridx = 0;
						gbc_btnToBack.gridy = 5;
						btnToBack.setBackground(Color.LIGHT_GRAY);
						btnToBack.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								controller.toBack();
							}
						});
						pnlEast.add(btnToBack, gbc_btnToBack);
						
						btnToFront.setEnabled(false);
						GridBagConstraints gbc_btnToFront = new GridBagConstraints();
						gbc_btnToFront.insets = new Insets(0, 0, 5, 0);
						gbc_btnToFront.gridx = 0;
						gbc_btnToFront.gridy = 6;
						btnToFront.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								controller.toFront();
							}
						});
						btnToFront.setBackground(Color.LIGHT_GRAY);
						pnlEast.add(btnToFront, gbc_btnToFront);
						
						GridBagConstraints gbc_btnBringToBack = new GridBagConstraints();
						gbc_btnBringToBack.insets = new Insets(0, 0, 5, 0);
						gbc_btnBringToBack.gridx = 0;
						gbc_btnBringToBack.gridy = 7;
						btnBringToBack.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								controller.bringToBack();
								
							}
						});
						btnBringToBack.setEnabled(false);
						btnBringToBack.setBackground(Color.LIGHT_GRAY);
						pnlEast.add(btnBringToBack, gbc_btnBringToBack);
						
						GridBagConstraints gbc_btnBringToFront = new GridBagConstraints();
						gbc_btnBringToFront.insets = new Insets(0, 0, 5, 0);
						gbc_btnBringToFront.gridx = 0;
						gbc_btnBringToFront.gridy = 8;
						btnBringToFront.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								controller.bringToFront();
								
							}
						});
						btnBringToFront.setEnabled(false);
						btnBringToFront.setBackground(Color.LIGHT_GRAY);
						pnlEast.add(btnBringToFront, gbc_btnBringToFront);
						
						
				        JPanel pnlCenter = new JPanel();
						contentPane.add(pnlCenter, BorderLayout.CENTER);
						
				
		
		
		contentPane.add(view, BorderLayout.CENTER);
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.mouseClicked(e);
			}
		});
		
		
	}


	public DrawingView getView() {
		return view;
	}


	public void setDrawingController(DrawingController drawingController) {
		this.controller = drawingController;
	}
	public JButton getBtnUndo() {
		return btnUndo;
	}


	public JButton getBtnRedo() {
		return btnRedo;
	}


	public JButton getBtnDelete() {
		return btnDelete;
	}


	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}


	public JButton getBtnModify() {
		return btnModify;
	}


	public void setBtnModify(JButton btnModify) {
		this.btnModify = btnModify;
	}


      public DefaultListModel<String> getDlmString() {
		return dlmString;
	}


	public void setDlmString(DefaultListModel<String> dlmString) {
		this.dlmString = dlmString;
	}


	public JList getListString() {
		return listString;
	}


	public void setListString(JList listString) {
		this.listString = listString;
	}


	public Color getBorderFill() {
		return borderFill;
	}


	public void setBorderFill(Color borderFill) {
		this.borderFill = borderFill;
	}


	public Color getInnerFill() {
		return innerFill;
	}


	public void setInnerFill(Color innerFill) {
		this.innerFill = innerFill;
	}


	public JButton getBtnToBack() {
		return btnToBack;
	}


	public JButton getBtnToFront() {
		return btnToFront;
	}


	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}


	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}


	public JButton getBtnSave() {
		return btnSave;
	}


	public JButton getBtnLoad() {
		return btnLoad;
	}


	public JButton getBtnLoadStep() {
		return btnLoadStep;
	}


	/*public Color getShapeColor() {
		return shapeColor;
	}


	public void setShapeColor(Color shapeColor) {
		this.shapeColor = shapeColor;
	}*/





	
	

		
	}


