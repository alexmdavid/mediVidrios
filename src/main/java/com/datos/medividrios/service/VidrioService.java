package com.datos.medividrios.service;

public class VidrioService {

    public double[] cubicar(double anchoCm, double altoCm) {
        double anchoM = anchoCm / 100.0;
        double altoM = altoCm / 100.0;

        double anchoCubicado = Math.ceil(anchoM * 10) / 10.0;
        double altoCubicado = Math.ceil(altoM * 10) / 10.0;

        return new double[]{anchoCubicado, altoCubicado};
    }
}
