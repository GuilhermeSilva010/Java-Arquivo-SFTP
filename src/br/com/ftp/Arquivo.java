package br.com.ftp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Arquivo {

		  private static PrintWriter gravarArq;

		public static void main(String[] args) throws IOException {
			int i, n;
		    try (Scanner ler = new Scanner(System.in)) {
			

				System.out.printf("Informe o número para a tabuada:\n");
				n = ler.nextInt();

				FileWriter arq = new FileWriter("c:\\tabuada.txt");
				gravarArq = new PrintWriter(arq);

				gravarArq.printf("+--Resultado--+%n");
				for (i=1; i<=10; i++) {
				  gravarArq.printf("| %2d X %d = %2d |%n", i, n, (i*n));
				}
				gravarArq.printf("+-------------+%n");

				arq.close();
			}
		    System.out.printf("\nTabuada do %d foi gravada com sucesso em \"c:\\tabuada.txt\".\n", n);
		  }

	}

