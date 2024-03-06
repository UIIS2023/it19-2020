package sort;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
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

public class DlgAdd extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX; //nisu get i set vec protected
	private JTextField txtY;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private boolean isOk; // proverava koja je vrednost ok promenljive
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgAdd dialog = new DlgAdd();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgAdd() {
		setModal(true);
		setTitle("Add");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCordinateX = new JLabel("Cordinate X:");
			GridBagConstraints gbc_lblCordinateX = new GridBagConstraints();
			gbc_lblCordinateX.insets = new Insets(0, 0, 5, 5);
			gbc_lblCordinateX.gridx = 2;
			gbc_lblCordinateX.gridy = 1;
			contentPanel.add(lblCordinateX, gbc_lblCordinateX);
		}
		{
			setTxtX(new JTextField());
			GridBagConstraints gbc_txtX = new GridBagConstraints();
			gbc_txtX.insets = new Insets(0, 0, 5, 0);
			gbc_txtX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtX.gridx = 4;
			gbc_txtX.gridy = 1;
			contentPanel.add(getTxtX(), gbc_txtX);
			getTxtX().setColumns(10);
		}
		{
			JLabel lblCordinateY = new JLabel("Cordinate Y:");
			GridBagConstraints gbc_lblCordinateY = new GridBagConstraints();
			gbc_lblCordinateY.insets = new Insets(0, 0, 5, 5);
			gbc_lblCordinateY.gridx = 2;
			gbc_lblCordinateY.gridy = 2;
			contentPanel.add(lblCordinateY, gbc_lblCordinateY);
		}
		{
			setTxtY(new JTextField());
			GridBagConstraints gbc_txtY = new GridBagConstraints();
			gbc_txtY.insets = new Insets(0, 0, 5, 0);
			gbc_txtY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtY.gridx = 4;
			gbc_txtY.gridy = 2;
			contentPanel.add(getTxtY(), gbc_txtY);
			getTxtY().setColumns(10);
		}
		{
			JLabel lblWidth = new JLabel("Width:");
			GridBagConstraints gbc_lblWidth = new GridBagConstraints();
			gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
			gbc_lblWidth.gridx = 2;
			gbc_lblWidth.gridy = 3;
			contentPanel.add(lblWidth, gbc_lblWidth);
		}
		{
			setTxtWidth(new JTextField());
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.insets = new Insets(0, 0, 5, 0);
			gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtWidth.gridx = 4;
			gbc_txtWidth.gridy = 3;
			contentPanel.add(getTxtWidth(), gbc_txtWidth);
			getTxtWidth().setColumns(10);
		}
		{
			JLabel lblHeight = new JLabel("Height:");
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.insets = new Insets(0, 0, 0, 5);
			gbc_lblHeight.gridx = 2;
			gbc_lblHeight.gridy = 4;
			contentPanel.add(lblHeight, gbc_lblHeight);
		}
		{
			setTxtHeight(new JTextField());
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtHeight.gridx = 4;
			gbc_txtHeight.gridy = 4;
			contentPanel.add(getTxtHeight(), gbc_txtHeight);
			getTxtHeight().setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setForeground(Color.CYAN);
				okButton.setBackground(Color.BLACK);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setOk(true);
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setForeground(Color.CYAN);
				cancelButton.setBackground(Color.BLACK);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			contentPanel.setBackground(Color.CYAN);
		}
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

}
