package com.toolbox.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.toolbox.modelo.Administrador;
import com.toolbox.modelo.Articulo;
import com.toolbox.servicio.AdministradorServicio;
import com.toolbox.servicio.ArticuloServicio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class GenericoControlador {
	
	@GetMapping("/")
	public String rutaPorDefecto() {
		return "redirect:/admin";
	}

}