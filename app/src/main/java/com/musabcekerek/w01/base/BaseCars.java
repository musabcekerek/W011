package com.musabcekerek.w01.base;

public  class BaseCars {

    private String CarName;
    private String ProductionWhell;
    private String ProductionDoor;
    private String ProductionEngine;


    public BaseCars(String CarName,String productionWhell, String productionDoor, String productionEngine) {
        this.CarName = CarName;
        this.ProductionWhell = productionWhell;
        this.ProductionDoor = productionDoor;
        this.ProductionEngine = productionEngine;
    }


    public BaseCars() {
    }


    public String getCarName() {
        return CarName;
    }

    public void setCarName(String carName) {
        CarName = carName;
    }


    public String getProductionWhell() {
        return ProductionWhell;
    }

    public void setProductionWhell(String productionWhell) {
        ProductionWhell = productionWhell;
    }

    public String getProductionDoor() {
        return ProductionDoor;
    }

    public void setProductionDoor(String productionDoor) {
        ProductionDoor = productionDoor;
    }

    public String getProductionEngine() {
        return ProductionEngine;
    }

    public void setProductionEngine(String productionEngine) {
        ProductionEngine = productionEngine;
    }
}


