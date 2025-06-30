package com.risosu.EDesalesProgramacionNCapasJunio3.RequestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demoapi")
public class DemoRestController {

    @GetMapping("saludo")
    private String HolaMundo() {
        return "HolaMundo";
    }

    @GetMapping("sumaABurl/{n}/{m}")
    private String SumaDeElemtosURL(@PathVariable int n, @PathVariable int m) {
        int resultado = n + m;
        return "suma por URL es n: " + n + " + M: " + m + " es = " + resultado;
    }

    @PostMapping("sumaCuerpoProyecto")
    public String SumarValoresXCuerpoProyecto(@RequestBody CuerpoExperimento cuerpoExperimento) {
        int resultado = cuerpoExperimento.getNumero1() + cuerpoExperimento.getNumero2();

        return "Parametro n cuerpo es : " + cuerpoExperimento.getNumero1() + " + parametro m es: " + cuerpoExperimento.getNumero2() + " el resultado es = " + resultado;
    }

    @PatchMapping("/actualizarArregloPeticion/{indice}")
    public String actualizarArreglo(
            @PathVariable int indice,
            @RequestBody CuerpoExperimento cuerpoExperimento) {
        int nuevoValor = 0;

        int[] arreglo = cuerpoExperimento.getArregloNumeros();
        int longitud = arreglo.length;


        if (indice < 0 || indice >= arreglo.length) {
        
            StringBuilder sb = new StringBuilder("Índice fuera de rango. Arreglo completo: [");
            for (int i = 0; i < longitud; i++) {
                sb.append(arreglo[i]);
                if (i < longitud - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();

        }

        int valorAnterior = arreglo[indice];
        arreglo[indice] = nuevoValor;
        
        if (indice > 0 || indice <= arreglo.length) {
        
            StringBuilder sb = new StringBuilder("Arreglo: [");
            for (int i = 0; i < longitud; i++) {
                sb.append(arreglo[i]);
                if (i < longitud - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();

        }
        
        
        return "Valor actualizado en índice " + indice + ": " + valorAnterior + " -> " + nuevoValor;
    }
}
