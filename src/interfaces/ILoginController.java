package interfaces;

import utils.Autenticazione;

public interface ILoginController {
	Autenticazione verificaCredenziali(String username,String password);
	void printLogin();
}
