package ca.mcgill.ecse223.block.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ca.mcgill.ecse223.block.controller.Block223Controller;
import ca.mcgill.ecse223.block.controller.InvalidInputException;
import ca.mcgill.ecse223.block.controller.TOBlock;

import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;

public class UpdateBlockUI {

	private static JFrame frame;
	private static JComboBox<String> blocks;
	private static HashMap<Integer, TOBlock> map;
	private static JLabel errorMsg;
	private static JSlider Rvalue;
	private static JSlider Gvalue;
	private static JSlider Bvalue;
	private static JSlider point;
	private static JLayeredPane blockPreview;
	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public static void init() {
		frame = new JFrame("Block Settings");
		frame.setBounds(100, 100, 610, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setBackground(new Color(106,108,110));
		JLabel lblPleaseSelectA = new JLabel("Please select a Block an update its parameters");
		lblPleaseSelectA.setFont(new Font("Century Gothic", Font.PLAIN, 16));

		JLabel label_1 = new JLabel("R:");
		label_1.setFont(new Font("Century Gothic", Font.BOLD, 15));

		Rvalue = new JSlider();
		Rvalue.setToolTipText("");
		Rvalue.setSnapToTicks(true);
		Rvalue.setPaintTicks(true);
		Rvalue.setPaintLabels(true);
		Rvalue.setMaximum(255);

		JLabel label_2 = new JLabel("50");
		label_2.setForeground(new Color(255, 0, 51));

		JLabel label_3 = new JLabel("0");

		JLabel label_4 = new JLabel("255");

		JLabel label_5 = new JLabel("G:");
		label_5.setFont(new Font("Century Gothic", Font.BOLD, 15));

		JLabel label_6 = new JLabel("50");
		label_6.setForeground(new Color(51, 153, 51));

		JLabel label_7 = new JLabel("0");

		Gvalue = new JSlider();
		Gvalue.setSnapToTicks(true);
		Gvalue.setPaintTicks(true);
		Gvalue.setPaintLabels(true);
		Gvalue.setMaximum(255);

		JLabel label_8 = new JLabel("255");

		JLabel label_9 = new JLabel("B:");
		label_9.setFont(new Font("Century Gothic", Font.BOLD, 15));

		JLabel label_10 = new JLabel("50");
		label_10.setForeground(new Color(51, 0, 255));

		Bvalue = new JSlider();
		Bvalue.setSnapToTicks(true);
		Bvalue.setPaintTicks(true);
		Bvalue.setPaintLabels(true);
		Bvalue.setMaximum(255);

		JLabel label_11 = new JLabel("0");

		JLabel label_12 = new JLabel("255");

		JLabel label_13 = new JLabel("1000");

		JLabel label_14 = new JLabel("Points:");
		label_14.setFont(new Font("Century Gothic", Font.BOLD, 15));

		JTextField pointTxt = new JTextField("50");
		pointTxt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pointTxt.setBackground(frame.getBackground());
		pointTxt.addActionListener((new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int value=0;
				try {
					value=Integer.parseInt(pointTxt.getText());
					if (value>=1 && value<=1000) {
						point.setValue(value);
					}else {
						errorMsg.setText("Please enter a value between 1 and 1000 only for the points.");
					}
				}catch(Exception e) {
					errorMsg.setText("Please enter integers only for points.");
				}
				
			}
			
		}));
		point = new JSlider();
		point.setSnapToTicks(true);
		point.setPaintTicks(true);
		point.setPaintLabels(true);
		point.setMinimum(1);
		point.setMaximum(1000);

		JLabel label_16 = new JLabel("1");

