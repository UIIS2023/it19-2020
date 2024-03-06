package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.HexagonAdapter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class DlgHexagon extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textX;
	private JTextField textY;
	private JTextField textRadius;
	private boolean isOk;
	private boolean isInnerColorOk;
	private boolean isBorderColorOk;
	private JButton btnInnerColor = new JButton("Inner Color:");
	private JButton btnBorderColor = new JButton("Border Color:");
	
	private HexagonAdapter hexaAdapter;
	
	public Color innerFill;
	public Color borderFill;

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgHexagon dialog = new DlgHexagon();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgHexagon() {
		setBounds(100, 100, 450, 300);
		setModal(true);
		setTitle("Add/Modify");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblX = new JLabel("X:");
			GridBagConstraints gbc_lblCenterX = new GridBagConstraints();
			gbc_lblCenterX.insets = new Insets(0, 0, 5, 5);
			gbc_lblCenterX.gridx = 1;
			gbc_lblCenterX.gridy = 1;
			contentPanel.add(lblX, gbc_lblCenterX);
		}
		{
			textX = new JTextField();
			GridBagConstraints gbc_textCenterX = new GridBagConstraints();
			gbc_textCenterX.insets = new Insets(0, 0, 5, 0);
			gbc_textCenterX.fill = GridBagConstraints.HORIZONTAL;
			gbc_textCenterX.gridx = 3;
			gbc_textCenterX.gridy = 1;
			contentPanel.add(textX, gbc_textCenterX);
			textX.setColumns(10);
			textX.setEditable(false);
		}
		{
			JLabel lblY = new JLabel("Y:");
			GridBagConstraints gbc_lblCenterY = new GridBagConstraints();
			gbc_lblCenterY.insets = new Insets(0, 0, 5, 5);
			gbc_lblCenterY.gridx = 1;
			gbc_lblCenterY.gridy = 3;
			contentPanel.add(lblY, gbc_lblCenterY);
		}
		{
			textY = new JTextField();
			textY.setText("");
			GridBagConstraints gbc_textCenterY = new GridBagConstraints();
			gbc_textCenterY.insets = new Insets(0, 0, 5, 0);
			gbc_textCenterY.fill = GridBagConstraints.HORIZONTAL;
			gbc_textCenterY.gridx = 3;
			gbc_textCenterY.gridy = 3;
			contentPanel.add(textY, gbc_textCenterY);
			textY.setColumns(10);
			textY.setEditable(false);
		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 1;
			gbc_lblRadius.gridy = 5;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			textRadius = new JTextField();
			textRadius.setText("");
			GridBagConstraints gbc_textRadius = new GridBagConstraints();
			gbc_textRadius.insets = new Insets(0, 0, 5, 0);
			gbc_textRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_textRadius.gridx = 3;
			gbc_textRadius.gridy = 5;
			contentPanel.add(textRadius, gbc_textRadius);
			textRadius.setColumns(10);
		}
		{
			btnInnerColor.setVisible(false);
			btnInnerColor.setBackground(Color.DARK_GRAY);
			btnInnerColor.setForeground(Color.RED);
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					innerFill=JColorChooser.showDialog(null, "Choose a color", Color.WHITE);
					btnInnerColor.setBackground(innerFill);
					isInnerColorOk=true;
				}
			});
			GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
			gbc_btnInnerColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnInnerColor.gridx = 1;
			gbc_btnInnerColor.gridy = 7;
			contentPanel.add(btnInnerColor, gbc_btnInnerColor);
		}
		{
			btnBorderColor.setVisible(false);
			btnBorderColor.setBackground(Color.DARK_GRAY);
			btnBorderColor.setForeground(Color.RED);
			btnBorderColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					borderFill = JColorChooser.showDialog(null, "Choose a border color",borderFill);
					btnBorderColor.setBackground(borderFill);
					isBorderColorOk= true;
				}
			});
			GridBagConstraints gbc_btnBorderColor = new GridBagConstraints();
			gbc_btnBorderColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnBorderColor.gridx = 2;
			gbc_btnBorderColor.gridy = 7;
			contentPanel.add(btnBorderColor, gbc_btnBorderColor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(Color.DARK_GRAY);
				okButton.setForeground(Color.RED);
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
				cancelButton.setBackground(Color.DARK_GRAY);
				cancelButton.setForeground(Color.RED);
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

	public JTextField getTextX() {
		return textX;
	}

	public void setTextX(JTextField textX) {
		this.textX = textX;
	}

	public JTextField getTextY() {
		return textY;
	}

	public void setTextY(JTextField textY) {
		this.textY = textY;
	}

	public JTextField getTextRadius() {
		return textRadius;
	}

	public void setTextRadius(JTextField textRadius) {
		this.textRadius = textRadius;
	}

	public Color getFill() {
		return innerFill;
	}

	public void setFill(Color fill) {
		this.innerFill = fill;
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
