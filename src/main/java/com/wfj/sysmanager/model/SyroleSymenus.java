package com.wfj.sysmanager.model;

/**
 * SyroleSyresources entity. @author MyEclipse Persistence Tools
 */

public class SyroleSymenus implements java.io.Serializable {

    // Fields

    private String id;
    private Syrole syrole;
    private Symenu symenu;

    // Constructors

    /**
     * default constructor
     */
    public SyroleSymenus() {
    }

    /**
     * full constructor
     */
    public SyroleSymenus(String id, Syrole syrole, Symenu symenu) {
        this.id = id;
        this.syrole = syrole;
        this.symenu = symenu;
    }

    // Property accessors

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Syrole getSyrole() {
        return this.syrole;
    }

    public void setSyrole(Syrole syrole) {
        this.syrole = syrole;
    }

    public Symenu getSymenu() {
        return symenu;
    }

    public void setSymenu(Symenu symenu) {
        this.symenu = symenu;
    }
}