package com.risosu.EDesalesProgramacionNCapasJunio3.Controller;

import com.risosu.EDesalesProgramacionNCapasJunio3.ML.Usuario;
import com.risosu.EDesalesProgramacionNCapasJunio3.ML.UsuarioDireccion;
import com.risosu.EDesalesProgramacionNCapasJunio3.ML.Colonia;
import com.risosu.EDesalesProgramacionNCapasJunio3.ML.Direccion;
import com.risosu.EDesalesProgramacionNCapasJunio3.ML.Estado;
import com.risosu.EDesalesProgramacionNCapasJunio3.ML.Municipio;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.client.RestTemplate;

@Controller //controlador
@RequestMapping("/Presentacion")
public class PresentacionController {

    @GetMapping
    public String Index(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Result<UsuarioDireccion>> response = restTemplate.exchange("http://localhost:8080/demoapi",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<Result<UsuarioDireccion>>() {
        });

        List<UsuarioDireccion> usuariosDireccion = response.getBody().objects;
        model.addAttribute("usuarioDireccion", usuariosDireccion);

        return "Presentacion";
    }

    @GetMapping("/UsuarioForm/{IdUsuario}")
    public String UsuarioDirecciones(Model model, @PathVariable int IdUsuario) {

        if (IdUsuario < 1) {
            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
            usuarioDireccion.Usuario = new Usuario();
            usuarioDireccion.Usuario.Roll = new Roll();
            usuarioDireccion.Direccion = new Direccion();

            model.addAttribute("usuarioDireccion", usuarioDireccion);

            RestTemplate restTemplateRoll = new RestTemplate();
            ResponseEntity<Result<Roll>> responseRoll = restTemplateRoll.exchange("http://localhost:8080/demoapi/Roll",
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<Result<Roll>>() {
            });

            List<Roll> roles = responseRoll.getBody().objects;
            model.addAttribute("Roll", roles);

            RestTemplate restTemplatePais = new RestTemplate();
            ResponseEntity<Result<Pais>> responsePais = restTemplatePais.exchange("http://localhost:8080/demoapi/Pais",
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<Result<Pais>>() {
            });

            List<Pais> paises = responsePais.getBody().objects;
            model.addAttribute("pais", paises);

            return "UsuarioForm";

        } else {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Result<UsuarioDireccion>> response = restTemplate.exchange("http://localhost:8080/demoapi/UsuarioDireccion/" + IdUsuario,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<Result<UsuarioDireccion>>() {
            });

            UsuarioDireccion usuariosDireccion = response.getBody().object; //pedias el objects pero el registro lo guardas en object
            model.addAttribute("usuarioDireccion", usuariosDireccion);

            return "UsuarioEditable";
        }
    }

    @GetMapping("/GetEstadosBYIdPais/{IdPais}")
    @ResponseBody
    public Result GetEstadoBYIdPais(@PathVariable("IdPais") int IdPais) {
        Result result = new Result();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Result<Estado>> response = restTemplate.exchange("http://localhost:8080/demoapi/Estado/" + IdPais,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<Result<Estado>>() {
        });

        result.correct = true;

        List<Estado> estados = response.getBody().objects;

        if (estados.size() != 0) {
            result.objects = new ArrayList<>();

            for (Estado estadoIteracion : estados) {
                Estado estado = new Estado();
                estado.setIdEstado(estadoIteracion.getIdEstado());
                estado.setNombre(estadoIteracion.getNombre());
                result.objects.add(estado);
            }

        }
        return result;
    }

    @GetMapping("/GetMunicipiosByIdEstado/{IdEstado}")
    @ResponseBody
    public Result GetMunicipioByIdEstados(@PathVariable("IdEstado") int IdEstado) {

        Result result = new Result();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Result<Municipio>> response = restTemplate.exchange("http://localhost:8080/demoapi/Municipio/" + IdEstado,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<Result<Municipio>>() {
        });

        result.correct = true;

        List<Municipio> municipios = response.getBody().objects;

        if (municipios.size() != 0) {
            result.objects = new ArrayList();
            for (Municipio municipioIteracion : municipios) {
                Municipio municipio = new Municipio();
                municipio.setIdMunicipio(municipioIteracion.getIdMunicipio());
                municipio.setNombre(municipioIteracion.getNombre());
                result.objects.add(municipio);

            }

        }

        return result;
    }

    @GetMapping("/GetColoniaByIdMunicipio/{IdMunicipio}")
    @ResponseBody
    public Result GetColoniaByIdMunicipio(@PathVariable("IdMunicipio") int IdMunicipio) {

        Result result = new Result();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Result<Colonia>> response = restTemplate.exchange("http://localhost:8080/demoapi/Colonia/" + IdMunicipio,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<Result<Colonia>>() {
        });

        result.correct = true;

        List<Colonia> colonias = response.getBody().objects;

        if (colonias.size() != 0) {
            result.objects = new ArrayList();
            for (Colonia coloniaInteracion : colonias) {
                Colonia colonia = new Colonia();
                colonia.setIdColonia(coloniaInteracion.getIdColonia());
                colonia.setNombre(coloniaInteracion.getNombre());
                colonia.setCodigoPostal(coloniaInteracion.getCodigoPostal());
                result.objects.add(colonia);

            }
        }

        return result;
    }

