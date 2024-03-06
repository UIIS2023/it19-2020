package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class DlgDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textCenterX;
	private JTextField txtCenterY;
	private JTextField textInnerRadius;
	private JTextField textRadius;
	boolean isOk;
	public Color innerFill;
	public Color borderFill;
	private boolean isInnerColorOk;
	private boolean isBorderColorOk;
	private JButton btnInnerColor = new JButton("Inner Color:");
	private JButton btnBorderColor = new JButton("Border Color:");



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDonut() {
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
			JLabel lblCenterX = new JLabel("Center-X:");
			GridBagConstraints gbc_lblCenterX = new GridBagConstraints();
			gbc_lblCenterX.insets = new Insets(0, 0, 5, 5);
			gbc_lblCenterX.gridx = 1;
			gbc_lblCenterX.gridy = 1;
			contentPanel.add(lblCenterX, gbc_lblCenterX);
		}
		{
			textCenterX = new JTextField();
			GridBagConstraints gbc_textCenterX = new GridBagConstraints();
			gbc_textCenterX.insets = new Insets(0, 0, 5, 0);
			gbc_textCenterX.fill = GridBagConstraints.HORIZONTAL;
			gbc_textCenterX.gridx = 3;
			gbc_textCenterX.gridy = 1;
			contentPanel.add(textCenterX, gbc_textCenterX);
			textCenterX.setColumns(10);
			textCenterX.setEditable(false);
		}
		{
			JLabel lblCenterY = new JLabel("Center-Y:");
			GridBagConstraints gbc_lblCenterY = new GridBagConstraints();
			gbc_lblCenterY.insets = new Insets(0, 0, 5, 5);
			gbc_lblCenterY.gridx = 1;
			gbc_lblCenterY.gridy = 3;
			contentPanel.add(lblCenterY, gbc_lblCenterY);
		}
		{
			txtCenterY = new JTextField();
			txtCenterY.setText("");
			GridBagConstraints gbc_txtCenterY = new GridBagConstraints();
			gbc_txtCenterY.insets = new Insets(0, 0, 5, 0);
			gbc_txtCenterY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCenterY.gridx = 3;
			gbc_txtCenterY.gridy = 3;
			contentPanel.add(txtCenterY, gbc_txtCenterY);
			txtCenterY.setColumns(10);
			txtCenterY.setEditable(false);
		}
		{
			JLabel lblInnerRadius = new JLabel("Inner Radius:");
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 1;
			gbc_lblRadius.gridy = 5;
			contentPanel.add(lblInnerRadius, gbc_lblRadius);
		}
		{
			textInnerRadius = new JTextField();
			//textInnerRadius.setText("");
			GridBagConstraints gbc_textRadius = new GridBagConstraints();
			gbc_textRadius.insets = new Insets(0, 0, 5, 0);
			gbc_textRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_textRadius.gridx = 3;
			gbc_textRadius.gridy = 5;
			contentPanel.add(textInnerRadius, gbc_textRadius);
			textInnerRadius.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			GridBagConstraints gbc_lblinnerRadius = new GridBagConstraints();
			gbc_lblinnerRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblinnerRadius.gridx = 1;
			gbc_lblinnerRadius.gridy = 7;
			contentPanel.add(lblRadius, gbc_lblinnerRadius);
		}
		{
			textRadius = new JTextField();
			GridBagConstraints gbc_textinnerRadius = new GridBagConstraints();
			gbc_textinnerRadius.insets = new Insets(0, 0, 5, 0);
			gbc_textinnerRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_textinnerRadius.gridx = 3;
			gbc_textinnerRadius.gridy = 7;
			contentPanel.add(textRadius, gbc_textinnerRadius);
			textRadius.setColumns(10);
		}
		{
			btnInnerColor.setVisible(false);
			btnInnerColor.setForeground(Color.RED);
			btnInnerColor.setBackground(Color.DARK_GRAY);
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					innerFill=JColorChooser.showDialog(null, "Choose an inner color", innerFill);
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
					borderFill=JColorChooser.showDialog(null,"Choose a border color", borderFill);
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
						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getTextCenterX() {
		return textCenterX;
	}

	public void setTextCenterX(JTextField textCenterX) {
		this.textCenterX = textCenterX;
	}

	public JTextField getTxtCenterY() {
		return txtCenterY;
	}

	public void setTxtCenterY(JTextField txtCenterY) {
		this.txtCenterY = txtCenterY;
	}

	public JTextField getTextRadius() {
		return textRadius;
	}

	public void setTextRadius(JTextField textRadius) {
		this.textRadius = textRadius;
	}

	public JTextField getTextinnerRadius() {
		return textInnerRadius;
	}

	public void setTextinnerRadius(JTextField textinnerRadius) {
		this.textInnerRadius = textinnerRadius;
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
	
	

}