package com.example.corretordegabarito;

public class Foto {
    private int largura;
    private int altura;
    private String nomearquivo;
    private long tamanho;
    private String caminhoarquivo;
    private String formato;
    private char tipo;

    /**
     * Construtor da classe.
     * @param largura - Largura da foto
     * @param altura - Altura da foto
     * @param nomearquivo - Nome do arquivo da foto
     * @param tamanho - Tamanho da foto
     * @param caminhoarquivo - Caminho do arquivo da foto
     * @param formato - Formato da foto
     * @param tipo - Tipo da foto
     */

    public Foto (int largura,int altura,String nomearquivo,long tamanho,String caminhoarquivo,String formato,char tipo)
    {
        this.largura = largura;
        this.altura = altura;
        this.nomearquivo = nomearquivo;
        this.tamanho = tamanho;
        this.caminhoarquivo = caminhoarquivo;
        this.formato = formato;
        this.tipo = tipo;
    }

    /**
     * Retorna o tipo da foto.
     * @return char tipo
     */

    public char getTipo() {
        return tipo;
    }

    /**
     * Retorna altura da foto.
     * @return int altura
     */

    public int getAltura() {
        return altura;
    }

    /**
     * Retorna largura da foto.
     * @return int largura
     */

    public int getLargura() {
        return largura;
    }

    /**
     * Retorna tamanho da foto.
     * @return long tamanho
     */

    public long getTamanho() {
        return tamanho;
    }

    /**
     * Retorna caminho do arquivo da foto.
     * @return String caminhoarquivo
     */

    public String getCaminhoarquivo() {
        return caminhoarquivo;
    }

    /**
     * Retorna o formato da foto.
     * @return String formato
     */

    public String getFormato() {
        return formato;
    }

    /**
     * Retorna nome do arquivo da foto.
     * @return String nomearquivo
     */

    public String getNomearquivo() {
        return nomearquivo;
    }

    /**
     * Seta altura da foto.
     * @param altura int
     */

    public void setAltura(int altura) {
        this.altura = altura;
    }

    /**
     * Seta o caminho do arquivo da foto.
     * @param caminhoarquivo String
     */

    public void setCaminhoarquivo(String caminhoarquivo) {
        this.caminhoarquivo = caminhoarquivo;
    }

    /**
     * Seta o formato da foto.
     * @param formato String
     */

    public void setFormato(String formato) {
        this.formato = formato;
    }

    /**
     * Seta a largura da foto.
     * @param largura int
     */

    public void setLargura(int largura) {
        this.largura = largura;
    }

    /**
     * Seta o nome do arquivo da foto.
     * @param nomearquivo String
     */

    public void setNomearquivo(String nomearquivo) {
        this.nomearquivo = nomearquivo;
    }

    /**
     * Seta o tamanho da foto.
     * @param tamanho long
     */

    public void setTamanho(long tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * Seta o tipo da foto.
     * @param tipo char
     */

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
}
