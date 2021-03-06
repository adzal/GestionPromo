package promo.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import promo.controller.PromoAppController;
import promo.model.Apprenant;
import promo.model.Promotion;

public class PromoDetail extends JPanel {

	private JTextField namePromoField;
	private JLabel namePromoLabel;

	private JButton updatePromotion;
	private JButton cancelPromotion;

	private JButton updateAlternant;
	private JButton createAlternant;
	private JButton deleteAlternant;
	private JLabel dateDebutLabel;
	private JTextField dateDebutField;
	private JLabel dureeActuelLabel;
	private JTextField dureeActuelField;
	private JLabel dureeTotalLabel;
	private JTextField dureeTotalField;

	private JTable alternantTable;
	private ApprenantTableModel tableModelAlternant;
	private PromoAppController promoController;
	private JMenuItem saveItem, saveAllItem;
	private boolean isNewPromotion = false;

	private Apprenant selectedApprenant;

	public Apprenant getSelectedApprenant() {
		return selectedApprenant;
	}

	public void setSelectedApprenant(Apprenant selectedApprenant) {
		this.selectedApprenant = selectedApprenant;
	}

	private Promotion promotion;

	public Promotion getPromotion() {
		return promotion;
	}

	/**
	 * Constructor for a new apprenant, will as to select the Apprentant type via a
	 * comboBox. Then will create the fields appropriately.
	 * 
	 * @param promoController
	 */
	public PromoDetail(PromoAppController promoController) {
		this.promoController = promoController;
		this.isNewPromotion = true;

		namePromoLabel = new JLabel("Formations : ");
		namePromoField = new JTextField("");

		dateDebutLabel = new JLabel("Debut de la formation : ");
		dateDebutField = new JTextField("");

		dureeActuelLabel = new JLabel("Temps passee : ");
		dureeActuelField = new JTextField("");

		dureeTotalLabel = new JLabel("Duree totale : ");
		dureeTotalField = new JTextField("0");

		setupButtons();
		createAlternant.setEnabled(false);
		deleteAlternant.setEnabled(false);

		setupLayout();
		setupListeners();
	}

	public PromoDetail(PromoAppController promoController, Promotion promo) {
		System.out.println("PromoDetail");
		System.out.println(promo);
		this.promoController = promoController;
		this.promotion = promo;

		namePromoLabel = new JLabel("Formations : ");
		namePromoField = new JTextField(promo.getNomPromotion());
		namePromoLabel.setFont(new Font("Arial", Font.BOLD, 15));

		dateDebutLabel = new JLabel("Debut de la formation : ");
		dateDebutField = new JTextField(promo.getDateDebutPromotion().toString());
		dateDebutLabel.setFont(new Font("Arial", Font.BOLD, 15));

		dureeActuelLabel = new JLabel("Temps passee : ");
		dureeActuelField = new JTextField(promo.joursPasses().toString());
		dureeActuelLabel.setFont(new Font("Arial", Font.BOLD, 15));

		dureeTotalLabel = new JLabel("Duree totale : ");
		dureeTotalField = new JTextField(Integer.toString(promo.getDureeTotal()));
		dureeTotalLabel.setFont(new Font("Arial", Font.BOLD, 15));

		setupButtons();

		tableModelAlternant = new ApprenantTableModel(promo.getApprenants());
		tableModelAlternant.setDelai(promo.getDelai());
		tableModelAlternant.setDureeTotal(promo.getDureeTotal());
		
		alternantTable = new JTable(tableModelAlternant);
		alternantTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		alternantTable.setBackground(Color.getHSBColor(0.1f, 0.75f, 1f));

		setupLayout();
		setupListeners();
		setupTableListeners();
	}

	private void setupButtons() {
		updatePromotion = new JButton("Update Promotion");
		cancelPromotion = new JButton("Back to Promotion List");

		createAlternant = new JButton("New Apprenant");
		updateAlternant = new JButton("Update Apprenant");
		updateAlternant.setEnabled(false);
		deleteAlternant = new JButton("Delete");
		deleteAlternant.setEnabled(false);
	}

	private void setupLayout() {

		this.setBackground(Color.getHSBColor(0.5f, 0.5f, 0.7f));
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbL = new GridBagConstraints();
		gbL.weightx = .2;
		gbL.gridx = 0;
		gbL.gridy = 0;
		gbL.anchor = GridBagConstraints.LINE_END;
		gbL.insets = new Insets(5, 5, 5, 5);

		GridBagConstraints gbR = new GridBagConstraints();
		gbR.weightx = 3;
		gbR.gridx = 1;
		gbR.gridy = 0;
		gbR.gridwidth = 1;
		gbR.fill = GridBagConstraints.HORIZONTAL;

		this.add(namePromoLabel, gbL);
		this.add(namePromoField, gbR);
		gbL.gridy++;
		gbR.gridy++;

		this.add(dateDebutLabel, gbL);
		this.add(dateDebutField, gbR);
		gbL.gridy++;
		gbR.gridy++;

		this.add(dureeActuelLabel, gbL);
		this.add(dureeActuelField, gbR);
		gbL.gridy++;
		gbR.gridy++;

		this.add(dureeTotalLabel, gbL);
		this.add(dureeTotalField, gbR);
		gbL.gridy++;
		gbR.gridy++;

		gbL.gridx = 0;
		gbL.gridy++;
		gbR.gridy++;
		this.add(updatePromotion, gbL);
		gbL.gridx++;
		this.add(cancelPromotion, gbR);

		gbL.gridx = 0;
		gbL.gridy = 6;

		gbL.gridwidth = 3;
		gbL.fill = GridBagConstraints.BOTH;
		gbL.weighty = 1;
		gbL.gridy = 7;
		gbR.gridy = 7;
		this.add(new JScrollPane(alternantTable), gbL);

		gbL.gridy++;
		gbL.weighty = 0;
		gbL.gridwidth = 1;
		gbL.fill = GridBagConstraints.NONE;
		gbL.anchor = GridBagConstraints.LINE_START;
		gbL.weightx = 0.5;

		gbL.gridx = 0;
		gbL.gridy++;
		this.add(createAlternant, gbL);
		gbL.gridx++;
		this.add(updateAlternant, gbL);
		gbL.gridx++;
		this.add(deleteAlternant, gbL);
	}

	private void setupListeners() {
		updatePromotion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isNewPromotion) {
					promotion = new Promotion();
				}
				promotion.setNomPromotion(namePromoField.getText());
				promotion.setDureeTotal(Integer.parseInt(dureeTotalField.getText()));

				promoController.updatePromotionClicked(promotion, isNewPromotion);
			}
		});
		cancelPromotion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				promoController.cancelPromotionClicked();
			}
		});

		createAlternant.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				promoController.newApprenantClicked();
			}
		});

		updateAlternant.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				promoController.updateApprenantClicked(selectedApprenant);
			}
		});

		deleteAlternant.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void setupTableListeners() {

		alternantTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					ListSelectionModel lsm = (ListSelectionModel) e.getSource();
					if (!lsm.isSelectionEmpty()) {
						int row = lsm.getMinSelectionIndex();
						deleteAlternant.setEnabled(true);
						updateAlternant.setEnabled(true);
						Apprenant apprenant = promotion.getApprenants().get(row);
						selectedApprenant = apprenant;
					}
				}
			}
		});

	}

	public void save() {
		promotion.setNomPromotion(namePromoField.getText());
		promotion.setDateDebutPromotion(LocalDate.now());
	}
}
