package br.com.ftp;
import com.chilkatsoft.*;

public class FTP {
	
	  static {
	    try {
	        System.loadLibrary("chilkat");
	    } catch (UnsatisfiedLinkError e) {
	      System.err.println("Falha ao carregar biblioteca de código nativo.\n" + e);
	      System.exit(1);
	    }
	  }

	  public static void main(String argv[])
	  {
		  // Este exemplo assume que a API Chilkat foi desbloqueada anteriormente. 
		  // Consulte Amostra de desbloqueio global para obter o código de amostra.
		  
	    CkSFtp sftp = new CkSFtp();

	    // Defina alguns tempos limite, em milissegundos:
	    sftp.put_ConnectTimeoutMs(5000);
	    sftp.put_IdleTimeoutMs(15000);

	    // Conecte-se ao servidor SSH.  
	    // A porta SSH padrão = 22 
	    // O nome do host pode ser um nome de host ou endereço IP.
	    String hostname = "sftp.exemplo.com.br";
	    int port = 22;
	    boolean success = sftp.Connect(hostname,port);
	    if (success != true) {
	        System.out.println(sftp.lastErrorText());
	        return;
	        }

	    // Autentique com o servidor SSH. Chilkat SFTP suporta 
	    // tanto a autenticação baseada em senha quanto a autenticação de chave pública
	    //. Este exemplo usa autenticação por senha.
	    success = sftp.AuthenticatePw("login","password");
	    if (success != true) {
	        System.out.println(sftp.lastErrorText());
	        return;
	        }

	    // Após a autenticação, o subsistema SFTP deve ser inicializado
	    success = sftp.InitializeSftp();
	    if (success != true) {
	        System.out.println(sftp.lastErrorText());
	        return;
	        }

	    // Abra um arquivo no servidor para gravação. 
	    // "createTruncate" significa que um novo arquivo é criado; se o arquivo já existir, ele será aberto e truncado.
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

