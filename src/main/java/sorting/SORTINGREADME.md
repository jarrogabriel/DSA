# Algoritmos de Ordenação

Este repositório contém a documentação e implementação de alguns algoritmos de ordenação.

## BubbleSort

O algoritmo BubbleSort é um método simples de ordenação que funciona repetidamente percorrendo a lista, 
comparando elementos adjacentes e trocando-os de posição, se necessário.

## Pseudocódigo

```plaintext
Para cada elemento no array:
  - Compare o elemento atual com o próximo.
  - Se o atual for maior, troque de posição.
  - Se nenhuma troca foi feita durante uma passagem, encerre o processo.
```

## RadixSort

O RadixSort ordena os números processando dígito por dígito, pode começar pelo 
menos significativo (LSD - Least Significant Digit) 
ou pelo mais significativo (MSD - Most Significant Digit).

## Pseudocódigo

```plaintext
    1. Encontre o maior número no array para descobrir a quantidade de dígitos.
    2. Para cada dígito:
        a. Distribua os números em buckets com base no dígito atual.
        b. Recombine os números na ordem dos buckets.
```