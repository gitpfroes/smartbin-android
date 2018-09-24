package br.pucminas.smartbin.model;

public class Jornada {
    private String id;
    private String caminhao;
    private String data;
    private double kgPlatico;
    private double kgPapel;
    private double kgMetal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(String caminhao) {
        this.caminhao = caminhao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getKgPlatico() {
        return kgPlatico;
    }

    public void setKgPlatico(double kgPlatico) {
        this.kgPlatico = kgPlatico;
    }

    public double getKgPapel() {
        return kgPapel;
    }

    public void setKgPapel(double kgPapel) {
        this.kgPapel = kgPapel;
    }

    public double getKgMetal() {
        return kgMetal;
    }

    public void setKgMetal(double kgMetal) {
        this.kgMetal = kgMetal;
    }
}
