package com.bancoazteca.eservice.command.logout;

import com.bancoazteca.elite.beans.ClienteTO;
import com.bancoazteca.eservice.command.base.CommandBase;
import com.bancoazteca.eservice.command.base.session.Session;
import com.bancoazteca.eservice.command.response.Response;

public class LogoutCommand extends CommandBase {

	@Override
	public Response execute(Session session) throws Exception {		
		try{
			ClienteTO clienteTO = (ClienteTO) session.getAttribute(CLIENTE_TO);			
			getDelegate().logout(clienteTO.getUserName());
		}catch(Throwable e){
			e.printStackTrace();
		}finally{
			session.invalidate();
		}
				
		return new Response();
	}
	
	

}
