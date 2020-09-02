/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.Main;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.services.CinemaServices;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author AsusPC
 */
public class Main {
     public static void main(String[] args) throws BeansException, CinemaException, CinemaPersistenceException {		
     
    	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
      
    	CinemaServices cs = ac.getBean(CinemaServices.class);
        String functionDate = "2020-08-27 15:30";
        List<CinemaFunction> functions = new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("La vengaza de Andres", "Action"), functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("El entierro de la anaconda cabezona", "Horror"), functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c= new Cinema("CINE COLOMBIA", functions);
        cs.addNewCinema(c);
        System.out.println("Retorna el ultimo cinema agregado"+ " "+cs.getAllCinemas().get(cs.getAllCinemas().size()-1).getName());
        System.out.println("obtener las funciones del cinema CINE COLOMBIA");
        for(CinemaFunction k: cs.getFunctionsbyCinemaAndDate("CINE COLOMBIA", functionDate)){
            System.out.println(k.getMovie().getName());
        }
        System.out.println("Compra entradas CINE COLOMBIA, la venganza de Andres ");
        cs.buyTicket(0, 0, "CINE COLOMBIA",functionDate , "La vengaza de Andres");
        System.out.println(c.getFunctions().get(0).getSeats().get(0).get(0)+ "aparece false debido a que a esta comprado");
	System.out.println(cs.filter(c, "Horror", functionDate).size()+ "AQUII");
        
     }
}
