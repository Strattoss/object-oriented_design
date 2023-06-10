package pl.edu.agh.dronka.shop.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Filter;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import pl.edu.agh.dronka.shop.controller.ShopController;
import pl.edu.agh.dronka.shop.model.Category;
import pl.edu.agh.dronka.shop.model.filter.ItemFilter;
import pl.edu.agh.dronka.shop.model.items.BookItem;
import pl.edu.agh.dronka.shop.model.items.ElectronicItem;
import pl.edu.agh.dronka.shop.model.items.Item;
import pl.edu.agh.dronka.shop.model.items.MusicItem;

public class PropertiesPanel extends JPanel {

	private static final long serialVersionUID = -2804446079853846996L;
	private ShopController shopController;

	private ItemFilter filter;

	public PropertiesPanel(ShopController shopController) {
		this.shopController = shopController;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	public void fillProperties() {
		removeAll();

		Category currentCategory = shopController.getCurrentCategory();

		filter = new ItemFilter(currentCategory);
		Item filterItemSpec = filter.getItemSpec();

		add(createPropertyCheckbox("Tanie bo polskie", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				filterItemSpec.setPolish(
						((JCheckBox) event.getSource()).isSelected());
				shopController.filterItems(filter);
			}
		}));

		add(createPropertyCheckbox("Używany", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				filterItemSpec.setSecondhand(
						((JCheckBox) event.getSource()).isSelected());
				shopController.filterItems(filter);
			}
		}));

		if (filterItemSpec instanceof BookItem) {
			add(createPropertyCheckbox("Twarda oprawa", new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					((BookItem) filterItemSpec).setHardcover(
							((JCheckBox) event.getSource()).isSelected());
					shopController.filterItems(filter);
				}
			}));
		}


		else if (filterItemSpec instanceof ElectronicItem) {
			add(createPropertyCheckbox("Mobilny", new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					((ElectronicItem) filterItemSpec).setMobile(
							((JCheckBox) event.getSource()).isSelected());
					shopController.filterItems(filter);
				}
			}));

			add(createPropertyCheckbox("Na gwarancji", new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					((ElectronicItem) filterItemSpec).setUnderWarranty(
							((JCheckBox) event.getSource()).isSelected());
					shopController.filterItems(filter);
				}
			}));
		}

		else if (filterItemSpec instanceof MusicItem) {
			add(createPropertyCheckbox("Dołączone wideo", new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					((MusicItem) filterItemSpec).setHasAttachedVideo(
							((JCheckBox) event.getSource()).isSelected());
					shopController.filterItems(filter);
				}
			}));
		}

	}

	private JCheckBox createPropertyCheckbox(String propertyName,
			ActionListener actionListener) {

		JCheckBox checkBox = new JCheckBox(propertyName);
		checkBox.setSelected(false);
		checkBox.addActionListener(actionListener);

		return checkBox;
	}

}
