/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverdevelopment;

/**
 *
 * @author Богдан
 */
public class Contacts {

    private final String contactName;
    private final String contactIp;

    public Contacts(String name, String ip) {
        this.contactName = name;
        this.contactIp = ip;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactIp() {
        return contactIp;
    }
    

}
