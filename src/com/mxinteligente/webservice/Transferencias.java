package com.mxinteligente.webservice;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import javax.ws.rs.Consumes;


import com.mxinteligente.config.SpringApplicationContext;

import com.mxinteligente.infra.model.nego.MailService;
import com.mxinteligente.infra.model.nego.TemplateHtml;
import com.mxinteligente.model.entidades.Categresos;
import com.mxinteligente.model.entidades.CategresosId;
import com.mxinteligente.model.entidades.Catingresos;
import com.mxinteligente.model.entidades.CatingresosId;

import com.mxinteligente.model.entidades.Usuarios;

import com.mxinteligente.model.negocio.RippleServI;
import com.mxinteligente.model.negocio.GeneralServI;
import com.mxinteligente.model.negocio.SesionesServI;
import com.mxinteligente.model.negocio.VentasServI;

import com.sun.jersey.api.spring.Autowire;

import static java.lang.Math.round;
import static java.lang.Math.random;
import static java.lang.Math.pow;
import static java.lang.Math.abs;
import static java.lang.Math.min;

import static org.apache.commons.lang.StringUtils.leftPad;

@Path("/ripple/")
@Produces({ "application/json", "application/xml" })
@Consumes({ "application/json", "application/xml" })
@Scope("singleton")
@Autowire
public class Transferencias {

	@Autowired(required = true)
	private GeneralServI generalServ;

	@Autowired(required = true)
	private PasswordEncoder passwordEncoder;

	@Autowired(required = true)
	SesionesServI sesionesServ;

	@Autowired(required = true)
	VentasServI ventasServ;

	@Autowired(required = true)
	MailService mailService;
	
	@Autowired(required = true)
	RippleServI rippleServ;

	private static int PUNTOS=5;
	@Autowired
	private TemplateHtml tamplateHtml;
	
	private static final String WALLET_SALDO="8HMpx1b34rDidF6rRjrGPo";
	
