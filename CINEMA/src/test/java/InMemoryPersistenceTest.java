
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.impl.InMemoryCinemaPersistence;
import edu.eci.arsw.cinema.services.CinemaServices;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import junit.framework.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cristian
 */
public class InMemoryPersistenceTest {

    @Test
    public void saveNewAndLoadTest() throws CinemaPersistenceException{
        InMemoryCinemaPersistence ipct=new InMemoryCinemaPersistence();

        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("Movies Bogotá",functions);
        ipct.saveCinema(c);
        
        assertNotNull("Loading a previously stored cinema returned null.",ipct.getCinema(c.getName()));
        assertEquals("Loading a previously stored cinema returned a different cinema.",ipct.getCinema(c.getName()), c);
    }


    @Test
    public void saveExistingCinemaTest() {
        InMemoryCinemaPersistence ipct=new InMemoryCinemaPersistence();
        
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("Movies Bogotá",functions);
        
        try {
            ipct.saveCinema(c);
        } catch (CinemaPersistenceException ex) {
            fail("Cinema persistence failed inserting the first cinema.");
        }
        
        List<CinemaFunction> functions2= new ArrayList<>();
        CinemaFunction funct12 = new CinemaFunction(new Movie("SuperHeroes Movie 3","Action"),functionDate);
        CinemaFunction funct22 = new CinemaFunction(new Movie("The Night 3","Horror"),functionDate);
        functions.add(funct12);
        functions.add(funct22);
        Cinema c2=new Cinema("Movies Bogotá",functions2);
        try{
            ipct.saveCinema(c2);
            fail("An exception was expected after saving a second cinema with the same name");
        }
        catch (CinemaPersistenceException ex){
            
        }
                
        
    }
    @Test
    public void pruebaBuyTicket() throws CinemaPersistenceException, CinemaException{
        InMemoryCinemaPersistence ipct = new InMemoryCinemaPersistence();
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions = new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2", "Horror"), functionDate);
        functions.add(funct1);
        functions.add(funct2);
        
        Cinema c = new Cinema("Movies Bogotá", functions);
        ipct.saveCinema(c);
        //System.out.println(c+"AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
        //System.out.println(c.getFunctions().size()+"AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
        ipct.buyTicket(0, 0, "Movies Bogotá",functionDate , "SuperHeroes Movie 2");
        
        assertEquals(c.getFunctions().get(0).getSeats().get(0).get(0),false);
        
        
    }
    @Test
    public void pruebaGetFunctionsbyCinemaAndDate() throws CinemaPersistenceException, CinemaException{
        InMemoryCinemaPersistence ipct = new InMemoryCinemaPersistence();
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions = new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night 2", "Horror"), functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c = new Cinema("Movies Bogotá", functions);
        ipct.saveCinema(c);
        //System.out.println("ASDSADSADSAFSAFGR43RERER"+c.getFunctions().size());
        List<CinemaFunction> nueva = ipct.getFunctionsbyCinemaAndDate("Movies Bogotá", functionDate);
        // System.out.println(nueva.get(0)+" asdsadas "+nueva.get(0));
        assertEquals(nueva.get(0).getMovie().getName(),"SuperHeroes Movie 2");
        assertEquals(nueva.get(1).getMovie().getName(),"The Night 2");
        
 
    }
    @Test
    public void pruebaFilter() throws CinemaPersistenceException, CinemaException{
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
     
                
	//System.out.println(cs.filter(c, "0", functionDate).size()+ "AQUII");
        List<Movie> movies = cs.filter(c, "20", functionDate);
        System.out.println("ASDSADSADSAFSAFGR43RERER     "+movies.size());
        
        // System.out.println(nueva.get(0)+" asdsadas "+nueva.get(0));
        assertEquals(movies.get(0).getName(),"La vengaza de Andres");
        //assertEquals(nueva.get(1).getMovie().getName(),"The Night 2");
        
 
    }
}
