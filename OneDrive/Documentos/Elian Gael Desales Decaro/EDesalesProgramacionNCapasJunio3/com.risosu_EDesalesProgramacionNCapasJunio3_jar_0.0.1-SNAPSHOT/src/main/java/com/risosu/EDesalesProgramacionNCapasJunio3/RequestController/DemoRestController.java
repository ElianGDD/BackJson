package com.risosu.EDesalesProgramacionNCapasJunio3.RequestController;

import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.ColoniaJPADAOImplementation;
import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.DireccionJPADAOImplementation;
import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.EstadoJPADAOImplementation;
import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.IUsuarioJPADAOImplementation;
import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.MunicipioJPADAOImplementation;
import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.PaisJPADAOImplementatio;
import com.risosu.EDesalesProgramacionNCapasJunio3.DAO.RolJPADAOImplementation;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Colonia;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Direccion;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Municipio;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Result;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.ResultValidarDatos;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Roll;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.Usuario;
import com.risosu.EDesalesProgramacionNCapasJunio3.JPA.UsuarioDireccion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/demoapi")
public class DemoRestController {

    @Autowired
    IUsuarioJPADAOImplementation iUsuarioJPADAOImplementation;
    @Autowired
    DireccionJPADAOImplementation direccionJPADAOImplementation;
    @Autowired
    RolJPADAOImplementation rolJPADAOImplementation;
    @Autowired
    PaisJPADAOImplementatio paisJPADAOImplementatio;
    @Autowired
    EstadoJPADAOImplementation estadoJPADAOImplementation;
    @Autowired
    MunicipioJPADAOImplementation municipioJPADAOImplementation;
    @Autowired
    ColoniaJPADAOImplementation coloniaJPADAOImplementation;

    @GetMapping
    public ResponseEntity GetAll() {
        try {
            Result result = iUsuarioJPADAOImplementation.GetAll();

            if (result.correct) {
                if (result.objects.size() == 0) {
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Sin información");
                } else {
                    return ResponseEntity.ok().body(result);
                }

            } else {
                return ResponseEntity.status(HttpStatus.valueOf(400)).body(result);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.valueOf(500)).body(ex.getLocalizedMessage());
        }
    }

