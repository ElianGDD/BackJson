package com.risosu.EDesalesProgramacionNCapasJunio3.Controller;

import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.ColoniaDAOImplementation;
import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.DireccionDAOImplementation;
import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.EstadoDAO;
import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.EstadoDAOImplementation;
import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.IColoniaJPADAOImplementation;
import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.IEstadoJPADAOImplementation;
import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.IMunicipioJPADAOImplementation;
import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.IPaisJPADAOImplementation;
import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.IRollJPADAOImplementation;
import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.IUsuarioJPADAOImplementation;
import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.MunicipioDAOImplementation;
import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.PaisDAOImplementation;
import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.RollDAOImplementation;
import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.UsuarioDAOImplementation;
import com.risosu.EDesalesProgramacionNCapasJunio3.ML.Usuario;
import com.risosu.EDesalesProgramacionNCapasJunio3.ML.UsuarioDireccion;
import com.risosu.EDesalesProgramacionNCapasJunio3.ML.Colonia;
import com.risosu.EDesalesProgramacionNCapasJunio3.ML.Direccion;
import com.risosu.EDesalesProgramacionNCapasJunio3.ML.Pais;
import com.risosu.EDesalesProgramacionNCapasJunio3.ML.Result;
import com.risosu.EDesalesProgramacionNCapasJunio3.ML.ResultValidarDatos;
import com.risosu.EDesalesProgramacionNCapasJunio3.ML.Roll;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Base64;
import java.security.AlgorithmConstraints;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.analysis.function.Add;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //controlador
@RequestMapping("/Presentacion")
public class PresentacionController {

    @Autowired
    private UsuarioDAOImplementation usuarioDAOImplementation;
    @Autowired
    private PaisDAOImplementation paisDAOImplementation;
    @Autowired
    private EstadoDAOImplementation estadoDAOImplementation;
    @Autowired
    private MunicipioDAOImplementation municipioDAOImplementation;
    @Autowired
    private ColoniaDAOImplementation coloniaDAOImplementation;
    @Autowired
    private DireccionDAOImplementation direccionDAOImplementation;
    @Autowired
    private IUsuarioJPADAOImplementation iUsuarioJPADAOImplementation;
    @Autowired
    private RollDAOImplementation rollDAOImplementation;
    @Autowired
    private IRollJPADAOImplementation iRollJPADAOImplementation;
    @Autowired
    private IPaisJPADAOImplementation iPaisJPADAOImplementation;
    @Autowired
    private IEstadoJPADAOImplementation iEstadoJPADAOImplementation;
    @Autowired
    private IMunicipioJPADAOImplementation iMunicipioJPADAOImplementation;
    @Autowired
    private IColoniaJPADAOImplementation iColoniaJPADAOImplementation;

    @GetMapping
    public String Index(Model model) {
        Result result = iUsuarioJPADAOImplementation.GetAll();
        //Result resultRol = usuarioDAOImplementation.GetAllRol();
        model.addAttribute("usuarioDireccion", result.objects);
        //model.addAttribute("roles", resultRol.objects);
        model.addAttribute("busquedaAbierta", new Usuario());
//        if (result.correct) {
//
//        }
        return "Presentacion";

    }

    @GetMapping("UsuarioForm/{idAlumno}") // este prepara la vista de formualrio
    public String Accion(Model model, @PathVariable int idAlumno) {

        if (idAlumno < 1) {
            model.addAttribute("usuarioDireccion", new UsuarioDireccion());
            model.addAttribute("pais", iPaisJPADAOImplementation.GetAllPaisJPA().objects);

            UsuarioDireccion alumnoDireccion = new UsuarioDireccion();
            alumnoDireccion.Usuario = new Usuario();
            alumnoDireccion.Usuario.Roll = new Roll();
            alumnoDireccion.Direccion = new Direccion();

            model.addAttribute("usuarioDireccion", alumnoDireccion);

            model.addAttribute("Roll", iRollJPADAOImplementation.GetAll().objects);

            return "UsuarioForm";
        } else {

            model.addAttribute("usuarioDireccion", iUsuarioJPADAOImplementation.GetDatosAlumnoPDByIdAlumnoJPA(idAlumno).object);
            return "UsuarioEditable";
        }

    }