	@GET
	@Path("setWallet/{mail}")
	@Produces("application/json")
	public Usuarios registrarUsuario(@PathParam("mail") String mail) {
		Usuarios user;
		try {
			if (mail != null ) {
				try {
					user = generalServ.buscarUsuario(mail);
					
					if (user == null) {
						SimpleDateFormat formatoDeFecha = new SimpleDateFormat(
								"dd/MM/yyyy");

						String codigo = new RandomAlphaNum().gen(25);
						user = new Usuarios();
						user.setApm(mail);
						user.setApp(mail);
						user.setEmail(mail);
						user.setEstatus(1);
						user.setNombre(mail);
						user.setFechnac(new Date());
						user.setDomicilio(mail);
						user.setClabe(mail);
						user.setCodigo(codigo);
						user.setPassword(mail);
						user.setNip(99999);
						user.setRol("ROLE_USER");
						
						String walletId = rippleServ.crearWallet(mail, "");
						if(walletId!=null && !walletId.equals("")){
							user.setWalletID(walletId);
						}
						else
						{
							walletId = rippleServ.crearWallet(mail, WALLET_SALDO);
						}
						user.setAddressWallet(rippleServ.obtenerAddress(walletId));

						generalServ.insertar(user);

						user = (Usuarios) generalServ.buscarUsuario(mail);

						Categresos egresos = new Categresos();
						egresos.setId(new CategresosId());

						egresos.getId().setUsuariosId(user.getId());
						egresos.getId().setId(1);
						egresos.setNombre("Transferencia");

						generalServ.insertar(egresos);

						Catingresos ingresos = new Catingresos();
						ingresos.setId(new CatingresosId());
						ingresos.getId().setId(1);
						ingresos.getId().setUsuariosId(user.getId());
						ingresos.setNombre("Transferencia");

						generalServ.insertar(ingresos);

//						String body1 = tamplateHtml
//								.templateVerificacion("https://www.saldo.mx/Saldos/Autentificar/validacion.zul?codigo="
//										+ codigo);
//						mailService.sendMail("avisos@saldo.mx",
//								user.getEmail(), "EMAIL CONFIRMACI�N ", body1);
						
						
						user.setWalletID("");
						return user;
					} else {
						
						
						if(user.getWalletID()!=null && !user.getWalletID().equals("")){
							return user;
						}else{
							String walletId = rippleServ.crearWallet(mail, "");
							
							if(walletId!=null && !walletId.equals("")){
								user.setWalletID(walletId);
							}
							else
							{
								walletId = rippleServ.crearWallet(mail, WALLET_SALDO);
							}
							
							user.setWalletID(walletId);
							user.setAddressWallet(rippleServ.obtenerAddress(walletId));
							generalServ.actualizar(user);
							
							user.setWalletID("");
						}

						return user;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return new Usuarios();

				}
			} else {
				return new Usuarios();
			}

		}

		catch (Exception e) {
			e.printStackTrace();

			return new Usuarios();
		}
	}
	
	@GET
	@Path("getAddress/{mail}")
	@Produces("application/json")
	public Usuarios getAddress(@PathParam("mail") String mail) {
		Usuarios user;
		try {
			if (mail != null ) {
				try {
					user = generalServ.buscarUsuario(mail);
					
					if (user == null) {						
						return new Usuarios();
					} else {
						user.setWalletID("");
						return user;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return new Usuarios();

				}
			} else {
				return new Usuarios();
			}

		}

		catch (Exception e) {
			e.printStackTrace();

			return new Usuarios();
		}
	}
	
	
	@GET
	@Path("getBalance/{mail}")
	@Produces("text/plain")
	public String getBalance(@PathParam("mail") String mail) {
		Usuarios user;
		try {
			if (mail != null ) {
				try {
					user = generalServ.buscarUsuario(mail);
					
					if (user == null) {						
						return "{null}";
					} else {
						return rippleServ.obtenerBalance(user.getWalletID());
					}
				} catch (Exception e) {
					e.printStackTrace();
					return "{null}";

				}
			} else {
				return "{null}";
			}

		}

		catch (Exception e) {
			e.printStackTrace();

			return "{null}";
		}
	}
	
	@GET
	@Path("getBalanceUnconfirmed/{mail}")
	@Produces("text/plain")
	public String getBalanceUnconfirmed(@PathParam("mail") String mail) {
		Usuarios user;
		try {
			if (mail != null ) {
				try {
					user = generalServ.buscarUsuario(mail);
					
					if (user == null) {						
						return "{null}";
					} else {
						return rippleServ.obtenerBalanceSinConfirmar(user.getWalletID());
					}
				} catch (Exception e) {
					e.printStackTrace();
					return "{null}";

				}
			} else {
				return "{null}";
			}

		}

		catch (Exception e) {
			e.printStackTrace();

			return "{null}";
		}
	}
	
	
	@GET
	@Path("doTransfer/{mail}/{address}/{monto}/{concepto}")
	@Produces("text/plain")
	public String doTransfer(@PathParam("mail") String mail,
			@PathParam("address") String address,
			@PathParam("monto") String monto,
			@PathParam("concepto") String concepto
			) {
		Usuarios user;
		try {
			if (mail != null ) {
				try {
					user = generalServ.buscarUsuario(mail);
					
					if (user == null) {						
						return "{null}";
					} else {
						return rippleServ.realizarTransferencia(user.getWalletID(), address, monto, "BTC", "avisos@saldo.mx", concepto, "en");
					}
				} catch (Exception e) {
					e.printStackTrace();
					return "{null}";

				}
			} else {
				return "{null}";
			}

		}

		catch (Exception e) {
			e.printStackTrace();

			return "{null}";
		}
	}
	
	@GET
	@Path("setPassword/{mail}/{password}")
	@Produces("application/json")
	public Usuarios registrarPassword(@PathParam("mail") String mail,
			@PathParam("password") String password) {
		Usuarios user;
		try {
			if (mail != null ) {
				try {
					user = generalServ.buscarUsuario(mail);
					
					if (user == null) {
						
						return new Usuarios();
					} else {
						passwordEncoder = (PasswordEncoder) SpringApplicationContext
								.getBean("passwordEncoder");
						String paswd = passwordEncoder.encodePassword(password,	null);
						user.setPassword(paswd);
						generalServ.actualizar(user);
						user.setWalletID("");
						user.setPassword("");
						return user;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return new Usuarios();

				}
			} else {
				return new Usuarios();
			}

		}

		catch (Exception e) {
			e.printStackTrace();

			return new Usuarios();
		}
	}
	
	@GET
	@Path("setPin/{mail}/{pin}")
	@Produces("application/json")
	public Usuarios registrarPin(@PathParam("mail") String mail,
			@PathParam("pin") String pin) {
		Usuarios user;
		try {
			if (mail != null ) {
				try {
					user = generalServ.buscarUsuario(mail);
					
					if (user == null) {
						
						return new Usuarios();
					} else {						
						user.setNip(new Integer(pin));
						generalServ.actualizar(user);
						user.setWalletID("");
						user.setPassword("");
						return user;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return new Usuarios();

				}
			} else {
				return new Usuarios();
			}

		}

		catch (Exception e) {
			e.printStackTrace();

			return new Usuarios();
		}
	}
	
	
	@GET
	@Path("doTransferPin/{mail}/{pin}/{address}/{monto}/{concepto}")
	@Produces("text/plain")
	public String doTransferPin(@PathParam("mail") String mail,
			@PathParam("address") String address,
			@PathParam("monto") String monto,
			@PathParam("concepto") String concepto,
			@PathParam("pin") String pin
			) {
		Usuarios user;
		try {
			if (mail != null ) {
				try {
					user = generalServ.buscarUsuario(mail);
					
					if (user == null) {						
						return "{null}";
					} else {
						if(pin.equals(user.getNip())){
							return rippleServ.realizarTransferencia(user.getWalletID(), address, monto, "BTC", "avisos@saldo.mx", concepto, "en");	
						}else{
							return "{incorrect user}";		
						}
						
					}
				} catch (Exception e) {
					e.printStackTrace();
					return "{null}";

				}
			} else {
				return "{null}";
			}

		}

		catch (Exception e) {
			e.printStackTrace();

			return "{null}";
		}
	}
	
	
	
	class RandomAlphaNum {
		public String gen(int length) {
			StringBuffer sb = new StringBuffer();
			for (int i = length; i > 0; i -= 12) {
				int n = min(12, abs(i));
				sb.append(leftPad(
						Long.toString(round(random() * pow(36, n)), 36), n, '0'));
			}
			return sb.toString();
		}
	}

	
}
