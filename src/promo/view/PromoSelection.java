package promo.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import promo.controller.PromoAppController;
import promo.model.PromotionList;

public class PromoSelection extends JPanel {
	private JTextField nameField;
	private JLabel nameLabel;

	private JButton createPromotion;
	private JButton savePromotion;
	private JButton deletePromotion;

	private JButton backupPromoList;
	private JButton restorePromoList;

	private JTable promoTable;
	private DefaultTableModel tableModel;

	public PromoSelection(PromoAppController promoController, PromotionList promoList) {
		System.out.println("PromoSelection");
		System.out.println(promoList);
		this.setBackground(Color.cyan);

		nameLabel = new JLabel("Name");
		nameField = new JTextField(promoList.getName());

		createPromotion = new JButton("New");
		savePromotion = new JButton("Save");
		deletePromotion = new JButton("Delete");

		// create the JTable from a DefaultTableModel filled from the promoList sent
		promoTable = new JTable();
		tableModel = new DefaultTableModel();

		Object[] columnsName = new Object[3];
		columnsName[0] = "Name";
		columnsName[1] = "Start Date";
		columnsName[2] = "Duree";
		tableModel.setColumnIdentifiers(columnsName);

		Object[] rowData = new Object[3];
		for (int i = 0; i < promoList.getPromoList().size(); i++) {
			rowData[0] = promoList.getPromoList().get(i).getNomPromotion();
			rowData[1] = promoList.getPromoList().get(i).getDateDebutPromotion();
			rowData[2] = promoList.getPromoList().get(i).getDureeTotal();

			tableModel.addRow(rowData);
		}
		promoTable.setModel(tableModel);

		backupPromoList = new JButton("Backup");
		restorePromoList = new JButton("Restore");

		setupLayout();
		setupListeners();
	}

	private void setupLayout() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbL = new GridBagConstraints();
		gbL.weightx = 0.1;
		gbL.gridx = 0;
		gbL.gridy = 0;
		gbL.anchor = GridBagConstraints.LINE_END;
		gbL.insets = new Insets(0, 0, 0, 10);

		GridBagConstraints gbR = new GridBagConstraints();
		gbR.weightx = 0.8;
		gbR.gridx = 1;
		gbR.gridy = 0;
		gbR.gridwidth = 1;
		gbR.fill = GridBagConstraints.HORIZONTAL;

		this.add(nameLabel, gbL);
		this.add(nameField, gbR);

		gbL.anchor = GridBagConstraints.LINE_START;
		gbL.weightx = 0.5;

		gbL.gridx = 0;
		gbL.gridy++;
		this.add(createPromotion, gbL);
		gbL.gridx++;
		this.add(savePromotion, gbL);
		gbL.gridx++;
		this.add(deletePromotion, gbL);

		gbL.gridx = 0;
		gbL.gridy++;
		this.add(backupPromoList, gbL);
		gbL.gridx += 2;
		this.add(restorePromoList, gbL);

		gbL.gridx = 0;
		gbL.gridy++;
		gbL.gridwidth = 3;
		gbL.fill = GridBagConstraints.HORIZONTAL;
		this.add(new JScrollPane(promoTable), gbL);
	}

	private void setupListeners() {
		createPromotion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		savePromotion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		deletePromotion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		backupPromoList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		
		restorePromoList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}
}