    @GetMapping("/formeditable")
    public String accionEditable(
            @RequestParam("idUsuario") int idUsuario,
            @RequestParam(required = false) Integer idDireccion,
            Model model) {
        //Editar usuario n y -1
        if (idDireccion == null) {

            Result result = iUsuarioJPADAOImplementation.GetDatosBasicosUsuarioByIdUsuarioJPA(idUsuario);

            UsuarioDireccion usuarioDireccion = (UsuarioDireccion) result.object;

//            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
//            usuarioDireccion.Usuario = usuario;
//            usuarioDireccion.Usuario.setIdUsuario(idUsuario);
            usuarioDireccion.Direccion = new Direccion();
            usuarioDireccion.Direccion.setIdDireccion(-1);

            model.addAttribute("usuarioDireccion", usuarioDireccion);

        } else if (idDireccion == 0) {

            Direccion direccion = (Direccion) direccionDAOImplementation.GetDireccionCMEPByIdUsuario(idUsuario).object;
            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
            usuarioDireccion.Usuario = new Usuario();
            usuarioDireccion.Usuario.setIdUsuario(idUsuario);
            usuarioDireccion.Direccion = new Direccion();

            model.addAttribute("usuarioDireccion", usuarioDireccion);
            model.addAttribute("pais", iPaisJPADAOImplementation.GetAllPaisJPA().objects);

        } else {
            // model.addAttribute("usuarioDireccion", new UsuarioDireccion());
            model.addAttribute("pais", iPaisJPADAOImplementation.GetAllPaisJPA().objects);

            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
//            alumnoDireccion.Usuario = new Usuario();
//            alumnoDireccion.Direccion = new Direccion();

            Result result = direccionDAOImplementation.GetDireccionCMEPByIdUsuario(idDireccion);

            usuarioDireccion = (UsuarioDireccion) result.object;
            usuarioDireccion.Usuario = new Usuario();
            usuarioDireccion.Usuario.setIdUsuario(idUsuario);

            model.addAttribute("usuarioDireccion", usuarioDireccion);

        }

        return "UsuarioForm";
    }

//    @GetMapping("UsuarioEditable")//Este prepara la vista para editar usuario
//    public String 
    @GetMapping("/GetEstadosBYIdPais/{IdPais}")
    @ResponseBody
    public Result GetEstadoBYIdPais(@PathVariable("IdPais") int IdPais) {

        return iEstadoJPADAOImplementation.GetEstadoByIdPais(IdPais);
    }

    @GetMapping("/GetMunicipiosByIdEstado/{IdEstado}")
    @ResponseBody
    public Result GetMunicipioByIdEstados(@PathVariable("IdEstado") int IdEstado) {

        return iMunicipioJPADAOImplementation.GetMunicipioByIdEstado(IdEstado);

    }

    @GetMapping("/GetColoniaByIdMunicipio/{IdMunicipio}")
    @ResponseBody
    public Result GetColoniaByIdMunicipio(@PathVariable("IdMunicipio") int IdMunicipio) {

        return iColoniaJPADAOImplementation.GetColoniaByMunicipio(IdMunicipio);

    }

    @GetMapping("/GetEstadosByPais/{idPais}")
    @ResponseBody // retorno de dato estructurado (objeto en JSON/XML)
    public Result GetEstadosByPais(@PathVariable("idPais") int IdPais) {
        return iEstadoJPADAOImplementation.GetEstadoByIdPais(IdPais);
    }