		JButton btnReturn = new JButton("Go Back");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goBackButtonActionPerformed();
			}
		});
		btnReturn.setFont(new Font("Century Gothic", Font.BOLD, 15));

		JButton btnUpdateBlock = new JButton("Update Block");
		btnUpdateBlock.setFont(new Font("Century Gothic", Font.BOLD, 15));
		btnUpdateBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateButtonActionPerformed(Rvalue, Gvalue, Bvalue, point);
			}
		});
		blocks = new JComboBox<String>();

		errorMsg = new JLabel("");
		errorMsg.setForeground(Color.RED);
		Rvalue.setBackground(new Color(106,108,110));
		Gvalue.setBackground(new Color(106,108,110));
		Bvalue.setBackground(new Color(106,108,110));
		point.setBackground(new Color(106,108,110));
		Rvalue.addChangeListener((new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				change(Rvalue.getValue(), label_2);
				((BlockPreview)blockPreview).update(Rvalue.getValue(),Gvalue.getValue(),Bvalue.getValue());
			}
		}));
		Gvalue.addChangeListener((new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				change(Gvalue.getValue(), label_6);
				((BlockPreview)blockPreview).update(Rvalue.getValue(),Gvalue.getValue(),Bvalue.getValue());
			}
		}));
		Bvalue.addChangeListener((new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				change(Bvalue.getValue(), label_10);
				((BlockPreview)blockPreview).update(Rvalue.getValue(),Gvalue.getValue(),Bvalue.getValue());
			}
		}));
		point.addChangeListener((new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				change(point.getValue(), pointTxt);
			}
		}));
		refresh();
		
		JButton btnDeleteBlock = new JButton("Delete Block");
		btnDeleteBlock.setForeground(new Color(255, 51, 51));
		btnDeleteBlock.setFont(new Font("Century Gothic", Font.BOLD, 15));
		btnDeleteBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteBlockActionPerformed();
			}
		});
		
		JLabel lblPreview = new JLabel("Preview: ");
		lblPreview.setFont(new Font("Century Gothic", Font.BOLD, 16));
		
		 blockPreview = new BlockPreview();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addComponent(lblPleaseSelectA, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(61)
					.addComponent(blocks, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(label_2, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
										.addComponent(label_6, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED))))
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(pointTxt, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)))
							.addComponent(lblPreview))
						.addComponent(btnReturn, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(42)
							.addComponent(btnDeleteBlock)
							.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
							.addComponent(btnUpdateBlock, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
									.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(blockPreview, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(errorMsg, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(Gvalue, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(Rvalue, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(point, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)))
								.addComponent(Bvalue, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblPleaseSelectA, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(blocks, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(errorMsg)
							.addGap(40))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPreview)
							.addGap(18))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(blockPreview, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(17)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_3)
							.addComponent(label_2))
						.addComponent(Rvalue, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(Gvalue, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
											.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
											.addComponent(label_10)
											.addComponent(label_11))
										.addComponent(Bvalue, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(label_8)
									.addGap(30)
									.addComponent(label_12)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_13)
								.addComponent(point, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_7)
								.addComponent(label_6))
							.addGap(72)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addComponent(pointTxt))
								.addComponent(label_16))))
					.addGap(74)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReturn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDeleteBlock)
						.addComponent(btnUpdateBlock, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(92))
		);
		frame.getContentPane().setLayout(groupLayout);
		blocks.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				newItemSelected();
			}

		});
	}

	private static void deleteBlockActionPerformed() {
		// TODO Auto-generated method stub
		errorMsg.setText("");
		if(blocks.getSelectedIndex() < 0) {
			errorMsg.setText("A block must be selected to be deleted");
			return;
		}
		try {
			TOBlock block = map.get(blocks.getSelectedIndex());
			Block223Controller.deleteBlock(block.getId());
		} catch (InvalidInputException e) {
			errorMsg.setText(e.getMessage());
		}
		refresh(blocks.getSelectedIndex() - 1);
		
	}

	private static void newItemSelected() {
		// TODO Auto-generated method stub

		if (blocks.getSelectedIndex() >= 0) {
			TOBlock block = map.get(blocks.getSelectedIndex());
			if (block != null) {
				if (Rvalue != null)
					Rvalue.setValue(block.getRed());
				if (Gvalue != null)
					Gvalue.setValue(block.getGreen());
				if (Bvalue != null)
					Bvalue.setValue(block.getBlue());
				if (point != null)
					point.setValue(block.getPoints());
				((BlockPreview)blockPreview).update(Rvalue.getValue(),Gvalue.getValue(),Bvalue.getValue());
			}
		} else {
			Rvalue.setValue(50);
			Gvalue.setValue(50);
			Bvalue.setValue(50);
			point.setValue(50);
			((BlockPreview)blockPreview).update(Rvalue.getValue(),Gvalue.getValue(),Bvalue.getValue());
		}
	}

	private static void change(int value, JLabel label) {
		label.setText(value + "");
	}
	private static void change(int value, JTextField label) {
		label.setText(value+"");
	}
	private static void goBackButtonActionPerformed() {
		// clear error message
		frame.dispose();
		GameSettingUI.init();
	}

	/**
	 * Specification for saveButton event when listener activated
	 * 
	 * @param evt
	 */

	private static void updateButtonActionPerformed(JSlider R, JSlider G, JSlider B, JSlider point) {
		errorMsg.setText("");
		if(blocks.getSelectedIndex() < 0) {
			errorMsg.setText("A block must be selected to be updated");
			return;
		}
		try {
			// close the main frame

			int id = map.get(blocks.getSelectedIndex()).getId();
			errorMsg.setText(blocks.getSelectedIndex() + " : " + id);
			Block223Controller.updateBlock(id, R.getValue(), G.getValue(), B.getValue(), point.getValue());
			refresh(blocks.getSelectedIndex());
			newItemSelected();
			// constructor call for game settings
		} catch (Exception e) {
			errorMsg.setText(e.getMessage());
		}
	}
	
	//refresh for update and delete block
	private static void refresh(int index) {

		blocks.removeAllItems();
		map = new HashMap<Integer, TOBlock>();
		int blockid = 0;
		errorMsg.setText("");
		try {
			for (TOBlock block : Block223Controller.getBlocksOfCurrentDesignableGame()) {
				map.put(blockid, block);
				String RGBPoints = (blockid + 1) + ") R: " + block.getRed() + " , G: " + block.getGreen() + " , B: " + block.getBlue()
						+ " , P: " + block.getPoints();
				blocks.addItem(RGBPoints);
				blockid++;
			}
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			errorMsg.setText(e.getMessage());
		}
		Rvalue.setValue(50);
		Gvalue.setValue(50);
		Bvalue.setValue(50);
		point.setValue(50);
		blocks.setSelectedIndex(index);
	}
	
	//refresh for deleteButton (overloading)
	private static void refresh() {

		blocks.removeAllItems();
		map = new HashMap<Integer, TOBlock>();
		int blockid = 0;
		errorMsg.setText("");
		try {
			for (TOBlock block : Block223Controller.getBlocksOfCurrentDesignableGame()) {
				map.put(blockid, block);
				String RGBPoints = (blockid + 1) + ") R: " + block.getRed() + " , G: " + block.getGreen() + " , B: " + block.getBlue()
						+ " , P: " + block.getPoints();
				blocks.addItem(RGBPoints);
				blockid++;
			}
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			errorMsg.setText(e.getMessage());
		}
		Rvalue.setValue(50);
		Gvalue.setValue(50);
		Bvalue.setValue(50);
		point.setValue(50);
		blocks.setSelectedIndex(-1);
	}
}
