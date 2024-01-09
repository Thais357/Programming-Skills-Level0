package cursojava.curso.controllers;

import cursojava.curso.dao.BancoDao;
import cursojava.curso.dao.UsuarioDao;
import cursojava.curso.dto.BancoDTO;
import cursojava.curso.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BancoController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private BancoDao bancoDao;



    @RequestMapping(value = "api/deposit",method = RequestMethod.POST)
    public void depositar(@RequestBody BancoDTO datos){

        Usuario user = usuarioDao.obtenerUsuario(datos.getEmail());
        bancoDao.depositar(datos.getMonto(),user);

    }


    @RequestMapping(value = "api/withdraw",method = RequestMethod.POST)
    public String extraer(@RequestBody BancoDTO datos){
        Usuario user = usuarioDao.obtenerUsuario(datos.getEmail());
        return bancoDao.extraer(datos.getMonto(),user);
    }

    @RequestMapping(value = "api/view",method = RequestMethod.GET)
    public String verSaldo(@RequestParam String email){
        Usuario user = usuarioDao.obtenerUsuario(email);
        return String.valueOf(bancoDao.verSaldo(user));
    }

    @RequestMapping(value = "api/transfer",method = RequestMethod.POST)
    public String transferir(@RequestBody BancoDTO datos){

      Usuario user = usuarioDao.obtenerUsuario(datos.getEmail());
      Usuario userDestino = usuarioDao.obtenerUsuario(datos.getEmailUsuarioDestino());

      return bancoDao.transferir(user,userDestino, datos.getMonto());

    }


}
