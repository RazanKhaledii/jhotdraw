/*
 * @(#)EditGridPanel.java
 *
 * Copyright (c) 2007 by the original authors of JHotDraw
 * and all its contributors.
 * All rights reserved.
 *
 * The copyright of this software is owned by the authors and  
 * contributors of the JHotDraw project ("the copyright holders").  
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * the copyright holders. For details see accompanying license terms. 
 */

package org.jhotdraw.draw.action;

import java.beans.*;
import org.jhotdraw.draw.*;
import org.jhotdraw.text.JavaNumberFormatter;
import org.jhotdraw.util.*;

/**
 * The EditGridPanel can be used to edit the properties of
 * a GridConstrainer.
 * 
 * @see org.jhotdraw.draw.GridConstrainer
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class EditGridPanel extends javax.swing.JPanel {
    private ResourceBundleUtil labels;
    private GridConstrainer constrainer;
    
    /** Creates new instance. */
    public EditGridPanel() {
        labels = ResourceBundleUtil.getBundle("org.jhotdraw.draw.Labels");
        initComponents();

        widthField.setFormatterFactory(JavaNumberFormatter.createFormatterFactory(0, 1000, 1));
        heightField.setFormatterFactory(JavaNumberFormatter.createFormatterFactory(0, 1000, 1));
        thetaField.setFormatterFactory(JavaNumberFormatter.createFormatterFactory(0, 180, 1));

        constrainer = new GridConstrainer(10, 10);
        
        widthField.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("value")) {
                    if (evt.getNewValue() != null) {
                        constrainer.setWidth((Double) evt.getNewValue());
                    }
                }
            }
        });
        
        heightField.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("value")) {
                    if (evt.getNewValue() != null) {
                        constrainer.setHeight((Double) evt.getNewValue());
                    }
                }
            }
        });
        
        thetaField.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("value")) {
                    if (evt.getNewValue() != null) {
                        constrainer.setTheta((Double) evt.getNewValue() * Math.PI / 180d);
                    }
                }
            }
        });
        
    }
    
    /**
     * Sets the GridConstrainer to be edited by this panel.
     * 
     * @param newValue The GridConstrainer.
     */
    public void setConstrainer(GridConstrainer newValue) {
        constrainer = newValue;
        widthField.setValue(constrainer.getWidth());
        heightField.setValue(constrainer.getHeight());
        thetaField.setValue(constrainer.getTheta() * 180d / Math.PI);
    }
    
    /**
     * Returns the GridConstrainer currently being edited by this panel.
     * 
     * @return The GridConstrainer.
     */
    public GridConstrainer getConstrainer() {
        return constrainer;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        widthLabel = new javax.swing.JLabel();
        heightLabel = new javax.swing.JLabel();
        widthField = new org.jhotdraw.gui.JLifeFormattedTextField();
        heightField = new org.jhotdraw.gui.JLifeFormattedTextField();
        thetaLabel = new javax.swing.JLabel();
        thetaField = new org.jhotdraw.gui.JLifeFormattedTextField();

        widthLabel.setText(labels.getString("view.grid.width.text")); // NOI18N

        heightLabel.setText(labels.getString("view.grid.height.text")); // NOI18N

        widthField.setColumns(5);

        heightField.setColumns(5);

        thetaLabel.setText(labels.getString("view.grid.theta.text")); // NOI18N

        thetaField.setColumns(5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(heightLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(widthLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(thetaLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(thetaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(heightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(widthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(widthLabel)
                    .addComponent(widthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(heightLabel)
                    .addComponent(heightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(thetaLabel)
                    .addComponent(thetaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jhotdraw.gui.JLifeFormattedTextField heightField;
    private javax.swing.JLabel heightLabel;
    private org.jhotdraw.gui.JLifeFormattedTextField thetaField;
    private javax.swing.JLabel thetaLabel;
    private org.jhotdraw.gui.JLifeFormattedTextField widthField;
    private javax.swing.JLabel widthLabel;
    // End of variables declaration//GEN-END:variables
    
}
