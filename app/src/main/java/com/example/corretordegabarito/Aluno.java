package com.example.corretordegabarito;

public class Aluno extends Foto {

    /**
     * Construtor da classe.
     * @param largura - Largura da foto do tipo aluno
     * @param altura - Altura da foto do tipo aluno
     * @param nomearquivo - Nome do arquivo da foto do tipo aluno
     * @param tamanho - Tamanho da foto do tipo aluno
     * @param caminhoarquivo - Caminho do arquivo da foto do tipo aluno
     * @param formato - Formato da foto do tipo aluno
     */

    public Aluno (int largura,int altura,String nomearquivo,int tamanho,String caminhoarquivo,String formato)
    {
        super(largura,altura,nomearquivo,tamanho,caminhoarquivo,formato);
    }
}