    @PostMapping("form") // este recupera los datos del formulario
    public String Accion(@Valid @ModelAttribute UsuarioDireccion alumnoDireccion,
            BindingResult bindingResult,
            @RequestParam(required = false) MultipartFile imagenFile,
            Model model) {

        if (alumnoDireccion.Usuario.getIdUsuario() > 0) {
            //Insertar Nueva dirección
            if (alumnoDireccion.Usuario.getIdUsuario() > 0 && alumnoDireccion.Direccion.getIdDireccion() == 0) {
                Result result = iUsuarioJPADAOImplementation.AddNuevaDireccionByIdUsuarioJPA(alumnoDireccion);

                //Actualizar Datos Direccion Usuario
            } else if (alumnoDireccion.Usuario.getIdUsuario() > 0 && alumnoDireccion.Direccion.getIdDireccion() == -1) {

                try {

                    if (!imagenFile.isEmpty()) {

                        byte[] bytes = imagenFile.getBytes();
                        String imgBase64 = Base64.getEncoder().encodeToString(bytes);
                        alumnoDireccion.Usuario.setImagenPerfil(imgBase64);

                    }
                } catch (Exception ex) {
                    System.out.println(ex.getLocalizedMessage());
                }
                Result result = iUsuarioJPADAOImplementation.UpdateDatosUsuario(alumnoDireccion);

                //Actualizar Datos Basicos Usuario
            } else {

                Result result = iUsuarioJPADAOImplementation.UpdateAlumnoDatosDireccionJPA(alumnoDireccion);

            }

        } else {
//            if (bindingResult.hasErrors()) {
//                model.addAttribute("usuarioDireccion", alumnoDireccion);
//                return "UsuarioForm";
//            }
            try {

                if (!imagenFile.isEmpty()) {

                    byte[] bytes = imagenFile.getBytes();
                    String imgBase64 = Base64.getEncoder().encodeToString(bytes);
                    alumnoDireccion.Usuario.setImagenPerfil(imgBase64);

                }
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());

            }

//            Result result = usuarioDAOImplementation.Add(alumnoDireccion);
            Result result = iUsuarioJPADAOImplementation.Add(alumnoDireccion);
        }

