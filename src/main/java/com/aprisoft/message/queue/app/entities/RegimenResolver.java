/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aprisoft.message.queue.app.entities;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author User10
 */
@Entity
@Table(name = "REGIMEN_RESOLVER")
@Data
public class RegimenResolver implements Serializable {

    @Id
    @Column(name = "REGIMENSYS")
    private String regimenSys;

    @Id
    @Column(name = "REGIMEN")
    private String regimen;
}
