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
@RequestMapping("/admin")
public class AdministradorControlador {

	@Autowired
	private AdministradorServicio administradorService;
	
	@Autowired
	private ArticuloServicio articuloService;
	
	
	
	

	@GetMapping("")
	public String login(HttpServletRequest request, HttpSession session, Model model) {
		if (request.getSession().getAttribute("admin_id") != null) {
			return "index";
		} else
			return "loginadmin";
	}

	@PostMapping("/login")
	public String validate(RedirectAttributes att, @RequestParam String email, @RequestParam String password,
			HttpServletRequest request, HttpSession session, Model model) {
		
		Administrador admin = administradorService.select(email, password);

		if (admin != null) {
			request.getSession().setAttribute("admin_id", admin.getId());
			return "redirect:/admin/inicio";
		} else {
			att.addFlashAttribute("loginError", "Usuario o contraseña incorrecta");
			return "redirect:/admin";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpSession session, Model model) {
		request.getSession().invalidate();
		return "redirect:/admin";
	}

	@PostMapping("/save")
	public String insertAdmin(RedirectAttributes att, Administrador admin, Model model) {
		administradorService.save(admin);
		att.addFlashAttribute("accion", "Administrador registrado con éxito!");
		return "redirect:/admin";
	}
	
	@GetMapping("/new")
	public String showForm(Model model) {
		return "registeradmin";
	}
	
//	Esto está basado en ProductoController.java del ejercicio de refencia, luego
//	pensamos bien si moverlo a otro lado, básicamente para recuperar los datos
//	del admin. y mostrarlos en el perfil
	

	
	@GetMapping("/inicio")
	public String elPerfil(HttpServletRequest request, Model model) {
		int admin_id = (int) request.getSession().getAttribute("admin_id");
 
		List<Articulo> art = articuloService.getAll();
		
		Administrador adm = this.administradorService.get(admin_id);
		model.addAttribute("admin",adm);
		model.addAttribute("articulos", art);
		return "inicio";
	}
	
	@GetMapping("/templateArticulo")
	public String articulo(HttpServletRequest request, Model model) {
		int admin_id = (int) request.getSession().getAttribute("admin_id");

 
		Articulo display = this.articuloService.get(1);

		List<Articulo> art = articuloService.getAll();
		
		Administrador adm = this.administradorService.get(admin_id);
		model.addAttribute("admin",adm);
		model.addAttribute("articulos", art);
		model.addAttribute("articuloID",display);
		return "templateArticulo";
	}

	@GetMapping("/categorias/{categoria}")
	public String mostrarTodaLaCategoria(@PathVariable("categoria") String categoria, HttpServletRequest request, Model model){
		int admin_id = (int) request.getSession().getAttribute("admin_id");


		List<Articulo> list = this.articuloService.getByCategoria(categoria);
		Administrador adm = this.administradorService.get(admin_id);
		model.addAttribute("admin",adm);
		model.addAttribute("articulosFiltrados", list);
		return "categorias";
	}
	
	@GetMapping("/categorias/{categoria}/{articulo}")
	public String mostrarArticuloDeCategoria(@PathVariable("categoria") String categoria, @PathVariable("articulo") String articulo, HttpServletRequest request, Model model){
		int admin_id = (int) request.getSession().getAttribute("admin_id");
		Integer id_articulo = Integer.parseInt(articulo);
		Articulo display = this.articuloService.buscarPorIdyCategoria(id_articulo, categoria);
		Administrador adm = this.administradorService.get(admin_id);
		model.addAttribute("articuloID",display);
		model.addAttribute("admin",adm);
		return "templateArticulo";
	}
	







}
