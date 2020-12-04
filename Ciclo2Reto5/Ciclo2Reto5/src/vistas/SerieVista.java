/*
 * Copyright (c) 2010, Oracle. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * * Neither the name of Oracle nor the names of its contributors
 *   may be used to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package vistas;

import controladores.ContenidoJpaController;
import controladores.SerieJpaController;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelos.Contenido;
import modelos.Serie;

public class SerieVista extends javax.swing.JFrame {

    private void estadoInicialComponentes() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ciclo2Reto5PU");
        SerieJpaController serviceSerie = new SerieJpaController(emf);
        List<Serie> series = serviceSerie.findSerieEntities();
        ContenidoJpaController serviceContenido = new ContenidoJpaController(emf);
        cmbIdentificadores.removeAllItems();
        cmbNombre.removeAllItems();
        for (Serie serie : series) {
            cmbIdentificadores.addItem(serie.getSerId());
            cmbNombre.addItem(serviceContenido.findContenido(serie.getSerId()).getConNombre());
        }
        txtEpisodios.setText("");
        txtEpisodios.setToolTipText("");
        txtIdentificador.setText("");
        txtIdentificador.setToolTipText("");
        txtNombre.setText("");
        txtNombre.setToolTipText("");
        txtTemporadas.setText("");
        txtTemporadas.setToolTipText("");
        btnAgregar.setEnabled(false);
        btnBorrar.setEnabled(false);
        btnLimpiar.setEnabled(false);
        btnModificar.setEnabled(false);
        lblEstado.setText("Modulo series.");
        if (cmbIdentificadores.getSelectedItem() == null) {
            cmbIdentificadores.setEnabled(false);
            cmbNombre.setEnabled(false);
        } else {
            cmbIdentificadores.setEnabled(true);
            cmbNombre.setEnabled(true);

        }
    }

    private Boolean OK() {
        Boolean respuesta = false;
        try {
            Integer.parseInt(txtIdentificador.getText());
            Integer.parseInt(txtEpisodios.getText());
            Integer.parseInt(txtTemporadas.getText());
            if (txtNombre.getText().length() > 0) {
                respuesta = true;
            }
        } catch (Exception e) {
            return false;
        }
        return respuesta;
    }

    private void seleccionado(int item) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ciclo2Reto5PU");
        ContenidoJpaController contenidoService = new ContenidoJpaController(emf);

        if (cmbIdentificadores.getSelectedItem() != null) {
            Contenido contenido = contenidoService.findContenido(item);
            txtEpisodios.setText(contenido.getSerie().getSerEpisodios() + "");
            txtEpisodios.setToolTipText(contenido.getSerie().getSerEpisodios() + "");
            txtIdentificador.setText(contenido.getConId().toString());
            txtIdentificador.setToolTipText(contenido.getConId().toString());
            txtNombre.setText(contenido.getConNombre());
            txtNombre.setToolTipText(contenido.getConNombre());
            txtTemporadas.setText(contenido.getSerie().getSerTemporadas() + "");
            txtTemporadas.setToolTipText(contenido.getSerie().getSerTemporadas() + "");
        }
        btnLimpiar.setEnabled(true);
        btnBorrar.setEnabled(true);
    }

    private void campoTextocambio() {
        if (txtIdentificador.getText().length() != 0) {//Con identificador
            if (txtIdentificador.getText().equals(txtIdentificador.getToolTipText())) {//No cambio identificador
                if (!txtEpisodios.getText().equals(txtEpisodios.getToolTipText()) || !txtNombre.getText().equals(txtNombre.getToolTipText()) || !txtTemporadas.getText().equals(txtTemporadas.getToolTipText())) {//Cambio algún campo
                    btnModificar.setEnabled(OK());
                    btnAgregar.setEnabled(false);
                    btnBorrar.setEnabled(false);
                } else {
                    btnModificar.setEnabled(false);
                    btnAgregar.setEnabled(false);
                    btnBorrar.setEnabled(true);
                }
            } else {
                btnBorrar.setEnabled(false);
                btnModificar.setEnabled(false);
                btnAgregar.setEnabled(OK());
            }
        } else {
            btnAgregar.setEnabled(false);
            btnBorrar.setEnabled(false);
            btnModificar.setEnabled(false);
        }
    }

    /**
     * Creates new form ContactEditor
     */
    public SerieVista() {
        initComponents();
        estadoInicialComponentes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIdentificador = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTemporadas = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEpisodios = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbIdentificadores = new javax.swing.JComboBox();
        cmbNombre = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RedFlix >>> Módulo Series");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Identificador:");

        jLabel2.setText("Nombre:");

        txtIdentificador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIdentificadorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIdentificadorFocusLost(evt);
            }
        });
        txtIdentificador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentificadorActionPerformed(evt);
            }
        });

        txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombreFocusLost(evt);
            }
        });
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        jLabel3.setText("Temporadas:");

        txtTemporadas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTemporadasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTemporadasFocusLost(evt);
            }
        });
        txtTemporadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTemporadasActionPerformed(evt);
            }
        });

        jLabel4.setText("Episodios:");

        txtEpisodios.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEpisodiosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEpisodiosFocusLost(evt);
            }
        });
        txtEpisodios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEpisodiosActionPerformed(evt);
            }
        });

        jLabel5.setText("Buscar por identificador:");

        cmbIdentificadores.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbIdentificadoresItemStateChanged(evt);
            }
        });
        cmbIdentificadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbIdentificadoresMouseReleased(evt);
            }
        });

        cmbNombre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbNombreItemStateChanged(evt);
            }
        });
        cmbNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbNombreMouseReleased(evt);
            }
        });

        jLabel8.setText("Buscar por nombre:");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel5)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(txtIdentificador))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabel3)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(txtTemporadas, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(cmbIdentificadores, 0, 432, Short.MAX_VALUE)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel2)
                                    .add(jLabel4))
                                .add(18, 18, 18)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(txtEpisodios)
                                    .add(txtNombre)))))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jLabel8)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(cmbNombre, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(txtNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(txtIdentificador, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(txtEpisodios, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4)
                    .add(txtTemporadas, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(cmbIdentificadores, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cmbNombre, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel8))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnBorrar.setText("Eliminar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel6.setText("Redflix > Series");

        lblEstado.setText(" ");
        lblEstado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(0, 0, Short.MAX_VALUE)
                                .add(btnLimpiar)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnBorrar)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnModificar)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(btnAgregar)
                                .add(156, 156, 156))
                            .add(jPanel1, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .add(layout.createSequentialGroup()
                        .add(107, 107, 107)
                        .add(jLabel6)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(lblEstado, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(new java.awt.Component[] {btnBorrar, btnLimpiar}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.linkSize(new java.awt.Component[] {btnAgregar, btnModificar}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel6)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnLimpiar)
                    .add(btnBorrar)
                    .add(btnModificar)
                    .add(btnAgregar))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lblEstado)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbIdentificadoresItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbIdentificadoresItemStateChanged
        // TODO add your handling code here:
        if (cmbIdentificadores.getSelectedItem() != null) {
            seleccionado((Integer) cmbIdentificadores.getSelectedItem());
        }
    }//GEN-LAST:event_cmbIdentificadoresItemStateChanged

    private void cmbNombreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbNombreItemStateChanged
        // TODO add your handling code here:
        if (cmbIdentificadores.getItemAt(cmbNombre.getSelectedIndex()) != null) {
            seleccionado((Integer) cmbIdentificadores.getItemAt(cmbNombre.getSelectedIndex()));
        }
    }//GEN-LAST:event_cmbNombreItemStateChanged

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        estadoInicialComponentes();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ciclo2Reto5PU");
        EntityManager em = emf.createEntityManager();
        SerieJpaController serviceSerie = new SerieJpaController(emf);
        ContenidoJpaController serviceContenido = new ContenidoJpaController(emf);

        em.getTransaction().begin();
        try {
            System.out.println(txtIdentificador.getText());
            serviceSerie.destroy(Integer.parseInt(txtIdentificador.getText()));
            serviceContenido.destroy(Integer.parseInt(txtIdentificador.getText()));
            em.getTransaction().commit();
            em.close();

            estadoInicialComponentes();
            lblEstado.setText("El registro ha sido borrado.");
        } catch (Exception e) {
            System.out.println(e.toString());
            lblEstado.setText("El registro no ha sido borrado!");

            em.getTransaction().rollback();
            em.close();
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void txtIdentificadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentificadorActionPerformed
        // TODO add your handling code here:
        campoTextocambio();
    }//GEN-LAST:event_txtIdentificadorActionPerformed

    private void cmbIdentificadoresMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbIdentificadoresMouseReleased
        // TODO add your handling code here:
        try {
            seleccionado((Integer) cmbIdentificadores.getSelectedItem());
        } catch (Exception e) {
            lblEstado.setText("Debe seleccionar una opción!");
        }
    }//GEN-LAST:event_cmbIdentificadoresMouseReleased

    private void txtIdentificadorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdentificadorFocusLost
        // TODO add your handling code here:
        campoTextocambio();
    }//GEN-LAST:event_txtIdentificadorFocusLost

    private void txtIdentificadorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdentificadorFocusGained
        // TODO add your handling code here:
        campoTextocambio();
    }//GEN-LAST:event_txtIdentificadorFocusGained

    private void txtNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusGained
        // TODO add your handling code here:
        campoTextocambio();
    }//GEN-LAST:event_txtNombreFocusGained

    private void txtNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombreFocusLost
        // TODO add your handling code here:
        campoTextocambio();
    }//GEN-LAST:event_txtNombreFocusLost

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
        campoTextocambio();
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtTemporadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTemporadasActionPerformed
        // TODO add your handling code here:
        campoTextocambio();
    }//GEN-LAST:event_txtTemporadasActionPerformed

    private void txtTemporadasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTemporadasFocusGained
        // TODO add your handling code here:
        campoTextocambio();
    }//GEN-LAST:event_txtTemporadasFocusGained

    private void txtTemporadasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTemporadasFocusLost
        // TODO add your handling code here:
        campoTextocambio();
    }//GEN-LAST:event_txtTemporadasFocusLost

    private void txtEpisodiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEpisodiosActionPerformed
        // TODO add your handling code here:
        campoTextocambio();
    }//GEN-LAST:event_txtEpisodiosActionPerformed

    private void txtEpisodiosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEpisodiosFocusGained
        // TODO add your handling code here:
        campoTextocambio();
    }//GEN-LAST:event_txtEpisodiosFocusGained

    private void txtEpisodiosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEpisodiosFocusLost
        // TODO add your handling code here:
        campoTextocambio();
    }//GEN-LAST:event_txtEpisodiosFocusLost

    private void cmbNombreMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbNombreMouseReleased
        // TODO add your handling code here:
        try {
            seleccionado((Integer) cmbIdentificadores.getSelectedItem());
        } catch (Exception e) {
            lblEstado.setText("Debe seleccionar una opción!");
        }
    }//GEN-LAST:event_cmbNombreMouseReleased

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ciclo2Reto5PU");
        EntityManager em = emf.createEntityManager();
        SerieJpaController serviceSerie = new SerieJpaController(emf);
        ContenidoJpaController serviceContenido = new ContenidoJpaController(emf);
        Contenido contenido = new Contenido();
        Serie serie = new Serie();
        serie.setSerId(Integer.parseInt(txtIdentificador.getText()));
        serie.setSerTemporadas(Integer.parseInt(txtTemporadas.getText()));
        serie.setSerEpisodios(Integer.parseInt(txtEpisodios.getText()));
        contenido.setConId(Integer.parseInt(txtIdentificador.getText()));
        contenido.setConNombre(txtNombre.getText());
        contenido.setSerie(serie);
        serie.setContenido(contenido);

        em.getTransaction().begin();
        try {
            serviceSerie.edit(serie);
            serviceContenido.edit(contenido);
            em.getTransaction().commit();

            estadoInicialComponentes();
            lblEstado.setText("El registro ha sido modificado.");
        } catch (Exception e) {
            System.out.println(e.toString());
            em.getTransaction().rollback();
            lblEstado.setText("El registro no ha sido modificado!");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ciclo2Reto5PU");
        EntityManager em = emf.createEntityManager();
        SerieJpaController serviceSerie = new SerieJpaController(emf);
        ContenidoJpaController serviceContenido = new ContenidoJpaController(emf);

        if (serviceContenido.findContenido(Integer.parseInt(txtIdentificador.getText())) == null) {
            Contenido contenido = new Contenido();
            Serie serie = new Serie();
            serie.setSerId(Integer.parseInt(txtIdentificador.getText()));
            serie.setSerTemporadas(Integer.parseInt(txtTemporadas.getText()));
            serie.setSerEpisodios(Integer.parseInt(txtEpisodios.getText()));
            contenido.setConId(Integer.parseInt(txtIdentificador.getText()));
            contenido.setConNombre(txtNombre.getText());
            serie.setContenido(contenido);

            em.getTransaction().begin();
            try {
                serviceContenido.create(contenido);
                serviceSerie.create(serie);
                em.getTransaction().commit();
                em.close();

                estadoInicialComponentes();
                lblEstado.setText("El registro ha sido agregado.");
            } catch (Exception e) {
                System.out.println(e.toString());
                lblEstado.setText("El registro no ha sido agregado!");

                em.getTransaction().rollback();
                em.close();
            }
        } else {
            lblEstado.setText("El Identificador ya está en uso. Operación no realizada!");
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.LookAndFeelInfo[] installedLookAndFeels = javax.swing.UIManager.getInstalledLookAndFeels();
            for (int idx = 0; idx < installedLookAndFeels.length; idx++) {
                if ("Nimbus".equals(installedLookAndFeels[idx].getName())) {
                    javax.swing.UIManager.setLookAndFeel(installedLookAndFeels[idx].getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SerieVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SerieVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SerieVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SerieVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SerieVista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmbIdentificadores;
    private javax.swing.JComboBox cmbNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JTextField txtEpisodios;
    private javax.swing.JTextField txtIdentificador;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTemporadas;
    // End of variables declaration//GEN-END:variables

}
