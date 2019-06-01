package br.com.projetointegrado.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.projetointegrado.model.Usuario;
import br.com.projetointegrado.service.UsuarioService;


public class FazerLogin implements Command {


	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("username");
		String senha = request.getParameter("passwd");
		
		Usuario usuario;
		UsuarioService us = new UsuarioService();
		usuario = us.carregar(email);
		
		if(email.equals(usuario.getEmail()) && senha.equals(usuario.getSenha())) {
			HttpSession session = request.getSession();
			session.setAttribute("logado", usuario);
			System.out.println("Usuario logado com Sucesso! " + usuario.getNome());
		}else {
			System.out.println("Erro ao logar usuario");
			usuario = null;
			HttpSession session = request.getSession();
			session.setAttribute("logado", usuario);
		}
		response.sendRedirect("index.jsp");
	}
		
	}

