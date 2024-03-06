package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Rectangle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textupX;
	private JTextField txtupY;
	private JTextField txtHeight;
	private JTextField textWidth;
	private boolean isOk;
	private DefaultListModel<Rectangle> dlm=new DefaultListModel<Rectangle>();
	public Color innerFill;
	public Color borderFill;
	private boolean isInnerColorOk;
	private boolean isBorderColorOk;
	private JButton btnInnerColor = new JButton("Inner Color:");
	private JButton btnBorderColor = new JButton("Border Color:");

	

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}

	public JButton getBtnBorderColor() {
		return btnBorderColor;
	}

	public void setBtnBorderColor(JButton btnBorderColor) {
		this.btnBorderColor = btnBorderColor;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setBounds(100, 100, 450, 300);
		setModal(true);
		setTitle("Add/Modify");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblupperLeftX = new JLabel("UpperLeft Point-X:");
			GridBagConstraints gbc_lblupperLeftX = new GridBagConstraints();
			gbc_lblupperLeftX.insets = new Insets(0, 0, 5, 5);
			gbc_lblupperLeftX.gridx = 1;
			gbc_lblupperLeftX.gridy = 1;
			contentPanel.add(lblupperLeftX, gbc_lblupperLeftX);
		}
		{
			textupX = new JTextField();
			textupX.setEditable(false);
			GridBagConstraints gbc_textupX = new GridBagConstraints();
			gbc_textupX.insets = new Insets(0, 0, 5, 0);
			gbc_textupX.fill = GridBagConstraints.HORIZONTAL;
			gbc_textupX.gridx = 3;
			gbc_textupX.gridy = 1;
			contentPanel.add(textupX, gbc_textupX);
			textupX.setColumns(10);
		}
		{
			JLabel lblupperLeftY = new JLabel("UpperLeft Point-Y:");
			GridBagConstraints gbc_lblupperLeftY = new GridBagConstraints();
			gbc_lblupperLeftY.insets = new Insets(0, 0, 5, 5);
			gbc_lblupperLeftY.gridx = 1;
			gbc_lblupperLeftY.gridy = 3;
			contentPanel.add(lblupperLeftY, gbc_lblupperLeftY);
		}
		{
			txtupY = new JTextField();
			txtupY.setEditable(false);
			txtupY.setText("");
			GridBagConstraints gbc_txtupY = new GridBagConstraints();
			gbc_txtupY.insets = new Insets(0, 0, 5, 0);
			gbc_txtupY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtupY.gridx = 3;
			gbc_txtupY.gridy = 3;
			contentPanel.add(txtupY, gbc_txtupY);
			txtupY.setColumns(10);
		}

		{
			JLabel lblwidth = new JLabel("Width:");
			GridBagConstraints gbc_lblwidth = new GridBagConstraints();
			gbc_lblwidth.insets = new Insets(0, 0, 5, 5);
			gbc_lblwidth.gridx = 1;
			gbc_lblwidth.gridy = 7;
			contentPanel.add(lblwidth, gbc_lblwidth);
		}
		{
			textWidth = new JTextField();
			textWidth.setText("");
			GridBagConstraints gbc_textWidth = new GridBagConstraints();
			gbc_textWidth.insets = new Insets(0, 0, 5, 0);
			gbc_textWidth.fill = GridBagConstraints.HORIZONTAL;
			gbc_textWidth.gridx = 3;
			gbc_textWidth.gridy = 7;
			contentPanel.add(textWidth, gbc_textWidth);
			textWidth.setColumns(10);
		}
		{
			JLabel lblheight = new JLabel("Height:");
			GridBagConstraints gbc_lblheight = new GridBagConstraints();
			gbc_lblheight.insets = new Insets(0, 0, 5, 5);
			gbc_lblheight.gridx = 1;
			gbc_lblheight.gridy = 5;
			contentPanel.add(lblheight, gbc_lblheight);
		}
		{
			txtHeight = new JTextField();
			txtHeight.setText("");
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.insets = new Insets(0, 0, 5, 0);
			gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtHeight.gridx = 3;
			gbc_txtHeight.gridy = 5;
			contentPanel.add(txtHeight, gbc_txtHeight);
			txtHeight.setColumns(10);
		}
		{
			
			btnInnerColor.setVisible(false);
			btnInnerColor.setForeground(Color.RED);
			btnInnerColor.setBackground(Color.DARK_GRAY);
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					innerFill=JColorChooser.showDialog(null, "Choose a color", innerFill);
					btnInnerColor.setBackground(innerFill);
					isInnerColorOk=true;
				}
			});
			GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
			gbc_btnInnerColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnInnerColor.gridx = 1;
			gbc_btnInnerColor.gridy = 8;
			contentPanel.add(btnInnerColor, gbc_btnInnerColor);
		}
		{
			btnBorderColor.setVisible(false);
			btnBorderColor.setForeground(Color.RED);
			btnBorderColor.setBackground(Color.DARK_GRAY);
			btnBorderColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					borderFill=JColorChooser.showDialog(null, "Choose a border color", borderFill);
					btnBorderColor.setBackground(borderFill);
					isBorderColorOk=true;
				}
			});
			GridBagConstraints gbc_btnBorderColor = new GridBagConstraints();
			gbc_btnBorderColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnBorderColor.gridx = 2;
			gbc_btnBorderColor.gridy = 8;
			contentPanel.add(btnBorderColor, gbc_btnBorderColor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setForeground(Color.RED);
				okButton.setBackground(Color.DARK_GRAY);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						isOk=true;
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setForeground(Color.RED);
				cancelButton.setBackground(Color.DARK_GRAY);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	////--------------------------Gettters and Setters----------------------------------------------------------------------------------

	public JTextField getTextupX() {
		return textupX;
	}

	public void setTextupX(JTextField textupX) {
		this.textupX = textupX;
	}

	public JTextField getTxtupY() {
		return txtupY;
	}

	public void setTxtupY(JTextField txtupY) {
		this.txtupY = txtupY;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public JTextField getTextWidth() {
		return textWidth;
	}

	public void setTextWidth(JTextField textWidth) {
		this.textWidth = textWidth;
	}
	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public Color getInnerFill() {
		return innerFill;
	}

	public void setInnerFill(Color innerFill) {
		this.innerFill = innerFill;
	}

	public Color getBorderFill() {
		return borderFill;
	}

	public void setBorderFill(Color borderFill) {
		this.borderFill = borderFill;
	}

	public boolean isInnerColorOk() {
		return isInnerColorOk;
	}

	public void setInnerColorOk(boolean isInnerColorOk) {
		this.isInnerColorOk = isInnerColorOk;
	}

	public boolean isBorderColorOk() {
		return isBorderColorOk;
	}

	public void setBorderColorOk(boolean isBorderColorOk) {
		this.isBorderColorOk = isBorderColorOk;
	}

	
	}