        return "Presentacion"; // redireccionen a la vista de GetAll

    }

    @PostMapping("BusquedaAbierta")
    public String BuscarPorNombre(
            @Valid @ModelAttribute Usuario usuario, Model model
    ) {
//        Result result = ;
//        
//        List<UsuarioDireccion> usuarioDireccion = ( List<UsuarioDireccion>)usuarioDAOImplementation.GetDatosPorNombre(usuario).objects;

        model.addAttribute("usuarioDireccion", iUsuarioJPADAOImplementation.GetBusquedaDinamica(usuario).objects);
        model.addAttribute("busquedaAbierta", new Usuario());
        return "Presentacion";
    }

    @GetMapping("CargaMasiva")
    public String CargaMasiva() {

        return "CargaMasiva";
    }

    @PostMapping("CargaMasiva")
    public String CargaMasiva(@RequestParam MultipartFile archivo, Model model, HttpSession session) throws IOException {
        // archivodato.txt
        // si aplico split ["archivosato","txt"]
        if (archivo != null && !archivo.isEmpty()) {

            String fileExtention = archivo.getOriginalFilename().split("\\.")[1];
            List<UsuarioDireccion> usuarioDireccion = new ArrayList<>();

            String root = System.getProperty("user.dir");
            String path = "src/main/resources/documents";
            String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String absolutePath = root + "/" + path + "/" + fecha + archivo.getOriginalFilename();
            archivo.transferTo(new File(absolutePath));

            if (fileExtention.equals("txt")) {
                usuarioDireccion = LecturaArchivoTXT(new File(absolutePath));
            } else { //"xlsx"
                usuarioDireccion = LecturaArchivoExcel(new File(absolutePath));
            }
            //metodo para validar datos
            List<ResultValidarDatos> listaErrores = ValidarDatos(usuarioDireccion);
            if (listaErrores.isEmpty()) {
                session.setAttribute("path", absolutePath);
                model.addAttribute("listaErrores", listaErrores);
                model.addAttribute("archivoCorrecto", true);

            } else {
                model.addAttribute("listaErrores", listaErrores);
                model.addAttribute("archivoCorrecto", false);

            }

            //si lista de errores es vacia
            // si hay errores, mostrar en la vista los errores
        }
        return "CargaMasiva";
    }

    @PostMapping("Lectura")
    public List<UsuarioDireccion> LecturaArchivoTXT(File archivo) {

        List<UsuarioDireccion> usuarioDireccion = new ArrayList<>();
        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(archivo));) {
            bufferedReader.readLine();
            String linea = "";
            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split("\\|");
                UsuarioDireccion usuarioDireccion1 = new UsuarioDireccion();
                usuarioDireccion1.Usuario = new Usuario();
                usuarioDireccion1.Usuario.setNombre(datos[0]);
                usuarioDireccion1.Usuario.setApellidoPaterno(datos[1]);
                usuarioDireccion.add(usuarioDireccion1);

            }
        } catch (Exception ex) {
            usuarioDireccion = null;

        }

        return usuarioDireccion;
    }

    private List<ResultValidarDatos> ValidarDatos(List<UsuarioDireccion> usuarios) {
        List<ResultValidarDatos> listaErrores = new ArrayList<>();
        int fila = 1;
        if (usuarios == null) {
            listaErrores.add(new ResultValidarDatos(0, "Lista inexistente", "Lista inexistente"));

        } else if (usuarios.isEmpty()) {
            listaErrores.add(new ResultValidarDatos(0, "Lista vacia", "Lista vacia"));

        } else {
            for (UsuarioDireccion usuarioDireccion : usuarios) {
//                if (usuarioDireccion.Usuario.getNombre() == null || usuarioDireccion.Usuario.getNombre().equals("")) {
//                    listaErrores.add(new ResultValidarDatos(fila, usuarioDireccion.Usuario.getNombre(), "Campo obligatorio"));
//
//                }
                fila++;
            }
        }
        return listaErrores;
    }

    public String ProcesarCargaMasiva(HttpSession session) {
        String ruta = session.getAttribute("path").toString();
        session.removeAttribute("path");
        return "";
    }

    public List<UsuarioDireccion> LecturaArchivoExcel(File archivo) {
        List<UsuarioDireccion> usuariosDireccion = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(archivo);) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                usuarioDireccion.Usuario = new Usuario();
                usuarioDireccion.Usuario.setNombre(row.getCell(0) != null ? row.getCell(0).toString() : "");
                usuarioDireccion.Usuario.setApellidoPaterno(row.getCell(1) != null ? row.getCell(1).toString() : "");
                usuariosDireccion.add(usuarioDireccion);
            }
        } catch (Exception ex) {
            System.out.println("Errore en apartura de archivo");
        }

        return usuariosDireccion;
    }

    @GetMapping("DeleteUsuario/{idAlumno}")
    public String DeleteUsuario(Model model, @PathVariable int idAlumno) {

        UsuarioDireccion usuarioDireccion = new UsuarioDireccion();

        Result resultDelete = iUsuarioJPADAOImplementation.DeleteAlumno(idAlumno);
        Result resultGet = iUsuarioJPADAOImplementation.GetAll();
        //Result resultRol = usuarioDAOImplementation.GetAllRol();
        model.addAttribute("usuarioDireccion", resultGet.objects);
        //model.addAttribute("roles", resultRol.objects);
        model.addAttribute("busquedaAbierta", new Usuario());

        return "Presentacion";
    }

    @GetMapping("delete/direccion")
    public String DeleteDireccionByIdDireccion(@RequestParam int idDireccion) {
        Result result = iUsuarioJPADAOImplementation.DeleteDireccionByIdDireccion(idDireccion);
        return "";
    }
}