    @GetMapping("/formeditable")
    public String accionEditable(
            @RequestParam("idUsuario") int idUsuario,
            @RequestParam(required = false) Integer idDireccion,
            Model model) {

        if (idDireccion == null) {

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Result<UsuarioDireccion>> response = restTemplate.exchange(
                    "http://localhost:8080/demoapi/Usuario?IdUsuario=" + idUsuario,
                    HttpMethod.GET,
                    HttpEntity.EMPTY,
                    new ParameterizedTypeReference<Result<UsuarioDireccion>>() {
            });

            UsuarioDireccion usuarioDireccion = response.getBody().object;

            // Asegurarse de que la dirección no sea null
            if (usuarioDireccion.getDireccion() == null) {
                usuarioDireccion.setDireccion(new Direccion());
                usuarioDireccion.getDireccion().setIdDireccion(-1);
            }

            model.addAttribute("usuarioDireccion", usuarioDireccion);

        } else if (idDireccion == 0) {

        } else {

        }
        return "UsuarioForm";
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



    @PostMapping("/ProcesarCargaMasiva")
    public String ProcesarCargaMasiva(HttpSession session) {
        Result result = new Result();
        String ruta = session.getAttribute("path").toString();
        session.removeAttribute("path");

        File archivo = new File(ruta);
        List<UsuarioDireccion> usuarios = LecturaArchivoTXT(archivo);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<UsuarioDireccion>> requestEntity = new HttpEntity<>(usuarios,headers);
        
        
          RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Result> response = restTemplate.exchange("http://localhost:8080/demoapi/CargaMasiva",
                HttpMethod.POST,
                requestEntity,
                Result.class
        );

        Result resultado = response.getBody();

        if (result.correct) {
            return "redirect:/Presentacion/CargaMasiva?success=true";
        } else {
            return "redirect:/Presentacion/CargaMasiva?error=true";
        }
    }

    @PostMapping("Lectura")
    public List<UsuarioDireccion> LecturaArchivoTXT(File archivo) {

        List<UsuarioDireccion> usuarioDireccionList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(archivo))) {
            bufferedReader.readLine(); // Saltar encabezado
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String[] datos = linea.split("\\|");

                // Crear y poblar Usuario
                Usuario usuario = new Usuario();
                usuario.setNombre(datos[0]);

                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    String fechaTexto = datos[1].trim();
                    usuario.setFechaNacimiento(fechaTexto.isEmpty() ? null : formatoFecha.parse(fechaTexto));
                } catch (ParseException e) {
                    usuario.setFechaNacimiento(null);
                }

                usuario.setUserName(datos[2]);
                usuario.setApellidoPaterno(datos[3]);
                usuario.setApellidoMaterno(datos[4]);
                usuario.setEmail(datos[5]);
                usuario.setPassword(datos[6]);
                usuario.setSexo(datos[7].isEmpty() ? '\0' : datos[7].charAt(0));
                usuario.setTelefono(datos[8]);
                usuario.setCelular(datos[9]);
                usuario.setCurp(datos[10]);

                Roll roll = new Roll();
                roll.setIdRoll(Integer.parseInt(datos[11]));
                usuario.setRoll(roll);

                usuario.setImagenPerfil(datos[12]);
                usuario.setStatus(Integer.parseInt(datos[13]));

                // Crear y poblar Direccion
                Direccion direccion = new Direccion();
                direccion.setCalle(datos[14]);
                direccion.setNumeroInterior(datos[15]);
                direccion.setNumeroExterior(datos[16]);
                direccion.Colonia = new Colonia();
                direccion.Colonia.setIdColonia(Integer.parseInt(datos[17]));

                // Asociar Usuario y Direccion al contenedor UsuarioDireccion
                UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                usuarioDireccion.setUsuario(usuario);
                usuarioDireccion.setDireccion(direccion);

                usuarioDireccionList.add(usuarioDireccion);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            usuarioDireccionList = null;
        }

        return usuarioDireccionList;
    }

    public List<UsuarioDireccion> LecturaArchivoExcel(File archivo) {
        List<UsuarioDireccion> usuariosDireccion = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(archivo);) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                usuarioDireccion.Usuario = new Usuario();

                usuarioDireccion.Usuario.setNombre(row.getCell(0) != null ? row.getCell(0).toString() : "");
                usuarioDireccion.Usuario.setFechaNacimiento(row.getCell(1) != null ? row.getCell(1).getDateCellValue() : null);
                usuarioDireccion.Usuario.setUserName(row.getCell(2) != null ? row.getCell(2).toString() : "");
                usuarioDireccion.Usuario.setApellidoPaterno(row.getCell(3) != null ? row.getCell(3).toString() : "");
                usuarioDireccion.Usuario.setApellidoMaterno(row.getCell(4) != null ? row.getCell(4).toString() : "");
                usuarioDireccion.Usuario.setEmail(row.getCell(5) != null ? row.getCell(5).toString() : "");
                usuarioDireccion.Usuario.setPassword(row.getCell(6) != null ? row.getCell(6).toString() : "");
                usuarioDireccion.Usuario.setSexo(row.getCell(7) != null ? row.getCell(7).toString().charAt(0) : ' ');
                usuarioDireccion.Usuario.setTelefono(row.getCell(8) != null ? row.getCell(8).toString() : "");
                usuarioDireccion.Usuario.setCelular(row.getCell(9) != null ? row.getCell(9).toString() : "");
                usuarioDireccion.Usuario.setCurp(row.getCell(10) != null ? row.getCell(10).toString() : "");

                usuarioDireccion.Usuario.Roll = new Roll();
                usuarioDireccion.Usuario.Roll.setIdRoll((int) row.getCell(11).getNumericCellValue());

                usuarioDireccion.Usuario.setImagenPerfil(row.getCell(12) != null ? row.getCell(12).toString() : "");
                usuarioDireccion.Usuario.setStatus((int) row.getCell(13).getNumericCellValue());

                usuariosDireccion.add(usuarioDireccion);

            }
        } catch (Exception ex) {
            System.out.println("Errores en apartura de archivo");
        }

        return usuariosDireccion;
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

}
