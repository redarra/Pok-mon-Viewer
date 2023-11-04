package View.Pokemon_Viewer;

import java.awt.Image;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

import Controller.Pokemon_Viewer.PokemonController;
import Model.Pokemon_Viewer.Pokemon;

 class CustomModel extends AbstractTableModel {

    public String[] columnNames = {"", ""};
    PokemonController controller;
    public CustomModel(PokemonController controller) {
    	this.controller=controller;




    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return 20;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pokemon student = controller.getPokeList().get(rowIndex);
        switch (columnIndex) {
        case 0:
             try {
                URL url = new URL( student.imageUrl);
                BufferedImage img = ImageIO.read(url);
                return new JLabel(new ImageIcon(img));
            } catch (IOException e) {
            }
        case 1:
            return student.name;
        default:
            return null;
        }
    }

}