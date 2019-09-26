package com.example.corretordegabarito;

public class Oficial extends Foto {

    /**
     * Construtor da classe.
     * @param largura - Largura da foto do tipo oficial
     * @param altura - Altura da foto do tipo oficial
     * @param nomearquivo - Nome do arquivo da foto do tipo oficial
     * @param tamanho - Tamanho da foto do tipo oficial
     * @param caminhoarquivo - Caminho do arquivo da foto do tipo oficial
     * @param formato - Formato da foto do tipo oficial
     */

    public Oficial (int largura,int altura,String nomearquivo,int tamanho,String caminhoarquivo,String formato)
    {
        super(largura,altura,nomearquivo,tamanho,caminhoarquivo,formato);
    }
}
