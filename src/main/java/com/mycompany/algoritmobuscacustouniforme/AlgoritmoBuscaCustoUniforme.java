/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.algoritmobuscacustouniforme;

/**
 *
 * @author marce
 */

import java.util.*;

class Nodo {
    int vertice;
    int custo;

    Nodo(int vertice, int custo) {
        this.vertice = vertice;
        this.custo = custo;
    }
}

class Grafo {
    private int numVertices; // Número de vértices
    private LinkedList<Nodo> adjList[]; // Lista de adjacência com custo

    // Construtor
    Grafo(int numVertices) {
        this.numVertices = numVertices;
        adjList = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Adiciona uma aresta ao grafo com custo
    void adicionarAresta(int v, int w, int custo) {
        adjList[v].add(new Nodo(w, custo));
        adjList[w].add(new Nodo(v, custo)); // Para grafos não direcionados
    }

    // Realiza a busca de custo uniforme a partir de um vértice fonte
    void UCS(int inicio, int objetivo) {
        // PriorityQueue para armazenar os nós com base no custo
        PriorityQueue<Nodo> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.custo));

        // Mapa para armazenar o custo mínimo para chegar a cada vértice
        Map<Integer, Integer> custos = new HashMap<>();

        // Inicializa todos os custos como infinito
        for (int i = 0; i < numVertices; i++) {
            custos.put(i, Integer.MAX_VALUE);
        }

        // Adiciona o vértice inicial na fila com custo 0
        pq.add(new Nodo(inicio, 0));
        custos.put(inicio, 0);

        while (!pq.isEmpty()) {
            Nodo atual = pq.poll();
            int vertice = atual.vertice;

            // Se o vértice atual é o objetivo, imprime o custo e retorna
            if (vertice == objetivo) {
                System.out.println("Custo mínimo para alcançar o vértice " + objetivo + " é " + atual.custo);
                return;
            }

            // Processa todos os vizinhos do vértice atual
            for (Nodo vizinho : adjList[vertice]) {
                int novoCusto = atual.custo + vizinho.custo;
                if (novoCusto < custos.get(vizinho.vertice)) {
                    custos.put(vizinho.vertice, novoCusto);
                    pq.add(new Nodo(vizinho.vertice, novoCusto));
                }
            }
        }

        System.out.println("Não há caminho do vértice " + inicio + " ao vértice " + objetivo);
    }
}

public class AlgoritmoBuscaCustoUniforme  {
    public static void main(String args[]) {
        Grafo g = new Grafo(5);

        g.adicionarAresta(0, 1, 10);
        g.adicionarAresta(0, 4, 3);
        g.adicionarAresta(1, 2, 2);
        g.adicionarAresta(1, 4, 4);
        g.adicionarAresta(2, 3, 9);
        g.adicionarAresta(3, 4, 7);
        g.adicionarAresta(4, 2, 6);

        System.out.println("Busca de Custo Uniforme (UCS) do vértice 0 ao vértice 3:");

        g.UCS(0, 3);
    }
}