    @GetMapping("/UsuarioDireccion/{IdUsuario}")
    public ResponseEntity GetUsuarioDireccion(@PathVariable int IdUsuario) {
        try {
            Result result = iUsuarioJPADAOImplementation.GetUsuarioDirecciones(IdUsuario);
            if (result.correct) {
                if (result.object == null) {
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Sin información");

                } else {
                    return ResponseEntity.ok().body(result);
                }

            } else {
                return ResponseEntity.status(HttpStatus.valueOf(400)).body(result);
            }

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.valueOf(500)).body(ex.getLocalizedMessage());

        }

    }

    @GetMapping("/Usuario")
    public ResponseEntity GetUsuario(@RequestParam int IdUsuario) {
        try {
            Result result = iUsuarioJPADAOImplementation.GetUsuario(IdUsuario);
            if (result.correct) {
                if (result.object == null) {
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Sin información");
                } else {
                    return ResponseEntity.ok().body(result);
                }

            } else {
                return ResponseEntity.status(HttpStatus.valueOf(400)).body(result);
            }

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.valueOf(500)).body(ex.getLocalizedMessage());
        }
    }

    @GetMapping("/Direccion/{IdUsuario}")
    public ResponseEntity GetDireccionByUsuario(@PathVariable int IdUsuario) {
        try {
            Result result = direccionJPADAOImplementation.GetDireccionesByIdUsuario(IdUsuario);
            if (result.correct) {
                if (result.objects.size() == 0) {
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Sin información");
                } else {
                    return ResponseEntity.ok().body(result);
                }

            } else {
                return ResponseEntity.status(HttpStatus.valueOf(400)).body(result);
            }

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.valueOf(500)).body(ex.getLocalizedMessage());
        }

    }

    @GetMapping("/Roll")
    public ResponseEntity GetAllRoll() {
        try {
            Result result = rolJPADAOImplementation.GetAllRoll();

            if (result.correct) {
                if (result.objects.size() == 0) {
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Sin información");
                } else {
                    return ResponseEntity.ok().body(result);
                }

            } else {
                return ResponseEntity.status(HttpStatus.valueOf(400)).body(result);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.valueOf(500)).body(ex.getLocalizedMessage());
        }

    }

    @GetMapping("/Pais")
    public ResponseEntity GetAllPais() {
        try {
            Result result = paisJPADAOImplementatio.GetAllPais();

            if (result.correct) {
                if (result.objects.size() == 0) {
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Sin información");
                } else {
                    return ResponseEntity.ok().body(result);
                }

            } else {
                return ResponseEntity.status(HttpStatus.valueOf(400)).body(result);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.valueOf(500)).body(ex.getLocalizedMessage());
        }
    }

    @GetMapping("/Estado/{IdPais}")
    public ResponseEntity GetEstadoByPais(@PathVariable int IdPais) {
        try {
            Result result = estadoJPADAOImplementation.GetEstadoByIdPais(IdPais);

            if (result.correct) {
                if (result.objects.size() == 0) {
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Sin información");
                } else {
                    return ResponseEntity.ok().body(result);
                }

            } else {
                return ResponseEntity.status(HttpStatus.valueOf(400)).body(result);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.valueOf(500)).body(ex.getLocalizedMessage());
        }
    }

    @GetMapping("/Municipio/{IdEstado}")
    public ResponseEntity GetMunicipioByEstado(@PathVariable int IdEstado) {
        try {
            Result result = municipioJPADAOImplementation.GetMunicipioByEstado(IdEstado);

            if (result.correct) {
                if (result.objects.size() == 0) {
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Sin información");
                } else {
                    return ResponseEntity.ok().body(result);
                }

            } else {
                return ResponseEntity.status(HttpStatus.valueOf(400)).body(result);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.valueOf(500)).body(ex.getLocalizedMessage());
        }
    }

    @GetMapping("/Colonia/{IdMunicipio}")
    public ResponseEntity GetColoniaByMunicipio(@PathVariable int IdMunicipio) {
        try {
            Result result = coloniaJPADAOImplementation.GetColoniaByIdMunicipio(IdMunicipio);

            if (result.correct) {
                if (result.objects.size() == 0) {
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Sin información");
                } else {
                    return ResponseEntity.ok().body(result);
                }

            } else {
                return ResponseEntity.status(HttpStatus.valueOf(400)).body(result);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.valueOf(500)).body(ex.getLocalizedMessage());
        }
    }

    @GetMapping("/ColoniaCP/{CP}")
    public ResponseEntity GetColoniaMEPByCP(@PathVariable String CP) {
        try {
            Result result = coloniaJPADAOImplementation.GetColoniaByCP(CP);

            if (result.correct) {
                if (result.objects.size() == 0) {
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Sin información");
                } else {
                    return ResponseEntity.ok().body(result);
                }

            } else {
                return ResponseEntity.status(HttpStatus.valueOf(400)).body(result);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.valueOf(500)).body(ex.getLocalizedMessage());
        }
    }

    @PostMapping("/Usuario")
    public ResponseEntity PostUsuarioAll(@RequestBody UsuarioDireccion usuarioDireccion) {
        try {
            Result result = iUsuarioJPADAOImplementation.PostAll(usuarioDireccion);

            if (result.correct) {
                if (result.object != null) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(result.object);
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Usuario creado pero no retornado.");
                }

            } else {
                return ResponseEntity.status(HttpStatus.valueOf(400)).body(result);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.valueOf(500)).body(ex.getLocalizedMessage());
        }

    }

    @PostMapping("/Direccion")
    public ResponseEntity PostDireccionByUsuario(@RequestBody UsuarioDireccion usuarioDireccion) {
        try {
            Result result = direccionJPADAOImplementation.POSTDireccionByIdUsuario(usuarioDireccion);

            if (result.correct) {
                if (result.object != null) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(result.object);
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Usuario creado pero no retornado.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.valueOf(400)).body(result);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.valueOf(500)).body(ex.getLocalizedMessage());
        }

    }
    
     @PostMapping("/CargaMasiva")
    public ResponseEntity PostCargaMasiva(@RequestBody List<UsuarioDireccion> usuarioDireccion){
        Result result = new Result();
        try{
             // Insertar en BD
            result = iUsuarioJPADAOImplementation.PostCargaMasiva(usuarioDireccion);
            return ResponseEntity.ok(result);

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = "Error interno al procesar el archivo";
            result.ex = ex;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

  

    


//    @PostMapping("Lectura")
//    public List<UsuarioDireccion> LecturaArchivoTXT(File archivo) {
//
//        List<UsuarioDireccion> usuarioDireccionList = new ArrayList<>();
//
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(archivo))) {
//            bufferedReader.readLine(); // Saltar encabezado
//            String linea;
//
//            while ((linea = bufferedReader.readLine()) != null) {
//                String[] datos = linea.split("\\|");
//
//                // Crear y poblar Usuario
//                Usuario usuario = new Usuario();
//                usuario.setNombre(datos[0]);
//
//                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
//                try {
//                    String fechaTexto = datos[1].trim();
//                    usuario.setFechaNacimiento(fechaTexto.isEmpty() ? null : formatoFecha.parse(fechaTexto));
//                } catch (ParseException e) {
//                    usuario.setFechaNacimiento(null);
//                }
//
//                usuario.setUserName(datos[2]);
//                usuario.setApellidoPaterno(datos[3]);
//                usuario.setApellidoMaterno(datos[4]);
//                usuario.setEmail(datos[5]);
//                usuario.setPassword(datos[6]);
//                usuario.setSexo(datos[7].isEmpty() ? '\0' : datos[7].charAt(0));
//                usuario.setTelefono(datos[8]);
//                usuario.setCelular(datos[9]);
//                usuario.setCurp(datos[10]);
//
//                Roll roll = new Roll();
//                roll.setIdRoll(Integer.parseInt(datos[11]));
//                usuario.setRoll(roll);
//
//                usuario.setImagenPerfil(datos[12]);
//                usuario.setStatus(Integer.parseInt(datos[13]));
//
//                // Crear y poblar Direccion
//                Direccion direccion = new Direccion();
//                direccion.setCalle(datos[14]);
//                direccion.setNumeroInterior(datos[15]);
//                direccion.setNumeroExterior(datos[16]);
//                direccion.Colonia = new Colonia();
//                direccion.Colonia.setIdColonia(Integer.parseInt(datos[17]));
//
//                // Asociar Usuario y Direccion al contenedor UsuarioDireccion
//                UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
//                usuarioDireccion.setUsuario(usuario);
//                usuarioDireccion.setDireccion(direccion);
//
//                usuarioDireccionList.add(usuarioDireccion);
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            usuarioDireccionList = null;
//        }
//
//        return usuarioDireccionList;
//    }
//
//    public List<UsuarioDireccion> LecturaArchivoExcel(File archivo) {
//        List<UsuarioDireccion> usuariosDireccion = new ArrayList<>();
//        try (XSSFWorkbook workbook = new XSSFWorkbook(archivo);) {
//            XSSFSheet sheet = workbook.getSheetAt(0);
//            for (Row row : sheet) {
//                UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
//                usuarioDireccion.Usuario = new Usuario();
//
//                usuarioDireccion.Usuario.setNombre(row.getCell(0) != null ? row.getCell(0).toString() : "");
//                usuarioDireccion.Usuario.setFechaNacimiento(row.getCell(1) != null ? row.getCell(1).getDateCellValue() : null);
//                usuarioDireccion.Usuario.setUserName(row.getCell(2) != null ? row.getCell(2).toString() : "");
//                usuarioDireccion.Usuario.setApellidoPaterno(row.getCell(3) != null ? row.getCell(3).toString() : "");
//                usuarioDireccion.Usuario.setApellidoMaterno(row.getCell(4) != null ? row.getCell(4).toString() : "");
//                usuarioDireccion.Usuario.setEmail(row.getCell(5) != null ? row.getCell(5).toString() : "");
//                usuarioDireccion.Usuario.setPassword(row.getCell(6) != null ? row.getCell(6).toString() : "");
//                usuarioDireccion.Usuario.setSexo(row.getCell(7) != null ? row.getCell(7).toString().charAt(0) : ' ');
//                usuarioDireccion.Usuario.setTelefono(row.getCell(8) != null ? row.getCell(8).toString() : "");
//                usuarioDireccion.Usuario.setCelular(row.getCell(9) != null ? row.getCell(9).toString() : "");
//                usuarioDireccion.Usuario.setCurp(row.getCell(10) != null ? row.getCell(10).toString() : "");
//
//                usuarioDireccion.Usuario.Roll = new Roll();
//                usuarioDireccion.Usuario.Roll.setIdRoll((int) row.getCell(11).getNumericCellValue());
//
//                usuarioDireccion.Usuario.setImagenPerfil(row.getCell(12) != null ? row.getCell(12).toString() : "");
//                usuarioDireccion.Usuario.setStatus((int) row.getCell(13).getNumericCellValue());
//
//                usuariosDireccion.add(usuarioDireccion);
//
//            }
//        } catch (Exception ex) {
//            System.out.println("Errores en apartura de archivo");
//        }
//
//        return usuariosDireccion;
//    }

    @PutMapping("/usuario")
    public ResponseEntity PutAll(@RequestBody UsuarioDireccion usuarioDireccion) {
        try {
            Result result = iUsuarioJPADAOImplementation.PutAll(usuarioDireccion);

            if (result.correct) {
                if (result.object != null) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(result.object);
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Usuario creado pero no retornado.");
                }

            } else {
                return ResponseEntity.status(HttpStatus.valueOf(400)).body(result);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.valueOf(500)).body(ex.getLocalizedMessage());
        }

    }

    @PutMapping("/Direccion")
    public ResponseEntity PUTDireccionByUsuario(@RequestBody UsuarioDireccion usuarioDireccion) {

        try {
            Result result = direccionJPADAOImplementation.PUTDireccionByUsuario(usuarioDireccion);

            if (result.correct) {
                if (result.object != null) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(result.object);
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Usuario creado pero no retornado.");
                }

            } else {
                return ResponseEntity.status(HttpStatus.valueOf(400)).body(result);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.valueOf(500)).body(ex.getLocalizedMessage());
        }
    }

    @DeleteMapping("/usuario")
    public ResponseEntity DELETEALL(@RequestBody UsuarioDireccion usuarioDireccion) {

        try {
            Result result = direccionJPADAOImplementation.PUTDireccionByUsuario(usuarioDireccion);

            if (result.correct) {
                if (result.object != null) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(result.object);
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Usuario creado pero no retornado.");
                }

            } else {
                return ResponseEntity.status(HttpStatus.valueOf(400)).body(result);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.valueOf(500)).body(ex.getLocalizedMessage());
        }
    }

}
