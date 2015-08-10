package model;

/**
 * Created by ismael on 19/03/2015.
 */
public class Desejo {

    private Integer id;
    private String produto;
    private String categoria;
    private String lojas;
    private Double pMinimo;
    private Double pMaximo;

    public Desejo(int id, String produto, String categoria, String lojas, Double pminimo, Double pmaximo) {
        this.id = id;
        this.produto = produto;
        this.categoria = categoria;
        this.lojas = lojas;
        this.pMinimo = pminimo;
        this.pMaximo = pmaximo;
    }

    public Desejo(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLojas() {
        return lojas;
    }

    public void setLojas(String lojas) {
        this.lojas = lojas;
    }

    public Double getpMinimo() {
        return pMinimo;
    }

    public void setpMinimo(Double pMinimo) {
        this.pMinimo = pMinimo;
    }

    public Double getpMaximo() {
        return pMaximo;
    }

    public void setpMaximo(Double pMaximo) {
        this.pMaximo = pMaximo;
    }
}
