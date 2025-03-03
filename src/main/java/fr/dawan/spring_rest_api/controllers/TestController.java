package fr.dawan.spring_rest_api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/test")
public class TestController {

    /*
    Doit founir des ressources (ends points) + méthodes d'accès
    Codes Status:
    1xx: infos fournies par le server
    2xx: succès de la req. HTTP
    3xx: redirection
    4xx: erreurs côté client
    5xx: erreurs côté server
     */

    //@RequestMapping(value = "/test/m1", method = RequestMethod.GET)
    @GetMapping(value = "/m1", produces = "text/plain")
    public String m1(){
        return "m1";
    }

    //@RequestMapping(value = "/test/m2", method = RequestMethod.GET)
    @GetMapping(value = "/m2", produces = "text/plain")
    public ResponseEntity<String> m2(){
        /*
        ResponseEntity<T>: permte de spécifier le type du contenu du body ainsi que le code status de la réponse
         */
        //Syntaxe 1:
        //return ResponseEntity.ok("m2");

        //Syntaxe 2: plus lisible
        return ResponseEntity.status(HttpStatus.OK).body("m2");
    }

    //Paramètres obligatoires

    @GetMapping(value = "/m3/{page}", produces = "text/plain")
    public ResponseEntity<String> m3(@PathVariable("page") int page){

        return ResponseEntity.status(HttpStatus.OK).body("m3 "+page);
    }

    //Paramètres optionnels: multi URIs

    @GetMapping(value = {"/m4/{page}", "/m4"}, produces = "text/plain")
    public ResponseEntity<String> m4(@PathVariable(value = "page", required = false) Optional<Integer> page){

        if(page.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("m4");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("m4 "+page.get());
        }

    }

    //Request Params: Paramètres nommés: localhost:8085/m5?page=55 ---- le param s'affiche dans l'uri (syntaxe non recommandée)

    @GetMapping(value = "/m5")
    public String m5(@RequestParam("page") int page){
        return "m5 "+page;
    }

    //Données fournies par le client

    @GetMapping(value = "/m6", consumes = "text/plain", produces = "text/plain")
    public String m6(@RequestBody String message){
        return "m6 "+message;
    }

}
