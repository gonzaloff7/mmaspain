package mma.mmaspain.controllers;

import mma.mmaspain.dao.UsuarioDAO;
import mma.mmaspain.dao.PrediccionDAO;
import mma.mmaspain.model.Prediccion;
import mma.mmaspain.model.Usuario;
import mma.mmaspain.model.Combate;
import org.springframework.stereotype.Controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import mma.mmaspain.model.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import mma.mmaspain.dao.EventoDAO;
import mma.mmaspain.dao.CombateDAO;
import mma.mmaspain.dao.PeleadorDAO;
import mma.mmaspain.dao.ComentarioDAO;
import mma.mmaspain.model.Comentario;
import mma.mmaspain.model.Peleador;
import mma.mmaspain.dao.PuntuacionDAO;
import mma.mmaspain.model.Puntuacion;
import mma.mmaspain.dao.UsuarioDAO;
import mma.mmaspain.dao.EventoDAO;
import mma.mmaspain.dao.CombateDAO;

import mma.mmaspain.dao.PeleadorDAO;
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {

        return "login";
    }
    @GetMapping("/evento")
    public String verEvento(

            @RequestParam int id,

            Model model
    ) {

        model.addAttribute(

                "combates",

                CombateDAO.obtenerCombates(id)
        );

        return "combates";
    }

    @PostMapping("/login")
    public String login(

            @RequestParam String email,

            HttpSession session,
            @RequestParam String password,

            Model model
    ) {

        Usuario usuario =
                UsuarioDAO.login(
                        email,
                        password
                );

        if (usuario != null) {

            model.addAttribute(
                    "usuario",
                    usuario
            );

            model.addAttribute(
                    "eventos",
                    EventoDAO.obtenerEventos()
            );
            session.setAttribute(
                    "usuario",
                    usuario
            );

            return "redirect:/home";
        }

        model.addAttribute(
                "error",
                "Email o contraseña incorrectos"
        );

        return "login";
    }
    
    @GetMapping("/home")
    public String home(

            HttpSession session,

            Model model
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        model.addAttribute(
                "usuario",
                usuario
        );

        model.addAttribute(

                "eventos",

                EventoDAO.obtenerEventos()
        );

        return "index";
    }
    
    @GetMapping("/register")
    public String registerPage() {

        return "registro";
    }

    @PostMapping("/register")
    public String register(

            @RequestParam String nombre,

            @RequestParam String email,

            @RequestParam String password,

            Model model
    ) {

        boolean registrado =
                UsuarioDAO.registrarUsuario(

                        nombre,

                        email,

                        password
                );

        if (!registrado) {

            model.addAttribute(

                    "error",

                    "⚠ Usuario o email ya existe"
            );

            return "registro";
        }

        return "login";
    }
    @GetMapping("/prediccion")
    public String abrirPrediccion(

            @RequestParam int combate,
            
            @RequestParam int evento,

            @RequestParam String p1,

            @RequestParam String p2,

            Model model
    ) {

        Combate c =
                new Combate(

                        combate,

                        p1,

                        p2
                );

        model.addAttribute(
                "combate",
                c
        );
 
        model.addAttribute(
                "evento",
                evento
        );
        return "prediccion";
    }
    @PostMapping("/guardarPrediccion")
    public String guardarPrediccion(

            @RequestParam int combate,

            @RequestParam int evento,

            HttpSession session,

            @RequestParam int ganador,

            @RequestParam String metodo,

            @RequestParam int round
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        Prediccion p =
                new Prediccion(

                        usuario.getId(),

                        combate,

                        ganador,

                        metodo,

                        round
                );

        PrediccionDAO.insertarPrediccion(p);

        return "redirect:/evento?id=" + evento;
    }
    
    @GetMapping("/perfil")
    public String perfil(

            HttpSession session,

            Model model
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        int predicciones =
                PrediccionDAO.contarPredicciones(
                        usuario.getId()
                );

        int aciertos =
                PrediccionDAO.contarAciertos(
                        usuario.getId()
                );

        String metodo =
                PrediccionDAO.metodoFavorito(
                        usuario.getId()
                );

        double porcentaje = 0;

        if (predicciones > 0) {

            porcentaje =
                    (double) aciertos
                    / predicciones
                    * 100;
        }

        String rango =
                "Novato";

        if (aciertos >= 5)
            rango = "Analista";

        if (aciertos >= 10)
            rango = "Experto";

        if (aciertos >= 20)
            rango = "GOAT MMA";

        int actividad =
                predicciones + aciertos;

        model.addAttribute(
                "usuario",
                usuario
        );

        model.addAttribute(
                "predicciones",
                predicciones
        );

        model.addAttribute(
                "aciertos",
                aciertos
        );

        model.addAttribute(
                "porcentaje",
                porcentaje
        );

        model.addAttribute(
                "metodo",
                metodo
        );

        model.addAttribute(
                "rango",
                rango
        );

        model.addAttribute(
                "actividad",
                actividad
        );

        return "perfil";
    }
    
    @GetMapping("/ranking")
    public String ranking(

            Model model
    ) {

        model.addAttribute(

                "ranking",

                PrediccionDAO.obtenerRanking()
        );

        return "ranking";
    }
    
    @GetMapping("/buscador")
    public String buscador(

            @RequestParam(
                    required = false
            ) String nombre,

            Model model
    ) {

        if (nombre == null) {

            nombre = "";
        }

        model.addAttribute(

                "busqueda",

                nombre
        );

        model.addAttribute(

                "peleadores",

                PeleadorDAO.buscarPeleadores(
                        nombre
                )
        );

        return "buscador";
    }
    
    @GetMapping("/foro")
    public String foro(

            @RequestParam int combate,

            @RequestParam int evento,

            Model model
    ) {

        model.addAttribute(

                "comentarios",

                ComentarioDAO.obtenerComentarios(
                        combate
                )
        );

        model.addAttribute(
                "combate",
                combate
        );

        model.addAttribute(
                "evento",
                evento
        );

        return "foro";
    }
    
    
    @PostMapping("/comentar")
    public String comentar(

            @RequestParam int combate,

            @RequestParam int evento,

            @RequestParam String texto,

            HttpSession session
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        ComentarioDAO.insertarComentario(

                usuario.getId(),

                combate,

                texto
        );

        return "redirect:/foro?combate="
                + combate
                + "&evento="
                + evento;
    }
    
    @GetMapping("/puntuar")
    public String puntuar(

            @RequestParam int combate,

            @RequestParam String p1,

            @RequestParam String p2,

            @RequestParam int evento,

            Model model
    ) {

        Combate c =
                new Combate(

                        combate,

                        p1,

                        p2
                );

        model.addAttribute(
                "combate",
                c
        );

        model.addAttribute(
                "evento",
                evento
        );

        return "puntuar";
    }
    
    @PostMapping("/guardarPuntuacion")
    public String guardarPuntuacion(

            @RequestParam int combate,

            @RequestParam int evento,

            @RequestParam int r1p1,

            @RequestParam int r1p2,

            @RequestParam int r2p1,

            @RequestParam int r2p2,

            @RequestParam int r3p1,

            @RequestParam int r3p2,

            HttpSession session
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        Puntuacion p =
                new Puntuacion(

                        usuario.getId(),

                        combate,

                        r1p1,

                        r1p2,

                        r2p1,

                        r2p2,

                        r3p1,

                        r3p2
                );

        PuntuacionDAO.insertarPuntuacion(p);

        return "redirect:/evento?id=" + evento;
    }
    
    @GetMapping("/settings")
    public String settings(

            HttpSession session,

            Model model
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        model.addAttribute(
                "usuario",
                usuario
        );

        return "settings";
    }
    
    @GetMapping("/logout")
    public String logout(

            HttpSession session
    ) {

        session.invalidate();

        return "redirect:/";
    }
    @GetMapping("/editarPerfil")
    public String editarPerfil(

            HttpSession session,

            Model model
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        model.addAttribute(
                "usuario",
                usuario
        );

        return "editarPerfil";
    }
    
    @PostMapping("/guardarPerfil")
    public String guardarPerfil(

            @RequestParam String nombre,

            @RequestParam String bio,

            @RequestParam String bandera,

            HttpSession session
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        UsuarioDAO.actualizarPerfil(

                usuario.getId(),

                nombre,

                bio,

                bandera
        );

        usuario =
                new Usuario(

                        usuario.getId(),

                        nombre,

                        usuario.getEmail(),

                        usuario.getPassword(),

                        usuario.getXp(),

                        usuario.getNivel(),

                        bio,

                        bandera,

                        usuario.isPremium(),
                        
                        usuario.isAdmin(),
                        
                        usuario.isBaneado(),
                        
                        usuario.isNotificaciones()
                );

        session.setAttribute(
                "usuario",
                usuario
        );

        return "redirect:/perfil";
    }
    
    @GetMapping("/premium")
    public String premium(

            HttpSession session
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        UsuarioDAO.activarPremium(
                usuario.getId()
        );

        usuario =
                new Usuario(

                        usuario.getId(),

                        usuario.getNombre(),

                        usuario.getEmail(),

                        usuario.getPassword(),

                        usuario.getXp(),

                        usuario.getNivel(),

                        usuario.getBio(),

                        usuario.getBandera(),
                        
                        usuario.isAdmin(),
                        
                        usuario.isBaneado(),
                        
                        usuario.isNotificaciones(),

                        true
                );

        session.setAttribute(
                "usuario",
                usuario
        );

        return "redirect:/perfil";
    }
    
    @GetMapping("/admin")
    public String admin(

            HttpSession session
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        if (!usuario.isAdmin()) {

            return "redirect:/home";
        }

        return "admin";
    }
    
    @GetMapping("/crearEvento")
    public String crearEvento(

            HttpSession session
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        if (!usuario.isAdmin()) {

            return "redirect:/home";
        }

        return "crearEvento";
    }
    
    @PostMapping("/guardarEvento")
    public String guardarEvento(

            @RequestParam String nombre,

            @RequestParam String organizacion,

            @RequestParam String lugar,

            HttpSession session
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        if (!usuario.isAdmin()) {

            return "redirect:/home";
        }

        EventoDAO.crearEvento(

                nombre,

                organizacion,

                lugar
        );

        return "redirect:/home";
    }
    
    @GetMapping("/crearCombate")
    public String crearCombate(

            HttpSession session,

            Model model
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        if (!usuario.isAdmin()) {

            return "redirect:/home";
        }

        model.addAttribute(

                "eventos",

                EventoDAO.obtenerEventos()
        );

        model.addAttribute(

                "peleadores",

                PeleadorDAO.obtenerPeleadores()
        );

        return "crearCombate";
    }

    @PostMapping("/guardarCombate")
    public String guardarCombate(

            @RequestParam int evento,

            @RequestParam int peleador1,

            @RequestParam int peleador2,

            HttpSession session
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        if (!usuario.isAdmin()) {

            return "redirect:/home";
        }

        CombateDAO.crearCombate(

                evento,

                peleador1,

                peleador2
        );

        return "redirect:/evento?id=" + evento;
    }
    
    @GetMapping("/resultados")
    public String resultados(

            HttpSession session,

            Model model
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        if (!usuario.isAdmin()) {

            return "redirect:/home";
        }

        model.addAttribute(

                "combates",

                CombateDAO.obtenerTodos()
        );

        return "resultados";
    }
    
    @PostMapping("/guardarResultado")
    public String guardarResultado(

            @RequestParam int combate,

            @RequestParam int ganador,

            @RequestParam String metodo,

            @RequestParam int round,

            HttpSession session
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        if (!usuario.isAdmin()) {

            return "redirect:/home";
        }

        CombateDAO.guardarResultado(

                combate,

                ganador,

                metodo,

                round
        );

        PrediccionDAO.validarPredicciones(

                combate,

                ganador
        );
        
        return "redirect:/admin";
    }
    
    
    @GetMapping("/moderarComentarios")
    public String moderarComentarios(

            HttpSession session,

            Model model
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        if (!usuario.isAdmin()) {

            return "redirect:/home";
        }

        model.addAttribute(

                "comentarios",

                ComentarioDAO.obtenerTodos()
        );

        return "moderarComentarios";
    }
    
    
    @GetMapping("/borrarComentario")
    public String borrarComentario(

            @RequestParam int id,

            HttpSession session
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        if (!usuario.isAdmin()) {

            return "redirect:/home";
        }

        ComentarioDAO.borrarComentario(id);

        return "redirect:/moderarComentarios";
    }
    
    @GetMapping("/banearUsuarios")
    public String banearUsuarios(

            HttpSession session,

            Model model
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        if (!usuario.isAdmin()) {

            return "redirect:/home";
        }

        model.addAttribute(

                "usuarios",

                UsuarioDAO.obtenerTodos()
        );

        return "banearUsuarios";
    }
    
    @GetMapping("/banear")
    public String banear(

            @RequestParam int id,

            HttpSession session
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        if (!usuario.isAdmin()) {

            return "redirect:/home";
        }

        UsuarioDAO.banearUsuario(id);

        return "redirect:/banearUsuarios";
    }
    
    
    @GetMapping("/gestionarPremium")
    public String gestionarPremium(

            HttpSession session,

            Model model
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        if (!usuario.isAdmin()) {

            return "redirect:/home";
        }

        model.addAttribute(

                "usuarios",

                UsuarioDAO.obtenerTodos()
        );

        return "gestionarPremium";
    }
    
    
    @GetMapping("/darPremium")
    public String darPremium(

            @RequestParam int id,

            HttpSession session
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        if (!usuario.isAdmin()) {

            return "redirect:/home";
        }

        UsuarioDAO.darPremium(id);

        return "redirect:/gestionarPremium";
    }
    
    
    @GetMapping("/quitarPremium")
    public String quitarPremium(

            @RequestParam int id,

            HttpSession session
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        if (!usuario.isAdmin()) {

            return "redirect:/home";
        }

        UsuarioDAO.quitarPremium(id);

        return "redirect:/gestionarPremium";
    }
    
    @GetMapping("/notificaciones")
    public String notificaciones(

            HttpSession session
    ) {

        Usuario usuario =
                (Usuario) session.getAttribute(
                        "usuario"
                );

        boolean nuevoValor =
                !usuario.isNotificaciones();

        UsuarioDAO.cambiarNotificaciones(

                usuario.getId(),

                nuevoValor
        );

        usuario =
                new Usuario(

                        usuario.getId(),

                        usuario.getNombre(),

                        usuario.getEmail(),

                        usuario.getPassword(),

                        usuario.getXp(),

                        usuario.getNivel(),

                        usuario.getBio(),

                        usuario.getBandera(),

                        usuario.isPremium(),

                        usuario.isAdmin(),

                        usuario.isBaneado(),

                        nuevoValor
                );

        session.setAttribute(

                "usuario",

                usuario
        );

        return "redirect:/settings";
    }
    
    @GetMapping("/help")
    public String help() {

        return "help";
    }
    
    
    @GetMapping("/privacy")
    public String privacy() {

        return "privacy";
    }
    
    @GetMapping("/premiumPage")
    public String premiumPage() {

        return "premium";
    }
    
    @GetMapping("/checkout")
    public String checkout() {

        return "checkout";
    }
    
    
}