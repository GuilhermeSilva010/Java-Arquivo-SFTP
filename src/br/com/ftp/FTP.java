package br.com.ftp;
import com.chilkatsoft.*;

public class FTP {
	
	  static {
	    try {
	        System.loadLibrary("chilkat");
	    } catch (UnsatisfiedLinkError e) {
	      System.err.println("Falha ao carregar biblioteca de c�digo nativo.\n" + e);
	      System.exit(1);
	    }
	  }

	  public static void main(String argv[])
	  {
		  // Este exemplo assume que a API Chilkat foi desbloqueada anteriormente. 
		  // Consulte Amostra de desbloqueio global para obter o c�digo de amostra.
		  
	    CkSFtp sftp = new CkSFtp();

	    // Defina alguns tempos limite, em milissegundos:
	    sftp.put_ConnectTimeoutMs(5000);
	    sftp.put_IdleTimeoutMs(15000);

	    // Conecte-se ao servidor SSH.  
	    // A porta SSH padr�o = 22 
	    // O nome do host pode ser um nome de host ou endere�o IP.
	    String hostname = "sftp.exemplo.com.br";
	    int port = 22;
	    boolean success = sftp.Connect(hostname,port);
	    if (success != true) {
	        System.out.println(sftp.lastErrorText());
	        return;
	        }

	    // Autentique com o servidor SSH. Chilkat SFTP suporta 
	    // tanto a autentica��o baseada em senha quanto a autentica��o de chave p�blica
	    //. Este exemplo usa autentica��o por senha.
	    success = sftp.AuthenticatePw("login","password");
	    if (success != true) {
	        System.out.println(sftp.lastErrorText());
	        return;
	        }

	    // Ap�s a autentica��o, o subsistema SFTP deve ser inicializado
	    success = sftp.InitializeSftp();
	    if (success != true) {
	        System.out.println(sftp.lastErrorText());
	        return;
	        }

	    // Abra um arquivo no servidor para grava��o. 
	    // "createTruncate" significa que um novo arquivo � criado; se o arquivo j� existir, ele ser� aberto e truncado.
	    // Para enviar o arquivo para um Diretorio especifico : "/DEV/IN/Arquivo.txt"
	    String handle = sftp.openFile("Arquivo.txt","escrever","createTruncate");
	    if (sftp.get_LastMethodSuccess() != true) {
	        System.out.println(sftp.lastErrorText());
	        return;
	        }

	    // Grave algum texto no arquivo: 
	    success = sftp.WriteFileText(handle,"ansi","Teste Escrita Arquivo - ");
	    if (success != true) {
	        System.out.println(sftp.lastErrorText());
	        return;
	        }

	    success = sftp.WriteFileText(handle,"ansi","- 1234567890 ");
	    if (success != true) {
	        System.out.println(sftp.lastErrorText());
	        return;
	        }

	    success = sftp.WriteFileText(handle,"ansi"," - ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	    if (success != true) {
	        System.out.println(sftp.lastErrorText());
	        return;
	        }

	    // Feche o arquivo..
	    success = sftp.CloseHandle(handle);
	    if (success != true) {
	        System.out.println(sftp.lastErrorText());
	        return;
	        }

	    System.out.println("Success.");
	  }
	}

