package com.musabcekerek.w01.manager;

import com.musabcekerek.w01.base.BaseCars;
import com.musabcekerek.w01.brand.AUDI;
import com.musabcekerek.w01.brand.BMW;
import com.musabcekerek.w01.brand.MERCEDES;
import com.musabcekerek.w01.enums.BrandEnums;

public class CarManager extends BaseCars {

    private static CarManager INSTANCE ;
    private BrandEnums brandEnums;


    private CarManager(BrandEnums brandEnums) {
        this.brandEnums = brandEnums;

    }

    public CarManager(String carName, String productionWhell, String productionDoor, String productionEngine) {
        super(carName, productionWhell, productionDoor, productionEngine);
    }

    public static CarManager getInstance(BrandEnums brandEnums ) {


            return INSTANCE = new CarManager(brandEnums);



    }

    public BaseCars getCars() {
        if(brandEnums == BrandEnums.AUDI)
            return new AUDI("AUDI","Üretilen teker sayısı: 4","Üretilen kapı sayısı: 5","Üretilen Motor Sayısı: 1");
        else if(brandEnums == BrandEnums.BMW) {
            return new BMW("BMW","Üretilen teker sayısı: 6","Üretilen kapı sayısı: 2","Üretilen Motor Sayısı: 2"); }

        else if(brandEnums == BrandEnums.MERCEDES) {
            return new MERCEDES("MERCEDES","Üretilen teker sayısı: 8","Üretilen kapı sayısı: 2","Üretilen Motor Sayısı: 2");
        }
        return null;
    }

}
